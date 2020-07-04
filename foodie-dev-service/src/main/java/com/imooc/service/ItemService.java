package com.imooc.service;

import com.imooc.pojo.*;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.utils.PagedGridResult;

import java.util.List;

public interface ItemService {
    /**
     * 根据商品ID查询商品
     * @param itemId
     * @return
     */
    public Items queryItemById(String itemId);
    /**
     * 根据商品ID查询商品图片
     */
    public List<ItemsImg> queryItemsImgList(String itemId);

    /**
     * 根据商品ID查询商品的参数
     * @param itemId
     * @return
     */
    public ItemsParam queryItemsParam(String itemId);

    /**
     * 查询商品的规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemsSpecList(String itemId);

    /**
     * 查询商品各个级别的评价数量
     * @param itemId
     * @return
     */
    public CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 查询各个评价的等级的相关参数
     * @param itemId
     * @param level
     * @return
     */
    public PagedGridResult queryCommetnPage(String itemId, Integer level,Integer page,Integer pageSize);

}
