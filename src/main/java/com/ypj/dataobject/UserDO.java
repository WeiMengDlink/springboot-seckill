package com.ypj.dataobject;

/**
 * 此模型与数据库用户表对应
 */
public class UserDO {
    private Integer id;
    private String accountName;
    private String accountId;
    private Byte age;
    private String gender;
    private String phoneNumber;

    public UserDO() {
    }

    public UserDO(Integer id, String accountName, String accountId, Byte age, String gender, String phoneNumber) {
        this.id = id;
        this.accountName = accountName;
        this.accountId = accountId;
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
        sb.append(",\"accountId\":\"")
                .append(accountId).append('\"');
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
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
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
        this.gender = gender == null ? null : gender.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }
}