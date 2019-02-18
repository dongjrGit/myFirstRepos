package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GroupbuyorderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupbuyorderExample() {
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

        public Criteria andBuyeridIsNull() {
            addCriterion("BuyerID is null");
            return (Criteria) this;
        }

        public Criteria andBuyeridIsNotNull() {
            addCriterion("BuyerID is not null");
            return (Criteria) this;
        }

        public Criteria andBuyeridEqualTo(Integer value) {
            addCriterion("BuyerID =", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridNotEqualTo(Integer value) {
            addCriterion("BuyerID <>", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridGreaterThan(Integer value) {
            addCriterion("BuyerID >", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridGreaterThanOrEqualTo(Integer value) {
            addCriterion("BuyerID >=", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridLessThan(Integer value) {
            addCriterion("BuyerID <", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridLessThanOrEqualTo(Integer value) {
            addCriterion("BuyerID <=", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridIn(List<Integer> values) {
            addCriterion("BuyerID in", values, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridNotIn(List<Integer> values) {
            addCriterion("BuyerID not in", values, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridBetween(Integer value1, Integer value2) {
            addCriterion("BuyerID between", value1, value2, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridNotBetween(Integer value1, Integer value2) {
            addCriterion("BuyerID not between", value1, value2, "buyerid");
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

        public Criteria andOrdercodeIsNull() {
            addCriterion("OrderCode is null");
            return (Criteria) this;
        }

        public Criteria andOrdercodeIsNotNull() {
            addCriterion("OrderCode is not null");
            return (Criteria) this;
        }

        public Criteria andOrdercodeEqualTo(String value) {
            addCriterion("OrderCode =", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotEqualTo(String value) {
            addCriterion("OrderCode <>", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeGreaterThan(String value) {
            addCriterion("OrderCode >", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeGreaterThanOrEqualTo(String value) {
            addCriterion("OrderCode >=", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeLessThan(String value) {
            addCriterion("OrderCode <", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeLessThanOrEqualTo(String value) {
            addCriterion("OrderCode <=", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeLike(String value) {
            addCriterion("OrderCode like", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotLike(String value) {
            addCriterion("OrderCode not like", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeIn(List<String> values) {
            addCriterion("OrderCode in", values, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotIn(List<String> values) {
            addCriterion("OrderCode not in", values, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeBetween(String value1, String value2) {
            addCriterion("OrderCode between", value1, value2, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotBetween(String value1, String value2) {
            addCriterion("OrderCode not between", value1, value2, "ordercode");
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
            addCriterionForJDBCDate("CreateTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CreateTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CreateTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CreateTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterionForJDBCDate("CreateTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CreateTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("CreateTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CreateTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CreateTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CreateTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeIsNull() {
            addCriterion("GroupBuyCode is null");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeIsNotNull() {
            addCriterion("GroupBuyCode is not null");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeEqualTo(String value) {
            addCriterion("GroupBuyCode =", value, "groupbuycode");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeNotEqualTo(String value) {
            addCriterion("GroupBuyCode <>", value, "groupbuycode");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeGreaterThan(String value) {
            addCriterion("GroupBuyCode >", value, "groupbuycode");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeGreaterThanOrEqualTo(String value) {
            addCriterion("GroupBuyCode >=", value, "groupbuycode");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeLessThan(String value) {
            addCriterion("GroupBuyCode <", value, "groupbuycode");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeLessThanOrEqualTo(String value) {
            addCriterion("GroupBuyCode <=", value, "groupbuycode");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeLike(String value) {
            addCriterion("GroupBuyCode like", value, "groupbuycode");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeNotLike(String value) {
            addCriterion("GroupBuyCode not like", value, "groupbuycode");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeIn(List<String> values) {
            addCriterion("GroupBuyCode in", values, "groupbuycode");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeNotIn(List<String> values) {
            addCriterion("GroupBuyCode not in", values, "groupbuycode");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeBetween(String value1, String value2) {
            addCriterion("GroupBuyCode between", value1, value2, "groupbuycode");
            return (Criteria) this;
        }

        public Criteria andGroupbuycodeNotBetween(String value1, String value2) {
            addCriterion("GroupBuyCode not between", value1, value2, "groupbuycode");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("StartTime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("StartTime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Date value) {
            addCriterionForJDBCDate("StartTime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("StartTime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("StartTime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("StartTime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Date value) {
            addCriterionForJDBCDate("StartTime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("StartTime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Date> values) {
            addCriterionForJDBCDate("StartTime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("StartTime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("StartTime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("StartTime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("EndTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("EndTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterionForJDBCDate("EndTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("EndTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("EndTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EndTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterionForJDBCDate("EndTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EndTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterionForJDBCDate("EndTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("EndTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EndTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EndTime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("Status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("Status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("Status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("Status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("Status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("Status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("Status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("Status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("Status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("Status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("Status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("Status not between", value1, value2, "status");
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

        public Criteria andIsdelEqualTo(Integer value) {
            addCriterion("IsDel =", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotEqualTo(Integer value) {
            addCriterion("IsDel <>", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThan(Integer value) {
            addCriterion("IsDel >", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsDel >=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThan(Integer value) {
            addCriterion("IsDel <", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThanOrEqualTo(Integer value) {
            addCriterion("IsDel <=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelIn(List<Integer> values) {
            addCriterion("IsDel in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotIn(List<Integer> values) {
            addCriterion("IsDel not in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelBetween(Integer value1, Integer value2) {
            addCriterion("IsDel between", value1, value2, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotBetween(Integer value1, Integer value2) {
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
            addCriterionForJDBCDate("DelTime =", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("DelTime <>", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeGreaterThan(Date value) {
            addCriterionForJDBCDate("DelTime >", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DelTime >=", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeLessThan(Date value) {
            addCriterionForJDBCDate("DelTime <", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DelTime <=", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeIn(List<Date> values) {
            addCriterionForJDBCDate("DelTime in", values, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("DelTime not in", values, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DelTime between", value1, value2, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DelTime not between", value1, value2, "deltime");
            return (Criteria) this;
        }
        public Criteria andPaytimeIsNull() {
            addCriterion("PayTime is null");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNotNull() {
            addCriterion("PayTime is not null");
            return (Criteria) this;
        }

        public Criteria andPaytimeEqualTo(Date value) {
            addCriterionForJDBCDate("PayTime =", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("PayTime <>", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThan(Date value) {
            addCriterionForJDBCDate("PayTime >", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PayTime >=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThan(Date value) {
            addCriterionForJDBCDate("PayTime <", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PayTime <=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeIn(List<Date> values) {
            addCriterionForJDBCDate("PayTime in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("PayTime not in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PayTime between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PayTime not between", value1, value2, "paytime");
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