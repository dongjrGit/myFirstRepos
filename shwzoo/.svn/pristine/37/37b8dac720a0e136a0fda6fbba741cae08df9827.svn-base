package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DispatchingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DispatchingExample() {
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

        public Criteria andOrdergroupcodeIsNull() {
            addCriterion("OrderGroupCode is null");
            return (Criteria) this;
        }

        public Criteria andOrdergroupcodeIsNotNull() {
            addCriterion("OrderGroupCode is not null");
            return (Criteria) this;
        }

        public Criteria andOrdergroupcodeEqualTo(String value) {
            addCriterion("OrderGroupCode =", value, "ordergroupcode");
            return (Criteria) this;
        }

        public Criteria andOrdergroupcodeNotEqualTo(String value) {
            addCriterion("OrderGroupCode <>", value, "ordergroupcode");
            return (Criteria) this;
        }

        public Criteria andOrdergroupcodeGreaterThan(String value) {
            addCriterion("OrderGroupCode >", value, "ordergroupcode");
            return (Criteria) this;
        }

        public Criteria andOrdergroupcodeGreaterThanOrEqualTo(String value) {
            addCriterion("OrderGroupCode >=", value, "ordergroupcode");
            return (Criteria) this;
        }

        public Criteria andOrdergroupcodeLessThan(String value) {
            addCriterion("OrderGroupCode <", value, "ordergroupcode");
            return (Criteria) this;
        }

        public Criteria andOrdergroupcodeLessThanOrEqualTo(String value) {
            addCriterion("OrderGroupCode <=", value, "ordergroupcode");
            return (Criteria) this;
        }

        public Criteria andOrdergroupcodeLike(String value) {
            addCriterion("OrderGroupCode like", value, "ordergroupcode");
            return (Criteria) this;
        }

        public Criteria andOrdergroupcodeNotLike(String value) {
            addCriterion("OrderGroupCode not like", value, "ordergroupcode");
            return (Criteria) this;
        }

        public Criteria andOrdergroupcodeIn(List<String> values) {
            addCriterion("OrderGroupCode in", values, "ordergroupcode");
            return (Criteria) this;
        }

        public Criteria andOrdergroupcodeNotIn(List<String> values) {
            addCriterion("OrderGroupCode not in", values, "ordergroupcode");
            return (Criteria) this;
        }

        public Criteria andOrdergroupcodeBetween(String value1, String value2) {
            addCriterion("OrderGroupCode between", value1, value2, "ordergroupcode");
            return (Criteria) this;
        }

        public Criteria andOrdergroupcodeNotBetween(String value1, String value2) {
            addCriterion("OrderGroupCode not between", value1, value2, "ordergroupcode");
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

        public Criteria andDatetypeIsNull() {
            addCriterion("DateType is null");
            return (Criteria) this;
        }

        public Criteria andDatetypeIsNotNull() {
            addCriterion("DateType is not null");
            return (Criteria) this;
        }

        public Criteria andDatetypeEqualTo(Integer value) {
            addCriterion("DateType =", value, "datetype");
            return (Criteria) this;
        }

        public Criteria andDatetypeNotEqualTo(Integer value) {
            addCriterion("DateType <>", value, "datetype");
            return (Criteria) this;
        }

        public Criteria andDatetypeGreaterThan(Integer value) {
            addCriterion("DateType >", value, "datetype");
            return (Criteria) this;
        }

        public Criteria andDatetypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("DateType >=", value, "datetype");
            return (Criteria) this;
        }

        public Criteria andDatetypeLessThan(Integer value) {
            addCriterion("DateType <", value, "datetype");
            return (Criteria) this;
        }

        public Criteria andDatetypeLessThanOrEqualTo(Integer value) {
            addCriterion("DateType <=", value, "datetype");
            return (Criteria) this;
        }

        public Criteria andDatetypeIn(List<Integer> values) {
            addCriterion("DateType in", values, "datetype");
            return (Criteria) this;
        }

        public Criteria andDatetypeNotIn(List<Integer> values) {
            addCriterion("DateType not in", values, "datetype");
            return (Criteria) this;
        }

        public Criteria andDatetypeBetween(Integer value1, Integer value2) {
            addCriterion("DateType between", value1, value2, "datetype");
            return (Criteria) this;
        }

        public Criteria andDatetypeNotBetween(Integer value1, Integer value2) {
            addCriterion("DateType not between", value1, value2, "datetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeIsNull() {
            addCriterion("TimeType is null");
            return (Criteria) this;
        }

        public Criteria andTimetypeIsNotNull() {
            addCriterion("TimeType is not null");
            return (Criteria) this;
        }

        public Criteria andTimetypeEqualTo(Integer value) {
            addCriterion("TimeType =", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeNotEqualTo(Integer value) {
            addCriterion("TimeType <>", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeGreaterThan(Integer value) {
            addCriterion("TimeType >", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("TimeType >=", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeLessThan(Integer value) {
            addCriterion("TimeType <", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeLessThanOrEqualTo(Integer value) {
            addCriterion("TimeType <=", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeIn(List<Integer> values) {
            addCriterion("TimeType in", values, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeNotIn(List<Integer> values) {
            addCriterion("TimeType not in", values, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeBetween(Integer value1, Integer value2) {
            addCriterion("TimeType between", value1, value2, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeNotBetween(Integer value1, Integer value2) {
            addCriterion("TimeType not between", value1, value2, "timetype");
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