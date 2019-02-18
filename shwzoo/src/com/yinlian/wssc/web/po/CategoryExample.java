package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CategoryExample() {
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

        public Criteria andSortIsNull() {
            addCriterion("Sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("Sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("Sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("Sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("Sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("Sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("Sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("Sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("Sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("Sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("Sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("Sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andCreattimeIsNull() {
            addCriterion("CreatTime is null");
            return (Criteria) this;
        }

        public Criteria andCreattimeIsNotNull() {
            addCriterion("CreatTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreattimeEqualTo(Date value) {
            addCriterion("CreatTime =", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotEqualTo(Date value) {
            addCriterion("CreatTime <>", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeGreaterThan(Date value) {
            addCriterion("CreatTime >", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CreatTime >=", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeLessThan(Date value) {
            addCriterion("CreatTime <", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeLessThanOrEqualTo(Date value) {
            addCriterion("CreatTime <=", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeIn(List<Date> values) {
            addCriterion("CreatTime in", values, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotIn(List<Date> values) {
            addCriterion("CreatTime not in", values, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeBetween(Date value1, Date value2) {
            addCriterion("CreatTime between", value1, value2, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotBetween(Date value1, Date value2) {
            addCriterion("CreatTime not between", value1, value2, "creattime");
            return (Criteria) this;
        }

        public Criteria andShopidIsNull() {
            addCriterion("ShopID is null");
            return (Criteria) this;
        }

        public Criteria andShopidIsNotNull() {
            addCriterion("ShopID is not null");
            return (Criteria) this;
        }

        public Criteria andShopidEqualTo(Integer value) {
            addCriterion("ShopID =", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotEqualTo(Integer value) {
            addCriterion("ShopID <>", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThan(Integer value) {
            addCriterion("ShopID >", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ShopID >=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThan(Integer value) {
            addCriterion("ShopID <", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThanOrEqualTo(Integer value) {
            addCriterion("ShopID <=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidIn(List<Integer> values) {
            addCriterion("ShopID in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotIn(List<Integer> values) {
            addCriterion("ShopID not in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidBetween(Integer value1, Integer value2) {
            addCriterion("ShopID between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotBetween(Integer value1, Integer value2) {
            addCriterion("ShopID not between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andClasstypeIsNull() {
            addCriterion("ClassType is null");
            return (Criteria) this;
        }

        public Criteria andClasstypeIsNotNull() {
            addCriterion("ClassType is not null");
            return (Criteria) this;
        }

        public Criteria andClasstypeEqualTo(Integer value) {
            addCriterion("ClassType =", value, "classtype");
            return (Criteria) this;
        }

        public Criteria andClasstypeNotEqualTo(Integer value) {
            addCriterion("ClassType <>", value, "classtype");
            return (Criteria) this;
        }

        public Criteria andClasstypeGreaterThan(Integer value) {
            addCriterion("ClassType >", value, "classtype");
            return (Criteria) this;
        }

        public Criteria andClasstypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ClassType >=", value, "classtype");
            return (Criteria) this;
        }

        public Criteria andClasstypeLessThan(Integer value) {
            addCriterion("ClassType <", value, "classtype");
            return (Criteria) this;
        }

        public Criteria andClasstypeLessThanOrEqualTo(Integer value) {
            addCriterion("ClassType <=", value, "classtype");
            return (Criteria) this;
        }

        public Criteria andClasstypeIn(List<Integer> values) {
            addCriterion("ClassType in", values, "classtype");
            return (Criteria) this;
        }

        public Criteria andClasstypeNotIn(List<Integer> values) {
            addCriterion("ClassType not in", values, "classtype");
            return (Criteria) this;
        }

        public Criteria andClasstypeBetween(Integer value1, Integer value2) {
            addCriterion("ClassType between", value1, value2, "classtype");
            return (Criteria) this;
        }

        public Criteria andClasstypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ClassType not between", value1, value2, "classtype");
            return (Criteria) this;
        }

        public Criteria andWarnnumIsNull() {
            addCriterion("WarnNum is null");
            return (Criteria) this;
        }

        public Criteria andWarnnumIsNotNull() {
            addCriterion("WarnNum is not null");
            return (Criteria) this;
        }

        public Criteria andWarnnumEqualTo(Integer value) {
            addCriterion("WarnNum =", value, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumNotEqualTo(Integer value) {
            addCriterion("WarnNum <>", value, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumGreaterThan(Integer value) {
            addCriterion("WarnNum >", value, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("WarnNum >=", value, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumLessThan(Integer value) {
            addCriterion("WarnNum <", value, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumLessThanOrEqualTo(Integer value) {
            addCriterion("WarnNum <=", value, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumIn(List<Integer> values) {
            addCriterion("WarnNum in", values, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumNotIn(List<Integer> values) {
            addCriterion("WarnNum not in", values, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumBetween(Integer value1, Integer value2) {
            addCriterion("WarnNum between", value1, value2, "warnnum");
            return (Criteria) this;
        }

        public Criteria andWarnnumNotBetween(Integer value1, Integer value2) {
            addCriterion("WarnNum not between", value1, value2, "warnnum");
            return (Criteria) this;
        }

        public Criteria andIsvirtualIsNull() {
            addCriterion("IsVirtual is null");
            return (Criteria) this;
        }

        public Criteria andIsvirtualIsNotNull() {
            addCriterion("IsVirtual is not null");
            return (Criteria) this;
        }

        public Criteria andIsvirtualEqualTo(Boolean value) {
            addCriterion("IsVirtual =", value, "isvirtual");
            return (Criteria) this;
        }

        public Criteria andIsvirtualNotEqualTo(Boolean value) {
            addCriterion("IsVirtual <>", value, "isvirtual");
            return (Criteria) this;
        }

        public Criteria andIsvirtualGreaterThan(Boolean value) {
            addCriterion("IsVirtual >", value, "isvirtual");
            return (Criteria) this;
        }

        public Criteria andIsvirtualGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsVirtual >=", value, "isvirtual");
            return (Criteria) this;
        }

        public Criteria andIsvirtualLessThan(Boolean value) {
            addCriterion("IsVirtual <", value, "isvirtual");
            return (Criteria) this;
        }

        public Criteria andIsvirtualLessThanOrEqualTo(Boolean value) {
            addCriterion("IsVirtual <=", value, "isvirtual");
            return (Criteria) this;
        }

        public Criteria andIsvirtualIn(List<Boolean> values) {
            addCriterion("IsVirtual in", values, "isvirtual");
            return (Criteria) this;
        }

        public Criteria andIsvirtualNotIn(List<Boolean> values) {
            addCriterion("IsVirtual not in", values, "isvirtual");
            return (Criteria) this;
        }

        public Criteria andIsvirtualBetween(Boolean value1, Boolean value2) {
            addCriterion("IsVirtual between", value1, value2, "isvirtual");
            return (Criteria) this;
        }

        public Criteria andIsvirtualNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsVirtual not between", value1, value2, "isvirtual");
            return (Criteria) this;
        }

        public Criteria andIseditableIsNull() {
            addCriterion("IsEditable is null");
            return (Criteria) this;
        }

        public Criteria andIseditableIsNotNull() {
            addCriterion("IsEditable is not null");
            return (Criteria) this;
        }

        public Criteria andIseditableEqualTo(Boolean value) {
            addCriterion("IsEditable =", value, "iseditable");
            return (Criteria) this;
        }

        public Criteria andIseditableNotEqualTo(Boolean value) {
            addCriterion("IsEditable <>", value, "iseditable");
            return (Criteria) this;
        }

        public Criteria andIseditableGreaterThan(Boolean value) {
            addCriterion("IsEditable >", value, "iseditable");
            return (Criteria) this;
        }

        public Criteria andIseditableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsEditable >=", value, "iseditable");
            return (Criteria) this;
        }

        public Criteria andIseditableLessThan(Boolean value) {
            addCriterion("IsEditable <", value, "iseditable");
            return (Criteria) this;
        }

        public Criteria andIseditableLessThanOrEqualTo(Boolean value) {
            addCriterion("IsEditable <=", value, "iseditable");
            return (Criteria) this;
        }

        public Criteria andIseditableIn(List<Boolean> values) {
            addCriterion("IsEditable in", values, "iseditable");
            return (Criteria) this;
        }

        public Criteria andIseditableNotIn(List<Boolean> values) {
            addCriterion("IsEditable not in", values, "iseditable");
            return (Criteria) this;
        }

        public Criteria andIseditableBetween(Boolean value1, Boolean value2) {
            addCriterion("IsEditable between", value1, value2, "iseditable");
            return (Criteria) this;
        }

        public Criteria andIseditableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsEditable not between", value1, value2, "iseditable");
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

        public Criteria andClassleverIsNull() {
            addCriterion("ClassLever is null");
            return (Criteria) this;
        }

        public Criteria andClassleverIsNotNull() {
            addCriterion("ClassLever is not null");
            return (Criteria) this;
        }

        public Criteria andClassleverEqualTo(Integer value) {
            addCriterion("ClassLever =", value, "classlever");
            return (Criteria) this;
        }

        public Criteria andClassleverNotEqualTo(Integer value) {
            addCriterion("ClassLever <>", value, "classlever");
            return (Criteria) this;
        }

        public Criteria andClassleverGreaterThan(Integer value) {
            addCriterion("ClassLever >", value, "classlever");
            return (Criteria) this;
        }

        public Criteria andClassleverGreaterThanOrEqualTo(Integer value) {
            addCriterion("ClassLever >=", value, "classlever");
            return (Criteria) this;
        }

        public Criteria andClassleverLessThan(Integer value) {
            addCriterion("ClassLever <", value, "classlever");
            return (Criteria) this;
        }

        public Criteria andClassleverLessThanOrEqualTo(Integer value) {
            addCriterion("ClassLever <=", value, "classlever");
            return (Criteria) this;
        }

        public Criteria andClassleverIn(List<Integer> values) {
            addCriterion("ClassLever in", values, "classlever");
            return (Criteria) this;
        }

        public Criteria andClassleverNotIn(List<Integer> values) {
            addCriterion("ClassLever not in", values, "classlever");
            return (Criteria) this;
        }

        public Criteria andClassleverBetween(Integer value1, Integer value2) {
            addCriterion("ClassLever between", value1, value2, "classlever");
            return (Criteria) this;
        }

        public Criteria andClassleverNotBetween(Integer value1, Integer value2) {
            addCriterion("ClassLever not between", value1, value2, "classlever");
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

        public Criteria andSearchpathIsNull() {
            addCriterion("searchpath is null");
            return (Criteria) this;
        }

        public Criteria andSearchpathIsNotNull() {
            addCriterion("searchpath is not null");
            return (Criteria) this;
        }

        public Criteria andSearchpathEqualTo(String value) {
            addCriterion("searchpath =", value, "searchpath");
            return (Criteria) this;
        }

        public Criteria andSearchpathNotEqualTo(String value) {
            addCriterion("searchpath <>", value, "searchpath");
            return (Criteria) this;
        }

        public Criteria andSearchpathGreaterThan(String value) {
            addCriterion("searchpath >", value, "searchpath");
            return (Criteria) this;
        }

        public Criteria andSearchpathGreaterThanOrEqualTo(String value) {
            addCriterion("searchpath >=", value, "searchpath");
            return (Criteria) this;
        }

        public Criteria andSearchpathLessThan(String value) {
            addCriterion("searchpath <", value, "searchpath");
            return (Criteria) this;
        }

        public Criteria andSearchpathLessThanOrEqualTo(String value) {
            addCriterion("searchpath <=", value, "searchpath");
            return (Criteria) this;
        }

        public Criteria andSearchpathLike(String value) {
            addCriterion("searchpath like", value, "searchpath");
            return (Criteria) this;
        }

        public Criteria andSearchpathNotLike(String value) {
            addCriterion("searchpath not like", value, "searchpath");
            return (Criteria) this;
        }

        public Criteria andSearchpathIn(List<String> values) {
            addCriterion("searchpath in", values, "searchpath");
            return (Criteria) this;
        }

        public Criteria andSearchpathNotIn(List<String> values) {
            addCriterion("searchpath not in", values, "searchpath");
            return (Criteria) this;
        }

        public Criteria andSearchpathBetween(String value1, String value2) {
            addCriterion("searchpath between", value1, value2, "searchpath");
            return (Criteria) this;
        }

        public Criteria andSearchpathNotBetween(String value1, String value2) {
            addCriterion("searchpath not between", value1, value2, "searchpath");
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