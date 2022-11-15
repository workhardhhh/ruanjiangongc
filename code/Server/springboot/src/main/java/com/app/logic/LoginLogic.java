package com.app.logic;

import com.alibaba.fastjson.JSONObject;
import com.app.Service.ServiceManagement;
import com.app.entity.Courses;
import com.app.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class LoginLogic {
    @Autowired
    ServiceManagement serviceManagement;

    private static Map<String, String> classInfo;
    static {
        classInfo = new HashMap<>();
    }
    private int autoCreateTableByClass(String TableName, String Tname, String Cname,String Sname, String Sno, String OpenId, String FormId)
    {

        String TandC = Cname + Tname  ;

        String TaskTable = TandC.concat("任务") ;
        String StudentTable = TandC +"学生";

        System.out.println(Cname);
        System.out.println(Constant.COURSES);
        System.out.println("TableName" + TaskTable);
        int courses = serviceManagement.selectCourses(Constant.COURSES, TaskTable, Tname, Cname);
        System.out.println("couflag" + courses);
        if (courses <= 0)
        {
            Integer id = serviceManagement.selectMaxId(Constant.COURSES, TableName) + 1;
            serviceManagement.insertCourse(TableName, Sname, Tname, Cname, Sno, id);
            System.out.println("insert courses success!\n");
            serviceManagement.createTable(Constant.COURSE_TASK, TaskTable);
            serviceManagement.createTable(Constant.COURSE_STUDENT, StudentTable);
            serviceManagement.insertCourseStudent(StudentTable, Sno, Sname, OpenId, FormId);
            return 1;
        }

        return 0;
    }

    public void JsonParse(JSONObject sentence, String Sno)
    {

        System.out.println(sentence);
        for (int i = 0;i < 11;i++)
        {
            List<String> stringList = sentenceParse(sentence.getJSONArray("星期一").get(i).toString()) ;

            if (stringList != null)
            {
                System.out.println(stringList.get(1));
                classInfo.put(stringList.get(0), stringList.get(1));
                stringList.clear();
            }
            stringList = sentenceParse(sentence.getJSONArray("星期二").get(i).toString()) ;
            if (stringList != null)
            {
                classInfo.put(stringList.get(0), stringList.get(1));
                stringList.clear();
            }
            stringList = sentenceParse(sentence.getJSONArray("星期三").get(i).toString()) ;
            if (stringList != null)
            {
                classInfo.put(stringList.get(0), stringList.get(1));
                stringList.clear();
            }
            stringList = sentenceParse(sentence.getJSONArray("星期四").get(i).toString()) ;
            if (stringList != null)
            {
                classInfo.put(stringList.get(0), stringList.get(1));
                stringList.clear();
            }
            stringList = sentenceParse(sentence.getJSONArray("星期五").get(i).toString()) ;
            if (stringList != null)
            {
                classInfo.put(stringList.get(0), stringList.get(1));
                stringList.clear();
            }
            stringList = sentenceParse(sentence.getJSONArray("星期六").get(i).toString()) ;
            if (stringList != null)
            {
                classInfo.put(stringList.get(0), stringList.get(1));
                stringList.clear();
            }
            System.out.println("星期日" + sentence.getJSONArray("星期日").get(i).toString());
            stringList = sentenceParse(sentence.getJSONArray("星期日").get(i).toString()) ;
            if (stringList != null)
            {
                classInfo.put(stringList.get(0), stringList.get(1));
                stringList.clear();
            }

        }
        for (Map.Entry<String, String> map:classInfo.entrySet())
        {
               String mapKey = map.getKey().trim();
               String mapValue = map.getValue().trim();
               System.out.println("Course" + mapKey + "teacher" + mapValue);
               autoCreateTableByClass("courses", mapValue, mapKey, "", Sno, "", "");
        }

    }

    private List<String> sentenceParse(String string)
    {

        List<String> stringList = new LinkedList<String>();
        String arr[] = string.split("\":\"");

        String classMessage = arr[1];
        String teacher = null;
        String tmp_arr[] = classMessage.split("[\\[\\]]");

        if (!checkname(tmp_arr[0].substring(tmp_arr[0].length() - 1)) && !Character.isLetter(tmp_arr[0].charAt(tmp_arr[0].length() - 1)))
        {
            System.out.println(tmp_arr[0].substring(0, tmp_arr[0].length() -1 ));
            tmp_arr[0] = tmp_arr[0].substring(0, tmp_arr[0].length() - 1);
        }
        String classLocation = tmp_arr[0];
        teacher = judgeTeacher(tmp_arr);
        if (teacher  == null)
        {
            return null;
        }

        stringList.add(classLocation);
        stringList.add(teacher);
        System.out.println(classLocation + "class");
        System.out.println(teacher + "teacher");
        return stringList;

    }
    private String judgeTeacher(String[] arr)
    {
        System.out.println(arr);
        for (String str:arr)
        {
            if (str.indexOf("-") >= 0 && str.indexOf("旗山") < 0)
            {
                String[] tmp = str.split("\\d");
                return tmp[0].replaceAll(",","A");
            }
        }
        return null;
    }

    public boolean checkname(String name)

    {

        int n = 0;

        for(int i = 0; i < name.length(); i++) {

            n = (int)name.charAt(i);

            if(!(19968 <= n && n <40869)) {

                return false;

            }

        }

        return true;

    }

}
