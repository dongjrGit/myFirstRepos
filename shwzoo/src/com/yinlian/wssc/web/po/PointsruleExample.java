package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PointsruleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PointsruleExample() {
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

        public Criteria andRulecodeIsNull() {
            addCriterion("RuleCode is null");
            return (Criteria) this;
        }

        public Criteria andRulecodeIsNotNull() {
            addCriterion("RuleCode is not null");
            return (Criteria) this;
        }

        public Criteria andRulecodeEqualTo(String value) {
            addCriterion("RuleCode =", value, "rulecode");
            return (Criteria) this;
        }

        public Criteria andRulecodeNotEqualTo(String value) {
            addCriterion("RuleCode <>", value, "rulecode");
            return (Criteria) this;
        }

        public Criteria andRulecodeGreaterThan(String value) {
            addCriterion("RuleCode >", value, "rulecode");
            return (Criteria) this;
        }

        public Criteria andRulecodeGreaterThanOrEqualTo(String value) {
            addCriterion("RuleCode >=", value, "rulecode");
            return (Criteria) this;
        }

        public Criteria andRulecodeLessThan(String value) {
            addCriterion("RuleCode <", value, "rulecode");
            return (Criteria) this;
        }

        public Criteria andRulecodeLessThanOrEqualTo(String value) {
            addCriterion("RuleCode <=", value, "rulecode");
            return (Criteria) this;
        }

        public Criteria andRulecodeLike(String value) {
            addCriterion("RuleCode like", value, "rulecode");
            return (Criteria) this;
        }

        public Criteria andRulecodeNotLike(String value) {
            addCriterion("RuleCode not like", value, "rulecode");
            return (Criteria) this;
        }

        public Criteria andRulecodeIn(List<String> values) {
            addCriterion("RuleCode in", values, "rulecode");
            return (Criteria) this;
        }

        public Criteria andRulecodeNotIn(List<String> values) {
            addCriterion("RuleCode not in", values, "rulecode");
            return (Criteria) this;
        }

        public Criteria andRulecodeBetween(String value1, String value2) {
            addCriterion("RuleCode between", value1, value2, "rulecode");
            return (Criteria) this;
        }

        public Criteria andRulecodeNotBetween(String value1, String value2) {
            addCriterion("RuleCode not between", value1, value2, "rulecode");
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

        public Criteria andValueIsNull() {
            addCriterion("Value is null");
            return (Criteria) this;
        }

        public Criteria andValueIsNotNull() {
            addCriterion("Value is not null");
            return (Criteria) this;
        }

        public Criteria andValueEqualTo(Long value) {
            addCriterion("Value =", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(Long value) {
            addCriterion("Value <>", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(Long value) {
            addCriterion("Value >", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(Long value) {
            addCriterion("Value >=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThan(Long value) {
            addCriterion("Value <", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(Long value) {
            addCriterion("Value <=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<Long> values) {
            addCriterion("Value in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<Long> values) {
            addCriterion("Value not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(Long value1, Long value2) {
            addCriterion("Value between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(Long value1, Long value2) {
            addCriterion("Value not between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andPointsIsNull() {
            addCriterion("Points is null");
            return (Criteria) this;
        }

        public Criteria andPointsIsNotNull() {
            addCriterion("Points is not null");
            return (Criteria) this;
        }

        public Criteria andPointsEqualTo(Integer value) {
            addCriterion("Points =", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotEqualTo(Integer value) {
            addCriterion("Points <>", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsGreaterThan(Integer value) {
            addCriterion("Points >", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsGreaterThanOrEqualTo(Integer value) {
            addCriterion("Points >=", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsLessThan(Integer value) {
            addCriterion("Points <", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsLessThanOrEqualTo(Integer value) {
            addCriterion("Points <=", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsIn(List<Integer> values) {
            addCriterion("Points in", values, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotIn(List<Integer> values) {
            addCriterion("Points not in", values, "points");
            return (Criteria) this;
        }

        public Criteria andPointsBetween(Integer value1, Integer value2) {
            addCriterion("Points between", value1, value2, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotBetween(Integer value1, Integer value2) {
            addCriterion("Points not between", value1, value2, "points");
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

        public Criteria andCreatetimeIsNull() {
            addCriterion("CreateTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("CreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("CreateTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("CreateTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("CreateTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CreateTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("CreateTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("CreateTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("CreateTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("CreateTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("CreateTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("CreateTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUserlevelIsNull() {
            addCriterion("UserLevel is null");
            return (Criteria) this;
        }

        public Criteria andUserlevelIsNotNull() {
            addCriterion("UserLevel is not null");
            return (Criteria) this;
        }

        public Criteria andUserlevelEqualTo(Integer value) {
            addCriterion("UserLevel =", value, "userlevel");
            return (Criteria) this;
        }

        public Criteria andUserlevelNotEqualTo(Integer value) {
            addCriterion("UserLevel <>", value, "userlevel");
            return (Criteria) this;
        }

        public Criteria andUserlevelGreaterThan(Integer value) {
            addCriterion("UserLevel >", value, "userlevel");
            return (Criteria) this;
        }

        public Criteria andUserlevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("UserLevel >=", value, "userlevel");
            return (Criteria) this;
        }

        public Criteria andUserlevelLessThan(Integer value) {
            addCriterion("UserLevel <", value, "userlevel");
            return (Criteria) this;
        }

        public Criteria andUserlevelLessThanOrEqualTo(Integer value) {
            addCriterion("UserLevel <=", value, "userlevel");
            return (Criteria) this;
        }

        public Criteria andUserlevelIn(List<Integer> values) {
            addCriterion("UserLevel in", values, "userlevel");
            return (Criteria) this;
        }

        public Criteria andUserlevelNotIn(List<Integer> values) {
            addCriterion("UserLevel not in", values, "userlevel");
            return (Criteria) this;
        }

        public Criteria andUserlevelBetween(Integer value1, Integer value2) {
            addCriterion("UserLevel between", value1, value2, "userlevel");
            return (Criteria) this;
        }

        public Criteria andUserlevelNotBetween(Integer value1, Integer value2) {
            addCriterion("UserLevel not between", value1, value2, "userlevel");
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