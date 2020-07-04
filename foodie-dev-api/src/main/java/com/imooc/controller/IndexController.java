
package com.imooc.controller;

        import com.imooc.enums.YesOrNo;
        import com.imooc.pojo.Carousel;
        import com.imooc.pojo.Category;
        import com.imooc.pojo.vo.CategoryVO;
        import com.imooc.pojo.vo.NewItemsVO;
        import com.imooc.service.CarouselService;
        import com.imooc.service.CategoryService;
        import com.imooc.utils.IMOOCJSONResult;
        import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import io.swagger.annotations.ApiParam;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.List;

@RestController
@Api(value = "首页的展示", tags = {"首页的图片的展示"})
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/carousel")
    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页的轮播图列表",httpMethod = "GET")
    public IMOOCJSONResult queryAllCarousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
        return IMOOCJSONResult.ok(list);
    }

    @GetMapping("/subCat/{rootCatId}")
    @ApiOperation(value = "获取首页一级分类下的所有子类", notes = "获取首页的一级分类下的所有子类",httpMethod = "GET")
    public IMOOCJSONResult subCat(
            @ApiParam(name = "rootCatId",value = "一级分类的ID",required = true)
            @PathVariable Integer rootCatId) {
        if (rootCatId==null){
            return IMOOCJSONResult.errorMsg("");
        }
        List<CategoryVO> list = categoryService.getSubCatList(rootCatId);
        return IMOOCJSONResult.ok(list);
    }
    @GetMapping("/sixNewItems/{rootCatId}")
    @ApiOperation(value = "获取首页一级分类下六条最新商品信息", notes = "获取首页一级分类下六条最新商品信息",httpMethod = "GET")
    public IMOOCJSONResult sixNewItems(
            @ApiParam(name = "rootCatId",value = "一级分类的ID",required = true)
            @PathVariable Integer rootCatId) {
        if (rootCatId==null){
            return IMOOCJSONResult.errorMsg("");
        }
        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return IMOOCJSONResult.ok(list);
    }
    @GetMapping("/cats")
    @ApiOperation(value = "获取首页一级分类", notes = "获取首页的一级分类",httpMethod = "GET")
    public IMOOCJSONResult cats() {
        List<Category> list = categoryService.queryAllRootLevelCat();
        return IMOOCJSONResult.ok(list);
    }
}
