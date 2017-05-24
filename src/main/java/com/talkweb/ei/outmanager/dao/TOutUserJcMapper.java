package com.talkweb.ei.outmanager.dao;

import com.talkweb.ei.outmanager.model.TOutUserJc;
import com.talkweb.ei.outmanager.model.TOutUserJcExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOutUserJcMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    long countByExample(TOutUserJcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    int deleteByExample(TOutUserJcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    int insert(TOutUserJc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    int insertSelective(TOutUserJc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    List<TOutUserJc> selectByExample(TOutUserJcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    TOutUserJc selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    int updateByExampleSelective(@Param("record") TOutUserJc record, @Param("example") TOutUserJcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    int updateByExample(@Param("record") TOutUserJc record, @Param("example") TOutUserJcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    int updateByPrimaryKeySelective(TOutUserJc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    int updateByPrimaryKey(TOutUserJc record);
}