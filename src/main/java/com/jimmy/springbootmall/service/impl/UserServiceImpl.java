package com.jimmy.springbootmall.service.impl;

import com.jimmy.springbootmall.dao.UserDao;
import com.jimmy.springbootmall.dto.UserLogin;
import com.jimmy.springbootmall.dto.UserRegisterRequest;
import com.jimmy.springbootmall.model.User;
import com.jimmy.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    //通用LOG寫法 要在其他地方紀錄 改參數就可以
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        //檢查註冊的EMAIL
        if (user != null) {
            log.warn("該email {} 已被註冊", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return userDao.createUser(userRegisterRequest);
    }

    //創建帳號
    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User login(UserLogin userLogin) {
        User user = userDao.getUserByEmail(userLogin.getEmail());

        if (user == null) {
            log.warn("該email {} 尚未註冊", userLogin.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (user.getPassword().equals(userLogin.getPassword())) {
            return user;
        } else {
            log.warn("email {} 的密碼不正確", userLogin.getPassword());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
