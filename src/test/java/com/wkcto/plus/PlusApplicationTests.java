package com.wkcto.plus;

import com.wkcto.plus.eneity.User;
import com.wkcto.plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SuppressWarnings("all")
@SpringBootTest
class PlusApplicationTests {

    // 使用自动注入 注入Mapper对象
    @Autowired
    private UserMapper userMapper;

    // 定义测试方法
    // 测试添加操作 insert
    @Test
    public void testUserInsert() {
        // 创建User对象
        User user = new User();
        user.setName("T");
        user.setAge(17);
        user.setEmail("t@like.com");
        // 调用UserMapper的方法, 也就是父借口BaseMapper中提供的方法
        int rows = userMapper.insert(user);
        System.out.println("insert 的结果 = " + rows);
    }

}
