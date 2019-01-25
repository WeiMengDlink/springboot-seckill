package com.ypj.error;

/** 定义
 * @ClassName ProcessException
 * @Author pain
 * @Date 2019/1/4 23:43
 * @Version 1.0
 **/
public class ProcessException extends Exception implements CommonError {
    private CommonError commonError;

    // 从传入的CommonError中获取错误信息构造出异常
    public ProcessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    // 从传入的CommonError和自定义错误信息构造异常
    public ProcessException(CommonError commonError, String errorMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrorMsg(errorMsg);
    }

    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.commonError.setErrorMsg(errorMsg);
        return this;
    }
}
