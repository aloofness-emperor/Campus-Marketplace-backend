package com.ruoyi.business.domain;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.xss.Xss;

/**
 * 商品对象 biz_product
 *
 * @author ruoyi
 * @date 2026-11-24
 */
public class BizProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    private Long id;

    /** 卖家用户ID */
    @Excel(name = "卖家用户ID")
    @NotNull(message = "卖家不能为空")
    private Long sellerId;

    /** 分类ID */
    @Excel(name = "分类ID")
    @NotNull(message = "分类不能为空")
    private Long categoryId;

    /** 校区ID */
    @Excel(name = "校区ID")
    @NotNull(message = "校区不能为空")
    private Long campusId;

    /** 商品标题 */
    @Excel(name = "商品标题")
    @NotBlank(message = "商品标题不能为空")
    @Size(max = 100, message = "商品标题不能超过100个字符")
    @Xss(message = "商品标题不能包含脚本字符")
    private String title;

    /** 商品详情描述 */
    @Size(max = 5000, message = "商品描述不能超过5000个字符")
    @Xss(message = "商品描述不能包含脚本字符")
    private String description;

    /** 售价 */
    @Excel(name = "售价")
    @NotNull(message = "售价不能为空")
    @DecimalMin(value = "0.01", message = "售价必须大于0")
    private BigDecimal price;

    /** 原价 */
    @Excel(name = "原价")
    @DecimalMin(value = "0.00", message = "原价不能为负数")
    private BigDecimal originalPrice;

    /** 商品图片URL，逗号分隔 */
    @Size(max = 1000, message = "商品图片地址不能超过1000个字符")
    private String images;

    /** 成色（1全新 2九成新 3八成新 4七成新及以下） */
    @Excel(name = "成色", readConverterExp = "1=全新,2=九成新,3=八成新,4=七成新及以下")
    @Min(value = 1, message = "成色不合法")
    @Max(value = 4, message = "成色不合法")
    private Integer quality;

    /** 交易方式（1自提 2邮寄 3面交） */
    @Excel(name = "交易方式", readConverterExp = "1=自提,2=邮寄,3=面交")
    @Min(value = 1, message = "交易方式不合法")
    @Max(value = 3, message = "交易方式不合法")
    private Integer tradeType;

    /** 状态（0下架 1上架 2已售出 3违规下架） */
    @Excel(name = "状态", readConverterExp = "0=下架,1=上架,2=已售出,3=违规下架")
    @Min(value = 0, message = "状态不合法")
    @Max(value = 3, message = "状态不合法")
    private Integer status;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Integer viewCount;

    /** 删除标志（0未删除 1已删除） */
    private Integer delFlag;

    /** 校区名称（关联查询字段） */
    @Excel(name = "校区")
    private String campusName;

    /** 分类名称（关联查询字段） */
    @Excel(name = "分类")
    private String categoryName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setSellerId(Long sellerId)
    {
        this.sellerId = sellerId;
    }

    public Long getSellerId()
    {
        return sellerId;
    }

    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }

    public void setCampusId(Long campusId)
    {
        this.campusId = campusId;
    }

    public Long getCampusId()
    {
        return campusId;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setOriginalPrice(BigDecimal originalPrice)
    {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getOriginalPrice()
    {
        return originalPrice;
    }

    public void setImages(String images)
    {
        this.images = images;
    }

    public String getImages()
    {
        return images;
    }

    public void setQuality(Integer quality)
    {
        this.quality = quality;
    }

    public Integer getQuality()
    {
        return quality;
    }

    public void setTradeType(Integer tradeType)
    {
        this.tradeType = tradeType;
    }

    public Integer getTradeType()
    {
        return tradeType;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setViewCount(Integer viewCount)
    {
        this.viewCount = viewCount;
    }

    public Integer getViewCount()
    {
        return viewCount;
    }

    public void setDelFlag(Integer delFlag)
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag()
    {
        return delFlag;
    }

    public String getCampusName()
    {
        return campusName;
    }

    public void setCampusName(String campusName)
    {
        this.campusName = campusName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sellerId", getSellerId())
            .append("categoryId", getCategoryId())
            .append("campusId", getCampusId())
            .append("title", getTitle())
            .append("description", getDescription())
            .append("price", getPrice())
            .append("originalPrice", getOriginalPrice())
            .append("images", getImages())
            .append("quality", getQuality())
            .append("tradeType", getTradeType())
            .append("status", getStatus())
            .append("viewCount", getViewCount())
            .append("campusName", getCampusName())
            .append("categoryName", getCategoryName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
