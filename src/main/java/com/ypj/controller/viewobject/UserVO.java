package com.ypj.controller.viewobject;

/**
 * @ClassName UserVO
 * @Author pain
 * @Date 2019/1/4 20:18
 * @Version 1.0
 **/
public class UserVO {
    private Integer id;
    private String accountName;
    private Byte age;
    private String gender;
    private String phoneNumber;

    public UserVO() {
    }

    public UserVO(Integer id, String accountName, Byte age, String gender, String phoneNumber) {
        this.id = id;
        this.accountName = accountName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"accountName\":\"")
                .append(accountName).append('\"');
        sb.append(",\"age\":")
                .append(age);
        sb.append(",\"gender\":\"")
                .append(gender).append('\"');
        sb.append(",\"phoneNumber\":\"")
                .append(phoneNumber).append('\"');
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
}
