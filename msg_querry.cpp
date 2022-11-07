#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<pthread.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include"http_down.h"


#define MAX_URL_LEN (200)
#define MAX_PATH_LEN (100)
#define MAX_TASK_CNT (10)
#define MSG_KEY 60002  //队列标识
#define MSG_DATA_LEN 128

static pthread_mutex_t task_slist_mutex;
//下载队列优先级
typedef enum
{
	PRIO_NORMAL,
	PRIO_HIGH,
}DOWNLOAD_PRIO;

typedef struct
{
	void** items;
	int item_maxcount;
	int item_count;
	int item_size;
}__Z_SLIST;
typedef void* Z_SLIST;
typedef struct _DOWNLOAD_TASK
{
	char url[MAX_URL_LEN];
	char save_path[MAX_PATH_LEN];
}__download_task;
struct message {
	long msg_type;
	char url[MSG_DATA_LEN];
	char save_pate[MAX_PATH_LEN];
};
enum MSG_TYPE
{
	MSG_TYPE = 1,
};

static Z_SLIST task_slist = NULL;

pthread_t tid = 0; //线程唯一ID号
void* download_thread(void* arg);//实际下载线程体函数
int init_service(void);//下载服务初始化函数
int deinit_service(void);//下载服务反初始化函数
int task_add(char* url, char* save_path, DOWNLOAD_PRIO prio);//将下载路径加入下载队列
int task_slist_add(Z_SLIST list, const void* item);//添加下载队列函数
void* task_slist_get(Z_SLIST list, int idx);//获取消息队列函数
int task_slist_delete(Z_SLIST list, int idx);//删除下载队列函数
Z_SLIST task_slist_new(int item_size, int item_maxcount);//创建下载队列函数
int task_slist_insert(Z_SLIST list, const void* item, int idx);//插入队列函数

Z_SLIST task_slist_new(int item_size, int item_maxcount)
{
	__Z_SLIST* list = NULL;
	int itemstotalsize = item_size * item_maxcount;

	list = (__Z_SLIST*)malloc(sizeof(__Z_SLIST));
	list->items = (void**)malloc(itemstotalsize);

	list->item_count = 0;
	list->item_maxcount = item_maxcount;
	list->item_size = item_size;

	return(Z_SLIST)list;
}
//删除下载任务
int task_slist_delete(Z_SLIST list, int idx)
{
	__Z_SLIST* l = (__Z_SLIST*)list;
	if (idx >= 0 && idx < l->item_count)
	{
		int i = 0;
		for (i = idx; i < l->item_count - 1; i++)
		{
			memcpy((char*)l->items + i * l->item_size, (char*)l->items + (i + 1) * l->item_size, (int)l->item_size);
		}
		l->item_count--;
	}
	return 0;
}
void* download_thread(void* arg)
{

	int  qid = 0;
	__download_task* task = NULL;
	struct message msg;
	/*步骤1：创建消息队列*/
	if ((qid = msgget(MSG_KEY, 0666 | IPC_CREAT)) == -1)
	{
		perror("msgget");
		exit(1);
	}//0666是指赋予所有用户读写权限
	while (1) {
		pthread_mutex_lock(&task_slist_mutex);
		/*步骤2：读取消息队列*/
		memset(&msg, 0, sizeof(msg));
		int i = msgrcv(qid, &msg, MSG_DATA_LEN, 0, IPC_NOWAIT);
		if ( i>= 0)
		{
			task = (__download_task*)task_slist_get(task_slist, 0);
			if (task != NULL)
			{
				printf("New download task:\n");
				printf("Dwonloading... url=%s\n", task->url);
				sleep(1);
				printf("Finish! save_path=%s\n\n", task->save_path);
				task_slist_delete(task_slist, 0);
				http_download(task->url,task->save_path);
			}
			printf("msg.url=%s,msg.save_path=%s\n", msg.url, msg.save_pate);
			
		}
		printf("i=%d", i);
		pthread_mutex_unlock(&task_slist_mutex);
		sleep(1);
	}
	return NULL;
}
int init_service(void)
{
	task_slist = task_slist_new(sizeof(__download_task), MAX_TASK_CNT);
	pthread_create(&tid, NULL, download_thread, NULL);//创建下载线程
	return tid;
}

