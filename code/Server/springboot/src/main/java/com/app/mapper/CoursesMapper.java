package com.app.mapper;

import com.app.entity.Courses;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface CoursesMapper {

    List<Courses> findAll(Courses courses);
    void insert(Courses courses);
    void update(Courses courses);
    void delete(Courses Sno);
    void createTable(Courses TableName);
    void drop(Courses TableName);
}
