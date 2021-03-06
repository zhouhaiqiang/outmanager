package com.talkweb.ei.outmanager.dao;

import com.talkweb.ei.outmanager.model.OutCompany;
import com.talkweb.ei.outmanager.model.OutCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OutCompanyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_COMPANY
     *
     * @mbg.generated Tue May 02 13:19:15 CST 2017
     */
    long countByExample(OutCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_COMPANY
     *
     * @mbg.generated Tue May 02 13:19:15 CST 2017
     */
    int deleteByExample(OutCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_COMPANY
     *
     * @mbg.generated Tue May 02 13:19:15 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_COMPANY
     *
     * @mbg.generated Tue May 02 13:19:15 CST 2017
     */
    int insert(OutCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_COMPANY
     *
     * @mbg.generated Tue May 02 13:19:15 CST 2017
     */
    int insertSelective(OutCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_COMPANY
     *
     * @mbg.generated Tue May 02 13:19:15 CST 2017
     */
    List<OutCompany> selectByExample(OutCompanyExample example);
    
    
    //��ҳ��ѯ
    List<OutCompany> selectPageByExample(OutCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_COMPANY
     *
     * @mbg.generated Tue May 02 13:19:15 CST 2017
     */
    OutCompany selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_COMPANY
     *
     * @mbg.generated Tue May 02 13:19:15 CST 2017
     */
    int updateByExampleSelective(@Param("record") OutCompany record, @Param("example") OutCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_COMPANY
     *
     * @mbg.generated Tue May 02 13:19:15 CST 2017
     */
    int updateByExample(@Param("record") OutCompany record, @Param("example") OutCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_COMPANY
     *
     * @mbg.generated Tue May 02 13:19:15 CST 2017
     */
    int updateByPrimaryKeySelective(OutCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_COMPANY
     *
     * @mbg.generated Tue May 02 13:19:15 CST 2017
     */
    int updateByPrimaryKey(OutCompany record);
}