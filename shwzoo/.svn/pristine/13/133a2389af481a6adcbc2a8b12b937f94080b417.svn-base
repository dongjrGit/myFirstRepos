package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoucherExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VoucherExample() {
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
            addCriterion("Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVouchercodeIsNull() {
            addCriterion("VoucherCode is null");
            return (Criteria) this;
        }

        public Criteria andVouchercodeIsNotNull() {
            addCriterion("VoucherCode is not null");
            return (Criteria) this;
        }

        public Criteria andVouchercodeEqualTo(String value) {
            addCriterion("VoucherCode =", value, "vouchercode");
            return (Criteria) this;
        }

        public Criteria andVouchercodeNotEqualTo(String value) {
            addCriterion("VoucherCode <>", value, "vouchercode");
            return (Criteria) this;
        }

        public Criteria andVouchercodeGreaterThan(String value) {
            addCriterion("VoucherCode >", value, "vouchercode");
            return (Criteria) this;
        }

        public Criteria andVouchercodeGreaterThanOrEqualTo(String value) {
            addCriterion("VoucherCode >=", value, "vouchercode");
            return (Criteria) this;
        }

        public Criteria andVouchercodeLessThan(String value) {
            addCriterion("VoucherCode <", value, "vouchercode");
            return (Criteria) this;
        }

        public Criteria andVouchercodeLessThanOrEqualTo(String value) {
            addCriterion("VoucherCode <=", value, "vouchercode");
            return (Criteria) this;
        }

        public Criteria andVouchercodeLike(String value) {
            addCriterion("VoucherCode like", value, "vouchercode");
            return (Criteria) this;
        }

        public Criteria andVouchercodeNotLike(String value) {
            addCriterion("VoucherCode not like", value, "vouchercode");
            return (Criteria) this;
        }

        public Criteria andVouchercodeIn(List<String> values) {
            addCriterion("VoucherCode in", values, "vouchercode");
            return (Criteria) this;
        }

        public Criteria andVouchercodeNotIn(List<String> values) {
            addCriterion("VoucherCode not in", values, "vouchercode");
            return (Criteria) this;
        }

        public Criteria andVouchercodeBetween(String value1, String value2) {
            addCriterion("VoucherCode between", value1, value2, "vouchercode");
            return (Criteria) this;
        }

        public Criteria andVouchercodeNotBetween(String value1, String value2) {
            addCriterion("VoucherCode not between", value1, value2, "vouchercode");
            return (Criteria) this;
        }

        public Criteria andVouchernameIsNull() {
            addCriterion("VoucherName is null");
            return (Criteria) this;
        }

        public Criteria andVouchernameIsNotNull() {
            addCriterion("VoucherName is not null");
            return (Criteria) this;
        }

        public Criteria andVouchernameEqualTo(String value) {
            addCriterion("VoucherName =", value, "vouchername");
            return (Criteria) this;
        }

        public Criteria andVouchernameNotEqualTo(String value) {
            addCriterion("VoucherName <>", value, "vouchername");
            return (Criteria) this;
        }

        public Criteria andVouchernameGreaterThan(String value) {
            addCriterion("VoucherName >", value, "vouchername");
            return (Criteria) this;
        }

        public Criteria andVouchernameGreaterThanOrEqualTo(String value) {
            addCriterion("VoucherName >=", value, "vouchername");
            return (Criteria) this;
        }

        public Criteria andVouchernameLessThan(String value) {
            addCriterion("VoucherName <", value, "vouchername");
            return (Criteria) this;
        }

        public Criteria andVouchernameLessThanOrEqualTo(String value) {
            addCriterion("VoucherName <=", value, "vouchername");
            return (Criteria) this;
        }

        public Criteria andVouchernameLike(String value) {
            addCriterion("VoucherName like", value, "vouchername");
            return (Criteria) this;
        }

        public Criteria andVouchernameNotLike(String value) {
            addCriterion("VoucherName not like", value, "vouchername");
            return (Criteria) this;
        }

        public Criteria andVouchernameIn(List<String> values) {
            addCriterion("VoucherName in", values, "vouchername");
            return (Criteria) this;
        }

        public Criteria andVouchernameNotIn(List<String> values) {
            addCriterion("VoucherName not in", values, "vouchername");
            return (Criteria) this;
        }

        public Criteria andVouchernameBetween(String value1, String value2) {
            addCriterion("VoucherName between", value1, value2, "vouchername");
            return (Criteria) this;
        }

        public Criteria andVouchernameNotBetween(String value1, String value2) {
            addCriterion("VoucherName not between", value1, value2, "vouchername");
            return (Criteria) this;
        }

        public Criteria andVouchervalueIsNull() {
            addCriterion("VoucherValue is null");
            return (Criteria) this;
        }

        public Criteria andVouchervalueIsNotNull() {
            addCriterion("VoucherValue is not null");
            return (Criteria) this;
        }

        public Criteria andVouchervalueEqualTo(Long value) {
            addCriterion("VoucherValue =", value, "vouchervalue");
            return (Criteria) this;
        }

        public Criteria andVouchervalueNotEqualTo(Long value) {
            addCriterion("VoucherValue <>", value, "vouchervalue");
            return (Criteria) this;
        }

        public Criteria andVouchervalueGreaterThan(Long value) {
            addCriterion("VoucherValue >", value, "vouchervalue");
            return (Criteria) this;
        }

        public Criteria andVouchervalueGreaterThanOrEqualTo(Long value) {
            addCriterion("VoucherValue >=", value, "vouchervalue");
            return (Criteria) this;
        }

        public Criteria andVouchervalueLessThan(Long value) {
            addCriterion("VoucherValue <", value, "vouchervalue");
            return (Criteria) this;
        }

        public Criteria andVouchervalueLessThanOrEqualTo(Long value) {
            addCriterion("VoucherValue <=", value, "vouchervalue");
            return (Criteria) this;
        }

        public Criteria andVouchervalueIn(List<Long> values) {
            addCriterion("VoucherValue in", values, "vouchervalue");
            return (Criteria) this;
        }

        public Criteria andVouchervalueNotIn(List<Long> values) {
            addCriterion("VoucherValue not in", values, "vouchervalue");
            return (Criteria) this;
        }

        public Criteria andVouchervalueBetween(Long value1, Long value2) {
            addCriterion("VoucherValue between", value1, value2, "vouchervalue");
            return (Criteria) this;
        }

        public Criteria andVouchervalueNotBetween(Long value1, Long value2) {
            addCriterion("VoucherValue not between", value1, value2, "vouchervalue");
            return (Criteria) this;
        }

        public Criteria andValidityIsNull() {
            addCriterion("Validity is null");
            return (Criteria) this;
        }

        public Criteria andValidityIsNotNull() {
            addCriterion("Validity is not null");
            return (Criteria) this;
        }

        public Criteria andValidityEqualTo(Date value) {
            addCriterion("Validity =", value, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityNotEqualTo(Date value) {
            addCriterion("Validity <>", value, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityGreaterThan(Date value) {
            addCriterion("Validity >", value, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityGreaterThanOrEqualTo(Date value) {
            addCriterion("Validity >=", value, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityLessThan(Date value) {
            addCriterion("Validity <", value, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityLessThanOrEqualTo(Date value) {
            addCriterion("Validity <=", value, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityIn(List<Date> values) {
            addCriterion("Validity in", values, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityNotIn(List<Date> values) {
            addCriterion("Validity not in", values, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityBetween(Date value1, Date value2) {
            addCriterion("Validity between", value1, value2, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityNotBetween(Date value1, Date value2) {
            addCriterion("Validity not between", value1, value2, "validity");
            return (Criteria) this;
        }

        public Criteria andQuotaIsNull() {
            addCriterion("Quota is null");
            return (Criteria) this;
        }

        public Criteria andQuotaIsNotNull() {
            addCriterion("Quota is not null");
            return (Criteria) this;
        }

        public Criteria andQuotaEqualTo(Long value) {
            addCriterion("Quota =", value, "quota");
            return (Criteria) this;
        }

        public Criteria andQuotaNotEqualTo(Long value) {
            addCriterion("Quota <>", value, "quota");
            return (Criteria) this;
        }

        public Criteria andQuotaGreaterThan(Long value) {
            addCriterion("Quota >", value, "quota");
            return (Criteria) this;
        }

        public Criteria andQuotaGreaterThanOrEqualTo(Long value) {
            addCriterion("Quota >=", value, "quota");
            return (Criteria) this;
        }

        public Criteria andQuotaLessThan(Long value) {
            addCriterion("Quota <", value, "quota");
            return (Criteria) this;
        }

        public Criteria andQuotaLessThanOrEqualTo(Long value) {
            addCriterion("Quota <=", value, "quota");
            return (Criteria) this;
        }

        public Criteria andQuotaIn(List<Long> values) {
            addCriterion("Quota in", values, "quota");
            return (Criteria) this;
        }

        public Criteria andQuotaNotIn(List<Long> values) {
            addCriterion("Quota not in", values, "quota");
            return (Criteria) this;
        }

        public Criteria andQuotaBetween(Long value1, Long value2) {
            addCriterion("Quota between", value1, value2, "quota");
            return (Criteria) this;
        }

        public Criteria andQuotaNotBetween(Long value1, Long value2) {
            addCriterion("Quota not between", value1, value2, "quota");
            return (Criteria) this;
        }

        public Criteria andBelongIsNull() {
            addCriterion("Belong is null");
            return (Criteria) this;
        }

        public Criteria andBelongIsNotNull() {
            addCriterion("Belong is not null");
            return (Criteria) this;
        }

        public Criteria andBelongEqualTo(String value) {
            addCriterion("Belong =", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotEqualTo(String value) {
            addCriterion("Belong <>", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongGreaterThan(String value) {
            addCriterion("Belong >", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongGreaterThanOrEqualTo(String value) {
            addCriterion("Belong >=", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLessThan(String value) {
            addCriterion("Belong <", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLessThanOrEqualTo(String value) {
            addCriterion("Belong <=", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLike(String value) {
            addCriterion("Belong like", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotLike(String value) {
            addCriterion("Belong not like", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongIn(List<String> values) {
            addCriterion("Belong in", values, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotIn(List<String> values) {
            addCriterion("Belong not in", values, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongBetween(String value1, String value2) {
            addCriterion("Belong between", value1, value2, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotBetween(String value1, String value2) {
            addCriterion("Belong not between", value1, value2, "belong");
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
        
        public Criteria andGroupcodeIsNull() {
            addCriterion("GroupCode is null");
            return (Criteria) this;
        }

        public Criteria andGroupcodeIsNotNull() {
            addCriterion("GroupCode is not null");
            return (Criteria) this;
        }

        public Criteria andGroupcodeEqualTo(String value) {
            addCriterion("GroupCode =", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeNotEqualTo(String value) {
            addCriterion("GroupCode <>", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeGreaterThan(String value) {
            addCriterion("GroupCode >", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeGreaterThanOrEqualTo(String value) {
            addCriterion("GroupCode >=", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeLessThan(String value) {
            addCriterion("GroupCode <", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeLessThanOrEqualTo(String value) {
            addCriterion("GroupCode <=", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeLike(String value) {
            addCriterion("GroupCode like", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeNotLike(String value) {
            addCriterion("GroupCode not like", value, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeIn(List<String> values) {
            addCriterion("GroupCode in", values, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeNotIn(List<String> values) {
            addCriterion("GroupCode not in", values, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeBetween(String value1, String value2) {
            addCriterion("GroupCode between", value1, value2, "groupcode");
            return (Criteria) this;
        }

        public Criteria andGroupcodeNotBetween(String value1, String value2) {
            addCriterion("GroupCode not between", value1, value2, "groupcode");
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