package com.ypj.service;

import com.ypj.service.model.PromotionModel;

/**
 * @InterfaceName PromotionService
 * @Author pain
 * @Date 2019/1/11 22:36
 * @Version 1.0
 **/
public interface PromotionService {

    /**
     * 根据商品id查找该商品的促销活动
     *
     * @param wareId
     * @return
     */
    PromotionModel getPromotionByWareId(Integer wareId);

}
