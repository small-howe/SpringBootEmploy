package com.tangwh.springbootemploy;

import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootemployApplicationTests {

    @Autowired
    ApplicationContext ioc;
    @Test
    public void contextLoads() {
        boolean student = ioc.containsBean("student");
        System.out.println(student);
    }


}
