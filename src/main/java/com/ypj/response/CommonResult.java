package com.ypj.response;

/**
 * 定义统一返回结果
 *
 * @ClassName CommonResult
 * @Author pain
 * @Date 2019/1/4 22:48
 * @Version 1.0
 **/
public class CommonResult {
    // 处理状态：success or fail
    private String status;
    // 若处理成功，保存上一层请求的的数据，若处理失败，保存错误信息
    private Object data;

    public CommonResult() {
    }

    /**
     * 返回状态码和要请求的数据或错误信息
     *
     * @param data
     * @param status
     * @return
     */
    public static CommonResult create(Object data, String status) {
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(status);
        commonResult.setData(data);
        return commonResult;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"status\":\"")
                .append(status).append('\"');
        sb.append(",\"data\":")
                .append(data);
        sb.append('}');
        return sb.toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
