package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopExample() {
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

        public Criteria andShopnumIsNull() {
            addCriterion("ShopNum is null");
            return (Criteria) this;
        }

        public Criteria andShopnumIsNotNull() {
            addCriterion("ShopNum is not null");
            return (Criteria) this;
        }

        public Criteria andShopnumEqualTo(String value) {
            addCriterion("ShopNum =", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumNotEqualTo(String value) {
            addCriterion("ShopNum <>", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumGreaterThan(String value) {
            addCriterion("ShopNum >", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumGreaterThanOrEqualTo(String value) {
            addCriterion("ShopNum >=", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumLessThan(String value) {
            addCriterion("ShopNum <", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumLessThanOrEqualTo(String value) {
            addCriterion("ShopNum <=", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumLike(String value) {
            addCriterion("ShopNum like", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumNotLike(String value) {
            addCriterion("ShopNum not like", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumIn(List<String> values) {
            addCriterion("ShopNum in", values, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumNotIn(List<String> values) {
            addCriterion("ShopNum not in", values, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumBetween(String value1, String value2) {
            addCriterion("ShopNum between", value1, value2, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumNotBetween(String value1, String value2) {
            addCriterion("ShopNum not between", value1, value2, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopaddressIsNull() {
            addCriterion("ShopAddress is null");
            return (Criteria) this;
        }

        public Criteria andShopaddressIsNotNull() {
            addCriterion("ShopAddress is not null");
            return (Criteria) this;
        }

        public Criteria andShopaddressEqualTo(String value) {
            addCriterion("ShopAddress =", value, "shopaddress");
            return (Criteria) this;
        }

        public Criteria andShopaddressNotEqualTo(String value) {
            addCriterion("ShopAddress <>", value, "shopaddress");
            return (Criteria) this;
        }

        public Criteria andShopaddressGreaterThan(String value) {
            addCriterion("ShopAddress >", value, "shopaddress");
            return (Criteria) this;
        }

        public Criteria andShopaddressGreaterThanOrEqualTo(String value) {
            addCriterion("ShopAddress >=", value, "shopaddress");
            return (Criteria) this;
        }

        public Criteria andShopaddressLessThan(String value) {
            addCriterion("ShopAddress <", value, "shopaddress");
            return (Criteria) this;
        }

        public Criteria andShopaddressLessThanOrEqualTo(String value) {
            addCriterion("ShopAddress <=", value, "shopaddress");
            return (Criteria) this;
        }

        public Criteria andShopaddressLike(String value) {
            addCriterion("ShopAddress like", value, "shopaddress");
            return (Criteria) this;
        }

        public Criteria andShopaddressNotLike(String value) {
            addCriterion("ShopAddress not like", value, "shopaddress");
            return (Criteria) this;
        }

        public Criteria andShopaddressIn(List<String> values) {
            addCriterion("ShopAddress in", values, "shopaddress");
            return (Criteria) this;
        }

        public Criteria andShopaddressNotIn(List<String> values) {
            addCriterion("ShopAddress not in", values, "shopaddress");
            return (Criteria) this;
        }

        public Criteria andShopaddressBetween(String value1, String value2) {
            addCriterion("ShopAddress between", value1, value2, "shopaddress");
            return (Criteria) this;
        }

        public Criteria andShopaddressNotBetween(String value1, String value2) {
            addCriterion("ShopAddress not between", value1, value2, "shopaddress");
            return (Criteria) this;
        }

        public Criteria andCompanynameIsNull() {
            addCriterion("CompanyName is null");
            return (Criteria) this;
        }

        public Criteria andCompanynameIsNotNull() {
            addCriterion("CompanyName is not null");
            return (Criteria) this;
        }

        public Criteria andCompanynameEqualTo(String value) {
            addCriterion("CompanyName =", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotEqualTo(String value) {
            addCriterion("CompanyName <>", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameGreaterThan(String value) {
            addCriterion("CompanyName >", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameGreaterThanOrEqualTo(String value) {
            addCriterion("CompanyName >=", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameLessThan(String value) {
            addCriterion("CompanyName <", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameLessThanOrEqualTo(String value) {
            addCriterion("CompanyName <=", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameLike(String value) {
            addCriterion("CompanyName like", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotLike(String value) {
            addCriterion("CompanyName not like", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameIn(List<String> values) {
            addCriterion("CompanyName in", values, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotIn(List<String> values) {
            addCriterion("CompanyName not in", values, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameBetween(String value1, String value2) {
            addCriterion("CompanyName between", value1, value2, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotBetween(String value1, String value2) {
            addCriterion("CompanyName not between", value1, value2, "companyname");
            return (Criteria) this;
        }

        public Criteria andSupporttelIsNull() {
            addCriterion("SupportTel is null");
            return (Criteria) this;
        }

        public Criteria andSupporttelIsNotNull() {
            addCriterion("SupportTel is not null");
            return (Criteria) this;
        }

        public Criteria andSupporttelEqualTo(String value) {
            addCriterion("SupportTel =", value, "supporttel");
            return (Criteria) this;
        }

        public Criteria andSupporttelNotEqualTo(String value) {
            addCriterion("SupportTel <>", value, "supporttel");
            return (Criteria) this;
        }

        public Criteria andSupporttelGreaterThan(String value) {
            addCriterion("SupportTel >", value, "supporttel");
            return (Criteria) this;
        }

        public Criteria andSupporttelGreaterThanOrEqualTo(String value) {
            addCriterion("SupportTel >=", value, "supporttel");
            return (Criteria) this;
        }

        public Criteria andSupporttelLessThan(String value) {
            addCriterion("SupportTel <", value, "supporttel");
            return (Criteria) this;
        }

        public Criteria andSupporttelLessThanOrEqualTo(String value) {
            addCriterion("SupportTel <=", value, "supporttel");
            return (Criteria) this;
        }

        public Criteria andSupporttelLike(String value) {
            addCriterion("SupportTel like", value, "supporttel");
            return (Criteria) this;
        }

        public Criteria andSupporttelNotLike(String value) {
            addCriterion("SupportTel not like", value, "supporttel");
            return (Criteria) this;
        }

        public Criteria andSupporttelIn(List<String> values) {
            addCriterion("SupportTel in", values, "supporttel");
            return (Criteria) this;
        }

        public Criteria andSupporttelNotIn(List<String> values) {
            addCriterion("SupportTel not in", values, "supporttel");
            return (Criteria) this;
        }

        public Criteria andSupporttelBetween(String value1, String value2) {
            addCriterion("SupportTel between", value1, value2, "supporttel");
            return (Criteria) this;
        }

        public Criteria andSupporttelNotBetween(String value1, String value2) {
            addCriterion("SupportTel not between", value1, value2, "supporttel");
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

        public Criteria andIsflagshipIsNull() {
            addCriterion("IsFlagship is null");
            return (Criteria) this;
        }

        public Criteria andIsflagshipIsNotNull() {
            addCriterion("IsFlagship is not null");
            return (Criteria) this;
        }

        public Criteria andIsflagshipEqualTo(Boolean value) {
            addCriterion("IsFlagship =", value, "isflagship");
            return (Criteria) this;
        }

        public Criteria andIsflagshipNotEqualTo(Boolean value) {
            addCriterion("IsFlagship <>", value, "isflagship");
            return (Criteria) this;
        }

        public Criteria andIsflagshipGreaterThan(Boolean value) {
            addCriterion("IsFlagship >", value, "isflagship");
            return (Criteria) this;
        }

        public Criteria andIsflagshipGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsFlagship >=", value, "isflagship");
            return (Criteria) this;
        }

        public Criteria andIsflagshipLessThan(Boolean value) {
            addCriterion("IsFlagship <", value, "isflagship");
            return (Criteria) this;
        }

        public Criteria andIsflagshipLessThanOrEqualTo(Boolean value) {
            addCriterion("IsFlagship <=", value, "isflagship");
            return (Criteria) this;
        }

        public Criteria andIsflagshipIn(List<Boolean> values) {
            addCriterion("IsFlagship in", values, "isflagship");
            return (Criteria) this;
        }

        public Criteria andIsflagshipNotIn(List<Boolean> values) {
            addCriterion("IsFlagship not in", values, "isflagship");
            return (Criteria) this;
        }

        public Criteria andIsflagshipBetween(Boolean value1, Boolean value2) {
            addCriterion("IsFlagship between", value1, value2, "isflagship");
            return (Criteria) this;
        }

        public Criteria andIsflagshipNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsFlagship not between", value1, value2, "isflagship");
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

        public Criteria andMaxEmployeeIsNull() {
            addCriterion("Max_Employee is null");
            return (Criteria) this;
        }

        public Criteria andMaxEmployeeIsNotNull() {
            addCriterion("Max_Employee is not null");
            return (Criteria) this;
        }

        public Criteria andMaxEmployeeEqualTo(Integer value) {
            addCriterion("Max_Employee =", value, "maxEmployee");
            return (Criteria) this;
        }

        public Criteria andMaxEmployeeNotEqualTo(Integer value) {
            addCriterion("Max_Employee <>", value, "maxEmployee");
            return (Criteria) this;
        }

        public Criteria andMaxEmployeeGreaterThan(Integer value) {
            addCriterion("Max_Employee >", value, "maxEmployee");
            return (Criteria) this;
        }

        public Criteria andMaxEmployeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("Max_Employee >=", value, "maxEmployee");
            return (Criteria) this;
        }

        public Criteria andMaxEmployeeLessThan(Integer value) {
            addCriterion("Max_Employee <", value, "maxEmployee");
            return (Criteria) this;
        }

        public Criteria andMaxEmployeeLessThanOrEqualTo(Integer value) {
            addCriterion("Max_Employee <=", value, "maxEmployee");
            return (Criteria) this;
        }

        public Criteria andMaxEmployeeIn(List<Integer> values) {
            addCriterion("Max_Employee in", values, "maxEmployee");
            return (Criteria) this;
        }

        public Criteria andMaxEmployeeNotIn(List<Integer> values) {
            addCriterion("Max_Employee not in", values, "maxEmployee");
            return (Criteria) this;
        }

        public Criteria andMaxEmployeeBetween(Integer value1, Integer value2) {
            addCriterion("Max_Employee between", value1, value2, "maxEmployee");
            return (Criteria) this;
        }

        public Criteria andMaxEmployeeNotBetween(Integer value1, Integer value2) {
            addCriterion("Max_Employee not between", value1, value2, "maxEmployee");
            return (Criteria) this;
        }

        public Criteria andMaxRoleIsNull() {
            addCriterion("Max_Role is null");
            return (Criteria) this;
        }

        public Criteria andMaxRoleIsNotNull() {
            addCriterion("Max_Role is not null");
            return (Criteria) this;
        }

        public Criteria andMaxRoleEqualTo(Integer value) {
            addCriterion("Max_Role =", value, "maxRole");
            return (Criteria) this;
        }

        public Criteria andMaxRoleNotEqualTo(Integer value) {
            addCriterion("Max_Role <>", value, "maxRole");
            return (Criteria) this;
        }

        public Criteria andMaxRoleGreaterThan(Integer value) {
            addCriterion("Max_Role >", value, "maxRole");
            return (Criteria) this;
        }

        public Criteria andMaxRoleGreaterThanOrEqualTo(Integer value) {
            addCriterion("Max_Role >=", value, "maxRole");
            return (Criteria) this;
        }

        public Criteria andMaxRoleLessThan(Integer value) {
            addCriterion("Max_Role <", value, "maxRole");
            return (Criteria) this;
        }

        public Criteria andMaxRoleLessThanOrEqualTo(Integer value) {
            addCriterion("Max_Role <=", value, "maxRole");
            return (Criteria) this;
        }

        public Criteria andMaxRoleIn(List<Integer> values) {
            addCriterion("Max_Role in", values, "maxRole");
            return (Criteria) this;
        }

        public Criteria andMaxRoleNotIn(List<Integer> values) {
            addCriterion("Max_Role not in", values, "maxRole");
            return (Criteria) this;
        }

        public Criteria andMaxRoleBetween(Integer value1, Integer value2) {
            addCriterion("Max_Role between", value1, value2, "maxRole");
            return (Criteria) this;
        }

        public Criteria andMaxRoleNotBetween(Integer value1, Integer value2) {
            addCriterion("Max_Role not between", value1, value2, "maxRole");
            return (Criteria) this;
        }

        public Criteria andMarginIsNull() {
            addCriterion("Margin is null");
            return (Criteria) this;
        }

        public Criteria andMarginIsNotNull() {
            addCriterion("Margin is not null");
            return (Criteria) this;
        }

        public Criteria andMarginEqualTo(Float value) {
            addCriterion("Margin =", value, "margin");
            return (Criteria) this;
        }

        public Criteria andMarginNotEqualTo(Float value) {
            addCriterion("Margin <>", value, "margin");
            return (Criteria) this;
        }

        public Criteria andMarginGreaterThan(Float value) {
            addCriterion("Margin >", value, "margin");
            return (Criteria) this;
        }

        public Criteria andMarginGreaterThanOrEqualTo(Float value) {
            addCriterion("Margin >=", value, "margin");
            return (Criteria) this;
        }

        public Criteria andMarginLessThan(Float value) {
            addCriterion("Margin <", value, "margin");
            return (Criteria) this;
        }

        public Criteria andMarginLessThanOrEqualTo(Float value) {
            addCriterion("Margin <=", value, "margin");
            return (Criteria) this;
        }

        public Criteria andMarginIn(List<Float> values) {
            addCriterion("Margin in", values, "margin");
            return (Criteria) this;
        }

        public Criteria andMarginNotIn(List<Float> values) {
            addCriterion("Margin not in", values, "margin");
            return (Criteria) this;
        }

        public Criteria andMarginBetween(Float value1, Float value2) {
            addCriterion("Margin between", value1, value2, "margin");
            return (Criteria) this;
        }

        public Criteria andMarginNotBetween(Float value1, Float value2) {
            addCriterion("Margin not between", value1, value2, "margin");
            return (Criteria) this;
        }

        public Criteria andShoplevelidIsNull() {
            addCriterion("ShopLevelID is null");
            return (Criteria) this;
        }

        public Criteria andShoplevelidIsNotNull() {
            addCriterion("ShopLevelID is not null");
            return (Criteria) this;
        }

        public Criteria andShoplevelidEqualTo(Integer value) {
            addCriterion("ShopLevelID =", value, "shoplevelid");
            return (Criteria) this;
        }

        public Criteria andShoplevelidNotEqualTo(Integer value) {
            addCriterion("ShopLevelID <>", value, "shoplevelid");
            return (Criteria) this;
        }

        public Criteria andShoplevelidGreaterThan(Integer value) {
            addCriterion("ShopLevelID >", value, "shoplevelid");
            return (Criteria) this;
        }

        public Criteria andShoplevelidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ShopLevelID >=", value, "shoplevelid");
            return (Criteria) this;
        }

        public Criteria andShoplevelidLessThan(Integer value) {
            addCriterion("ShopLevelID <", value, "shoplevelid");
            return (Criteria) this;
        }

        public Criteria andShoplevelidLessThanOrEqualTo(Integer value) {
            addCriterion("ShopLevelID <=", value, "shoplevelid");
            return (Criteria) this;
        }

        public Criteria andShoplevelidIn(List<Integer> values) {
            addCriterion("ShopLevelID in", values, "shoplevelid");
            return (Criteria) this;
        }

        public Criteria andShoplevelidNotIn(List<Integer> values) {
            addCriterion("ShopLevelID not in", values, "shoplevelid");
            return (Criteria) this;
        }

        public Criteria andShoplevelidBetween(Integer value1, Integer value2) {
            addCriterion("ShopLevelID between", value1, value2, "shoplevelid");
            return (Criteria) this;
        }

        public Criteria andShoplevelidNotBetween(Integer value1, Integer value2) {
            addCriterion("ShopLevelID not between", value1, value2, "shoplevelid");
            return (Criteria) this;
        }

        public Criteria andSettlementtypeIsNull() {
            addCriterion("SettlementType is null");
            return (Criteria) this;
        }

        public Criteria andSettlementtypeIsNotNull() {
            addCriterion("SettlementType is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementtypeEqualTo(Integer value) {
            addCriterion("SettlementType =", value, "settlementtype");
            return (Criteria) this;
        }

        public Criteria andSettlementtypeNotEqualTo(Integer value) {
            addCriterion("SettlementType <>", value, "settlementtype");
            return (Criteria) this;
        }

        public Criteria andSettlementtypeGreaterThan(Integer value) {
            addCriterion("SettlementType >", value, "settlementtype");
            return (Criteria) this;
        }

        public Criteria andSettlementtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("SettlementType >=", value, "settlementtype");
            return (Criteria) this;
        }

        public Criteria andSettlementtypeLessThan(Integer value) {
            addCriterion("SettlementType <", value, "settlementtype");
            return (Criteria) this;
        }

        public Criteria andSettlementtypeLessThanOrEqualTo(Integer value) {
            addCriterion("SettlementType <=", value, "settlementtype");
            return (Criteria) this;
        }

        public Criteria andSettlementtypeIn(List<Integer> values) {
            addCriterion("SettlementType in", values, "settlementtype");
            return (Criteria) this;
        }

        public Criteria andSettlementtypeNotIn(List<Integer> values) {
            addCriterion("SettlementType not in", values, "settlementtype");
            return (Criteria) this;
        }

        public Criteria andSettlementtypeBetween(Integer value1, Integer value2) {
            addCriterion("SettlementType between", value1, value2, "settlementtype");
            return (Criteria) this;
        }

        public Criteria andSettlementtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("SettlementType not between", value1, value2, "settlementtype");
            return (Criteria) this;
        }

        public Criteria andWorkingcostIsNull() {
            addCriterion("WorkingCost is null");
            return (Criteria) this;
        }

        public Criteria andWorkingcostIsNotNull() {
            addCriterion("WorkingCost is not null");
            return (Criteria) this;
        }

        public Criteria andWorkingcostEqualTo(Float value) {
            addCriterion("WorkingCost =", value, "workingcost");
            return (Criteria) this;
        }

        public Criteria andWorkingcostNotEqualTo(Float value) {
            addCriterion("WorkingCost <>", value, "workingcost");
            return (Criteria) this;
        }

        public Criteria andWorkingcostGreaterThan(Float value) {
            addCriterion("WorkingCost >", value, "workingcost");
            return (Criteria) this;
        }

        public Criteria andWorkingcostGreaterThanOrEqualTo(Float value) {
            addCriterion("WorkingCost >=", value, "workingcost");
            return (Criteria) this;
        }

        public Criteria andWorkingcostLessThan(Float value) {
            addCriterion("WorkingCost <", value, "workingcost");
            return (Criteria) this;
        }

        public Criteria andWorkingcostLessThanOrEqualTo(Float value) {
            addCriterion("WorkingCost <=", value, "workingcost");
            return (Criteria) this;
        }

        public Criteria andWorkingcostIn(List<Float> values) {
            addCriterion("WorkingCost in", values, "workingcost");
            return (Criteria) this;
        }

        public Criteria andWorkingcostNotIn(List<Float> values) {
            addCriterion("WorkingCost not in", values, "workingcost");
            return (Criteria) this;
        }

        public Criteria andWorkingcostBetween(Float value1, Float value2) {
            addCriterion("WorkingCost between", value1, value2, "workingcost");
            return (Criteria) this;
        }

        public Criteria andWorkingcostNotBetween(Float value1, Float value2) {
            addCriterion("WorkingCost not between", value1, value2, "workingcost");
            return (Criteria) this;
        }

        public Criteria andRoyaltyrateIsNull() {
            addCriterion("RoyaltyRate is null");
            return (Criteria) this;
        }

        public Criteria andRoyaltyrateIsNotNull() {
            addCriterion("RoyaltyRate is not null");
            return (Criteria) this;
        }

        public Criteria andRoyaltyrateEqualTo(Float value) {
            addCriterion("RoyaltyRate =", value, "royaltyrate");
            return (Criteria) this;
        }

        public Criteria andRoyaltyrateNotEqualTo(Float value) {
            addCriterion("RoyaltyRate <>", value, "royaltyrate");
            return (Criteria) this;
        }

        public Criteria andRoyaltyrateGreaterThan(Float value) {
            addCriterion("RoyaltyRate >", value, "royaltyrate");
            return (Criteria) this;
        }

        public Criteria andRoyaltyrateGreaterThanOrEqualTo(Float value) {
            addCriterion("RoyaltyRate >=", value, "royaltyrate");
            return (Criteria) this;
        }

        public Criteria andRoyaltyrateLessThan(Float value) {
            addCriterion("RoyaltyRate <", value, "royaltyrate");
            return (Criteria) this;
        }

        public Criteria andRoyaltyrateLessThanOrEqualTo(Float value) {
            addCriterion("RoyaltyRate <=", value, "royaltyrate");
            return (Criteria) this;
        }

        public Criteria andRoyaltyrateIn(List<Float> values) {
            addCriterion("RoyaltyRate in", values, "royaltyrate");
            return (Criteria) this;
        }

        public Criteria andRoyaltyrateNotIn(List<Float> values) {
            addCriterion("RoyaltyRate not in", values, "royaltyrate");
            return (Criteria) this;
        }

        public Criteria andRoyaltyrateBetween(Float value1, Float value2) {
            addCriterion("RoyaltyRate between", value1, value2, "royaltyrate");
            return (Criteria) this;
        }

        public Criteria andRoyaltyrateNotBetween(Float value1, Float value2) {
            addCriterion("RoyaltyRate not between", value1, value2, "royaltyrate");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("Reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("Reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("Reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("Reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("Reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("Reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("Reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("Reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("Reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("Reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("Reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("Reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("Reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("Reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("Description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("Description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("Description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("Description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("Description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("Description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("Description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("Description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("Description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("Description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("Description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("Description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("Description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("Description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andCreattimeIsNull() {
            addCriterion("Creattime is null");
            return (Criteria) this;
        }

        public Criteria andCreattimeIsNotNull() {
            addCriterion("Creattime is not null");
            return (Criteria) this;
        }

        public Criteria andCreattimeEqualTo(Date value) {
            addCriterion("Creattime =", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotEqualTo(Date value) {
            addCriterion("Creattime <>", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeGreaterThan(Date value) {
            addCriterion("Creattime >", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeGreaterThanOrEqualTo(Date value) {
            addCriterion("Creattime >=", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeLessThan(Date value) {
            addCriterion("Creattime <", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeLessThanOrEqualTo(Date value) {
            addCriterion("Creattime <=", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeIn(List<Date> values) {
            addCriterion("Creattime in", values, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotIn(List<Date> values) {
            addCriterion("Creattime not in", values, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeBetween(Date value1, Date value2) {
            addCriterion("Creattime between", value1, value2, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotBetween(Date value1, Date value2) {
            addCriterion("Creattime not between", value1, value2, "creattime");
            return (Criteria) this;
        }

        public Criteria andIsjckIsNull() {
            addCriterion("isjck is null");
            return (Criteria) this;
        }

        public Criteria andIsjckIsNotNull() {
            addCriterion("isjck is not null");
            return (Criteria) this;
        }

        public Criteria andIsjckEqualTo(Boolean value) {
            addCriterion("isjck =", value, "isjck");
            return (Criteria) this;
        }

        public Criteria andIsjckNotEqualTo(Boolean value) {
            addCriterion("isjck <>", value, "isjck");
            return (Criteria) this;
        }

        public Criteria andIsjckGreaterThan(Boolean value) {
            addCriterion("isjck >", value, "isjck");
            return (Criteria) this;
        }

        public Criteria andIsjckGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isjck >=", value, "isjck");
            return (Criteria) this;
        }

        public Criteria andIsjckLessThan(Boolean value) {
            addCriterion("isjck <", value, "isjck");
            return (Criteria) this;
        }

        public Criteria andIsjckLessThanOrEqualTo(Boolean value) {
            addCriterion("isjck <=", value, "isjck");
            return (Criteria) this;
        }

        public Criteria andIsjckIn(List<Boolean> values) {
            addCriterion("isjck in", values, "isjck");
            return (Criteria) this;
        }

        public Criteria andIsjckNotIn(List<Boolean> values) {
            addCriterion("isjck not in", values, "isjck");
            return (Criteria) this;
        }

        public Criteria andIsjckBetween(Boolean value1, Boolean value2) {
            addCriterion("isjck between", value1, value2, "isjck");
            return (Criteria) this;
        }

        public Criteria andIsjckNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isjck not between", value1, value2, "isjck");
            return (Criteria) this;
        }

        public Criteria andIsfreeIsNull() {
            addCriterion("isfree is null");
            return (Criteria) this;
        }

        public Criteria andIsfreeIsNotNull() {
            addCriterion("isfree is not null");
            return (Criteria) this;
        }

        public Criteria andIsfreeEqualTo(Boolean value) {
            addCriterion("isfree =", value, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeNotEqualTo(Boolean value) {
            addCriterion("isfree <>", value, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeGreaterThan(Boolean value) {
            addCriterion("isfree >", value, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isfree >=", value, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeLessThan(Boolean value) {
            addCriterion("isfree <", value, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeLessThanOrEqualTo(Boolean value) {
            addCriterion("isfree <=", value, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeIn(List<Boolean> values) {
            addCriterion("isfree in", values, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeNotIn(List<Boolean> values) {
            addCriterion("isfree not in", values, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeBetween(Boolean value1, Boolean value2) {
            addCriterion("isfree between", value1, value2, "isfree");
            return (Criteria) this;
        }

        public Criteria andIsfreeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isfree not between", value1, value2, "isfree");
            return (Criteria) this;
        }

        public Criteria andIssupportIsNull() {
            addCriterion("issupport is null");
            return (Criteria) this;
        }

        public Criteria andIssupportIsNotNull() {
            addCriterion("issupport is not null");
            return (Criteria) this;
        }

        public Criteria andIssupportEqualTo(Boolean value) {
            addCriterion("issupport =", value, "issupport");
            return (Criteria) this;
        }

        public Criteria andIssupportNotEqualTo(Boolean value) {
            addCriterion("issupport <>", value, "issupport");
            return (Criteria) this;
        }

        public Criteria andIssupportGreaterThan(Boolean value) {
            addCriterion("issupport >", value, "issupport");
            return (Criteria) this;
        }

        public Criteria andIssupportGreaterThanOrEqualTo(Boolean value) {
            addCriterion("issupport >=", value, "issupport");
            return (Criteria) this;
        }

        public Criteria andIssupportLessThan(Boolean value) {
            addCriterion("issupport <", value, "issupport");
            return (Criteria) this;
        }

        public Criteria andIssupportLessThanOrEqualTo(Boolean value) {
            addCriterion("issupport <=", value, "issupport");
            return (Criteria) this;
        }

        public Criteria andIssupportIn(List<Boolean> values) {
            addCriterion("issupport in", values, "issupport");
            return (Criteria) this;
        }

        public Criteria andIssupportNotIn(List<Boolean> values) {
            addCriterion("issupport not in", values, "issupport");
            return (Criteria) this;
        }

        public Criteria andIssupportBetween(Boolean value1, Boolean value2) {
            addCriterion("issupport between", value1, value2, "issupport");
            return (Criteria) this;
        }

        public Criteria andIssupportNotBetween(Boolean value1, Boolean value2) {
            addCriterion("issupport not between", value1, value2, "issupport");
            return (Criteria) this;
        }

        public Criteria andShopcircleIsNull() {
            addCriterion("shopCircle is null");
            return (Criteria) this;
        }

        public Criteria andShopcircleIsNotNull() {
            addCriterion("shopCircle is not null");
            return (Criteria) this;
        }

        public Criteria andShopcircleEqualTo(String value) {
            addCriterion("shopCircle =", value, "shopcircle");
            return (Criteria) this;
        }

        public Criteria andShopcircleNotEqualTo(String value) {
            addCriterion("shopCircle <>", value, "shopcircle");
            return (Criteria) this;
        }

        public Criteria andShopcircleGreaterThan(String value) {
            addCriterion("shopCircle >", value, "shopcircle");
            return (Criteria) this;
        }

        public Criteria andShopcircleGreaterThanOrEqualTo(String value) {
            addCriterion("shopCircle >=", value, "shopcircle");
            return (Criteria) this;
        }

        public Criteria andShopcircleLessThan(String value) {
            addCriterion("shopCircle <", value, "shopcircle");
            return (Criteria) this;
        }

        public Criteria andShopcircleLessThanOrEqualTo(String value) {
            addCriterion("shopCircle <=", value, "shopcircle");
            return (Criteria) this;
        }

        public Criteria andShopcircleLike(String value) {
            addCriterion("shopCircle like", value, "shopcircle");
            return (Criteria) this;
        }

        public Criteria andShopcircleNotLike(String value) {
            addCriterion("shopCircle not like", value, "shopcircle");
            return (Criteria) this;
        }

        public Criteria andShopcircleIn(List<String> values) {
            addCriterion("shopCircle in", values, "shopcircle");
            return (Criteria) this;
        }

        public Criteria andShopcircleNotIn(List<String> values) {
            addCriterion("shopCircle not in", values, "shopcircle");
            return (Criteria) this;
        }

        public Criteria andShopcircleBetween(String value1, String value2) {
            addCriterion("shopCircle between", value1, value2, "shopcircle");
            return (Criteria) this;
        }

        public Criteria andShopcircleNotBetween(String value1, String value2) {
            addCriterion("shopCircle not between", value1, value2, "shopcircle");
            return (Criteria) this;
        }

        public Criteria andIsepIsNull() {
            addCriterion("IsEp is null");
            return (Criteria) this;
        }

        public Criteria andIsepIsNotNull() {
            addCriterion("IsEp is not null");
            return (Criteria) this;
        }

        public Criteria andIsepEqualTo(Boolean value) {
            addCriterion("IsEp =", value, "isep");
            return (Criteria) this;
        }

        public Criteria andIsepNotEqualTo(Boolean value) {
            addCriterion("IsEp <>", value, "isep");
            return (Criteria) this;
        }

        public Criteria andIsepGreaterThan(Boolean value) {
            addCriterion("IsEp >", value, "isep");
            return (Criteria) this;
        }

        public Criteria andIsepGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsEp >=", value, "isep");
            return (Criteria) this;
        }

        public Criteria andIsepLessThan(Boolean value) {
            addCriterion("IsEp <", value, "isep");
            return (Criteria) this;
        }

        public Criteria andIsepLessThanOrEqualTo(Boolean value) {
            addCriterion("IsEp <=", value, "isep");
            return (Criteria) this;
        }

        public Criteria andIsepIn(List<Boolean> values) {
            addCriterion("IsEp in", values, "isep");
            return (Criteria) this;
        }

        public Criteria andIsepNotIn(List<Boolean> values) {
            addCriterion("IsEp not in", values, "isep");
            return (Criteria) this;
        }

        public Criteria andIsepBetween(Boolean value1, Boolean value2) {
            addCriterion("IsEp between", value1, value2, "isep");
            return (Criteria) this;
        }

        public Criteria andIsepNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsEp not between", value1, value2, "isep");
            return (Criteria) this;
        }

        public Criteria andHodernameIsNull() {
            addCriterion("HoderName is null");
            return (Criteria) this;
        }

        public Criteria andHodernameIsNotNull() {
            addCriterion("HoderName is not null");
            return (Criteria) this;
        }

        public Criteria andHodernameEqualTo(String value) {
            addCriterion("HoderName =", value, "hodername");
            return (Criteria) this;
        }

        public Criteria andHodernameNotEqualTo(String value) {
            addCriterion("HoderName <>", value, "hodername");
            return (Criteria) this;
        }

        public Criteria andHodernameGreaterThan(String value) {
            addCriterion("HoderName >", value, "hodername");
            return (Criteria) this;
        }

        public Criteria andHodernameGreaterThanOrEqualTo(String value) {
            addCriterion("HoderName >=", value, "hodername");
            return (Criteria) this;
        }

        public Criteria andHodernameLessThan(String value) {
            addCriterion("HoderName <", value, "hodername");
            return (Criteria) this;
        }

        public Criteria andHodernameLessThanOrEqualTo(String value) {
            addCriterion("HoderName <=", value, "hodername");
            return (Criteria) this;
        }

        public Criteria andHodernameLike(String value) {
            addCriterion("HoderName like", value, "hodername");
            return (Criteria) this;
        }

        public Criteria andHodernameNotLike(String value) {
            addCriterion("HoderName not like", value, "hodername");
            return (Criteria) this;
        }

        public Criteria andHodernameIn(List<String> values) {
            addCriterion("HoderName in", values, "hodername");
            return (Criteria) this;
        }

        public Criteria andHodernameNotIn(List<String> values) {
            addCriterion("HoderName not in", values, "hodername");
            return (Criteria) this;
        }

        public Criteria andHodernameBetween(String value1, String value2) {
            addCriterion("HoderName between", value1, value2, "hodername");
            return (Criteria) this;
        }

        public Criteria andHodernameNotBetween(String value1, String value2) {
            addCriterion("HoderName not between", value1, value2, "hodername");
            return (Criteria) this;
        }

        public Criteria andBankcardnoIsNull() {
            addCriterion("BankCardNo is null");
            return (Criteria) this;
        }

        public Criteria andBankcardnoIsNotNull() {
            addCriterion("BankCardNo is not null");
            return (Criteria) this;
        }

        public Criteria andBankcardnoEqualTo(String value) {
            addCriterion("BankCardNo =", value, "bankcardno");
            return (Criteria) this;
        }

        public Criteria andBankcardnoNotEqualTo(String value) {
            addCriterion("BankCardNo <>", value, "bankcardno");
            return (Criteria) this;
        }

        public Criteria andBankcardnoGreaterThan(String value) {
            addCriterion("BankCardNo >", value, "bankcardno");
            return (Criteria) this;
        }

        public Criteria andBankcardnoGreaterThanOrEqualTo(String value) {
            addCriterion("BankCardNo >=", value, "bankcardno");
            return (Criteria) this;
        }

        public Criteria andBankcardnoLessThan(String value) {
            addCriterion("BankCardNo <", value, "bankcardno");
            return (Criteria) this;
        }

        public Criteria andBankcardnoLessThanOrEqualTo(String value) {
            addCriterion("BankCardNo <=", value, "bankcardno");
            return (Criteria) this;
        }

        public Criteria andBankcardnoLike(String value) {
            addCriterion("BankCardNo like", value, "bankcardno");
            return (Criteria) this;
        }

        public Criteria andBankcardnoNotLike(String value) {
            addCriterion("BankCardNo not like", value, "bankcardno");
            return (Criteria) this;
        }

        public Criteria andBankcardnoIn(List<String> values) {
            addCriterion("BankCardNo in", values, "bankcardno");
            return (Criteria) this;
        }

        public Criteria andBankcardnoNotIn(List<String> values) {
            addCriterion("BankCardNo not in", values, "bankcardno");
            return (Criteria) this;
        }

        public Criteria andBankcardnoBetween(String value1, String value2) {
            addCriterion("BankCardNo between", value1, value2, "bankcardno");
            return (Criteria) this;
        }

        public Criteria andBankcardnoNotBetween(String value1, String value2) {
            addCriterion("BankCardNo not between", value1, value2, "bankcardno");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNull() {
            addCriterion("BankName is null");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNotNull() {
            addCriterion("BankName is not null");
            return (Criteria) this;
        }

        public Criteria andBanknameEqualTo(String value) {
            addCriterion("BankName =", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotEqualTo(String value) {
            addCriterion("BankName <>", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThan(String value) {
            addCriterion("BankName >", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThanOrEqualTo(String value) {
            addCriterion("BankName >=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThan(String value) {
            addCriterion("BankName <", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThanOrEqualTo(String value) {
            addCriterion("BankName <=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLike(String value) {
            addCriterion("BankName like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotLike(String value) {
            addCriterion("BankName not like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameIn(List<String> values) {
            addCriterion("BankName in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotIn(List<String> values) {
            addCriterion("BankName not in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameBetween(String value1, String value2) {
            addCriterion("BankName between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotBetween(String value1, String value2) {
            addCriterion("BankName not between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andLinenoIsNull() {
            addCriterion("LineNo is null");
            return (Criteria) this;
        }

        public Criteria andLinenoIsNotNull() {
            addCriterion("LineNo is not null");
            return (Criteria) this;
        }

        public Criteria andLinenoEqualTo(String value) {
            addCriterion("LineNo =", value, "lineno");
            return (Criteria) this;
        }

        public Criteria andLinenoNotEqualTo(String value) {
            addCriterion("LineNo <>", value, "lineno");
            return (Criteria) this;
        }

        public Criteria andLinenoGreaterThan(String value) {
            addCriterion("LineNo >", value, "lineno");
            return (Criteria) this;
        }

        public Criteria andLinenoGreaterThanOrEqualTo(String value) {
            addCriterion("LineNo >=", value, "lineno");
            return (Criteria) this;
        }

        public Criteria andLinenoLessThan(String value) {
            addCriterion("LineNo <", value, "lineno");
            return (Criteria) this;
        }

        public Criteria andLinenoLessThanOrEqualTo(String value) {
            addCriterion("LineNo <=", value, "lineno");
            return (Criteria) this;
        }

        public Criteria andLinenoLike(String value) {
            addCriterion("LineNo like", value, "lineno");
            return (Criteria) this;
        }

        public Criteria andLinenoNotLike(String value) {
            addCriterion("LineNo not like", value, "lineno");
            return (Criteria) this;
        }

        public Criteria andLinenoIn(List<String> values) {
            addCriterion("LineNo in", values, "lineno");
            return (Criteria) this;
        }

        public Criteria andLinenoNotIn(List<String> values) {
            addCriterion("LineNo not in", values, "lineno");
            return (Criteria) this;
        }

        public Criteria andLinenoBetween(String value1, String value2) {
            addCriterion("LineNo between", value1, value2, "lineno");
            return (Criteria) this;
        }

        public Criteria andLinenoNotBetween(String value1, String value2) {
            addCriterion("LineNo not between", value1, value2, "lineno");
            return (Criteria) this;
        }

        public Criteria andBanktypeIsNull() {
            addCriterion("BankType is null");
            return (Criteria) this;
        }

        public Criteria andBanktypeIsNotNull() {
            addCriterion("BankType is not null");
            return (Criteria) this;
        }

        public Criteria andBanktypeEqualTo(String value) {
            addCriterion("BankType =", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeNotEqualTo(String value) {
            addCriterion("BankType <>", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeGreaterThan(String value) {
            addCriterion("BankType >", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeGreaterThanOrEqualTo(String value) {
            addCriterion("BankType >=", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeLessThan(String value) {
            addCriterion("BankType <", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeLessThanOrEqualTo(String value) {
            addCriterion("BankType <=", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeLike(String value) {
            addCriterion("BankType like", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeNotLike(String value) {
            addCriterion("BankType not like", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeIn(List<String> values) {
            addCriterion("BankType in", values, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeNotIn(List<String> values) {
            addCriterion("BankType not in", values, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeBetween(String value1, String value2) {
            addCriterion("BankType between", value1, value2, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeNotBetween(String value1, String value2) {
            addCriterion("BankType not between", value1, value2, "banktype");
            return (Criteria) this;
        }

        public Criteria andIssyncupIsNull() {
            addCriterion("IsSyncUp is null");
            return (Criteria) this;
        }

        public Criteria andIssyncupIsNotNull() {
            addCriterion("IsSyncUp is not null");
            return (Criteria) this;
        }

        public Criteria andIssyncupEqualTo(Boolean value) {
            addCriterion("IsSyncUp =", value, "issyncup");
            return (Criteria) this;
        }

        public Criteria andIssyncupNotEqualTo(Boolean value) {
            addCriterion("IsSyncUp <>", value, "issyncup");
            return (Criteria) this;
        }

        public Criteria andIssyncupGreaterThan(Boolean value) {
            addCriterion("IsSyncUp >", value, "issyncup");
            return (Criteria) this;
        }

        public Criteria andIssyncupGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsSyncUp >=", value, "issyncup");
            return (Criteria) this;
        }

        public Criteria andIssyncupLessThan(Boolean value) {
            addCriterion("IsSyncUp <", value, "issyncup");
            return (Criteria) this;
        }

        public Criteria andIssyncupLessThanOrEqualTo(Boolean value) {
            addCriterion("IsSyncUp <=", value, "issyncup");
            return (Criteria) this;
        }

        public Criteria andIssyncupIn(List<Boolean> values) {
            addCriterion("IsSyncUp in", values, "issyncup");
            return (Criteria) this;
        }

        public Criteria andIssyncupNotIn(List<Boolean> values) {
            addCriterion("IsSyncUp not in", values, "issyncup");
            return (Criteria) this;
        }

        public Criteria andIssyncupBetween(Boolean value1, Boolean value2) {
            addCriterion("IsSyncUp between", value1, value2, "issyncup");
            return (Criteria) this;
        }

        public Criteria andIssyncupNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsSyncUp not between", value1, value2, "issyncup");
            return (Criteria) this;
        }

        public Criteria andClassidIsNull() {
            addCriterion("ClassId is null");
            return (Criteria) this;
        }

        public Criteria andClassidIsNotNull() {
            addCriterion("ClassId is not null");
            return (Criteria) this;
        }

        public Criteria andClassidEqualTo(Integer value) {
            addCriterion("ClassId =", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotEqualTo(Integer value) {
            addCriterion("ClassId <>", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThan(Integer value) {
            addCriterion("ClassId >", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ClassId >=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThan(Integer value) {
            addCriterion("ClassId <", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThanOrEqualTo(Integer value) {
            addCriterion("ClassId <=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidIn(List<Integer> values) {
            addCriterion("ClassId in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotIn(List<Integer> values) {
            addCriterion("ClassId not in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidBetween(Integer value1, Integer value2) {
            addCriterion("ClassId between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotBetween(Integer value1, Integer value2) {
            addCriterion("ClassId not between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andAccounttypeIsNull() {
            addCriterion("AccountType is null");
            return (Criteria) this;
        }

        public Criteria andAccounttypeIsNotNull() {
            addCriterion("AccountType is not null");
            return (Criteria) this;
        }

        public Criteria andAccounttypeEqualTo(Integer value) {
            addCriterion("AccountType =", value, "accounttype");
            return (Criteria) this;
        }

        public Criteria andAccounttypeNotEqualTo(Integer value) {
            addCriterion("AccountType <>", value, "accounttype");
            return (Criteria) this;
        }

        public Criteria andAccounttypeGreaterThan(Integer value) {
            addCriterion("AccountType >", value, "accounttype");
            return (Criteria) this;
        }

        public Criteria andAccounttypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("AccountType >=", value, "accounttype");
            return (Criteria) this;
        }

        public Criteria andAccounttypeLessThan(Integer value) {
            addCriterion("AccountType <", value, "accounttype");
            return (Criteria) this;
        }

        public Criteria andAccounttypeLessThanOrEqualTo(Integer value) {
            addCriterion("AccountType <=", value, "accounttype");
            return (Criteria) this;
        }

        public Criteria andAccounttypeIn(List<Integer> values) {
            addCriterion("AccountType in", values, "accounttype");
            return (Criteria) this;
        }

        public Criteria andAccounttypeNotIn(List<Integer> values) {
            addCriterion("AccountType not in", values, "accounttype");
            return (Criteria) this;
        }

        public Criteria andAccounttypeBetween(Integer value1, Integer value2) {
            addCriterion("AccountType between", value1, value2, "accounttype");
            return (Criteria) this;
        }

        public Criteria andAccounttypeNotBetween(Integer value1, Integer value2) {
            addCriterion("AccountType not between", value1, value2, "accounttype");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(String value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(String value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(String value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(String value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(String value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(String value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLike(String value) {
            addCriterion("discount like", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotLike(String value) {
            addCriterion("discount not like", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<String> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<String> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(String value1, String value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(String value1, String value2) {
            addCriterion("discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andPopularityIsNull() {
            addCriterion("popularity is null");
            return (Criteria) this;
        }

        public Criteria andPopularityIsNotNull() {
            addCriterion("popularity is not null");
            return (Criteria) this;
        }

        public Criteria andPopularityEqualTo(Integer value) {
            addCriterion("popularity =", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityNotEqualTo(Integer value) {
            addCriterion("popularity <>", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityGreaterThan(Integer value) {
            addCriterion("popularity >", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityGreaterThanOrEqualTo(Integer value) {
            addCriterion("popularity >=", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityLessThan(Integer value) {
            addCriterion("popularity <", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityLessThanOrEqualTo(Integer value) {
            addCriterion("popularity <=", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityIn(List<Integer> values) {
            addCriterion("popularity in", values, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityNotIn(List<Integer> values) {
            addCriterion("popularity not in", values, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityBetween(Integer value1, Integer value2) {
            addCriterion("popularity between", value1, value2, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityNotBetween(Integer value1, Integer value2) {
            addCriterion("popularity not between", value1, value2, "popularity");
            return (Criteria) this;
        }

        public Criteria andConsumptionIsNull() {
            addCriterion("consumption is null");
            return (Criteria) this;
        }

        public Criteria andConsumptionIsNotNull() {
            addCriterion("consumption is not null");
            return (Criteria) this;
        }

        public Criteria andConsumptionEqualTo(Float value) {
            addCriterion("consumption =", value, "consumption");
            return (Criteria) this;
        }

        public Criteria andConsumptionNotEqualTo(Float value) {
            addCriterion("consumption <>", value, "consumption");
            return (Criteria) this;
        }

        public Criteria andConsumptionGreaterThan(Float value) {
            addCriterion("consumption >", value, "consumption");
            return (Criteria) this;
        }

        public Criteria andConsumptionGreaterThanOrEqualTo(Float value) {
            addCriterion("consumption >=", value, "consumption");
            return (Criteria) this;
        }

        public Criteria andConsumptionLessThan(Float value) {
            addCriterion("consumption <", value, "consumption");
            return (Criteria) this;
        }

        public Criteria andConsumptionLessThanOrEqualTo(Float value) {
            addCriterion("consumption <=", value, "consumption");
            return (Criteria) this;
        }

        public Criteria andConsumptionIn(List<Float> values) {
            addCriterion("consumption in", values, "consumption");
            return (Criteria) this;
        }

        public Criteria andConsumptionNotIn(List<Float> values) {
            addCriterion("consumption not in", values, "consumption");
            return (Criteria) this;
        }

        public Criteria andConsumptionBetween(Float value1, Float value2) {
            addCriterion("consumption between", value1, value2, "consumption");
            return (Criteria) this;
        }

        public Criteria andConsumptionNotBetween(Float value1, Float value2) {
            addCriterion("consumption not between", value1, value2, "consumption");
            return (Criteria) this;
        }

        public Criteria andCircleidIsNull() {
            addCriterion("circleid is null");
            return (Criteria) this;
        }

        public Criteria andCircleidIsNotNull() {
            addCriterion("circleid is not null");
            return (Criteria) this;
        }

        public Criteria andCircleidEqualTo(Integer value) {
            addCriterion("circleid =", value, "circleid");
            return (Criteria) this;
        }

        public Criteria andCircleidNotEqualTo(Integer value) {
            addCriterion("circleid <>", value, "circleid");
            return (Criteria) this;
        }

        public Criteria andCircleidGreaterThan(Integer value) {
            addCriterion("circleid >", value, "circleid");
            return (Criteria) this;
        }

        public Criteria andCircleidGreaterThanOrEqualTo(Integer value) {
            addCriterion("circleid >=", value, "circleid");
            return (Criteria) this;
        }

        public Criteria andCircleidLessThan(Integer value) {
            addCriterion("circleid <", value, "circleid");
            return (Criteria) this;
        }

        public Criteria andCircleidLessThanOrEqualTo(Integer value) {
            addCriterion("circleid <=", value, "circleid");
            return (Criteria) this;
        }

        public Criteria andCircleidIn(List<Integer> values) {
            addCriterion("circleid in", values, "circleid");
            return (Criteria) this;
        }

        public Criteria andCircleidNotIn(List<Integer> values) {
            addCriterion("circleid not in", values, "circleid");
            return (Criteria) this;
        }

        public Criteria andCircleidBetween(Integer value1, Integer value2) {
            addCriterion("circleid between", value1, value2, "circleid");
            return (Criteria) this;
        }

        public Criteria andCircleidNotBetween(Integer value1, Integer value2) {
            addCriterion("circleid not between", value1, value2, "circleid");
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