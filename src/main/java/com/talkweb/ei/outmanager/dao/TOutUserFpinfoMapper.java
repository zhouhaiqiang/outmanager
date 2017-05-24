package com.talkweb.ei.outmanager.dao;

import com.talkweb.ei.outmanager.model.TOutUserFpinfo;
import com.talkweb.ei.outmanager.model.TOutUserFpinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOutUserFpinfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_FPINFO
     *
     * @mbg.generated Wed May 24 16:23:08 CST 2017
     */
    long countByExample(TOutUserFpinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_FPINFO
     *
     * @mbg.generated Wed May 24 16:23:08 CST 2017
     */
    int deleteByExample(TOutUserFpinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_FPINFO
     *
     * @mbg.generated Wed May 24 16:23:08 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_FPINFO
     *
     * @mbg.generated Wed May 24 16:23:08 CST 2017
     */
    int insert(TOutUserFpinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_FPINFO
     *
     * @mbg.generated Wed May 24 16:23:08 CST 2017
     */
    int insertSelective(TOutUserFpinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_FPINFO
     *
     * @mbg.generated Wed May 24 16:23:08 CST 2017
     */
    List<TOutUserFpinfo> selectByExample(TOutUserFpinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_FPINFO
     *
     * @mbg.generated Wed May 24 16:23:08 CST 2017
     */
    TOutUserFpinfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_FPINFO
     *
     * @mbg.generated Wed May 24 16:23:08 CST 2017
     */
    int updateByExampleSelective(@Param("record") TOutUserFpinfo record, @Param("example") TOutUserFpinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_FPINFO
     *
     * @mbg.generated Wed May 24 16:23:08 CST 2017
     */
    int updateByExample(@Param("record") TOutUserFpinfo record, @Param("example") TOutUserFpinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_FPINFO
     *
     * @mbg.generated Wed May 24 16:23:08 CST 2017
     */
    int updateByPrimaryKeySelective(TOutUserFpinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_FPINFO
     *
     * @mbg.generated Wed May 24 16:23:08 CST 2017
     */
    int updateByPrimaryKey(TOutUserFpinfo record);
}