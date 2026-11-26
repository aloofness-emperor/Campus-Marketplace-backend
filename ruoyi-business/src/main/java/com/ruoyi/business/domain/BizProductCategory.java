package com.ruoyi.business.domain;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.xss.Xss;

/**
 * 商品分类对象 biz_product_category
 *
 * @author ruoyi
 * @date 2026-11-24
 */
public class BizProductCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID */
    private Long id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    @NotBlank(message = "分类名称不能为空")
    @Size(max = 30, message = "分类名称不能超过30个字符")
    @Xss(message = "分类名称不能包含脚本字符")
    private String name;

    /** 父分类ID（0为一级分类） */
    @Min(value = 0, message = "父分类不合法")
    private Long parentId;

    /** 分类图标 */
    @Size(max = 255, message = "图标地址不能超过255个字符")
    private String icon;

    /** 排序 */
    @Excel(name = "排序")
    @Min(value = 0, message = "排序不能为负数")
    private Integer sort;

    /** 状态（0停用 1启用） */
    @Excel(name = "状态", readConverterExp = "0=停用,1=启用")
    @Min(value = 0, message = "状态不合法")
    @Max(value = 1, message = "状态不合法")
    private Integer status;

    /** 删除标志（0未删除 1已删除） */
    private Integer delFlag;

    /** 父分类名称（关联查询字段） */
    @Excel(name = "父分类")
    private String parentName;

    /** 子分类（非数据库字段） */
    private List<BizProductCategory> children = new ArrayList<>();

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public Integer getSort()
    {
        return sort;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setDelFlag(Integer delFlag)
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag()
    {
        return delFlag;
    }

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    public List<BizProductCategory> getChildren()
    {
        return children;
    }

    public void setChildren(List<BizProductCategory> children)
    {
        this.children = children;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("parentId", getParentId())
            .append("parentName", getParentName())
            .append("icon", getIcon())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
