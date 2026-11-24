package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizProduct;

/**
 * 商品管理Service接口
 * 
 * @author ruoyi
 * @date 2026-11-24
 */
public interface IBizProductService 
{
    /**
     * 查询商品管理
     * 
     * @param id 商品管理主键
     * @return 商品管理
     */
    public BizProduct selectBizProductById(Long id);

    /**
     * 查询商品管理列表
     * 
     * @param bizProduct 商品管理
     * @return 商品管理集合
     */
    public List<BizProduct> selectBizProductList(BizProduct bizProduct);

    /**
     * 新增商品管理
     * 
     * @param bizProduct 商品管理
     * @return 结果
     */
    public int insertBizProduct(BizProduct bizProduct);

    /**
     * 修改商品管理
     * 
     * @param bizProduct 商品管理
     * @return 结果
     */
    public int updateBizProduct(BizProduct bizProduct);

    /**
     * 批量删除商品管理
     * 
     * @param ids 需要删除的商品管理主键集合
     * @return 结果
     */
    public int deleteBizProductByIds(Long[] ids);

    /**
     * 删除商品管理信息
     * 
     * @param id 商品管理主键
     * @return 结果
     */
    public int deleteBizProductById(Long id);
}
