package com.talkweb.ei.outmanager.model;

public class TOutDuty extends ImportObj {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JAVAOAS.T_OUT_DUTY.ID
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JAVAOAS.T_OUT_DUTY.NAME
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JAVAOAS.T_OUT_DUTY.REMARK
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JAVAOAS.T_OUT_DUTY.BAK
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    private String bak;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JAVAOAS.T_OUT_DUTY.ID
     *
     * @return the value of JAVAOAS.T_OUT_DUTY.ID
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JAVAOAS.T_OUT_DUTY.ID
     *
     * @param id the value for JAVAOAS.T_OUT_DUTY.ID
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JAVAOAS.T_OUT_DUTY.NAME
     *
     * @return the value of JAVAOAS.T_OUT_DUTY.NAME
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JAVAOAS.T_OUT_DUTY.NAME
     *
     * @param name the value for JAVAOAS.T_OUT_DUTY.NAME
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JAVAOAS.T_OUT_DUTY.REMARK
     *
     * @return the value of JAVAOAS.T_OUT_DUTY.REMARK
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JAVAOAS.T_OUT_DUTY.REMARK
     *
     * @param remark the value for JAVAOAS.T_OUT_DUTY.REMARK
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JAVAOAS.T_OUT_DUTY.BAK
     *
     * @return the value of JAVAOAS.T_OUT_DUTY.BAK
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    public String getBak() {
        return bak;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JAVAOAS.T_OUT_DUTY.BAK
     *
     * @param bak the value for JAVAOAS.T_OUT_DUTY.BAK
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    public void setBak(String bak) {
        this.bak = bak == null ? null : bak.trim();
    }
}