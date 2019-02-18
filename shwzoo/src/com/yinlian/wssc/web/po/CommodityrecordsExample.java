package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.List;

public class CommodityrecordsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommodityrecordsExample() {
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

        public Criteria andRelationidIsNull() {
            addCriterion("RelationId is null");
            return (Criteria) this;
        }

        public Criteria andRelationidIsNotNull() {
            addCriterion("RelationId is not null");
            return (Criteria) this;
        }

        public Criteria andRelationidEqualTo(String value) {
            addCriterion("RelationId =", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidNotEqualTo(String value) {
            addCriterion("RelationId <>", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidGreaterThan(String value) {
            addCriterion("RelationId >", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidGreaterThanOrEqualTo(String value) {
            addCriterion("RelationId >=", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidLessThan(String value) {
            addCriterion("RelationId <", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidLessThanOrEqualTo(String value) {
            addCriterion("RelationId <=", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidLike(String value) {
            addCriterion("RelationId like", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidNotLike(String value) {
            addCriterion("RelationId not like", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidIn(List<String> values) {
            addCriterion("RelationId in", values, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidNotIn(List<String> values) {
            addCriterion("RelationId not in", values, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidBetween(String value1, String value2) {
            addCriterion("RelationId between", value1, value2, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidNotBetween(String value1, String value2) {
            addCriterion("RelationId not between", value1, value2, "relationid");
            return (Criteria) this;
        }

        public Criteria andSpuidIsNull() {
            addCriterion("SpuId is null");
            return (Criteria) this;
        }

        public Criteria andSpuidIsNotNull() {
            addCriterion("SpuId is not null");
            return (Criteria) this;
        }

        public Criteria andSpuidEqualTo(Integer value) {
            addCriterion("SpuId =", value, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidNotEqualTo(Integer value) {
            addCriterion("SpuId <>", value, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidGreaterThan(Integer value) {
            addCriterion("SpuId >", value, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidGreaterThanOrEqualTo(Integer value) {
            addCriterion("SpuId >=", value, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidLessThan(Integer value) {
            addCriterion("SpuId <", value, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidLessThanOrEqualTo(Integer value) {
            addCriterion("SpuId <=", value, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidIn(List<Integer> values) {
            addCriterion("SpuId in", values, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidNotIn(List<Integer> values) {
            addCriterion("SpuId not in", values, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidBetween(Integer value1, Integer value2) {
            addCriterion("SpuId between", value1, value2, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidNotBetween(Integer value1, Integer value2) {
            addCriterion("SpuId not between", value1, value2, "spuid");
            return (Criteria) this;
        }

        public Criteria andShopidIsNull() {
            addCriterion("ShopId is null");
            return (Criteria) this;
        }

        public Criteria andShopidIsNotNull() {
            addCriterion("ShopId is not null");
            return (Criteria) this;
        }

        public Criteria andShopidEqualTo(Integer value) {
            addCriterion("ShopId =", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotEqualTo(Integer value) {
            addCriterion("ShopId <>", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThan(Integer value) {
            addCriterion("ShopId >", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ShopId >=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThan(Integer value) {
            addCriterion("ShopId <", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThanOrEqualTo(Integer value) {
            addCriterion("ShopId <=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidIn(List<Integer> values) {
            addCriterion("ShopId in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotIn(List<Integer> values) {
            addCriterion("ShopId not in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidBetween(Integer value1, Integer value2) {
            addCriterion("ShopId between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotBetween(Integer value1, Integer value2) {
            addCriterion("ShopId not between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopnameIsNull() {
            addCriterion("ShopName is null");
            return (Criteria) this;
        }

        public Criteria andShopnameIsNotNull() {
            addCriterion("ShopName is not null");
            return (Criteria) this;
        }

        public Criteria andShopnameEqualTo(String value) {
            addCriterion("ShopName =", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotEqualTo(String value) {
            addCriterion("ShopName <>", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameGreaterThan(String value) {
            addCriterion("ShopName >", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameGreaterThanOrEqualTo(String value) {
            addCriterion("ShopName >=", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLessThan(String value) {
            addCriterion("ShopName <", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLessThanOrEqualTo(String value) {
            addCriterion("ShopName <=", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameLike(String value) {
            addCriterion("ShopName like", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotLike(String value) {
            addCriterion("ShopName not like", value, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameIn(List<String> values) {
            addCriterion("ShopName in", values, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotIn(List<String> values) {
            addCriterion("ShopName not in", values, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameBetween(String value1, String value2) {
            addCriterion("ShopName between", value1, value2, "shopname");
            return (Criteria) this;
        }

        public Criteria andShopnameNotBetween(String value1, String value2) {
            addCriterion("ShopName not between", value1, value2, "shopname");
            return (Criteria) this;
        }

        public Criteria andSkunameIsNull() {
            addCriterion("SkuName is null");
            return (Criteria) this;
        }

        public Criteria andSkunameIsNotNull() {
            addCriterion("SkuName is not null");
            return (Criteria) this;
        }

        public Criteria andSkunameEqualTo(String value) {
            addCriterion("SkuName =", value, "skuname");
            return (Criteria) this;
        }

        public Criteria andSkunameNotEqualTo(String value) {
            addCriterion("SkuName <>", value, "skuname");
            return (Criteria) this;
        }

        public Criteria andSkunameGreaterThan(String value) {
            addCriterion("SkuName >", value, "skuname");
            return (Criteria) this;
        }

        public Criteria andSkunameGreaterThanOrEqualTo(String value) {
            addCriterion("SkuName >=", value, "skuname");
            return (Criteria) this;
        }

        public Criteria andSkunameLessThan(String value) {
            addCriterion("SkuName <", value, "skuname");
            return (Criteria) this;
        }

        public Criteria andSkunameLessThanOrEqualTo(String value) {
            addCriterion("SkuName <=", value, "skuname");
            return (Criteria) this;
        }

        public Criteria andSkunameLike(String value) {
            addCriterion("SkuName like", value, "skuname");
            return (Criteria) this;
        }

        public Criteria andSkunameNotLike(String value) {
            addCriterion("SkuName not like", value, "skuname");
            return (Criteria) this;
        }

        public Criteria andSkunameIn(List<String> values) {
            addCriterion("SkuName in", values, "skuname");
            return (Criteria) this;
        }

        public Criteria andSkunameNotIn(List<String> values) {
            addCriterion("SkuName not in", values, "skuname");
            return (Criteria) this;
        }

        public Criteria andSkunameBetween(String value1, String value2) {
            addCriterion("SkuName between", value1, value2, "skuname");
            return (Criteria) this;
        }

        public Criteria andSkunameNotBetween(String value1, String value2) {
            addCriterion("SkuName not between", value1, value2, "skuname");
            return (Criteria) this;
        }

        public Criteria andSkuidIsNull() {
            addCriterion("SkuId is null");
            return (Criteria) this;
        }

        public Criteria andSkuidIsNotNull() {
            addCriterion("SkuId is not null");
            return (Criteria) this;
        }

        public Criteria andSkuidEqualTo(Integer value) {
            addCriterion("SkuId =", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotEqualTo(Integer value) {
            addCriterion("SkuId <>", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidGreaterThan(Integer value) {
            addCriterion("SkuId >", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidGreaterThanOrEqualTo(Integer value) {
            addCriterion("SkuId >=", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidLessThan(Integer value) {
            addCriterion("SkuId <", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidLessThanOrEqualTo(Integer value) {
            addCriterion("SkuId <=", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidIn(List<Integer> values) {
            addCriterion("SkuId in", values, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotIn(List<Integer> values) {
            addCriterion("SkuId not in", values, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidBetween(Integer value1, Integer value2) {
            addCriterion("SkuId between", value1, value2, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotBetween(Integer value1, Integer value2) {
            addCriterion("SkuId not between", value1, value2, "skuid");
            return (Criteria) this;
        }

        public Criteria andSpunameIsNull() {
            addCriterion("SpuName is null");
            return (Criteria) this;
        }

        public Criteria andSpunameIsNotNull() {
            addCriterion("SpuName is not null");
            return (Criteria) this;
        }

        public Criteria andSpunameEqualTo(String value) {
            addCriterion("SpuName =", value, "spuname");
            return (Criteria) this;
        }

        public Criteria andSpunameNotEqualTo(String value) {
            addCriterion("SpuName <>", value, "spuname");
            return (Criteria) this;
        }

        public Criteria andSpunameGreaterThan(String value) {
            addCriterion("SpuName >", value, "spuname");
            return (Criteria) this;
        }

        public Criteria andSpunameGreaterThanOrEqualTo(String value) {
            addCriterion("SpuName >=", value, "spuname");
            return (Criteria) this;
        }

        public Criteria andSpunameLessThan(String value) {
            addCriterion("SpuName <", value, "spuname");
            return (Criteria) this;
        }

        public Criteria andSpunameLessThanOrEqualTo(String value) {
            addCriterion("SpuName <=", value, "spuname");
            return (Criteria) this;
        }

        public Criteria andSpunameLike(String value) {
            addCriterion("SpuName like", value, "spuname");
            return (Criteria) this;
        }

        public Criteria andSpunameNotLike(String value) {
            addCriterion("SpuName not like", value, "spuname");
            return (Criteria) this;
        }

        public Criteria andSpunameIn(List<String> values) {
            addCriterion("SpuName in", values, "spuname");
            return (Criteria) this;
        }

        public Criteria andSpunameNotIn(List<String> values) {
            addCriterion("SpuName not in", values, "spuname");
            return (Criteria) this;
        }

        public Criteria andSpunameBetween(String value1, String value2) {
            addCriterion("SpuName between", value1, value2, "spuname");
            return (Criteria) this;
        }

        public Criteria andSpunameNotBetween(String value1, String value2) {
            addCriterion("SpuName not between", value1, value2, "spuname");
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