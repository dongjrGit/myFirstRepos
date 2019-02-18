package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpikeactivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpikeactivityExample() {
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

        public Criteria andSpikenumIsNull() {
            addCriterion("SpikeNum is null");
            return (Criteria) this;
        }

        public Criteria andSpikenumIsNotNull() {
            addCriterion("SpikeNum is not null");
            return (Criteria) this;
        }

        public Criteria andSpikenumEqualTo(String value) {
            addCriterion("SpikeNum =", value, "spikenum");
            return (Criteria) this;
        }

        public Criteria andSpikenumNotEqualTo(String value) {
            addCriterion("SpikeNum <>", value, "spikenum");
            return (Criteria) this;
        }

        public Criteria andSpikenumGreaterThan(String value) {
            addCriterion("SpikeNum >", value, "spikenum");
            return (Criteria) this;
        }

        public Criteria andSpikenumGreaterThanOrEqualTo(String value) {
            addCriterion("SpikeNum >=", value, "spikenum");
            return (Criteria) this;
        }

        public Criteria andSpikenumLessThan(String value) {
            addCriterion("SpikeNum <", value, "spikenum");
            return (Criteria) this;
        }

        public Criteria andSpikenumLessThanOrEqualTo(String value) {
            addCriterion("SpikeNum <=", value, "spikenum");
            return (Criteria) this;
        }

        public Criteria andSpikenumLike(String value) {
            addCriterion("SpikeNum like", value, "spikenum");
            return (Criteria) this;
        }

        public Criteria andSpikenumNotLike(String value) {
            addCriterion("SpikeNum not like", value, "spikenum");
            return (Criteria) this;
        }

        public Criteria andSpikenumIn(List<String> values) {
            addCriterion("SpikeNum in", values, "spikenum");
            return (Criteria) this;
        }

        public Criteria andSpikenumNotIn(List<String> values) {
            addCriterion("SpikeNum not in", values, "spikenum");
            return (Criteria) this;
        }

        public Criteria andSpikenumBetween(String value1, String value2) {
            addCriterion("SpikeNum between", value1, value2, "spikenum");
            return (Criteria) this;
        }

        public Criteria andSpikenumNotBetween(String value1, String value2) {
            addCriterion("SpikeNum not between", value1, value2, "spikenum");
            return (Criteria) this;
        }

        public Criteria andSpikenameIsNull() {
            addCriterion("SpikeName is null");
            return (Criteria) this;
        }

        public Criteria andSpikenameIsNotNull() {
            addCriterion("SpikeName is not null");
            return (Criteria) this;
        }

        public Criteria andSpikenameEqualTo(String value) {
            addCriterion("SpikeName =", value, "spikename");
            return (Criteria) this;
        }

        public Criteria andSpikenameNotEqualTo(String value) {
            addCriterion("SpikeName <>", value, "spikename");
            return (Criteria) this;
        }

        public Criteria andSpikenameGreaterThan(String value) {
            addCriterion("SpikeName >", value, "spikename");
            return (Criteria) this;
        }

        public Criteria andSpikenameGreaterThanOrEqualTo(String value) {
            addCriterion("SpikeName >=", value, "spikename");
            return (Criteria) this;
        }

        public Criteria andSpikenameLessThan(String value) {
            addCriterion("SpikeName <", value, "spikename");
            return (Criteria) this;
        }

        public Criteria andSpikenameLessThanOrEqualTo(String value) {
            addCriterion("SpikeName <=", value, "spikename");
            return (Criteria) this;
        }

        public Criteria andSpikenameLike(String value) {
            addCriterion("SpikeName like", value, "spikename");
            return (Criteria) this;
        }

        public Criteria andSpikenameNotLike(String value) {
            addCriterion("SpikeName not like", value, "spikename");
            return (Criteria) this;
        }

        public Criteria andSpikenameIn(List<String> values) {
            addCriterion("SpikeName in", values, "spikename");
            return (Criteria) this;
        }

        public Criteria andSpikenameNotIn(List<String> values) {
            addCriterion("SpikeName not in", values, "spikename");
            return (Criteria) this;
        }

        public Criteria andSpikenameBetween(String value1, String value2) {
            addCriterion("SpikeName between", value1, value2, "spikename");
            return (Criteria) this;
        }

        public Criteria andSpikenameNotBetween(String value1, String value2) {
            addCriterion("SpikeName not between", value1, value2, "spikename");
            return (Criteria) this;
        }

        public Criteria andSpiketypeIsNull() {
            addCriterion("SpikeType is null");
            return (Criteria) this;
        }

        public Criteria andSpiketypeIsNotNull() {
            addCriterion("SpikeType is not null");
            return (Criteria) this;
        }

        public Criteria andSpiketypeEqualTo(Integer value) {
            addCriterion("SpikeType =", value, "spiketype");
            return (Criteria) this;
        }

        public Criteria andSpiketypeNotEqualTo(Integer value) {
            addCriterion("SpikeType <>", value, "spiketype");
            return (Criteria) this;
        }

        public Criteria andSpiketypeGreaterThan(Integer value) {
            addCriterion("SpikeType >", value, "spiketype");
            return (Criteria) this;
        }

        public Criteria andSpiketypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("SpikeType >=", value, "spiketype");
            return (Criteria) this;
        }

        public Criteria andSpiketypeLessThan(Integer value) {
            addCriterion("SpikeType <", value, "spiketype");
            return (Criteria) this;
        }

        public Criteria andSpiketypeLessThanOrEqualTo(Integer value) {
            addCriterion("SpikeType <=", value, "spiketype");
            return (Criteria) this;
        }

        public Criteria andSpiketypeIn(List<Integer> values) {
            addCriterion("SpikeType in", values, "spiketype");
            return (Criteria) this;
        }

        public Criteria andSpiketypeNotIn(List<Integer> values) {
            addCriterion("SpikeType not in", values, "spiketype");
            return (Criteria) this;
        }

        public Criteria andSpiketypeBetween(Integer value1, Integer value2) {
            addCriterion("SpikeType between", value1, value2, "spiketype");
            return (Criteria) this;
        }

        public Criteria andSpiketypeNotBetween(Integer value1, Integer value2) {
            addCriterion("SpikeType not between", value1, value2, "spiketype");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("StartTime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("StartTime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Date value) {
            addCriterion("StartTime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Date value) {
            addCriterion("StartTime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Date value) {
            addCriterion("StartTime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("StartTime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Date value) {
            addCriterion("StartTime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("StartTime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Date> values) {
            addCriterion("StartTime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Date> values) {
            addCriterion("StartTime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Date value1, Date value2) {
            addCriterion("StartTime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("StartTime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("EndTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("EndTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("EndTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("EndTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("EndTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("EndTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("EndTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("EndTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("EndTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("EndTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("EndTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("EndTime not between", value1, value2, "endtime");
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

        public Criteria andOrderbyIsNull() {
            addCriterion("OrderBy is null");
            return (Criteria) this;
        }

        public Criteria andOrderbyIsNotNull() {
            addCriterion("OrderBy is not null");
            return (Criteria) this;
        }

        public Criteria andOrderbyEqualTo(Integer value) {
            addCriterion("OrderBy =", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyNotEqualTo(Integer value) {
            addCriterion("OrderBy <>", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyGreaterThan(Integer value) {
            addCriterion("OrderBy >", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyGreaterThanOrEqualTo(Integer value) {
            addCriterion("OrderBy >=", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyLessThan(Integer value) {
            addCriterion("OrderBy <", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyLessThanOrEqualTo(Integer value) {
            addCriterion("OrderBy <=", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyIn(List<Integer> values) {
            addCriterion("OrderBy in", values, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyNotIn(List<Integer> values) {
            addCriterion("OrderBy not in", values, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyBetween(Integer value1, Integer value2) {
            addCriterion("OrderBy between", value1, value2, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyNotBetween(Integer value1, Integer value2) {
            addCriterion("OrderBy not between", value1, value2, "orderby");
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

        public Criteria andCreateuseridIsNull() {
            addCriterion("CreateUserID is null");
            return (Criteria) this;
        }

        public Criteria andCreateuseridIsNotNull() {
            addCriterion("CreateUserID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateuseridEqualTo(Integer value) {
            addCriterion("CreateUserID =", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridNotEqualTo(Integer value) {
            addCriterion("CreateUserID <>", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridGreaterThan(Integer value) {
            addCriterion("CreateUserID >", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("CreateUserID >=", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridLessThan(Integer value) {
            addCriterion("CreateUserID <", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridLessThanOrEqualTo(Integer value) {
            addCriterion("CreateUserID <=", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridIn(List<Integer> values) {
            addCriterion("CreateUserID in", values, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridNotIn(List<Integer> values) {
            addCriterion("CreateUserID not in", values, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridBetween(Integer value1, Integer value2) {
            addCriterion("CreateUserID between", value1, value2, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridNotBetween(Integer value1, Integer value2) {
            addCriterion("CreateUserID not between", value1, value2, "createuserid");
            return (Criteria) this;
        }
        public Criteria andShopIdIsNull() {
            addCriterion("ShopID is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("ShopID is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Integer value) {
            addCriterion("ShopID =", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Integer value) {
            addCriterion("ShopID <>", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Integer value) {
            addCriterion("ShopID >", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ShopID >=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Integer value) {
            addCriterion("ShopID <", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Integer value) {
            addCriterion("ShopID <=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Integer> values) {
            addCriterion("ShopID in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Integer> values) {
            addCriterion("ShopID not in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Integer value1, Integer value2) {
            addCriterion("ShopID between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ShopID not between", value1, value2, "shopid");
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