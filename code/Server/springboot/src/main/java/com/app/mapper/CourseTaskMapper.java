package com.app.mapper;



import com.app.entity.CourseTask;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface CourseTaskMapper {
    List<CourseTask> findAll(CourseTask courses);
    void insert(CourseTask courseTask);
    void update(CourseTask courseTask);
    void delete(CourseTask courseTask);
    void createTable(CourseTask TableName);
    void drop(CourseTask TableName);
}
