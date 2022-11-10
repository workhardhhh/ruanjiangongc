package com.mybatis.mapper;



import com.mybatis.entity.CourseTask;

import java.util.List;

public interface CourseTaskMapper {
    List<CourseTask> findAll(CourseTask courses);
    void insert(CourseTask courseTask);
    void update(CourseTask courseTask);
    void delete(CourseTask courseTask);
    void createTable(CourseTask TableName);
    void deleteTable(CourseTask TableName);
}
