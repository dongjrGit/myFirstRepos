package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.List;

public class PaysetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PaysetExample() {
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

        public Criteria andPaynameIsNull() {
            addCriterion("PayName is null");
            return (Criteria) this;
        }

        public Criteria andPaynameIsNotNull() {
            addCriterion("PayName is not null");
            return (Criteria) this;
        }

        public Criteria andPaynameEqualTo(String value) {
            addCriterion("PayName =", value, "payname");
            return (Criteria) this;
        }

        public Criteria andPaynameNotEqualTo(String value) {
            addCriterion("PayName <>", value, "payname");
            return (Criteria) this;
        }

        public Criteria andPaynameGreaterThan(String value) {
            addCriterion("PayName >", value, "payname");
            return (Criteria) this;
        }

        public Criteria andPaynameGreaterThanOrEqualTo(String value) {
            addCriterion("PayName >=", value, "payname");
            return (Criteria) this;
        }

        public Criteria andPaynameLessThan(String value) {
            addCriterion("PayName <", value, "payname");
            return (Criteria) this;
        }

        public Criteria andPaynameLessThanOrEqualTo(String value) {
            addCriterion("PayName <=", value, "payname");
            return (Criteria) this;
        }

        public Criteria andPaynameLike(String value) {
            addCriterion("PayName like", value, "payname");
            return (Criteria) this;
        }

        public Criteria andPaynameNotLike(String value) {
            addCriterion("PayName not like", value, "payname");
            return (Criteria) this;
        }

        public Criteria andPaynameIn(List<String> values) {
            addCriterion("PayName in", values, "payname");
            return (Criteria) this;
        }

        public Criteria andPaynameNotIn(List<String> values) {
            addCriterion("PayName not in", values, "payname");
            return (Criteria) this;
        }

        public Criteria andPaynameBetween(String value1, String value2) {
            addCriterion("PayName between", value1, value2, "payname");
            return (Criteria) this;
        }

        public Criteria andPaynameNotBetween(String value1, String value2) {
            addCriterion("PayName not between", value1, value2, "payname");
            return (Criteria) this;
        }

        public Criteria andMoneynameIsNull() {
            addCriterion("MoneyName is null");
            return (Criteria) this;
        }

        public Criteria andMoneynameIsNotNull() {
            addCriterion("MoneyName is not null");
            return (Criteria) this;
        }

        public Criteria andMoneynameEqualTo(String value) {
            addCriterion("MoneyName =", value, "moneyname");
            return (Criteria) this;
        }

        public Criteria andMoneynameNotEqualTo(String value) {
            addCriterion("MoneyName <>", value, "moneyname");
            return (Criteria) this;
        }

        public Criteria andMoneynameGreaterThan(String value) {
            addCriterion("MoneyName >", value, "moneyname");
            return (Criteria) this;
        }

        public Criteria andMoneynameGreaterThanOrEqualTo(String value) {
            addCriterion("MoneyName >=", value, "moneyname");
            return (Criteria) this;
        }

        public Criteria andMoneynameLessThan(String value) {
            addCriterion("MoneyName <", value, "moneyname");
            return (Criteria) this;
        }

        public Criteria andMoneynameLessThanOrEqualTo(String value) {
            addCriterion("MoneyName <=", value, "moneyname");
            return (Criteria) this;
        }

        public Criteria andMoneynameLike(String value) {
            addCriterion("MoneyName like", value, "moneyname");
            return (Criteria) this;
        }

        public Criteria andMoneynameNotLike(String value) {
            addCriterion("MoneyName not like", value, "moneyname");
            return (Criteria) this;
        }

        public Criteria andMoneynameIn(List<String> values) {
            addCriterion("MoneyName in", values, "moneyname");
            return (Criteria) this;
        }

        public Criteria andMoneynameNotIn(List<String> values) {
            addCriterion("MoneyName not in", values, "moneyname");
            return (Criteria) this;
        }

        public Criteria andMoneynameBetween(String value1, String value2) {
            addCriterion("MoneyName between", value1, value2, "moneyname");
            return (Criteria) this;
        }

        public Criteria andMoneynameNotBetween(String value1, String value2) {
            addCriterion("MoneyName not between", value1, value2, "moneyname");
            return (Criteria) this;
        }

