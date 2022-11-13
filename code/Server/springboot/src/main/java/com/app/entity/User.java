package com.app.entity;

public class User {
    private String password;
    private String student_id;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", student_id='" + student_id + '\'' +
                '}';
    }
}
