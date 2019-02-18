package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenusExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MenusExample() {
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

        public Criteria andMenutypeIsNull() {
            addCriterion("MenuType is null");
            return (Criteria) this;
        }

        public Criteria andMenutypeIsNotNull() {
            addCriterion("MenuType is not null");
            return (Criteria) this;
        }

        public Criteria andMenutypeEqualTo(Integer value) {
            addCriterion("MenuType =", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeNotEqualTo(Integer value) {
            addCriterion("MenuType <>", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeGreaterThan(Integer value) {
            addCriterion("MenuType >", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("MenuType >=", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeLessThan(Integer value) {
            addCriterion("MenuType <", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeLessThanOrEqualTo(Integer value) {
            addCriterion("MenuType <=", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeIn(List<Integer> values) {
            addCriterion("MenuType in", values, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeNotIn(List<Integer> values) {
            addCriterion("MenuType not in", values, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeBetween(Integer value1, Integer value2) {
            addCriterion("MenuType between", value1, value2, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeNotBetween(Integer value1, Integer value2) {
            addCriterion("MenuType not between", value1, value2, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenuurlIsNull() {
            addCriterion("MenuUrl is null");
            return (Criteria) this;
        }

        public Criteria andMenuurlIsNotNull() {
            addCriterion("MenuUrl is not null");
            return (Criteria) this;
        }

        public Criteria andMenuurlEqualTo(String value) {
            addCriterion("MenuUrl =", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlNotEqualTo(String value) {
            addCriterion("MenuUrl <>", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlGreaterThan(String value) {
            addCriterion("MenuUrl >", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlGreaterThanOrEqualTo(String value) {
            addCriterion("MenuUrl >=", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlLessThan(String value) {
            addCriterion("MenuUrl <", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlLessThanOrEqualTo(String value) {
            addCriterion("MenuUrl <=", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlLike(String value) {
            addCriterion("MenuUrl like", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlNotLike(String value) {
            addCriterion("MenuUrl not like", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlIn(List<String> values) {
            addCriterion("MenuUrl in", values, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlNotIn(List<String> values) {
            addCriterion("MenuUrl not in", values, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlBetween(String value1, String value2) {
            addCriterion("MenuUrl between", value1, value2, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlNotBetween(String value1, String value2) {
            addCriterion("MenuUrl not between", value1, value2, "menuurl");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("Level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("Level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("Level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("Level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("Level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("Level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("Level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("Level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("Level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("Level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("Level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("Level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andFatheridIsNull() {
            addCriterion("FatherID is null");
            return (Criteria) this;
        }

        public Criteria andFatheridIsNotNull() {
            addCriterion("FatherID is not null");
            return (Criteria) this;
        }

        public Criteria andFatheridEqualTo(Integer value) {
            addCriterion("FatherID =", value, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridNotEqualTo(Integer value) {
            addCriterion("FatherID <>", value, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridGreaterThan(Integer value) {
            addCriterion("FatherID >", value, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridGreaterThanOrEqualTo(Integer value) {
            addCriterion("FatherID >=", value, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridLessThan(Integer value) {
            addCriterion("FatherID <", value, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridLessThanOrEqualTo(Integer value) {
            addCriterion("FatherID <=", value, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridIn(List<Integer> values) {
            addCriterion("FatherID in", values, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridNotIn(List<Integer> values) {
            addCriterion("FatherID not in", values, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridBetween(Integer value1, Integer value2) {
            addCriterion("FatherID between", value1, value2, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridNotBetween(Integer value1, Integer value2) {
            addCriterion("FatherID not between", value1, value2, "fatherid");
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

        public Criteria andDeluserIsNull() {
            addCriterion("DelUser is null");
            return (Criteria) this;
        }

        public Criteria andDeluserIsNotNull() {
            addCriterion("DelUser is not null");
            return (Criteria) this;
        }

        public Criteria andDeluserEqualTo(Integer value) {
            addCriterion("DelUser =", value, "deluser");
            return (Criteria) this;
        }

        public Criteria andDeluserNotEqualTo(Integer value) {
            addCriterion("DelUser <>", value, "deluser");
            return (Criteria) this;
        }

        public Criteria andDeluserGreaterThan(Integer value) {
            addCriterion("DelUser >", value, "deluser");
            return (Criteria) this;
        }

        public Criteria andDeluserGreaterThanOrEqualTo(Integer value) {
            addCriterion("DelUser >=", value, "deluser");
            return (Criteria) this;
        }

        public Criteria andDeluserLessThan(Integer value) {
            addCriterion("DelUser <", value, "deluser");
            return (Criteria) this;
        }

        public Criteria andDeluserLessThanOrEqualTo(Integer value) {
            addCriterion("DelUser <=", value, "deluser");
            return (Criteria) this;
        }

        public Criteria andDeluserIn(List<Integer> values) {
            addCriterion("DelUser in", values, "deluser");
            return (Criteria) this;
        }

        public Criteria andDeluserNotIn(List<Integer> values) {
            addCriterion("DelUser not in", values, "deluser");
            return (Criteria) this;
        }

        public Criteria andDeluserBetween(Integer value1, Integer value2) {
            addCriterion("DelUser between", value1, value2, "deluser");
            return (Criteria) this;
        }

        public Criteria andDeluserNotBetween(Integer value1, Integer value2) {
            addCriterion("DelUser not between", value1, value2, "deluser");
            return (Criteria) this;
        }

        public Criteria andFullpathIsNull() {
            addCriterion("FullPath is null");
            return (Criteria) this;
        }

        public Criteria andFullpathIsNotNull() {
            addCriterion("FullPath is not null");
            return (Criteria) this;
        }

        public Criteria andFullpathEqualTo(String value) {
            addCriterion("FullPath =", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathNotEqualTo(String value) {
            addCriterion("FullPath <>", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathGreaterThan(String value) {
            addCriterion("FullPath >", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathGreaterThanOrEqualTo(String value) {
            addCriterion("FullPath >=", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathLessThan(String value) {
            addCriterion("FullPath <", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathLessThanOrEqualTo(String value) {
            addCriterion("FullPath <=", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathLike(String value) {
            addCriterion("FullPath like", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathNotLike(String value) {
            addCriterion("FullPath not like", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathIn(List<String> values) {
            addCriterion("FullPath in", values, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathNotIn(List<String> values) {
            addCriterion("FullPath not in", values, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathBetween(String value1, String value2) {
            addCriterion("FullPath between", value1, value2, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathNotBetween(String value1, String value2) {
            addCriterion("FullPath not between", value1, value2, "fullpath");
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

        public Criteria andHaschildIsNull() {
            addCriterion("HasChild is null");
            return (Criteria) this;
        }

        public Criteria andHaschildIsNotNull() {
            addCriterion("HasChild is not null");
            return (Criteria) this;
        }

        public Criteria andHaschildEqualTo(Boolean value) {
            addCriterion("HasChild =", value, "haschild");
            return (Criteria) this;
        }

        public Criteria andHaschildNotEqualTo(Boolean value) {
            addCriterion("HasChild <>", value, "haschild");
            return (Criteria) this;
        }

        public Criteria andHaschildGreaterThan(Boolean value) {
            addCriterion("HasChild >", value, "haschild");
            return (Criteria) this;
        }

        public Criteria andHaschildGreaterThanOrEqualTo(Boolean value) {
            addCriterion("HasChild >=", value, "haschild");
            return (Criteria) this;
        }

        public Criteria andHaschildLessThan(Boolean value) {
            addCriterion("HasChild <", value, "haschild");
            return (Criteria) this;
        }

        public Criteria andHaschildLessThanOrEqualTo(Boolean value) {
            addCriterion("HasChild <=", value, "haschild");
            return (Criteria) this;
        }

        public Criteria andHaschildIn(List<Boolean> values) {
            addCriterion("HasChild in", values, "haschild");
            return (Criteria) this;
        }

        public Criteria andHaschildNotIn(List<Boolean> values) {
            addCriterion("HasChild not in", values, "haschild");
            return (Criteria) this;
        }

        public Criteria andHaschildBetween(Boolean value1, Boolean value2) {
            addCriterion("HasChild between", value1, value2, "haschild");
            return (Criteria) this;
        }

        public Criteria andHaschildNotBetween(Boolean value1, Boolean value2) {
            addCriterion("HasChild not between", value1, value2, "haschild");
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