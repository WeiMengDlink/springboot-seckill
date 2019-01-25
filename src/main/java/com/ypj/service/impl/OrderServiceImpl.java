package com.ypj.service.impl;

import com.ypj.dao.OrderDOMapper;
import com.ypj.dataobject.OrderDO;
import com.ypj.error.ProcessErrorEnum;
import com.ypj.error.ProcessException;
import com.ypj.service.*;
import com.ypj.service.model.OrderModel;
import com.ypj.service.model.WareModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @ClassName OrderServiceImpl
 * @Author pain
 * @Date 2019/1/10 23:09
 * @Version 1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserService userService;

    @Autowired
    WareService wareService;

    @Autowired
    StockService stockService;

    @Autowired
    SalesService salesService;

    @Autowired
    SequenceService sequenceService;

    @Autowired
    OrderDOMapper orderDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderModel insertOrder(Integer userId, Integer wareId, Integer promotionId, Integer amount) throws ProcessException {
        WareModel wareModel = wareService.getWareById(wareId);
        // 如果商品不存在抛出异常
        if (null == wareModel) {
            throw new ProcessException(ProcessErrorEnum.WARE_NOT_EXIST);
        }
        // 如果购买数量不合法或者大于该商品的库存，抛出异常
        if (amount <= 0 || amount >= wareModel.getStock()) {
            throw new ProcessException(ProcessErrorEnum.INVALID_PARAMETER, "购买数量不合法或商品库存不足");
        }
        // 判断促销活动id对应的活动是否有该商品
        if (null != promotionId) {
            // (1).检查该商品的促销活动id是否与传入的促销活动id匹配，不匹配抛出异常
            if (!promotionId.equals(wareModel.getPromotionModel().getId())) {
                throw new ProcessException(ProcessErrorEnum.INVALID_PARAMETER, "该商品没有促销活动");
            } else if (1 != wareModel.getPromotionModel().getPromotionStatus().intValue()) {
                // (2).检查该商品的促销活动是否为正在进行状态，状态错误抛出异常
                throw new ProcessException(ProcessErrorEnum.INVALID_PARAMETER, "该商品促销活动还未开始，请选择其他商品");
            }
        }

        // 2、对应商品库存数减少
        int affectedRows = stockService.reduceWareStock(wareId, amount);
        // 减库存失败抛出异常
        if (affectedRows <= 0) {
            throw new ProcessException(ProcessErrorEnum.STOCK_NOT_ENOUGH);
        }

        // 3、创建订单并设置订单的用户id、商品id、商品数量
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setWareId(wareId);
        // 设置促销活动id
        orderModel.setPromotionId(promotionId);
        orderModel.setAmount(amount);
        // 根据该商品是否存在促销活动判断商品价格取正常价格还是秒杀活动价格
        if (null != promotionId) {
            // 取促销活动价格
            orderModel.setWarePrice(wareModel.getPromotionModel().getWarePrice());
        } else {
            // 取正常价格
            orderModel.setWarePrice(wareModel.getPrice());
        }
        // 调用SequenceService的generateOrderId()方法生成订单id
        String orderId = sequenceService.generateOrderId();
        orderModel.setId(orderId);
        orderModel.setSumPrice(orderModel.getWarePrice().multiply(new BigDecimal(orderModel.getAmount())));
        // 调用方法将OrderModel转换为OrderDO
        OrderDO orderDO = convertOrderDOFromOrderModel(orderModel);
        // 添加订单
        orderDOMapper.insertSelective(orderDO);

        // 4、对应商品销量增加
        salesService.addSales(orderModel.getWareId(), orderModel.getAmount());

        return orderModel;
    }

    /**
     * 将OrderModel转换为OrderDO供数据库层操作
     *
     * @param orderModel
     * @return
     */
    private OrderDO convertOrderDOFromOrderModel(OrderModel orderModel) {
        if (null == orderModel) {
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        return orderDO;
    }

}
