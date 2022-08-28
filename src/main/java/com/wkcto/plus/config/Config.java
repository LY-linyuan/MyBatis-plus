package com.wkcto.plus.config;

/**
 * @Author 临渊
 * @Date 2022-08-28 9:03
 */

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration标注的类就相当于xml配置文件
 */

@Configuration
public class Config {

    /**
     *  定义方法, 返回值是java对象, 这个对象是放入到spring容器中
     *  使用Bean修饰方法
     */
    /*@Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }*/

    @Bean
    public MybatisPlusInterceptor paginationInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();

        // 分页拦截器
        PaginationInnerInterceptor paginationInnerInterceptor=new PaginationInnerInterceptor();
        paginationInnerInterceptor.setOverflow(true); // 到最后一页时，自动跳转到第一页  到第一页时,不会再向前
        paginationInnerInterceptor.setMaxLimit(5L); // 每页最多为5条
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);

        return mybatisPlusInterceptor;
    }
}
