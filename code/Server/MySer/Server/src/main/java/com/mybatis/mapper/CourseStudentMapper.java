package com.mybatis.mapper;

import com.mybatis.entity.CourseStudent;

import java.util.List;


public interface CourseStudentMapper {
    List<CourseStudent> findAll(CourseStudent courses);
    void insert(CourseStudent courseStudent);
    void update(CourseStudent courseStudent);
    void delete(CourseStudent courseStudent);
    void createTable(CourseStudent TableName);
    void deleteTable(CourseStudent TableName);
}
