package com.ruoyi.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.business.domain.BizProductCategory;

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

    /**
     * 查询分类管理
     *
     * @param id 分类管理主键
     * @return 分类管理
     */
    public BizProductCategory selectBizProductCategoryById(Long id);

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
     * 软删除分类管理
     *
     * @param id 分类管理主键
     * @param updateBy 操作人
     * @return 结果
     */
    public int deleteBizProductCategoryById(@Param("id") Long id, @Param("updateBy") String updateBy);

    /**
     * 批量软删除分类管理
     *
     * @param ids 需要删除的数据主键集合
     * @param updateBy 操作人
     * @return 结果
     */
    public int deleteBizProductCategoryByIds(@Param("ids") Long[] ids, @Param("updateBy") String updateBy);

    /**
     * 统计子分类数量
     *
     * @param parentId 父分类ID
     * @return 数量
     */
    public int countChildrenByParentId(@Param("parentId") Long parentId);

    /**
     * 统计分类下有效商品数量
     *
     * @param categoryId 分类ID
     * @return 数量
     */
    public int countProductByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * 统计同级下同名分类数量
     *
     * @param parentId 父分类ID
     * @param name 分类名称
     * @param excludeId 排除的分类ID（修改时传当前ID）
     * @return 数量
     */
    public int countByName(@Param("parentId") Long parentId, @Param("name") String name, @Param("excludeId") Long excludeId);
}
