package com.mybatis.entity;

import java.io.Serializable;

public class Courses implements Serializable {
    private String Sno;
    private  String Sname;
    private  String Cname;
    private String Tname;
    private String TableName;
    private int id;

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "Sno='" + Sno + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Cname='" + Cname + '\'' +
                ", Tname='" + Tname + '\'' +
                ", TableName='" + TableName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
