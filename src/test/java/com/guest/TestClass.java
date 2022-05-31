package com.guest;

import com.guest.service.IBackgroundService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * @author: Chuanguo.Cao
 * @date: 2022-05-11 15:53
 **/
@SpringBootTest(classes = GuestApplication.class)
@RunWith(SpringRunner.class)
public class TestClass {

    @Autowired
    DataSource dataSource;

    @Test
    public void test1(){
        System.out.println(dataSource.toString());
    }
}
