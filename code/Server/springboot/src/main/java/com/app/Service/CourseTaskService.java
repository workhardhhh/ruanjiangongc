package com.app.Service;

import com.app.entity.CourseTask;
import com.app.serinterface.DataService;
import com.app.mapper.CourseTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("CourseTask")
public class CourseTaskService extends DataService<CourseTask,String,Date,Integer > {
    @Autowired
    private CourseTaskMapper courseTaskMapper;
    @Autowired
    private CourseTask courseTask;

    @Override
    public List<CourseTask> findAll(String TableName) {
        courseTask.setTableName(TableName);
        return courseTaskMapper.findAll(courseTask);
    }

    @Override
    public void insert(CourseTask courseTask) {
        courseTaskMapper.insert(courseTask);
    }
    @Override
    public void insert(String taskId, String content, String type, String TableName, Date date) {
        courseTask.setTaskId(taskId);
        courseTask.setContent(content);
        courseTask.setTime(date);
        courseTask.setType(type);
        courseTask.setTableName(TableName);

        courseTaskMapper.insert(courseTask);

    }
    @Override
    public void update(CourseTask courseTask) {

    }

    @Override
    public void delete(CourseTask courseTask) {

    }

    @Override
    public void createTable(String TableName) {
        courseTask.setTableName(TableName);
        courseTaskMapper.createTable(courseTask);
    }

    @Override
    public void drop(String TableName) {
        courseTask.setTableName(TableName);
        courseTaskMapper.drop(courseTask);
    }

    public CourseTask getCourseTask() {
        return courseTask;
    }

    public void setCourseTask(CourseTask courseTask) {
        this.courseTask = courseTask;
    }
}
