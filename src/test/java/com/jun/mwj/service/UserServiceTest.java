package com.jun.mwj.service;

import com.jun.mwj.dao.UserDao;
import com.jun.mwj.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserDao userDao;

    @Test
    void get() {
        List<User> user = userDao.findAll();
        user.forEach(System.out::println);
    }
}