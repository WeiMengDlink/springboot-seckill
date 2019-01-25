package com.ypj.controller.viewobject;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @ClassName WareVO
 * @Author pain
 * @Date 2019/1/10 13:46
 * @Version 1.0
 **/
public class WareVO {
    // id
    private Integer id;

    // 添加用于促销活动的属性
    // 促销活动id
    private Integer promotionId;

    // 促销活动状态
    private Integer promotionStatus;

    // 促销活动商品价格
    private BigDecimal promotionPrice;

    // 促销活动开始时间，为了简化操作使用String类型显示时间
    private String startDate;

    // 商品标题
    private String title;

    // 商品描述
    private String description;

    // 商品图片
    private String imageUrl;

    // 商品价格
    private BigDecimal price;

    // 商品库存
    private Integer stock;

    // 商品销量
    private Integer sales;

    public WareVO() {
    }

    public WareVO(Integer id, Integer promotionId, Integer promotionStatus, BigDecimal promotionPrice
            , String startDate, String title, String description, String imageUrl, BigDecimal price
            , Integer stock, Integer sales) {
        this.id = id;
        this.promotionId = promotionId;
        this.promotionStatus = promotionStatus;
        this.promotionPrice = promotionPrice;
        this.startDate = startDate;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
        this.sales = sales;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"promotionId\":")
                .append(promotionId);
        sb.append(",\"promotionStatus\":")
                .append(promotionStatus);
        sb.append(",\"promotionPrice\":")
                .append(promotionPrice);
        sb.append(",\"startDate\":")
                .append(startDate);
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"description\":\"")
                .append(description).append('\"');
        sb.append(",\"imageUrl\":\"")
                .append(imageUrl).append('\"');
        sb.append(",\"price\":")
                .append(price);
        sb.append(",\"stock\":")
                .append(stock);
        sb.append(",\"sales\":")
                .append(sales);
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(Integer promotionStatus) {
        this.promotionStatus = promotionStatus;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }
}
