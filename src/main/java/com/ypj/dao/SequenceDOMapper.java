package com.ypj.dao;

import com.ypj.dataobject.SequenceDO;

public interface SequenceDOMapper {
    int deleteByPrimaryKey(String name);

    int insert(SequenceDO record);

    int insertSelective(SequenceDO record);

    SequenceDO selectByPrimaryKey(String name);

    SequenceDO selectByName(String name);

    int updateByPrimaryKeySelective(SequenceDO record);

    int updateByPrimaryKey(SequenceDO record);
}