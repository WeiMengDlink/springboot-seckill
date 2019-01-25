package com.ypj.service.impl;

import com.ypj.dao.PasswordDOMapper;
import com.ypj.dao.UserDOMapper;
import com.ypj.dataobject.PasswordDO;
import com.ypj.dataobject.UserDO;
import com.ypj.error.ProcessErrorEnum;
import com.ypj.error.ProcessException;
import com.ypj.service.UserService;
import com.ypj.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserServiceImpl
 * @Author pain
 * @Date 2019/1/4 19:22
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDOMapper userDOMapper;

    @Autowired
    PasswordDOMapper passwordDOMapper;

    /**
     * 通过id获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public UserModel getUserById(Integer id) {
        // 根据用户id获取该用户
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (null == userDO) {
            return null;
        }
        // 根据用户id获取该用户的密码
        PasswordDO passwordDO = passwordDOMapper.selectByUserId(userDO.getId());
        if (null == passwordDO) {
            return null;
        }
        // 调用convertUserModelFromUserDOAndPasswordDO()方法将UserDO和PasswordDO组装成UserModel
        return convertUserModelFromUserDOAndPasswordDO(userDO, passwordDO);
    }

    /**
     * 用户注册
     *
     * @param userModel
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserModel userModel) throws ProcessException {
        // 判断字段是否为空
        //if (StringUtils.isEmpty(userModel.getAccountName()) || userModel.getAge() == null
        //        || StringUtils.isEmpty(userModel.getGender()) || StringUtils.isEmpty(userModel.getPhoneNumber())
        //        || StringUtils.isEmpty(userModel.getPassword())) {
        //    throw new ProcessException(ProcessErrorEnum.INVALID_PARAMETER, "用户信息不能为空");
        //}
        // 调用convertUserDOFromUserModel()方法将UserModel转化为UserDO
        UserDO userDO = convertUserDOFromUserModel(userModel);
        // 插入用户，在user_info表中已经为phoneNumber字段设置唯一索引，在这里捕获DuplicateKeyException异常方便前端显示错误信息
        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException exception) {
            throw new ProcessException(ProcessErrorEnum.INVALID_PARAMETER, "手机号已注册");
        }
        //设置用户id，因为在password_info表中用户id是和用户密码绑定的
        userModel.setId(userDO.getId());
        // 调用convertPasswordDOFromUserModel()方法将UserModel转换为PasswordDO
        PasswordDO passwordDO = convertPasswordDOFromUserModel(userModel);
        // 插入密码
        passwordDOMapper.insertSelective(passwordDO);
    }

    /**
     * 用户登录
     *
     * @param phoneNumber
     * @param password
     * @return
     * @throws ProcessException
     */
    @Override
    public UserModel login(String phoneNumber, String password) throws ProcessException {
        // 根据用户手机号查找用户信息
        UserDO userDO = userDOMapper.selectByPhoneNumber(phoneNumber);
        if (null == userDO) {
            throw new ProcessException(ProcessErrorEnum.LOGIN_FAIL);
        }
        // 根据用户id从password_info表获取该用户的加密密码并和UserModel中加密密码的比较
        PasswordDO passwordDO = passwordDOMapper.selectByUserId(userDO.getId());
        if (!StringUtils.equals(passwordDO.getPassword(), password)) {
            throw new ProcessException(ProcessErrorEnum.LOGIN_FAIL, "手机号或密码错误");
        }

        // 获取UserModel
        UserModel userModel = convertUserModelFromUserDOAndPasswordDO(userDO, passwordDO);
        return userModel;
    }

    /**
     * 将UserModel转换为UserDO供数据库层使用
     *
     * @param userModel
     * @return
     */
    private UserDO convertUserDOFromUserModel(UserModel userModel) {
        if (null == userModel) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }

    /**
     * 将UserModel转换为PasswordDO供数据库层使用
     *
     * @param userModel
     * @return
     */
    private PasswordDO convertPasswordDOFromUserModel(UserModel userModel) {
        if (null == userModel) {
            return null;
        }
        PasswordDO passwordDO = new PasswordDO();
        // 设置用户id，因为用户id和密码是一一绑定的
        passwordDO.setUserId(userModel.getId());
        passwordDO.setPassword(userModel.getPassword());
        return passwordDO;
    }

    /**
     * 将UserDO、PasswordDO组装成UserModel
     *
     * @param userDO
     * @param passwordDO
     * @return
     */
    private UserModel convertUserModelFromUserDOAndPasswordDO(UserDO userDO, PasswordDO passwordDO) {
        if (null == userDO) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);

        if (null == passwordDO) {
            return null;
        }
        userModel.setPassword(passwordDO.getPassword());
        return userModel;
    }
}
