package com.app.Service;

import com.app.entity.CourseStudent;
import com.app.serinterface.DataService;
import com.app.mapper.CourseStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("CourseStudent")
public class CourseStudentService extends DataService<CourseStudent,String,String, Date> {
    @Autowired
    private CourseStudentMapper courseStudentMapper;
    @Autowired
    private CourseStudent courseStudent;

    @Override
    public List<CourseStudent> findAll(String TableName) {
        courseStudent.setTableName(TableName);
        return courseStudentMapper.findAll(courseStudent);
    }

    @Override
    public void insert(CourseStudent courseStudent) {
        courseStudentMapper.insert(courseStudent);
    }
    @Override
    public void insert(String TableName, String Sno, String Name, String OpenId, String FormId)
    {
        courseStudent.setTableName(TableName);
        courseStudent.setSno(Sno);
        courseStudent.setName(Name);
        courseStudent.setFormId(FormId);
        courseStudent.setOpenId(OpenId);
        courseStudentMapper.insert(courseStudent);
    }
    @Override
    public void update(CourseStudent courseStudent) {
        courseStudentMapper.update(courseStudent);
    }

    @Override
    public void delete(CourseStudent courseStudent) {
        courseStudentMapper.delete(courseStudent);
    }

    @Override
    public void createTable(String TableName) {
        courseStudent.setTableName(TableName);
        courseStudentMapper.createTable(courseStudent);
    }

    @Override
    public void drop(String TableName) {
        courseStudent.setTableName(TableName);
        courseStudentMapper.drop(courseStudent);
    }


    public CourseStudent getCourseStudent() {
        return courseStudent;
    }

    public void setCourseStudent(CourseStudent courseStudent) {
        this.courseStudent = courseStudent;
    }
}
