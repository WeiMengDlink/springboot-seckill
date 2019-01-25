package com.ypj.service;

import com.ypj.service.model.WareModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 定义商品操作
 */
public interface WareService {

    /**
     * 添加商品信息
     *
     * @param wareModel
     * @return
     */
    WareModel insertWare(WareModel wareModel);

    /**
     * 根据id获取商品信息
     *
     * @param id
     * @return
     */
    WareModel getWareById(Integer id);

    /**
     * 获取所有商品信息
     *
     * @return
     */
    List<WareModel> listWares();

}
