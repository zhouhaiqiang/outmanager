package com.talkweb.ei.outmanager.dao;

import com.talkweb.ei.outmanager.model.TOutUserJyinfo;
import com.talkweb.ei.outmanager.model.TOutUserJyinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOutUserJyinfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JYINFO
     *
     * @mbg.generated Wed May 31 09:45:25 CST 2017
     */
    long countByExample(TOutUserJyinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JYINFO
     *
     * @mbg.generated Wed May 31 09:45:25 CST 2017
     */
    int deleteByExample(TOutUserJyinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JYINFO
     *
     * @mbg.generated Wed May 31 09:45:25 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JYINFO
     *
     * @mbg.generated Wed May 31 09:45:25 CST 2017
     */
    int insert(TOutUserJyinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JYINFO
     *
     * @mbg.generated Wed May 31 09:45:25 CST 2017
     */
    int insertSelective(TOutUserJyinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JYINFO
     *
     * @mbg.generated Wed May 31 09:45:25 CST 2017
     */
    List<TOutUserJyinfo> selectByExample(TOutUserJyinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JYINFO
     *
     * @mbg.generated Wed May 31 09:45:25 CST 2017
     */
    TOutUserJyinfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JYINFO
     *
     * @mbg.generated Wed May 31 09:45:25 CST 2017
     */
    int updateByExampleSelective(@Param("record") TOutUserJyinfo record, @Param("example") TOutUserJyinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JYINFO
     *
     * @mbg.generated Wed May 31 09:45:25 CST 2017
     */
    int updateByExample(@Param("record") TOutUserJyinfo record, @Param("example") TOutUserJyinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JYINFO
     *
     * @mbg.generated Wed May 31 09:45:25 CST 2017
     */
    int updateByPrimaryKeySelective(TOutUserJyinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JYINFO
     *
     * @mbg.generated Wed May 31 09:45:25 CST 2017
     */
    int updateByPrimaryKey(TOutUserJyinfo record);
}