        public Criteria andPoundageIsNull() {
            addCriterion("Poundage is null");
            return (Criteria) this;
        }

        public Criteria andPoundageIsNotNull() {
            addCriterion("Poundage is not null");
            return (Criteria) this;
        }

        public Criteria andPoundageEqualTo(Float value) {
            addCriterion("Poundage =", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageNotEqualTo(Float value) {
            addCriterion("Poundage <>", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageGreaterThan(Float value) {
            addCriterion("Poundage >", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageGreaterThanOrEqualTo(Float value) {
            addCriterion("Poundage >=", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageLessThan(Float value) {
            addCriterion("Poundage <", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageLessThanOrEqualTo(Float value) {
            addCriterion("Poundage <=", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageIn(List<Float> values) {
            addCriterion("Poundage in", values, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageNotIn(List<Float> values) {
            addCriterion("Poundage not in", values, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageBetween(Float value1, Float value2) {
            addCriterion("Poundage between", value1, value2, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageNotBetween(Float value1, Float value2) {
            addCriterion("Poundage not between", value1, value2, "poundage");
            return (Criteria) this;
        }

        public Criteria andIspersentIsNull() {
            addCriterion("IsPersent is null");
            return (Criteria) this;
        }

        public Criteria andIspersentIsNotNull() {
            addCriterion("IsPersent is not null");
            return (Criteria) this;
        }

        public Criteria andIspersentEqualTo(Boolean value) {
            addCriterion("IsPersent =", value, "ispersent");
            return (Criteria) this;
        }

        public Criteria andIspersentNotEqualTo(Boolean value) {
            addCriterion("IsPersent <>", value, "ispersent");
            return (Criteria) this;
        }

        public Criteria andIspersentGreaterThan(Boolean value) {
            addCriterion("IsPersent >", value, "ispersent");
            return (Criteria) this;
        }

        public Criteria andIspersentGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsPersent >=", value, "ispersent");
            return (Criteria) this;
        }

        public Criteria andIspersentLessThan(Boolean value) {
            addCriterion("IsPersent <", value, "ispersent");
            return (Criteria) this;
        }

        public Criteria andIspersentLessThanOrEqualTo(Boolean value) {
            addCriterion("IsPersent <=", value, "ispersent");
            return (Criteria) this;
        }

        public Criteria andIspersentIn(List<Boolean> values) {
            addCriterion("IsPersent in", values, "ispersent");
            return (Criteria) this;
        }

        public Criteria andIspersentNotIn(List<Boolean> values) {
            addCriterion("IsPersent not in", values, "ispersent");
            return (Criteria) this;
        }

        public Criteria andIspersentBetween(Boolean value1, Boolean value2) {
            addCriterion("IsPersent between", value1, value2, "ispersent");
            return (Criteria) this;
        }

        public Criteria andIspersentNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsPersent not between", value1, value2, "ispersent");
            return (Criteria) this;
        }

        public Criteria andIsonlineIsNull() {
            addCriterion("IsOnline is null");
            return (Criteria) this;
        }

        public Criteria andIsonlineIsNotNull() {
            addCriterion("IsOnline is not null");
            return (Criteria) this;
        }

        public Criteria andIsonlineEqualTo(Boolean value) {
            addCriterion("IsOnline =", value, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineNotEqualTo(Boolean value) {
            addCriterion("IsOnline <>", value, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineGreaterThan(Boolean value) {
            addCriterion("IsOnline >", value, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsOnline >=", value, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineLessThan(Boolean value) {
            addCriterion("IsOnline <", value, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineLessThanOrEqualTo(Boolean value) {
            addCriterion("IsOnline <=", value, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineIn(List<Boolean> values) {
            addCriterion("IsOnline in", values, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineNotIn(List<Boolean> values) {
            addCriterion("IsOnline not in", values, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineBetween(Boolean value1, Boolean value2) {
            addCriterion("IsOnline between", value1, value2, "isonline");
            return (Criteria) this;
        }

        public Criteria andIsonlineNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsOnline not between", value1, value2, "isonline");
            return (Criteria) this;
        }

        public Criteria andInterfacetypeIsNull() {
            addCriterion("InterfaceType is null");
            return (Criteria) this;
        }

        public Criteria andInterfacetypeIsNotNull() {
            addCriterion("InterfaceType is not null");
            return (Criteria) this;
        }

        public Criteria andInterfacetypeEqualTo(Integer value) {
            addCriterion("InterfaceType =", value, "interfacetype");
            return (Criteria) this;
        }

        public Criteria andInterfacetypeNotEqualTo(Integer value) {
            addCriterion("InterfaceType <>", value, "interfacetype");
            return (Criteria) this;
        }

        public Criteria andInterfacetypeGreaterThan(Integer value) {
            addCriterion("InterfaceType >", value, "interfacetype");
            return (Criteria) this;
        }

        public Criteria andInterfacetypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("InterfaceType >=", value, "interfacetype");
            return (Criteria) this;
        }

        public Criteria andInterfacetypeLessThan(Integer value) {
            addCriterion("InterfaceType <", value, "interfacetype");
            return (Criteria) this;
        }

        public Criteria andInterfacetypeLessThanOrEqualTo(Integer value) {
            addCriterion("InterfaceType <=", value, "interfacetype");
            return (Criteria) this;
        }

        public Criteria andInterfacetypeIn(List<Integer> values) {
            addCriterion("InterfaceType in", values, "interfacetype");
            return (Criteria) this;
        }

        public Criteria andInterfacetypeNotIn(List<Integer> values) {
            addCriterion("InterfaceType not in", values, "interfacetype");
            return (Criteria) this;
        }

        public Criteria andInterfacetypeBetween(Integer value1, Integer value2) {
            addCriterion("InterfaceType between", value1, value2, "interfacetype");
            return (Criteria) this;
        }

        public Criteria andInterfacetypeNotBetween(Integer value1, Integer value2) {
            addCriterion("InterfaceType not between", value1, value2, "interfacetype");
            return (Criteria) this;
        }

        public Criteria andDisplayIsNull() {
            addCriterion("Display is null");
            return (Criteria) this;
        }

        public Criteria andDisplayIsNotNull() {
            addCriterion("Display is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayEqualTo(Integer value) {
            addCriterion("Display =", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayNotEqualTo(Integer value) {
            addCriterion("Display <>", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayGreaterThan(Integer value) {
            addCriterion("Display >", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayGreaterThanOrEqualTo(Integer value) {
            addCriterion("Display >=", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayLessThan(Integer value) {
            addCriterion("Display <", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayLessThanOrEqualTo(Integer value) {
            addCriterion("Display <=", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayIn(List<Integer> values) {
            addCriterion("Display in", values, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayNotIn(List<Integer> values) {
            addCriterion("Display not in", values, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayBetween(Integer value1, Integer value2) {
            addCriterion("Display between", value1, value2, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayNotBetween(Integer value1, Integer value2) {
            addCriterion("Display not between", value1, value2, "display");
            return (Criteria) this;
        }

        public Criteria andDiscriptionIsNull() {
            addCriterion("Discription is null");
            return (Criteria) this;
        }

        public Criteria andDiscriptionIsNotNull() {
            addCriterion("Discription is not null");
            return (Criteria) this;
        }

        public Criteria andDiscriptionEqualTo(String value) {
            addCriterion("Discription =", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionNotEqualTo(String value) {
            addCriterion("Discription <>", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionGreaterThan(String value) {
            addCriterion("Discription >", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionGreaterThanOrEqualTo(String value) {
            addCriterion("Discription >=", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionLessThan(String value) {
            addCriterion("Discription <", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionLessThanOrEqualTo(String value) {
            addCriterion("Discription <=", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionLike(String value) {
            addCriterion("Discription like", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionNotLike(String value) {
            addCriterion("Discription not like", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionIn(List<String> values) {
            addCriterion("Discription in", values, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionNotIn(List<String> values) {
            addCriterion("Discription not in", values, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionBetween(String value1, String value2) {
            addCriterion("Discription between", value1, value2, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionNotBetween(String value1, String value2) {
            addCriterion("Discription not between", value1, value2, "discription");
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