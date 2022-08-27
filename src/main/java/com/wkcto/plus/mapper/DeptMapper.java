package com.wkcto.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wkcto.plus.eneity.Dept;

/**
 * @Author 临渊
 * @Date 2022-08-27 8:44
 */

/**
 *  DeptMapper是不需要使用的, MP需要使用DeptMapper获取数据库的表的信息
 *  如果不定义DeptMapper, MP会报错  找不到表的定义信息
 */
public interface DeptMapper extends BaseMapper<Dept> {
}
