package com.imooc.service;

import com.imooc.pojo.Stu;

public interface StuService {
    public Stu getInfo(int id);

    public void saveStu();

    public void updateStu(int id);

    public void deleteStu(int id);
}
