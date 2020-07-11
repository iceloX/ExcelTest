package com.icelo.dao;

import com.icelo.model.Student;

import java.util.List;
import java.util.Vector;

public interface StudentDao {
    void saveStudent(Student student);

    List<Student> selecAll();
}
