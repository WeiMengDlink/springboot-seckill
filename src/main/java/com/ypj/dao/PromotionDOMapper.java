package com.ypj.dao;

import com.ypj.dataobject.PromotionDO;

public interface PromotionDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PromotionDO record);

    int insertSelective(PromotionDO record);

    PromotionDO selectByPrimaryKey(Integer id);

    PromotionDO getPromotionByWareId(Integer wareId);

    int updateByPrimaryKeySelective(PromotionDO record);

    int updateByPrimaryKey(PromotionDO record);
}