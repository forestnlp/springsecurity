package com.test;

import com.demo.SpringSecurity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = SpringSecurity.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Test01 {

    @Test
    public void test01(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("321321");
        System.out.println(result);

        boolean match  = encoder.matches("123123",result);
        System.out.println(match);
    }
}
