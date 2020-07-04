package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBo;
import com.imooc.service.StuService;
import com.imooc.service.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("passport")
@Api(value="用于注册的接口",tags={"用户注册接口"})
public class PassportController {
    @Autowired
    private UserService userService;
    @ApiOperation(value="用户名是否为空",notes = "用户名是否为空",httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username){
        if(StringUtils.isBlank(username)){
            return IMOOCJSONResult.errorMsg("用户名不能为空！");
        }
        boolean isExist = userService.queryUsernameIsExist(username);
        if(isExist){
            return IMOOCJSONResult.errorMsg("用户名已经存在！");
        }
        System.out.println("验证用户名是否存在！");
        return IMOOCJSONResult.ok();
    }
    @PostMapping("/regist")
    @ApiOperation(value="用户注册",notes = "用户注册",httpMethod = "POST")
    public IMOOCJSONResult regist(@RequestBody UserBo userBo,
                                  HttpServletRequest request,
                                  HttpServletResponse response){
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPwd = userBo.getConfirmPassword();
        System.out.println(username);
        System.out.println(password);
        System.out.println(confirmPwd);
        //1.判断用户名称和密码一定不能为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }

        //2.判断用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if(isExist){
            return IMOOCJSONResult.errorMsg("用户名已经存在！");
        }
        //3.判断密码是否大于6位
        if(password.length()<6){
            return IMOOCJSONResult.errorMsg("密码不能小于6位！");
        }
        //4.判断两次输入的密码是否一致
        if(!password.equals(confirmPwd)){
            return IMOOCJSONResult.errorMsg("两次输入的密码不一致，请重新输入！");
        }
        //5.插入数据库中创建账户
        Users resultUser = userService.createUser(userBo);
        resultUser = setPropertyNull(resultUser);
        CookieUtils.setCookie(request,response,
                "user",
                JsonUtils.objectToJson(resultUser),
                true);
        return IMOOCJSONResult.ok();



    }

    @PostMapping("/login")
    @ApiOperation(value="用户登录",notes = "用户登录",httpMethod = "POST")
    public IMOOCJSONResult login(@RequestBody UserBo userBo,
                                 HttpServletRequest request,
                                 HttpServletResponse response)throws Exception{
        String username = userBo.getUsername();
        String password = userBo.getPassword();

        System.out.println(username);
        System.out.println(password);

        //1.判断用户名称和密码一定不能为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)){
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }

        //2.实现登录
        Users resultUser = userService.queryUserForLogin(username,
                MD5Utils.getMD5Str(password) );
        if (resultUser==null){
            return IMOOCJSONResult.errorMsg("用户名或密码不正确！");
        }
        resultUser = setPropertyNull(resultUser);
        CookieUtils.setCookie(request,response,
                "user",
                JsonUtils.objectToJson(resultUser),
                true);
        return IMOOCJSONResult.ok(resultUser);

    }
    @PostMapping("/logout")
    @ApiOperation(value="用户退出",notes = "用户退出",httpMethod = "POST")
    public IMOOCJSONResult logout(@RequestParam String userId,HttpServletRequest request,
                                  HttpServletResponse response){
        CookieUtils.deleteCookie(request,response,"user");
        return IMOOCJSONResult.ok();

    }
    private Users setPropertyNull(Users resultUser){
        resultUser.setUpdatedTime(null);
        resultUser.setPassword(null);
        resultUser.setBirthday(null);
        resultUser.setEmail(null);
        resultUser.setMobile(null);
        resultUser.setCreatedTime(null);
        return resultUser;
    }
}
