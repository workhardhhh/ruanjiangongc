package com.app.mapper;

import com.app.entity.CourseStudent;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CourseStudentMapper  {
    List<CourseStudent> findAll(CourseStudent courses);
    void insert(CourseStudent courseStudent);
    void update(CourseStudent courseStudent);
    void delete(CourseStudent courseStudent);
    void createTable(CourseStudent TableName);
    void drop(CourseStudent TableName);
}
