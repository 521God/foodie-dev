package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有一级分类
     * @return
     */
    public List<Category> queryAllRootLevelCat();

    /**
     * 查询所有一级分类下的子分类
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询六个root下的商品ID
     * @param rootCatId
     * @return
     */
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);

}
