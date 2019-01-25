package com.ypj.controller;

import com.ypj.error.ProcessErrorEnum;
import com.ypj.error.ProcessException;
import com.ypj.response.CommonResult;
import com.ypj.service.OrderService;
import com.ypj.service.model.OrderModel;
import com.ypj.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName OrderController
 * @Author pain
 * @Date 2019/1/11 19:53
 * @Version 1.0
 **/
@RestController
@RequestMapping("/seckill")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")    // 跨域请求
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @PostMapping("order")
    public CommonResult addOrder(@RequestParam("wareId") Integer wareId, @RequestParam(value = "promotionId"
            , required = false) Integer promotionId, @RequestParam("amount") Integer amount) throws ProcessException {
        // 从当前Session中获取登录状态和用户信息
        Boolean loginStatus = (Boolean) httpServletRequest.getSession().getAttribute("LOGIN_STATUS");
        UserModel loginUser = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        // 如果用户登录状态不正常抛出异常
        if (null == loginStatus || !loginStatus || null == loginUser) {
            throw new ProcessException(ProcessErrorEnum.USER_NOT_EXIST, "请先登录");
        }

        OrderModel orderModel = orderService.insertOrder(loginUser.getId(), wareId, promotionId, amount);

        return CommonResult.create(null, "success");
    }

}
