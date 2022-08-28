package com.wkcto.plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wkcto.plus.eneity.Student;
import com.wkcto.plus.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testAllEq() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        // 组装条件
        Map<String, Object> map = new HashMap<String, Object>();
        // key 字段名 value 查询的值
        map.put("name", "T");
        map.put("age", 17);
        qw.allEq(map);
        // 调用MP自己的查询方法
        // SELECT id,name,age,email,status FROM student WHERE (name = ? AND age = ?)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * Map 对象中有key的value是null
     *  使用的是        qw.allEq(map, true);
     *      结果
     * SELECT id,name,age,email,status FROM student WHERE (name = ? AND age IS NULL)
     *  使用        qw.allEq(map, false);
     * SELECT id,name,age,email,status FROM student WHERE (name = ?)
     *  使用        qw.allEq(map);
     * SELECT id,name,age,email,status FROM student WHERE (name = ? AND age IS NULL)
     *
     *      结论 : true 处理null值
     *           : false 忽略null值  不作为where条件
     */
    @Test
    public void testAllEq2() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        // 组装条件
        Map<String, Object> map = new HashMap<String, Object>();
        // key 字段名 value 查询的值
        map.put("name", "T");
        // age是null
        map.put("age", null);
        // allEq第二个参数为true
        qw.allEq(map, true);
        // SELECT id,name,age,email,status FROM student WHERE (name = ? AND age IS NULL)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试eq方法的使用
     * eq("列名", "值")
     */
    @Test
    public void testEq() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        // 组装条件
        qw.eq("name", "T");
        // SELECT id,name,age,email,status FROM student WHERE (name = ?)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试ne方法的使用
     * ne  表示不等于
     * eq("列名", "值")
     */
    @Test
    public void testNe() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        // 组装条件
        qw.ne("name", "zhangsan");
        // SELECT id,name,age,email,status FROM student WHERE (name <> ?)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }


    /**
     * 测试gt方法的使用
     * gt  大于
     * gt("列名", "值")
     */
    @Test
    public void testGt() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        // 组装条件
        qw.gt("age", 15);
        // SELECT id,name,age,email,status FROM student WHERE (age > ?)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试ge方法的使用
     * ge  大于等于
     * ge("列名", "值")
     */
    @Test
    public void testGe() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        // 组装条件
        qw.ge("age", 17);
        // SELECT id,name,age,email,status FROM student WHERE (age >= ?)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试lt方法的使用
     * lt  小于
     * lt("列名", "值")
     */
    @Test
    public void testLt() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        // 组装条件
        qw.lt("age", 22);
        // SELECT id,name,age,email,status FROM student WHERE (age < ?)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试le方法的使用
     * le  小于等于
     * le("列名", "值")
     */
    @Test
    public void testLe() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        // 组装条件
        qw.le("age", 22);
        // SELECT id,name,age,email,status FROM student WHERE (age <= ?)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试Between方法的使用
     * Between
     * qw.between("age", 开始值, 结束值);  包含初值和结束值
     */
    @Test
    public void testBetween() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        // qw.between("age", 开始值, 结束值);  包含初值和结束值
        qw.between("age", 17, 22);
        // SELECT id,name,age,email,status FROM student WHERE (age BETWEEN ? AND ?)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试NotBetween方法的使用
     * NotBetween
     * qw.notBetween("age", 开始值, 结束值);  包含初值和结束值
     * 闭区间 除去 age >= 25 and age <= 100
     * age < 25 and age > 100
     */
    @Test
    public void testNotBetween() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        // qw.notBetween("age", 开始值, 结束值);  包含初值和结束值
        // 闭区间 除去 age >= 25 and age <= 100
        // age < 25 and age > 100
        qw.notBetween("age", 25, 100);
        // SELECT id,name,age,email,status FROM student WHERE (age NOT BETWEEN ? AND ?)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试like方法的使用
     *  匹配某个值
     *
     */
    @Test
    public void testLike() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.like("name", "zhang");
        // SELECT id,name,age,email,status FROM student WHERE (name LIKE ?)
        //==> Parameters: %zhang%(String)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试notlike方法的使用
     *  匹配某个值
     *
     */
    @Test
    public void testNotLike() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.notLike("name", "zhang");
        // SELECT id,name,age,email,status FROM student WHERE (name NOT LIKE ?)
        //==> Parameters: %zhang%(String)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试likeLeft方法的使用
     *  匹配某个值
     *
     */
    @Test
    public void testLikeLeft() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.likeLeft("name", "zhang");
        // Preparing: SELECT id,name,age,email,status FROM student WHERE (name LIKE ?)
        //==> Parameters: %zhang(String)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试likeRight方法的使用
     *  匹配某个值
     *
     */
    @Test
    public void testLikeRight() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.likeRight("name", "zhang");
        // Preparing: SELECT id,name,age,email,status FROM student WHERE (name LIKE ?)
        //==> Parameters: zhang%(String)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试isNull方法的使用
     *
     */
    @Test
    public void testIsNull() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.isNull("status");
        // SELECT id,name,age,email,status FROM student WHERE (status IS NULL)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试isNull方法的使用
     *
     */
    @Test
    public void testIsNotNull() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.isNotNull("status");
        // SELECT id,name,age,email,status FROM student WHERE (status IS NOT NULL)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试in方法的使用
     *
     */
    @Test
    public void testIn() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.in("name", "张三", "zhangsan");
        // SELECT id,name,age,email,status FROM student WHERE (name IN (?,?))
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    @Test
    public void testIn2() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        List<Object> list = new ArrayList<Object>();
        list.add("张三");
        list.add("zhangsan");
        qw.in("name", list);
        // SELECT id,name,age,email,status FROM student WHERE (name IN (?,?))
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试notIn方法的使用
     *
     */
    @Test
    public void testNotIn() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.notIn("name", "张三", "zhangsan");
        // Preparing: SELECT id,name,age,email,status FROM student WHERE (name IN (?,?))
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试inSql方法的使用
     *
     */
    @Test
    public void testInSql() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.inSql("age", "select age from student where id = 1 ");
        // SELECT id,name,age,email,status FROM student WHERE (age IN (select age from student where id = 1 ))
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试notInSql方法的使用
     *
     */
    @Test
    public void testNotInSql() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.notInSql("age", "select age from student where id = 1 ");
        // SELECT id,name,age,email,status FROM student WHERE (age NOT IN (select age from student where id = 1 ))
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试groupBy方法的使用
     *
     */
    @Test
    public void testGroupBy() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.select("name, count(*) personNumbers"); // select name, Count(*) personNumbers
        qw.groupBy("name");
        // SELECT name, count(*) personNumbers FROM student GROUP BY name
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试orderAsc方法的使用
     *
     */
    @Test
    public void testOrderAsc() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.orderByAsc("age");
        // SELECT id,name,age,email,status FROM student ORDER BY age ASC
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试orderAsc方法的使用
     *
     */
    @Test
    public void testOrderAsc2() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.orderByAsc("age", "name");  // 可以多个字段
        // SELECT id,name,age,email,status FROM student ORDER BY age ASC,name ASC
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试orderDesc方法的使用
     *
     */
    @Test
    public void testOrderByDesc() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.orderByDesc("age");
        // SELECT id,name,age,email,status FROM student ORDER BY age DESC
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * 测试orderBy方法的使用
     *   指定排序方向
     *    第一个参数为真 SELECT id,name,age,email,status FROM student ORDER BY age ASC
     *    第一个参数为假 SELECT id,name,age,email,status FROM studentSELECT id,name,age,email,status FROM student
     *
     */
    @Test
    public void testOrderBy() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.orderBy(true, true, "age");
        // SELECT id,name,age,email,status FROM student ORDER BY age ASC
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    @Test
    public void testOrderBy2() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.orderBy(true, true, "age").
                orderBy(true, false, "name");
        // SELECT id,name,age,email,status FROM student ORDER BY age ASC,name DESC
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * and or
     */
    @Test
    public void testAndOr() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.eq("name", "张三").or().eq("age", "22");
        // SELECT id,name,age,email,status FROM student WHERE (name = ? OR age = ?)
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * last
     */
    @Test
    public void testLast() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.eq("name", "张三").or().eq("age", "22").last("limit 5");
        // SELECT id,name,age,email,status FROM student WHERE (name = ? OR age = ?) limit 5
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * exists
     */
    @Test
    public void testExists() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.exists("select id from student where age > 15");
        // SELECT id,name,age,email,status FROM student WHERE (EXISTS (select id from student where age > 15))
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * notExists
     */
    @Test
    public void testNotExists() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        qw.notExists("select id from student where age > 90");
        // SELECT id,name,age,email,status FROM student WHERE (NOT EXISTS (select id from student where age > 90))
        List<Student> studentList = studentMapper.selectList(qw);
        studentList.forEach(System.out::println);

    }

    /**
     * Preparing: SELECT id,name,age,email,status FROM student LIMIT 0,3
     *
     *  Preparing: SELECT id,name,age,email,status FROM student LIMIT ?
     * ==> Parameters: 3(Long)
     */
    @Test
    public void testPage() {
        QueryWrapper<Student> qw = new QueryWrapper<Student>();
        IPage<Student> page = new Page<Student>();
        // 设置分页的数据
        page.setCurrent(1); // 第一页
        page.setSize(3); // 每页的记录数
        IPage<Student> result = studentMapper.selectPage(page, qw);
        // 获取分页的记录
        // Preparing: SELECT id,name,age,email,status FROM student LIMIT 0,3
        List<Student> studentList = result.getRecords();
        System.out.println("当前页一共有几条数据 = " + studentList.size());
        // 分页的信息
        long pages = result.getPages();
        System.out.println("页数 = " + pages);
        System.out.println("当前的页码 = " + result.getCurrent());
        System.out.println("每页的记录数 = " + result.getSize());
        // 总的记录数
        System.out.println("总的记录数 = " + result.getTotal());
        studentList.forEach(System.out::println);

    }

}
