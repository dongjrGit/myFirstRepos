package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupbuyhistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupbuyhistoryExample() {
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
            addCriterion("Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCpriceIsNull() {
            addCriterion("CPrice is null");
            return (Criteria) this;
        }

        public Criteria andCpriceIsNotNull() {
            addCriterion("CPrice is not null");
            return (Criteria) this;
        }

        public Criteria andCpriceEqualTo(Float value) {
            addCriterion("CPrice =", value, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceNotEqualTo(Float value) {
            addCriterion("CPrice <>", value, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceGreaterThan(Float value) {
            addCriterion("CPrice >", value, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceGreaterThanOrEqualTo(Float value) {
            addCriterion("CPrice >=", value, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceLessThan(Float value) {
            addCriterion("CPrice <", value, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceLessThanOrEqualTo(Float value) {
            addCriterion("CPrice <=", value, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceIn(List<Float> values) {
            addCriterion("CPrice in", values, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceNotIn(List<Float> values) {
            addCriterion("CPrice not in", values, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceBetween(Float value1, Float value2) {
            addCriterion("CPrice between", value1, value2, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceNotBetween(Float value1, Float value2) {
            addCriterion("CPrice not between", value1, value2, "cprice");
            return (Criteria) this;
        }

        public Criteria andOpriceIsNull() {
            addCriterion("OPrice is null");
            return (Criteria) this;
        }

        public Criteria andOpriceIsNotNull() {
            addCriterion("OPrice is not null");
            return (Criteria) this;
        }

        public Criteria andOpriceEqualTo(Float value) {
            addCriterion("OPrice =", value, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceNotEqualTo(Float value) {
            addCriterion("OPrice <>", value, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceGreaterThan(Float value) {
            addCriterion("OPrice >", value, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceGreaterThanOrEqualTo(Float value) {
            addCriterion("OPrice >=", value, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceLessThan(Float value) {
            addCriterion("OPrice <", value, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceLessThanOrEqualTo(Float value) {
            addCriterion("OPrice <=", value, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceIn(List<Float> values) {
            addCriterion("OPrice in", values, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceNotIn(List<Float> values) {
            addCriterion("OPrice not in", values, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceBetween(Float value1, Float value2) {
            addCriterion("OPrice between", value1, value2, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceNotBetween(Float value1, Float value2) {
            addCriterion("OPrice not between", value1, value2, "oprice");
            return (Criteria) this;
        }

        public Criteria andValiditystartIsNull() {
            addCriterion("Validitystart is null");
            return (Criteria) this;
        }

        public Criteria andValiditystartIsNotNull() {
            addCriterion("Validitystart is not null");
            return (Criteria) this;
        }

        public Criteria andValiditystartEqualTo(Date value) {
            addCriterion("Validitystart =", value, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartNotEqualTo(Date value) {
            addCriterion("Validitystart <>", value, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartGreaterThan(Date value) {
            addCriterion("Validitystart >", value, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartGreaterThanOrEqualTo(Date value) {
            addCriterion("Validitystart >=", value, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartLessThan(Date value) {
            addCriterion("Validitystart <", value, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartLessThanOrEqualTo(Date value) {
            addCriterion("Validitystart <=", value, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartIn(List<Date> values) {
            addCriterion("Validitystart in", values, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartNotIn(List<Date> values) {
            addCriterion("Validitystart not in", values, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartBetween(Date value1, Date value2) {
            addCriterion("Validitystart between", value1, value2, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartNotBetween(Date value1, Date value2) {
            addCriterion("Validitystart not between", value1, value2, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValidityendIsNull() {
            addCriterion("Validityend is null");
            return (Criteria) this;
        }

        public Criteria andValidityendIsNotNull() {
            addCriterion("Validityend is not null");
            return (Criteria) this;
        }

        public Criteria andValidityendEqualTo(Date value) {
            addCriterion("Validityend =", value, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendNotEqualTo(Date value) {
            addCriterion("Validityend <>", value, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendGreaterThan(Date value) {
            addCriterion("Validityend >", value, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendGreaterThanOrEqualTo(Date value) {
            addCriterion("Validityend >=", value, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendLessThan(Date value) {
            addCriterion("Validityend <", value, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendLessThanOrEqualTo(Date value) {
            addCriterion("Validityend <=", value, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendIn(List<Date> values) {
            addCriterion("Validityend in", values, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendNotIn(List<Date> values) {
            addCriterion("Validityend not in", values, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendBetween(Date value1, Date value2) {
            addCriterion("Validityend between", value1, value2, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendNotBetween(Date value1, Date value2) {
            addCriterion("Validityend not between", value1, value2, "validityend");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("Type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("Type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("Type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("Type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("Type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("Type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("Type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("Type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("Type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("Type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("Type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("Type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andIsanytimeIsNull() {
            addCriterion("IsAnytime is null");
            return (Criteria) this;
        }

        public Criteria andIsanytimeIsNotNull() {
            addCriterion("IsAnytime is not null");
            return (Criteria) this;
        }

        public Criteria andIsanytimeEqualTo(Boolean value) {
            addCriterion("IsAnytime =", value, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeNotEqualTo(Boolean value) {
            addCriterion("IsAnytime <>", value, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeGreaterThan(Boolean value) {
            addCriterion("IsAnytime >", value, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsAnytime >=", value, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeLessThan(Boolean value) {
            addCriterion("IsAnytime <", value, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeLessThanOrEqualTo(Boolean value) {
            addCriterion("IsAnytime <=", value, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeIn(List<Boolean> values) {
            addCriterion("IsAnytime in", values, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeNotIn(List<Boolean> values) {
            addCriterion("IsAnytime not in", values, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeBetween(Boolean value1, Boolean value2) {
            addCriterion("IsAnytime between", value1, value2, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsAnytime not between", value1, value2, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsbookIsNull() {
            addCriterion("IsBook is null");
            return (Criteria) this;
        }

        public Criteria andIsbookIsNotNull() {
            addCriterion("IsBook is not null");
            return (Criteria) this;
        }

        public Criteria andIsbookEqualTo(Boolean value) {
            addCriterion("IsBook =", value, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookNotEqualTo(Boolean value) {
            addCriterion("IsBook <>", value, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookGreaterThan(Boolean value) {
            addCriterion("IsBook >", value, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsBook >=", value, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookLessThan(Boolean value) {
            addCriterion("IsBook <", value, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookLessThanOrEqualTo(Boolean value) {
            addCriterion("IsBook <=", value, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookIn(List<Boolean> values) {
            addCriterion("IsBook in", values, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookNotIn(List<Boolean> values) {
            addCriterion("IsBook not in", values, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookBetween(Boolean value1, Boolean value2) {
            addCriterion("IsBook between", value1, value2, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsBook not between", value1, value2, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsexpiredIsNull() {
            addCriterion("IsExpired is null");
            return (Criteria) this;
        }

        public Criteria andIsexpiredIsNotNull() {
            addCriterion("IsExpired is not null");
            return (Criteria) this;
        }

        public Criteria andIsexpiredEqualTo(Boolean value) {
            addCriterion("IsExpired =", value, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredNotEqualTo(Boolean value) {
            addCriterion("IsExpired <>", value, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredGreaterThan(Boolean value) {
            addCriterion("IsExpired >", value, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsExpired >=", value, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredLessThan(Boolean value) {
            addCriterion("IsExpired <", value, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredLessThanOrEqualTo(Boolean value) {
            addCriterion("IsExpired <=", value, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredIn(List<Boolean> values) {
            addCriterion("IsExpired in", values, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredNotIn(List<Boolean> values) {
            addCriterion("IsExpired not in", values, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredBetween(Boolean value1, Boolean value2) {
            addCriterion("IsExpired between", value1, value2, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsExpired not between", value1, value2, "isexpired");
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

        public Criteria andStockIsNull() {
            addCriterion("Stock is null");
            return (Criteria) this;
        }

        public Criteria andStockIsNotNull() {
            addCriterion("Stock is not null");
            return (Criteria) this;
        }

        public Criteria andStockEqualTo(Integer value) {
            addCriterion("Stock =", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotEqualTo(Integer value) {
            addCriterion("Stock <>", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThan(Integer value) {
            addCriterion("Stock >", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("Stock >=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThan(Integer value) {
            addCriterion("Stock <", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThanOrEqualTo(Integer value) {
            addCriterion("Stock <=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockIn(List<Integer> values) {
            addCriterion("Stock in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotIn(List<Integer> values) {
            addCriterion("Stock not in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockBetween(Integer value1, Integer value2) {
            addCriterion("Stock between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotBetween(Integer value1, Integer value2) {
            addCriterion("Stock not between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andSalescountIsNull() {
            addCriterion("SalesCount is null");
            return (Criteria) this;
        }

        public Criteria andSalescountIsNotNull() {
            addCriterion("SalesCount is not null");
            return (Criteria) this;
        }

        public Criteria andSalescountEqualTo(Integer value) {
            addCriterion("SalesCount =", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountNotEqualTo(Integer value) {
            addCriterion("SalesCount <>", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountGreaterThan(Integer value) {
            addCriterion("SalesCount >", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountGreaterThanOrEqualTo(Integer value) {
            addCriterion("SalesCount >=", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountLessThan(Integer value) {
            addCriterion("SalesCount <", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountLessThanOrEqualTo(Integer value) {
            addCriterion("SalesCount <=", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountIn(List<Integer> values) {
            addCriterion("SalesCount in", values, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountNotIn(List<Integer> values) {
            addCriterion("SalesCount not in", values, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountBetween(Integer value1, Integer value2) {
            addCriterion("SalesCount between", value1, value2, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountNotBetween(Integer value1, Integer value2) {
            addCriterion("SalesCount not between", value1, value2, "salescount");
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

        public Criteria andHacodeIsNull() {
            addCriterion("HaCode is null");
            return (Criteria) this;
        }

        public Criteria andHacodeIsNotNull() {
            addCriterion("HaCode is not null");
            return (Criteria) this;
        }

        public Criteria andHacodeEqualTo(String value) {
            addCriterion("HaCode =", value, "hacode");
            return (Criteria) this;
        }

        public Criteria andHacodeNotEqualTo(String value) {
            addCriterion("HaCode <>", value, "hacode");
            return (Criteria) this;
        }

        public Criteria andHacodeGreaterThan(String value) {
            addCriterion("HaCode >", value, "hacode");
            return (Criteria) this;
        }

        public Criteria andHacodeGreaterThanOrEqualTo(String value) {
            addCriterion("HaCode >=", value, "hacode");
            return (Criteria) this;
        }

        public Criteria andHacodeLessThan(String value) {
            addCriterion("HaCode <", value, "hacode");
            return (Criteria) this;
        }

        public Criteria andHacodeLessThanOrEqualTo(String value) {
            addCriterion("HaCode <=", value, "hacode");
            return (Criteria) this;
        }

        public Criteria andHacodeLike(String value) {
            addCriterion("HaCode like", value, "hacode");
            return (Criteria) this;
        }

        public Criteria andHacodeNotLike(String value) {
            addCriterion("HaCode not like", value, "hacode");
            return (Criteria) this;
        }

        public Criteria andHacodeIn(List<String> values) {
            addCriterion("HaCode in", values, "hacode");
            return (Criteria) this;
        }

        public Criteria andHacodeNotIn(List<String> values) {
            addCriterion("HaCode not in", values, "hacode");
            return (Criteria) this;
        }

        public Criteria andHacodeBetween(String value1, String value2) {
            addCriterion("HaCode between", value1, value2, "hacode");
            return (Criteria) this;
        }

        public Criteria andHacodeNotBetween(String value1, String value2) {
            addCriterion("HaCode not between", value1, value2, "hacode");
            return (Criteria) this;
        }

        public Criteria andGroupidIsNull() {
            addCriterion("GroupID is null");
            return (Criteria) this;
        }

        public Criteria andGroupidIsNotNull() {
            addCriterion("GroupID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupidEqualTo(Integer value) {
            addCriterion("GroupID =", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotEqualTo(Integer value) {
            addCriterion("GroupID <>", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThan(Integer value) {
            addCriterion("GroupID >", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThanOrEqualTo(Integer value) {
            addCriterion("GroupID >=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThan(Integer value) {
            addCriterion("GroupID <", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThanOrEqualTo(Integer value) {
            addCriterion("GroupID <=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidIn(List<Integer> values) {
            addCriterion("GroupID in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotIn(List<Integer> values) {
            addCriterion("GroupID not in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidBetween(Integer value1, Integer value2) {
            addCriterion("GroupID between", value1, value2, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotBetween(Integer value1, Integer value2) {
            addCriterion("GroupID not between", value1, value2, "groupid");
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