package com.talkweb.ei.outmanager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TOutUserJcExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    public TOutUserJcExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
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
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
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

        public Criteria andJcreasonIsNull() {
            addCriterion("JCREASON is null");
            return (Criteria) this;
        }

        public Criteria andJcreasonIsNotNull() {
            addCriterion("JCREASON is not null");
            return (Criteria) this;
        }

        public Criteria andJcreasonEqualTo(String value) {
            addCriterion("JCREASON =", value, "jcreason");
            return (Criteria) this;
        }

        public Criteria andJcreasonNotEqualTo(String value) {
            addCriterion("JCREASON <>", value, "jcreason");
            return (Criteria) this;
        }

        public Criteria andJcreasonGreaterThan(String value) {
            addCriterion("JCREASON >", value, "jcreason");
            return (Criteria) this;
        }

        public Criteria andJcreasonGreaterThanOrEqualTo(String value) {
            addCriterion("JCREASON >=", value, "jcreason");
            return (Criteria) this;
        }

        public Criteria andJcreasonLessThan(String value) {
            addCriterion("JCREASON <", value, "jcreason");
            return (Criteria) this;
        }

        public Criteria andJcreasonLessThanOrEqualTo(String value) {
            addCriterion("JCREASON <=", value, "jcreason");
            return (Criteria) this;
        }

        public Criteria andJcreasonLike(String value) {
            addCriterion("JCREASON like", value, "jcreason");
            return (Criteria) this;
        }

        public Criteria andJcreasonNotLike(String value) {
            addCriterion("JCREASON not like", value, "jcreason");
            return (Criteria) this;
        }

        public Criteria andJcreasonIn(List<String> values) {
            addCriterion("JCREASON in", values, "jcreason");
            return (Criteria) this;
        }

        public Criteria andJcreasonNotIn(List<String> values) {
            addCriterion("JCREASON not in", values, "jcreason");
            return (Criteria) this;
        }

        public Criteria andJcreasonBetween(String value1, String value2) {
            addCriterion("JCREASON between", value1, value2, "jcreason");
            return (Criteria) this;
        }

        public Criteria andJcreasonNotBetween(String value1, String value2) {
            addCriterion("JCREASON not between", value1, value2, "jcreason");
            return (Criteria) this;
        }

        public Criteria andJcdateIsNull() {
            addCriterion("JCDATE is null");
            return (Criteria) this;
        }

        public Criteria andJcdateIsNotNull() {
            addCriterion("JCDATE is not null");
            return (Criteria) this;
        }

        public Criteria andJcdateEqualTo(Date value) {
            addCriterionForJDBCDate("JCDATE =", value, "jcdate");
            return (Criteria) this;
        }

        public Criteria andJcdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("JCDATE <>", value, "jcdate");
            return (Criteria) this;
        }

        public Criteria andJcdateGreaterThan(Date value) {
            addCriterionForJDBCDate("JCDATE >", value, "jcdate");
            return (Criteria) this;
        }

        public Criteria andJcdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("JCDATE >=", value, "jcdate");
            return (Criteria) this;
        }

        public Criteria andJcdateLessThan(Date value) {
            addCriterionForJDBCDate("JCDATE <", value, "jcdate");
            return (Criteria) this;
        }

        public Criteria andJcdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("JCDATE <=", value, "jcdate");
            return (Criteria) this;
        }

        public Criteria andJcdateIn(List<Date> values) {
            addCriterionForJDBCDate("JCDATE in", values, "jcdate");
            return (Criteria) this;
        }

        public Criteria andJcdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("JCDATE not in", values, "jcdate");
            return (Criteria) this;
        }

        public Criteria andJcdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("JCDATE between", value1, value2, "jcdate");
            return (Criteria) this;
        }

        public Criteria andJcdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("JCDATE not between", value1, value2, "jcdate");
            return (Criteria) this;
        }

        public Criteria andGzenddateIsNull() {
            addCriterion("GZENDDATE is null");
            return (Criteria) this;
        }

        public Criteria andGzenddateIsNotNull() {
            addCriterion("GZENDDATE is not null");
            return (Criteria) this;
        }

        public Criteria andGzenddateEqualTo(Date value) {
            addCriterionForJDBCDate("GZENDDATE =", value, "gzenddate");
            return (Criteria) this;
        }

        public Criteria andGzenddateNotEqualTo(Date value) {
            addCriterionForJDBCDate("GZENDDATE <>", value, "gzenddate");
            return (Criteria) this;
        }

        public Criteria andGzenddateGreaterThan(Date value) {
            addCriterionForJDBCDate("GZENDDATE >", value, "gzenddate");
            return (Criteria) this;
        }

        public Criteria andGzenddateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("GZENDDATE >=", value, "gzenddate");
            return (Criteria) this;
        }

        public Criteria andGzenddateLessThan(Date value) {
            addCriterionForJDBCDate("GZENDDATE <", value, "gzenddate");
            return (Criteria) this;
        }

        public Criteria andGzenddateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("GZENDDATE <=", value, "gzenddate");
            return (Criteria) this;
        }

        public Criteria andGzenddateIn(List<Date> values) {
            addCriterionForJDBCDate("GZENDDATE in", values, "gzenddate");
            return (Criteria) this;
        }

        public Criteria andGzenddateNotIn(List<Date> values) {
            addCriterionForJDBCDate("GZENDDATE not in", values, "gzenddate");
            return (Criteria) this;
        }

        public Criteria andGzenddateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("GZENDDATE between", value1, value2, "gzenddate");
            return (Criteria) this;
        }

        public Criteria andGzenddateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("GZENDDATE not between", value1, value2, "gzenddate");
            return (Criteria) this;
        }

        public Criteria andQtIsNull() {
            addCriterion("QT is null");
            return (Criteria) this;
        }

        public Criteria andQtIsNotNull() {
            addCriterion("QT is not null");
            return (Criteria) this;
        }

        public Criteria andQtEqualTo(String value) {
            addCriterion("QT =", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtNotEqualTo(String value) {
            addCriterion("QT <>", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtGreaterThan(String value) {
            addCriterion("QT >", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtGreaterThanOrEqualTo(String value) {
            addCriterion("QT >=", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtLessThan(String value) {
            addCriterion("QT <", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtLessThanOrEqualTo(String value) {
            addCriterion("QT <=", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtLike(String value) {
            addCriterion("QT like", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtNotLike(String value) {
            addCriterion("QT not like", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtIn(List<String> values) {
            addCriterion("QT in", values, "qt");
            return (Criteria) this;
        }

        public Criteria andQtNotIn(List<String> values) {
            addCriterion("QT not in", values, "qt");
            return (Criteria) this;
        }

        public Criteria andQtBetween(String value1, String value2) {
            addCriterion("QT between", value1, value2, "qt");
            return (Criteria) this;
        }

        public Criteria andQtNotBetween(String value1, String value2) {
            addCriterion("QT not between", value1, value2, "qt");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("USERID is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("USERID is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("USERID =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("USERID <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("USERID >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("USERID >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("USERID <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("USERID <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("USERID like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("USERID not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("USERID in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("USERID not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("USERID between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("USERID not between", value1, value2, "userid");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated do_not_delete_during_merge Wed May 24 16:24:40 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table JAVAOAS.T_OUT_USER_JC
     *
     * @mbg.generated Wed May 24 16:24:40 CST 2017
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