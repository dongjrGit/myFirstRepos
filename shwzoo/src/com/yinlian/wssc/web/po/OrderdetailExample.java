package com.yinlian.wssc.web.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderdetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderdetailExample() {
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

        public Criteria andOrderidIsNull() {
            addCriterion("OrderID is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("OrderID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(Integer value) {
            addCriterion("OrderID =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Integer value) {
            addCriterion("OrderID <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Integer value) {
            addCriterion("OrderID >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Integer value) {
            addCriterion("OrderID >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Integer value) {
            addCriterion("OrderID <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Integer value) {
            addCriterion("OrderID <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Integer> values) {
            addCriterion("OrderID in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Integer> values) {
            addCriterion("OrderID not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Integer value1, Integer value2) {
            addCriterion("OrderID between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Integer value1, Integer value2) {
            addCriterion("OrderID not between", value1, value2, "orderid");
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

        public Criteria andSkuIdIsNull() {
            addCriterion("SKU_ID is null");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNotNull() {
            addCriterion("SKU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSkuIdEqualTo(Integer value) {
            addCriterion("SKU_ID =", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotEqualTo(Integer value) {
            addCriterion("SKU_ID <>", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThan(Integer value) {
            addCriterion("SKU_ID >", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SKU_ID >=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThan(Integer value) {
            addCriterion("SKU_ID <", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThanOrEqualTo(Integer value) {
            addCriterion("SKU_ID <=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIn(List<Integer> values) {
            addCriterion("SKU_ID in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotIn(List<Integer> values) {
            addCriterion("SKU_ID not in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdBetween(Integer value1, Integer value2) {
            addCriterion("SKU_ID between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SKU_ID not between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andProductnumIsNull() {
            addCriterion("ProductNum is null");
            return (Criteria) this;
        }

        public Criteria andProductnumIsNotNull() {
            addCriterion("ProductNum is not null");
            return (Criteria) this;
        }

        public Criteria andProductnumEqualTo(String value) {
            addCriterion("ProductNum =", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumNotEqualTo(String value) {
            addCriterion("ProductNum <>", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumGreaterThan(String value) {
            addCriterion("ProductNum >", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumGreaterThanOrEqualTo(String value) {
            addCriterion("ProductNum >=", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumLessThan(String value) {
            addCriterion("ProductNum <", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumLessThanOrEqualTo(String value) {
            addCriterion("ProductNum <=", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumLike(String value) {
            addCriterion("ProductNum like", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumNotLike(String value) {
            addCriterion("ProductNum not like", value, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumIn(List<String> values) {
            addCriterion("ProductNum in", values, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumNotIn(List<String> values) {
            addCriterion("ProductNum not in", values, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumBetween(String value1, String value2) {
            addCriterion("ProductNum between", value1, value2, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductnumNotBetween(String value1, String value2) {
            addCriterion("ProductNum not between", value1, value2, "productnum");
            return (Criteria) this;
        }

        public Criteria andProductimgIsNull() {
            addCriterion("ProductImg is null");
            return (Criteria) this;
        }

        public Criteria andProductimgIsNotNull() {
            addCriterion("ProductImg is not null");
            return (Criteria) this;
        }

        public Criteria andProductimgEqualTo(String value) {
            addCriterion("ProductImg =", value, "productimg");
            return (Criteria) this;
        }

        public Criteria andProductimgNotEqualTo(String value) {
            addCriterion("ProductImg <>", value, "productimg");
            return (Criteria) this;
        }

        public Criteria andProductimgGreaterThan(String value) {
            addCriterion("ProductImg >", value, "productimg");
            return (Criteria) this;
        }

        public Criteria andProductimgGreaterThanOrEqualTo(String value) {
            addCriterion("ProductImg >=", value, "productimg");
            return (Criteria) this;
        }

        public Criteria andProductimgLessThan(String value) {
            addCriterion("ProductImg <", value, "productimg");
            return (Criteria) this;
        }

        public Criteria andProductimgLessThanOrEqualTo(String value) {
            addCriterion("ProductImg <=", value, "productimg");
            return (Criteria) this;
        }

        public Criteria andProductimgLike(String value) {
            addCriterion("ProductImg like", value, "productimg");
            return (Criteria) this;
        }

        public Criteria andProductimgNotLike(String value) {
            addCriterion("ProductImg not like", value, "productimg");
            return (Criteria) this;
        }

        public Criteria andProductimgIn(List<String> values) {
            addCriterion("ProductImg in", values, "productimg");
            return (Criteria) this;
        }

        public Criteria andProductimgNotIn(List<String> values) {
            addCriterion("ProductImg not in", values, "productimg");
            return (Criteria) this;
        }

        public Criteria andProductimgBetween(String value1, String value2) {
            addCriterion("ProductImg between", value1, value2, "productimg");
            return (Criteria) this;
        }

        public Criteria andProductimgNotBetween(String value1, String value2) {
            addCriterion("ProductImg not between", value1, value2, "productimg");
            return (Criteria) this;
        }

        public Criteria andProductnameIsNull() {
            addCriterion("ProductName is null");
            return (Criteria) this;
        }

        public Criteria andProductnameIsNotNull() {
            addCriterion("ProductName is not null");
            return (Criteria) this;
        }

        public Criteria andProductnameEqualTo(String value) {
            addCriterion("ProductName =", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotEqualTo(String value) {
            addCriterion("ProductName <>", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameGreaterThan(String value) {
            addCriterion("ProductName >", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameGreaterThanOrEqualTo(String value) {
            addCriterion("ProductName >=", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLessThan(String value) {
            addCriterion("ProductName <", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLessThanOrEqualTo(String value) {
            addCriterion("ProductName <=", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLike(String value) {
            addCriterion("ProductName like", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotLike(String value) {
            addCriterion("ProductName not like", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameIn(List<String> values) {
            addCriterion("ProductName in", values, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotIn(List<String> values) {
            addCriterion("ProductName not in", values, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameBetween(String value1, String value2) {
            addCriterion("ProductName between", value1, value2, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotBetween(String value1, String value2) {
            addCriterion("ProductName not between", value1, value2, "productname");
            return (Criteria) this;
        }

        public Criteria andProductpriceIsNull() {
            addCriterion("ProductPrice is null");
            return (Criteria) this;
        }

        public Criteria andProductpriceIsNotNull() {
            addCriterion("ProductPrice is not null");
            return (Criteria) this;
        }

        public Criteria andProductpriceEqualTo(BigDecimal value) {
            addCriterion("ProductPrice =", value, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceNotEqualTo(BigDecimal value) {
            addCriterion("ProductPrice <>", value, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceGreaterThan(BigDecimal value) {
            addCriterion("ProductPrice >", value, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ProductPrice >=", value, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceLessThan(BigDecimal value) {
            addCriterion("ProductPrice <", value, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ProductPrice <=", value, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceIn(List<BigDecimal> values) {
            addCriterion("ProductPrice in", values, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceNotIn(List<BigDecimal> values) {
            addCriterion("ProductPrice not in", values, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ProductPrice between", value1, value2, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ProductPrice not between", value1, value2, "productprice");
            return (Criteria) this;
        }

        public Criteria andProductcountIsNull() {
            addCriterion("ProductCount is null");
            return (Criteria) this;
        }

        public Criteria andProductcountIsNotNull() {
            addCriterion("ProductCount is not null");
            return (Criteria) this;
        }

        public Criteria andProductcountEqualTo(Integer value) {
            addCriterion("ProductCount =", value, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountNotEqualTo(Integer value) {
            addCriterion("ProductCount <>", value, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountGreaterThan(Integer value) {
            addCriterion("ProductCount >", value, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("ProductCount >=", value, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountLessThan(Integer value) {
            addCriterion("ProductCount <", value, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountLessThanOrEqualTo(Integer value) {
            addCriterion("ProductCount <=", value, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountIn(List<Integer> values) {
            addCriterion("ProductCount in", values, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountNotIn(List<Integer> values) {
            addCriterion("ProductCount not in", values, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountBetween(Integer value1, Integer value2) {
            addCriterion("ProductCount between", value1, value2, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductcountNotBetween(Integer value1, Integer value2) {
            addCriterion("ProductCount not between", value1, value2, "productcount");
            return (Criteria) this;
        }

        public Criteria andProductweightIsNull() {
            addCriterion("ProductWeight is null");
            return (Criteria) this;
        }

        public Criteria andProductweightIsNotNull() {
            addCriterion("ProductWeight is not null");
            return (Criteria) this;
        }

        public Criteria andProductweightEqualTo(BigDecimal value) {
            addCriterion("ProductWeight =", value, "productweight");
            return (Criteria) this;
        }

        public Criteria andProductweightNotEqualTo(BigDecimal value) {
            addCriterion("ProductWeight <>", value, "productweight");
            return (Criteria) this;
        }

        public Criteria andProductweightGreaterThan(BigDecimal value) {
            addCriterion("ProductWeight >", value, "productweight");
            return (Criteria) this;
        }

        public Criteria andProductweightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ProductWeight >=", value, "productweight");
            return (Criteria) this;
        }

        public Criteria andProductweightLessThan(BigDecimal value) {
            addCriterion("ProductWeight <", value, "productweight");
            return (Criteria) this;
        }

        public Criteria andProductweightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ProductWeight <=", value, "productweight");
            return (Criteria) this;
        }

        public Criteria andProductweightIn(List<BigDecimal> values) {
            addCriterion("ProductWeight in", values, "productweight");
            return (Criteria) this;
        }

        public Criteria andProductweightNotIn(List<BigDecimal> values) {
            addCriterion("ProductWeight not in", values, "productweight");
            return (Criteria) this;
        }

        public Criteria andProductweightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ProductWeight between", value1, value2, "productweight");
            return (Criteria) this;
        }

        public Criteria andProductweightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ProductWeight not between", value1, value2, "productweight");
            return (Criteria) this;
        }

        public Criteria andVaildflagIsNull() {
            addCriterion("VaildFlag is null");
            return (Criteria) this;
        }

        public Criteria andVaildflagIsNotNull() {
            addCriterion("VaildFlag is not null");
            return (Criteria) this;
        }

        public Criteria andVaildflagEqualTo(Integer value) {
            addCriterion("VaildFlag =", value, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagNotEqualTo(Integer value) {
            addCriterion("VaildFlag <>", value, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagGreaterThan(Integer value) {
            addCriterion("VaildFlag >", value, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("VaildFlag >=", value, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagLessThan(Integer value) {
            addCriterion("VaildFlag <", value, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagLessThanOrEqualTo(Integer value) {
            addCriterion("VaildFlag <=", value, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagIn(List<Integer> values) {
            addCriterion("VaildFlag in", values, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagNotIn(List<Integer> values) {
            addCriterion("VaildFlag not in", values, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagBetween(Integer value1, Integer value2) {
            addCriterion("VaildFlag between", value1, value2, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagNotBetween(Integer value1, Integer value2) {
            addCriterion("VaildFlag not between", value1, value2, "vaildflag");
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

        public Criteria andIsbackcommentIsNull() {
            addCriterion("IsBackComment is null");
            return (Criteria) this;
        }

        public Criteria andIsbackcommentIsNotNull() {
            addCriterion("IsBackComment is not null");
            return (Criteria) this;
        }

        public Criteria andIsbackcommentEqualTo(Integer value) {
            addCriterion("IsBackComment =", value, "isbackcomment");
            return (Criteria) this;
        }

        public Criteria andIsbackcommentNotEqualTo(Integer value) {
            addCriterion("IsBackComment <>", value, "isbackcomment");
            return (Criteria) this;
        }

        public Criteria andIsbackcommentGreaterThan(Integer value) {
            addCriterion("IsBackComment >", value, "isbackcomment");
            return (Criteria) this;
        }

        public Criteria andIsbackcommentGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsBackComment >=", value, "isbackcomment");
            return (Criteria) this;
        }

        public Criteria andIsbackcommentLessThan(Integer value) {
            addCriterion("IsBackComment <", value, "isbackcomment");
            return (Criteria) this;
        }

        public Criteria andIsbackcommentLessThanOrEqualTo(Integer value) {
            addCriterion("IsBackComment <=", value, "isbackcomment");
            return (Criteria) this;
        }

        public Criteria andIsbackcommentIn(List<Integer> values) {
            addCriterion("IsBackComment in", values, "isbackcomment");
            return (Criteria) this;
        }

        public Criteria andIsbackcommentNotIn(List<Integer> values) {
            addCriterion("IsBackComment not in", values, "isbackcomment");
            return (Criteria) this;
        }

        public Criteria andIsbackcommentBetween(Integer value1, Integer value2) {
            addCriterion("IsBackComment between", value1, value2, "isbackcomment");
            return (Criteria) this;
        }

        public Criteria andIsbackcommentNotBetween(Integer value1, Integer value2) {
            addCriterion("IsBackComment not between", value1, value2, "isbackcomment");
            return (Criteria) this;
        }

        public Criteria andIsshowimgIsNull() {
            addCriterion("IsShowImg is null");
            return (Criteria) this;
        }

        public Criteria andIsshowimgIsNotNull() {
            addCriterion("IsShowImg is not null");
            return (Criteria) this;
        }

        public Criteria andIsshowimgEqualTo(Integer value) {
            addCriterion("IsShowImg =", value, "isshowimg");
            return (Criteria) this;
        }

        public Criteria andIsshowimgNotEqualTo(Integer value) {
            addCriterion("IsShowImg <>", value, "isshowimg");
            return (Criteria) this;
        }

        public Criteria andIsshowimgGreaterThan(Integer value) {
            addCriterion("IsShowImg >", value, "isshowimg");
            return (Criteria) this;
        }

        public Criteria andIsshowimgGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsShowImg >=", value, "isshowimg");
            return (Criteria) this;
        }

        public Criteria andIsshowimgLessThan(Integer value) {
            addCriterion("IsShowImg <", value, "isshowimg");
            return (Criteria) this;
        }

        public Criteria andIsshowimgLessThanOrEqualTo(Integer value) {
            addCriterion("IsShowImg <=", value, "isshowimg");
            return (Criteria) this;
        }

        public Criteria andIsshowimgIn(List<Integer> values) {
            addCriterion("IsShowImg in", values, "isshowimg");
            return (Criteria) this;
        }

        public Criteria andIsshowimgNotIn(List<Integer> values) {
            addCriterion("IsShowImg not in", values, "isshowimg");
            return (Criteria) this;
        }

        public Criteria andIsshowimgBetween(Integer value1, Integer value2) {
            addCriterion("IsShowImg between", value1, value2, "isshowimg");
            return (Criteria) this;
        }

        public Criteria andIsshowimgNotBetween(Integer value1, Integer value2) {
            addCriterion("IsShowImg not between", value1, value2, "isshowimg");
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

        public Criteria andPackageidIsNull() {
            addCriterion("PackageID is null");
            return (Criteria) this;
        }

        public Criteria andPackageidIsNotNull() {
            addCriterion("PackageID is not null");
            return (Criteria) this;
        }

        public Criteria andPackageidEqualTo(Integer value) {
            addCriterion("PackageID =", value, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidNotEqualTo(Integer value) {
            addCriterion("PackageID <>", value, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidGreaterThan(Integer value) {
            addCriterion("PackageID >", value, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidGreaterThanOrEqualTo(Integer value) {
            addCriterion("PackageID >=", value, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidLessThan(Integer value) {
            addCriterion("PackageID <", value, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidLessThanOrEqualTo(Integer value) {
            addCriterion("PackageID <=", value, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidIn(List<Integer> values) {
            addCriterion("PackageID in", values, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidNotIn(List<Integer> values) {
            addCriterion("PackageID not in", values, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidBetween(Integer value1, Integer value2) {
            addCriterion("PackageID between", value1, value2, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidNotBetween(Integer value1, Integer value2) {
            addCriterion("PackageID not between", value1, value2, "packageid");
            return (Criteria) this;
        }

        public Criteria andMarketidIsNull() {
            addCriterion("MarketID is null");
            return (Criteria) this;
        }

        public Criteria andMarketidIsNotNull() {
            addCriterion("MarketID is not null");
            return (Criteria) this;
        }

        public Criteria andMarketidEqualTo(Integer value) {
            addCriterion("MarketID =", value, "marketid");
            return (Criteria) this;
        }

        public Criteria andMarketidNotEqualTo(Integer value) {
            addCriterion("MarketID <>", value, "marketid");
            return (Criteria) this;
        }

        public Criteria andMarketidGreaterThan(Integer value) {
            addCriterion("MarketID >", value, "marketid");
            return (Criteria) this;
        }

        public Criteria andMarketidGreaterThanOrEqualTo(Integer value) {
            addCriterion("MarketID >=", value, "marketid");
            return (Criteria) this;
        }

        public Criteria andMarketidLessThan(Integer value) {
            addCriterion("MarketID <", value, "marketid");
            return (Criteria) this;
        }

        public Criteria andMarketidLessThanOrEqualTo(Integer value) {
            addCriterion("MarketID <=", value, "marketid");
            return (Criteria) this;
        }

        public Criteria andMarketidIn(List<Integer> values) {
            addCriterion("MarketID in", values, "marketid");
            return (Criteria) this;
        }

        public Criteria andMarketidNotIn(List<Integer> values) {
            addCriterion("MarketID not in", values, "marketid");
            return (Criteria) this;
        }

        public Criteria andMarketidBetween(Integer value1, Integer value2) {
            addCriterion("MarketID between", value1, value2, "marketid");
            return (Criteria) this;
        }

        public Criteria andMarketidNotBetween(Integer value1, Integer value2) {
            addCriterion("MarketID not between", value1, value2, "marketid");
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

        public Criteria andApplytimeIsNull() {
            addCriterion("ApplyTime is null");
            return (Criteria) this;
        }

        public Criteria andApplytimeIsNotNull() {
            addCriterion("ApplyTime is not null");
            return (Criteria) this;
        }

        public Criteria andApplytimeEqualTo(Date value) {
            addCriterion("ApplyTime =", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeNotEqualTo(Date value) {
            addCriterion("ApplyTime <>", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeGreaterThan(Date value) {
            addCriterion("ApplyTime >", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ApplyTime >=", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeLessThan(Date value) {
            addCriterion("ApplyTime <", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeLessThanOrEqualTo(Date value) {
            addCriterion("ApplyTime <=", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeIn(List<Date> values) {
            addCriterion("ApplyTime in", values, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeNotIn(List<Date> values) {
            addCriterion("ApplyTime not in", values, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeBetween(Date value1, Date value2) {
            addCriterion("ApplyTime between", value1, value2, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeNotBetween(Date value1, Date value2) {
            addCriterion("ApplyTime not between", value1, value2, "applytime");
            return (Criteria) this;
        }

        public Criteria andDisposetimeIsNull() {
            addCriterion("DisposeTime is null");
            return (Criteria) this;
        }

        public Criteria andDisposetimeIsNotNull() {
            addCriterion("DisposeTime is not null");
            return (Criteria) this;
        }

        public Criteria andDisposetimeEqualTo(Date value) {
            addCriterion("DisposeTime =", value, "disposetime");
            return (Criteria) this;
        }

        public Criteria andDisposetimeNotEqualTo(Date value) {
            addCriterion("DisposeTime <>", value, "disposetime");
            return (Criteria) this;
        }

        public Criteria andDisposetimeGreaterThan(Date value) {
            addCriterion("DisposeTime >", value, "disposetime");
            return (Criteria) this;
        }

        public Criteria andDisposetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("DisposeTime >=", value, "disposetime");
            return (Criteria) this;
        }

        public Criteria andDisposetimeLessThan(Date value) {
            addCriterion("DisposeTime <", value, "disposetime");
            return (Criteria) this;
        }

        public Criteria andDisposetimeLessThanOrEqualTo(Date value) {
            addCriterion("DisposeTime <=", value, "disposetime");
            return (Criteria) this;
        }

        public Criteria andDisposetimeIn(List<Date> values) {
            addCriterion("DisposeTime in", values, "disposetime");
            return (Criteria) this;
        }

        public Criteria andDisposetimeNotIn(List<Date> values) {
            addCriterion("DisposeTime not in", values, "disposetime");
            return (Criteria) this;
        }

        public Criteria andDisposetimeBetween(Date value1, Date value2) {
            addCriterion("DisposeTime between", value1, value2, "disposetime");
            return (Criteria) this;
        }

        public Criteria andDisposetimeNotBetween(Date value1, Date value2) {
            addCriterion("DisposeTime not between", value1, value2, "disposetime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeIsNull() {
            addCriterion("FinishTime is null");
            return (Criteria) this;
        }

        public Criteria andFinishtimeIsNotNull() {
            addCriterion("FinishTime is not null");
            return (Criteria) this;
        }

        public Criteria andFinishtimeEqualTo(Date value) {
            addCriterion("FinishTime =", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeNotEqualTo(Date value) {
            addCriterion("FinishTime <>", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeGreaterThan(Date value) {
            addCriterion("FinishTime >", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("FinishTime >=", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeLessThan(Date value) {
            addCriterion("FinishTime <", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeLessThanOrEqualTo(Date value) {
            addCriterion("FinishTime <=", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeIn(List<Date> values) {
            addCriterion("FinishTime in", values, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeNotIn(List<Date> values) {
            addCriterion("FinishTime not in", values, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeBetween(Date value1, Date value2) {
            addCriterion("FinishTime between", value1, value2, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeNotBetween(Date value1, Date value2) {
            addCriterion("FinishTime not between", value1, value2, "finishtime");
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