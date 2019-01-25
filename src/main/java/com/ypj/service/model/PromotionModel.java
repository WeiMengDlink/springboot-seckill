package com.ypj.service.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * 用于业务处理的促销活动模型
 *
 * @ClassName PromotionModel
 * @Author pain
 * @Date 2019/1/11 22:04
 * @Version 1.0
 **/
public class PromotionModel {
    // 促销活动的id
    private Integer id;

    // 促销活动的标题
    private String title;

    // 添加一个字段表示促销活动状态：0-未开始，1-进行中，2-已结束
    private Integer promotionStatus;

    // 促销活动的开始时间
    private DateTime startDate;

    // 促销活动的结束时间
    private DateTime endDate;

    // 促销活动的促销商品id
    private Integer wareId;

    // 促销活动的商品价格
    private BigDecimal warePrice;

    public PromotionModel() {
    }

    public PromotionModel(Integer id, String title, Integer promotionStatus, DateTime startDate, DateTime endDate, Integer wareId, BigDecimal warePrice) {
        this.id = id;
        this.title = title;
        this.promotionStatus = promotionStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.wareId = wareId;
        this.warePrice = warePrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"promotionStatus\":")
                .append(promotionStatus);
        sb.append(",\"startDate\":")
                .append(startDate);
        sb.append(",\"endDate\":")
                .append(endDate);
        sb.append(",\"wareId\":")
                .append(wareId);
        sb.append(",\"warePrice\":")
                .append(warePrice);
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getWareId() {
        return wareId;
    }

    public void setWareId(Integer wareId) {
        this.wareId = wareId;
    }

    public BigDecimal getWarePrice() {
        return warePrice;
    }

    public void setWarePrice(BigDecimal warePrice) {
        this.warePrice = warePrice;
    }

    public Integer getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(Integer promotionStatus) {
        this.promotionStatus = promotionStatus;
    }
}
