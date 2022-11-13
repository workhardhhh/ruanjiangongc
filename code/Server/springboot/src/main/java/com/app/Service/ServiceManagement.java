package com.app.Service;

import com.app.serinterface.DataService;
import com.app.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class ServiceManagement {
    @Autowired
    private Map<String, DataService> map;


    public String findAll(String dataType, String TableName)
    {
        System.out.println(TableName);
        DataService dataService = map.get(dataType);
        return dataService.findAll(TableName).toString();
    }
    public void insertCourse(String TableName, String Sname, String Tname, String Cname, String Sno, Integer id)
    {

        DataService dataService = map.get(Constant.COURSES);
        dataService.insert(TableName, Sname, Tname, Cname, Sno, id);
    }
    public void insertCourseStudent(String TableName, String Sno, String Name, String OpenId, String FormId)
    {
        DataService dataService = map.get(Constant.COURSE_STUDENT);
        dataService.insert(TableName, Sno, Name, OpenId, FormId);
    }
    public void insertCourseTask(String taskId, String content, String type, String TableName, Date date)
    {
        DataService dataService = map.get(Constant.COURSE_TASK);
        dataService.insert(taskId, content, type, TableName, date);
    }
    public void createTable(String dataType, String TableName)
    {
        DataService dataService = map.get(dataType);
        dataService.createTable(TableName);
    }
    public void drop(String dataType, String TableName)
    {
        DataService dataService = map.get(dataType);
        dataService.drop(TableName);
    }
}
