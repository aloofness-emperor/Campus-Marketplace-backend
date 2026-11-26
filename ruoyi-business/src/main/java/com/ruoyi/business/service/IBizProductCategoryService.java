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

    /**
     * 查询分类管理
     *
     * @param id 分类管理主键
     * @return 分类管理
     */
    public BizProductCategory selectBizProductCategoryById(Long id);

    /**
     * 查询分类管理列表
     *
     * @param bizProductCategory 分类管理
     * @return 分类管理集合
     */
    public List<BizProductCategory> selectBizProductCategoryList(BizProductCategory bizProductCategory);

    /**
     * 新增分类管理
     *
     * @param bizProductCategory 分类管理
     * @return 结果
     */
    public int insertBizProductCategory(BizProductCategory bizProductCategory);

    /**
     * 修改分类管理
     *
     * @param bizProductCategory 分类管理
     * @return 结果
     */
    public int updateBizProductCategory(BizProductCategory bizProductCategory);

    /**
     * 批量删除分类管理
     *
     * @param ids 需要删除的分类管理主键集合
     * @return 结果
     */
    public int deleteBizProductCategoryByIds(Long[] ids);

    /**
     * 删除分类管理信息
     *
     * @param id 分类管理主键
     * @return 结果
     */
    public int deleteBizProductCategoryById(Long id);
}
