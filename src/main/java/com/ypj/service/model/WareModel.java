package com.ypj.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 用于业务处理的商品模型
 *
 * @ClassName WareModel
 * @Author pain
 * @Date 2019/1/8 19:46
 * @Version 1.0
 **/
public class WareModel {
    // id
    private Integer id;

    // 使用聚合模型，如果promotionModel不为空，表示该商品拥有未结束的促销活动
    private PromotionModel promotionModel;

    // 商品标题
    @NotBlank(message = "请输入商品标题")
    private String title;

    // 商品描述
    @NotBlank(message = "请输入商品描述")
    private String description;

    // 商品图片
    @NotBlank(message = "请输入图片URL")
    private String imageUrl;

    // 商品价格
    @NotNull(message = "请输入商品价格，最低为0")
    @Min(value = 0, message = "价格必须大于等于0")
    private BigDecimal price;

    // 商品库存
    @NotNull(message = "请输入商品库存，最低为0")
    @Min(value = 0, message = "库存必须大于等于0")
    private Integer stock;

    // 商品销量，销量是从数据库统计出来的，不是输入的
    private Integer sales;

    public WareModel() {
    }

    public WareModel(Integer id, PromotionModel promotionModel, String title, String description, String imageUrl, BigDecimal price, Integer stock, Integer sales) {
        this.id = id;
        this.promotionModel = promotionModel;
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
        sb.append(",\"promotionModel\":")
                .append(promotionModel);
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

    public PromotionModel getPromotionModel() {
        return promotionModel;
    }

    public void setPromotionModel(PromotionModel promotionModel) {
        this.promotionModel = promotionModel;
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
