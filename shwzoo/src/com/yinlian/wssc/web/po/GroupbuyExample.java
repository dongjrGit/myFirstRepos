package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupbuyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupbuyExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andCpriceIsNull() {
            addCriterion("cprice is null");
            return (Criteria) this;
        }

        public Criteria andCpriceIsNotNull() {
            addCriterion("cprice is not null");
            return (Criteria) this;
        }

        public Criteria andCpriceEqualTo(Float value) {
            addCriterion("cprice =", value, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceNotEqualTo(Float value) {
            addCriterion("cprice <>", value, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceGreaterThan(Float value) {
            addCriterion("cprice >", value, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceGreaterThanOrEqualTo(Float value) {
            addCriterion("cprice >=", value, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceLessThan(Float value) {
            addCriterion("cprice <", value, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceLessThanOrEqualTo(Float value) {
            addCriterion("cprice <=", value, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceIn(List<Float> values) {
            addCriterion("cprice in", values, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceNotIn(List<Float> values) {
            addCriterion("cprice not in", values, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceBetween(Float value1, Float value2) {
            addCriterion("cprice between", value1, value2, "cprice");
            return (Criteria) this;
        }

        public Criteria andCpriceNotBetween(Float value1, Float value2) {
            addCriterion("cprice not between", value1, value2, "cprice");
            return (Criteria) this;
        }

        public Criteria andOpriceIsNull() {
            addCriterion("oprice is null");
            return (Criteria) this;
        }

        public Criteria andOpriceIsNotNull() {
            addCriterion("oprice is not null");
            return (Criteria) this;
        }

        public Criteria andOpriceEqualTo(Float value) {
            addCriterion("oprice =", value, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceNotEqualTo(Float value) {
            addCriterion("oprice <>", value, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceGreaterThan(Float value) {
            addCriterion("oprice >", value, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceGreaterThanOrEqualTo(Float value) {
            addCriterion("oprice >=", value, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceLessThan(Float value) {
            addCriterion("oprice <", value, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceLessThanOrEqualTo(Float value) {
            addCriterion("oprice <=", value, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceIn(List<Float> values) {
            addCriterion("oprice in", values, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceNotIn(List<Float> values) {
            addCriterion("oprice not in", values, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceBetween(Float value1, Float value2) {
            addCriterion("oprice between", value1, value2, "oprice");
            return (Criteria) this;
        }

        public Criteria andOpriceNotBetween(Float value1, Float value2) {
            addCriterion("oprice not between", value1, value2, "oprice");
            return (Criteria) this;
        }

        public Criteria andValiditystartIsNull() {
            addCriterion("validitystart is null");
            return (Criteria) this;
        }

        public Criteria andValiditystartIsNotNull() {
            addCriterion("validitystart is not null");
            return (Criteria) this;
        }

        public Criteria andValiditystartEqualTo(Date value) {
            addCriterion("validitystart =", value, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartNotEqualTo(Date value) {
            addCriterion("validitystart <>", value, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartGreaterThan(Date value) {
            addCriterion("validitystart >", value, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartGreaterThanOrEqualTo(Date value) {
            addCriterion("validitystart >=", value, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartLessThan(Date value) {
            addCriterion("validitystart <", value, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartLessThanOrEqualTo(Date value) {
            addCriterion("validitystart <=", value, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartIn(List<Date> values) {
            addCriterion("validitystart in", values, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartNotIn(List<Date> values) {
            addCriterion("validitystart not in", values, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartBetween(Date value1, Date value2) {
            addCriterion("validitystart between", value1, value2, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValiditystartNotBetween(Date value1, Date value2) {
            addCriterion("validitystart not between", value1, value2, "validitystart");
            return (Criteria) this;
        }

        public Criteria andValidityendIsNull() {
            addCriterion("validityend is null");
            return (Criteria) this;
        }

        public Criteria andValidityendIsNotNull() {
            addCriterion("validityend is not null");
            return (Criteria) this;
        }

        public Criteria andValidityendEqualTo(Date value) {
            addCriterion("validityend =", value, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendNotEqualTo(Date value) {
            addCriterion("validityend <>", value, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendGreaterThan(Date value) {
            addCriterion("validityend >", value, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendGreaterThanOrEqualTo(Date value) {
            addCriterion("validityend >=", value, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendLessThan(Date value) {
            addCriterion("validityend <", value, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendLessThanOrEqualTo(Date value) {
            addCriterion("validityend <=", value, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendIn(List<Date> values) {
            addCriterion("validityend in", values, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendNotIn(List<Date> values) {
            addCriterion("validityend not in", values, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendBetween(Date value1, Date value2) {
            addCriterion("validityend between", value1, value2, "validityend");
            return (Criteria) this;
        }

        public Criteria andValidityendNotBetween(Date value1, Date value2) {
            addCriterion("validityend not between", value1, value2, "validityend");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andIsanytimeIsNull() {
            addCriterion("isAnytime is null");
            return (Criteria) this;
        }

        public Criteria andIsanytimeIsNotNull() {
            addCriterion("isAnytime is not null");
            return (Criteria) this;
        }

        public Criteria andIsanytimeEqualTo(Boolean value) {
            addCriterion("isAnytime =", value, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeNotEqualTo(Boolean value) {
            addCriterion("isAnytime <>", value, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeGreaterThan(Boolean value) {
            addCriterion("isAnytime >", value, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isAnytime >=", value, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeLessThan(Boolean value) {
            addCriterion("isAnytime <", value, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeLessThanOrEqualTo(Boolean value) {
            addCriterion("isAnytime <=", value, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeIn(List<Boolean> values) {
            addCriterion("isAnytime in", values, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeNotIn(List<Boolean> values) {
            addCriterion("isAnytime not in", values, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeBetween(Boolean value1, Boolean value2) {
            addCriterion("isAnytime between", value1, value2, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsanytimeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isAnytime not between", value1, value2, "isanytime");
            return (Criteria) this;
        }

        public Criteria andIsbookIsNull() {
            addCriterion("isBook is null");
            return (Criteria) this;
        }

        public Criteria andIsbookIsNotNull() {
            addCriterion("isBook is not null");
            return (Criteria) this;
        }

        public Criteria andIsbookEqualTo(Boolean value) {
            addCriterion("isBook =", value, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookNotEqualTo(Boolean value) {
            addCriterion("isBook <>", value, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookGreaterThan(Boolean value) {
            addCriterion("isBook >", value, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isBook >=", value, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookLessThan(Boolean value) {
            addCriterion("isBook <", value, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookLessThanOrEqualTo(Boolean value) {
            addCriterion("isBook <=", value, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookIn(List<Boolean> values) {
            addCriterion("isBook in", values, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookNotIn(List<Boolean> values) {
            addCriterion("isBook not in", values, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookBetween(Boolean value1, Boolean value2) {
            addCriterion("isBook between", value1, value2, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsbookNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isBook not between", value1, value2, "isbook");
            return (Criteria) this;
        }

        public Criteria andIsexpiredIsNull() {
            addCriterion("isExpired is null");
            return (Criteria) this;
        }

        public Criteria andIsexpiredIsNotNull() {
            addCriterion("isExpired is not null");
            return (Criteria) this;
        }

        public Criteria andIsexpiredEqualTo(Boolean value) {
            addCriterion("isExpired =", value, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredNotEqualTo(Boolean value) {
            addCriterion("isExpired <>", value, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredGreaterThan(Boolean value) {
            addCriterion("isExpired >", value, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isExpired >=", value, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredLessThan(Boolean value) {
            addCriterion("isExpired <", value, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredLessThanOrEqualTo(Boolean value) {
            addCriterion("isExpired <=", value, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredIn(List<Boolean> values) {
            addCriterion("isExpired in", values, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredNotIn(List<Boolean> values) {
            addCriterion("isExpired not in", values, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredBetween(Boolean value1, Boolean value2) {
            addCriterion("isExpired between", value1, value2, "isexpired");
            return (Criteria) this;
        }

        public Criteria andIsexpiredNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isExpired not between", value1, value2, "isexpired");
            return (Criteria) this;
        }

        public Criteria andDetaildescIsNull() {
            addCriterion("detaildesc is null");
            return (Criteria) this;
        }

        public Criteria andDetaildescIsNotNull() {
            addCriterion("detaildesc is not null");
            return (Criteria) this;
        }

        public Criteria andDetaildescEqualTo(String value) {
            addCriterion("detaildesc =", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescNotEqualTo(String value) {
            addCriterion("detaildesc <>", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescGreaterThan(String value) {
            addCriterion("detaildesc >", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescGreaterThanOrEqualTo(String value) {
            addCriterion("detaildesc >=", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescLessThan(String value) {
            addCriterion("detaildesc <", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescLessThanOrEqualTo(String value) {
            addCriterion("detaildesc <=", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescLike(String value) {
            addCriterion("detaildesc like", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescNotLike(String value) {
            addCriterion("detaildesc not like", value, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescIn(List<String> values) {
            addCriterion("detaildesc in", values, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescNotIn(List<String> values) {
            addCriterion("detaildesc not in", values, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescBetween(String value1, String value2) {
            addCriterion("detaildesc between", value1, value2, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andDetaildescNotBetween(String value1, String value2) {
            addCriterion("detaildesc not between", value1, value2, "detaildesc");
            return (Criteria) this;
        }

        public Criteria andListdescIsNull() {
            addCriterion("listdesc is null");
            return (Criteria) this;
        }

        public Criteria andListdescIsNotNull() {
            addCriterion("listdesc is not null");
            return (Criteria) this;
        }

        public Criteria andListdescEqualTo(String value) {
            addCriterion("listdesc =", value, "listdesc");
            return (Criteria) this;
        }

        public Criteria andListdescNotEqualTo(String value) {
            addCriterion("listdesc <>", value, "listdesc");
            return (Criteria) this;
        }

        public Criteria andListdescGreaterThan(String value) {
            addCriterion("listdesc >", value, "listdesc");
            return (Criteria) this;
        }

        public Criteria andListdescGreaterThanOrEqualTo(String value) {
            addCriterion("listdesc >=", value, "listdesc");
            return (Criteria) this;
        }

        public Criteria andListdescLessThan(String value) {
            addCriterion("listdesc <", value, "listdesc");
            return (Criteria) this;
        }

        public Criteria andListdescLessThanOrEqualTo(String value) {
            addCriterion("listdesc <=", value, "listdesc");
            return (Criteria) this;
        }

        public Criteria andListdescLike(String value) {
            addCriterion("listdesc like", value, "listdesc");
            return (Criteria) this;
        }

        public Criteria andListdescNotLike(String value) {
            addCriterion("listdesc not like", value, "listdesc");
            return (Criteria) this;
        }

        public Criteria andListdescIn(List<String> values) {
            addCriterion("listdesc in", values, "listdesc");
            return (Criteria) this;
        }

        public Criteria andListdescNotIn(List<String> values) {
            addCriterion("listdesc not in", values, "listdesc");
            return (Criteria) this;
        }

        public Criteria andListdescBetween(String value1, String value2) {
            addCriterion("listdesc between", value1, value2, "listdesc");
            return (Criteria) this;
        }

        public Criteria andListdescNotBetween(String value1, String value2) {
            addCriterion("listdesc not between", value1, value2, "listdesc");
            return (Criteria) this;
        }

        public Criteria andShopidIsNull() {
            addCriterion("shopid is null");
            return (Criteria) this;
        }

        public Criteria andShopidIsNotNull() {
            addCriterion("shopid is not null");
            return (Criteria) this;
        }

        public Criteria andShopidEqualTo(Integer value) {
            addCriterion("shopid =", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotEqualTo(Integer value) {
            addCriterion("shopid <>", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThan(Integer value) {
            addCriterion("shopid >", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThanOrEqualTo(Integer value) {
            addCriterion("shopid >=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThan(Integer value) {
            addCriterion("shopid <", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThanOrEqualTo(Integer value) {
            addCriterion("shopid <=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidIn(List<Integer> values) {
            addCriterion("shopid in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotIn(List<Integer> values) {
            addCriterion("shopid not in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidBetween(Integer value1, Integer value2) {
            addCriterion("shopid between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotBetween(Integer value1, Integer value2) {
            addCriterion("shopid not between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andStockIsNull() {
            addCriterion("stock is null");
            return (Criteria) this;
        }

        public Criteria andStockIsNotNull() {
            addCriterion("stock is not null");
            return (Criteria) this;
        }

        public Criteria andStockEqualTo(Integer value) {
            addCriterion("stock =", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotEqualTo(Integer value) {
            addCriterion("stock <>", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThan(Integer value) {
            addCriterion("stock >", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock >=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThan(Integer value) {
            addCriterion("stock <", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThanOrEqualTo(Integer value) {
            addCriterion("stock <=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockIn(List<Integer> values) {
            addCriterion("stock in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotIn(List<Integer> values) {
            addCriterion("stock not in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockBetween(Integer value1, Integer value2) {
            addCriterion("stock between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotBetween(Integer value1, Integer value2) {
            addCriterion("stock not between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andSalescountIsNull() {
            addCriterion("salesCount is null");
            return (Criteria) this;
        }

        public Criteria andSalescountIsNotNull() {
            addCriterion("salesCount is not null");
            return (Criteria) this;
        }

        public Criteria andSalescountEqualTo(Integer value) {
            addCriterion("salesCount =", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountNotEqualTo(Integer value) {
            addCriterion("salesCount <>", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountGreaterThan(Integer value) {
            addCriterion("salesCount >", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountGreaterThanOrEqualTo(Integer value) {
            addCriterion("salesCount >=", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountLessThan(Integer value) {
            addCriterion("salesCount <", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountLessThanOrEqualTo(Integer value) {
            addCriterion("salesCount <=", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountIn(List<Integer> values) {
            addCriterion("salesCount in", values, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountNotIn(List<Integer> values) {
            addCriterion("salesCount not in", values, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountBetween(Integer value1, Integer value2) {
            addCriterion("salesCount between", value1, value2, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountNotBetween(Integer value1, Integer value2) {
            addCriterion("salesCount not between", value1, value2, "salescount");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNull() {
            addCriterion("isdel is null");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNotNull() {
            addCriterion("isdel is not null");
            return (Criteria) this;
        }

        public Criteria andIsdelEqualTo(Boolean value) {
            addCriterion("isdel =", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotEqualTo(Boolean value) {
            addCriterion("isdel <>", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThan(Boolean value) {
            addCriterion("isdel >", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isdel >=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThan(Boolean value) {
            addCriterion("isdel <", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThanOrEqualTo(Boolean value) {
            addCriterion("isdel <=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelIn(List<Boolean> values) {
            addCriterion("isdel in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotIn(List<Boolean> values) {
            addCriterion("isdel not in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelBetween(Boolean value1, Boolean value2) {
            addCriterion("isdel between", value1, value2, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isdel not between", value1, value2, "isdel");
            return (Criteria) this;
        }

        public Criteria andCreattimeIsNull() {
            addCriterion("creattime is null");
            return (Criteria) this;
        }

        public Criteria andCreattimeIsNotNull() {
            addCriterion("creattime is not null");
            return (Criteria) this;
        }

        public Criteria andCreattimeEqualTo(Date value) {
            addCriterion("creattime =", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotEqualTo(Date value) {
            addCriterion("creattime <>", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeGreaterThan(Date value) {
            addCriterion("creattime >", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeGreaterThanOrEqualTo(Date value) {
            addCriterion("creattime >=", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeLessThan(Date value) {
            addCriterion("creattime <", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeLessThanOrEqualTo(Date value) {
            addCriterion("creattime <=", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeIn(List<Date> values) {
            addCriterion("creattime in", values, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotIn(List<Date> values) {
            addCriterion("creattime not in", values, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeBetween(Date value1, Date value2) {
            addCriterion("creattime between", value1, value2, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotBetween(Date value1, Date value2) {
            addCriterion("creattime not between", value1, value2, "creattime");
            return (Criteria) this;
        }

        public Criteria andDeltimeIsNull() {
            addCriterion("deltime is null");
            return (Criteria) this;
        }

        public Criteria andDeltimeIsNotNull() {
            addCriterion("deltime is not null");
            return (Criteria) this;
        }

        public Criteria andDeltimeEqualTo(Date value) {
            addCriterion("deltime =", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotEqualTo(Date value) {
            addCriterion("deltime <>", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeGreaterThan(Date value) {
            addCriterion("deltime >", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeGreaterThanOrEqualTo(Date value) {
            addCriterion("deltime >=", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeLessThan(Date value) {
            addCriterion("deltime <", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeLessThanOrEqualTo(Date value) {
            addCriterion("deltime <=", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeIn(List<Date> values) {
            addCriterion("deltime in", values, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotIn(List<Date> values) {
            addCriterion("deltime not in", values, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeBetween(Date value1, Date value2) {
            addCriterion("deltime between", value1, value2, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotBetween(Date value1, Date value2) {
            addCriterion("deltime not between", value1, value2, "deltime");
            return (Criteria) this;
        }
        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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