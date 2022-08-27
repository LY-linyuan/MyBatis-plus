package com.wkcto.plus;

import com.wkcto.plus.eneity.Student;
import com.wkcto.plus.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author 临渊
 * @Date 2022-08-27 15:09
 */

@SpringBootTest
@SuppressWarnings("all")
public class StudentTest {

    // 注入Mapper对象
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testInsert() {
        Student student = new Student();
        student.setName("T");
        student.setEmail("@T.C.com");
        student.setAge(17);
        student.setStatus(1);
        // insert into student ( name, age, email, status ) values ( ?, ?, ?, ? );
        int rows = studentMapper.insertStudent(student);
        System.out.println("insertStudent rows = " + rows);
    }

    @Test
    public void testSelectStudentById() {
        // select id, name, age, email, status from student where id=?
        Student student = studentMapper.selectStudentById(7);
        System.out.println("selectStudentById student = " + student);
    }

    @Test
    public void testSelectStudentByName() {
        // select id, name, age, email, status from student where name=?
        List<Student> studentList = studentMapper.selectByName("T");
        studentList.forEach(System.out::println);
    }

}
