package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopBrandExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopBrandExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andShopidIsNull() {
            addCriterion("ShopID is null");
            return (Criteria) this;
        }

        public Criteria andShopidIsNotNull() {
            addCriterion("ShopID is not null");
            return (Criteria) this;
        }

        public Criteria andShopidEqualTo(Integer value) {
            addCriterion("ShopID =", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotEqualTo(Integer value) {
            addCriterion("ShopID <>", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThan(Integer value) {
            addCriterion("ShopID >", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ShopID >=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThan(Integer value) {
            addCriterion("ShopID <", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThanOrEqualTo(Integer value) {
            addCriterion("ShopID <=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidIn(List<Integer> values) {
            addCriterion("ShopID in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotIn(List<Integer> values) {
            addCriterion("ShopID not in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidBetween(Integer value1, Integer value2) {
            addCriterion("ShopID between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotBetween(Integer value1, Integer value2) {
            addCriterion("ShopID not between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andBrandidIsNull() {
            addCriterion("BrandID is null");
            return (Criteria) this;
        }

        public Criteria andBrandidIsNotNull() {
            addCriterion("BrandID is not null");
            return (Criteria) this;
        }

        public Criteria andBrandidEqualTo(Integer value) {
            addCriterion("BrandID =", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidNotEqualTo(Integer value) {
            addCriterion("BrandID <>", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidGreaterThan(Integer value) {
            addCriterion("BrandID >", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidGreaterThanOrEqualTo(Integer value) {
            addCriterion("BrandID >=", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidLessThan(Integer value) {
            addCriterion("BrandID <", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidLessThanOrEqualTo(Integer value) {
            addCriterion("BrandID <=", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidIn(List<Integer> values) {
            addCriterion("BrandID in", values, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidNotIn(List<Integer> values) {
            addCriterion("BrandID not in", values, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidBetween(Integer value1, Integer value2) {
            addCriterion("BrandID between", value1, value2, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidNotBetween(Integer value1, Integer value2) {
            addCriterion("BrandID not between", value1, value2, "brandid");
            return (Criteria) this;
        }

        public Criteria andCheckstatusIsNull() {
            addCriterion("CheckStatus is null");
            return (Criteria) this;
        }

        public Criteria andCheckstatusIsNotNull() {
            addCriterion("CheckStatus is not null");
            return (Criteria) this;
        }

        public Criteria andCheckstatusEqualTo(Integer value) {
            addCriterion("CheckStatus =", value, "checkstatus");
            return (Criteria) this;
        }

        public Criteria andCheckstatusNotEqualTo(Integer value) {
            addCriterion("CheckStatus <>", value, "checkstatus");
            return (Criteria) this;
        }

        public Criteria andCheckstatusGreaterThan(Integer value) {
            addCriterion("CheckStatus >", value, "checkstatus");
            return (Criteria) this;
        }

        public Criteria andCheckstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("CheckStatus >=", value, "checkstatus");
            return (Criteria) this;
        }

        public Criteria andCheckstatusLessThan(Integer value) {
            addCriterion("CheckStatus <", value, "checkstatus");
            return (Criteria) this;
        }

        public Criteria andCheckstatusLessThanOrEqualTo(Integer value) {
            addCriterion("CheckStatus <=", value, "checkstatus");
            return (Criteria) this;
        }

        public Criteria andCheckstatusIn(List<Integer> values) {
            addCriterion("CheckStatus in", values, "checkstatus");
            return (Criteria) this;
        }

        public Criteria andCheckstatusNotIn(List<Integer> values) {
            addCriterion("CheckStatus not in", values, "checkstatus");
            return (Criteria) this;
        }

        public Criteria andCheckstatusBetween(Integer value1, Integer value2) {
            addCriterion("CheckStatus between", value1, value2, "checkstatus");
            return (Criteria) this;
        }

        public Criteria andCheckstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("CheckStatus not between", value1, value2, "checkstatus");
            return (Criteria) this;
        }

        public Criteria andCheckuseridIsNull() {
            addCriterion("CheckUserID is null");
            return (Criteria) this;
        }

        public Criteria andCheckuseridIsNotNull() {
            addCriterion("CheckUserID is not null");
            return (Criteria) this;
        }

        public Criteria andCheckuseridEqualTo(Integer value) {
            addCriterion("CheckUserID =", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridNotEqualTo(Integer value) {
            addCriterion("CheckUserID <>", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridGreaterThan(Integer value) {
            addCriterion("CheckUserID >", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("CheckUserID >=", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridLessThan(Integer value) {
            addCriterion("CheckUserID <", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridLessThanOrEqualTo(Integer value) {
            addCriterion("CheckUserID <=", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridIn(List<Integer> values) {
            addCriterion("CheckUserID in", values, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridNotIn(List<Integer> values) {
            addCriterion("CheckUserID not in", values, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridBetween(Integer value1, Integer value2) {
            addCriterion("CheckUserID between", value1, value2, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridNotBetween(Integer value1, Integer value2) {
            addCriterion("CheckUserID not between", value1, value2, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andChecktimeIsNull() {
            addCriterion("CheckTime is null");
            return (Criteria) this;
        }

        public Criteria andChecktimeIsNotNull() {
            addCriterion("CheckTime is not null");
            return (Criteria) this;
        }

        public Criteria andChecktimeEqualTo(Date value) {
            addCriterion("CheckTime =", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeNotEqualTo(Date value) {
            addCriterion("CheckTime <>", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeGreaterThan(Date value) {
            addCriterion("CheckTime >", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CheckTime >=", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeLessThan(Date value) {
            addCriterion("CheckTime <", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeLessThanOrEqualTo(Date value) {
            addCriterion("CheckTime <=", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeIn(List<Date> values) {
            addCriterion("CheckTime in", values, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeNotIn(List<Date> values) {
            addCriterion("CheckTime not in", values, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeBetween(Date value1, Date value2) {
            addCriterion("CheckTime between", value1, value2, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeNotBetween(Date value1, Date value2) {
            addCriterion("CheckTime not between", value1, value2, "checktime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("CreateTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("CreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("CreateTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("CreateTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("CreateTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CreateTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("CreateTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("CreateTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("CreateTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("CreateTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("CreateTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("CreateTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNull() {
            addCriterion("IsDel is null");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNotNull() {
            addCriterion("IsDel is not null");
            return (Criteria) this;
        }

        public Criteria andIsdelEqualTo(Boolean value) {
            addCriterion("IsDel =", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotEqualTo(Boolean value) {
            addCriterion("IsDel <>", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThan(Boolean value) {
            addCriterion("IsDel >", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsDel >=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThan(Boolean value) {
            addCriterion("IsDel <", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThanOrEqualTo(Boolean value) {
            addCriterion("IsDel <=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelIn(List<Boolean> values) {
            addCriterion("IsDel in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotIn(List<Boolean> values) {
            addCriterion("IsDel not in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelBetween(Boolean value1, Boolean value2) {
            addCriterion("IsDel between", value1, value2, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsDel not between", value1, value2, "isdel");
            return (Criteria) this;
        }

        public Criteria andDeltimeIsNull() {
            addCriterion("DelTime is null");
            return (Criteria) this;
        }

        public Criteria andDeltimeIsNotNull() {
            addCriterion("DelTime is not null");
            return (Criteria) this;
        }

        public Criteria andDeltimeEqualTo(Date value) {
            addCriterion("DelTime =", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotEqualTo(Date value) {
            addCriterion("DelTime <>", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeGreaterThan(Date value) {
            addCriterion("DelTime >", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeGreaterThanOrEqualTo(Date value) {
            addCriterion("DelTime >=", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeLessThan(Date value) {
            addCriterion("DelTime <", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeLessThanOrEqualTo(Date value) {
            addCriterion("DelTime <=", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeIn(List<Date> values) {
            addCriterion("DelTime in", values, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotIn(List<Date> values) {
            addCriterion("DelTime not in", values, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeBetween(Date value1, Date value2) {
            addCriterion("DelTime between", value1, value2, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotBetween(Date value1, Date value2) {
            addCriterion("DelTime not between", value1, value2, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeluseridIsNull() {
            addCriterion("DelUserID is null");
            return (Criteria) this;
        }

        public Criteria andDeluseridIsNotNull() {
            addCriterion("DelUserID is not null");
            return (Criteria) this;
        }

        public Criteria andDeluseridEqualTo(Integer value) {
            addCriterion("DelUserID =", value, "deluserid");
            return (Criteria) this;
        }

        public Criteria andDeluseridNotEqualTo(Integer value) {
            addCriterion("DelUserID <>", value, "deluserid");
            return (Criteria) this;
        }

        public Criteria andDeluseridGreaterThan(Integer value) {
            addCriterion("DelUserID >", value, "deluserid");
            return (Criteria) this;
        }

        public Criteria andDeluseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("DelUserID >=", value, "deluserid");
            return (Criteria) this;
        }

        public Criteria andDeluseridLessThan(Integer value) {
            addCriterion("DelUserID <", value, "deluserid");
            return (Criteria) this;
        }

        public Criteria andDeluseridLessThanOrEqualTo(Integer value) {
            addCriterion("DelUserID <=", value, "deluserid");
            return (Criteria) this;
        }

        public Criteria andDeluseridIn(List<Integer> values) {
            addCriterion("DelUserID in", values, "deluserid");
            return (Criteria) this;
        }

        public Criteria andDeluseridNotIn(List<Integer> values) {
            addCriterion("DelUserID not in", values, "deluserid");
            return (Criteria) this;
        }

        public Criteria andDeluseridBetween(Integer value1, Integer value2) {
            addCriterion("DelUserID between", value1, value2, "deluserid");
            return (Criteria) this;
        }

        public Criteria andDeluseridNotBetween(Integer value1, Integer value2) {
            addCriterion("DelUserID not between", value1, value2, "deluserid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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