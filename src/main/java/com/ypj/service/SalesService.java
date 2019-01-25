package com.ypj.service;

/**
 * @InterfaceName SalesService
 * @Author pain
 * @Date 2019/1/11 20:29
 * @Version 1.0
 **/
public interface SalesService {

    /**
     * 根据商品id和购买商品数量增加该商品的销量
     *
     * @param wareId
     * @param amount
     */
    void addSales(Integer wareId, Integer amount);

}
