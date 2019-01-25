package com.ypj.service;

import com.ypj.error.ProcessException;
import com.ypj.service.model.UserModel;

/**
 * 定义用户操作
 */
public interface UserService {

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    UserModel getUserById(Integer id);

    /**
     * 用户注册
     *
     * @param userModel
     * @throws ProcessException
     */
    void register(UserModel userModel) throws ProcessException;

    /**
     * 用户登录
     *
     * @param phoneNumber
     * @param password
     * @return
     * @throws ProcessException
     */
    UserModel login(String phoneNumber, String password) throws ProcessException;

}
