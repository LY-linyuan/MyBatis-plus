package com.wkcto.plus;

import com.wkcto.plus.eneity.Address;
import com.wkcto.plus.mapper.AddressMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author 临渊
 * @Date 2022-08-27 15:09
 */

@SpringBootTest
@SuppressWarnings("all")
public class AddressTest {

    // 定义Mapper对象
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void testInsert() {
        Address address = new Address();
        address.setCity("先");
        address.setStreet("x街道");
        address.setZipcode("010");
        // INSERT INTO user_address ( city, street, zipcode ) VALUES ( ?, ?, ? )
        int rows = addressMapper.insert(address);
        System.out.println(rows);
    }

}
