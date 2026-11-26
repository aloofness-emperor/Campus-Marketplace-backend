package com.ruoyi.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.business.domain.BizProductCategory;
import com.ruoyi.business.mapper.BizProductCategoryMapper;
import com.ruoyi.business.service.IBizProductCategoryService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;

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

    @Override
    public BizProductCategory selectBizProductCategoryById(Long id)
    {
        return bizProductCategoryMapper.selectBizProductCategoryById(id);
    }

    @Override
    public List<BizProductCategory> selectBizProductCategoryList(BizProductCategory bizProductCategory)
    {
        return bizProductCategoryMapper.selectBizProductCategoryList(bizProductCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertBizProductCategory(BizProductCategory bizProductCategory)
    {
        normalizeName(bizProductCategory);
        if (bizProductCategory.getParentId() == null)
        {
            bizProductCategory.setParentId(0L);
        }
        if (bizProductCategory.getStatus() == null)
        {
            bizProductCategory.setStatus(1);
        }
        if (bizProductCategory.getSort() == null)
        {
            bizProductCategory.setSort(0);
        }

        bizProductCategory.setId(null);
        bizProductCategory.setDelFlag(0);
        bizProductCategory.setCreateBy(SecurityUtils.getUsername());
        bizProductCategory.setCreateTime(DateUtils.getNowDate());
        bizProductCategory.setUpdateBy(null);
        bizProductCategory.setUpdateTime(null);

        checkParent(bizProductCategory);
        checkNameUnique(bizProductCategory, null);
        return bizProductCategoryMapper.insertBizProductCategory(bizProductCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBizProductCategory(BizProductCategory bizProductCategory)
    {
        if (bizProductCategory.getId() == null)
        {
            throw new ServiceException("分类ID不能为空");
        }
        BizProductCategory old = bizProductCategoryMapper.selectBizProductCategoryById(bizProductCategory.getId());
        if (old == null)
        {
            throw new ServiceException("分类不存在或已删除");
        }

        normalizeName(bizProductCategory);
        // 未传 parentId 时沿用原父级，避免部分更新把分类误移到根节点
        if (bizProductCategory.getParentId() == null)
        {
            bizProductCategory.setParentId(old.getParentId());
        }

        checkParent(bizProductCategory);
        checkNameUnique(bizProductCategory, bizProductCategory.getId());

        // 审计字段和删除标志只能由服务端维护
        bizProductCategory.setCreateBy(null);
        bizProductCategory.setCreateTime(null);
        bizProductCategory.setDelFlag(null);
        bizProductCategory.setUpdateBy(SecurityUtils.getUsername());
        bizProductCategory.setUpdateTime(DateUtils.getNowDate());
        return bizProductCategoryMapper.updateBizProductCategory(bizProductCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBizProductCategoryByIds(Long[] ids)
    {
        if (ids == null || ids.length == 0)
        {
            return 0;
        }
        for (Long id : ids)
        {
            if (id == null)
            {
                continue;
            }
            BizProductCategory category = bizProductCategoryMapper.selectBizProductCategoryById(id);
            if (category == null)
            {
                continue;
            }
            if (bizProductCategoryMapper.countChildrenByParentId(id) > 0)
            {
                throw new ServiceException("分类【" + category.getName() + "】下存在子分类，不能删除");
            }
            if (bizProductCategoryMapper.countProductByCategoryId(id) > 0)
            {
                throw new ServiceException("分类【" + category.getName() + "】下存在商品，不能删除");
            }
        }
        return bizProductCategoryMapper.deleteBizProductCategoryByIds(ids, SecurityUtils.getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBizProductCategoryById(Long id)
    {
        if (id == null)
        {
            throw new ServiceException("分类ID不能为空");
        }
        return deleteBizProductCategoryByIds(new Long[] { id });
    }

    /**
     * 名称去空格 + 基础校验
     */
    private void normalizeName(BizProductCategory category)
    {
        if (StringUtils.isBlank(category.getName()))
        {
            throw new ServiceException("分类名称不能为空");
        }
        category.setName(category.getName().trim());
    }

    /**
     * 校验父分类是否存在、是否自引用、是否形成环
     */
    private void checkParent(BizProductCategory category)
    {
        Long id = category.getId();
        Long parentId = category.getParentId();
        if (parentId == null || parentId == 0L)
        {
            category.setParentId(0L);
            return;
        }
        if (parentId.equals(id))
        {
            throw new ServiceException("父分类不能是自己");
        }
        BizProductCategory parent = bizProductCategoryMapper.selectBizProductCategoryById(parentId);
        if (parent == null)
        {
            throw new ServiceException("父分类不存在或已删除");
        }

        // 从父节点向上找祖先，若遇到当前节点说明把当前分类挂到了自己的子孙下
        Long cursor = parent.getParentId();
        int depth = 0;
        while (cursor != null && cursor != 0L && depth++ < 100)
        {
            if (cursor.equals(id))
            {
                throw new ServiceException("父分类不能是当前分类的子分类");
            }
            BizProductCategory node = bizProductCategoryMapper.selectBizProductCategoryById(cursor);
            if (node == null)
            {
                break;
            }
            cursor = node.getParentId();
        }
    }

    /**
     * 同级下分类名称不能重复
     */
    private void checkNameUnique(BizProductCategory category, Long excludeId)
    {
        Long parentId = category.getParentId() == null ? 0L : category.getParentId();
        int count = bizProductCategoryMapper.countByName(parentId, category.getName(), excludeId);
        if (count > 0)
        {
            throw new ServiceException("同级下已存在同名分类");
        }
    }
}
