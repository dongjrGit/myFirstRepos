package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.List;

public class VSpecsinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VSpecsinfoExample() {
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

        public Criteria andSkuIdIsNull() {
            addCriterion("SKU_ID is null");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNotNull() {
            addCriterion("SKU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSkuIdEqualTo(Integer value) {
            addCriterion("SKU_ID =", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotEqualTo(Integer value) {
            addCriterion("SKU_ID <>", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThan(Integer value) {
            addCriterion("SKU_ID >", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SKU_ID >=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThan(Integer value) {
            addCriterion("SKU_ID <", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThanOrEqualTo(Integer value) {
            addCriterion("SKU_ID <=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIn(List<Integer> values) {
            addCriterion("SKU_ID in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotIn(List<Integer> values) {
            addCriterion("SKU_ID not in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdBetween(Integer value1, Integer value2) {
            addCriterion("SKU_ID between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SKU_ID not between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andValueidIsNull() {
            addCriterion("ValueID is null");
            return (Criteria) this;
        }

        public Criteria andValueidIsNotNull() {
            addCriterion("ValueID is not null");
            return (Criteria) this;
        }

        public Criteria andValueidEqualTo(Integer value) {
            addCriterion("ValueID =", value, "valueid");
            return (Criteria) this;
        }

        public Criteria andValueidNotEqualTo(Integer value) {
            addCriterion("ValueID <>", value, "valueid");
            return (Criteria) this;
        }

        public Criteria andValueidGreaterThan(Integer value) {
            addCriterion("ValueID >", value, "valueid");
            return (Criteria) this;
        }

        public Criteria andValueidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ValueID >=", value, "valueid");
            return (Criteria) this;
        }

        public Criteria andValueidLessThan(Integer value) {
            addCriterion("ValueID <", value, "valueid");
            return (Criteria) this;
        }

        public Criteria andValueidLessThanOrEqualTo(Integer value) {
            addCriterion("ValueID <=", value, "valueid");
            return (Criteria) this;
        }

        public Criteria andValueidIn(List<Integer> values) {
            addCriterion("ValueID in", values, "valueid");
            return (Criteria) this;
        }

        public Criteria andValueidNotIn(List<Integer> values) {
            addCriterion("ValueID not in", values, "valueid");
            return (Criteria) this;
        }

        public Criteria andValueidBetween(Integer value1, Integer value2) {
            addCriterion("ValueID between", value1, value2, "valueid");
            return (Criteria) this;
        }

        public Criteria andValueidNotBetween(Integer value1, Integer value2) {
            addCriterion("ValueID not between", value1, value2, "valueid");
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

        public Criteria andValueEqualTo(String value) {
            addCriterion("Value =", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(String value) {
            addCriterion("Value <>", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(String value) {
            addCriterion("Value >", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(String value) {
            addCriterion("Value >=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThan(String value) {
            addCriterion("Value <", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(String value) {
            addCriterion("Value <=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLike(String value) {
            addCriterion("Value like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotLike(String value) {
            addCriterion("Value not like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<String> values) {
            addCriterion("Value in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<String> values) {
            addCriterion("Value not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(String value1, String value2) {
            addCriterion("Value between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(String value1, String value2) {
            addCriterion("Value not between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andSpecsidIsNull() {
            addCriterion("SpecsID is null");
            return (Criteria) this;
        }

        public Criteria andSpecsidIsNotNull() {
            addCriterion("SpecsID is not null");
            return (Criteria) this;
        }

        public Criteria andSpecsidEqualTo(Integer value) {
            addCriterion("SpecsID =", value, "specsid");
            return (Criteria) this;
        }

        public Criteria andSpecsidNotEqualTo(Integer value) {
            addCriterion("SpecsID <>", value, "specsid");
            return (Criteria) this;
        }

        public Criteria andSpecsidGreaterThan(Integer value) {
            addCriterion("SpecsID >", value, "specsid");
            return (Criteria) this;
        }

        public Criteria andSpecsidGreaterThanOrEqualTo(Integer value) {
            addCriterion("SpecsID >=", value, "specsid");
            return (Criteria) this;
        }

        public Criteria andSpecsidLessThan(Integer value) {
            addCriterion("SpecsID <", value, "specsid");
            return (Criteria) this;
        }

        public Criteria andSpecsidLessThanOrEqualTo(Integer value) {
            addCriterion("SpecsID <=", value, "specsid");
            return (Criteria) this;
        }

        public Criteria andSpecsidIn(List<Integer> values) {
            addCriterion("SpecsID in", values, "specsid");
            return (Criteria) this;
        }

        public Criteria andSpecsidNotIn(List<Integer> values) {
            addCriterion("SpecsID not in", values, "specsid");
            return (Criteria) this;
        }

        public Criteria andSpecsidBetween(Integer value1, Integer value2) {
            addCriterion("SpecsID between", value1, value2, "specsid");
            return (Criteria) this;
        }

        public Criteria andSpecsidNotBetween(Integer value1, Integer value2) {
            addCriterion("SpecsID not between", value1, value2, "specsid");
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

        public Criteria andTypeidIsNull() {
            addCriterion("TypeID is null");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNotNull() {
            addCriterion("TypeID is not null");
            return (Criteria) this;
        }

        public Criteria andTypeidEqualTo(Integer value) {
            addCriterion("TypeID =", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotEqualTo(Integer value) {
            addCriterion("TypeID <>", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThan(Integer value) {
            addCriterion("TypeID >", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("TypeID >=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThan(Integer value) {
            addCriterion("TypeID <", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThanOrEqualTo(Integer value) {
            addCriterion("TypeID <=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidIn(List<Integer> values) {
            addCriterion("TypeID in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotIn(List<Integer> values) {
            addCriterion("TypeID not in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidBetween(Integer value1, Integer value2) {
            addCriterion("TypeID between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("TypeID not between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypenameIsNull() {
            addCriterion("TypeName is null");
            return (Criteria) this;
        }

        public Criteria andTypenameIsNotNull() {
            addCriterion("TypeName is not null");
            return (Criteria) this;
        }

        public Criteria andTypenameEqualTo(String value) {
            addCriterion("TypeName =", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotEqualTo(String value) {
            addCriterion("TypeName <>", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameGreaterThan(String value) {
            addCriterion("TypeName >", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameGreaterThanOrEqualTo(String value) {
            addCriterion("TypeName >=", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLessThan(String value) {
            addCriterion("TypeName <", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLessThanOrEqualTo(String value) {
            addCriterion("TypeName <=", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLike(String value) {
            addCriterion("TypeName like", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotLike(String value) {
            addCriterion("TypeName not like", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameIn(List<String> values) {
            addCriterion("TypeName in", values, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotIn(List<String> values) {
            addCriterion("TypeName not in", values, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameBetween(String value1, String value2) {
            addCriterion("TypeName between", value1, value2, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotBetween(String value1, String value2) {
            addCriterion("TypeName not between", value1, value2, "typename");
            return (Criteria) this;
        }

        public Criteria andTypeorderIsNull() {
            addCriterion("TypeOrder is null");
            return (Criteria) this;
        }

        public Criteria andTypeorderIsNotNull() {
            addCriterion("TypeOrder is not null");
            return (Criteria) this;
        }

        public Criteria andTypeorderEqualTo(Integer value) {
            addCriterion("TypeOrder =", value, "typeorder");
            return (Criteria) this;
        }

        public Criteria andTypeorderNotEqualTo(Integer value) {
            addCriterion("TypeOrder <>", value, "typeorder");
            return (Criteria) this;
        }

        public Criteria andTypeorderGreaterThan(Integer value) {
            addCriterion("TypeOrder >", value, "typeorder");
            return (Criteria) this;
        }

        public Criteria andTypeorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("TypeOrder >=", value, "typeorder");
            return (Criteria) this;
        }

        public Criteria andTypeorderLessThan(Integer value) {
            addCriterion("TypeOrder <", value, "typeorder");
            return (Criteria) this;
        }

        public Criteria andTypeorderLessThanOrEqualTo(Integer value) {
            addCriterion("TypeOrder <=", value, "typeorder");
            return (Criteria) this;
        }

        public Criteria andTypeorderIn(List<Integer> values) {
            addCriterion("TypeOrder in", values, "typeorder");
            return (Criteria) this;
        }

        public Criteria andTypeorderNotIn(List<Integer> values) {
            addCriterion("TypeOrder not in", values, "typeorder");
            return (Criteria) this;
        }

        public Criteria andTypeorderBetween(Integer value1, Integer value2) {
            addCriterion("TypeOrder between", value1, value2, "typeorder");
            return (Criteria) this;
        }

        public Criteria andTypeorderNotBetween(Integer value1, Integer value2) {
            addCriterion("TypeOrder not between", value1, value2, "typeorder");
            return (Criteria) this;
        }

        public Criteria andSpecsorderIsNull() {
            addCriterion("SpecsOrder is null");
            return (Criteria) this;
        }

        public Criteria andSpecsorderIsNotNull() {
            addCriterion("SpecsOrder is not null");
            return (Criteria) this;
        }

        public Criteria andSpecsorderEqualTo(Integer value) {
            addCriterion("SpecsOrder =", value, "specsorder");
            return (Criteria) this;
        }

        public Criteria andSpecsorderNotEqualTo(Integer value) {
            addCriterion("SpecsOrder <>", value, "specsorder");
            return (Criteria) this;
        }

        public Criteria andSpecsorderGreaterThan(Integer value) {
            addCriterion("SpecsOrder >", value, "specsorder");
            return (Criteria) this;
        }

        public Criteria andSpecsorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("SpecsOrder >=", value, "specsorder");
            return (Criteria) this;
        }

        public Criteria andSpecsorderLessThan(Integer value) {
            addCriterion("SpecsOrder <", value, "specsorder");
            return (Criteria) this;
        }

        public Criteria andSpecsorderLessThanOrEqualTo(Integer value) {
            addCriterion("SpecsOrder <=", value, "specsorder");
            return (Criteria) this;
        }

        public Criteria andSpecsorderIn(List<Integer> values) {
            addCriterion("SpecsOrder in", values, "specsorder");
            return (Criteria) this;
        }

        public Criteria andSpecsorderNotIn(List<Integer> values) {
            addCriterion("SpecsOrder not in", values, "specsorder");
            return (Criteria) this;
        }

        public Criteria andSpecsorderBetween(Integer value1, Integer value2) {
            addCriterion("SpecsOrder between", value1, value2, "specsorder");
            return (Criteria) this;
        }

        public Criteria andSpecsorderNotBetween(Integer value1, Integer value2) {
            addCriterion("SpecsOrder not between", value1, value2, "specsorder");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationIsNull() {
            addCriterion("DisplayLocation is null");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationIsNotNull() {
            addCriterion("DisplayLocation is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationEqualTo(String value) {
            addCriterion("DisplayLocation =", value, "displaylocation");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationNotEqualTo(String value) {
            addCriterion("DisplayLocation <>", value, "displaylocation");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationGreaterThan(String value) {
            addCriterion("DisplayLocation >", value, "displaylocation");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationGreaterThanOrEqualTo(String value) {
            addCriterion("DisplayLocation >=", value, "displaylocation");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationLessThan(String value) {
            addCriterion("DisplayLocation <", value, "displaylocation");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationLessThanOrEqualTo(String value) {
            addCriterion("DisplayLocation <=", value, "displaylocation");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationLike(String value) {
            addCriterion("DisplayLocation like", value, "displaylocation");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationNotLike(String value) {
            addCriterion("DisplayLocation not like", value, "displaylocation");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationIn(List<String> values) {
            addCriterion("DisplayLocation in", values, "displaylocation");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationNotIn(List<String> values) {
            addCriterion("DisplayLocation not in", values, "displaylocation");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationBetween(String value1, String value2) {
            addCriterion("DisplayLocation between", value1, value2, "displaylocation");
            return (Criteria) this;
        }

        public Criteria andDisplaylocationNotBetween(String value1, String value2) {
            addCriterion("DisplayLocation not between", value1, value2, "displaylocation");
            return (Criteria) this;
        }

        public Criteria andFullpathIsNull() {
            addCriterion("Fullpath is null");
            return (Criteria) this;
        }

        public Criteria andFullpathIsNotNull() {
            addCriterion("Fullpath is not null");
            return (Criteria) this;
        }

        public Criteria andFullpathEqualTo(String value) {
            addCriterion("Fullpath =", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathNotEqualTo(String value) {
            addCriterion("Fullpath <>", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathGreaterThan(String value) {
            addCriterion("Fullpath >", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathGreaterThanOrEqualTo(String value) {
            addCriterion("Fullpath >=", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathLessThan(String value) {
            addCriterion("Fullpath <", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathLessThanOrEqualTo(String value) {
            addCriterion("Fullpath <=", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathLike(String value) {
            addCriterion("Fullpath like", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathNotLike(String value) {
            addCriterion("Fullpath not like", value, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathIn(List<String> values) {
            addCriterion("Fullpath in", values, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathNotIn(List<String> values) {
            addCriterion("Fullpath not in", values, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathBetween(String value1, String value2) {
            addCriterion("Fullpath between", value1, value2, "fullpath");
            return (Criteria) this;
        }

        public Criteria andFullpathNotBetween(String value1, String value2) {
            addCriterion("Fullpath not between", value1, value2, "fullpath");
            return (Criteria) this;
        }

        public Criteria andClassidIsNull() {
            addCriterion("ClassID is null");
            return (Criteria) this;
        }

        public Criteria andClassidIsNotNull() {
            addCriterion("ClassID is not null");
            return (Criteria) this;
        }

        public Criteria andClassidEqualTo(Integer value) {
            addCriterion("ClassID =", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotEqualTo(Integer value) {
            addCriterion("ClassID <>", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThan(Integer value) {
            addCriterion("ClassID >", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ClassID >=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThan(Integer value) {
            addCriterion("ClassID <", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThanOrEqualTo(Integer value) {
            addCriterion("ClassID <=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidIn(List<Integer> values) {
            addCriterion("ClassID in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotIn(List<Integer> values) {
            addCriterion("ClassID not in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidBetween(Integer value1, Integer value2) {
            addCriterion("ClassID between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotBetween(Integer value1, Integer value2) {
            addCriterion("ClassID not between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andTypestatusIsNull() {
            addCriterion("TypeStatus is null");
            return (Criteria) this;
        }

        public Criteria andTypestatusIsNotNull() {
            addCriterion("TypeStatus is not null");
            return (Criteria) this;
        }

        public Criteria andTypestatusEqualTo(Integer value) {
            addCriterion("TypeStatus =", value, "typestatus");
            return (Criteria) this;
        }

        public Criteria andTypestatusNotEqualTo(Integer value) {
            addCriterion("TypeStatus <>", value, "typestatus");
            return (Criteria) this;
        }

        public Criteria andTypestatusGreaterThan(Integer value) {
            addCriterion("TypeStatus >", value, "typestatus");
            return (Criteria) this;
        }

        public Criteria andTypestatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("TypeStatus >=", value, "typestatus");
            return (Criteria) this;
        }

        public Criteria andTypestatusLessThan(Integer value) {
            addCriterion("TypeStatus <", value, "typestatus");
            return (Criteria) this;
        }

        public Criteria andTypestatusLessThanOrEqualTo(Integer value) {
            addCriterion("TypeStatus <=", value, "typestatus");
            return (Criteria) this;
        }

        public Criteria andTypestatusIn(List<Integer> values) {
            addCriterion("TypeStatus in", values, "typestatus");
            return (Criteria) this;
        }

        public Criteria andTypestatusNotIn(List<Integer> values) {
            addCriterion("TypeStatus not in", values, "typestatus");
            return (Criteria) this;
        }

        public Criteria andTypestatusBetween(Integer value1, Integer value2) {
            addCriterion("TypeStatus between", value1, value2, "typestatus");
            return (Criteria) this;
        }

        public Criteria andTypestatusNotBetween(Integer value1, Integer value2) {
            addCriterion("TypeStatus not between", value1, value2, "typestatus");
            return (Criteria) this;
        }

        public Criteria andValuestatusIsNull() {
            addCriterion("ValueStatus is null");
            return (Criteria) this;
        }

        public Criteria andValuestatusIsNotNull() {
            addCriterion("ValueStatus is not null");
            return (Criteria) this;
        }

        public Criteria andValuestatusEqualTo(Integer value) {
            addCriterion("ValueStatus =", value, "valuestatus");
            return (Criteria) this;
        }

        public Criteria andValuestatusNotEqualTo(Integer value) {
            addCriterion("ValueStatus <>", value, "valuestatus");
            return (Criteria) this;
        }

        public Criteria andValuestatusGreaterThan(Integer value) {
            addCriterion("ValueStatus >", value, "valuestatus");
            return (Criteria) this;
        }

        public Criteria andValuestatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("ValueStatus >=", value, "valuestatus");
            return (Criteria) this;
        }

        public Criteria andValuestatusLessThan(Integer value) {
            addCriterion("ValueStatus <", value, "valuestatus");
            return (Criteria) this;
        }

        public Criteria andValuestatusLessThanOrEqualTo(Integer value) {
            addCriterion("ValueStatus <=", value, "valuestatus");
            return (Criteria) this;
        }

        public Criteria andValuestatusIn(List<Integer> values) {
            addCriterion("ValueStatus in", values, "valuestatus");
            return (Criteria) this;
        }

        public Criteria andValuestatusNotIn(List<Integer> values) {
            addCriterion("ValueStatus not in", values, "valuestatus");
            return (Criteria) this;
        }

        public Criteria andValuestatusBetween(Integer value1, Integer value2) {
            addCriterion("ValueStatus between", value1, value2, "valuestatus");
            return (Criteria) this;
        }

        public Criteria andValuestatusNotBetween(Integer value1, Integer value2) {
            addCriterion("ValueStatus not between", value1, value2, "valuestatus");
            return (Criteria) this;
        }

        public Criteria andSpecsstatusIsNull() {
            addCriterion("SpecsStatus is null");
            return (Criteria) this;
        }

        public Criteria andSpecsstatusIsNotNull() {
            addCriterion("SpecsStatus is not null");
            return (Criteria) this;
        }

        public Criteria andSpecsstatusEqualTo(Integer value) {
            addCriterion("SpecsStatus =", value, "specsstatus");
            return (Criteria) this;
        }

        public Criteria andSpecsstatusNotEqualTo(Integer value) {
            addCriterion("SpecsStatus <>", value, "specsstatus");
            return (Criteria) this;
        }

        public Criteria andSpecsstatusGreaterThan(Integer value) {
            addCriterion("SpecsStatus >", value, "specsstatus");
            return (Criteria) this;
        }

        public Criteria andSpecsstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("SpecsStatus >=", value, "specsstatus");
            return (Criteria) this;
        }

        public Criteria andSpecsstatusLessThan(Integer value) {
            addCriterion("SpecsStatus <", value, "specsstatus");
            return (Criteria) this;
        }

        public Criteria andSpecsstatusLessThanOrEqualTo(Integer value) {
            addCriterion("SpecsStatus <=", value, "specsstatus");
            return (Criteria) this;
        }

        public Criteria andSpecsstatusIn(List<Integer> values) {
            addCriterion("SpecsStatus in", values, "specsstatus");
            return (Criteria) this;
        }

        public Criteria andSpecsstatusNotIn(List<Integer> values) {
            addCriterion("SpecsStatus not in", values, "specsstatus");
            return (Criteria) this;
        }

        public Criteria andSpecsstatusBetween(Integer value1, Integer value2) {
            addCriterion("SpecsStatus between", value1, value2, "specsstatus");
            return (Criteria) this;
        }

        public Criteria andSpecsstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("SpecsStatus not between", value1, value2, "specsstatus");
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