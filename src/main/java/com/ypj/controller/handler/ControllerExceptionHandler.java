package com.ypj.controller.handler;

import com.ypj.error.ProcessErrorEnum;
import com.ypj.error.ProcessException;
import com.ypj.response.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Controller增强器：处理Controller层处理业务抛出的异常
 *
 * @ClassName ControllerExceptionHandler
 * @Author pain
 * @Date 2019/1/5 15:17
 * @Version 1.0
 **/
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 处理抛出的Exception
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(Exception exception) {
        // 将异常信息保存在map中
        Map<String, Object> map = new HashMap<>();
        map.put("errorCode", ProcessErrorEnum.UNKNOWN_ERROR.getErrorCode());
        map.put("errorMsg", exception.getMessage());
        return CommonResult.create(map, "fail");
    }

    /**
     * 处理抛出的ProcessException
     *
     * @param processException
     * @return
     */
    @ExceptionHandler(ProcessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleProcessException(ProcessException processException) {
        // 将异常中的错误码和错误信息保存在Map中
        Map<String, Object> map = new HashMap<>();
        map.put("errorCode", processException.getErrorCode());
        map.put("errorMsg", processException.getErrorMsg());

        System.out.println("errorCode：" + processException.getErrorCode());
        System.out.println("errorMsg：" + processException.getErrorMsg());

        return CommonResult.create(map, "fail");
    }

    /**
     * 处理抛出的ValidationException（校验数据过程中产生的异常）
     *
     * @param validationException
     * @return
     * @throws ProcessException
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleValidatonException(ValidationException validationException) {
        // 将异常中的错误码和错误信息保存在Map中
        Map<String, Object> map = new HashMap<>();
        // 使用StringBuilder构建错误信息
        StringBuilder stringBuilder = new StringBuilder();
        // 判断该异常是否为ConstraintViolationException
        if (validationException instanceof ConstraintViolationException) {
            // 将该异常转换为ConstraintViolationException
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) validationException;
            Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
            // 遍历constraintViolations获取错误信息
            for (ConstraintViolation constraintViolation : constraintViolations) {
                stringBuilder.append("错误信息:").append(constraintViolation.getMessage()).append(";");
            }
        }
        String errorMsg = stringBuilder.toString();
        map.put("errorCode", ProcessErrorEnum.INVALID_PARAMETER.getErrorCode());
        map.put("errorMsg", errorMsg);
        return CommonResult.create(map, "fail");
    }

}
