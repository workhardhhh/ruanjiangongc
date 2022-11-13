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
public class CoursesController {
    @Autowired
    private ServiceManagement serviceManagement;

    @GetMapping("/courses")
    public String findAllCourses()
    {
        System.out.println("122");
        return serviceManagement.findAll(Constant.COURSES, "courses");

    }
    @PostMapping("/courses")
    public String modifyCourses(@RequestBody Map<String, String> map)
    {

        String Sname ;
        String Tname ;
        String Cname ;
        String Sno ;
        String TableName;
        Integer id;
        int status = Integer.parseInt(map.get("Status"));
        switch (status)
        {
            case 0:
                 Sname = map.get("Sname");
                 Tname = map.get("Tname");
                 Cname = map.get("Cname");
                 Sno = map.get("Sno");
                 TableName = map.get("TableName");
                 id = Integer.parseInt(map.get("id"));

                serviceManagement.insertCourse(TableName, Sname, Tname, Cname, Sno, id);
                return "success";

            case 1:
                TableName = map.get("TableName");
                serviceManagement.createTable(Constant.COURSES, TableName);
                return "success";
            case 2:
                TableName = map.get("TableName");
                serviceManagement.drop(Constant.COURSES, TableName);
                return "success";
            case 3:

                break;
            case 4:
                break;
        }
        return "fail";
    }




}
