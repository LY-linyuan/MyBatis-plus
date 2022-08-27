package com.wkcto.plus;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.wkcto.plus.eneity.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author 临渊
 * @Date 2022-08-27 15:09
 */

@SpringBootTest
public class DeptARTest {

    @Test
    public void testARInsert() {
        // 定义部门的实体对象
        Dept dept = new Dept();
        dept.setName("like");
        dept.setMobile("010-12345678");
        dept.setManager(1);
        // 调用实体对象自己的方法, 完成自身到数据库的添加操作
        // INSERT INTO dept ( name, mobile, manager ) VALUES ( ?, ?, ? )
        boolean flag = dept.insert();
        System.out.println("AR的 insert的输出结果 = " + flag);
    }

    @Test
    public void testARUpdate() {
        // 定义部门的实体对象
        Dept dept = new Dept();
        dept.setName("like");
        dept.setMobile("010-516526123456");
        dept.setManager(1);
        dept.setId(1);
        // 使用dept实体主类的id作为where的条件
        // UPDATE dept SET name=?, mobile=?, manager=? WHERE id=?
        boolean flag = dept.updateById();
        System.out.println("AR的 updateById的输出结果 = " + flag);
    }

    @Test
    public void testARUpdate2() {
        // 定义部门的实体对象
        Dept dept = new Dept();
        dept.setMobile("010-516526123456");
        dept.setManager(1);
        dept.setId(1);
        // 使用dept实体主类的id作为where的条件
        // 只更新非空属性字段  属性为null时不处理
        // UPDATE dept SET mobile=?, manager=? WHERE id=?
        boolean flag = dept.updateById();
        System.out.println("AR的 updateById的输出结果 = " + flag);
    }

    /**
     *  AR deleteById
     */
    @Test
    public void testARDeleteById() {
        Dept dept = new Dept();
        dept.setId(1);
        // 使用dept实体主类的id作为where的条件
        // DELETE FROM dept WHERE id=?
        boolean flag = dept.deleteById();
        System.out.println("AR的 deleteById的输出结果 = " + flag);
    }

    @Test
    public void testARDeleteById2() {
        Dept dept = new Dept();
        // 使用dept实体主类的id作为where的条件
        // DELETE FROM dept WHERE id=?
        boolean flag = dept.deleteById(1);
        System.out.println("AR的 deleteById的输出结果 = " + flag);
    }

    /**
     * 按主键进行查询
     */
    @Test
    public void testARSelectById() {
        Dept dept = new Dept();
        // 使用dept实体主类的id作为where的条件
        dept.setId(2);
        // SELECT id,name,mobile,manager FROM dept WHERE id=?
        Dept dept1 = dept.selectById();
        System.out.println("AR的 deleteById的输出结果 = " + dept1);
    }

    @Test
    public void testARSelectById2() {
        Dept dept = new Dept();
        // 使用dept实体主类的id作为where的条件
        // SELECT id,name,mobile,manager FROM dept WHERE id=?
        Dept dept2 = dept.selectById(2);
        System.out.println("AR的 deleteById的输出结果 = " + dept2);
    }

    @Test
    public void testARSelectById3() {
        Dept dept = new Dept();
        // SELECT id,name,mobile,manager FROM dept
        List<Dept> deptList = dept.selectAll();
        System.out.println("AR的 selectAll 的个数 = " + deptList.size());
        deptList.forEach(System.out::println);
    }


}
