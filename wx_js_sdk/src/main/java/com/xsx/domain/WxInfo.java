package com.xsx.domain;

import java.io.Serializable;

public class WxInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6003657525699019576L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wxinfo.id
     *
     * @mbggenerated Thu Nov 09 10:38:35 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wxinfo.accesstoken
     *
     * @mbggenerated Thu Nov 09 10:38:35 CST 2017
     */
    private String accesstoken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wxinfo.jsapiTicket
     *
     * @mbggenerated Thu Nov 09 10:38:35 CST 2017
     */
    private String jsapiticket;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wxinfo.id
     *
     * @return the value of wxinfo.id
     *
     * @mbggenerated Thu Nov 09 10:38:35 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wxinfo.id
     *
     * @param id the value for wxinfo.id
     *
     * @mbggenerated Thu Nov 09 10:38:35 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wxinfo.accesstoken
     *
     * @return the value of wxinfo.accesstoken
     *
     * @mbggenerated Thu Nov 09 10:38:35 CST 2017
     */
    public String getAccesstoken() {
        return accesstoken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wxinfo.accesstoken
     *
     * @param accesstoken the value for wxinfo.accesstoken
     *
     * @mbggenerated Thu Nov 09 10:38:35 CST 2017
     */
    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wxinfo.jsapiTicket
     *
     * @return the value of wxinfo.jsapiTicket
     *
     * @mbggenerated Thu Nov 09 10:38:35 CST 2017
     */
    public String getJsapiticket() {
        return jsapiticket;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wxinfo.jsapiTicket
     *
     * @param jsapiticket the value for wxinfo.jsapiTicket
     *
     * @mbggenerated Thu Nov 09 10:38:35 CST 2017
     */
    public void setJsapiticket(String jsapiticket) {
        this.jsapiticket = jsapiticket;
    }
}