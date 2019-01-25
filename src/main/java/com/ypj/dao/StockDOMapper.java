package com.ypj.dao;

import com.ypj.dataobject.StockDO;
import org.apache.ibatis.annotations.Param;

public interface StockDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockDO record);

    int insertSelective(StockDO record);

    StockDO selectByPrimaryKey(Integer id);

    /**
     * 根据商品id获取该商品的库存信息
     *
     * @param wareId
     * @return
     */
    StockDO selectByWareId(Integer wareId);

    /**
     * 根据商品id减少该商品的库存
     *
     * @param wareId
     * @param amount
     * @return
     */
    int reduceWareStock(@Param("wareId") Integer wareId,@Param("amount") Integer amount);

    int updateByPrimaryKeySelective(StockDO record);

    int updateByPrimaryKey(StockDO record);
}