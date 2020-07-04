package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "实例对象",description = "从前端传过来的数据在这里进行封装成Userbo")
public class UserBo {
    @ApiModelProperty(value = "用户名称",name = "username",notes = "用户名",example = "imooc",required = true)
    private String username;
    @ApiModelProperty(value = "用户密码",name = "password",notes = "用户密码",example = "123456",required = true)
    private String password;
    @ApiModelProperty(value = "用户确认密码",name = "confirmPassword",notes = "用户确认密码",example = "123456",required = false)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
