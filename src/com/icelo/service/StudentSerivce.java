package com.icelo.service;

import com.icelo.model.Student;

import java.util.List;
import java.util.Vector;

public interface StudentSerivce {
    void saveStudent(List<Student> students);


    List<Student> selectAll();
}
