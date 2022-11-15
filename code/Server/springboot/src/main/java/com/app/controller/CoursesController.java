package com.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.Service.ServiceManagement;
import com.app.logic.LoginLogic;
import com.app.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class CoursesController {
    @Autowired
    private ServiceManagement serviceManagement;
    @Autowired
    private LoginLogic loginLogic;

    @GetMapping("/courses")
    public String findAllCourses()
    {
        System.out.println("122");
        Integer id = serviceManagement.selectMaxId(Constant.COURSES, "courses");
        return serviceManagement.findAll(Constant.COURSES, "courses") + id;

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
                TableName = map.get("TableName");
                return serviceManagement.findAll(Constant.COURSES, TableName);
            case 4:
                break;
        }
        return "fail";
    }

    @PostMapping("/courses/user")
    public String userTest(@RequestBody Map<String, String> map) throws IOException, InterruptedException {
        String user = map.get("Student_id");
        String password = map.get("Password");
        System.out.println(user);
        System.out.println(password);
        String[] args1 = new String[] { "python", "C:\\Users\\ouyang\\Test1.py", user, password };
        Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gb2312"));
        String line = null;
        StringBuffer buf = new StringBuffer();
        while ((line = in.readLine()) != null) {
            System.out.println(line + "\n");
            buf.append(line);
        }
        JSONObject json=(JSONObject) JSONObject.toJSON(JSON.parse(buf.toString()));

        //  System.out.println(json.get("1"));
        System.out.println(json);
        JSONObject course_data = json.getJSONObject("parsel_data");

        loginLogic.JsonParse(course_data, user);
        System.out.println(course_data.toString());



        in.close();
        proc.waitFor();
        return course_data.toString();

    }

    @GetMapping("/courses/user")
    public String getUserTest() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("课表.json"));
        String line = null;
        StringBuffer buf = new StringBuffer();
        while ((line = in.readLine()) != null) {
            System.out.println(line + "\n");
            buf.append(line);
        }
        JSONObject json=(JSONObject) JSONObject.toJSON(JSON.parse(buf.toString()));

        //  System.out.println(json.get("1"));
        System.out.println(json);
        JSONObject course_data = json.getJSONObject("parsel_data");


        loginLogic.JsonParse(course_data, "102192113");
        System.out.println(course_data.toString());



        in.close();
        return  course_data.toString();

    }

}
