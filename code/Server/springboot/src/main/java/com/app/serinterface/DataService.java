package com.app.serinterface;

import java.util.List;

public abstract class DataService<E,F,G,K> {
    public abstract List<E> findAll(String TableName);
    public abstract void insert(E e);
    public  void insert(F f1,F f2,F f3,F f4,F f5, G g)
    {
        System.out.println("course insert");
    }
    public  void insert(F f1,F f2,F f3,F f4, G g)
    {
        System.out.println("courseTask insert\ncourseStudent insert");
    }



    public abstract void update(E e);
    public abstract void delete(E e);
    public abstract void createTable(String TableName);
    public abstract void drop(String TableName);

}
