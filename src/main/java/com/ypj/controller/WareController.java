package com.ypj.controller;

import com.ypj.controller.viewobject.WareVO;
import com.ypj.error.ProcessErrorEnum;
import com.ypj.error.ProcessException;
import com.ypj.response.CommonResult;
import com.ypj.service.WareService;
import com.ypj.service.model.UserModel;
import com.ypj.service.model.WareModel;
import com.ypj.util.BindingResultUtil;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName WareController
 * @Author pain
 * @Date 2019/1/10 13:53
 * @Version 1.0
 **/
@RestController
@RequestMapping("/seckill")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")    // 跨域请求
public class WareController {

    @Autowired
    WareService wareService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @PostMapping("/ware")
    public CommonResult addWare(@Valid WareModel wareModel, BindingResult bindingResult) throws ProcessException {
        // 检查校验是否有错误，如果有错误，抛出ProcessException异常并自定义异常信息
        if (bindingResult.hasErrors()) {
            // 从BindingResult中获取错误信息
            String errorMsg = BindingResultUtil.getErrorFromBingdingResult(bindingResult);
            // 抛出异常
            throw new ProcessException(ProcessErrorEnum.INVALID_PARAMETER, errorMsg);
        }
        // 调用WareService的insertWare()方法添加商品
        wareService.insertWare(wareModel);
        return CommonResult.create(null, "success");
    }

    /**
     * 返回所有商品信息
     *
     * @return
     */
    @GetMapping("/ware")
    public CommonResult listAllWares() {
        List<WareModel> wareModelList = wareService.listWares();
        return CommonResult.create(wareModelList, "success");
    }

    /**
     * 根据商品id获取商品详情
     *
     * @param id
     * @return
     * @throws ProcessException
     */
    @GetMapping("/ware/{id}")
    public CommonResult getWareById(@PathVariable("id") Integer id) throws ProcessException {
        // 从当前Session获取用户信息，如果用户不存在提示用户登录
        Boolean loginStatus = (Boolean) httpServletRequest.getSession().getAttribute("LOGIN_STATUS");
        UserModel loginUser = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        // 如果用户登录状态不正常抛出异常
        if (null == loginStatus || !loginStatus || null == loginUser) {
            throw new ProcessException(ProcessErrorEnum.USER_NOT_EXIST, "请先登录");
        }
        // 根据商品id获取商品详情
        WareModel wareModel = wareService.getWareById(id);
        if (null == wareModel) {
            throw new ProcessException(ProcessErrorEnum.WARE_NOT_EXIST);
        }

        System.out.println("----------------------该商品名称：" + wareModel.getTitle() + ",促销活动：" + wareModel.getPromotionModel());

        WareVO wareVO = this.convertWareVOFromWareModel(wareModel);
        return CommonResult.create(wareVO, "success");
    }

    /**
     * 将WareModel转换为WareVO交由视图层显示
     *
     * @param wareModel
     * @return
     */
    private WareVO convertWareVOFromWareModel(WareModel wareModel) {
        if (null == wareModel) {
            return null;
        }
        WareVO wareVO = new WareVO();
        BeanUtils.copyProperties(wareModel, wareVO);

        // 判断该商品是否有促销活动
        if (null != wareModel.getPromotionModel()) {
            // 如果促销活动还在有效状态
            wareVO.setPromotionId(wareModel.getPromotionModel().getId());
            wareVO.setPromotionStatus(wareModel.getPromotionModel().getPromotionStatus());
            wareVO.setPromotionPrice(wareModel.getPromotionModel().getWarePrice());
            wareVO.setStartDate(wareModel.getPromotionModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        } else {
            // 活动无效设置状态为-1
            wareVO.setPromotionStatus(-1);
        }
        return wareVO;
    }

}
