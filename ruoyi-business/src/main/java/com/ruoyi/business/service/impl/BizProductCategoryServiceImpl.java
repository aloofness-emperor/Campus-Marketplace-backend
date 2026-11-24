package com.ruoyi.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.domain.BizProductCategory;
import com.ruoyi.business.mapper.BizProductCategoryMapper;
import com.ruoyi.business.service.IBizProductCategoryService;

/**
 * 商品分类Service业务层处理
 *
 * @author ruoyi
 * @date 2026-11-24
 */
@Service
public class BizProductCategoryServiceImpl implements IBizProductCategoryService
{
    @Autowired
    private BizProductCategoryMapper bizProductCategoryMapper;

    @Override
    public List<BizProductCategory> selectCategoryTree()
    {
        BizProductCategory query = new BizProductCategory();
        query.setStatus(1);
        List<BizProductCategory> list = bizProductCategoryMapper.selectBizProductCategoryList(query);

        Map<Long, BizProductCategory> nodeMap = new LinkedHashMap<>();
        for (BizProductCategory category : list)
        {
            category.setChildren(new ArrayList<>());
            nodeMap.put(category.getId(), category);
        }

        List<BizProductCategory> roots = new ArrayList<>();
        for (BizProductCategory category : list)
        {
            Long parentId = category.getParentId();
            if (parentId == null || parentId == 0L)
            {
                roots.add(category);
                continue;
            }
            BizProductCategory parent = nodeMap.get(parentId);
            if (parent == null)
            {
                // 父分类被停用时，子分类仍然作为根节点返回，避免下拉里丢数据
                roots.add(category);
            }
            else
            {
                parent.getChildren().add(category);
            }
        }
        return roots;
    }
}
