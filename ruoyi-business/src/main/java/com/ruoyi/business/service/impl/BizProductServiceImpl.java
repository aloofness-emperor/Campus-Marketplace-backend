package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.business.domain.BizProduct;
import com.ruoyi.business.mapper.BizProductMapper;
import com.ruoyi.business.service.IBizProductService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;

/**
 * 商品Service业务层处理
 *
 * @author ruoyi
 * @date 2026-11-24
 */
@Service
public class BizProductServiceImpl implements IBizProductService
{
    @Autowired
    private BizProductMapper bizProductMapper;

    @Override
    public BizProduct selectBizProductById(Long id)
    {
        return bizProductMapper.selectBizProductById(id);
    }

    @Override
    public List<BizProduct> selectBizProductList(BizProduct bizProduct)
    {
        return bizProductMapper.selectBizProductList(bizProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertBizProduct(BizProduct bizProduct)
    {
        // 服务端兜底，避免客户端伪造主键、审计字段、删除标志等
        bizProduct.setId(null);
        bizProduct.setViewCount(0);
        bizProduct.setDelFlag(0);
        bizProduct.setCreateBy(SecurityUtils.getUsername());
        bizProduct.setCreateTime(DateUtils.getNowDate());
        bizProduct.setUpdateBy(null);
        bizProduct.setUpdateTime(null);
        if (bizProduct.getQuality() == null)
        {
            bizProduct.setQuality(3);
        }
        if (bizProduct.getTradeType() == null)
        {
            bizProduct.setTradeType(1);
        }
        if (bizProduct.getStatus() == null)
        {
            bizProduct.setStatus(1);
        }
        return bizProductMapper.insertBizProduct(bizProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBizProduct(BizProduct bizProduct)
    {
        if (bizProduct.getId() == null)
        {
            throw new ServiceException("商品ID不能为空");
        }
        // 不允许通过普通编辑修改卖家、浏览量和删除标志
        bizProduct.setSellerId(null);
        bizProduct.setViewCount(null);
        bizProduct.setDelFlag(null);
        bizProduct.setCreateBy(null);
        bizProduct.setCreateTime(null);
        bizProduct.setUpdateBy(SecurityUtils.getUsername());
        bizProduct.setUpdateTime(DateUtils.getNowDate());
        return bizProductMapper.updateBizProduct(bizProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBizProductByIds(Long[] ids)
    {
        if (ids == null || ids.length == 0)
        {
            return 0;
        }
        return bizProductMapper.deleteBizProductByIds(ids, SecurityUtils.getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBizProductById(Long id)
    {
        if (id == null)
        {
            throw new ServiceException("商品ID不能为空");
        }
        return bizProductMapper.deleteBizProductById(id, SecurityUtils.getUsername());
    }
}
