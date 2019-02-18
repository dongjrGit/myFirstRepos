package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticlesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticlesExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("Title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("Title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("Title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("Title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("Title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("Title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("Title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("Title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("Title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("Title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("Title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("Title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("Title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("Title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andDigestIsNull() {
            addCriterion("Digest is null");
            return (Criteria) this;
        }

        public Criteria andDigestIsNotNull() {
            addCriterion("Digest is not null");
            return (Criteria) this;
        }

        public Criteria andDigestEqualTo(String value) {
            addCriterion("Digest =", value, "digest");
            return (Criteria) this;
        }

        public Criteria andDigestNotEqualTo(String value) {
            addCriterion("Digest <>", value, "digest");
            return (Criteria) this;
        }

        public Criteria andDigestGreaterThan(String value) {
            addCriterion("Digest >", value, "digest");
            return (Criteria) this;
        }

        public Criteria andDigestGreaterThanOrEqualTo(String value) {
            addCriterion("Digest >=", value, "digest");
            return (Criteria) this;
        }

        public Criteria andDigestLessThan(String value) {
            addCriterion("Digest <", value, "digest");
            return (Criteria) this;
        }

        public Criteria andDigestLessThanOrEqualTo(String value) {
            addCriterion("Digest <=", value, "digest");
            return (Criteria) this;
        }

        public Criteria andDigestLike(String value) {
            addCriterion("Digest like", value, "digest");
            return (Criteria) this;
        }

        public Criteria andDigestNotLike(String value) {
            addCriterion("Digest not like", value, "digest");
            return (Criteria) this;
        }

        public Criteria andDigestIn(List<String> values) {
            addCriterion("Digest in", values, "digest");
            return (Criteria) this;
        }

        public Criteria andDigestNotIn(List<String> values) {
            addCriterion("Digest not in", values, "digest");
            return (Criteria) this;
        }

        public Criteria andDigestBetween(String value1, String value2) {
            addCriterion("Digest between", value1, value2, "digest");
            return (Criteria) this;
        }

        public Criteria andDigestNotBetween(String value1, String value2) {
            addCriterion("Digest not between", value1, value2, "digest");
            return (Criteria) this;
        }

        public Criteria andBytitleIsNull() {
            addCriterion("ByTitle is null");
            return (Criteria) this;
        }

        public Criteria andBytitleIsNotNull() {
            addCriterion("ByTitle is not null");
            return (Criteria) this;
        }

        public Criteria andBytitleEqualTo(String value) {
            addCriterion("ByTitle =", value, "bytitle");
            return (Criteria) this;
        }

        public Criteria andBytitleNotEqualTo(String value) {
            addCriterion("ByTitle <>", value, "bytitle");
            return (Criteria) this;
        }

        public Criteria andBytitleGreaterThan(String value) {
            addCriterion("ByTitle >", value, "bytitle");
            return (Criteria) this;
        }

        public Criteria andBytitleGreaterThanOrEqualTo(String value) {
            addCriterion("ByTitle >=", value, "bytitle");
            return (Criteria) this;
        }

        public Criteria andBytitleLessThan(String value) {
            addCriterion("ByTitle <", value, "bytitle");
            return (Criteria) this;
        }

        public Criteria andBytitleLessThanOrEqualTo(String value) {
            addCriterion("ByTitle <=", value, "bytitle");
            return (Criteria) this;
        }

        public Criteria andBytitleLike(String value) {
            addCriterion("ByTitle like", value, "bytitle");
            return (Criteria) this;
        }

        public Criteria andBytitleNotLike(String value) {
            addCriterion("ByTitle not like", value, "bytitle");
            return (Criteria) this;
        }

        public Criteria andBytitleIn(List<String> values) {
            addCriterion("ByTitle in", values, "bytitle");
            return (Criteria) this;
        }

        public Criteria andBytitleNotIn(List<String> values) {
            addCriterion("ByTitle not in", values, "bytitle");
            return (Criteria) this;
        }

        public Criteria andBytitleBetween(String value1, String value2) {
            addCriterion("ByTitle between", value1, value2, "bytitle");
            return (Criteria) this;
        }

        public Criteria andBytitleNotBetween(String value1, String value2) {
            addCriterion("ByTitle not between", value1, value2, "bytitle");
            return (Criteria) this;
        }

        public Criteria andClassfyidIsNull() {
            addCriterion("ClassfyID is null");
            return (Criteria) this;
        }

        public Criteria andClassfyidIsNotNull() {
            addCriterion("ClassfyID is not null");
            return (Criteria) this;
        }

        public Criteria andClassfyidEqualTo(Integer value) {
            addCriterion("ClassfyID =", value, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidNotEqualTo(Integer value) {
            addCriterion("ClassfyID <>", value, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidGreaterThan(Integer value) {
            addCriterion("ClassfyID >", value, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ClassfyID >=", value, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidLessThan(Integer value) {
            addCriterion("ClassfyID <", value, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidLessThanOrEqualTo(Integer value) {
            addCriterion("ClassfyID <=", value, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidIn(List<Integer> values) {
            addCriterion("ClassfyID in", values, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidNotIn(List<Integer> values) {
            addCriterion("ClassfyID not in", values, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidBetween(Integer value1, Integer value2) {
            addCriterion("ClassfyID between", value1, value2, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidNotBetween(Integer value1, Integer value2) {
            addCriterion("ClassfyID not between", value1, value2, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyIsNull() {
            addCriterion("Classfy is null");
            return (Criteria) this;
        }

        public Criteria andClassfyIsNotNull() {
            addCriterion("Classfy is not null");
            return (Criteria) this;
        }

        public Criteria andClassfyEqualTo(String value) {
            addCriterion("Classfy =", value, "classfy");
            return (Criteria) this;
        }

        public Criteria andClassfyNotEqualTo(String value) {
            addCriterion("Classfy <>", value, "classfy");
            return (Criteria) this;
        }

        public Criteria andClassfyGreaterThan(String value) {
            addCriterion("Classfy >", value, "classfy");
            return (Criteria) this;
        }

        public Criteria andClassfyGreaterThanOrEqualTo(String value) {
            addCriterion("Classfy >=", value, "classfy");
            return (Criteria) this;
        }

        public Criteria andClassfyLessThan(String value) {
            addCriterion("Classfy <", value, "classfy");
            return (Criteria) this;
        }

        public Criteria andClassfyLessThanOrEqualTo(String value) {
            addCriterion("Classfy <=", value, "classfy");
            return (Criteria) this;
        }

        public Criteria andClassfyLike(String value) {
            addCriterion("Classfy like", value, "classfy");
            return (Criteria) this;
        }

        public Criteria andClassfyNotLike(String value) {
            addCriterion("Classfy not like", value, "classfy");
            return (Criteria) this;
        }

        public Criteria andClassfyIn(List<String> values) {
            addCriterion("Classfy in", values, "classfy");
            return (Criteria) this;
        }

        public Criteria andClassfyNotIn(List<String> values) {
            addCriterion("Classfy not in", values, "classfy");
            return (Criteria) this;
        }

        public Criteria andClassfyBetween(String value1, String value2) {
            addCriterion("Classfy between", value1, value2, "classfy");
            return (Criteria) this;
        }

        public Criteria andClassfyNotBetween(String value1, String value2) {
            addCriterion("Classfy not between", value1, value2, "classfy");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNull() {
            addCriterion("SendTime is null");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNotNull() {
            addCriterion("SendTime is not null");
            return (Criteria) this;
        }

        public Criteria andSendtimeEqualTo(Date value) {
            addCriterion("SendTime =", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotEqualTo(Date value) {
            addCriterion("SendTime <>", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThan(Date value) {
            addCriterion("SendTime >", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SendTime >=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThan(Date value) {
            addCriterion("SendTime <", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThanOrEqualTo(Date value) {
            addCriterion("SendTime <=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeIn(List<Date> values) {
            addCriterion("SendTime in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotIn(List<Date> values) {
            addCriterion("SendTime not in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeBetween(Date value1, Date value2) {
            addCriterion("SendTime between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotBetween(Date value1, Date value2) {
            addCriterion("SendTime not between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andClicknumIsNull() {
            addCriterion("ClickNum is null");
            return (Criteria) this;
        }

        public Criteria andClicknumIsNotNull() {
            addCriterion("ClickNum is not null");
            return (Criteria) this;
        }

        public Criteria andClicknumEqualTo(Integer value) {
            addCriterion("ClickNum =", value, "clicknum");
            return (Criteria) this;
        }

        public Criteria andClicknumNotEqualTo(Integer value) {
            addCriterion("ClickNum <>", value, "clicknum");
            return (Criteria) this;
        }

        public Criteria andClicknumGreaterThan(Integer value) {
            addCriterion("ClickNum >", value, "clicknum");
            return (Criteria) this;
        }

        public Criteria andClicknumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ClickNum >=", value, "clicknum");
            return (Criteria) this;
        }

        public Criteria andClicknumLessThan(Integer value) {
            addCriterion("ClickNum <", value, "clicknum");
            return (Criteria) this;
        }

        public Criteria andClicknumLessThanOrEqualTo(Integer value) {
            addCriterion("ClickNum <=", value, "clicknum");
            return (Criteria) this;
        }

        public Criteria andClicknumIn(List<Integer> values) {
            addCriterion("ClickNum in", values, "clicknum");
            return (Criteria) this;
        }

        public Criteria andClicknumNotIn(List<Integer> values) {
            addCriterion("ClickNum not in", values, "clicknum");
            return (Criteria) this;
        }

        public Criteria andClicknumBetween(Integer value1, Integer value2) {
            addCriterion("ClickNum between", value1, value2, "clicknum");
            return (Criteria) this;
        }

        public Criteria andClicknumNotBetween(Integer value1, Integer value2) {
            addCriterion("ClickNum not between", value1, value2, "clicknum");
            return (Criteria) this;
        }

        public Criteria andSendidIsNull() {
            addCriterion("SendID is null");
            return (Criteria) this;
        }

        public Criteria andSendidIsNotNull() {
            addCriterion("SendID is not null");
            return (Criteria) this;
        }

        public Criteria andSendidEqualTo(Integer value) {
            addCriterion("SendID =", value, "sendid");
            return (Criteria) this;
        }

        public Criteria andSendidNotEqualTo(Integer value) {
            addCriterion("SendID <>", value, "sendid");
            return (Criteria) this;
        }

        public Criteria andSendidGreaterThan(Integer value) {
            addCriterion("SendID >", value, "sendid");
            return (Criteria) this;
        }

        public Criteria andSendidGreaterThanOrEqualTo(Integer value) {
            addCriterion("SendID >=", value, "sendid");
            return (Criteria) this;
        }

        public Criteria andSendidLessThan(Integer value) {
            addCriterion("SendID <", value, "sendid");
            return (Criteria) this;
        }

        public Criteria andSendidLessThanOrEqualTo(Integer value) {
            addCriterion("SendID <=", value, "sendid");
            return (Criteria) this;
        }

        public Criteria andSendidIn(List<Integer> values) {
            addCriterion("SendID in", values, "sendid");
            return (Criteria) this;
        }

        public Criteria andSendidNotIn(List<Integer> values) {
            addCriterion("SendID not in", values, "sendid");
            return (Criteria) this;
        }

        public Criteria andSendidBetween(Integer value1, Integer value2) {
            addCriterion("SendID between", value1, value2, "sendid");
            return (Criteria) this;
        }

        public Criteria andSendidNotBetween(Integer value1, Integer value2) {
            addCriterion("SendID not between", value1, value2, "sendid");
            return (Criteria) this;
        }

        public Criteria andSendpeopleIsNull() {
            addCriterion("SendPeople is null");
            return (Criteria) this;
        }

        public Criteria andSendpeopleIsNotNull() {
            addCriterion("SendPeople is not null");
            return (Criteria) this;
        }

        public Criteria andSendpeopleEqualTo(String value) {
            addCriterion("SendPeople =", value, "sendpeople");
            return (Criteria) this;
        }

        public Criteria andSendpeopleNotEqualTo(String value) {
            addCriterion("SendPeople <>", value, "sendpeople");
            return (Criteria) this;
        }

        public Criteria andSendpeopleGreaterThan(String value) {
            addCriterion("SendPeople >", value, "sendpeople");
            return (Criteria) this;
        }

        public Criteria andSendpeopleGreaterThanOrEqualTo(String value) {
            addCriterion("SendPeople >=", value, "sendpeople");
            return (Criteria) this;
        }

        public Criteria andSendpeopleLessThan(String value) {
            addCriterion("SendPeople <", value, "sendpeople");
            return (Criteria) this;
        }

        public Criteria andSendpeopleLessThanOrEqualTo(String value) {
            addCriterion("SendPeople <=", value, "sendpeople");
            return (Criteria) this;
        }

        public Criteria andSendpeopleLike(String value) {
            addCriterion("SendPeople like", value, "sendpeople");
            return (Criteria) this;
        }

        public Criteria andSendpeopleNotLike(String value) {
            addCriterion("SendPeople not like", value, "sendpeople");
            return (Criteria) this;
        }

        public Criteria andSendpeopleIn(List<String> values) {
            addCriterion("SendPeople in", values, "sendpeople");
            return (Criteria) this;
        }

        public Criteria andSendpeopleNotIn(List<String> values) {
            addCriterion("SendPeople not in", values, "sendpeople");
            return (Criteria) this;
        }

        public Criteria andSendpeopleBetween(String value1, String value2) {
            addCriterion("SendPeople between", value1, value2, "sendpeople");
            return (Criteria) this;
        }

        public Criteria andSendpeopleNotBetween(String value1, String value2) {
            addCriterion("SendPeople not between", value1, value2, "sendpeople");
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

        public Criteria andIstopIsNull() {
            addCriterion("IsTop is null");
            return (Criteria) this;
        }

        public Criteria andIstopIsNotNull() {
            addCriterion("IsTop is not null");
            return (Criteria) this;
        }

        public Criteria andIstopEqualTo(Boolean value) {
            addCriterion("IsTop =", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopNotEqualTo(Boolean value) {
            addCriterion("IsTop <>", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopGreaterThan(Boolean value) {
            addCriterion("IsTop >", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsTop >=", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopLessThan(Boolean value) {
            addCriterion("IsTop <", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopLessThanOrEqualTo(Boolean value) {
            addCriterion("IsTop <=", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopIn(List<Boolean> values) {
            addCriterion("IsTop in", values, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopNotIn(List<Boolean> values) {
            addCriterion("IsTop not in", values, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopBetween(Boolean value1, Boolean value2) {
            addCriterion("IsTop between", value1, value2, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsTop not between", value1, value2, "istop");
            return (Criteria) this;
        }

        public Criteria andIsheadIsNull() {
            addCriterion("IsHead is null");
            return (Criteria) this;
        }

        public Criteria andIsheadIsNotNull() {
            addCriterion("IsHead is not null");
            return (Criteria) this;
        }

        public Criteria andIsheadEqualTo(Boolean value) {
            addCriterion("IsHead =", value, "ishead");
            return (Criteria) this;
        }

        public Criteria andIsheadNotEqualTo(Boolean value) {
            addCriterion("IsHead <>", value, "ishead");
            return (Criteria) this;
        }

        public Criteria andIsheadGreaterThan(Boolean value) {
            addCriterion("IsHead >", value, "ishead");
            return (Criteria) this;
        }

        public Criteria andIsheadGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsHead >=", value, "ishead");
            return (Criteria) this;
        }

        public Criteria andIsheadLessThan(Boolean value) {
            addCriterion("IsHead <", value, "ishead");
            return (Criteria) this;
        }

        public Criteria andIsheadLessThanOrEqualTo(Boolean value) {
            addCriterion("IsHead <=", value, "ishead");
            return (Criteria) this;
        }

        public Criteria andIsheadIn(List<Boolean> values) {
            addCriterion("IsHead in", values, "ishead");
            return (Criteria) this;
        }

        public Criteria andIsheadNotIn(List<Boolean> values) {
            addCriterion("IsHead not in", values, "ishead");
            return (Criteria) this;
        }

        public Criteria andIsheadBetween(Boolean value1, Boolean value2) {
            addCriterion("IsHead between", value1, value2, "ishead");
            return (Criteria) this;
        }

        public Criteria andIsheadNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsHead not between", value1, value2, "ishead");
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

        public Criteria andImgurlIsNull() {
            addCriterion("ImgUrl is null");
            return (Criteria) this;
        }

        public Criteria andImgurlIsNotNull() {
            addCriterion("ImgUrl is not null");
            return (Criteria) this;
        }

        public Criteria andImgurlEqualTo(String value) {
            addCriterion("ImgUrl =", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotEqualTo(String value) {
            addCriterion("ImgUrl <>", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlGreaterThan(String value) {
            addCriterion("ImgUrl >", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlGreaterThanOrEqualTo(String value) {
            addCriterion("ImgUrl >=", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLessThan(String value) {
            addCriterion("ImgUrl <", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLessThanOrEqualTo(String value) {
            addCriterion("ImgUrl <=", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLike(String value) {
            addCriterion("ImgUrl like", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotLike(String value) {
            addCriterion("ImgUrl not like", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlIn(List<String> values) {
            addCriterion("ImgUrl in", values, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotIn(List<String> values) {
            addCriterion("ImgUrl not in", values, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlBetween(String value1, String value2) {
            addCriterion("ImgUrl between", value1, value2, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotBetween(String value1, String value2) {
            addCriterion("ImgUrl not between", value1, value2, "imgurl");
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