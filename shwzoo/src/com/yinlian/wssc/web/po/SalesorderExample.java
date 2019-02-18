package com.yinlian.wssc.web.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesorderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SalesorderExample() {
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

        public Criteria andShopnameIsNull() {
            addCriterion("ShopName is null");
            return (Criteria) this;
        }

        public Criteria andShopnameIsNotNull() {
            addCriterion("ShopName is not null");
            return (Criteria) this;
        }

        public Criteria andShopnameEqualTo(String value) {
            addCriterion("ShopName =", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotEqualTo(String value) {
            addCriterion("ShopName <>", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameGreaterThan(String value) {
            addCriterion("ShopName >", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameGreaterThanOrEqualTo(String value) {
            addCriterion("ShopName >=", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLessThan(String value) {
            addCriterion("ShopName <", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLessThanOrEqualTo(String value) {
            addCriterion("ShopName <=", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLike(String value) {
            addCriterion("ShopName like", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotLike(String value) {
            addCriterion("ShopName not like", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameIn(List<String> values) {
            addCriterion("ShopName in", values, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotIn(List<String> values) {
            addCriterion("ShopName not in", values, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameBetween(String value1, String value2) {
            addCriterion("ShopName between", value1, value2, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotBetween(String value1, String value2) {
            addCriterion("ShopName not between", value1, value2, "shopname");
            return (Criteria) this;
        }

        public Criteria andSelleridIsNull() {
            addCriterion("SellerID is null");
            return (Criteria) this;
        }

        public Criteria andSelleridIsNotNull() {
            addCriterion("SellerID is not null");
            return (Criteria) this;
        }

        public Criteria andSelleridEqualTo(Integer value) {
            addCriterion("SellerID =", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridNotEqualTo(Integer value) {
            addCriterion("SellerID <>", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridGreaterThan(Integer value) {
            addCriterion("SellerID >", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridGreaterThanOrEqualTo(Integer value) {
            addCriterion("SellerID >=", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridLessThan(Integer value) {
            addCriterion("SellerID <", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridLessThanOrEqualTo(Integer value) {
            addCriterion("SellerID <=", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridIn(List<Integer> values) {
            addCriterion("SellerID in", values, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridNotIn(List<Integer> values) {
            addCriterion("SellerID not in", values, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridBetween(Integer value1, Integer value2) {
            addCriterion("SellerID between", value1, value2, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridNotBetween(Integer value1, Integer value2) {
            addCriterion("SellerID not between", value1, value2, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSellernameIsNull() {
            addCriterion("SellerName is null");
            return (Criteria) this;
        }

        public Criteria andSellernameIsNotNull() {
            addCriterion("SellerName is not null");
            return (Criteria) this;
        }

        public Criteria andSellernameEqualTo(String value) {
            addCriterion("SellerName =", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameNotEqualTo(String value) {
            addCriterion("SellerName <>", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameGreaterThan(String value) {
            addCriterion("SellerName >", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameGreaterThanOrEqualTo(String value) {
            addCriterion("SellerName >=", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameLessThan(String value) {
            addCriterion("SellerName <", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameLessThanOrEqualTo(String value) {
            addCriterion("SellerName <=", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameLike(String value) {
            addCriterion("SellerName like", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameNotLike(String value) {
            addCriterion("SellerName not like", value, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameIn(List<String> values) {
            addCriterion("SellerName in", values, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameNotIn(List<String> values) {
            addCriterion("SellerName not in", values, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameBetween(String value1, String value2) {
            addCriterion("SellerName between", value1, value2, "sellername");
            return (Criteria) this;
        }

        public Criteria andSellernameNotBetween(String value1, String value2) {
            addCriterion("SellerName not between", value1, value2, "sellername");
            return (Criteria) this;
        }

        public Criteria andStypeIsNull() {
            addCriterion("sType is null");
            return (Criteria) this;
        }

        public Criteria andStypeIsNotNull() {
            addCriterion("sType is not null");
            return (Criteria) this;
        }

        public Criteria andStypeEqualTo(Integer value) {
            addCriterion("sType =", value, "stype");
            return (Criteria) this;
        }

        public Criteria andStypeNotEqualTo(Integer value) {
            addCriterion("sType <>", value, "stype");
            return (Criteria) this;
        }

        public Criteria andStypeGreaterThan(Integer value) {
            addCriterion("sType >", value, "stype");
            return (Criteria) this;
        }

        public Criteria andStypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sType >=", value, "stype");
            return (Criteria) this;
        }

        public Criteria andStypeLessThan(Integer value) {
            addCriterion("sType <", value, "stype");
            return (Criteria) this;
        }

        public Criteria andStypeLessThanOrEqualTo(Integer value) {
            addCriterion("sType <=", value, "stype");
            return (Criteria) this;
        }

        public Criteria andStypeIn(List<Integer> values) {
            addCriterion("sType in", values, "stype");
            return (Criteria) this;
        }

        public Criteria andStypeNotIn(List<Integer> values) {
            addCriterion("sType not in", values, "stype");
            return (Criteria) this;
        }

        public Criteria andStypeBetween(Integer value1, Integer value2) {
            addCriterion("sType between", value1, value2, "stype");
            return (Criteria) this;
        }

        public Criteria andStypeNotBetween(Integer value1, Integer value2) {
            addCriterion("sType not between", value1, value2, "stype");
            return (Criteria) this;
        }

        public Criteria andAddorderdateIsNull() {
            addCriterion("AddOrderDate is null");
            return (Criteria) this;
        }

        public Criteria andAddorderdateIsNotNull() {
            addCriterion("AddOrderDate is not null");
            return (Criteria) this;
        }

        public Criteria andAddorderdateEqualTo(Date value) {
            addCriterion("AddOrderDate =", value, "addorderdate");
            return (Criteria) this;
        }

        public Criteria andAddorderdateNotEqualTo(Date value) {
            addCriterion("AddOrderDate <>", value, "addorderdate");
            return (Criteria) this;
        }

        public Criteria andAddorderdateGreaterThan(Date value) {
            addCriterion("AddOrderDate >", value, "addorderdate");
            return (Criteria) this;
        }

        public Criteria andAddorderdateGreaterThanOrEqualTo(Date value) {
            addCriterion("AddOrderDate >=", value, "addorderdate");
            return (Criteria) this;
        }

        public Criteria andAddorderdateLessThan(Date value) {
            addCriterion("AddOrderDate <", value, "addorderdate");
            return (Criteria) this;
        }

        public Criteria andAddorderdateLessThanOrEqualTo(Date value) {
            addCriterion("AddOrderDate <=", value, "addorderdate");
            return (Criteria) this;
        }

        public Criteria andAddorderdateIn(List<Date> values) {
            addCriterion("AddOrderDate in", values, "addorderdate");
            return (Criteria) this;
        }

        public Criteria andAddorderdateNotIn(List<Date> values) {
            addCriterion("AddOrderDate not in", values, "addorderdate");
            return (Criteria) this;
        }

        public Criteria andAddorderdateBetween(Date value1, Date value2) {
            addCriterion("AddOrderDate between", value1, value2, "addorderdate");
            return (Criteria) this;
        }

        public Criteria andAddorderdateNotBetween(Date value1, Date value2) {
            addCriterion("AddOrderDate not between", value1, value2, "addorderdate");
            return (Criteria) this;
        }

        public Criteria andTotalcountIsNull() {
            addCriterion("TotalCount is null");
            return (Criteria) this;
        }

        public Criteria andTotalcountIsNotNull() {
            addCriterion("TotalCount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalcountEqualTo(Integer value) {
            addCriterion("TotalCount =", value, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountNotEqualTo(Integer value) {
            addCriterion("TotalCount <>", value, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountGreaterThan(Integer value) {
            addCriterion("TotalCount >", value, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("TotalCount >=", value, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountLessThan(Integer value) {
            addCriterion("TotalCount <", value, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountLessThanOrEqualTo(Integer value) {
            addCriterion("TotalCount <=", value, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountIn(List<Integer> values) {
            addCriterion("TotalCount in", values, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountNotIn(List<Integer> values) {
            addCriterion("TotalCount not in", values, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountBetween(Integer value1, Integer value2) {
            addCriterion("TotalCount between", value1, value2, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountNotBetween(Integer value1, Integer value2) {
            addCriterion("TotalCount not between", value1, value2, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyIsNull() {
            addCriterion("TotalMoney is null");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyIsNotNull() {
            addCriterion("TotalMoney is not null");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyEqualTo(BigDecimal value) {
            addCriterion("TotalMoney =", value, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyNotEqualTo(BigDecimal value) {
            addCriterion("TotalMoney <>", value, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyGreaterThan(BigDecimal value) {
            addCriterion("TotalMoney >", value, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TotalMoney >=", value, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyLessThan(BigDecimal value) {
            addCriterion("TotalMoney <", value, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TotalMoney <=", value, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyIn(List<BigDecimal> values) {
            addCriterion("TotalMoney in", values, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyNotIn(List<BigDecimal> values) {
            addCriterion("TotalMoney not in", values, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TotalMoney between", value1, value2, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TotalMoney not between", value1, value2, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andCountDfkIsNull() {
            addCriterion("Count_DFK is null");
            return (Criteria) this;
        }

        public Criteria andCountDfkIsNotNull() {
            addCriterion("Count_DFK is not null");
            return (Criteria) this;
        }

        public Criteria andCountDfkEqualTo(Integer value) {
            addCriterion("Count_DFK =", value, "countDfk");
            return (Criteria) this;
        }

        public Criteria andCountDfkNotEqualTo(Integer value) {
            addCriterion("Count_DFK <>", value, "countDfk");
            return (Criteria) this;
        }

        public Criteria andCountDfkGreaterThan(Integer value) {
            addCriterion("Count_DFK >", value, "countDfk");
            return (Criteria) this;
        }

        public Criteria andCountDfkGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_DFK >=", value, "countDfk");
            return (Criteria) this;
        }

        public Criteria andCountDfkLessThan(Integer value) {
            addCriterion("Count_DFK <", value, "countDfk");
            return (Criteria) this;
        }

        public Criteria andCountDfkLessThanOrEqualTo(Integer value) {
            addCriterion("Count_DFK <=", value, "countDfk");
            return (Criteria) this;
        }

        public Criteria andCountDfkIn(List<Integer> values) {
            addCriterion("Count_DFK in", values, "countDfk");
            return (Criteria) this;
        }

        public Criteria andCountDfkNotIn(List<Integer> values) {
            addCriterion("Count_DFK not in", values, "countDfk");
            return (Criteria) this;
        }

        public Criteria andCountDfkBetween(Integer value1, Integer value2) {
            addCriterion("Count_DFK between", value1, value2, "countDfk");
            return (Criteria) this;
        }

        public Criteria andCountDfkNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_DFK not between", value1, value2, "countDfk");
            return (Criteria) this;
        }

        public Criteria andMoneyDfkIsNull() {
            addCriterion("Money_DFK is null");
            return (Criteria) this;
        }

        public Criteria andMoneyDfkIsNotNull() {
            addCriterion("Money_DFK is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyDfkEqualTo(BigDecimal value) {
            addCriterion("Money_DFK =", value, "moneyDfk");
            return (Criteria) this;
        }

        public Criteria andMoneyDfkNotEqualTo(BigDecimal value) {
            addCriterion("Money_DFK <>", value, "moneyDfk");
            return (Criteria) this;
        }

        public Criteria andMoneyDfkGreaterThan(BigDecimal value) {
            addCriterion("Money_DFK >", value, "moneyDfk");
            return (Criteria) this;
        }

        public Criteria andMoneyDfkGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_DFK >=", value, "moneyDfk");
            return (Criteria) this;
        }

        public Criteria andMoneyDfkLessThan(BigDecimal value) {
            addCriterion("Money_DFK <", value, "moneyDfk");
            return (Criteria) this;
        }

        public Criteria andMoneyDfkLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_DFK <=", value, "moneyDfk");
            return (Criteria) this;
        }

        public Criteria andMoneyDfkIn(List<BigDecimal> values) {
            addCriterion("Money_DFK in", values, "moneyDfk");
            return (Criteria) this;
        }

        public Criteria andMoneyDfkNotIn(List<BigDecimal> values) {
            addCriterion("Money_DFK not in", values, "moneyDfk");
            return (Criteria) this;
        }

        public Criteria andMoneyDfkBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_DFK between", value1, value2, "moneyDfk");
            return (Criteria) this;
        }

        public Criteria andMoneyDfkNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_DFK not between", value1, value2, "moneyDfk");
            return (Criteria) this;
        }

        public Criteria andCountFkIsNull() {
            addCriterion("Count_FK is null");
            return (Criteria) this;
        }

        public Criteria andCountFkIsNotNull() {
            addCriterion("Count_FK is not null");
            return (Criteria) this;
        }

        public Criteria andCountFkEqualTo(Integer value) {
            addCriterion("Count_FK =", value, "countFk");
            return (Criteria) this;
        }

        public Criteria andCountFkNotEqualTo(Integer value) {
            addCriterion("Count_FK <>", value, "countFk");
            return (Criteria) this;
        }

        public Criteria andCountFkGreaterThan(Integer value) {
            addCriterion("Count_FK >", value, "countFk");
            return (Criteria) this;
        }

        public Criteria andCountFkGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_FK >=", value, "countFk");
            return (Criteria) this;
        }

        public Criteria andCountFkLessThan(Integer value) {
            addCriterion("Count_FK <", value, "countFk");
            return (Criteria) this;
        }

        public Criteria andCountFkLessThanOrEqualTo(Integer value) {
            addCriterion("Count_FK <=", value, "countFk");
            return (Criteria) this;
        }

        public Criteria andCountFkIn(List<Integer> values) {
            addCriterion("Count_FK in", values, "countFk");
            return (Criteria) this;
        }

        public Criteria andCountFkNotIn(List<Integer> values) {
            addCriterion("Count_FK not in", values, "countFk");
            return (Criteria) this;
        }

        public Criteria andCountFkBetween(Integer value1, Integer value2) {
            addCriterion("Count_FK between", value1, value2, "countFk");
            return (Criteria) this;
        }

        public Criteria andCountFkNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_FK not between", value1, value2, "countFk");
            return (Criteria) this;
        }

        public Criteria andMoneyFkIsNull() {
            addCriterion("Money_FK is null");
            return (Criteria) this;
        }

        public Criteria andMoneyFkIsNotNull() {
            addCriterion("Money_FK is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyFkEqualTo(BigDecimal value) {
            addCriterion("Money_FK =", value, "moneyFk");
            return (Criteria) this;
        }

        public Criteria andMoneyFkNotEqualTo(BigDecimal value) {
            addCriterion("Money_FK <>", value, "moneyFk");
            return (Criteria) this;
        }

        public Criteria andMoneyFkGreaterThan(BigDecimal value) {
            addCriterion("Money_FK >", value, "moneyFk");
            return (Criteria) this;
        }

        public Criteria andMoneyFkGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_FK >=", value, "moneyFk");
            return (Criteria) this;
        }

        public Criteria andMoneyFkLessThan(BigDecimal value) {
            addCriterion("Money_FK <", value, "moneyFk");
            return (Criteria) this;
        }

        public Criteria andMoneyFkLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_FK <=", value, "moneyFk");
            return (Criteria) this;
        }

        public Criteria andMoneyFkIn(List<BigDecimal> values) {
            addCriterion("Money_FK in", values, "moneyFk");
            return (Criteria) this;
        }

        public Criteria andMoneyFkNotIn(List<BigDecimal> values) {
            addCriterion("Money_FK not in", values, "moneyFk");
            return (Criteria) this;
        }

        public Criteria andMoneyFkBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_FK between", value1, value2, "moneyFk");
            return (Criteria) this;
        }

        public Criteria andMoneyFkNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_FK not between", value1, value2, "moneyFk");
            return (Criteria) this;
        }

        public Criteria andCountDfhIsNull() {
            addCriterion("Count_DFH is null");
            return (Criteria) this;
        }

        public Criteria andCountDfhIsNotNull() {
            addCriterion("Count_DFH is not null");
            return (Criteria) this;
        }

        public Criteria andCountDfhEqualTo(Integer value) {
            addCriterion("Count_DFH =", value, "countDfh");
            return (Criteria) this;
        }

        public Criteria andCountDfhNotEqualTo(Integer value) {
            addCriterion("Count_DFH <>", value, "countDfh");
            return (Criteria) this;
        }

        public Criteria andCountDfhGreaterThan(Integer value) {
            addCriterion("Count_DFH >", value, "countDfh");
            return (Criteria) this;
        }

        public Criteria andCountDfhGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_DFH >=", value, "countDfh");
            return (Criteria) this;
        }

        public Criteria andCountDfhLessThan(Integer value) {
            addCriterion("Count_DFH <", value, "countDfh");
            return (Criteria) this;
        }

        public Criteria andCountDfhLessThanOrEqualTo(Integer value) {
            addCriterion("Count_DFH <=", value, "countDfh");
            return (Criteria) this;
        }

        public Criteria andCountDfhIn(List<Integer> values) {
            addCriterion("Count_DFH in", values, "countDfh");
            return (Criteria) this;
        }

        public Criteria andCountDfhNotIn(List<Integer> values) {
            addCriterion("Count_DFH not in", values, "countDfh");
            return (Criteria) this;
        }

        public Criteria andCountDfhBetween(Integer value1, Integer value2) {
            addCriterion("Count_DFH between", value1, value2, "countDfh");
            return (Criteria) this;
        }

        public Criteria andCountDfhNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_DFH not between", value1, value2, "countDfh");
            return (Criteria) this;
        }

        public Criteria andMoneyDfhIsNull() {
            addCriterion("Money_DFH is null");
            return (Criteria) this;
        }

        public Criteria andMoneyDfhIsNotNull() {
            addCriterion("Money_DFH is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyDfhEqualTo(BigDecimal value) {
            addCriterion("Money_DFH =", value, "moneyDfh");
            return (Criteria) this;
        }

        public Criteria andMoneyDfhNotEqualTo(BigDecimal value) {
            addCriterion("Money_DFH <>", value, "moneyDfh");
            return (Criteria) this;
        }

        public Criteria andMoneyDfhGreaterThan(BigDecimal value) {
            addCriterion("Money_DFH >", value, "moneyDfh");
            return (Criteria) this;
        }

        public Criteria andMoneyDfhGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_DFH >=", value, "moneyDfh");
            return (Criteria) this;
        }

        public Criteria andMoneyDfhLessThan(BigDecimal value) {
            addCriterion("Money_DFH <", value, "moneyDfh");
            return (Criteria) this;
        }

        public Criteria andMoneyDfhLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_DFH <=", value, "moneyDfh");
            return (Criteria) this;
        }

        public Criteria andMoneyDfhIn(List<BigDecimal> values) {
            addCriterion("Money_DFH in", values, "moneyDfh");
            return (Criteria) this;
        }

        public Criteria andMoneyDfhNotIn(List<BigDecimal> values) {
            addCriterion("Money_DFH not in", values, "moneyDfh");
            return (Criteria) this;
        }

        public Criteria andMoneyDfhBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_DFH between", value1, value2, "moneyDfh");
            return (Criteria) this;
        }

        public Criteria andMoneyDfhNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_DFH not between", value1, value2, "moneyDfh");
            return (Criteria) this;
        }

        public Criteria andCountYshIsNull() {
            addCriterion("Count_YSH is null");
            return (Criteria) this;
        }

        public Criteria andCountYshIsNotNull() {
            addCriterion("Count_YSH is not null");
            return (Criteria) this;
        }

        public Criteria andCountYshEqualTo(Integer value) {
            addCriterion("Count_YSH =", value, "countYsh");
            return (Criteria) this;
        }

        public Criteria andCountYshNotEqualTo(Integer value) {
            addCriterion("Count_YSH <>", value, "countYsh");
            return (Criteria) this;
        }

        public Criteria andCountYshGreaterThan(Integer value) {
            addCriterion("Count_YSH >", value, "countYsh");
            return (Criteria) this;
        }

        public Criteria andCountYshGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_YSH >=", value, "countYsh");
            return (Criteria) this;
        }

        public Criteria andCountYshLessThan(Integer value) {
            addCriterion("Count_YSH <", value, "countYsh");
            return (Criteria) this;
        }

        public Criteria andCountYshLessThanOrEqualTo(Integer value) {
            addCriterion("Count_YSH <=", value, "countYsh");
            return (Criteria) this;
        }

        public Criteria andCountYshIn(List<Integer> values) {
            addCriterion("Count_YSH in", values, "countYsh");
            return (Criteria) this;
        }

        public Criteria andCountYshNotIn(List<Integer> values) {
            addCriterion("Count_YSH not in", values, "countYsh");
            return (Criteria) this;
        }

        public Criteria andCountYshBetween(Integer value1, Integer value2) {
            addCriterion("Count_YSH between", value1, value2, "countYsh");
            return (Criteria) this;
        }

        public Criteria andCountYshNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_YSH not between", value1, value2, "countYsh");
            return (Criteria) this;
        }

        public Criteria andMoneyYshIsNull() {
            addCriterion("Money_YSH is null");
            return (Criteria) this;
        }

        public Criteria andMoneyYshIsNotNull() {
            addCriterion("Money_YSH is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyYshEqualTo(BigDecimal value) {
            addCriterion("Money_YSH =", value, "moneyYsh");
            return (Criteria) this;
        }

        public Criteria andMoneyYshNotEqualTo(BigDecimal value) {
            addCriterion("Money_YSH <>", value, "moneyYsh");
            return (Criteria) this;
        }

        public Criteria andMoneyYshGreaterThan(BigDecimal value) {
            addCriterion("Money_YSH >", value, "moneyYsh");
            return (Criteria) this;
        }

        public Criteria andMoneyYshGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_YSH >=", value, "moneyYsh");
            return (Criteria) this;
        }

        public Criteria andMoneyYshLessThan(BigDecimal value) {
            addCriterion("Money_YSH <", value, "moneyYsh");
            return (Criteria) this;
        }

        public Criteria andMoneyYshLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_YSH <=", value, "moneyYsh");
            return (Criteria) this;
        }

        public Criteria andMoneyYshIn(List<BigDecimal> values) {
            addCriterion("Money_YSH in", values, "moneyYsh");
            return (Criteria) this;
        }

        public Criteria andMoneyYshNotIn(List<BigDecimal> values) {
            addCriterion("Money_YSH not in", values, "moneyYsh");
            return (Criteria) this;
        }

        public Criteria andMoneyYshBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_YSH between", value1, value2, "moneyYsh");
            return (Criteria) this;
        }

        public Criteria andMoneyYshNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_YSH not between", value1, value2, "moneyYsh");
            return (Criteria) this;
        }

        public Criteria andCountQxzIsNull() {
            addCriterion("Count_QXZ is null");
            return (Criteria) this;
        }

        public Criteria andCountQxzIsNotNull() {
            addCriterion("Count_QXZ is not null");
            return (Criteria) this;
        }

        public Criteria andCountQxzEqualTo(Integer value) {
            addCriterion("Count_QXZ =", value, "countQxz");
            return (Criteria) this;
        }

        public Criteria andCountQxzNotEqualTo(Integer value) {
            addCriterion("Count_QXZ <>", value, "countQxz");
            return (Criteria) this;
        }

        public Criteria andCountQxzGreaterThan(Integer value) {
            addCriterion("Count_QXZ >", value, "countQxz");
            return (Criteria) this;
        }

        public Criteria andCountQxzGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_QXZ >=", value, "countQxz");
            return (Criteria) this;
        }

        public Criteria andCountQxzLessThan(Integer value) {
            addCriterion("Count_QXZ <", value, "countQxz");
            return (Criteria) this;
        }

        public Criteria andCountQxzLessThanOrEqualTo(Integer value) {
            addCriterion("Count_QXZ <=", value, "countQxz");
            return (Criteria) this;
        }

        public Criteria andCountQxzIn(List<Integer> values) {
            addCriterion("Count_QXZ in", values, "countQxz");
            return (Criteria) this;
        }

        public Criteria andCountQxzNotIn(List<Integer> values) {
            addCriterion("Count_QXZ not in", values, "countQxz");
            return (Criteria) this;
        }

        public Criteria andCountQxzBetween(Integer value1, Integer value2) {
            addCriterion("Count_QXZ between", value1, value2, "countQxz");
            return (Criteria) this;
        }

        public Criteria andCountQxzNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_QXZ not between", value1, value2, "countQxz");
            return (Criteria) this;
        }

        public Criteria andMoneyQxzIsNull() {
            addCriterion("Money_QXZ is null");
            return (Criteria) this;
        }

        public Criteria andMoneyQxzIsNotNull() {
            addCriterion("Money_QXZ is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyQxzEqualTo(BigDecimal value) {
            addCriterion("Money_QXZ =", value, "moneyQxz");
            return (Criteria) this;
        }

        public Criteria andMoneyQxzNotEqualTo(BigDecimal value) {
            addCriterion("Money_QXZ <>", value, "moneyQxz");
            return (Criteria) this;
        }

        public Criteria andMoneyQxzGreaterThan(BigDecimal value) {
            addCriterion("Money_QXZ >", value, "moneyQxz");
            return (Criteria) this;
        }

        public Criteria andMoneyQxzGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_QXZ >=", value, "moneyQxz");
            return (Criteria) this;
        }

        public Criteria andMoneyQxzLessThan(BigDecimal value) {
            addCriterion("Money_QXZ <", value, "moneyQxz");
            return (Criteria) this;
        }

        public Criteria andMoneyQxzLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_QXZ <=", value, "moneyQxz");
            return (Criteria) this;
        }

        public Criteria andMoneyQxzIn(List<BigDecimal> values) {
            addCriterion("Money_QXZ in", values, "moneyQxz");
            return (Criteria) this;
        }

        public Criteria andMoneyQxzNotIn(List<BigDecimal> values) {
            addCriterion("Money_QXZ not in", values, "moneyQxz");
            return (Criteria) this;
        }

        public Criteria andMoneyQxzBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_QXZ between", value1, value2, "moneyQxz");
            return (Criteria) this;
        }

        public Criteria andMoneyQxzNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_QXZ not between", value1, value2, "moneyQxz");
            return (Criteria) this;
        }

        public Criteria andCountQxIsNull() {
            addCriterion("Count_QX is null");
            return (Criteria) this;
        }

        public Criteria andCountQxIsNotNull() {
            addCriterion("Count_QX is not null");
            return (Criteria) this;
        }

        public Criteria andCountQxEqualTo(Integer value) {
            addCriterion("Count_QX =", value, "countQx");
            return (Criteria) this;
        }

        public Criteria andCountQxNotEqualTo(Integer value) {
            addCriterion("Count_QX <>", value, "countQx");
            return (Criteria) this;
        }

        public Criteria andCountQxGreaterThan(Integer value) {
            addCriterion("Count_QX >", value, "countQx");
            return (Criteria) this;
        }

        public Criteria andCountQxGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_QX >=", value, "countQx");
            return (Criteria) this;
        }

        public Criteria andCountQxLessThan(Integer value) {
            addCriterion("Count_QX <", value, "countQx");
            return (Criteria) this;
        }

        public Criteria andCountQxLessThanOrEqualTo(Integer value) {
            addCriterion("Count_QX <=", value, "countQx");
            return (Criteria) this;
        }

        public Criteria andCountQxIn(List<Integer> values) {
            addCriterion("Count_QX in", values, "countQx");
            return (Criteria) this;
        }

        public Criteria andCountQxNotIn(List<Integer> values) {
            addCriterion("Count_QX not in", values, "countQx");
            return (Criteria) this;
        }

        public Criteria andCountQxBetween(Integer value1, Integer value2) {
            addCriterion("Count_QX between", value1, value2, "countQx");
            return (Criteria) this;
        }

        public Criteria andCountQxNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_QX not between", value1, value2, "countQx");
            return (Criteria) this;
        }

        public Criteria andMoneyQxIsNull() {
            addCriterion("Money_QX is null");
            return (Criteria) this;
        }

        public Criteria andMoneyQxIsNotNull() {
            addCriterion("Money_QX is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyQxEqualTo(BigDecimal value) {
            addCriterion("Money_QX =", value, "moneyQx");
            return (Criteria) this;
        }

        public Criteria andMoneyQxNotEqualTo(BigDecimal value) {
            addCriterion("Money_QX <>", value, "moneyQx");
            return (Criteria) this;
        }

        public Criteria andMoneyQxGreaterThan(BigDecimal value) {
            addCriterion("Money_QX >", value, "moneyQx");
            return (Criteria) this;
        }

        public Criteria andMoneyQxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_QX >=", value, "moneyQx");
            return (Criteria) this;
        }

        public Criteria andMoneyQxLessThan(BigDecimal value) {
            addCriterion("Money_QX <", value, "moneyQx");
            return (Criteria) this;
        }

        public Criteria andMoneyQxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_QX <=", value, "moneyQx");
            return (Criteria) this;
        }

        public Criteria andMoneyQxIn(List<BigDecimal> values) {
            addCriterion("Money_QX in", values, "moneyQx");
            return (Criteria) this;
        }

        public Criteria andMoneyQxNotIn(List<BigDecimal> values) {
            addCriterion("Money_QX not in", values, "moneyQx");
            return (Criteria) this;
        }

        public Criteria andMoneyQxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_QX between", value1, value2, "moneyQx");
            return (Criteria) this;
        }

        public Criteria andMoneyQxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_QX not between", value1, value2, "moneyQx");
            return (Criteria) this;
        }

        public Criteria andCountThsqIsNull() {
            addCriterion("Count_THSQ is null");
            return (Criteria) this;
        }

        public Criteria andCountThsqIsNotNull() {
            addCriterion("Count_THSQ is not null");
            return (Criteria) this;
        }

        public Criteria andCountThsqEqualTo(Integer value) {
            addCriterion("Count_THSQ =", value, "countThsq");
            return (Criteria) this;
        }

        public Criteria andCountThsqNotEqualTo(Integer value) {
            addCriterion("Count_THSQ <>", value, "countThsq");
            return (Criteria) this;
        }

        public Criteria andCountThsqGreaterThan(Integer value) {
            addCriterion("Count_THSQ >", value, "countThsq");
            return (Criteria) this;
        }

        public Criteria andCountThsqGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_THSQ >=", value, "countThsq");
            return (Criteria) this;
        }

        public Criteria andCountThsqLessThan(Integer value) {
            addCriterion("Count_THSQ <", value, "countThsq");
            return (Criteria) this;
        }

        public Criteria andCountThsqLessThanOrEqualTo(Integer value) {
            addCriterion("Count_THSQ <=", value, "countThsq");
            return (Criteria) this;
        }

        public Criteria andCountThsqIn(List<Integer> values) {
            addCriterion("Count_THSQ in", values, "countThsq");
            return (Criteria) this;
        }

        public Criteria andCountThsqNotIn(List<Integer> values) {
            addCriterion("Count_THSQ not in", values, "countThsq");
            return (Criteria) this;
        }

        public Criteria andCountThsqBetween(Integer value1, Integer value2) {
            addCriterion("Count_THSQ between", value1, value2, "countThsq");
            return (Criteria) this;
        }

        public Criteria andCountThsqNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_THSQ not between", value1, value2, "countThsq");
            return (Criteria) this;
        }

        public Criteria andMoneyThsqIsNull() {
            addCriterion("Money_THSQ is null");
            return (Criteria) this;
        }

        public Criteria andMoneyThsqIsNotNull() {
            addCriterion("Money_THSQ is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyThsqEqualTo(BigDecimal value) {
            addCriterion("Money_THSQ =", value, "moneyThsq");
            return (Criteria) this;
        }

        public Criteria andMoneyThsqNotEqualTo(BigDecimal value) {
            addCriterion("Money_THSQ <>", value, "moneyThsq");
            return (Criteria) this;
        }

        public Criteria andMoneyThsqGreaterThan(BigDecimal value) {
            addCriterion("Money_THSQ >", value, "moneyThsq");
            return (Criteria) this;
        }

        public Criteria andMoneyThsqGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_THSQ >=", value, "moneyThsq");
            return (Criteria) this;
        }

        public Criteria andMoneyThsqLessThan(BigDecimal value) {
            addCriterion("Money_THSQ <", value, "moneyThsq");
            return (Criteria) this;
        }

        public Criteria andMoneyThsqLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_THSQ <=", value, "moneyThsq");
            return (Criteria) this;
        }

        public Criteria andMoneyThsqIn(List<BigDecimal> values) {
            addCriterion("Money_THSQ in", values, "moneyThsq");
            return (Criteria) this;
        }

        public Criteria andMoneyThsqNotIn(List<BigDecimal> values) {
            addCriterion("Money_THSQ not in", values, "moneyThsq");
            return (Criteria) this;
        }

        public Criteria andMoneyThsqBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_THSQ between", value1, value2, "moneyThsq");
            return (Criteria) this;
        }

        public Criteria andMoneyThsqNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_THSQ not between", value1, value2, "moneyThsq");
            return (Criteria) this;
        }

        public Criteria andCountThzIsNull() {
            addCriterion("Count_THZ is null");
            return (Criteria) this;
        }

        public Criteria andCountThzIsNotNull() {
            addCriterion("Count_THZ is not null");
            return (Criteria) this;
        }

        public Criteria andCountThzEqualTo(Integer value) {
            addCriterion("Count_THZ =", value, "countThz");
            return (Criteria) this;
        }

        public Criteria andCountThzNotEqualTo(Integer value) {
            addCriterion("Count_THZ <>", value, "countThz");
            return (Criteria) this;
        }

        public Criteria andCountThzGreaterThan(Integer value) {
            addCriterion("Count_THZ >", value, "countThz");
            return (Criteria) this;
        }

        public Criteria andCountThzGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_THZ >=", value, "countThz");
            return (Criteria) this;
        }

        public Criteria andCountThzLessThan(Integer value) {
            addCriterion("Count_THZ <", value, "countThz");
            return (Criteria) this;
        }

        public Criteria andCountThzLessThanOrEqualTo(Integer value) {
            addCriterion("Count_THZ <=", value, "countThz");
            return (Criteria) this;
        }

        public Criteria andCountThzIn(List<Integer> values) {
            addCriterion("Count_THZ in", values, "countThz");
            return (Criteria) this;
        }

        public Criteria andCountThzNotIn(List<Integer> values) {
            addCriterion("Count_THZ not in", values, "countThz");
            return (Criteria) this;
        }

        public Criteria andCountThzBetween(Integer value1, Integer value2) {
            addCriterion("Count_THZ between", value1, value2, "countThz");
            return (Criteria) this;
        }

        public Criteria andCountThzNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_THZ not between", value1, value2, "countThz");
            return (Criteria) this;
        }

        public Criteria andMoneyThzIsNull() {
            addCriterion("Money_THZ is null");
            return (Criteria) this;
        }

        public Criteria andMoneyThzIsNotNull() {
            addCriterion("Money_THZ is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyThzEqualTo(BigDecimal value) {
            addCriterion("Money_THZ =", value, "moneyThz");
            return (Criteria) this;
        }

        public Criteria andMoneyThzNotEqualTo(BigDecimal value) {
            addCriterion("Money_THZ <>", value, "moneyThz");
            return (Criteria) this;
        }

        public Criteria andMoneyThzGreaterThan(BigDecimal value) {
            addCriterion("Money_THZ >", value, "moneyThz");
            return (Criteria) this;
        }

        public Criteria andMoneyThzGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_THZ >=", value, "moneyThz");
            return (Criteria) this;
        }

        public Criteria andMoneyThzLessThan(BigDecimal value) {
            addCriterion("Money_THZ <", value, "moneyThz");
            return (Criteria) this;
        }

        public Criteria andMoneyThzLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_THZ <=", value, "moneyThz");
            return (Criteria) this;
        }

        public Criteria andMoneyThzIn(List<BigDecimal> values) {
            addCriterion("Money_THZ in", values, "moneyThz");
            return (Criteria) this;
        }

        public Criteria andMoneyThzNotIn(List<BigDecimal> values) {
            addCriterion("Money_THZ not in", values, "moneyThz");
            return (Criteria) this;
        }

        public Criteria andMoneyThzBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_THZ between", value1, value2, "moneyThz");
            return (Criteria) this;
        }

        public Criteria andMoneyThzNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_THZ not between", value1, value2, "moneyThz");
            return (Criteria) this;
        }

        public Criteria andCountThIsNull() {
            addCriterion("Count_TH is null");
            return (Criteria) this;
        }

        public Criteria andCountThIsNotNull() {
            addCriterion("Count_TH is not null");
            return (Criteria) this;
        }

        public Criteria andCountThEqualTo(Integer value) {
            addCriterion("Count_TH =", value, "countTh");
            return (Criteria) this;
        }

        public Criteria andCountThNotEqualTo(Integer value) {
            addCriterion("Count_TH <>", value, "countTh");
            return (Criteria) this;
        }

        public Criteria andCountThGreaterThan(Integer value) {
            addCriterion("Count_TH >", value, "countTh");
            return (Criteria) this;
        }

        public Criteria andCountThGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_TH >=", value, "countTh");
            return (Criteria) this;
        }

        public Criteria andCountThLessThan(Integer value) {
            addCriterion("Count_TH <", value, "countTh");
            return (Criteria) this;
        }

        public Criteria andCountThLessThanOrEqualTo(Integer value) {
            addCriterion("Count_TH <=", value, "countTh");
            return (Criteria) this;
        }

        public Criteria andCountThIn(List<Integer> values) {
            addCriterion("Count_TH in", values, "countTh");
            return (Criteria) this;
        }

        public Criteria andCountThNotIn(List<Integer> values) {
            addCriterion("Count_TH not in", values, "countTh");
            return (Criteria) this;
        }

        public Criteria andCountThBetween(Integer value1, Integer value2) {
            addCriterion("Count_TH between", value1, value2, "countTh");
            return (Criteria) this;
        }

        public Criteria andCountThNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_TH not between", value1, value2, "countTh");
            return (Criteria) this;
        }

        public Criteria andMoneyThIsNull() {
            addCriterion("Money_TH is null");
            return (Criteria) this;
        }

        public Criteria andMoneyThIsNotNull() {
            addCriterion("Money_TH is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyThEqualTo(BigDecimal value) {
            addCriterion("Money_TH =", value, "moneyTh");
            return (Criteria) this;
        }

        public Criteria andMoneyThNotEqualTo(BigDecimal value) {
            addCriterion("Money_TH <>", value, "moneyTh");
            return (Criteria) this;
        }

        public Criteria andMoneyThGreaterThan(BigDecimal value) {
            addCriterion("Money_TH >", value, "moneyTh");
            return (Criteria) this;
        }

        public Criteria andMoneyThGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_TH >=", value, "moneyTh");
            return (Criteria) this;
        }

        public Criteria andMoneyThLessThan(BigDecimal value) {
            addCriterion("Money_TH <", value, "moneyTh");
            return (Criteria) this;
        }

        public Criteria andMoneyThLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_TH <=", value, "moneyTh");
            return (Criteria) this;
        }

        public Criteria andMoneyThIn(List<BigDecimal> values) {
            addCriterion("Money_TH in", values, "moneyTh");
            return (Criteria) this;
        }

        public Criteria andMoneyThNotIn(List<BigDecimal> values) {
            addCriterion("Money_TH not in", values, "moneyTh");
            return (Criteria) this;
        }

        public Criteria andMoneyThBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_TH between", value1, value2, "moneyTh");
            return (Criteria) this;
        }

        public Criteria andMoneyThNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_TH not between", value1, value2, "moneyTh");
            return (Criteria) this;
        }

        public Criteria andCountTkIsNull() {
            addCriterion("Count_TK is null");
            return (Criteria) this;
        }

        public Criteria andCountTkIsNotNull() {
            addCriterion("Count_TK is not null");
            return (Criteria) this;
        }

        public Criteria andCountTkEqualTo(Integer value) {
            addCriterion("Count_TK =", value, "countTk");
            return (Criteria) this;
        }

        public Criteria andCountTkNotEqualTo(Integer value) {
            addCriterion("Count_TK <>", value, "countTk");
            return (Criteria) this;
        }

        public Criteria andCountTkGreaterThan(Integer value) {
            addCriterion("Count_TK >", value, "countTk");
            return (Criteria) this;
        }

        public Criteria andCountTkGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_TK >=", value, "countTk");
            return (Criteria) this;
        }

        public Criteria andCountTkLessThan(Integer value) {
            addCriterion("Count_TK <", value, "countTk");
            return (Criteria) this;
        }

        public Criteria andCountTkLessThanOrEqualTo(Integer value) {
            addCriterion("Count_TK <=", value, "countTk");
            return (Criteria) this;
        }

        public Criteria andCountTkIn(List<Integer> values) {
            addCriterion("Count_TK in", values, "countTk");
            return (Criteria) this;
        }

        public Criteria andCountTkNotIn(List<Integer> values) {
            addCriterion("Count_TK not in", values, "countTk");
            return (Criteria) this;
        }

        public Criteria andCountTkBetween(Integer value1, Integer value2) {
            addCriterion("Count_TK between", value1, value2, "countTk");
            return (Criteria) this;
        }

        public Criteria andCountTkNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_TK not between", value1, value2, "countTk");
            return (Criteria) this;
        }

        public Criteria andMoneyTkIsNull() {
            addCriterion("Money_TK is null");
            return (Criteria) this;
        }

        public Criteria andMoneyTkIsNotNull() {
            addCriterion("Money_TK is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyTkEqualTo(BigDecimal value) {
            addCriterion("Money_TK =", value, "moneyTk");
            return (Criteria) this;
        }

        public Criteria andMoneyTkNotEqualTo(BigDecimal value) {
            addCriterion("Money_TK <>", value, "moneyTk");
            return (Criteria) this;
        }

        public Criteria andMoneyTkGreaterThan(BigDecimal value) {
            addCriterion("Money_TK >", value, "moneyTk");
            return (Criteria) this;
        }

        public Criteria andMoneyTkGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_TK >=", value, "moneyTk");
            return (Criteria) this;
        }

        public Criteria andMoneyTkLessThan(BigDecimal value) {
            addCriterion("Money_TK <", value, "moneyTk");
            return (Criteria) this;
        }

        public Criteria andMoneyTkLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_TK <=", value, "moneyTk");
            return (Criteria) this;
        }

        public Criteria andMoneyTkIn(List<BigDecimal> values) {
            addCriterion("Money_TK in", values, "moneyTk");
            return (Criteria) this;
        }

        public Criteria andMoneyTkNotIn(List<BigDecimal> values) {
            addCriterion("Money_TK not in", values, "moneyTk");
            return (Criteria) this;
        }

        public Criteria andMoneyTkBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_TK between", value1, value2, "moneyTk");
            return (Criteria) this;
        }

        public Criteria andMoneyTkNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_TK not between", value1, value2, "moneyTk");
            return (Criteria) this;
        }

        public Criteria andCountHhsqIsNull() {
            addCriterion("Count_HHSQ is null");
            return (Criteria) this;
        }

        public Criteria andCountHhsqIsNotNull() {
            addCriterion("Count_HHSQ is not null");
            return (Criteria) this;
        }

        public Criteria andCountHhsqEqualTo(Integer value) {
            addCriterion("Count_HHSQ =", value, "countHhsq");
            return (Criteria) this;
        }

        public Criteria andCountHhsqNotEqualTo(Integer value) {
            addCriterion("Count_HHSQ <>", value, "countHhsq");
            return (Criteria) this;
        }

        public Criteria andCountHhsqGreaterThan(Integer value) {
            addCriterion("Count_HHSQ >", value, "countHhsq");
            return (Criteria) this;
        }

        public Criteria andCountHhsqGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_HHSQ >=", value, "countHhsq");
            return (Criteria) this;
        }

        public Criteria andCountHhsqLessThan(Integer value) {
            addCriterion("Count_HHSQ <", value, "countHhsq");
            return (Criteria) this;
        }

        public Criteria andCountHhsqLessThanOrEqualTo(Integer value) {
            addCriterion("Count_HHSQ <=", value, "countHhsq");
            return (Criteria) this;
        }

        public Criteria andCountHhsqIn(List<Integer> values) {
            addCriterion("Count_HHSQ in", values, "countHhsq");
            return (Criteria) this;
        }

        public Criteria andCountHhsqNotIn(List<Integer> values) {
            addCriterion("Count_HHSQ not in", values, "countHhsq");
            return (Criteria) this;
        }

        public Criteria andCountHhsqBetween(Integer value1, Integer value2) {
            addCriterion("Count_HHSQ between", value1, value2, "countHhsq");
            return (Criteria) this;
        }

        public Criteria andCountHhsqNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_HHSQ not between", value1, value2, "countHhsq");
            return (Criteria) this;
        }

        public Criteria andMoneyHhsqIsNull() {
            addCriterion("Money_HHSQ is null");
            return (Criteria) this;
        }

        public Criteria andMoneyHhsqIsNotNull() {
            addCriterion("Money_HHSQ is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyHhsqEqualTo(BigDecimal value) {
            addCriterion("Money_HHSQ =", value, "moneyHhsq");
            return (Criteria) this;
        }

        public Criteria andMoneyHhsqNotEqualTo(BigDecimal value) {
            addCriterion("Money_HHSQ <>", value, "moneyHhsq");
            return (Criteria) this;
        }

        public Criteria andMoneyHhsqGreaterThan(BigDecimal value) {
            addCriterion("Money_HHSQ >", value, "moneyHhsq");
            return (Criteria) this;
        }

        public Criteria andMoneyHhsqGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_HHSQ >=", value, "moneyHhsq");
            return (Criteria) this;
        }

        public Criteria andMoneyHhsqLessThan(BigDecimal value) {
            addCriterion("Money_HHSQ <", value, "moneyHhsq");
            return (Criteria) this;
        }

        public Criteria andMoneyHhsqLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_HHSQ <=", value, "moneyHhsq");
            return (Criteria) this;
        }

        public Criteria andMoneyHhsqIn(List<BigDecimal> values) {
            addCriterion("Money_HHSQ in", values, "moneyHhsq");
            return (Criteria) this;
        }

        public Criteria andMoneyHhsqNotIn(List<BigDecimal> values) {
            addCriterion("Money_HHSQ not in", values, "moneyHhsq");
            return (Criteria) this;
        }

        public Criteria andMoneyHhsqBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_HHSQ between", value1, value2, "moneyHhsq");
            return (Criteria) this;
        }

        public Criteria andMoneyHhsqNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_HHSQ not between", value1, value2, "moneyHhsq");
            return (Criteria) this;
        }

        public Criteria andCountMjshIsNull() {
            addCriterion("Count_MJSH is null");
            return (Criteria) this;
        }

        public Criteria andCountMjshIsNotNull() {
            addCriterion("Count_MJSH is not null");
            return (Criteria) this;
        }

        public Criteria andCountMjshEqualTo(Integer value) {
            addCriterion("Count_MJSH =", value, "countMjsh");
            return (Criteria) this;
        }

        public Criteria andCountMjshNotEqualTo(Integer value) {
            addCriterion("Count_MJSH <>", value, "countMjsh");
            return (Criteria) this;
        }

        public Criteria andCountMjshGreaterThan(Integer value) {
            addCriterion("Count_MJSH >", value, "countMjsh");
            return (Criteria) this;
        }

        public Criteria andCountMjshGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_MJSH >=", value, "countMjsh");
            return (Criteria) this;
        }

        public Criteria andCountMjshLessThan(Integer value) {
            addCriterion("Count_MJSH <", value, "countMjsh");
            return (Criteria) this;
        }

        public Criteria andCountMjshLessThanOrEqualTo(Integer value) {
            addCriterion("Count_MJSH <=", value, "countMjsh");
            return (Criteria) this;
        }

        public Criteria andCountMjshIn(List<Integer> values) {
            addCriterion("Count_MJSH in", values, "countMjsh");
            return (Criteria) this;
        }

        public Criteria andCountMjshNotIn(List<Integer> values) {
            addCriterion("Count_MJSH not in", values, "countMjsh");
            return (Criteria) this;
        }

        public Criteria andCountMjshBetween(Integer value1, Integer value2) {
            addCriterion("Count_MJSH between", value1, value2, "countMjsh");
            return (Criteria) this;
        }

        public Criteria andCountMjshNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_MJSH not between", value1, value2, "countMjsh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjshIsNull() {
            addCriterion("Money_MJSH is null");
            return (Criteria) this;
        }

        public Criteria andMoneyMjshIsNotNull() {
            addCriterion("Money_MJSH is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyMjshEqualTo(BigDecimal value) {
            addCriterion("Money_MJSH =", value, "moneyMjsh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjshNotEqualTo(BigDecimal value) {
            addCriterion("Money_MJSH <>", value, "moneyMjsh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjshGreaterThan(BigDecimal value) {
            addCriterion("Money_MJSH >", value, "moneyMjsh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjshGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_MJSH >=", value, "moneyMjsh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjshLessThan(BigDecimal value) {
            addCriterion("Money_MJSH <", value, "moneyMjsh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjshLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_MJSH <=", value, "moneyMjsh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjshIn(List<BigDecimal> values) {
            addCriterion("Money_MJSH in", values, "moneyMjsh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjshNotIn(List<BigDecimal> values) {
            addCriterion("Money_MJSH not in", values, "moneyMjsh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjshBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_MJSH between", value1, value2, "moneyMjsh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjshNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_MJSH not between", value1, value2, "moneyMjsh");
            return (Criteria) this;
        }

        public Criteria andCountMjfhIsNull() {
            addCriterion("Count_MJFH is null");
            return (Criteria) this;
        }

        public Criteria andCountMjfhIsNotNull() {
            addCriterion("Count_MJFH is not null");
            return (Criteria) this;
        }

        public Criteria andCountMjfhEqualTo(Integer value) {
            addCriterion("Count_MJFH =", value, "countMjfh");
            return (Criteria) this;
        }

        public Criteria andCountMjfhNotEqualTo(Integer value) {
            addCriterion("Count_MJFH <>", value, "countMjfh");
            return (Criteria) this;
        }

        public Criteria andCountMjfhGreaterThan(Integer value) {
            addCriterion("Count_MJFH >", value, "countMjfh");
            return (Criteria) this;
        }

        public Criteria andCountMjfhGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_MJFH >=", value, "countMjfh");
            return (Criteria) this;
        }

        public Criteria andCountMjfhLessThan(Integer value) {
            addCriterion("Count_MJFH <", value, "countMjfh");
            return (Criteria) this;
        }

        public Criteria andCountMjfhLessThanOrEqualTo(Integer value) {
            addCriterion("Count_MJFH <=", value, "countMjfh");
            return (Criteria) this;
        }

        public Criteria andCountMjfhIn(List<Integer> values) {
            addCriterion("Count_MJFH in", values, "countMjfh");
            return (Criteria) this;
        }

        public Criteria andCountMjfhNotIn(List<Integer> values) {
            addCriterion("Count_MJFH not in", values, "countMjfh");
            return (Criteria) this;
        }

        public Criteria andCountMjfhBetween(Integer value1, Integer value2) {
            addCriterion("Count_MJFH between", value1, value2, "countMjfh");
            return (Criteria) this;
        }

        public Criteria andCountMjfhNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_MJFH not between", value1, value2, "countMjfh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjfhIsNull() {
            addCriterion("Money_MJFH is null");
            return (Criteria) this;
        }

        public Criteria andMoneyMjfhIsNotNull() {
            addCriterion("Money_MJFH is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyMjfhEqualTo(BigDecimal value) {
            addCriterion("Money_MJFH =", value, "moneyMjfh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjfhNotEqualTo(BigDecimal value) {
            addCriterion("Money_MJFH <>", value, "moneyMjfh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjfhGreaterThan(BigDecimal value) {
            addCriterion("Money_MJFH >", value, "moneyMjfh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjfhGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_MJFH >=", value, "moneyMjfh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjfhLessThan(BigDecimal value) {
            addCriterion("Money_MJFH <", value, "moneyMjfh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjfhLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_MJFH <=", value, "moneyMjfh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjfhIn(List<BigDecimal> values) {
            addCriterion("Money_MJFH in", values, "moneyMjfh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjfhNotIn(List<BigDecimal> values) {
            addCriterion("Money_MJFH not in", values, "moneyMjfh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjfhBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_MJFH between", value1, value2, "moneyMjfh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjfhNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_MJFH not between", value1, value2, "moneyMjfh");
            return (Criteria) this;
        }

        public Criteria andCountBuyshIsNull() {
            addCriterion("Count_BuySH is null");
            return (Criteria) this;
        }

        public Criteria andCountBuyshIsNotNull() {
            addCriterion("Count_BuySH is not null");
            return (Criteria) this;
        }

        public Criteria andCountBuyshEqualTo(Integer value) {
            addCriterion("Count_BuySH =", value, "countBuysh");
            return (Criteria) this;
        }

        public Criteria andCountBuyshNotEqualTo(Integer value) {
            addCriterion("Count_BuySH <>", value, "countBuysh");
            return (Criteria) this;
        }

        public Criteria andCountBuyshGreaterThan(Integer value) {
            addCriterion("Count_BuySH >", value, "countBuysh");
            return (Criteria) this;
        }

        public Criteria andCountBuyshGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_BuySH >=", value, "countBuysh");
            return (Criteria) this;
        }

        public Criteria andCountBuyshLessThan(Integer value) {
            addCriterion("Count_BuySH <", value, "countBuysh");
            return (Criteria) this;
        }

        public Criteria andCountBuyshLessThanOrEqualTo(Integer value) {
            addCriterion("Count_BuySH <=", value, "countBuysh");
            return (Criteria) this;
        }

        public Criteria andCountBuyshIn(List<Integer> values) {
            addCriterion("Count_BuySH in", values, "countBuysh");
            return (Criteria) this;
        }

        public Criteria andCountBuyshNotIn(List<Integer> values) {
            addCriterion("Count_BuySH not in", values, "countBuysh");
            return (Criteria) this;
        }

        public Criteria andCountBuyshBetween(Integer value1, Integer value2) {
            addCriterion("Count_BuySH between", value1, value2, "countBuysh");
            return (Criteria) this;
        }

        public Criteria andCountBuyshNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_BuySH not between", value1, value2, "countBuysh");
            return (Criteria) this;
        }

        public Criteria andMoneyBuyshIsNull() {
            addCriterion("Money_BuySH is null");
            return (Criteria) this;
        }

        public Criteria andMoneyBuyshIsNotNull() {
            addCriterion("Money_BuySH is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyBuyshEqualTo(BigDecimal value) {
            addCriterion("Money_BuySH =", value, "moneyBuysh");
            return (Criteria) this;
        }

        public Criteria andMoneyBuyshNotEqualTo(BigDecimal value) {
            addCriterion("Money_BuySH <>", value, "moneyBuysh");
            return (Criteria) this;
        }

        public Criteria andMoneyBuyshGreaterThan(BigDecimal value) {
            addCriterion("Money_BuySH >", value, "moneyBuysh");
            return (Criteria) this;
        }

        public Criteria andMoneyBuyshGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_BuySH >=", value, "moneyBuysh");
            return (Criteria) this;
        }

        public Criteria andMoneyBuyshLessThan(BigDecimal value) {
            addCriterion("Money_BuySH <", value, "moneyBuysh");
            return (Criteria) this;
        }

        public Criteria andMoneyBuyshLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_BuySH <=", value, "moneyBuysh");
            return (Criteria) this;
        }

        public Criteria andMoneyBuyshIn(List<BigDecimal> values) {
            addCriterion("Money_BuySH in", values, "moneyBuysh");
            return (Criteria) this;
        }

        public Criteria andMoneyBuyshNotIn(List<BigDecimal> values) {
            addCriterion("Money_BuySH not in", values, "moneyBuysh");
            return (Criteria) this;
        }

        public Criteria andMoneyBuyshBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_BuySH between", value1, value2, "moneyBuysh");
            return (Criteria) this;
        }

        public Criteria andMoneyBuyshNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_BuySH not between", value1, value2, "moneyBuysh");
            return (Criteria) this;
        }

        public Criteria andCountHhIsNull() {
            addCriterion("Count_HH is null");
            return (Criteria) this;
        }

        public Criteria andCountHhIsNotNull() {
            addCriterion("Count_HH is not null");
            return (Criteria) this;
        }

        public Criteria andCountHhEqualTo(Integer value) {
            addCriterion("Count_HH =", value, "countHh");
            return (Criteria) this;
        }

        public Criteria andCountHhNotEqualTo(Integer value) {
            addCriterion("Count_HH <>", value, "countHh");
            return (Criteria) this;
        }

        public Criteria andCountHhGreaterThan(Integer value) {
            addCriterion("Count_HH >", value, "countHh");
            return (Criteria) this;
        }

        public Criteria andCountHhGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_HH >=", value, "countHh");
            return (Criteria) this;
        }

        public Criteria andCountHhLessThan(Integer value) {
            addCriterion("Count_HH <", value, "countHh");
            return (Criteria) this;
        }

        public Criteria andCountHhLessThanOrEqualTo(Integer value) {
            addCriterion("Count_HH <=", value, "countHh");
            return (Criteria) this;
        }

        public Criteria andCountHhIn(List<Integer> values) {
            addCriterion("Count_HH in", values, "countHh");
            return (Criteria) this;
        }

        public Criteria andCountHhNotIn(List<Integer> values) {
            addCriterion("Count_HH not in", values, "countHh");
            return (Criteria) this;
        }

        public Criteria andCountHhBetween(Integer value1, Integer value2) {
            addCriterion("Count_HH between", value1, value2, "countHh");
            return (Criteria) this;
        }

        public Criteria andCountHhNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_HH not between", value1, value2, "countHh");
            return (Criteria) this;
        }

        public Criteria andMoneyHhIsNull() {
            addCriterion("Money_HH is null");
            return (Criteria) this;
        }

        public Criteria andMoneyHhIsNotNull() {
            addCriterion("Money_HH is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyHhEqualTo(BigDecimal value) {
            addCriterion("Money_HH =", value, "moneyHh");
            return (Criteria) this;
        }

        public Criteria andMoneyHhNotEqualTo(BigDecimal value) {
            addCriterion("Money_HH <>", value, "moneyHh");
            return (Criteria) this;
        }

        public Criteria andMoneyHhGreaterThan(BigDecimal value) {
            addCriterion("Money_HH >", value, "moneyHh");
            return (Criteria) this;
        }

        public Criteria andMoneyHhGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_HH >=", value, "moneyHh");
            return (Criteria) this;
        }

        public Criteria andMoneyHhLessThan(BigDecimal value) {
            addCriterion("Money_HH <", value, "moneyHh");
            return (Criteria) this;
        }

        public Criteria andMoneyHhLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_HH <=", value, "moneyHh");
            return (Criteria) this;
        }

        public Criteria andMoneyHhIn(List<BigDecimal> values) {
            addCriterion("Money_HH in", values, "moneyHh");
            return (Criteria) this;
        }

        public Criteria andMoneyHhNotIn(List<BigDecimal> values) {
            addCriterion("Money_HH not in", values, "moneyHh");
            return (Criteria) this;
        }

        public Criteria andMoneyHhBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_HH between", value1, value2, "moneyHh");
            return (Criteria) this;
        }

        public Criteria andMoneyHhNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_HH not between", value1, value2, "moneyHh");
            return (Criteria) this;
        }

        public Criteria andCountMjbhIsNull() {
            addCriterion("Count_MJBH is null");
            return (Criteria) this;
        }

        public Criteria andCountMjbhIsNotNull() {
            addCriterion("Count_MJBH is not null");
            return (Criteria) this;
        }

        public Criteria andCountMjbhEqualTo(Integer value) {
            addCriterion("Count_MJBH =", value, "countMjbh");
            return (Criteria) this;
        }

        public Criteria andCountMjbhNotEqualTo(Integer value) {
            addCriterion("Count_MJBH <>", value, "countMjbh");
            return (Criteria) this;
        }

        public Criteria andCountMjbhGreaterThan(Integer value) {
            addCriterion("Count_MJBH >", value, "countMjbh");
            return (Criteria) this;
        }

        public Criteria andCountMjbhGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_MJBH >=", value, "countMjbh");
            return (Criteria) this;
        }

        public Criteria andCountMjbhLessThan(Integer value) {
            addCriterion("Count_MJBH <", value, "countMjbh");
            return (Criteria) this;
        }

        public Criteria andCountMjbhLessThanOrEqualTo(Integer value) {
            addCriterion("Count_MJBH <=", value, "countMjbh");
            return (Criteria) this;
        }

        public Criteria andCountMjbhIn(List<Integer> values) {
            addCriterion("Count_MJBH in", values, "countMjbh");
            return (Criteria) this;
        }

        public Criteria andCountMjbhNotIn(List<Integer> values) {
            addCriterion("Count_MJBH not in", values, "countMjbh");
            return (Criteria) this;
        }

        public Criteria andCountMjbhBetween(Integer value1, Integer value2) {
            addCriterion("Count_MJBH between", value1, value2, "countMjbh");
            return (Criteria) this;
        }

        public Criteria andCountMjbhNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_MJBH not between", value1, value2, "countMjbh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjbhIsNull() {
            addCriterion("Money_MJBH is null");
            return (Criteria) this;
        }

        public Criteria andMoneyMjbhIsNotNull() {
            addCriterion("Money_MJBH is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyMjbhEqualTo(BigDecimal value) {
            addCriterion("Money_MJBH =", value, "moneyMjbh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjbhNotEqualTo(BigDecimal value) {
            addCriterion("Money_MJBH <>", value, "moneyMjbh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjbhGreaterThan(BigDecimal value) {
            addCriterion("Money_MJBH >", value, "moneyMjbh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjbhGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_MJBH >=", value, "moneyMjbh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjbhLessThan(BigDecimal value) {
            addCriterion("Money_MJBH <", value, "moneyMjbh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjbhLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_MJBH <=", value, "moneyMjbh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjbhIn(List<BigDecimal> values) {
            addCriterion("Money_MJBH in", values, "moneyMjbh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjbhNotIn(List<BigDecimal> values) {
            addCriterion("Money_MJBH not in", values, "moneyMjbh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjbhBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_MJBH between", value1, value2, "moneyMjbh");
            return (Criteria) this;
        }

        public Criteria andMoneyMjbhNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_MJBH not between", value1, value2, "moneyMjbh");
            return (Criteria) this;
        }

        public Criteria andCountYpjIsNull() {
            addCriterion("Count_YPJ is null");
            return (Criteria) this;
        }

        public Criteria andCountYpjIsNotNull() {
            addCriterion("Count_YPJ is not null");
            return (Criteria) this;
        }

        public Criteria andCountYpjEqualTo(Integer value) {
            addCriterion("Count_YPJ =", value, "countYpj");
            return (Criteria) this;
        }

        public Criteria andCountYpjNotEqualTo(Integer value) {
            addCriterion("Count_YPJ <>", value, "countYpj");
            return (Criteria) this;
        }

        public Criteria andCountYpjGreaterThan(Integer value) {
            addCriterion("Count_YPJ >", value, "countYpj");
            return (Criteria) this;
        }

        public Criteria andCountYpjGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count_YPJ >=", value, "countYpj");
            return (Criteria) this;
        }

        public Criteria andCountYpjLessThan(Integer value) {
            addCriterion("Count_YPJ <", value, "countYpj");
            return (Criteria) this;
        }

        public Criteria andCountYpjLessThanOrEqualTo(Integer value) {
            addCriterion("Count_YPJ <=", value, "countYpj");
            return (Criteria) this;
        }

        public Criteria andCountYpjIn(List<Integer> values) {
            addCriterion("Count_YPJ in", values, "countYpj");
            return (Criteria) this;
        }

        public Criteria andCountYpjNotIn(List<Integer> values) {
            addCriterion("Count_YPJ not in", values, "countYpj");
            return (Criteria) this;
        }

        public Criteria andCountYpjBetween(Integer value1, Integer value2) {
            addCriterion("Count_YPJ between", value1, value2, "countYpj");
            return (Criteria) this;
        }

        public Criteria andCountYpjNotBetween(Integer value1, Integer value2) {
            addCriterion("Count_YPJ not between", value1, value2, "countYpj");
            return (Criteria) this;
        }

        public Criteria andMoneyYpjIsNull() {
            addCriterion("Money_YPJ is null");
            return (Criteria) this;
        }

        public Criteria andMoneyYpjIsNotNull() {
            addCriterion("Money_YPJ is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyYpjEqualTo(BigDecimal value) {
            addCriterion("Money_YPJ =", value, "moneyYpj");
            return (Criteria) this;
        }

        public Criteria andMoneyYpjNotEqualTo(BigDecimal value) {
            addCriterion("Money_YPJ <>", value, "moneyYpj");
            return (Criteria) this;
        }

        public Criteria andMoneyYpjGreaterThan(BigDecimal value) {
            addCriterion("Money_YPJ >", value, "moneyYpj");
            return (Criteria) this;
        }

        public Criteria andMoneyYpjGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_YPJ >=", value, "moneyYpj");
            return (Criteria) this;
        }

        public Criteria andMoneyYpjLessThan(BigDecimal value) {
            addCriterion("Money_YPJ <", value, "moneyYpj");
            return (Criteria) this;
        }

        public Criteria andMoneyYpjLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Money_YPJ <=", value, "moneyYpj");
            return (Criteria) this;
        }

        public Criteria andMoneyYpjIn(List<BigDecimal> values) {
            addCriterion("Money_YPJ in", values, "moneyYpj");
            return (Criteria) this;
        }

        public Criteria andMoneyYpjNotIn(List<BigDecimal> values) {
            addCriterion("Money_YPJ not in", values, "moneyYpj");
            return (Criteria) this;
        }

        public Criteria andMoneyYpjBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_YPJ between", value1, value2, "moneyYpj");
            return (Criteria) this;
        }

        public Criteria andMoneyYpjNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Money_YPJ not between", value1, value2, "moneyYpj");
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