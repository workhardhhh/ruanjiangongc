package com.app.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
@Component
public class CourseTask implements Serializable {
    private String TableName;
    private String Content;
    private String TaskId;
    private Date Time;
    private String Type;

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTaskId() {
        return TaskId;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "CourseTask{" +
                "TableName='" + TableName + '\'' +
                ", Content='" + Content + '\'' +
                ", TaskId='" + TaskId + '\'' +
                ", Time=" + Time +
                ", Type='" + Type + '\'' +
                '}';
    }
}
