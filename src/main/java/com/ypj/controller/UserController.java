package com.ypj.controller;

import com.ypj.controller.viewobject.UserVO;
import com.ypj.error.ProcessErrorEnum;
import com.ypj.error.ProcessException;
import com.ypj.response.CommonResult;
import com.ypj.service.UserService;
import com.ypj.service.model.UserModel;
import com.ypj.util.BindingResultUtil;
import com.ypj.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @ClassName UserController
 * @Author pain
 * @Date 2019/1/4 18:28
 * @Version 1.0
 **/
@RestController
@RequestMapping("/seckill")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")    // 跨域请求
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest httpServletRequest;

    /**
     * 模拟生成6位数字验证码，
     * 在ValidationConfig中配置MethodValidationPostProcessor
     * 在Controller上添加@Validated注解
     * 在String phoneNumber前加上相应注解校验手机号
     *
     * @param phoneNumber
     * @return
     */
    @PostMapping("/getVerificationCode")
    public CommonResult getVerificationCode(@NotBlank(message = "手机号不能为空") @Pattern(regexp
            = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]" +
            "{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))"
            , message = "请输入正确的11位手机号") String phoneNumber) {
        // 使用Random类生成随机数
        Random random = new Random();
        int code = random.nextInt(899999);
        // 随机数+1000使其变成6位数字
        code = code + 100000;
        String codeStr = String.valueOf(code);
        // 获取HttpSession
        HttpSession httpSession = httpServletRequest.getSession();
        // 将手机号和验证码作为K、V存入Session，在输入验证码后验证改手机号和验证码是否匹配
        httpSession.setAttribute(phoneNumber, codeStr);

        // 在控制台输出验证码，模拟短信收到验证码
        System.out.println("收到的验证码：" + codeStr);

        return CommonResult.create(codeStr, "success");
    }

    /**
     * 用户注册
     * 这里从Session中获取的验证码为空，原因是ajax的跨域请求,在Controller上添加注解@CrossOrigin(allowCredentials = "true"
     * , allowedHeaders = "*"),在ajax请求中添加xhrFields:{withCredentials:true}
     * 使用@Valid UserModel userModel校验UserModel，需要在UserModel类的属性上添加相应注解，BindingResult保存的是校验后的信息
     * ，如果校验出错，获取错误信息，并抛出自定义异常ProcessException，其中包含错误信息
     *
     * @param userModel        提交的用户信息
     * @param verificationCode 提交的验证码
     * @return
     * @throws ProcessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping("/register")
    public CommonResult register(@Valid UserModel userModel, BindingResult bindingResult, String verificationCode) throws ProcessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        // 检查校验是否有错误，如果有错误，抛出ProcessException异常并自定义异常信息
        if (bindingResult.hasErrors()) {
            // 从BindingResult中获取错误信息
            String errorMsg = BindingResultUtil.getErrorFromBingdingResult(bindingResult);
            // 抛出异常
            throw new ProcessException(ProcessErrorEnum.INVALID_PARAMETER, errorMsg);
        }
        // 从当前Session中获取与手机号绑定的验证码与用户输入的验证码对比，如果不一致则抛出异常信息
        String code = (String) this.httpServletRequest.getSession().getAttribute(userModel.getPhoneNumber());
        if (!StringUtils.equals(verificationCode, code)) {
            throw new ProcessException(ProcessErrorEnum.INVALID_PARAMETER, "短信验证码错误");
        }
        // 设置用户密码为加密后的密码
        userModel.setPassword(UserUtil.encryptPassword(userModel.getPassword()));
        // 调用UserService的register()方法添加用户
        userService.register(userModel);
        return CommonResult.create(null, "success");
    }

    /**
     * 用户登录
     *
     * @param phoneNumber
     * @param password
     * @return
     * @throws ProcessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping("/login")
    public CommonResult login(String phoneNumber, String password) throws ProcessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        // 校验手机号和密码是否为空
        if (StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(password)) {
            throw new ProcessException(ProcessErrorEnum.INVALID_PARAMETER, "请输入手机号或密码");
        }
        // 调用UserService的login()方法，注意：传入的密码是加密后的密码
        UserModel userModel = userService.login(phoneNumber, UserUtil.encryptPassword(password));
        // 在Session中写入用户登录状态和用户信息
        this.httpServletRequest.getSession().setAttribute("LOGIN_STATUS", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonResult.create(null, "success");
    }

    /**
     * 根据用户id返回用户信息
     *
     * @param id
     * @return
     * @throws ProcessException
     */
    @GetMapping("/user/{id}")
    public CommonResult getUserById(@PathVariable("id") Integer id) throws ProcessException {
        UserModel userModel = userService.getUserById(id);
        if (null == userModel) {
            throw new ProcessException(ProcessErrorEnum.USER_NOT_EXIST);
        }
        UserVO userVO = convertUserVOFromUserModel(userModel);
        return CommonResult.create(userVO, "success");
    }

    /**
     * 把UserModel转换为UserVO交由视图层显示
     *
     * @param userModel
     * @return
     */
    private UserVO convertUserVOFromUserModel(UserModel userModel) {
        if (null == userModel) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

}
