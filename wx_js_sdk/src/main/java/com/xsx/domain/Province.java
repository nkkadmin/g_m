package com.xsx.domain;

import java.io.Serializable;

public class Province implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4616161144142403914L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column province.id
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column province.prov_id
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    private Integer provId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column province.prov_name
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    private String provName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column province.prov_type
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    private String provType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column province.prov_state
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    private String provState;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column province.id
     *
     * @return the value of province.id
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column province.id
     *
     * @param id the value for province.id
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column province.prov_id
     *
     * @return the value of province.prov_id
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    public Integer getProvId() {
        return provId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column province.prov_id
     *
     * @param provId the value for province.prov_id
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    public void setProvId(Integer provId) {
        this.provId = provId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column province.prov_name
     *
     * @return the value of province.prov_name
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    public String getProvName() {
        return provName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column province.prov_name
     *
     * @param provName the value for province.prov_name
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    public void setProvName(String provName) {
        this.provName = provName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column province.prov_type
     *
     * @return the value of province.prov_type
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    public String getProvType() {
        return provType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column province.prov_type
     *
     * @param provType the value for province.prov_type
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    public void setProvType(String provType) {
        this.provType = provType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column province.prov_state
     *
     * @return the value of province.prov_state
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    public String getProvState() {
        return provState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column province.prov_state
     *
     * @param provState the value for province.prov_state
     *
     * @mbggenerated Tue Oct 31 11:27:22 CST 2017
     */
    public void setProvState(String provState) {
        this.provState = provState;
    }
}