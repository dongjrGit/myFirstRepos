package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopormemberstatushistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopormemberstatushistoryExample() {
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

        public Criteria andObjidIsNull() {
            addCriterion("ObjID is null");
            return (Criteria) this;
        }

        public Criteria andObjidIsNotNull() {
            addCriterion("ObjID is not null");
            return (Criteria) this;
        }

        public Criteria andObjidEqualTo(Integer value) {
            addCriterion("ObjID =", value, "objid");
            return (Criteria) this;
        }

        public Criteria andObjidNotEqualTo(Integer value) {
            addCriterion("ObjID <>", value, "objid");
            return (Criteria) this;
        }

        public Criteria andObjidGreaterThan(Integer value) {
            addCriterion("ObjID >", value, "objid");
            return (Criteria) this;
        }

        public Criteria andObjidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ObjID >=", value, "objid");
            return (Criteria) this;
        }

        public Criteria andObjidLessThan(Integer value) {
            addCriterion("ObjID <", value, "objid");
            return (Criteria) this;
        }

        public Criteria andObjidLessThanOrEqualTo(Integer value) {
            addCriterion("ObjID <=", value, "objid");
            return (Criteria) this;
        }

        public Criteria andObjidIn(List<Integer> values) {
            addCriterion("ObjID in", values, "objid");
            return (Criteria) this;
        }

        public Criteria andObjidNotIn(List<Integer> values) {
            addCriterion("ObjID not in", values, "objid");
            return (Criteria) this;
        }

        public Criteria andObjidBetween(Integer value1, Integer value2) {
            addCriterion("ObjID between", value1, value2, "objid");
            return (Criteria) this;
        }

        public Criteria andObjidNotBetween(Integer value1, Integer value2) {
            addCriterion("ObjID not between", value1, value2, "objid");
            return (Criteria) this;
        }

        public Criteria andCurrentidIsNull() {
            addCriterion("CurrentID is null");
            return (Criteria) this;
        }

        public Criteria andCurrentidIsNotNull() {
            addCriterion("CurrentID is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentidEqualTo(Integer value) {
            addCriterion("CurrentID =", value, "currentid");
            return (Criteria) this;
        }

        public Criteria andCurrentidNotEqualTo(Integer value) {
            addCriterion("CurrentID <>", value, "currentid");
            return (Criteria) this;
        }

        public Criteria andCurrentidGreaterThan(Integer value) {
            addCriterion("CurrentID >", value, "currentid");
            return (Criteria) this;
        }

        public Criteria andCurrentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("CurrentID >=", value, "currentid");
            return (Criteria) this;
        }

        public Criteria andCurrentidLessThan(Integer value) {
            addCriterion("CurrentID <", value, "currentid");
            return (Criteria) this;
        }

        public Criteria andCurrentidLessThanOrEqualTo(Integer value) {
            addCriterion("CurrentID <=", value, "currentid");
            return (Criteria) this;
        }

        public Criteria andCurrentidIn(List<Integer> values) {
            addCriterion("CurrentID in", values, "currentid");
            return (Criteria) this;
        }

        public Criteria andCurrentidNotIn(List<Integer> values) {
            addCriterion("CurrentID not in", values, "currentid");
            return (Criteria) this;
        }

        public Criteria andCurrentidBetween(Integer value1, Integer value2) {
            addCriterion("CurrentID between", value1, value2, "currentid");
            return (Criteria) this;
        }

        public Criteria andCurrentidNotBetween(Integer value1, Integer value2) {
            addCriterion("CurrentID not between", value1, value2, "currentid");
            return (Criteria) this;
        }

        public Criteria andCurrentnameIsNull() {
            addCriterion("CurrentName is null");
            return (Criteria) this;
        }

        public Criteria andCurrentnameIsNotNull() {
            addCriterion("CurrentName is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentnameEqualTo(String value) {
            addCriterion("CurrentName =", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameNotEqualTo(String value) {
            addCriterion("CurrentName <>", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameGreaterThan(String value) {
            addCriterion("CurrentName >", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameGreaterThanOrEqualTo(String value) {
            addCriterion("CurrentName >=", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameLessThan(String value) {
            addCriterion("CurrentName <", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameLessThanOrEqualTo(String value) {
            addCriterion("CurrentName <=", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameLike(String value) {
            addCriterion("CurrentName like", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameNotLike(String value) {
            addCriterion("CurrentName not like", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameIn(List<String> values) {
            addCriterion("CurrentName in", values, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameNotIn(List<String> values) {
            addCriterion("CurrentName not in", values, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameBetween(String value1, String value2) {
            addCriterion("CurrentName between", value1, value2, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameNotBetween(String value1, String value2) {
            addCriterion("CurrentName not between", value1, value2, "currentname");
            return (Criteria) this;
        }

        public Criteria andLastidIsNull() {
            addCriterion("LastID is null");
            return (Criteria) this;
        }

        public Criteria andLastidIsNotNull() {
            addCriterion("LastID is not null");
            return (Criteria) this;
        }

        public Criteria andLastidEqualTo(Integer value) {
            addCriterion("LastID =", value, "lastid");
            return (Criteria) this;
        }

        public Criteria andLastidNotEqualTo(Integer value) {
            addCriterion("LastID <>", value, "lastid");
            return (Criteria) this;
        }

        public Criteria andLastidGreaterThan(Integer value) {
            addCriterion("LastID >", value, "lastid");
            return (Criteria) this;
        }

        public Criteria andLastidGreaterThanOrEqualTo(Integer value) {
            addCriterion("LastID >=", value, "lastid");
            return (Criteria) this;
        }

        public Criteria andLastidLessThan(Integer value) {
            addCriterion("LastID <", value, "lastid");
            return (Criteria) this;
        }

        public Criteria andLastidLessThanOrEqualTo(Integer value) {
            addCriterion("LastID <=", value, "lastid");
            return (Criteria) this;
        }

        public Criteria andLastidIn(List<Integer> values) {
            addCriterion("LastID in", values, "lastid");
            return (Criteria) this;
        }

        public Criteria andLastidNotIn(List<Integer> values) {
            addCriterion("LastID not in", values, "lastid");
            return (Criteria) this;
        }

        public Criteria andLastidBetween(Integer value1, Integer value2) {
            addCriterion("LastID between", value1, value2, "lastid");
            return (Criteria) this;
        }

        public Criteria andLastidNotBetween(Integer value1, Integer value2) {
            addCriterion("LastID not between", value1, value2, "lastid");
            return (Criteria) this;
        }

        public Criteria andLastnameIsNull() {
            addCriterion("LastName is null");
            return (Criteria) this;
        }

        public Criteria andLastnameIsNotNull() {
            addCriterion("LastName is not null");
            return (Criteria) this;
        }

        public Criteria andLastnameEqualTo(String value) {
            addCriterion("LastName =", value, "lastname");
            return (Criteria) this;
        }

        public Criteria andLastnameNotEqualTo(String value) {
            addCriterion("LastName <>", value, "lastname");
            return (Criteria) this;
        }

        public Criteria andLastnameGreaterThan(String value) {
            addCriterion("LastName >", value, "lastname");
            return (Criteria) this;
        }

        public Criteria andLastnameGreaterThanOrEqualTo(String value) {
            addCriterion("LastName >=", value, "lastname");
            return (Criteria) this;
        }

        public Criteria andLastnameLessThan(String value) {
            addCriterion("LastName <", value, "lastname");
            return (Criteria) this;
        }

        public Criteria andLastnameLessThanOrEqualTo(String value) {
            addCriterion("LastName <=", value, "lastname");
            return (Criteria) this;
        }

        public Criteria andLastnameLike(String value) {
            addCriterion("LastName like", value, "lastname");
            return (Criteria) this;
        }

        public Criteria andLastnameNotLike(String value) {
            addCriterion("LastName not like", value, "lastname");
            return (Criteria) this;
        }

        public Criteria andLastnameIn(List<String> values) {
            addCriterion("LastName in", values, "lastname");
            return (Criteria) this;
        }

        public Criteria andLastnameNotIn(List<String> values) {
            addCriterion("LastName not in", values, "lastname");
            return (Criteria) this;
        }

        public Criteria andLastnameBetween(String value1, String value2) {
            addCriterion("LastName between", value1, value2, "lastname");
            return (Criteria) this;
        }

        public Criteria andLastnameNotBetween(String value1, String value2) {
            addCriterion("LastName not between", value1, value2, "lastname");
            return (Criteria) this;
        }

        public Criteria andOperatoridIsNull() {
            addCriterion("OperatorID is null");
            return (Criteria) this;
        }

        public Criteria andOperatoridIsNotNull() {
            addCriterion("OperatorID is not null");
            return (Criteria) this;
        }

        public Criteria andOperatoridEqualTo(Integer value) {
            addCriterion("OperatorID =", value, "operatorid");
            return (Criteria) this;
        }

        public Criteria andOperatoridNotEqualTo(Integer value) {
            addCriterion("OperatorID <>", value, "operatorid");
            return (Criteria) this;
        }

        public Criteria andOperatoridGreaterThan(Integer value) {
            addCriterion("OperatorID >", value, "operatorid");
            return (Criteria) this;
        }

        public Criteria andOperatoridGreaterThanOrEqualTo(Integer value) {
            addCriterion("OperatorID >=", value, "operatorid");
            return (Criteria) this;
        }

        public Criteria andOperatoridLessThan(Integer value) {
            addCriterion("OperatorID <", value, "operatorid");
            return (Criteria) this;
        }

        public Criteria andOperatoridLessThanOrEqualTo(Integer value) {
            addCriterion("OperatorID <=", value, "operatorid");
            return (Criteria) this;
        }

        public Criteria andOperatoridIn(List<Integer> values) {
            addCriterion("OperatorID in", values, "operatorid");
            return (Criteria) this;
        }

        public Criteria andOperatoridNotIn(List<Integer> values) {
            addCriterion("OperatorID not in", values, "operatorid");
            return (Criteria) this;
        }

        public Criteria andOperatoridBetween(Integer value1, Integer value2) {
            addCriterion("OperatorID between", value1, value2, "operatorid");
            return (Criteria) this;
        }

        public Criteria andOperatoridNotBetween(Integer value1, Integer value2) {
            addCriterion("OperatorID not between", value1, value2, "operatorid");
            return (Criteria) this;
        }

        public Criteria andOperatornameIsNull() {
            addCriterion("OperatorName is null");
            return (Criteria) this;
        }

        public Criteria andOperatornameIsNotNull() {
            addCriterion("OperatorName is not null");
            return (Criteria) this;
        }

        public Criteria andOperatornameEqualTo(String value) {
            addCriterion("OperatorName =", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameNotEqualTo(String value) {
            addCriterion("OperatorName <>", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameGreaterThan(String value) {
            addCriterion("OperatorName >", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameGreaterThanOrEqualTo(String value) {
            addCriterion("OperatorName >=", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameLessThan(String value) {
            addCriterion("OperatorName <", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameLessThanOrEqualTo(String value) {
            addCriterion("OperatorName <=", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameLike(String value) {
            addCriterion("OperatorName like", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameNotLike(String value) {
            addCriterion("OperatorName not like", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameIn(List<String> values) {
            addCriterion("OperatorName in", values, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameNotIn(List<String> values) {
            addCriterion("OperatorName not in", values, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameBetween(String value1, String value2) {
            addCriterion("OperatorName between", value1, value2, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameNotBetween(String value1, String value2) {
            addCriterion("OperatorName not between", value1, value2, "operatorname");
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