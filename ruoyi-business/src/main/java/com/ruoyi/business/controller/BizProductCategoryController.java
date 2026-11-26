package com.ruoyi.business.controller;

import java.util.List;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
@Validated
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

    /**
     * 查询分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('business:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizProductCategory bizProductCategory)
    {
        startPage();
        List<BizProductCategory> list = bizProductCategoryService.selectBizProductCategoryList(bizProductCategory);
        return getDataTable(list);
    }

    /**
     * 导出分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('business:category:export')")
    @Log(title = "分类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizProductCategory bizProductCategory)
    {
        List<BizProductCategory> list = bizProductCategoryService.selectBizProductCategoryList(bizProductCategory);
        ExcelUtil<BizProductCategory> util = new ExcelUtil<BizProductCategory>(BizProductCategory.class);
        util.exportExcel(response, list, "分类管理数据");
    }

    /**
     * 获取分类管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        BizProductCategory category = bizProductCategoryService.selectBizProductCategoryById(id);
        return category == null ? error("分类不存在或已删除") : success(category);
    }

    /**
     * 新增分类管理
     */
    @PreAuthorize("@ss.hasPermi('business:category:add')")
    @Log(title = "分类管理", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@Valid @RequestBody BizProductCategory bizProductCategory)
    {
        return toAjax(bizProductCategoryService.insertBizProductCategory(bizProductCategory));
    }

    /**
     * 修改分类管理
     */
    @PreAuthorize("@ss.hasPermi('business:category:edit')")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody BizProductCategory bizProductCategory)
    {
        return toAjax(bizProductCategoryService.updateBizProductCategory(bizProductCategory));
    }

    /**
     * 删除分类管理
     */
    @PreAuthorize("@ss.hasPermi('business:category:remove')")
    @Log(title = "分类管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizProductCategoryService.deleteBizProductCategoryByIds(ids));
    }
}
