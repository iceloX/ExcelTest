package com.icelo.model;

public class Student {
    private int id;
    private  String name;
    private String cls;

    public Student(int id, String name, String cls) {
        this.id = id;
        this.name = name;
        this.cls = cls;
    }

    public Student(String name, String cls) {
        this.name = name;
        this.cls = cls;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCls() {
        return cls;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cls='" + cls + '\'' +
                '}';
    }
}
