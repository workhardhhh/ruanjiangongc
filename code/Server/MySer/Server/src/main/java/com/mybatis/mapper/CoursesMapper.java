package com.mybatis.mapper;

import com.mybatis.entity.Courses;

import java.util.List;

public interface CoursesMapper {

    List<Courses> findAll(Courses courses);
    void insert(Courses courses);
    void update(Courses courses);
    void delete(Courses Sno);
    void createTable(Courses TableName);
    void deleteTable(Courses TableName);
}