int deinit_service(void)
{
	int ret = -1;

	ret = pthread_cancel(tid);
	if (0 == ret)
	{
		pthread_join(tid, NULL);
	}
	return ret;
}
int task_add(char* url, char* save_path, DOWNLOAD_PRIO prio)
{
	int ret = -1;
	int qid = 0;
	__download_task task = { 0 };
	struct message msg;
	strncpy(task.url, url, sizeof(task.url));
	strncpy(task.save_path, save_path, sizeof(task.save_path));
	pthread_mutex_lock(&task_slist_mutex);
	if (prio == PRIO_HIGH)
	{
		/*步骤4：若当前通话状态为挂断则推出任务，并发送消息给UI*/
			/*添加消息到消息队列*/
			if ((qid = msgget(MSG_KEY, IPC_EXCL))>=0)
			{
				printf("Send quit msg!\n");
				memset(&msg, 0, sizeof(msg));
				msg.msg_type = MSG_TYPE;
				strncpy(msg.url, url, sizeof(task.url));
				strncpy(msg.save_pate, save_path, sizeof(msg.save_pate));
				msgsnd(qid, &msg, MSG_DATA_LEN, 0);
			}
			
		
		ret = task_slist_insert(task_slist, &task, 0);
	}
	else
	{
		/*步骤4：若当前通话状态为挂断则推出任务，并发送消息给UI*/
			/*添加消息到消息队列*/
		if ((qid = msgget(MSG_KEY, IPC_EXCL))>=0)
		{
			printf("Send quit msg!\n");
			memset(&msg, 0, sizeof(msg));
			msg.msg_type = MSG_TYPE;
			strncpy(msg.url, url, sizeof(task.url));
			strncpy(msg.save_pate, save_path, sizeof(msg.save_pate));
			msgsnd(qid, &msg, MSG_DATA_LEN, 0);
		}
		ret = task_slist_add(task_slist, &task);

	}
	pthread_mutex_unlock(&task_slist_mutex);
	return ret;
}

int task_slist_add(Z_SLIST list, const void* item)
{
	__Z_SLIST* l = (__Z_SLIST*)list;
	if (l->item_count >= l->item_maxcount)
	{
		return -1;
	}
	if (l->item_count < l->item_maxcount)
	{
		memcpy((char*)l->items + l->item_count * l->item_size, item, (int)l->item_size);
		l->item_count++;
	}
	return 0;
}
//获取下载任务
void* task_slist_get(Z_SLIST list, int idx)
{
	__Z_SLIST* l = (__Z_SLIST*)list;
	if (!(idx >= 0) && (idx < l->item_count))
	{
		return NULL;
	}
	return (void*)((char*)l->items + idx * l->item_size);
}
int task_slist_insert(Z_SLIST list, const void* item, int idx)
{
	__Z_SLIST* l = (__Z_SLIST*)list;
	if (l->item_count >= l->item_maxcount)
	{
		return -1;
	}
	if (idx >= 0 && idx <= l->item_count)
	{
		int i = 0;
		for (i = l->item_count; i < idx; i++)
		{
			memcpy((char*)l->items + i * l->item_size, (char*)l->items + (i - 1) * l->item_size, (int)l->item_size);
		}
		memcpy((char*)l->items + idx * l->item_size, item, (int)l->item_size);
		l->item_count++;
	}
	return idx;

}

int init_querry()
{
	int qid = 0;
	int len = 0;
	int time = 0;
	struct message msg;
	/*步骤1：创建消息队列*/
	if ((qid = msgget(MSG_KEY, 0666 | IPC_CREAT)) == -1)
	{
		perror("msgget");
		exit(1);
	}//0666是指赋予所有用户读写权限

}
int main()
{
	if (pthread_mutex_init(&task_slist_mutex, NULL) != 0)
	{
		printf("Init mutex error.\n");
		return 1;
	}
	init_service();		//初始化下载服务

	task_add("http://39.108.177.3:8080/images/bill.jpg", "bill.jpg", PRIO_NORMAL);
	//task_add("http://video2", "/sdcard/video2", PRIO_NORMAL);
	sleep(1);
	//task_add("http://video3", "/sdcard/video3", PRIO_HIGH);
	sleep(8); //休眠5秒


	deinit_service();	//反初始化下载服务
	pthread_mutex_destroy(&task_slist_mutex);
	return 0;
}
