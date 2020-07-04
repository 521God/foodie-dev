package com.imooc.service.impl;

import com.imooc.mapper.CarouselMapper;
import com.imooc.pojo.Carousel;
import com.imooc.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        //创建一个条件对象
        Example example = new Example(Carousel.class);
        example.orderBy("sort").desc();
        //初始化查询字段
        Example.Criteria criteria = example.createCriteria();
        //根据条件进行拼接SQL语句
        criteria.andEqualTo("isShow",isShow);
        //根据设置的条件进行条件查询,结果进行返回
        List<Carousel> result = carouselMapper.selectByExample(example);
        return result;
    }
}


