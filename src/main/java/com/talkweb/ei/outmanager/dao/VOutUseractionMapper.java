package com.talkweb.ei.outmanager.dao;

import com.talkweb.ei.outmanager.model.VOutUseraction;
import com.talkweb.ei.outmanager.model.VOutUseractionExample;
import com.talkweb.ei.outmanager.model.VOutUsergz;
import com.talkweb.ei.outmanager.model.VOutUsergzExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VOutUseractionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.V_OUT_USERACTION
     *
     * @mbg.generated Tue Jul 11 15:41:06 CST 2017
     */
    long countByExample(VOutUseractionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.V_OUT_USERACTION
     *
     * @mbg.generated Tue Jul 11 15:41:06 CST 2017
     */
    int deleteByExample(VOutUseractionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.V_OUT_USERACTION
     *
     * @mbg.generated Tue Jul 11 15:41:06 CST 2017
     */
    int insert(VOutUseraction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.V_OUT_USERACTION
     *
     * @mbg.generated Tue Jul 11 15:41:06 CST 2017
     */
    int insertSelective(VOutUseraction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.V_OUT_USERACTION
     *
     * @mbg.generated Tue Jul 11 15:41:06 CST 2017
     */
    List<VOutUseraction> selectByExample(VOutUseractionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.V_OUT_USERACTION
     *
     * @mbg.generated Tue Jul 11 15:41:06 CST 2017
     */
    int updateByExampleSelective(@Param("record") VOutUseraction record, @Param("example") VOutUseractionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.V_OUT_USERACTION
     *
     * @mbg.generated Tue Jul 11 15:41:06 CST 2017
     */
    int updateByExample(@Param("record") VOutUseraction record, @Param("example") VOutUseractionExample example);
    
    
    
    //��ҳ��ѯ
    List<VOutUseraction> selectPageByExample(VOutUseractionExample example);
    
}