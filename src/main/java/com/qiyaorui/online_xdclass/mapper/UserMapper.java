package com.qiyaorui.online_xdclass.mapper;

import com.qiyaorui.online_xdclass.model.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 注册用户
     * @return
     */
    int save(User user);

    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);

    User findById(@Param("id") Integer id);
}
