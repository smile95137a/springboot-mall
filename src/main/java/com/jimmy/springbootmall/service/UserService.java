package com.jimmy.springbootmall.service;

import com.jimmy.springbootmall.dto.UserRegisterRequest;
import com.jimmy.springbootmall.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
}
