package com.ruoyi.business.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.business.domain.BizCampus;
import com.ruoyi.business.service.IBizCampusService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * 校区Controller
 *
 * @author ruoyi
 * @date 2026-11-24
 */
@RestController
@RequestMapping("/business/campus")
public class BizCampusController extends BaseController
{
    @Autowired
    private IBizCampusService bizCampusService;

    /**
     * 查询校区下拉列表（只返回启用的校区）
     */
    @PreAuthorize("@ss.hasPermi('business:product:list')")
    @GetMapping("/list")
    public AjaxResult list(BizCampus bizCampus)
    {
        bizCampus.setStatus(1);
        List<BizCampus> list = bizCampusService.selectBizCampusList(bizCampus);
        return success(list);
    }
}
