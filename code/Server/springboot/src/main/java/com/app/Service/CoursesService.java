package com.app.Service;

import com.app.entity.Courses;
import com.app.serinterface.DataService;
import com.app.mapper.CoursesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("Courses")
public class CoursesService extends DataService<Courses,String,Integer, Date> {
    @Autowired
    private CoursesMapper coursesMapper;

    @Autowired
    private Courses courses;

    @Override
    public List<Courses> findAll(String TableName) {
        courses.setTableName(TableName);
        return coursesMapper.findAll(courses);
    }

    @Override
    public void insert(Courses courses) {
        coursesMapper.insert(courses);
    }


    @Override
    public void insert(String TableName, String Sname, String Tname, String Cname, String Sno, Integer id) {
        courses.setTableName(TableName);
        courses.setSno(Sno);
        courses.setSname(Sname);
        courses.setTname(Tname);
        courses.setCname(Cname);
        courses.setId(id);

        coursesMapper.insert(courses);
    }

    @Override
    public void update(Courses courses) {
        coursesMapper.update(courses);
    }

    @Override
    public void delete(Courses courses) {
        coursesMapper.delete(courses);
    }

    @Override
    public void createTable(String TableName) {
        courses.setTableName(TableName);
        coursesMapper.createTable(courses);
    }

    @Override
    public void drop(String TableName) {
        courses.setTableName(TableName);
        coursesMapper.drop(courses);
    }


    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }
}
