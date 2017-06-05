package com.talkweb.ei.outmanager.dao;

import com.talkweb.ei.outmanager.model.TOutJthy;
import com.talkweb.ei.outmanager.model.TOutJthyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOutJthyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_JTHY
     *
     * @mbg.generated Mon Jun 05 15:25:07 CST 2017
     */
    long countByExample(TOutJthyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_JTHY
     *
     * @mbg.generated Mon Jun 05 15:25:07 CST 2017
     */
    int deleteByExample(TOutJthyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_JTHY
     *
     * @mbg.generated Mon Jun 05 15:25:07 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_JTHY
     *
     * @mbg.generated Mon Jun 05 15:25:07 CST 2017
     */
    int insert(TOutJthy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_JTHY
     *
     * @mbg.generated Mon Jun 05 15:25:07 CST 2017
     */
    int insertSelective(TOutJthy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_JTHY
     *
     * @mbg.generated Mon Jun 05 15:25:07 CST 2017
     */
    List<TOutJthy> selectByExample(TOutJthyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_JTHY
     *
     * @mbg.generated Mon Jun 05 15:25:07 CST 2017
     */
    TOutJthy selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_JTHY
     *
     * @mbg.generated Mon Jun 05 15:25:07 CST 2017
     */
    int updateByExampleSelective(@Param("record") TOutJthy record, @Param("example") TOutJthyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_JTHY
     *
     * @mbg.generated Mon Jun 05 15:25:07 CST 2017
     */
    int updateByExample(@Param("record") TOutJthy record, @Param("example") TOutJthyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_JTHY
     *
     * @mbg.generated Mon Jun 05 15:25:07 CST 2017
     */
    int updateByPrimaryKeySelective(TOutJthy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_JTHY
     *
     * @mbg.generated Mon Jun 05 15:25:07 CST 2017
     */
    int updateByPrimaryKey(TOutJthy record);
}