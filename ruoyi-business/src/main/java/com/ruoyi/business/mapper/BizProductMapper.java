package com.ruoyi.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.business.domain.BizProduct;

/**
 * 商品Mapper接口
 *
 * @author ruoyi
 * @date 2026-11-24
 */
@Mapper
public interface BizProductMapper
{
    /**
     * 查询商品
     *
     * @param id 商品主键
     * @return 商品
     */
    public BizProduct selectBizProductById(Long id);

    /**
     * 查询商品列表
     *
     * @param bizProduct 商品
     * @return 商品集合
     */
    public List<BizProduct> selectBizProductList(BizProduct bizProduct);

    /**
     * 新增商品
     *
     * @param bizProduct 商品
     * @return 结果
     */
    public int insertBizProduct(BizProduct bizProduct);

    /**
     * 修改商品
     *
     * @param bizProduct 商品
     * @return 结果
     */
    public int updateBizProduct(BizProduct bizProduct);

    /**
     * 软删除商品
     *
     * @param id 商品主键
     * @param updateBy 操作人
     * @return 结果
     */
    public int deleteBizProductById(@Param("id") Long id, @Param("updateBy") String updateBy);

    /**
     * 批量软删除商品
     *
     * @param ids 需要删除的商品主键集合
     * @param updateBy 操作人
     * @return 结果
     */
    public int deleteBizProductByIds(@Param("ids") Long[] ids, @Param("updateBy") String updateBy);
}
