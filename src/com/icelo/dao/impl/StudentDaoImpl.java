package com.icelo.dao.impl;

import com.icelo.dao.StudentDao;
import com.icelo.model.Student;
import com.icelo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class StudentDaoImpl implements StudentDao {
    //导入到数据库
    @Override
    public void saveStudent(Student student) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="insert into student (id,name,cls) values(?,?,?)";
        try {
            connection= DBUtils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,student.getId());
            preparedStatement.setString(2,student.getName());
            preparedStatement.setString(3,student.getCls());
            int i=preparedStatement.executeUpdate();
            if(i>0){
                System.out.println("成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> selecAll() {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sql="select * from student";
        List<Student> list=new ArrayList<Student>();
        Student student=null;
        try {
            connection=DBUtils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
               int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String cls=resultSet.getString("cls");
                student=new Student(id,name,cls);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
