package com.talkweb.ei.outmanager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TOutReportstExample extends ExampleObj {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    public TOutReportstExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRepdateIsNull() {
            addCriterion("REPDATE is null");
            return (Criteria) this;
        }

        public Criteria andRepdateIsNotNull() {
            addCriterion("REPDATE is not null");
            return (Criteria) this;
        }

        public Criteria andRepdateEqualTo(Date value) {
            addCriterionForJDBCDate("REPDATE =", value, "repdate");
            return (Criteria) this;
        }

        public Criteria andRepdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("REPDATE <>", value, "repdate");
            return (Criteria) this;
        }

        public Criteria andRepdateGreaterThan(Date value) {
            addCriterionForJDBCDate("REPDATE >", value, "repdate");
            return (Criteria) this;
        }

        public Criteria andRepdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("REPDATE >=", value, "repdate");
            return (Criteria) this;
        }

        public Criteria andRepdateLessThan(Date value) {
            addCriterionForJDBCDate("REPDATE <", value, "repdate");
            return (Criteria) this;
        }

        public Criteria andRepdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("REPDATE <=", value, "repdate");
            return (Criteria) this;
        }

        public Criteria andRepdateIn(List<Date> values) {
            addCriterionForJDBCDate("REPDATE in", values, "repdate");
            return (Criteria) this;
        }

        public Criteria andRepdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("REPDATE not in", values, "repdate");
            return (Criteria) this;
        }

        public Criteria andRepdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("REPDATE between", value1, value2, "repdate");
            return (Criteria) this;
        }

        public Criteria andRepdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("REPDATE not between", value1, value2, "repdate");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("UNIT is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("UNIT =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("UNIT <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("UNIT >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("UNIT >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("UNIT <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("UNIT <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("UNIT like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("UNIT not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("UNIT in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("UNIT not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("UNIT between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("UNIT not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andReptypeIsNull() {
            addCriterion("REPTYPE is null");
            return (Criteria) this;
        }

        public Criteria andReptypeIsNotNull() {
            addCriterion("REPTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andReptypeEqualTo(String value) {
            addCriterion("REPTYPE =", value, "reptype");
            return (Criteria) this;
        }

        public Criteria andReptypeNotEqualTo(String value) {
            addCriterion("REPTYPE <>", value, "reptype");
            return (Criteria) this;
        }

        public Criteria andReptypeGreaterThan(String value) {
            addCriterion("REPTYPE >", value, "reptype");
            return (Criteria) this;
        }

        public Criteria andReptypeGreaterThanOrEqualTo(String value) {
            addCriterion("REPTYPE >=", value, "reptype");
            return (Criteria) this;
        }

        public Criteria andReptypeLessThan(String value) {
            addCriterion("REPTYPE <", value, "reptype");
            return (Criteria) this;
        }

        public Criteria andReptypeLessThanOrEqualTo(String value) {
            addCriterion("REPTYPE <=", value, "reptype");
            return (Criteria) this;
        }

        public Criteria andReptypeLike(String value) {
            addCriterion("REPTYPE like", value, "reptype");
            return (Criteria) this;
        }

        public Criteria andReptypeNotLike(String value) {
            addCriterion("REPTYPE not like", value, "reptype");
            return (Criteria) this;
        }

        public Criteria andReptypeIn(List<String> values) {
            addCriterion("REPTYPE in", values, "reptype");
            return (Criteria) this;
        }

        public Criteria andReptypeNotIn(List<String> values) {
            addCriterion("REPTYPE not in", values, "reptype");
            return (Criteria) this;
        }

        public Criteria andReptypeBetween(String value1, String value2) {
            addCriterion("REPTYPE between", value1, value2, "reptype");
            return (Criteria) this;
        }

        public Criteria andReptypeNotBetween(String value1, String value2) {
            addCriterion("REPTYPE not between", value1, value2, "reptype");
            return (Criteria) this;
        }

        public Criteria andIntfIsNull() {
            addCriterion("INTF is null");
            return (Criteria) this;
        }

        public Criteria andIntfIsNotNull() {
            addCriterion("INTF is not null");
            return (Criteria) this;
        }

        public Criteria andIntfEqualTo(String value) {
            addCriterion("INTF =", value, "intf");
            return (Criteria) this;
        }

        public Criteria andIntfNotEqualTo(String value) {
            addCriterion("INTF <>", value, "intf");
            return (Criteria) this;
        }

        public Criteria andIntfGreaterThan(String value) {
            addCriterion("INTF >", value, "intf");
            return (Criteria) this;
        }

        public Criteria andIntfGreaterThanOrEqualTo(String value) {
            addCriterion("INTF >=", value, "intf");
            return (Criteria) this;
        }

        public Criteria andIntfLessThan(String value) {
            addCriterion("INTF <", value, "intf");
            return (Criteria) this;
        }

        public Criteria andIntfLessThanOrEqualTo(String value) {
            addCriterion("INTF <=", value, "intf");
            return (Criteria) this;
        }

        public Criteria andIntfLike(String value) {
            addCriterion("INTF like", value, "intf");
            return (Criteria) this;
        }

        public Criteria andIntfNotLike(String value) {
            addCriterion("INTF not like", value, "intf");
            return (Criteria) this;
        }

        public Criteria andIntfIn(List<String> values) {
            addCriterion("INTF in", values, "intf");
            return (Criteria) this;
        }

        public Criteria andIntfNotIn(List<String> values) {
            addCriterion("INTF not in", values, "intf");
            return (Criteria) this;
        }

        public Criteria andIntfBetween(String value1, String value2) {
            addCriterion("INTF between", value1, value2, "intf");
            return (Criteria) this;
        }

        public Criteria andIntfNotBetween(String value1, String value2) {
            addCriterion("INTF not between", value1, value2, "intf");
            return (Criteria) this;
        }

        public Criteria andIsrecreateIsNull() {
            addCriterion("ISRECREATE is null");
            return (Criteria) this;
        }

        public Criteria andIsrecreateIsNotNull() {
            addCriterion("ISRECREATE is not null");
            return (Criteria) this;
        }

        public Criteria andIsrecreateEqualTo(String value) {
            addCriterion("ISRECREATE =", value, "isrecreate");
            return (Criteria) this;
        }

        public Criteria andIsrecreateNotEqualTo(String value) {
            addCriterion("ISRECREATE <>", value, "isrecreate");
            return (Criteria) this;
        }

        public Criteria andIsrecreateGreaterThan(String value) {
            addCriterion("ISRECREATE >", value, "isrecreate");
            return (Criteria) this;
        }

        public Criteria andIsrecreateGreaterThanOrEqualTo(String value) {
            addCriterion("ISRECREATE >=", value, "isrecreate");
            return (Criteria) this;
        }

        public Criteria andIsrecreateLessThan(String value) {
            addCriterion("ISRECREATE <", value, "isrecreate");
            return (Criteria) this;
        }

        public Criteria andIsrecreateLessThanOrEqualTo(String value) {
            addCriterion("ISRECREATE <=", value, "isrecreate");
            return (Criteria) this;
        }

        public Criteria andIsrecreateLike(String value) {
            addCriterion("ISRECREATE like", value, "isrecreate");
            return (Criteria) this;
        }

        public Criteria andIsrecreateNotLike(String value) {
            addCriterion("ISRECREATE not like", value, "isrecreate");
            return (Criteria) this;
        }

        public Criteria andIsrecreateIn(List<String> values) {
            addCriterion("ISRECREATE in", values, "isrecreate");
            return (Criteria) this;
        }

        public Criteria andIsrecreateNotIn(List<String> values) {
            addCriterion("ISRECREATE not in", values, "isrecreate");
            return (Criteria) this;
        }

        public Criteria andIsrecreateBetween(String value1, String value2) {
            addCriterion("ISRECREATE between", value1, value2, "isrecreate");
            return (Criteria) this;
        }

        public Criteria andIsrecreateNotBetween(String value1, String value2) {
            addCriterion("ISRECREATE not between", value1, value2, "isrecreate");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("CREATEDATE is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("CREATEDATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterionForJDBCDate("CREATEDATE =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATEDATE <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATEDATE >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATEDATE >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterionForJDBCDate("CREATEDATE <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATEDATE <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterionForJDBCDate("CREATEDATE in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATEDATE not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATEDATE between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATEDATE not between", value1, value2, "createdate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated do_not_delete_during_merge Tue Jul 18 17:13:40 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table JAVAOAS.T_OUT_REPORTST
     *
     * @mbg.generated Tue Jul 18 17:13:40 CST 2017
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}