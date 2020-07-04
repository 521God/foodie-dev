package com.imooc.service.impl;
import com.imooc.enums.Sex;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBo;
import com.imooc.service.UserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private Sid sid;
    private static final String USER_FACE="file:///C:/Users/Administrator/Desktop/下载.jpg";
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria= userExample.createCriteria();
        userCriteria.andEqualTo("username",username);
        Users result = usersMapper.selectOneByExample(userExample);
        return result == null? false : true;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBo userBo) {
        String userId =sid.nextShort();
        Users user = new Users();
        user.setId(userId);
        user.setUsername(userBo.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setFace(USER_FACE);
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        user.setNickname(userBo.getUsername());
        user.setSex(Sex.serect.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        usersMapper.insert(user);
        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria= userExample.createCriteria();
        userCriteria.andEqualTo("username",username);
        userCriteria.andEqualTo("password",password);
        Users result = usersMapper.selectOneByExample(userExample);
        return result;
    }
}
