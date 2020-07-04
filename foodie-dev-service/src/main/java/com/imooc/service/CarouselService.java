package com.imooc.service;

import com.imooc.pojo.Carousel;
import com.imooc.pojo.Stu;

import java.util.List;

public interface CarouselService {
    /**
     * 查询所有轮播图
     * @param isShow
     * @return
     */
    public List<Carousel> queryAll(Integer isShow);
}
