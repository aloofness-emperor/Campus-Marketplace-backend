package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.domain.BizCampus;
import com.ruoyi.business.mapper.BizCampusMapper;
import com.ruoyi.business.service.IBizCampusService;

/**
 * 校区Service业务层处理
 *
 * @author ruoyi
 * @date 2026-11-24
 */
@Service
public class BizCampusServiceImpl implements IBizCampusService
{
    @Autowired
    private BizCampusMapper bizCampusMapper;

    @Override
    public List<BizCampus> selectBizCampusList(BizCampus bizCampus)
    {
        return bizCampusMapper.selectBizCampusList(bizCampus);
    }
}
