package com.xsx.mapper;

import com.xsx.domain.EmployeeExtension;

public interface EmployeeExtensionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employeeExtension
     *
     * @mbggenerated Sat Jan 13 18:19:27 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employeeExtension
     *
     * @mbggenerated Sat Jan 13 18:19:27 CST 2018
     */
    int insert(EmployeeExtension record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employeeExtension
     *
     * @mbggenerated Sat Jan 13 18:19:27 CST 2018
     */
    int insertSelective(EmployeeExtension record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employeeExtension
     *
     * @mbggenerated Sat Jan 13 18:19:27 CST 2018
     */
    EmployeeExtension selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employeeExtension
     *
     * @mbggenerated Sat Jan 13 18:19:27 CST 2018
     */
    int updateByPrimaryKeySelective(EmployeeExtension record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employeeExtension
     *
     * @mbggenerated Sat Jan 13 18:19:27 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(EmployeeExtension record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employeeExtension
     *
     * @mbggenerated Sat Jan 13 18:19:27 CST 2018
     */
    int updateByPrimaryKey(EmployeeExtension record);
    
	EmployeeExtension selectByEmployeeId(Integer employeeId);
}