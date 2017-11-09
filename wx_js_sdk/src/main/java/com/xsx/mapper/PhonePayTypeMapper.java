package com.xsx.mapper;

import com.xsx.domain.PhonePayType;

public interface PhonePayTypeMapper {

	int deleteByPrimaryKey(Integer id);

    int insert(PhonePayType record);

    int insertSelective(PhonePayType record);

    PhonePayType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhonePayType record);

    int updateByPrimaryKey(PhonePayType record);
    
    int countAll();
}