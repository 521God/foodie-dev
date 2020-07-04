package com.imooc.service.impl;

import com.imooc.enums.Grade;
import com.imooc.mapper.CategoryMapper;
import com.imooc.mapper.CategoryMapperCustom;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;
import com.imooc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
    CategoryMapper categoryMapper;
@Autowired
    CategoryMapperCustom categoryMapperCustom;

    /**
     * 查询所有一级分类下的子类
     * @param rootCatId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }


    /**
     * 查询所有的一级分类
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", Grade.FIRST.type);
        List<Category> result = categoryMapper.selectByExample(example);
        return result;
    }

    /**
     * 查询root下的六个商品
     * @param rootCatId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) {
        Map<String,Object> map = new HashMap<>();
        map.put("rootCatId",rootCatId);
        return categoryMapperCustom.getSixNewItemsLazy(map);
    }
}


