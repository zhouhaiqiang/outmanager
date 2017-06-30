package com.talkweb.ei.outmanager.dao;

import com.talkweb.ei.outmanager.model.TOutDuty;
import com.talkweb.ei.outmanager.model.TOutDutyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOutDutyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_DUTY
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    long countByExample(TOutDutyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_DUTY
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    int deleteByExample(TOutDutyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_DUTY
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_DUTY
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    int insert(TOutDuty record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_DUTY
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    int insertSelective(TOutDuty record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_DUTY
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    List<TOutDuty> selectByExample(TOutDutyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_DUTY
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    TOutDuty selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_DUTY
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    int updateByExampleSelective(@Param("record") TOutDuty record, @Param("example") TOutDutyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_DUTY
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    int updateByExample(@Param("record") TOutDuty record, @Param("example") TOutDutyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_DUTY
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    int updateByPrimaryKeySelective(TOutDuty record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_DUTY
     *
     * @mbg.generated Fri Jun 30 15:23:01 CST 2017
     */
    int updateByPrimaryKey(TOutDuty record);
}