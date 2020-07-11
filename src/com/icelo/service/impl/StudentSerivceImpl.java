package com.icelo.service.impl;

import com.icelo.dao.StudentDao;
import com.icelo.dao.impl.StudentDaoImpl;
import com.icelo.model.Student;
import com.icelo.service.StudentSerivce;

import java.util.List;
import java.util.Vector;

public class StudentSerivceImpl implements StudentSerivce {

    StudentDao dao=new StudentDaoImpl();
    @Override
    public void saveStudent(List<Student> students) {
        for (Student student : students) {
            dao.saveStudent(student);
        }
    }

    @Override
    public List<Student> selectAll() {
        return dao.selecAll();
    }
}
