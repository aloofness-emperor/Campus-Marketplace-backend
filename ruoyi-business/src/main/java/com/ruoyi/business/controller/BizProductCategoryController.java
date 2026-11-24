package com.ruoyi.business.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.business.domain.BizProductCategory;
import com.ruoyi.business.service.IBizProductCategoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * 商品分类Controller
 *
 * @author ruoyi
 * @date 2026-11-24
 */
@RestController
@RequestMapping("/business/category")
public class BizProductCategoryController extends BaseController
{
    @Autowired
    private IBizProductCategoryService bizProductCategoryService;

    /**
     * 查询商品分类树（只返回启用的分类）
     */
    @PreAuthorize("@ss.hasPermi('business:product:list')")
    @GetMapping("/tree")
    public AjaxResult tree()
    {
        List<BizProductCategory> tree = bizProductCategoryService.selectCategoryTree();
        return success(tree);
    }
}
