package com.jimmy.springbootmall.dao;

import com.jimmy.springbootmall.dto.UserRegisterRequest;
import com.jimmy.springbootmall.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserByEmail(String email);
    User getUserById(Integer userId);
}
