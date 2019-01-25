package com.ypj.dao;

import com.ypj.dataobject.SalesDO;
import org.apache.ibatis.annotations.Param;

public interface SalesDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesDO record);

    int insertSelective(SalesDO record);

    SalesDO selectByPrimaryKey(Integer id);

    /**
     * 根据商品id获取该商品销量信息
     *
     * @param wareId
     * @return
     */
    SalesDO selectByWareId(Integer wareId);

    int updateByPrimaryKeySelective(SalesDO record);

    /**
     * 根据商品id和购买商品数量增加该商品的销量
     *
     * @param wareId
     * @param amount
     * @return
     */
    int addSales(@Param("wareId") Integer wareId, @Param("amount") Integer amount);

    int updateByPrimaryKey(SalesDO record);
}