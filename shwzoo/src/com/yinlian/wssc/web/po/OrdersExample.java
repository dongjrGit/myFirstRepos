package com.yinlian.wssc.web.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrdersExample() {
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

        public Criteria andCodeIsNull() {
            addCriterion("Code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("Code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("Code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("Code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("Code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("Code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("Code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("Code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("Code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("Code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("Code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("Code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("Code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("Code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andGroupcodeIsNull() {
            addCriterion("GroupCode is null");
            return (Criteria) this;
        }

        public Criteria andGroupcodeIsNotNull() {
            addCriterion("GroupCode is not null");
            return (Criteria) this;
        }

        public Criteria andGroupcodeEqualTo(String value) {
            addCriterion("GroupCode =", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeNotEqualTo(String value) {
            addCriterion("GroupCode <>", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeGreaterThan(String value) {
            addCriterion("GroupCode >", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeGreaterThanOrEqualTo(String value) {
            addCriterion("GroupCode >=", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeLessThan(String value) {
            addCriterion("GroupCode <", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeLessThanOrEqualTo(String value) {
            addCriterion("GroupCode <=", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeLike(String value) {
            addCriterion("GroupCode like", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeNotLike(String value) {
            addCriterion("GroupCode not like", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeIn(List<String> values) {
            addCriterion("GroupCode in", values, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeNotIn(List<String> values) {
            addCriterion("GroupCode not in", values, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeBetween(String value1, String value2) {
            addCriterion("GroupCode between", value1, value2, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeNotBetween(String value1, String value2) {
            addCriterion("GroupCode not between", value1, value2, "groupcode");
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

        public Criteria andPriceIsNull() {
            addCriterion("Price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("Price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("Price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("Price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("Price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("Price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("Price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("Price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("Freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("Freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(BigDecimal value) {
            addCriterion("Freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(BigDecimal value) {
            addCriterion("Freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(BigDecimal value) {
            addCriterion("Freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(BigDecimal value) {
            addCriterion("Freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<BigDecimal> values) {
            addCriterion("Freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<BigDecimal> values) {
            addCriterion("Freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Freight not between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNull() {
            addCriterion("PayType is null");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNotNull() {
            addCriterion("PayType is not null");
            return (Criteria) this;
        }

        public Criteria andPaytypeEqualTo(Integer value) {
            addCriterion("PayType =", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotEqualTo(Integer value) {
            addCriterion("PayType <>", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThan(Integer value) {
            addCriterion("PayType >", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("PayType >=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThan(Integer value) {
            addCriterion("PayType <", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThanOrEqualTo(Integer value) {
            addCriterion("PayType <=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeIn(List<Integer> values) {
            addCriterion("PayType in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotIn(List<Integer> values) {
            addCriterion("PayType not in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeBetween(Integer value1, Integer value2) {
            addCriterion("PayType between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotBetween(Integer value1, Integer value2) {
            addCriterion("PayType not between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andActualpayIsNull() {
            addCriterion("ActualPay is null");
            return (Criteria) this;
        }

        public Criteria andActualpayIsNotNull() {
            addCriterion("ActualPay is not null");
            return (Criteria) this;
        }

        public Criteria andActualpayEqualTo(BigDecimal value) {
            addCriterion("ActualPay =", value, "actualpay");
            return (Criteria) this;
        }

        public Criteria andActualpayNotEqualTo(BigDecimal value) {
            addCriterion("ActualPay <>", value, "actualpay");
            return (Criteria) this;
        }

        public Criteria andActualpayGreaterThan(BigDecimal value) {
            addCriterion("ActualPay >", value, "actualpay");
            return (Criteria) this;
        }

        public Criteria andActualpayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ActualPay >=", value, "actualpay");
            return (Criteria) this;
        }

        public Criteria andActualpayLessThan(BigDecimal value) {
            addCriterion("ActualPay <", value, "actualpay");
            return (Criteria) this;
        }

        public Criteria andActualpayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ActualPay <=", value, "actualpay");
            return (Criteria) this;
        }

        public Criteria andActualpayIn(List<BigDecimal> values) {
            addCriterion("ActualPay in", values, "actualpay");
            return (Criteria) this;
        }

        public Criteria andActualpayNotIn(List<BigDecimal> values) {
            addCriterion("ActualPay not in", values, "actualpay");
            return (Criteria) this;
        }

        public Criteria andActualpayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ActualPay between", value1, value2, "actualpay");
            return (Criteria) this;
        }

        public Criteria andActualpayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ActualPay not between", value1, value2, "actualpay");
            return (Criteria) this;
        }

        public Criteria andIsinvoiceIsNull() {
            addCriterion("IsInvoice is null");
            return (Criteria) this;
        }

        public Criteria andIsinvoiceIsNotNull() {
            addCriterion("IsInvoice is not null");
            return (Criteria) this;
        }

        public Criteria andIsinvoiceEqualTo(Integer value) {
            addCriterion("IsInvoice =", value, "isinvoice");
            return (Criteria) this;
        }

        public Criteria andIsinvoiceNotEqualTo(Integer value) {
            addCriterion("IsInvoice <>", value, "isinvoice");
            return (Criteria) this;
        }

        public Criteria andIsinvoiceGreaterThan(Integer value) {
            addCriterion("IsInvoice >", value, "isinvoice");
            return (Criteria) this;
        }

        public Criteria andIsinvoiceGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsInvoice >=", value, "isinvoice");
            return (Criteria) this;
        }

        public Criteria andIsinvoiceLessThan(Integer value) {
            addCriterion("IsInvoice <", value, "isinvoice");
            return (Criteria) this;
        }

        public Criteria andIsinvoiceLessThanOrEqualTo(Integer value) {
            addCriterion("IsInvoice <=", value, "isinvoice");
            return (Criteria) this;
        }

        public Criteria andIsinvoiceIn(List<Integer> values) {
            addCriterion("IsInvoice in", values, "isinvoice");
            return (Criteria) this;
        }

        public Criteria andIsinvoiceNotIn(List<Integer> values) {
            addCriterion("IsInvoice not in", values, "isinvoice");
            return (Criteria) this;
        }

        public Criteria andIsinvoiceBetween(Integer value1, Integer value2) {
            addCriterion("IsInvoice between", value1, value2, "isinvoice");
            return (Criteria) this;
        }

        public Criteria andIsinvoiceNotBetween(Integer value1, Integer value2) {
            addCriterion("IsInvoice not between", value1, value2, "isinvoice");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameIsNull() {
            addCriterion("LogisticsName is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameIsNotNull() {
            addCriterion("LogisticsName is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameEqualTo(String value) {
            addCriterion("LogisticsName =", value, "logisticsname");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameNotEqualTo(String value) {
            addCriterion("LogisticsName <>", value, "logisticsname");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameGreaterThan(String value) {
            addCriterion("LogisticsName >", value, "logisticsname");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameGreaterThanOrEqualTo(String value) {
            addCriterion("LogisticsName >=", value, "logisticsname");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameLessThan(String value) {
            addCriterion("LogisticsName <", value, "logisticsname");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameLessThanOrEqualTo(String value) {
            addCriterion("LogisticsName <=", value, "logisticsname");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameLike(String value) {
            addCriterion("LogisticsName like", value, "logisticsname");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameNotLike(String value) {
            addCriterion("LogisticsName not like", value, "logisticsname");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameIn(List<String> values) {
            addCriterion("LogisticsName in", values, "logisticsname");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameNotIn(List<String> values) {
            addCriterion("LogisticsName not in", values, "logisticsname");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameBetween(String value1, String value2) {
            addCriterion("LogisticsName between", value1, value2, "logisticsname");
            return (Criteria) this;
        }

        public Criteria andLogisticsnameNotBetween(String value1, String value2) {
            addCriterion("LogisticsName not between", value1, value2, "logisticsname");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeIsNull() {
            addCriterion("LogisticsCode is null");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeIsNotNull() {
            addCriterion("LogisticsCode is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeEqualTo(String value) {
            addCriterion("LogisticsCode =", value, "logisticscode");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeNotEqualTo(String value) {
            addCriterion("LogisticsCode <>", value, "logisticscode");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeGreaterThan(String value) {
            addCriterion("LogisticsCode >", value, "logisticscode");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeGreaterThanOrEqualTo(String value) {
            addCriterion("LogisticsCode >=", value, "logisticscode");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeLessThan(String value) {
            addCriterion("LogisticsCode <", value, "logisticscode");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeLessThanOrEqualTo(String value) {
            addCriterion("LogisticsCode <=", value, "logisticscode");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeLike(String value) {
            addCriterion("LogisticsCode like", value, "logisticscode");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeNotLike(String value) {
            addCriterion("LogisticsCode not like", value, "logisticscode");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeIn(List<String> values) {
            addCriterion("LogisticsCode in", values, "logisticscode");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeNotIn(List<String> values) {
            addCriterion("LogisticsCode not in", values, "logisticscode");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeBetween(String value1, String value2) {
            addCriterion("LogisticsCode between", value1, value2, "logisticscode");
            return (Criteria) this;
        }

        public Criteria andLogisticscodeNotBetween(String value1, String value2) {
            addCriterion("LogisticsCode not between", value1, value2, "logisticscode");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("Remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("Remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("Remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("Remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("Remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("Remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("Remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("Remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("Remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("Remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("Remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("Remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("Remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("Remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCancelreasonIsNull() {
            addCriterion("CancelReason is null");
            return (Criteria) this;
        }

        public Criteria andCancelreasonIsNotNull() {
            addCriterion("CancelReason is not null");
            return (Criteria) this;
        }

        public Criteria andCancelreasonEqualTo(String value) {
            addCriterion("CancelReason =", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonNotEqualTo(String value) {
            addCriterion("CancelReason <>", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonGreaterThan(String value) {
            addCriterion("CancelReason >", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonGreaterThanOrEqualTo(String value) {
            addCriterion("CancelReason >=", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonLessThan(String value) {
            addCriterion("CancelReason <", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonLessThanOrEqualTo(String value) {
            addCriterion("CancelReason <=", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonLike(String value) {
            addCriterion("CancelReason like", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonNotLike(String value) {
            addCriterion("CancelReason not like", value, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonIn(List<String> values) {
            addCriterion("CancelReason in", values, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonNotIn(List<String> values) {
            addCriterion("CancelReason not in", values, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonBetween(String value1, String value2) {
            addCriterion("CancelReason between", value1, value2, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andCancelreasonNotBetween(String value1, String value2) {
            addCriterion("CancelReason not between", value1, value2, "cancelreason");
            return (Criteria) this;
        }

        public Criteria andValidflagIsNull() {
            addCriterion("ValidFlag is null");
            return (Criteria) this;
        }

        public Criteria andValidflagIsNotNull() {
            addCriterion("ValidFlag is not null");
            return (Criteria) this;
        }

        public Criteria andValidflagEqualTo(Integer value) {
            addCriterion("ValidFlag =", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagNotEqualTo(Integer value) {
            addCriterion("ValidFlag <>", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagGreaterThan(Integer value) {
            addCriterion("ValidFlag >", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("ValidFlag >=", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagLessThan(Integer value) {
            addCriterion("ValidFlag <", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagLessThanOrEqualTo(Integer value) {
            addCriterion("ValidFlag <=", value, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagIn(List<Integer> values) {
            addCriterion("ValidFlag in", values, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagNotIn(List<Integer> values) {
            addCriterion("ValidFlag not in", values, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagBetween(Integer value1, Integer value2) {
            addCriterion("ValidFlag between", value1, value2, "validflag");
            return (Criteria) this;
        }

        public Criteria andValidflagNotBetween(Integer value1, Integer value2) {
            addCriterion("ValidFlag not between", value1, value2, "validflag");
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

        public Criteria andPaydateIsNull() {
            addCriterion("PayDate is null");
            return (Criteria) this;
        }

        public Criteria andPaydateIsNotNull() {
            addCriterion("PayDate is not null");
            return (Criteria) this;
        }

        public Criteria andPaydateEqualTo(Date value) {
            addCriterion("PayDate =", value, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateNotEqualTo(Date value) {
            addCriterion("PayDate <>", value, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateGreaterThan(Date value) {
            addCriterion("PayDate >", value, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateGreaterThanOrEqualTo(Date value) {
            addCriterion("PayDate >=", value, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateLessThan(Date value) {
            addCriterion("PayDate <", value, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateLessThanOrEqualTo(Date value) {
            addCriterion("PayDate <=", value, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateIn(List<Date> values) {
            addCriterion("PayDate in", values, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateNotIn(List<Date> values) {
            addCriterion("PayDate not in", values, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateBetween(Date value1, Date value2) {
            addCriterion("PayDate between", value1, value2, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateNotBetween(Date value1, Date value2) {
            addCriterion("PayDate not between", value1, value2, "paydate");
            return (Criteria) this;
        }

        public Criteria andDeliverdateIsNull() {
            addCriterion("DeliverDate is null");
            return (Criteria) this;
        }

        public Criteria andDeliverdateIsNotNull() {
            addCriterion("DeliverDate is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverdateEqualTo(Date value) {
            addCriterion("DeliverDate =", value, "deliverdate");
            return (Criteria) this;
        }

        public Criteria andDeliverdateNotEqualTo(Date value) {
            addCriterion("DeliverDate <>", value, "deliverdate");
            return (Criteria) this;
        }

        public Criteria andDeliverdateGreaterThan(Date value) {
            addCriterion("DeliverDate >", value, "deliverdate");
            return (Criteria) this;
        }

        public Criteria andDeliverdateGreaterThanOrEqualTo(Date value) {
            addCriterion("DeliverDate >=", value, "deliverdate");
            return (Criteria) this;
        }

        public Criteria andDeliverdateLessThan(Date value) {
            addCriterion("DeliverDate <", value, "deliverdate");
            return (Criteria) this;
        }

        public Criteria andDeliverdateLessThanOrEqualTo(Date value) {
            addCriterion("DeliverDate <=", value, "deliverdate");
            return (Criteria) this;
        }

        public Criteria andDeliverdateIn(List<Date> values) {
            addCriterion("DeliverDate in", values, "deliverdate");
            return (Criteria) this;
        }

        public Criteria andDeliverdateNotIn(List<Date> values) {
            addCriterion("DeliverDate not in", values, "deliverdate");
            return (Criteria) this;
        }

        public Criteria andDeliverdateBetween(Date value1, Date value2) {
            addCriterion("DeliverDate between", value1, value2, "deliverdate");
            return (Criteria) this;
        }

        public Criteria andDeliverdateNotBetween(Date value1, Date value2) {
            addCriterion("DeliverDate not between", value1, value2, "deliverdate");
            return (Criteria) this;
        }

        public Criteria andDeliverconfirmdateIsNull() {
            addCriterion("DeliverConfirmDate is null");
            return (Criteria) this;
        }

        public Criteria andDeliverconfirmdateIsNotNull() {
            addCriterion("DeliverConfirmDate is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverconfirmdateEqualTo(Date value) {
            addCriterion("DeliverConfirmDate =", value, "deliverconfirmdate");
            return (Criteria) this;
        }

        public Criteria andDeliverconfirmdateNotEqualTo(Date value) {
            addCriterion("DeliverConfirmDate <>", value, "deliverconfirmdate");
            return (Criteria) this;
        }

        public Criteria andDeliverconfirmdateGreaterThan(Date value) {
            addCriterion("DeliverConfirmDate >", value, "deliverconfirmdate");
            return (Criteria) this;
        }

        public Criteria andDeliverconfirmdateGreaterThanOrEqualTo(Date value) {
            addCriterion("DeliverConfirmDate >=", value, "deliverconfirmdate");
            return (Criteria) this;
        }

        public Criteria andDeliverconfirmdateLessThan(Date value) {
            addCriterion("DeliverConfirmDate <", value, "deliverconfirmdate");
            return (Criteria) this;
        }

        public Criteria andDeliverconfirmdateLessThanOrEqualTo(Date value) {
            addCriterion("DeliverConfirmDate <=", value, "deliverconfirmdate");
            return (Criteria) this;
        }

        public Criteria andDeliverconfirmdateIn(List<Date> values) {
            addCriterion("DeliverConfirmDate in", values, "deliverconfirmdate");
            return (Criteria) this;
        }

        public Criteria andDeliverconfirmdateNotIn(List<Date> values) {
            addCriterion("DeliverConfirmDate not in", values, "deliverconfirmdate");
            return (Criteria) this;
        }

        public Criteria andDeliverconfirmdateBetween(Date value1, Date value2) {
            addCriterion("DeliverConfirmDate between", value1, value2, "deliverconfirmdate");
            return (Criteria) this;
        }

        public Criteria andDeliverconfirmdateNotBetween(Date value1, Date value2) {
            addCriterion("DeliverConfirmDate not between", value1, value2, "deliverconfirmdate");
            return (Criteria) this;
        }

        public Criteria andDeldateIsNull() {
            addCriterion("DelDate is null");
            return (Criteria) this;
        }

        public Criteria andDeldateIsNotNull() {
            addCriterion("DelDate is not null");
            return (Criteria) this;
        }

        public Criteria andDeldateEqualTo(Date value) {
            addCriterion("DelDate =", value, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateNotEqualTo(Date value) {
            addCriterion("DelDate <>", value, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateGreaterThan(Date value) {
            addCriterion("DelDate >", value, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateGreaterThanOrEqualTo(Date value) {
            addCriterion("DelDate >=", value, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateLessThan(Date value) {
            addCriterion("DelDate <", value, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateLessThanOrEqualTo(Date value) {
            addCriterion("DelDate <=", value, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateIn(List<Date> values) {
            addCriterion("DelDate in", values, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateNotIn(List<Date> values) {
            addCriterion("DelDate not in", values, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateBetween(Date value1, Date value2) {
            addCriterion("DelDate between", value1, value2, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateNotBetween(Date value1, Date value2) {
            addCriterion("DelDate not between", value1, value2, "deldate");
            return (Criteria) this;
        }

        public Criteria andIsownedIsNull() {
            addCriterion("IsOwned is null");
            return (Criteria) this;
        }

        public Criteria andIsownedIsNotNull() {
            addCriterion("IsOwned is not null");
            return (Criteria) this;
        }

        public Criteria andIsownedEqualTo(Boolean value) {
            addCriterion("IsOwned =", value, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedNotEqualTo(Boolean value) {
            addCriterion("IsOwned <>", value, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedGreaterThan(Boolean value) {
            addCriterion("IsOwned >", value, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsOwned >=", value, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedLessThan(Boolean value) {
            addCriterion("IsOwned <", value, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedLessThanOrEqualTo(Boolean value) {
            addCriterion("IsOwned <=", value, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedIn(List<Boolean> values) {
            addCriterion("IsOwned in", values, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedNotIn(List<Boolean> values) {
            addCriterion("IsOwned not in", values, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedBetween(Boolean value1, Boolean value2) {
            addCriterion("IsOwned between", value1, value2, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsOwned not between", value1, value2, "isowned");
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

        public Criteria andIscommentIsNull() {
            addCriterion("IsComment is null");
            return (Criteria) this;
        }

        public Criteria andIscommentIsNotNull() {
            addCriterion("IsComment is not null");
            return (Criteria) this;
        }

        public Criteria andIscommentEqualTo(Integer value) {
            addCriterion("IsComment =", value, "iscomment");
            return (Criteria) this;
        }

        public Criteria andIscommentNotEqualTo(Integer value) {
            addCriterion("IsComment <>", value, "iscomment");
            return (Criteria) this;
        }

        public Criteria andIscommentGreaterThan(Integer value) {
            addCriterion("IsComment >", value, "iscomment");
            return (Criteria) this;
        }

        public Criteria andIscommentGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsComment >=", value, "iscomment");
            return (Criteria) this;
        }

        public Criteria andIscommentLessThan(Integer value) {
            addCriterion("IsComment <", value, "iscomment");
            return (Criteria) this;
        }

        public Criteria andIscommentLessThanOrEqualTo(Integer value) {
            addCriterion("IsComment <=", value, "iscomment");
            return (Criteria) this;
        }

        public Criteria andIscommentIn(List<Integer> values) {
            addCriterion("IsComment in", values, "iscomment");
            return (Criteria) this;
        }

        public Criteria andIscommentNotIn(List<Integer> values) {
            addCriterion("IsComment not in", values, "iscomment");
            return (Criteria) this;
        }

        public Criteria andIscommentBetween(Integer value1, Integer value2) {
            addCriterion("IsComment between", value1, value2, "iscomment");
            return (Criteria) this;
        }

        public Criteria andIscommentNotBetween(Integer value1, Integer value2) {
            addCriterion("IsComment not between", value1, value2, "iscomment");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("Discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("Discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(Float value) {
            addCriterion("Discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(Float value) {
            addCriterion("Discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(Float value) {
            addCriterion("Discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(Float value) {
            addCriterion("Discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(Float value) {
            addCriterion("Discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(Float value) {
            addCriterion("Discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<Float> values) {
            addCriterion("Discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<Float> values) {
            addCriterion("Discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(Float value1, Float value2) {
            addCriterion("Discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(Float value1, Float value2) {
            addCriterion("Discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andActivityidIsNull() {
            addCriterion("ActivityID is null");
            return (Criteria) this;
        }

        public Criteria andActivityidIsNotNull() {
            addCriterion("ActivityID is not null");
            return (Criteria) this;
        }

        public Criteria andActivityidEqualTo(Integer value) {
            addCriterion("ActivityID =", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidNotEqualTo(Integer value) {
            addCriterion("ActivityID <>", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidGreaterThan(Integer value) {
            addCriterion("ActivityID >", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ActivityID >=", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidLessThan(Integer value) {
            addCriterion("ActivityID <", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidLessThanOrEqualTo(Integer value) {
            addCriterion("ActivityID <=", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidIn(List<Integer> values) {
            addCriterion("ActivityID in", values, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidNotIn(List<Integer> values) {
            addCriterion("ActivityID not in", values, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidBetween(Integer value1, Integer value2) {
            addCriterion("ActivityID between", value1, value2, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidNotBetween(Integer value1, Integer value2) {
            addCriterion("ActivityID not between", value1, value2, "activityid");
            return (Criteria) this;
        }

        public Criteria andShopidIsNull() {
            addCriterion("ShopId is null");
            return (Criteria) this;
        }

        public Criteria andShopidIsNotNull() {
            addCriterion("ShopId is not null");
            return (Criteria) this;
        }

        public Criteria andShopidEqualTo(Integer value) {
            addCriterion("ShopId =", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotEqualTo(Integer value) {
            addCriterion("ShopId <>", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThan(Integer value) {
            addCriterion("ShopId >", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ShopId >=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThan(Integer value) {
            addCriterion("ShopId <", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThanOrEqualTo(Integer value) {
            addCriterion("ShopId <=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidIn(List<Integer> values) {
            addCriterion("ShopId in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotIn(List<Integer> values) {
            addCriterion("ShopId not in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidBetween(Integer value1, Integer value2) {
            addCriterion("ShopId between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotBetween(Integer value1, Integer value2) {
            addCriterion("ShopId not between", value1, value2, "shopid");
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