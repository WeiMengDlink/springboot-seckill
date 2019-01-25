package com.ypj.error;

/**
 * 定义枚举统一各种错误信息，并实现CommonError
 */
public enum ProcessErrorEnum implements CommonError {
    USER_NOT_EXIST(1001, "用户不存在"),
    WARE_NOT_EXIST(1002, "商品不存在"),
    STOCK_NOT_ENOUGH(1003, "商品库存不足"),
    LOGIN_FAIL(1003, "登录失败"),
    LOGIN_SUCCESS(1004, "登录成功"),
    INVALID_PARAMETER(1005, "无效的参数"),
    UNKNOWN_ERROR(1006, "未知错误");

    private int errorCode;
    private String errorMsg;

    private ProcessErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
