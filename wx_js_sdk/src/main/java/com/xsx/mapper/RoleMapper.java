package com.xsx.mapper;

import org.apache.ibatis.annotations.Param;

import com.xsx.domain.Role;

public interface RoleMapper {
 
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    int countAll();
    
    Role selectByDescript(@Param("descript") String descript);
}