package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogisticscompanyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogisticscompanyExample() {
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

        public Criteria andShortnameIsNull() {
            addCriterion("ShortName is null");
            return (Criteria) this;
        }

        public Criteria andShortnameIsNotNull() {
            addCriterion("ShortName is not null");
            return (Criteria) this;
        }

        public Criteria andShortnameEqualTo(String value) {
            addCriterion("ShortName =", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameNotEqualTo(String value) {
            addCriterion("ShortName <>", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameGreaterThan(String value) {
            addCriterion("ShortName >", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameGreaterThanOrEqualTo(String value) {
            addCriterion("ShortName >=", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameLessThan(String value) {
            addCriterion("ShortName <", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameLessThanOrEqualTo(String value) {
            addCriterion("ShortName <=", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameLike(String value) {
            addCriterion("ShortName like", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameNotLike(String value) {
            addCriterion("ShortName not like", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameIn(List<String> values) {
            addCriterion("ShortName in", values, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameNotIn(List<String> values) {
            addCriterion("ShortName not in", values, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameBetween(String value1, String value2) {
            addCriterion("ShortName between", value1, value2, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameNotBetween(String value1, String value2) {
            addCriterion("ShortName not between", value1, value2, "shortname");
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