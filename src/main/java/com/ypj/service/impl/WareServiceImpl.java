package com.ypj.service.impl;

import com.ypj.dao.SalesDOMapper;
import com.ypj.dao.StockDOMapper;
import com.ypj.dao.WareDOMapper;
import com.ypj.dataobject.SalesDO;
import com.ypj.dataobject.StockDO;
import com.ypj.dataobject.WareDO;
import com.ypj.service.PromotionService;
import com.ypj.service.WareService;
import com.ypj.service.model.PromotionModel;
import com.ypj.service.model.WareModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName WareServiceImpl
 * @Author pain
 * @Date 2019/1/8 20:47
 * @Version 1.0
 **/
@Service
public class WareServiceImpl implements WareService {

    @Autowired
    WareDOMapper wareDOMapper;

    @Autowired
    StockDOMapper stockDOMapper;

    @Autowired
    SalesDOMapper salesDOMapper;

    @Autowired
    PromotionService promotionService;

    /**
     * 添加商品信息
     *
     * @param wareModel
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public WareModel insertWare(WareModel wareModel) {
        // 调用convertWareDOFromWareModel()方法将WareModel转化为WareDO
        WareDO wareDO = convertWareDOFromWareModel(wareModel);
        wareDOMapper.insertSelective(wareDO);

        // 添加WareDO后获取商品id并设置到WareModel，因为该id是自增主键，不是直接输入的
        wareModel.setId(wareDO.getId());

        // 调用convertStockDOFromWareModel()方法将WareModel转化为StockDO
        StockDO stockDO = convertStockDOFromWareModel(wareModel);
        stockDOMapper.insertSelective(stockDO);

        // 调用convertSalesDOFromWareModel()方法将WareModel转化为SalesDO
        SalesDO salesDO = convertSalesDOFromWareModel(wareModel);
        salesDOMapper.insertSelective(salesDO);

        // 返回刚刚创建的WareModel
        return this.getWareById(wareModel.getId());
    }

    /**
     * 根据id获取商品信息
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public WareModel getWareById(Integer id) {
        // 根据id获取商品信息
        WareDO wareDO = wareDOMapper.selectByPrimaryKey(id);
        if (null == wareDO) {
            return null;
        }
        // 根据商品id获取商品库存信息
        StockDO stockDO = stockDOMapper.selectByWareId(wareDO.getId());

        // 根据商品id获取商品销量信息
        SalesDO salesDO = salesDOMapper.selectByWareId(wareDO.getId());

        // 调用convertWareModelFromWareDOAndStockDOAndSalesDO()方法将WareDO、StockDO和SalesDO组装成WareModel
        WareModel wareModel = convertWareModelFromWareDOAndStockDOAndSalesDO(wareDO, stockDO, salesDO);

        // 根据商品id获取促销活动信息
        PromotionModel promotionModel = promotionService.getPromotionByWareId(wareModel.getId());

        // 如果促销活动存在并且活动不是已结束状态
        if (null != promotionModel && promotionModel.getPromotionStatus() != 2) {
            wareModel.setPromotionModel(promotionModel);
        }
        return wareModel;
    }

    /**
     * 获取所有商品信息
     *
     * @return
     */
    @Override
    public List<WareModel> listWares() {
        // 获取所有商品信息，不包含库存和销量信息
        List<WareDO> wareDOList = wareDOMapper.listWareModels();
        // 使用java8 stream().map 将WareDO转化为WareModel
        List<WareModel> wareModelList = wareDOList.stream().map(wareDO -> {
            // 根据商品id获取商品库存信息
            StockDO stockDO = stockDOMapper.selectByWareId(wareDO.getId());
            // 根据商品id获取商品销量信息
            SalesDO salesDO = salesDOMapper.selectByWareId(wareDO.getId());
            // 调用convertWareModelFromWareDOAndStockDOAndSalesDO()方法将WareDO、StockDO、SalesDO组装成WareModel
            WareModel wareModel = this.convertWareModelFromWareDOAndStockDOAndSalesDO(wareDO, stockDO, salesDO);
            return wareModel;
        }).collect(Collectors.toList());
        return wareModelList;
    }

    /**
     * 将WareModel转换为WareDO供数据库层使用
     *
     * @param wareModel
     * @return
     */
    private WareDO convertWareDOFromWareModel(WareModel wareModel) {
        if (null == wareModel) {
            return null;
        }
        WareDO wareDO = new WareDO();
        BeanUtils.copyProperties(wareModel, wareDO);
        return wareDO;
    }

    /**
     * 将WareModel转换为StockDO供数据库层使用
     *
     * @param wareModel
     * @return
     */
    private StockDO convertStockDOFromWareModel(WareModel wareModel) {
        if (null == wareModel) {
            return null;
        }
        StockDO stockDO = new StockDO();
        // 从WareModel获取商品id和商品库存并设置到StockDO
        stockDO.setWareId(wareModel.getId());
        stockDO.setStock(wareModel.getStock());
        return stockDO;
    }

    /**
     * 将WareModel转换为SalesDO供数据库层使用
     *
     * @param wareModel
     * @return
     */
    private SalesDO convertSalesDOFromWareModel(WareModel wareModel) {
        if (null == wareModel) {
            return null;
        }
        SalesDO salesDO = new SalesDO();
        // 从WareModel获取商品id和商品销量并设置到SalesDO
        salesDO.setWareId(wareModel.getId());
        salesDO.setSales(wareModel.getSales());
        return salesDO;
    }

    /**
     * 将WareDO、StockDO、SalesDO组装成WareModel
     *
     * @param wareDO
     * @param stockDO
     * @param salesDO
     * @return
     */
    private WareModel convertWareModelFromWareDOAndStockDOAndSalesDO(WareDO wareDO, StockDO stockDO, SalesDO salesDO) {
        WareModel wareModel = new WareModel();
        if (null == wareDO) {
            return null;
        }
        BeanUtils.copyProperties(wareDO, wareModel);
        if (null == stockDO) {
            return null;
        }
        // 从StockDO获取库存数量并设置到WareModel
        wareModel.setStock(stockDO.getStock());
        if (null == salesDO) {
            return null;
        }
        // 从SalesDO获取销量并设置到WareModel
        wareModel.setSales(salesDO.getSales());
        return wareModel;
    }
}
