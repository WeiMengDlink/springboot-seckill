package com.ypj;

import com.ypj.dataobject.SequenceDO;
import com.ypj.error.ProcessException;
import com.ypj.service.*;
import com.ypj.service.model.WareModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    SequenceService sequenceService;

    @Autowired
    SalesService salesService;

    @Autowired
    WareService wareService;

    @Test
    public void testOrderService() {
        //orderService.insertOrder()
    }


    @Test
    public void testSalesService() {
        //salesService.addSales(11, 1);
        salesService.addSales(12, 4);
    }

    @Test
    public void testGenerateOrderId() {
        // 使用StringBuilder拼接id
        StringBuilder stringBuilder = new StringBuilder();
        // 获取当前日期
        LocalDate localDate = LocalDate.now();
        // BASIC_ISO_DATE：2019-01-11，详情参考https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        String dateString = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        // 拼接8位年月日
        stringBuilder.append(dateString);
        // 获取SequenceDO
        SequenceDO sequenceDO = sequenceService.selectByName("order_sq");
        // 判断当前值是否达到最大值，最大值：999999，初始值：0
        if (sequenceDO.getCurrentValue() > sequenceDO.getMaxValue()) {
            // 设置当前值为初始值
            sequenceDO.setCurrentValue(sequenceDO.getInitValue());
            // 更新序列
            sequenceService.updateSequence(sequenceDO);
        }
        // 获取该序列的当前值
        int currentValue = sequenceDO.getCurrentValue();
        // 根据步长值更新当前值
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        // 更新该序列
        sequenceService.updateSequence(sequenceDO);
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
        System.out.println("----------------------------------" + stringBuilder.toString());
        // 201901110001
    }

    @Test
    public void testUserService() throws ProcessException {

        System.out.println("-------------------" + sequenceService.generateOrderId());

        //UserModel userModel = new UserModel();
        //userModel.setAccountName("疾风剑豪");
        //userModel.setAge((byte) 32);
        //userModel.setGender("m");
        //userModel.setPhoneNumber("18700000001");
        //userModel.setPassword("afsdfasdf");
        //userService.register(userModel);
    }

    @Test
    public void testWareService() {
        //WareModel wareModel01 = new WareModel();
        //wareModel01.setTitle("MacBook Pro 2018");
        //wareModel01.setDescription("2018年新款MacBookPro");
        //wareModel01.setImageUrl("https://img14.360buyimg.com/n0/jfs/t23386/9/1066712099/277967/615ccafb/5b4f0e3aN262237fc.jpg");
        //wareModel01.setPrice(new BigDecimal(20488.00));
        //wareModel01.setStock(99);
        //wareModel01.setSales(10);
        //wareService.insertWare(wareModel01);
        //
        //WareModel wareModel02 = new WareModel();
        //wareModel02.setTitle("ipad Pro 2018");
        //wareModel02.setDescription("2018年新款ipad Pro");
        //wareModel02.setImageUrl("https://img14.360buyimg.com/n0/jfs/t1/2060/2/13752/217276/5bd8a77dEa1d786e7/d6acfae792866f77.jpg");
        //wareModel02.setPrice(new BigDecimal(6499.00));
        //wareModel02.setStock(80);
        //wareModel02.setSales(20);
        //wareService.insertWare(wareModel02);

        List<WareModel> wareModelList = wareService.listWares();
        for (WareModel wareModel : wareModelList) {
            System.out.println("--------------------商品信息--------------------" + wareModel);
        }
    }

}
