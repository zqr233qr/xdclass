package com.qiyaorui.online_xdclass.service;

import com.qiyaorui.online_xdclass.model.entity.User;

import java.util.Map;

public interface UserService {

    int save(Map<String,String> map);

    String findByPhoneAndPwd(String phone, String pwd);

    User findById(Integer id);
}
