package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConcernExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConcernExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("Title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("Title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("Title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("Title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("Title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("Title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("Title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("Title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("Title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("Title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("Title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("Title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("Title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("Title not between", value1, value2, "title");
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

        public Criteria andShopimgurlIsNull() {
            addCriterion("ShopImgUrl is null");
            return (Criteria) this;
        }

        public Criteria andShopimgurlIsNotNull() {
            addCriterion("ShopImgUrl is not null");
            return (Criteria) this;
        }

        public Criteria andShopimgurlEqualTo(String value) {
            addCriterion("ShopImgUrl =", value, "shopimgurl");
            return (Criteria) this;
        }

        public Criteria andShopimgurlNotEqualTo(String value) {
            addCriterion("ShopImgUrl <>", value, "shopimgurl");
            return (Criteria) this;
        }

        public Criteria andShopimgurlGreaterThan(String value) {
            addCriterion("ShopImgUrl >", value, "shopimgurl");
            return (Criteria) this;
        }

        public Criteria andShopimgurlGreaterThanOrEqualTo(String value) {
            addCriterion("ShopImgUrl >=", value, "shopimgurl");
            return (Criteria) this;
        }

        public Criteria andShopimgurlLessThan(String value) {
            addCriterion("ShopImgUrl <", value, "shopimgurl");
            return (Criteria) this;
        }

        public Criteria andShopimgurlLessThanOrEqualTo(String value) {
            addCriterion("ShopImgUrl <=", value, "shopimgurl");
            return (Criteria) this;
        }

        public Criteria andShopimgurlLike(String value) {
            addCriterion("ShopImgUrl like", value, "shopimgurl");
            return (Criteria) this;
        }

        public Criteria andShopimgurlNotLike(String value) {
            addCriterion("ShopImgUrl not like", value, "shopimgurl");
            return (Criteria) this;
        }

        public Criteria andShopimgurlIn(List<String> values) {
            addCriterion("ShopImgUrl in", values, "shopimgurl");
            return (Criteria) this;
        }

        public Criteria andShopimgurlNotIn(List<String> values) {
            addCriterion("ShopImgUrl not in", values, "shopimgurl");
            return (Criteria) this;
        }

        public Criteria andShopimgurlBetween(String value1, String value2) {
            addCriterion("ShopImgUrl between", value1, value2, "shopimgurl");
            return (Criteria) this;
        }

        public Criteria andShopimgurlNotBetween(String value1, String value2) {
            addCriterion("ShopImgUrl not between", value1, value2, "shopimgurl");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("UserID is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("UserID is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("UserID =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("UserID <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("UserID >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("UserID >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("UserID <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("UserID <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("UserID in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("UserID not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("UserID between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("UserID not between", value1, value2, "userid");
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

        public Criteria andCommentcountIsNull() {
            addCriterion("CommentCount is null");
            return (Criteria) this;
        }

        public Criteria andCommentcountIsNotNull() {
            addCriterion("CommentCount is not null");
            return (Criteria) this;
        }

        public Criteria andCommentcountEqualTo(Integer value) {
            addCriterion("CommentCount =", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountNotEqualTo(Integer value) {
            addCriterion("CommentCount <>", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountGreaterThan(Integer value) {
            addCriterion("CommentCount >", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("CommentCount >=", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountLessThan(Integer value) {
            addCriterion("CommentCount <", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountLessThanOrEqualTo(Integer value) {
            addCriterion("CommentCount <=", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountIn(List<Integer> values) {
            addCriterion("CommentCount in", values, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountNotIn(List<Integer> values) {
            addCriterion("CommentCount not in", values, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountBetween(Integer value1, Integer value2) {
            addCriterion("CommentCount between", value1, value2, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountNotBetween(Integer value1, Integer value2) {
            addCriterion("CommentCount not between", value1, value2, "commentcount");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("AddTime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("AddTime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(Date value) {
            addCriterion("AddTime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(Date value) {
            addCriterion("AddTime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(Date value) {
            addCriterion("AddTime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("AddTime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(Date value) {
            addCriterion("AddTime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("AddTime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<Date> values) {
            addCriterion("AddTime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<Date> values) {
            addCriterion("AddTime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(Date value1, Date value2) {
            addCriterion("AddTime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("AddTime not between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andSkuidIsNull() {
            addCriterion("SkuID is null");
            return (Criteria) this;
        }

        public Criteria andSkuidIsNotNull() {
            addCriterion("SkuID is not null");
            return (Criteria) this;
        }

        public Criteria andSkuidEqualTo(Integer value) {
            addCriterion("SkuID =", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotEqualTo(Integer value) {
            addCriterion("SkuID <>", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidGreaterThan(Integer value) {
            addCriterion("SkuID >", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidGreaterThanOrEqualTo(Integer value) {
            addCriterion("SkuID >=", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidLessThan(Integer value) {
            addCriterion("SkuID <", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidLessThanOrEqualTo(Integer value) {
            addCriterion("SkuID <=", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidIn(List<Integer> values) {
            addCriterion("SkuID in", values, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotIn(List<Integer> values) {
            addCriterion("SkuID not in", values, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidBetween(Integer value1, Integer value2) {
            addCriterion("SkuID between", value1, value2, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotBetween(Integer value1, Integer value2) {
            addCriterion("SkuID not between", value1, value2, "skuid");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("Tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("Tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("Tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("Tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("Tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("Tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("Tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("Tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("Tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("Tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("Tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("Tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("Tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("Tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andConcerntypeIsNull() {
            addCriterion("ConcernType is null");
            return (Criteria) this;
        }

        public Criteria andConcerntypeIsNotNull() {
            addCriterion("ConcernType is not null");
            return (Criteria) this;
        }

        public Criteria andConcerntypeEqualTo(Integer value) {
            addCriterion("ConcernType =", value, "concerntype");
            return (Criteria) this;
        }

        public Criteria andConcerntypeNotEqualTo(Integer value) {
            addCriterion("ConcernType <>", value, "concerntype");
            return (Criteria) this;
        }

        public Criteria andConcerntypeGreaterThan(Integer value) {
            addCriterion("ConcernType >", value, "concerntype");
            return (Criteria) this;
        }

        public Criteria andConcerntypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ConcernType >=", value, "concerntype");
            return (Criteria) this;
        }

        public Criteria andConcerntypeLessThan(Integer value) {
            addCriterion("ConcernType <", value, "concerntype");
            return (Criteria) this;
        }

        public Criteria andConcerntypeLessThanOrEqualTo(Integer value) {
            addCriterion("ConcernType <=", value, "concerntype");
            return (Criteria) this;
        }

        public Criteria andConcerntypeIn(List<Integer> values) {
            addCriterion("ConcernType in", values, "concerntype");
            return (Criteria) this;
        }

        public Criteria andConcerntypeNotIn(List<Integer> values) {
            addCriterion("ConcernType not in", values, "concerntype");
            return (Criteria) this;
        }

        public Criteria andConcerntypeBetween(Integer value1, Integer value2) {
            addCriterion("ConcernType between", value1, value2, "concerntype");
            return (Criteria) this;
        }

        public Criteria andConcerntypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ConcernType not between", value1, value2, "concerntype");
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