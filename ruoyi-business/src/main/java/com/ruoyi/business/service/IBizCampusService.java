package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizCampus;

/**
 * 校区Service接口
 *
 * @author ruoyi
 * @date 2026-11-24
 */
public interface IBizCampusService
{
    /**
     * 查询校区列表
     *
     * @param bizCampus 查询条件
     * @return 校区集合
     */
    public List<BizCampus> selectBizCampusList(BizCampus bizCampus);
}
