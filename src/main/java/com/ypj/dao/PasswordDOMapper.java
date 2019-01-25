package com.ypj.dao;

import com.ypj.dataobject.PasswordDO;

public interface PasswordDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PasswordDO record);

    int insertSelective(PasswordDO record);

    PasswordDO selectByPrimaryKey(Integer id);

    PasswordDO selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(PasswordDO record);

    int updateByPrimaryKey(PasswordDO record);
}