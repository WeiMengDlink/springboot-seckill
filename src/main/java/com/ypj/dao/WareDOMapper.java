package com.ypj.dao;

import com.ypj.dataobject.WareDO;

import java.util.List;

public interface WareDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WareDO record);

    int insertSelective(WareDO record);

    WareDO selectByPrimaryKey(Integer id);

    List<WareDO> listWareModels();

    int updateByPrimaryKeySelective(WareDO record);

    int updateByPrimaryKey(WareDO record);
}