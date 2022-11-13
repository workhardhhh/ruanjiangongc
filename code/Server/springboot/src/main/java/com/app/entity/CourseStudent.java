package com.app.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class CourseStudent implements Serializable {
    private String TableName;
    private String Sno;
    private String Name;
    private String OpenId;
    private String FormId;

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getOpenId() {
        return OpenId;
    }

    public void setOpenId(String openId) {
        OpenId = openId;
    }

    public String getFormId() {
        return FormId;
    }

    public void setFormId(String formId) {
        FormId = formId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "CourseStudent{" +
                "TableName='" + TableName + '\'' +
                ", Sno='" + Sno + '\'' +
                ", Name='" + Name + '\'' +
                ", OpenId='" + OpenId + '\'' +
                ", FormId='" + FormId + '\'' +
                '}';
    }
}
