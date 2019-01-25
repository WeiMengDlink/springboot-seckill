package com.ypj.service.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

/**
 * 用于业务处理的用户模型
 *
 * @ClassName UserModel
 * @Author pain
 * @Date 2019/1/4 19:31
 * @Version 1.0
 **/
public class UserModel {
    // id
    private Integer id;

    // 用户名
    @NotBlank(message = "用户名不能为空")
    private String accountName;

    // 用户id
    private String accountId;

    // 用户年龄
    @NotNull(message = "年龄不能为空")
    @Range(min = 0, max = 120, message = "年龄必须在0~120岁之间")
    private Byte age;

    // 用户性别
    @NotNull(message = "性别不能为空")
    private String gender;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}" +
            "\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))", message = "请输入正确的11位手机号")

    // 用户手机号
    private String phoneNumber;
    @NotBlank(message = "密码不能为空")

    // 用户密码
    private String password;

    public UserModel() {
    }

    public UserModel(Integer id, String accountName, String accountId, Byte age, String gender, String phoneNumber, String password) {
        this.id = id;
        this.accountName = accountName;
        this.accountId = accountId;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"accountName\":\"")
                .append(accountName).append('\"');
        sb.append(",\"accountId\":\"")
                .append(accountId).append('\"');
        sb.append(",\"age\":")
                .append(age);
        sb.append(",\"gender\":\"")
                .append(gender).append('\"');
        sb.append(",\"phoneNumber\":\"")
                .append(phoneNumber).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
