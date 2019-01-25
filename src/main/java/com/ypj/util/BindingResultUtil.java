package com.ypj.util;

import com.ypj.error.ProcessErrorEnum;
import com.ypj.error.ProcessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @ClassName BindingResultUtil
 * @Author pain
 * @Date 2019/1/10 14:48
 * @Version 1.0
 **/
public class BindingResultUtil {

    /**
     * 从BindingResult中获取错误信息
     *
     * @param bindingResult
     * @return
     */
    public static String getErrorFromBingdingResult(BindingResult bindingResult) {
        if (null == bindingResult) {
            return null;
        }
        // 使用StringBuilder构建错误信息
        StringBuilder stringBuilder = new StringBuilder();
        // 获取所有错误信息
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        // 遍历错误信息分别获取错误字段和错误信息
        for (FieldError fieldError : fieldErrors) {
            System.out.println("错误字段：" + fieldError.getField());
            System.out.println("错误信息：" + fieldError.getDefaultMessage());
            stringBuilder.append("错误字段:").append(fieldError.getField()).append(",错误信息:").append(fieldError.getDefaultMessage()).append(";");
        }
        return stringBuilder.toString();
    }

}
