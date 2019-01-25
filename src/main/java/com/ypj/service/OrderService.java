package com.ypj.service;

import com.ypj.error.ProcessException;
import com.ypj.service.model.OrderModel;

/**
 * @InterfaceName OrderService
 * @Author pain
 * @Date 2019/1/10 23:06
 * @Version 1.0
 **/
public interface OrderService {

    /**
     * 添加订单，这里使用第一种设计方案
     * 1、通过前端URL传递秒杀活动id，在订单接口中校验该id是否属于对应的商品，且该促销活动已开始
     * 2、直接在订单接口中判断商品是否有促销活动，如果促销活动存在则按照促销活动价格下单
     *
     * @param userId
     * @param wareId
     * @param promotionId
     * @param amount
     * @return
     * @throws ProcessException
     */
    OrderModel insertOrder(Integer userId, Integer wareId, Integer promotionId, Integer amount) throws ProcessException;

}
