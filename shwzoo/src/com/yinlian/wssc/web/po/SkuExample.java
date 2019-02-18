package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.List;

public class SkuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SkuExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("Name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("Name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("Name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("Name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("Name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("Name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("Name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("Name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("Name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("Name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("Name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("Name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("Name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("Name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("Num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("Num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(String value) {
            addCriterion("Num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(String value) {
            addCriterion("Num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(String value) {
            addCriterion("Num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(String value) {
            addCriterion("Num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(String value) {
            addCriterion("Num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(String value) {
            addCriterion("Num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLike(String value) {
            addCriterion("Num like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotLike(String value) {
            addCriterion("Num not like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<String> values) {
            addCriterion("Num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<String> values) {
            addCriterion("Num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(String value1, String value2) {
            addCriterion("Num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(String value1, String value2) {
            addCriterion("Num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andSpuIdIsNull() {
            addCriterion("SPU_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpuIdIsNotNull() {
            addCriterion("SPU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpuIdEqualTo(Integer value) {
            addCriterion("SPU_ID =", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdNotEqualTo(Integer value) {
            addCriterion("SPU_ID <>", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdGreaterThan(Integer value) {
            addCriterion("SPU_ID >", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SPU_ID >=", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdLessThan(Integer value) {
            addCriterion("SPU_ID <", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdLessThanOrEqualTo(Integer value) {
            addCriterion("SPU_ID <=", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdIn(List<Integer> values) {
            addCriterion("SPU_ID in", values, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdNotIn(List<Integer> values) {
            addCriterion("SPU_ID not in", values, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdBetween(Integer value1, Integer value2) {
            addCriterion("SPU_ID between", value1, value2, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SPU_ID not between", value1, value2, "spuId");
            return (Criteria) this;
        }

        public Criteria andImgurlIsNull() {
            addCriterion("ImgUrl is null");
            return (Criteria) this;
        }

        public Criteria andImgurlIsNotNull() {
            addCriterion("ImgUrl is not null");
            return (Criteria) this;
        }

        public Criteria andImgurlEqualTo(String value) {
            addCriterion("ImgUrl =", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotEqualTo(String value) {
            addCriterion("ImgUrl <>", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlGreaterThan(String value) {
            addCriterion("ImgUrl >", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlGreaterThanOrEqualTo(String value) {
            addCriterion("ImgUrl >=", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLessThan(String value) {
            addCriterion("ImgUrl <", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLessThanOrEqualTo(String value) {
            addCriterion("ImgUrl <=", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLike(String value) {
            addCriterion("ImgUrl like", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotLike(String value) {
            addCriterion("ImgUrl not like", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlIn(List<String> values) {
            addCriterion("ImgUrl in", values, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotIn(List<String> values) {
            addCriterion("ImgUrl not in", values, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlBetween(String value1, String value2) {
            addCriterion("ImgUrl between", value1, value2, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotBetween(String value1, String value2) {
            addCriterion("ImgUrl not between", value1, value2, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlAppIsNull() {
            addCriterion("ImgUrl_App is null");
            return (Criteria) this;
        }

        public Criteria andImgurlAppIsNotNull() {
            addCriterion("ImgUrl_App is not null");
            return (Criteria) this;
        }

        public Criteria andImgurlAppEqualTo(String value) {
            addCriterion("ImgUrl_App =", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppNotEqualTo(String value) {
            addCriterion("ImgUrl_App <>", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppGreaterThan(String value) {
            addCriterion("ImgUrl_App >", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppGreaterThanOrEqualTo(String value) {
            addCriterion("ImgUrl_App >=", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppLessThan(String value) {
            addCriterion("ImgUrl_App <", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppLessThanOrEqualTo(String value) {
            addCriterion("ImgUrl_App <=", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppLike(String value) {
            addCriterion("ImgUrl_App like", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppNotLike(String value) {
            addCriterion("ImgUrl_App not like", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppIn(List<String> values) {
            addCriterion("ImgUrl_App in", values, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppNotIn(List<String> values) {
            addCriterion("ImgUrl_App not in", values, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppBetween(String value1, String value2) {
            addCriterion("ImgUrl_App between", value1, value2, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppNotBetween(String value1, String value2) {
            addCriterion("ImgUrl_App not between", value1, value2, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallIsNull() {
            addCriterion("ImgSmall is null");
            return (Criteria) this;
        }

        public Criteria andImgsmallIsNotNull() {
            addCriterion("ImgSmall is not null");
            return (Criteria) this;
        }

        public Criteria andImgsmallEqualTo(String value) {
            addCriterion("ImgSmall =", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallNotEqualTo(String value) {
            addCriterion("ImgSmall <>", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallGreaterThan(String value) {
            addCriterion("ImgSmall >", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallGreaterThanOrEqualTo(String value) {
            addCriterion("ImgSmall >=", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallLessThan(String value) {
            addCriterion("ImgSmall <", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallLessThanOrEqualTo(String value) {
            addCriterion("ImgSmall <=", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallLike(String value) {
            addCriterion("ImgSmall like", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallNotLike(String value) {
            addCriterion("ImgSmall not like", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallIn(List<String> values) {
            addCriterion("ImgSmall in", values, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallNotIn(List<String> values) {
            addCriterion("ImgSmall not in", values, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallBetween(String value1, String value2) {
            addCriterion("ImgSmall between", value1, value2, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallNotBetween(String value1, String value2) {
            addCriterion("ImgSmall not between", value1, value2, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppIsNull() {
            addCriterion("ImgSmall_App is null");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppIsNotNull() {
            addCriterion("ImgSmall_App is not null");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppEqualTo(String value) {
            addCriterion("ImgSmall_App =", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppNotEqualTo(String value) {
            addCriterion("ImgSmall_App <>", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppGreaterThan(String value) {
            addCriterion("ImgSmall_App >", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppGreaterThanOrEqualTo(String value) {
            addCriterion("ImgSmall_App >=", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppLessThan(String value) {
            addCriterion("ImgSmall_App <", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppLessThanOrEqualTo(String value) {
            addCriterion("ImgSmall_App <=", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppLike(String value) {
            addCriterion("ImgSmall_App like", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppNotLike(String value) {
            addCriterion("ImgSmall_App not like", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppIn(List<String> values) {
            addCriterion("ImgSmall_App in", values, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppNotIn(List<String> values) {
            addCriterion("ImgSmall_App not in", values, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppBetween(String value1, String value2) {
            addCriterion("ImgSmall_App between", value1, value2, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppNotBetween(String value1, String value2) {
            addCriterion("ImgSmall_App not between", value1, value2, "imgsmallApp");
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

        public Criteria andPriceEqualTo(Float value) {
            addCriterion("Price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Float value) {
            addCriterion("Price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Float value) {
            addCriterion("Price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("Price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Float value) {
            addCriterion("Price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Float value) {
            addCriterion("Price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Float> values) {
            addCriterion("Price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Float> values) {
            addCriterion("Price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Float value1, Float value2) {
            addCriterion("Price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Float value1, Float value2) {
            addCriterion("Price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andOldpriceIsNull() {
            addCriterion("OldPrice is null");
            return (Criteria) this;
        }

        public Criteria andOldpriceIsNotNull() {
            addCriterion("OldPrice is not null");
            return (Criteria) this;
        }

        public Criteria andOldpriceEqualTo(Float value) {
            addCriterion("OldPrice =", value, "oldprice");
            return (Criteria) this;
        }

        public Criteria andOldpriceNotEqualTo(Float value) {
            addCriterion("OldPrice <>", value, "oldprice");
            return (Criteria) this;
        }

        public Criteria andOldpriceGreaterThan(Float value) {
            addCriterion("OldPrice >", value, "oldprice");
            return (Criteria) this;
        }

        public Criteria andOldpriceGreaterThanOrEqualTo(Float value) {
            addCriterion("OldPrice >=", value, "oldprice");
            return (Criteria) this;
        }

        public Criteria andOldpriceLessThan(Float value) {
            addCriterion("OldPrice <", value, "oldprice");
            return (Criteria) this;
        }

        public Criteria andOldpriceLessThanOrEqualTo(Float value) {
            addCriterion("OldPrice <=", value, "oldprice");
            return (Criteria) this;
        }

        public Criteria andOldpriceIn(List<Float> values) {
            addCriterion("OldPrice in", values, "oldprice");
            return (Criteria) this;
        }

        public Criteria andOldpriceNotIn(List<Float> values) {
            addCriterion("OldPrice not in", values, "oldprice");
            return (Criteria) this;
        }

        public Criteria andOldpriceBetween(Float value1, Float value2) {
            addCriterion("OldPrice between", value1, value2, "oldprice");
            return (Criteria) this;
        }

        public Criteria andOldpriceNotBetween(Float value1, Float value2) {
            addCriterion("OldPrice not between", value1, value2, "oldprice");
            return (Criteria) this;
        }

        public Criteria andApppriceIsNull() {
            addCriterion("AppPrice is null");
            return (Criteria) this;
        }

        public Criteria andApppriceIsNotNull() {
            addCriterion("AppPrice is not null");
            return (Criteria) this;
        }

        public Criteria andApppriceEqualTo(Float value) {
            addCriterion("AppPrice =", value, "appprice");
            return (Criteria) this;
        }

        public Criteria andApppriceNotEqualTo(Float value) {
            addCriterion("AppPrice <>", value, "appprice");
            return (Criteria) this;
        }

        public Criteria andApppriceGreaterThan(Float value) {
            addCriterion("AppPrice >", value, "appprice");
            return (Criteria) this;
        }

        public Criteria andApppriceGreaterThanOrEqualTo(Float value) {
            addCriterion("AppPrice >=", value, "appprice");
            return (Criteria) this;
        }

        public Criteria andApppriceLessThan(Float value) {
            addCriterion("AppPrice <", value, "appprice");
            return (Criteria) this;
        }

        public Criteria andApppriceLessThanOrEqualTo(Float value) {
            addCriterion("AppPrice <=", value, "appprice");
            return (Criteria) this;
        }

        public Criteria andApppriceIn(List<Float> values) {
            addCriterion("AppPrice in", values, "appprice");
            return (Criteria) this;
        }

        public Criteria andApppriceNotIn(List<Float> values) {
            addCriterion("AppPrice not in", values, "appprice");
            return (Criteria) this;
        }

        public Criteria andApppriceBetween(Float value1, Float value2) {
            addCriterion("AppPrice between", value1, value2, "appprice");
            return (Criteria) this;
        }

        public Criteria andApppriceNotBetween(Float value1, Float value2) {
            addCriterion("AppPrice not between", value1, value2, "appprice");
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

        public Criteria andWarnnumIsNull() {
            addCriterion("WarnNum is null");
            return (Criteria) this;
        }

        public Criteria andWarnnumIsNotNull() {
            addCriterion("WarnNum is not null");
            return (Criteria) this;
        }

        public Criteria andWarnnumEqualTo(Integer value) {
            addCriterion("WarnNum =", value, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumNotEqualTo(Integer value) {
            addCriterion("WarnNum <>", value, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumGreaterThan(Integer value) {
            addCriterion("WarnNum >", value, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("WarnNum >=", value, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumLessThan(Integer value) {
            addCriterion("WarnNum <", value, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumLessThanOrEqualTo(Integer value) {
            addCriterion("WarnNum <=", value, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumIn(List<Integer> values) {
            addCriterion("WarnNum in", values, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumNotIn(List<Integer> values) {
            addCriterion("WarnNum not in", values, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumBetween(Integer value1, Integer value2) {
            addCriterion("WarnNum between", value1, value2, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumNotBetween(Integer value1, Integer value2) {
            addCriterion("WarnNum not between", value1, value2, "warnnum");
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

        public Criteria andIsusevippriceIsNull() {
            addCriterion("IsUseVipPrice is null");
            return (Criteria) this;
        }

        public Criteria andIsusevippriceIsNotNull() {
            addCriterion("IsUseVipPrice is not null");
            return (Criteria) this;
        }

        public Criteria andIsusevippriceEqualTo(Boolean value) {
            addCriterion("IsUseVipPrice =", value, "isusevipprice");
            return (Criteria) this;
        }

        public Criteria andIsusevippriceNotEqualTo(Boolean value) {
            addCriterion("IsUseVipPrice <>", value, "isusevipprice");
            return (Criteria) this;
        }

        public Criteria andIsusevippriceGreaterThan(Boolean value) {
            addCriterion("IsUseVipPrice >", value, "isusevipprice");
            return (Criteria) this;
        }

        public Criteria andIsusevippriceGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsUseVipPrice >=", value, "isusevipprice");
            return (Criteria) this;
        }

        public Criteria andIsusevippriceLessThan(Boolean value) {
            addCriterion("IsUseVipPrice <", value, "isusevipprice");
            return (Criteria) this;
        }

        public Criteria andIsusevippriceLessThanOrEqualTo(Boolean value) {
            addCriterion("IsUseVipPrice <=", value, "isusevipprice");
            return (Criteria) this;
        }

        public Criteria andIsusevippriceIn(List<Boolean> values) {
            addCriterion("IsUseVipPrice in", values, "isusevipprice");
            return (Criteria) this;
        }

        public Criteria andIsusevippriceNotIn(List<Boolean> values) {
            addCriterion("IsUseVipPrice not in", values, "isusevipprice");
            return (Criteria) this;
        }

        public Criteria andIsusevippriceBetween(Boolean value1, Boolean value2) {
            addCriterion("IsUseVipPrice between", value1, value2, "isusevipprice");
            return (Criteria) this;
        }

        public Criteria andIsusevippriceNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsUseVipPrice not between", value1, value2, "isusevipprice");
            return (Criteria) this;
        }

        public Criteria andViplevelIsNull() {
            addCriterion("VipLevel is null");
            return (Criteria) this;
        }

        public Criteria andViplevelIsNotNull() {
            addCriterion("VipLevel is not null");
            return (Criteria) this;
        }

        public Criteria andViplevelEqualTo(Integer value) {
            addCriterion("VipLevel =", value, "viplevel");
            return (Criteria) this;
        }

        public Criteria andViplevelNotEqualTo(Integer value) {
            addCriterion("VipLevel <>", value, "viplevel");
            return (Criteria) this;
        }

        public Criteria andViplevelGreaterThan(Integer value) {
            addCriterion("VipLevel >", value, "viplevel");
            return (Criteria) this;
        }

        public Criteria andViplevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("VipLevel >=", value, "viplevel");
            return (Criteria) this;
        }

        public Criteria andViplevelLessThan(Integer value) {
            addCriterion("VipLevel <", value, "viplevel");
            return (Criteria) this;
        }

        public Criteria andViplevelLessThanOrEqualTo(Integer value) {
            addCriterion("VipLevel <=", value, "viplevel");
            return (Criteria) this;
        }

        public Criteria andViplevelIn(List<Integer> values) {
            addCriterion("VipLevel in", values, "viplevel");
            return (Criteria) this;
        }

        public Criteria andViplevelNotIn(List<Integer> values) {
            addCriterion("VipLevel not in", values, "viplevel");
            return (Criteria) this;
        }

        public Criteria andViplevelBetween(Integer value1, Integer value2) {
            addCriterion("VipLevel between", value1, value2, "viplevel");
            return (Criteria) this;
        }

        public Criteria andViplevelNotBetween(Integer value1, Integer value2) {
            addCriterion("VipLevel not between", value1, value2, "viplevel");
            return (Criteria) this;
        }

        public Criteria andVippriceIsNull() {
            addCriterion("VipPrice is null");
            return (Criteria) this;
        }

        public Criteria andVippriceIsNotNull() {
            addCriterion("VipPrice is not null");
            return (Criteria) this;
        }

        public Criteria andVippriceEqualTo(Float value) {
            addCriterion("VipPrice =", value, "vipprice");
            return (Criteria) this;
        }

        public Criteria andVippriceNotEqualTo(Float value) {
            addCriterion("VipPrice <>", value, "vipprice");
            return (Criteria) this;
        }

        public Criteria andVippriceGreaterThan(Float value) {
            addCriterion("VipPrice >", value, "vipprice");
            return (Criteria) this;
        }

        public Criteria andVippriceGreaterThanOrEqualTo(Float value) {
            addCriterion("VipPrice >=", value, "vipprice");
            return (Criteria) this;
        }

        public Criteria andVippriceLessThan(Float value) {
            addCriterion("VipPrice <", value, "vipprice");
            return (Criteria) this;
        }

        public Criteria andVippriceLessThanOrEqualTo(Float value) {
            addCriterion("VipPrice <=", value, "vipprice");
            return (Criteria) this;
        }

        public Criteria andVippriceIn(List<Float> values) {
            addCriterion("VipPrice in", values, "vipprice");
            return (Criteria) this;
        }

        public Criteria andVippriceNotIn(List<Float> values) {
            addCriterion("VipPrice not in", values, "vipprice");
            return (Criteria) this;
        }

        public Criteria andVippriceBetween(Float value1, Float value2) {
            addCriterion("VipPrice between", value1, value2, "vipprice");
            return (Criteria) this;
        }

        public Criteria andVippriceNotBetween(Float value1, Float value2) {
            addCriterion("VipPrice not between", value1, value2, "vipprice");
            return (Criteria) this;
        }

        public Criteria andAppvippriceIsNull() {
            addCriterion("AppVipPrice is null");
            return (Criteria) this;
        }

        public Criteria andAppvippriceIsNotNull() {
            addCriterion("AppVipPrice is not null");
            return (Criteria) this;
        }

        public Criteria andAppvippriceEqualTo(Float value) {
            addCriterion("AppVipPrice =", value, "appvipprice");
            return (Criteria) this;
        }

        public Criteria andAppvippriceNotEqualTo(Float value) {
            addCriterion("AppVipPrice <>", value, "appvipprice");
            return (Criteria) this;
        }

        public Criteria andAppvippriceGreaterThan(Float value) {
            addCriterion("AppVipPrice >", value, "appvipprice");
            return (Criteria) this;
        }

        public Criteria andAppvippriceGreaterThanOrEqualTo(Float value) {
            addCriterion("AppVipPrice >=", value, "appvipprice");
            return (Criteria) this;
        }

        public Criteria andAppvippriceLessThan(Float value) {
            addCriterion("AppVipPrice <", value, "appvipprice");
            return (Criteria) this;
        }

        public Criteria andAppvippriceLessThanOrEqualTo(Float value) {
            addCriterion("AppVipPrice <=", value, "appvipprice");
            return (Criteria) this;
        }

        public Criteria andAppvippriceIn(List<Float> values) {
            addCriterion("AppVipPrice in", values, "appvipprice");
            return (Criteria) this;
        }

        public Criteria andAppvippriceNotIn(List<Float> values) {
            addCriterion("AppVipPrice not in", values, "appvipprice");
            return (Criteria) this;
        }

        public Criteria andAppvippriceBetween(Float value1, Float value2) {
            addCriterion("AppVipPrice between", value1, value2, "appvipprice");
            return (Criteria) this;
        }

        public Criteria andAppvippriceNotBetween(Float value1, Float value2) {
            addCriterion("AppVipPrice not between", value1, value2, "appvipprice");
            return (Criteria) this;
        }

        public Criteria andSubtitleIsNull() {
            addCriterion("SubTitle is null");
            return (Criteria) this;
        }

        public Criteria andSubtitleIsNotNull() {
            addCriterion("SubTitle is not null");
            return (Criteria) this;
        }

        public Criteria andSubtitleEqualTo(String value) {
            addCriterion("SubTitle =", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleNotEqualTo(String value) {
            addCriterion("SubTitle <>", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleGreaterThan(String value) {
            addCriterion("SubTitle >", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleGreaterThanOrEqualTo(String value) {
            addCriterion("SubTitle >=", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleLessThan(String value) {
            addCriterion("SubTitle <", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleLessThanOrEqualTo(String value) {
            addCriterion("SubTitle <=", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleLike(String value) {
            addCriterion("SubTitle like", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleNotLike(String value) {
            addCriterion("SubTitle not like", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleIn(List<String> values) {
            addCriterion("SubTitle in", values, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleNotIn(List<String> values) {
            addCriterion("SubTitle not in", values, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleBetween(String value1, String value2) {
            addCriterion("SubTitle between", value1, value2, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleNotBetween(String value1, String value2) {
            addCriterion("SubTitle not between", value1, value2, "subtitle");
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