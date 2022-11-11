package com.Interface;

import java.util.List;

public interface DataStrategy <E>{
    List<E> findAll(E courses) throws Exception;
    void insert(E courses) throws Exception;
    void update(E courses) throws Exception;
    void delete(E Sno) throws Exception;
    void createTable(E TableName) throws Exception;
    void deleteTable(E TableName)throws Exception;
}
