package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.List;

public class TimesetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TimesetExample() {
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

        public Criteria andTimenameIsNull() {
            addCriterion("TimeName is null");
            return (Criteria) this;
        }

        public Criteria andTimenameIsNotNull() {
            addCriterion("TimeName is not null");
            return (Criteria) this;
        }

        public Criteria andTimenameEqualTo(String value) {
            addCriterion("TimeName =", value, "timename");
            return (Criteria) this;
        }

        public Criteria andTimenameNotEqualTo(String value) {
            addCriterion("TimeName <>", value, "timename");
            return (Criteria) this;
        }

        public Criteria andTimenameGreaterThan(String value) {
            addCriterion("TimeName >", value, "timename");
            return (Criteria) this;
        }

        public Criteria andTimenameGreaterThanOrEqualTo(String value) {
            addCriterion("TimeName >=", value, "timename");
            return (Criteria) this;
        }

        public Criteria andTimenameLessThan(String value) {
            addCriterion("TimeName <", value, "timename");
            return (Criteria) this;
        }

        public Criteria andTimenameLessThanOrEqualTo(String value) {
            addCriterion("TimeName <=", value, "timename");
            return (Criteria) this;
        }

        public Criteria andTimenameLike(String value) {
            addCriterion("TimeName like", value, "timename");
            return (Criteria) this;
        }

        public Criteria andTimenameNotLike(String value) {
            addCriterion("TimeName not like", value, "timename");
            return (Criteria) this;
        }

        public Criteria andTimenameIn(List<String> values) {
            addCriterion("TimeName in", values, "timename");
            return (Criteria) this;
        }

        public Criteria andTimenameNotIn(List<String> values) {
            addCriterion("TimeName not in", values, "timename");
            return (Criteria) this;
        }

        public Criteria andTimenameBetween(String value1, String value2) {
            addCriterion("TimeName between", value1, value2, "timename");
            return (Criteria) this;
        }

