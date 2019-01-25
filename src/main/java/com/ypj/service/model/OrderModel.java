package com.ypj.service.model;

import java.math.BigDecimal;

/**
 * 用于业务处理的订单模型
 *
 * @ClassName OrderModel
 * @Author pain
 * @Date 2019/1/10 22:45
 * @Version 1.0
 **/
public class OrderModel {
    // id，形式：20190110xxxxxxxx
    private String id;

    // 用户id
    private Integer userId;

    // 商品id
    private Integer wareId;

    // 要购买的商品数量
    private Integer amount;

    // 促销活动的id，若promotionId非空，则表示该订单来自秒杀活动
    private Integer promotionId;

    // 购买商品的价格，若promotionId非空，则表示该订单的商品价格应为促销活动价格
    private BigDecimal warePrice;

    // 总金额，若promotionId非空，则表示该订单的总金额应按照促销活动价格计算
    private BigDecimal sumPrice;

    public OrderModel() {
    }

    public OrderModel(String id, Integer userId, Integer wareId, Integer amount, Integer promotionId, BigDecimal warePrice, BigDecimal sumPrice) {
        this.id = id;
        this.userId = userId;
        this.wareId = wareId;
        this.amount = amount;
        this.promotionId = promotionId;
        this.warePrice = warePrice;
        this.sumPrice = sumPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"userId\":")
                .append(userId);
        sb.append(",\"wareId\":")
                .append(wareId);
        sb.append(",\"amount\":")
                .append(amount);
        sb.append(",\"promotionId\":")
                .append(promotionId);
        sb.append(",\"warePrice\":")
                .append(warePrice);
        sb.append(",\"sumPrice\":")
                .append(sumPrice);
        sb.append('}');
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getWareId() {
        return wareId;
    }

    public void setWareId(Integer wareId) {
        this.wareId = wareId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public BigDecimal getWarePrice() {
        return warePrice;
    }

    public void setWarePrice(BigDecimal warePrice) {
        this.warePrice = warePrice;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }
}
