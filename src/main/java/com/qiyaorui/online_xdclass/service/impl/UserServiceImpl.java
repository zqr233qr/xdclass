package com.qiyaorui.online_xdclass.service.impl;

import com.qiyaorui.online_xdclass.model.entity.User;
import com.qiyaorui.online_xdclass.mapper.UserMapper;
import com.qiyaorui.online_xdclass.service.UserService;
import com.qiyaorui.online_xdclass.utils.CommonUtils;
import com.qiyaorui.online_xdclass.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册用户
     * @param map
     * @return
     */
    @Override
    public int save(Map<String, String> map) {
        User user = mapParse2User(map);
        if (user!=null){
            userMapper.save(user);
            return 0;
        }else {
            return -1;
        }
    }

    /**
     * 用户登录
     * @param phone
     * @param pwd
     * @return
     */
    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {
        User user = userMapper.findByPhoneAndPwd(phone, CommonUtils.MD5(pwd));
        if (user == null){
            return null;
        }
        String token = JWTUtils.geneJsonWebToken(user);
        return token;
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    /**
     * 解析 user 对象
     * @param map
     * @return
     */
    private User mapParse2User(Map<String, String> map) {
        if (map.containsKey("name") && map.containsKey("phone") && map.containsKey("pwd")){
            User user = new User();
            user.setName(map.get("name"));
            user.setPhone(map.get("phone"));
            user.setHeadImg(getRandomHeadImg());
            String pwd = map.get("pwd");
            //MD5加密
            user.setPwd(CommonUtils.MD5(pwd));
            user.setCreateTime(new Date());
            return user;
        }else {
            return null;
        }
    }

    /**
     * 随机头像库
     */
    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    /**
     * 获取随机头像
     * @return
     */
    private String getRandomHeadImg(){
        Random random = new Random();
        int i = random.nextInt(headImg.length);
        return headImg[i];
    }
}
