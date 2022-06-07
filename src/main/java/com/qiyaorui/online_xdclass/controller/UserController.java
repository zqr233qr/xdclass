package com.qiyaorui.online_xdclass.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qiyaorui.online_xdclass.model.entity.User;
import com.qiyaorui.online_xdclass.model.request.LoginRequest;
import com.qiyaorui.online_xdclass.service.UserService;
import com.qiyaorui.online_xdclass.utils.JsonData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册接口
     * @RequestBody 前端传来json数据要使用
     * @param userInfo
     * @return
     */
    @PostMapping("register")
    public JsonData register(@RequestBody Map<String,String> userInfo){
        int save = userService.save(userInfo);
        return save==0?JsonData.buildSuccess():JsonData.buildError("注册失败，请重试");
    }

    /**
     * 登录接口
     * @param loginRequest
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest){
        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(),loginRequest.getPwd());
        return token == null ? JsonData.buildError("账号密码错误"):JsonData.buildSuccess(token);
    }


    /**
     * 根据token查询用户信息
     * @param request
     * @return
     */
    @GetMapping("find_by_token")
    public JsonData findByToken(HttpServletRequest request){
        Integer id = (Integer) request.getAttribute("user_id");
        if (id == null){
            return JsonData.buildError("查询失败");
        }
        User user = userService.findById(id);
        return user == null ? JsonData.buildError("查询失败"):JsonData.buildSuccess(user);
    }
}
