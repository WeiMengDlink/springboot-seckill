package com.ypj.error;

/**
 * 定义通用错误接口
 */
public interface CommonError {
    int getErrorCode();

    String getErrorMsg();

    CommonError setErrorMsg(String errorMsg);
}
