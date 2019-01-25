package com.ypj.service;

import com.ypj.dataobject.SequenceDO;

/**
 * @InterfaceName SequenceService
 * @Author pain
 * @Date 2019/1/11 18:35
 * @Version 1.0
 **/
public interface SequenceService {

    /**
     * 根据名称获取序列
     *
     * @param name
     * @return
     */
    SequenceDO selectByName(String name);

    /**
     * 生成订单id
     *
     * @return
     */
    String generateOrderId();

    /**
     * 更新序列
     *
     * @param sequenceDO
     */
    void updateSequence(SequenceDO sequenceDO);

}
