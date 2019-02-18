package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.List;

public class ApplyshopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApplyshopExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andShopnameIsNull() {
            addCriterion("shopname is null");
            return (Criteria) this;
        }

        public Criteria andShopnameIsNotNull() {
            addCriterion("shopname is not null");
            return (Criteria) this;
        }

        public Criteria andShopnameEqualTo(String value) {
            addCriterion("shopname =", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotEqualTo(String value) {
            addCriterion("shopname <>", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameGreaterThan(String value) {
            addCriterion("shopname >", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameGreaterThanOrEqualTo(String value) {
            addCriterion("shopname >=", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLessThan(String value) {
            addCriterion("shopname <", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLessThanOrEqualTo(String value) {
            addCriterion("shopname <=", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLike(String value) {
            addCriterion("shopname like", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotLike(String value) {
            addCriterion("shopname not like", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameIn(List<String> values) {
            addCriterion("shopname in", values, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotIn(List<String> values) {
            addCriterion("shopname not in", values, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameBetween(String value1, String value2) {
            addCriterion("shopname between", value1, value2, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotBetween(String value1, String value2) {
            addCriterion("shopname not between", value1, value2, "shopname");
            return (Criteria) this;
        }

        public Criteria andContactsnameIsNull() {
            addCriterion("contactsname is null");
            return (Criteria) this;
        }

        public Criteria andContactsnameIsNotNull() {
            addCriterion("contactsname is not null");
            return (Criteria) this;
        }

        public Criteria andContactsnameEqualTo(String value) {
            addCriterion("contactsname =", value, "contactsname");
            return (Criteria) this;
        }

        public Criteria andContactsnameNotEqualTo(String value) {
            addCriterion("contactsname <>", value, "contactsname");
            return (Criteria) this;
        }

        public Criteria andContactsnameGreaterThan(String value) {
            addCriterion("contactsname >", value, "contactsname");
            return (Criteria) this;
        }

        public Criteria andContactsnameGreaterThanOrEqualTo(String value) {
            addCriterion("contactsname >=", value, "contactsname");
            return (Criteria) this;
        }

        public Criteria andContactsnameLessThan(String value) {
            addCriterion("contactsname <", value, "contactsname");
            return (Criteria) this;
        }

        public Criteria andContactsnameLessThanOrEqualTo(String value) {
            addCriterion("contactsname <=", value, "contactsname");
            return (Criteria) this;
        }

        public Criteria andContactsnameLike(String value) {
            addCriterion("contactsname like", value, "contactsname");
            return (Criteria) this;
        }

        public Criteria andContactsnameNotLike(String value) {
            addCriterion("contactsname not like", value, "contactsname");
            return (Criteria) this;
        }

        public Criteria andContactsnameIn(List<String> values) {
            addCriterion("contactsname in", values, "contactsname");
            return (Criteria) this;
        }

        public Criteria andContactsnameNotIn(List<String> values) {
            addCriterion("contactsname not in", values, "contactsname");
            return (Criteria) this;
        }

        public Criteria andContactsnameBetween(String value1, String value2) {
            addCriterion("contactsname between", value1, value2, "contactsname");
            return (Criteria) this;
        }

        public Criteria andContactsnameNotBetween(String value1, String value2) {
            addCriterion("contactsname not between", value1, value2, "contactsname");
            return (Criteria) this;
        }

        public Criteria andPcodeIsNull() {
            addCriterion("pcode is null");
            return (Criteria) this;
        }

        public Criteria andPcodeIsNotNull() {
            addCriterion("pcode is not null");
            return (Criteria) this;
        }

        public Criteria andPcodeEqualTo(String value) {
            addCriterion("pcode =", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeNotEqualTo(String value) {
            addCriterion("pcode <>", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeGreaterThan(String value) {
            addCriterion("pcode >", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeGreaterThanOrEqualTo(String value) {
            addCriterion("pcode >=", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeLessThan(String value) {
            addCriterion("pcode <", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeLessThanOrEqualTo(String value) {
            addCriterion("pcode <=", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeLike(String value) {
            addCriterion("pcode like", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeNotLike(String value) {
            addCriterion("pcode not like", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeIn(List<String> values) {
            addCriterion("pcode in", values, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeNotIn(List<String> values) {
            addCriterion("pcode not in", values, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeBetween(String value1, String value2) {
            addCriterion("pcode between", value1, value2, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeNotBetween(String value1, String value2) {
            addCriterion("pcode not between", value1, value2, "pcode");
            return (Criteria) this;
        }

        public Criteria andCcodeIsNull() {
            addCriterion("ccode is null");
            return (Criteria) this;
        }

        public Criteria andCcodeIsNotNull() {
            addCriterion("ccode is not null");
            return (Criteria) this;
        }

        public Criteria andCcodeEqualTo(String value) {
            addCriterion("ccode =", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeNotEqualTo(String value) {
            addCriterion("ccode <>", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeGreaterThan(String value) {
            addCriterion("ccode >", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeGreaterThanOrEqualTo(String value) {
            addCriterion("ccode >=", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeLessThan(String value) {
            addCriterion("ccode <", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeLessThanOrEqualTo(String value) {
            addCriterion("ccode <=", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeLike(String value) {
            addCriterion("ccode like", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeNotLike(String value) {
            addCriterion("ccode not like", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeIn(List<String> values) {
            addCriterion("ccode in", values, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeNotIn(List<String> values) {
            addCriterion("ccode not in", values, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeBetween(String value1, String value2) {
            addCriterion("ccode between", value1, value2, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeNotBetween(String value1, String value2) {
            addCriterion("ccode not between", value1, value2, "ccode");
            return (Criteria) this;
        }

        public Criteria andAcodeIsNull() {
            addCriterion("acode is null");
            return (Criteria) this;
        }

        public Criteria andAcodeIsNotNull() {
            addCriterion("acode is not null");
            return (Criteria) this;
        }

        public Criteria andAcodeEqualTo(String value) {
            addCriterion("acode =", value, "acode");
            return (Criteria) this;
        }

        public Criteria andAcodeNotEqualTo(String value) {
            addCriterion("acode <>", value, "acode");
            return (Criteria) this;
        }

        public Criteria andAcodeGreaterThan(String value) {
            addCriterion("acode >", value, "acode");
            return (Criteria) this;
        }

        public Criteria andAcodeGreaterThanOrEqualTo(String value) {
            addCriterion("acode >=", value, "acode");
            return (Criteria) this;
        }

        public Criteria andAcodeLessThan(String value) {
            addCriterion("acode <", value, "acode");
            return (Criteria) this;
        }

        public Criteria andAcodeLessThanOrEqualTo(String value) {
            addCriterion("acode <=", value, "acode");
            return (Criteria) this;
        }

        public Criteria andAcodeLike(String value) {
            addCriterion("acode like", value, "acode");
            return (Criteria) this;
        }

        public Criteria andAcodeNotLike(String value) {
            addCriterion("acode not like", value, "acode");
            return (Criteria) this;
        }

        public Criteria andAcodeIn(List<String> values) {
            addCriterion("acode in", values, "acode");
            return (Criteria) this;
        }

        public Criteria andAcodeNotIn(List<String> values) {
            addCriterion("acode not in", values, "acode");
            return (Criteria) this;
        }

        public Criteria andAcodeBetween(String value1, String value2) {
            addCriterion("acode between", value1, value2, "acode");
            return (Criteria) this;
        }

        public Criteria andAcodeNotBetween(String value1, String value2) {
            addCriterion("acode not between", value1, value2, "acode");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andIscontactIsNull() {
            addCriterion("iscontact is null");
            return (Criteria) this;
        }

        public Criteria andIscontactIsNotNull() {
            addCriterion("iscontact is not null");
            return (Criteria) this;
        }

        public Criteria andIscontactEqualTo(Boolean value) {
            addCriterion("iscontact =", value, "iscontact");
            return (Criteria) this;
        }

        public Criteria andIscontactNotEqualTo(Boolean value) {
            addCriterion("iscontact <>", value, "iscontact");
            return (Criteria) this;
        }

        public Criteria andIscontactGreaterThan(Boolean value) {
            addCriterion("iscontact >", value, "iscontact");
            return (Criteria) this;
        }

        public Criteria andIscontactGreaterThanOrEqualTo(Boolean value) {
            addCriterion("iscontact >=", value, "iscontact");
            return (Criteria) this;
        }

        public Criteria andIscontactLessThan(Boolean value) {
            addCriterion("iscontact <", value, "iscontact");
            return (Criteria) this;
        }

        public Criteria andIscontactLessThanOrEqualTo(Boolean value) {
            addCriterion("iscontact <=", value, "iscontact");
            return (Criteria) this;
        }

        public Criteria andIscontactIn(List<Boolean> values) {
            addCriterion("iscontact in", values, "iscontact");
            return (Criteria) this;
        }

        public Criteria andIscontactNotIn(List<Boolean> values) {
            addCriterion("iscontact not in", values, "iscontact");
            return (Criteria) this;
        }

        public Criteria andIscontactBetween(Boolean value1, Boolean value2) {
            addCriterion("iscontact between", value1, value2, "iscontact");
            return (Criteria) this;
        }

        public Criteria andIscontactNotBetween(Boolean value1, Boolean value2) {
            addCriterion("iscontact not between", value1, value2, "iscontact");
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