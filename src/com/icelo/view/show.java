package com.icelo.view;

import com.icelo.model.Student;
import com.icelo.service.StudentSerivce;
import com.icelo.service.impl.StudentSerivceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class show {

    public static void main(String[] args) {
        StudentSerivce ss = new StudentSerivceImpl();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入你要选择的功能 1：导入 2：到出");
        int i = input.nextInt();
        /**
         * 导入功能实现
         */
        if (i == 1) {
            System.out.println("请输入导入文件的路径：");
            String path = input.next();
            List<Student> students = save(path);
            ss.saveStudent(students);
            System.out.println("当前数据已经全部存入数据库中！");
        } else if (i == 2) {
            System.out.println("请选择储存位置！");
            String path = input.next();
            List<Student> list = ss.selectAll();
            export(list, path);
        } else {
            System.out.println("输入有错误！");
        }
    }

    /**
     * 从数据库中导出数据信息到Excel中实现保存
     * @param list 从数据库中查询到的数据（学生类的集和）
     * @param path 需要保存文件的位置
     */
    private static void export(List<Student> list, String path) {

        //获取工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //获取一个工作表，取名为学生
        XSSFSheet sheet = xssfWorkbook.createSheet("学生");
        //因为Excel的第一行不存储数据，为每一列的命名，一般是数据库的字段类似
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("班级");

        for (int i = 0; i < list.size(); i++) {
            //从第二行开始遍历
            XSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(list.get(i).getId());
            row1.createCell(1).setCellValue(list.get(i).getName());
            row1.createCell(2).setCellValue(list.get(i).getCls());
        }
        try {
            //通过文件输出流写入文件
            FileOutputStream f = new FileOutputStream(path);
            //调用XSSFWorkbook的write()方法
            xssfWorkbook.write(f);
            f.flush();//刷新
            xssfWorkbook.close();//关闭资源
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 从Excel导入数据到数据库！
     *
     * @param path 需要传入的Excel的文件位置
     * @return 返回一个实体类集和（我这里是用学生实体类）
     */
    public static List<Student> save(String path) {
        List<Student> studentList = new ArrayList<Student>();//用于存取导入的学生信息
        try {
            //获取工作簿
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(path);
            //获取工作表
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            //得到工作表的行数
            int lastRow = xssfSheet.getLastRowNum();
            //遍历
            for (int i = 1; i <= lastRow; i++) {
                XSSFRow row = xssfSheet.getRow(i);
                //获取每一行，不为空的情况下，遍历每一个单元格
                if (row != null) {
                    List<String> list = new ArrayList<String>();
                    //在每一行中遍历每一个单元格
                    for (Cell cell : row) {
                        if (cell != null) {
                            cell.setCellType(CellType.STRING);//控制字符格式为String
                            String value = cell.getStringCellValue();
                            if (value != null && !"".equals(value)) {//如果单元格中字符不为空添加到list集和中
                                list.add(value);
                            }
                        }
                    }
                    if (list.size() > 0) {
                        //通过构造函数封装得到的数据
                        Student student = new Student(Integer.parseInt(list.get(0)), list.get(1), list.get(2));
                        studentList.add(student);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentList;
    }
}
