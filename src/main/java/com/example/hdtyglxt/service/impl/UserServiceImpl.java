package com.example.hdtyglxt.service.impl;

import com.example.hdtyglxt.base.service.impl.BaseServiceImpl;
import com.example.hdtyglxt.entity.User;
import com.example.hdtyglxt.mapper.UserMapper;
import com.example.hdtyglxt.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
}