        public Criteria andTimenameNotBetween(String value1, String value2) {
            addCriterion("TimeName not between", value1, value2, "timename");
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

        public Criteria andTiemvalueIsNull() {
            addCriterion("TiemValue is null");
            return (Criteria) this;
        }

        public Criteria andTiemvalueIsNotNull() {
            addCriterion("TiemValue is not null");
            return (Criteria) this;
        }

        public Criteria andTiemvalueEqualTo(Integer value) {
            addCriterion("TiemValue =", value, "tiemvalue");
            return (Criteria) this;
        }

        public Criteria andTiemvalueNotEqualTo(Integer value) {
            addCriterion("TiemValue <>", value, "tiemvalue");
            return (Criteria) this;
        }

        public Criteria andTiemvalueGreaterThan(Integer value) {
            addCriterion("TiemValue >", value, "tiemvalue");
            return (Criteria) this;
        }

        public Criteria andTiemvalueGreaterThanOrEqualTo(Integer value) {
            addCriterion("TiemValue >=", value, "tiemvalue");
            return (Criteria) this;
        }

        public Criteria andTiemvalueLessThan(Integer value) {
            addCriterion("TiemValue <", value, "tiemvalue");
            return (Criteria) this;
        }

        public Criteria andTiemvalueLessThanOrEqualTo(Integer value) {
            addCriterion("TiemValue <=", value, "tiemvalue");
            return (Criteria) this;
        }

        public Criteria andTiemvalueIn(List<Integer> values) {
            addCriterion("TiemValue in", values, "tiemvalue");
            return (Criteria) this;
        }

        public Criteria andTiemvalueNotIn(List<Integer> values) {
            addCriterion("TiemValue not in", values, "tiemvalue");
            return (Criteria) this;
        }

        public Criteria andTiemvalueBetween(Integer value1, Integer value2) {
            addCriterion("TiemValue between", value1, value2, "tiemvalue");
            return (Criteria) this;
        }

        public Criteria andTiemvalueNotBetween(Integer value1, Integer value2) {
            addCriterion("TiemValue not between", value1, value2, "tiemvalue");
            return (Criteria) this;
        }

        public Criteria andTimeunitIsNull() {
            addCriterion("TimeUnit is null");
            return (Criteria) this;
        }

        public Criteria andTimeunitIsNotNull() {
            addCriterion("TimeUnit is not null");
            return (Criteria) this;
        }

        public Criteria andTimeunitEqualTo(String value) {
            addCriterion("TimeUnit =", value, "timeunit");
            return (Criteria) this;
        }

        public Criteria andTimeunitNotEqualTo(String value) {
            addCriterion("TimeUnit <>", value, "timeunit");
            return (Criteria) this;
        }

        public Criteria andTimeunitGreaterThan(String value) {
            addCriterion("TimeUnit >", value, "timeunit");
            return (Criteria) this;
        }

        public Criteria andTimeunitGreaterThanOrEqualTo(String value) {
            addCriterion("TimeUnit >=", value, "timeunit");
            return (Criteria) this;
        }

        public Criteria andTimeunitLessThan(String value) {
            addCriterion("TimeUnit <", value, "timeunit");
            return (Criteria) this;
        }

        public Criteria andTimeunitLessThanOrEqualTo(String value) {
            addCriterion("TimeUnit <=", value, "timeunit");
            return (Criteria) this;
        }

        public Criteria andTimeunitLike(String value) {
            addCriterion("TimeUnit like", value, "timeunit");
            return (Criteria) this;
        }

        public Criteria andTimeunitNotLike(String value) {
            addCriterion("TimeUnit not like", value, "timeunit");
            return (Criteria) this;
        }

        public Criteria andTimeunitIn(List<String> values) {
            addCriterion("TimeUnit in", values, "timeunit");
            return (Criteria) this;
        }

        public Criteria andTimeunitNotIn(List<String> values) {
            addCriterion("TimeUnit not in", values, "timeunit");
            return (Criteria) this;
        }

        public Criteria andTimeunitBetween(String value1, String value2) {
            addCriterion("TimeUnit between", value1, value2, "timeunit");
            return (Criteria) this;
        }

        public Criteria andTimeunitNotBetween(String value1, String value2) {
            addCriterion("TimeUnit not between", value1, value2, "timeunit");
            return (Criteria) this;
        }

        public Criteria andTimedescriptIsNull() {
            addCriterion("TimeDescript is null");
            return (Criteria) this;
        }

        public Criteria andTimedescriptIsNotNull() {
            addCriterion("TimeDescript is not null");
            return (Criteria) this;
        }

        public Criteria andTimedescriptEqualTo(String value) {
            addCriterion("TimeDescript =", value, "timedescript");
            return (Criteria) this;
        }

        public Criteria andTimedescriptNotEqualTo(String value) {
            addCriterion("TimeDescript <>", value, "timedescript");
            return (Criteria) this;
        }

        public Criteria andTimedescriptGreaterThan(String value) {
            addCriterion("TimeDescript >", value, "timedescript");
            return (Criteria) this;
        }

        public Criteria andTimedescriptGreaterThanOrEqualTo(String value) {
            addCriterion("TimeDescript >=", value, "timedescript");
            return (Criteria) this;
        }

        public Criteria andTimedescriptLessThan(String value) {
            addCriterion("TimeDescript <", value, "timedescript");
            return (Criteria) this;
        }

        public Criteria andTimedescriptLessThanOrEqualTo(String value) {
            addCriterion("TimeDescript <=", value, "timedescript");
            return (Criteria) this;
        }

        public Criteria andTimedescriptLike(String value) {
            addCriterion("TimeDescript like", value, "timedescript");
            return (Criteria) this;
        }

        public Criteria andTimedescriptNotLike(String value) {
            addCriterion("TimeDescript not like", value, "timedescript");
            return (Criteria) this;
        }

        public Criteria andTimedescriptIn(List<String> values) {
            addCriterion("TimeDescript in", values, "timedescript");
            return (Criteria) this;
        }

        public Criteria andTimedescriptNotIn(List<String> values) {
            addCriterion("TimeDescript not in", values, "timedescript");
            return (Criteria) this;
        }

        public Criteria andTimedescriptBetween(String value1, String value2) {
            addCriterion("TimeDescript between", value1, value2, "timedescript");
            return (Criteria) this;
        }

        public Criteria andTimedescriptNotBetween(String value1, String value2) {
            addCriterion("TimeDescript not between", value1, value2, "timedescript");
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