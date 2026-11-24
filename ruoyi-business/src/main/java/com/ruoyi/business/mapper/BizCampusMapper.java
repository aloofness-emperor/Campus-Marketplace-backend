package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BizCampus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 校区Mapper接口
 *
 * @author ruoyi
 * @date 2026-11-24
 */
@Mapper
public interface BizCampusMapper
{
    /**
     * 查询校区列表
     *
     * @param bizCampus 查询条件
     * @return 校区集合
     */
    public List<BizCampus> selectBizCampusList(BizCampus bizCampus);
}
