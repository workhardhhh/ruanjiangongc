package com.app.mapper;

import com.app.entity.Courses;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface CoursesMapper {

    List<Courses> findAll(Courses courses);
    int selectByClass(@Param("TableName") String TableNmae);
    int selectMaxId(Courses courses);
    void insert(Courses courses);
    void update(Courses courses);
    void delete(Courses Sno);
    void createTable(Courses TableName);
    void drop(Courses TableName);

}
