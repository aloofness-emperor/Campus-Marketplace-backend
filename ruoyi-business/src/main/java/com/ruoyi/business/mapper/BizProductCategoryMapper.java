package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BizProductCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类Mapper接口
 *
 * @author ruoyi
 * @date 2026-11-24
 */
@Mapper
public interface BizProductCategoryMapper
{
    /**
     * 查询商品分类列表
     *
     * @param category 查询条件
     * @return 商品分类集合
     */
    public List<BizProductCategory> selectBizProductCategoryList(BizProductCategory category);
}
