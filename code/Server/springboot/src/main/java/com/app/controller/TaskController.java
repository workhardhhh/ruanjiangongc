package com.app.controller;

import com.app.Service.ServiceManagement;
import com.app.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class TaskController {

    @Autowired
    private ServiceManagement serviceManagement;

    @GetMapping("/courseTask")
    public String findAllCourseStudent()
    {

        return serviceManagement.findAll(Constant.COURSE_TASK, "高等数学李白任务表");

    }

    @PostMapping("/courseTask")
    public String modifyCourseTask(@RequestBody Map<String, String> map) throws ParseException {

        String Content ;
        String TaskId ;
        String Type ;
        Date time ;
        String timeBuf;
        String TableName;

        int status = Integer.parseInt(map.get("Status"));
        switch (status)
        {
            case 0:
                Content = map.get("Content");
                TaskId = map.get("TaskId");
                Type = map.get("Type");
                timeBuf = map.get("Time");
                TableName = map.get("TableName");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                time = format.parse(timeBuf);

                serviceManagement.insertCourseTask(TaskId, Content, Type, TableName, time);
                return "success";

            case 1:
                TableName = map.get("TableName");
                serviceManagement.createTable(Constant.COURSE_TASK, TableName);
                return "success";
            case 2:
                TableName = map.get("TableName");
                serviceManagement.drop(Constant.COURSE_TASK, TableName);
                return "success";
            case 3:

                break;
            case 4:
                break;
        }
        return "fail";
    }
}
