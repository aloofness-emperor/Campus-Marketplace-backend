package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizProductCategory;

/**
 * 商品分类Service接口
 *
 * @author ruoyi
 * @date 2026-11-24
 */
public interface IBizProductCategoryService
{
    /**
     * 查询商品分类树
     *
     * @return 分类树
     */
    public List<BizProductCategory> selectCategoryTree();
}
