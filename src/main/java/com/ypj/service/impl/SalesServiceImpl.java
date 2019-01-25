package com.ypj.service.impl;

import com.ypj.dao.SalesDOMapper;
import com.ypj.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName SalesServiceImpl
 * @Author pain
 * @Date 2019/1/11 20:30
 * @Version 1.0
 **/
@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    SalesDOMapper salesDOMapper;

    /**
     * 根据商品id和购买商品数量增加该商品的销量
     *
     * @param wareId
     * @param amount
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSales(Integer wareId, Integer amount) {
        salesDOMapper.addSales(wareId, amount);
    }
}
