package com.wkcto.plus;

import com.wkcto.plus.eneity.Customer;
import com.wkcto.plus.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author 临渊
 * @Date 2022-08-27 15:09
 */

@SpringBootTest
@SuppressWarnings("all")
public class CustomerTest {

    // 定义Mapper对象
    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void testInsert() {
        Customer customer = new Customer();
        customer.setCustName("T");
        customer.setCustAge(17);
        customer.setCustEmail("like.com");
        // INSERT INTO customer ( cust_name, cust_age, cust_email ) VALUES ( ?, ?, ? )
        int rows = customerMapper.insert(customer);
        System.out.println(rows);
    }

}
