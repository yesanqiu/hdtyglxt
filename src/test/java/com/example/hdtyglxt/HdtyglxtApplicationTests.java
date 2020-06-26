package com.example.hdtyglxt;

import com.example.hdtyglxt.base.service.BaseService;
import com.example.hdtyglxt.base.service.impl.BaseServiceImpl;
import com.example.hdtyglxt.entity.User;
import com.example.hdtyglxt.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HdtyglxtApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private BaseService baseService;
    @Test
    public void contextLoads() {
        try {
            User user = new User("1","1");
            baseService.reflexObject(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("222");
    }

}
