package com.wkcto.plus;

import com.wkcto.plus.eneity.User;
import com.wkcto.plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    // 添加数据后获取主键值
    @Test
    public void testInsertGetId() {
        // 创建User对象
        User user = new User();
        user.setName("C");
        user.setAge(19);
        user.setEmail("t@like.com");
        // 调用UserMapper的方法, 也就是父借口BaseMapper中提供的方法
        int rows = userMapper.insert(user);
        System.out.println("insert 的结果 = " + rows);

        // 获取主键id, 添加到数据空中的id
        Integer id = user.getId();
        System.out.println(id);
    }

    /**
     * 更新操作 update
     */
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setName("C");
        user.setAge(19);
        user.setEmail("t@likelike.com");
        user.setId(4);
        // 执行更新  根据主键值进行更新
        /**
         * UPDATE user SET name=?, email=?, age=? WHERE id=?
         * 更新了所有非空属性值
         */
        int rows = userMapper.updateById(user);
        System.out.println("跟心的记录条数 = " + rows);
    }

    /**
     * 控制更新的字段
     */
    @Test
    public void testUpdateUser2() {
        User user = new User();
        user.setEmail("t@likelike.com");
        user.setId(2);
        // 执行更新  根据主键值进行更新
        /**
         * UPDATE user SET email=? WHERE id=?
         * 更新了所有非空属性值
         */
        int rows = userMapper.updateById(user);
        System.out.println("跟心的记录条数 = " + rows);
    }

    /**
     * 实体类的属性是基本类型  int age
     * UPDATE user SET email=?, age=? WHERE id=?
     */
    @Test
    public void testUpdateUser3() {
        User user = new User();
        user.setEmail("t@likelikelike.com");
        user.setId(2);
        // 执行更新  根据主键值进行更新
        int rows = userMapper.updateById(user);
        System.out.println("跟心的记录条数 = " + rows);
    }

    /**
     * 按主键删除一条数据
     *  方法是deleteById()
     *  参数: 主键值
     *  返回值: 删除的成功记录数
     */
    @Test
    public void testDeleteById() {
        // DELETE FROM user WHERE id=?
        int rows = userMapper.deleteById(7);
        System.out.println("删除的记录条数 = " + rows);
    }

    /**
     * 按条件删除数据 条件封装到Map中
     *  方法是deleteByMap()
     *  参数: deleteByMap(Map对象)
     *  返回值: 删除的成功记录数
     */
    @Test
    public void testDeleteByMap() {
        // 创建map对象保存条件值
        Map<String, Object> map = new HashMap<String, Object>();
        // put("标的字段名", 条件值), 可以封装多个条件
        map.put("name", "张三");
        map.put("age", 20);
        // DELETE FROM user WHERE name = ? AND age = ?
        int rows = userMapper.deleteByMap(map);
        System.out.println("删除的记录条数 = " + rows);
    }

    /**
     * 批处理方式, 使用多个主键值, 删除数据
     * 参数: Colllection<?, extends Serializable> var 1
     * 返回值: 删除的记录数
     */
    @Test
    public void testDeleteBatchId() {
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(8);
        ids.add(9);
        // 删除操作
        // DELETE FROM user WHERE id IN ( ? , ? )
        int rows = userMapper.deleteBatchIds(ids);
        System.out.println("删除的记录条数 = " + rows);
    }

    /**
     *  使用lambda创建list集合
     */
    @Test
    public void testDeleteBatchId2() {
        List<Integer> ids = Stream.of(7, 8, 9, 10, 11, 12, 13).collect(Collectors.toList());
        // DELETE FROM user WHERE id IN ( ? , ? , ? , ? , ? , ? , ? )
        int rows = userMapper.deleteBatchIds(ids);
        System.out.println("删除的记录条数 = " + rows);
    }

    /**
     * 实现查询 selectById 根据主键值查询
     * 参数 主键值
     * 返回值 实体对象 (唯一的一个对象)
     */
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(3);
        /**
         * SELECT id,name,email,age FROM user WHERE id=?
         * 如果根据句主键没有查到数据, 得到的返回值是null
         */
        System.out.println("selectById得到的user = " + user);
        // 在使用对象之前需要去判读先对象是否为空
        /*if (user != null) {
            for (int i = 0 ; i < 10; i++) {
                // 创建User对象
                User user3 = new User();
                user3.setName("T");
                user3.setAge(17);
                user3.setEmail("c@like.com" + i);
                // 调用UserMapper的方法, 也就是父借口BaseMapper中提供的方法
                int rows = userMapper.insert(user3);
                System.out.println("insert 的结果 = " + rows);

                // 创建User对象
                User user4 = new User();
                user4.setName("C");
                user4.setAge(19);
                user4.setEmail("t@like.com" + i);
                // 调用UserMapper的方法, 也就是父借口BaseMapper中提供的方法
                rows = userMapper.insert(user4);
                System.out.println("insert 的结果 = " + rows);
            }
        }*/
    }

    /**
     * 实现批处理查询 : 根据多个主键值查询 获取到list
     * 方法 selectBatchIds
     * 参数 : id的集合
     * 返回值 list <T></T>
     */
    @Test
    public void testSelectBatchId() {
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(3);
        ids.add(4);
        // 查询数据
        // SELECT id,name,email,age FROM user WHERE id IN ( ? , ? , ? )
        List<User> userList = userMapper.selectBatchIds(ids);
        System.out.println("size: " + userList.size());
        for (User user: userList) {
            System.out.println(user);
        }

    }

    /**
     * 使用lambda创建list集合
     */
    @Test
    public void testSelectBatchId2() {
        List<Integer> ids = Stream.of(1, 3, 4, 1, 7).collect(Collectors.toList());
        // 查询数据
        // SELECT id,name,email,age FROM user WHERE id IN ( ? , ? , ? , ? , ? )
        List<User> userList = userMapper.selectBatchIds(ids);
        System.out.println("size: " + userList.size());
        userList.forEach(user -> {
            System.out.println(user);
        });
        userList.forEach(System.out::println);
    }

    /**
     * 按条件查询数据 条件封装到Map中
     *  方法是selectByMap()
     *  参数: selectByMap(Map对象)
     *  返回值: List<T>
     */
    @Test
    public void testSelectMap() {
        // 创建map对象保存条件值
        Map<String, Object> map = new HashMap<String, Object>();
        // put("标的字段名", 条件值), 可以封装多个条件
        map.put("name", "T");
        map.put("age", 17);
        // SELECT id,name,email,age FROM user WHERE name = ? AND age = ?
        List<User> userList = userMapper.selectByMap(map);
        System.out.println("size: " + userList.size());
        userList.forEach(user -> {
            System.out.println(user);
        });
        userList.forEach(System.out::println);
    }
}
