package com.wkcto.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wkcto.plus.eneity.User;

/**
 * @Author 临渊
 * @Date 2022-08-27 8:44
 */

/**
 * 自定义Mapper 就是Dao接口
 *  1. 要是子安BaseMapper
 *  2. 指定实体类
 *
 * BaseMapper 是MP框架中的对选哪个, 是定义了17个操作方法(CRUD)
 */
public interface UserMapper extends BaseMapper<User> {
}
