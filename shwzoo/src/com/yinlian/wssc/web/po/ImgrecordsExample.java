package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImgrecordsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImgrecordsExample() {
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

        public Criteria andGroupnameIsNull() {
            addCriterion("GroupName is null");
            return (Criteria) this;
        }

        public Criteria andGroupnameIsNotNull() {
            addCriterion("GroupName is not null");
            return (Criteria) this;
        }

        public Criteria andGroupnameEqualTo(String value) {
            addCriterion("GroupName =", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotEqualTo(String value) {
            addCriterion("GroupName <>", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameGreaterThan(String value) {
            addCriterion("GroupName >", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameGreaterThanOrEqualTo(String value) {
            addCriterion("GroupName >=", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLessThan(String value) {
            addCriterion("GroupName <", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLessThanOrEqualTo(String value) {
            addCriterion("GroupName <=", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLike(String value) {
            addCriterion("GroupName like", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotLike(String value) {
            addCriterion("GroupName not like", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameIn(List<String> values) {
            addCriterion("GroupName in", values, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotIn(List<String> values) {
            addCriterion("GroupName not in", values, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameBetween(String value1, String value2) {
            addCriterion("GroupName between", value1, value2, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotBetween(String value1, String value2) {
            addCriterion("GroupName not between", value1, value2, "groupname");
            return (Criteria) this;
        }

        public Criteria andServernameIsNull() {
            addCriterion("ServerName is null");
            return (Criteria) this;
        }

        public Criteria andServernameIsNotNull() {
            addCriterion("ServerName is not null");
            return (Criteria) this;
        }

        public Criteria andServernameEqualTo(String value) {
            addCriterion("ServerName =", value, "servername");
            return (Criteria) this;
        }

        public Criteria andServernameNotEqualTo(String value) {
            addCriterion("ServerName <>", value, "servername");
            return (Criteria) this;
        }

        public Criteria andServernameGreaterThan(String value) {
            addCriterion("ServerName >", value, "servername");
            return (Criteria) this;
        }

        public Criteria andServernameGreaterThanOrEqualTo(String value) {
            addCriterion("ServerName >=", value, "servername");
            return (Criteria) this;
        }

        public Criteria andServernameLessThan(String value) {
            addCriterion("ServerName <", value, "servername");
            return (Criteria) this;
        }

        public Criteria andServernameLessThanOrEqualTo(String value) {
            addCriterion("ServerName <=", value, "servername");
            return (Criteria) this;
        }

        public Criteria andServernameLike(String value) {
            addCriterion("ServerName like", value, "servername");
            return (Criteria) this;
        }

        public Criteria andServernameNotLike(String value) {
            addCriterion("ServerName not like", value, "servername");
            return (Criteria) this;
        }

        public Criteria andServernameIn(List<String> values) {
            addCriterion("ServerName in", values, "servername");
            return (Criteria) this;
        }

        public Criteria andServernameNotIn(List<String> values) {
            addCriterion("ServerName not in", values, "servername");
            return (Criteria) this;
        }

        public Criteria andServernameBetween(String value1, String value2) {
            addCriterion("ServerName between", value1, value2, "servername");
            return (Criteria) this;
        }

        public Criteria andServernameNotBetween(String value1, String value2) {
            addCriterion("ServerName not between", value1, value2, "servername");
            return (Criteria) this;
        }

        public Criteria andLocalnameIsNull() {
            addCriterion("LocalName is null");
            return (Criteria) this;
        }

        public Criteria andLocalnameIsNotNull() {
            addCriterion("LocalName is not null");
            return (Criteria) this;
        }

        public Criteria andLocalnameEqualTo(String value) {
            addCriterion("LocalName =", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameNotEqualTo(String value) {
            addCriterion("LocalName <>", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameGreaterThan(String value) {
            addCriterion("LocalName >", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameGreaterThanOrEqualTo(String value) {
            addCriterion("LocalName >=", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameLessThan(String value) {
            addCriterion("LocalName <", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameLessThanOrEqualTo(String value) {
            addCriterion("LocalName <=", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameLike(String value) {
            addCriterion("LocalName like", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameNotLike(String value) {
            addCriterion("LocalName not like", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameIn(List<String> values) {
            addCriterion("LocalName in", values, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameNotIn(List<String> values) {
            addCriterion("LocalName not in", values, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameBetween(String value1, String value2) {
            addCriterion("LocalName between", value1, value2, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameNotBetween(String value1, String value2) {
            addCriterion("LocalName not between", value1, value2, "localname");
            return (Criteria) this;
        }

        public Criteria andFiletypeIsNull() {
            addCriterion("FileType is null");
            return (Criteria) this;
        }

        public Criteria andFiletypeIsNotNull() {
            addCriterion("FileType is not null");
            return (Criteria) this;
        }

        public Criteria andFiletypeEqualTo(Integer value) {
            addCriterion("FileType =", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeNotEqualTo(Integer value) {
            addCriterion("FileType <>", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeGreaterThan(Integer value) {
            addCriterion("FileType >", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("FileType >=", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeLessThan(Integer value) {
            addCriterion("FileType <", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeLessThanOrEqualTo(Integer value) {
            addCriterion("FileType <=", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeIn(List<Integer> values) {
            addCriterion("FileType in", values, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeNotIn(List<Integer> values) {
            addCriterion("FileType not in", values, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeBetween(Integer value1, Integer value2) {
            addCriterion("FileType between", value1, value2, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeNotBetween(Integer value1, Integer value2) {
            addCriterion("FileType not between", value1, value2, "filetype");
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

        public Criteria andRelationidEqualTo(Integer value) {
            addCriterion("RelationId =", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidNotEqualTo(Integer value) {
            addCriterion("RelationId <>", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidGreaterThan(Integer value) {
            addCriterion("RelationId >", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidGreaterThanOrEqualTo(Integer value) {
            addCriterion("RelationId >=", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidLessThan(Integer value) {
            addCriterion("RelationId <", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidLessThanOrEqualTo(Integer value) {
            addCriterion("RelationId <=", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidIn(List<Integer> values) {
            addCriterion("RelationId in", values, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidNotIn(List<Integer> values) {
            addCriterion("RelationId not in", values, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidBetween(Integer value1, Integer value2) {
            addCriterion("RelationId between", value1, value2, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidNotBetween(Integer value1, Integer value2) {
            addCriterion("RelationId not between", value1, value2, "relationid");
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

        public Criteria andRelationtypeIsNull() {
            addCriterion("RelationType is null");
            return (Criteria) this;
        }

        public Criteria andRelationtypeIsNotNull() {
            addCriterion("RelationType is not null");
            return (Criteria) this;
        }

        public Criteria andRelationtypeEqualTo(Integer value) {
            addCriterion("RelationType =", value, "relationtype");
            return (Criteria) this;
        }

        public Criteria andRelationtypeNotEqualTo(Integer value) {
            addCriterion("RelationType <>", value, "relationtype");
            return (Criteria) this;
        }

        public Criteria andRelationtypeGreaterThan(Integer value) {
            addCriterion("RelationType >", value, "relationtype");
            return (Criteria) this;
        }

        public Criteria andRelationtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("RelationType >=", value, "relationtype");
            return (Criteria) this;
        }

        public Criteria andRelationtypeLessThan(Integer value) {
            addCriterion("RelationType <", value, "relationtype");
            return (Criteria) this;
        }

        public Criteria andRelationtypeLessThanOrEqualTo(Integer value) {
            addCriterion("RelationType <=", value, "relationtype");
            return (Criteria) this;
        }

        public Criteria andRelationtypeIn(List<Integer> values) {
            addCriterion("RelationType in", values, "relationtype");
            return (Criteria) this;
        }

        public Criteria andRelationtypeNotIn(List<Integer> values) {
            addCriterion("RelationType not in", values, "relationtype");
            return (Criteria) this;
        }

        public Criteria andRelationtypeBetween(Integer value1, Integer value2) {
            addCriterion("RelationType between", value1, value2, "relationtype");
            return (Criteria) this;
        }

        public Criteria andRelationtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("RelationType not between", value1, value2, "relationtype");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("Url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("Url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("Url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("Url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("Url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("Url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("Url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("Url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("Url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("Url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("Url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("Url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("Url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("Url not between", value1, value2, "url");
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