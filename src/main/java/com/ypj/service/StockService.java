package com.ypj.service;

import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName StockService
 * @Author pain
 * @Date 2019/1/10 23:40
 * @Version 1.0
 **/
public interface StockService {

    /**
     * 根据商品id减少该商品的库存
     * @param wareId
     * @param amount
     * @return
     */
    int reduceWareStock(@Param("wareId") Integer wareId, @Param("amount") Integer amount);

}
