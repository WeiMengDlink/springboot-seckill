package com.ypj.service.impl;

import com.ypj.dao.SequenceDOMapper;
import com.ypj.dataobject.SequenceDO;
import com.ypj.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName SequenceServiceImpl
 * @Author pain
 * @Date 2019/1/11 18:36
 * @Version 1.0
 **/
@Service
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    SequenceDOMapper sequenceDOMapper;

    /**
     * 根据名称获取序列
     *
     * @param name
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SequenceDO selectByName(String name) {
        return sequenceDOMapper.selectByName(name);
    }

    /**
     * 生成订单id
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public String generateOrderId() {
        // 使用StringBuilder拼接id
        StringBuilder stringBuilder = new StringBuilder();
        // 获取当前日期
        LocalDate localDate = LocalDate.now();
        // BASIC_ISO_DATE：2019-01-11，详情参考https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        String dateString = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        // 拼接8位年月日
        stringBuilder.append(dateString);
        // 获取SequenceDO
        SequenceDO sequenceDO = sequenceDOMapper.selectByName("order_sq");
        // 判断当前值是否达到最大值，最大值：999999，初始值：0
        if (sequenceDO.getCurrentValue() > sequenceDO.getMaxValue()) {
            // 设置当前值为初始值
            sequenceDO.setCurrentValue(sequenceDO.getInitValue());
            // 更新序列
            sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        }
        // 获取该序列的当前值
        int currentValue = sequenceDO.getCurrentValue();
        // 根据步长值更新当前值
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        // 更新该序列
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        // 将序列当前值转为String类型
        String currentValueString = String.valueOf(currentValue);
        // 根据当前值大小（位数）判断是否需要填充0
        for (int i = 0; i < 6 - currentValueString.length(); i++) {
            // 位数不够6位拼接0
            stringBuilder.append(0);
        }
        // 拼接完0后拼接序列的当前值
        stringBuilder.append(currentValueString);
        // 添加分库分表位，这里暂时使用固定数字
        stringBuilder.append("01");
        return stringBuilder.toString();
    }

    /**
     * 更新序列
     *
     * @param sequenceDO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSequence(SequenceDO sequenceDO) {
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
    }

}
