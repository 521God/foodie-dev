package com.imooc.controller;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.service.ItemService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "商品详情接口", tags = {"商品详情"})
@RequestMapping("/items")
public class ItemsController extends BaseController{
    @Autowired
    private ItemService itemService;

    @GetMapping("/info/{itemId}")
    @ApiOperation(value = "获取商品详情", notes = "获取商品详情", httpMethod = "GET")
    public IMOOCJSONResult info(
            @ApiParam(name = "itemId", value = "商品Id", required = true)
            @PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            IMOOCJSONResult.errorMsg("");
        }
        Items item = itemService.queryItemById(itemId);
        List<ItemsImg> itemImgList = itemService.queryItemsImgList(itemId);
        List<ItemsSpec> itemSpecList = itemService.queryItemsSpecList(itemId);
        ItemsParam itemParams = itemService.queryItemsParam(itemId);
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(item);
        itemInfoVO.setItemImgList(itemImgList);
        itemInfoVO.setItemParams(itemParams);
        itemInfoVO.setItemSpecList(itemSpecList);
        System.out.println(itemInfoVO);
        return IMOOCJSONResult.ok(itemInfoVO);
    }
    @GetMapping("/commentLevel")
    @ApiOperation(value = "获取好评中评差评 总的评论数量", notes = "获取好评中评差评 总的评论数量", httpMethod = "GET")
    public IMOOCJSONResult comments(
            @ApiParam(name = "itemId", value = "商品Id", required = true)
            @RequestParam String itemId){
        //由于是请求路径的参数所以用的是RequestParam
        if (StringUtils.isBlank(itemId)) {
            IMOOCJSONResult.errorMsg("");
        }
        CommentLevelCountsVO commentLevelCounts = itemService.queryCommentCounts(itemId);

        return IMOOCJSONResult.ok(commentLevelCounts);
    }
    @GetMapping("/comments")
    @ApiOperation(value = "获取关于评论的参数比如用户头像、商品规格、评论内容等", notes = "获取关于评论的参数比如用户头像、商品规格、评论内容等", httpMethod = "GET")
    public IMOOCJSONResult comments(
            @ApiParam(name = "itemId", value = "商品Id", required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level", value = "商品评论等级", required = true)
            @RequestParam Integer level,
            @ApiParam(name = "page", value = "页数", required = true)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "每一页中的数量", required = true)
            @RequestParam Integer pageSize){
        //由于是请求路径的参数所以用的是RequestParam
        if (StringUtils.isBlank(itemId)) {
            IMOOCJSONResult.errorMsg("");
        }
        if (page == 0){
            page = 1;
        }
        if (pageSize ==0){
            pageSize= COMMENT_PAGE_SIZE;
        }
        PagedGridResult grid = itemService.queryCommetnPage(itemId,level,page,pageSize);

        return IMOOCJSONResult.ok(grid);
    }
}
