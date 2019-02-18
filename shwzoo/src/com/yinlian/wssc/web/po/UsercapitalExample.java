package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.List;

public class UsercapitalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UsercapitalExample() {
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

        public Criteria andBalanceIsNull() {
            addCriterion("Balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("Balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(Float value) {
            addCriterion("Balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(Float value) {
            addCriterion("Balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(Float value) {
            addCriterion("Balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(Float value) {
            addCriterion("Balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(Float value) {
            addCriterion("Balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(Float value) {
            addCriterion("Balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<Float> values) {
            addCriterion("Balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<Float> values) {
            addCriterion("Balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(Float value1, Float value2) {
            addCriterion("Balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(Float value1, Float value2) {
            addCriterion("Balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyIsNull() {
            addCriterion("FreezeMoney is null");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyIsNotNull() {
            addCriterion("FreezeMoney is not null");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyEqualTo(Float value) {
            addCriterion("FreezeMoney =", value, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyNotEqualTo(Float value) {
            addCriterion("FreezeMoney <>", value, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyGreaterThan(Float value) {
            addCriterion("FreezeMoney >", value, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyGreaterThanOrEqualTo(Float value) {
            addCriterion("FreezeMoney >=", value, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyLessThan(Float value) {
            addCriterion("FreezeMoney <", value, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyLessThanOrEqualTo(Float value) {
            addCriterion("FreezeMoney <=", value, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyIn(List<Float> values) {
            addCriterion("FreezeMoney in", values, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyNotIn(List<Float> values) {
            addCriterion("FreezeMoney not in", values, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyBetween(Float value1, Float value2) {
            addCriterion("FreezeMoney between", value1, value2, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyNotBetween(Float value1, Float value2) {
            addCriterion("FreezeMoney not between", value1, value2, "freezemoney");
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

        public Criteria andBondIsNull() {
            addCriterion("Bond is null");
            return (Criteria) this;
        }

        public Criteria andBondIsNotNull() {
            addCriterion("Bond is not null");
            return (Criteria) this;
        }

        public Criteria andBondEqualTo(Float value) {
            addCriterion("Bond =", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondNotEqualTo(Float value) {
            addCriterion("Bond <>", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondGreaterThan(Float value) {
            addCriterion("Bond >", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondGreaterThanOrEqualTo(Float value) {
            addCriterion("Bond >=", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondLessThan(Float value) {
            addCriterion("Bond <", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondLessThanOrEqualTo(Float value) {
            addCriterion("Bond <=", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondIn(List<Float> values) {
            addCriterion("Bond in", values, "bond");
            return (Criteria) this;
        }

        public Criteria andBondNotIn(List<Float> values) {
            addCriterion("Bond not in", values, "bond");
            return (Criteria) this;
        }

        public Criteria andBondBetween(Float value1, Float value2) {
            addCriterion("Bond between", value1, value2, "bond");
            return (Criteria) this;
        }

        public Criteria andBondNotBetween(Float value1, Float value2) {
            addCriterion("Bond not between", value1, value2, "bond");
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