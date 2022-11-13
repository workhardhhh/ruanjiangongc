package com.app.controller;

import com.app.Service.ServiceManagement;
import com.app.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private ServiceManagement serviceManagement;

    @GetMapping("/courseStudent")
    public String findAllCourseStudent()
    {

        return serviceManagement.findAll(Constant.COURSE_STUDENT, "高等数学李白学生表");

    }

    @PostMapping("/courseStudent")
    public String modifyCourseStudent(@RequestBody Map<String, String> map)  {

        String Sno ;
        String Name ;
        String OpenId ;
        String FormId;
        String TableName;

        int status = Integer.parseInt(map.get("Status"));
        switch (status)
        {
            case 0:
                Sno = map.get("Sno");
                Name = map.get("Name");
                OpenId = map.get("OpenId");
                FormId = map.get("FormId");
                TableName = map.get("TableName");

                serviceManagement.insertCourseStudent(TableName, Sno, Name, OpenId, FormId);
                return "success";

            case 1:
                TableName = map.get("TableName");
                serviceManagement.createTable(Constant.COURSE_STUDENT, TableName);
                return "success";
            case 2:
                TableName = map.get("TableName");
                serviceManagement.drop(Constant.COURSE_STUDENT, TableName);
                return "success";
            case 3:

                break;
            case 4:
                break;
        }
        return "fail";
    }

}
