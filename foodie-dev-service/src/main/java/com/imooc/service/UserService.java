package com.imooc.service;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBo;


public interface UserService {
    /**
     * 判断用户名称是否存在
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userBo
     * @return
     */
    public Users createUser(UserBo userBo);

    /**
     * 用户登录
     * @param username password
     * @return
     */
    public Users queryUserForLogin(String username,String password);

}
