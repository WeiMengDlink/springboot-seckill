package com.ypj.service.impl;

import com.ypj.dao.PromotionDOMapper;
import com.ypj.dataobject.PromotionDO;
import com.ypj.service.PromotionService;
import com.ypj.service.model.PromotionModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName PromotionServiceImpl
 * @Author pain
 * @Date 2019/1/11 22:38
 * @Version 1.0
 **/
@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    PromotionDOMapper promotionDOMapper;

    /**
     * 根据商品id获取该商品的促销活动
     *
     * @param wareId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PromotionModel getPromotionByWareId(Integer wareId) {
        PromotionDO promotionDO = promotionDOMapper.getPromotionByWareId(wareId);
        // 调用convertPromotionModelFromPromotionDO()方法将PromotionDO转换为PromotionModel
        PromotionModel promotionModel = convertPromotionModelFromPromotionDO(promotionDO);
        if (null == promotionModel) {
            return null;
        }

        // 根据当前时间判断促销活动是未开始、进行中、已结束中哪一种状态
        if (promotionModel.getStartDate().isAfterNow()) {
            // 活动还没开始
            promotionModel.setPromotionStatus(0);
        } else if (promotionModel.getEndDate().isBeforeNow()) {
            // 活动已经结束
            promotionModel.setPromotionStatus(2);
        } else {
            // 活动正在进行
            promotionModel.setPromotionStatus(1);
        }
        return promotionModel;
    }

    /**
     * 将PromotionDO转换为PromotionModel
     *
     * @param promotionDO
     * @return
     */
    private PromotionModel convertPromotionModelFromPromotionDO(PromotionDO promotionDO) {
        if (null == promotionDO) {
            return null;
        }
        PromotionModel promotionModel = new PromotionModel();
        BeanUtils.copyProperties(promotionDO, promotionModel);
        // 手动进行一些属性修正
        promotionModel.setStartDate(new DateTime(promotionDO.getStartDate()));
        promotionModel.setEndDate(new DateTime(promotionDO.getEndDate()));
        promotionModel.setWarePrice(promotionDO.getWarePrice());
        return promotionModel;
    }

}
