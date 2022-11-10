package com.CouFactory;

import com.mybatis.entity.CourseStudent;
import com.mybatis.entity.CourseTask;
import com.mybatis.entity.Courses;

import java.io.Serializable;
import java.sql.Date;

public class CoursesFactory  {


    public Courses getCourses(int id, String Cname, String Tname, String Sno, String Sname, String TableName) {
        Courses courses = new Courses();
        courses.setSname(Sname);
        courses.setCname(Cname);
        courses.setId(id);
        courses.setTableName(TableName);
        courses.setTname(Tname);
        courses.setSno(Sno);
        return courses;
    }

    public CourseTask getCourseTask(String TaskId, String Content, String TableName, String Type, Date time) {
        CourseTask courseTask = new CourseTask();
        courseTask.setTaskId(TaskId);
        courseTask.setContent(Content);
        courseTask.setTableName(TableName);
        courseTask.setType(Type);
        courseTask.setTime(time);
        return courseTask;
    }
    public CourseStudent getCourseStudent(String Name, String URL, String Sno, String TableName) {
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setName(Name);
        courseStudent.setSno(Sno);
        courseStudent.setTableName(TableName);
        courseStudent.setURL(URL);
        return courseStudent;
    }

}
