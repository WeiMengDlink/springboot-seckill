package com.ypj.service.impl;

import com.ypj.dao.StockDOMapper;
import com.ypj.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName StockServiceImpl
 * @Author pain
 * @Date 2019/1/10 23:41
 * @Version 1.0
 **/
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockDOMapper stockDOMapper;

    /**
     * 减少商品库存
     *
     * @param wareId
     * @param amount
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int reduceWareStock(Integer wareId, Integer amount) {
        return stockDOMapper.reduceWareStock(wareId, amount);
    }

}
