package com.yinlian.wssc.web.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpuExample() {
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

        public Criteria andPronumIsNull() {
            addCriterion("ProNum is null");
            return (Criteria) this;
        }

        public Criteria andPronumIsNotNull() {
            addCriterion("ProNum is not null");
            return (Criteria) this;
        }

        public Criteria andPronumEqualTo(String value) {
            addCriterion("ProNum =", value, "pronum");
            return (Criteria) this;
        }

        public Criteria andPronumNotEqualTo(String value) {
            addCriterion("ProNum <>", value, "pronum");
            return (Criteria) this;
        }

        public Criteria andPronumGreaterThan(String value) {
            addCriterion("ProNum >", value, "pronum");
            return (Criteria) this;
        }

        public Criteria andPronumGreaterThanOrEqualTo(String value) {
            addCriterion("ProNum >=", value, "pronum");
            return (Criteria) this;
        }

        public Criteria andPronumLessThan(String value) {
            addCriterion("ProNum <", value, "pronum");
            return (Criteria) this;
        }

        public Criteria andPronumLessThanOrEqualTo(String value) {
            addCriterion("ProNum <=", value, "pronum");
            return (Criteria) this;
        }

        public Criteria andPronumLike(String value) {
            addCriterion("ProNum like", value, "pronum");
            return (Criteria) this;
        }

        public Criteria andPronumNotLike(String value) {
            addCriterion("ProNum not like", value, "pronum");
            return (Criteria) this;
        }

        public Criteria andPronumIn(List<String> values) {
            addCriterion("ProNum in", values, "pronum");
            return (Criteria) this;
        }

        public Criteria andPronumNotIn(List<String> values) {
            addCriterion("ProNum not in", values, "pronum");
            return (Criteria) this;
        }

        public Criteria andPronumBetween(String value1, String value2) {
            addCriterion("ProNum between", value1, value2, "pronum");
            return (Criteria) this;
        }

        public Criteria andPronumNotBetween(String value1, String value2) {
            addCriterion("ProNum not between", value1, value2, "pronum");
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

        public Criteria andTitleShortIsNull() {
            addCriterion("Title_short is null");
            return (Criteria) this;
        }

        public Criteria andTitleShortIsNotNull() {
            addCriterion("Title_short is not null");
            return (Criteria) this;
        }

        public Criteria andTitleShortEqualTo(String value) {
            addCriterion("Title_short =", value, "titleShort");
            return (Criteria) this;
        }

        public Criteria andTitleShortNotEqualTo(String value) {
            addCriterion("Title_short <>", value, "titleShort");
            return (Criteria) this;
        }

        public Criteria andTitleShortGreaterThan(String value) {
            addCriterion("Title_short >", value, "titleShort");
            return (Criteria) this;
        }

        public Criteria andTitleShortGreaterThanOrEqualTo(String value) {
            addCriterion("Title_short >=", value, "titleShort");
            return (Criteria) this;
        }

        public Criteria andTitleShortLessThan(String value) {
            addCriterion("Title_short <", value, "titleShort");
            return (Criteria) this;
        }

        public Criteria andTitleShortLessThanOrEqualTo(String value) {
            addCriterion("Title_short <=", value, "titleShort");
            return (Criteria) this;
        }

        public Criteria andTitleShortLike(String value) {
            addCriterion("Title_short like", value, "titleShort");
            return (Criteria) this;
        }

        public Criteria andTitleShortNotLike(String value) {
            addCriterion("Title_short not like", value, "titleShort");
            return (Criteria) this;
        }

        public Criteria andTitleShortIn(List<String> values) {
            addCriterion("Title_short in", values, "titleShort");
            return (Criteria) this;
        }

        public Criteria andTitleShortNotIn(List<String> values) {
            addCriterion("Title_short not in", values, "titleShort");
            return (Criteria) this;
        }

        public Criteria andTitleShortBetween(String value1, String value2) {
            addCriterion("Title_short between", value1, value2, "titleShort");
            return (Criteria) this;
        }

        public Criteria andTitleShortNotBetween(String value1, String value2) {
            addCriterion("Title_short not between", value1, value2, "titleShort");
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

        public Criteria andUseridIsNull() {
            addCriterion("UserID is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("UserID is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("UserID =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("UserID <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("UserID >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("UserID >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("UserID <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("UserID <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("UserID in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("UserID not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("UserID between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("UserID not between", value1, value2, "userid");
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

        public Criteria andImgurlAppIsNull() {
            addCriterion("ImgUrl_App is null");
            return (Criteria) this;
        }

        public Criteria andImgurlAppIsNotNull() {
            addCriterion("ImgUrl_App is not null");
            return (Criteria) this;
        }

        public Criteria andImgurlAppEqualTo(String value) {
            addCriterion("ImgUrl_App =", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppNotEqualTo(String value) {
            addCriterion("ImgUrl_App <>", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppGreaterThan(String value) {
            addCriterion("ImgUrl_App >", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppGreaterThanOrEqualTo(String value) {
            addCriterion("ImgUrl_App >=", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppLessThan(String value) {
            addCriterion("ImgUrl_App <", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppLessThanOrEqualTo(String value) {
            addCriterion("ImgUrl_App <=", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppLike(String value) {
            addCriterion("ImgUrl_App like", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppNotLike(String value) {
            addCriterion("ImgUrl_App not like", value, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppIn(List<String> values) {
            addCriterion("ImgUrl_App in", values, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppNotIn(List<String> values) {
            addCriterion("ImgUrl_App not in", values, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppBetween(String value1, String value2) {
            addCriterion("ImgUrl_App between", value1, value2, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgurlAppNotBetween(String value1, String value2) {
            addCriterion("ImgUrl_App not between", value1, value2, "imgurlApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallIsNull() {
            addCriterion("ImgSmall is null");
            return (Criteria) this;
        }

        public Criteria andImgsmallIsNotNull() {
            addCriterion("ImgSmall is not null");
            return (Criteria) this;
        }

        public Criteria andImgsmallEqualTo(String value) {
            addCriterion("ImgSmall =", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallNotEqualTo(String value) {
            addCriterion("ImgSmall <>", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallGreaterThan(String value) {
            addCriterion("ImgSmall >", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallGreaterThanOrEqualTo(String value) {
            addCriterion("ImgSmall >=", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallLessThan(String value) {
            addCriterion("ImgSmall <", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallLessThanOrEqualTo(String value) {
            addCriterion("ImgSmall <=", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallLike(String value) {
            addCriterion("ImgSmall like", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallNotLike(String value) {
            addCriterion("ImgSmall not like", value, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallIn(List<String> values) {
            addCriterion("ImgSmall in", values, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallNotIn(List<String> values) {
            addCriterion("ImgSmall not in", values, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallBetween(String value1, String value2) {
            addCriterion("ImgSmall between", value1, value2, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallNotBetween(String value1, String value2) {
            addCriterion("ImgSmall not between", value1, value2, "imgsmall");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppIsNull() {
            addCriterion("ImgSmall_App is null");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppIsNotNull() {
            addCriterion("ImgSmall_App is not null");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppEqualTo(String value) {
            addCriterion("ImgSmall_App =", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppNotEqualTo(String value) {
            addCriterion("ImgSmall_App <>", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppGreaterThan(String value) {
            addCriterion("ImgSmall_App >", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppGreaterThanOrEqualTo(String value) {
            addCriterion("ImgSmall_App >=", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppLessThan(String value) {
            addCriterion("ImgSmall_App <", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppLessThanOrEqualTo(String value) {
            addCriterion("ImgSmall_App <=", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppLike(String value) {
            addCriterion("ImgSmall_App like", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppNotLike(String value) {
            addCriterion("ImgSmall_App not like", value, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppIn(List<String> values) {
            addCriterion("ImgSmall_App in", values, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppNotIn(List<String> values) {
            addCriterion("ImgSmall_App not in", values, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppBetween(String value1, String value2) {
            addCriterion("ImgSmall_App between", value1, value2, "imgsmallApp");
            return (Criteria) this;
        }

        public Criteria andImgsmallAppNotBetween(String value1, String value2) {
            addCriterion("ImgSmall_App not between", value1, value2, "imgsmallApp");
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

        public Criteria andCustomcidIsNull() {
            addCriterion("CustomCID is null");
            return (Criteria) this;
        }

        public Criteria andCustomcidIsNotNull() {
            addCriterion("CustomCID is not null");
            return (Criteria) this;
        }

        public Criteria andCustomcidEqualTo(Integer value) {
            addCriterion("CustomCID =", value, "customcid");
            return (Criteria) this;
        }

        public Criteria andCustomcidNotEqualTo(Integer value) {
            addCriterion("CustomCID <>", value, "customcid");
            return (Criteria) this;
        }

        public Criteria andCustomcidGreaterThan(Integer value) {
            addCriterion("CustomCID >", value, "customcid");
            return (Criteria) this;
        }

        public Criteria andCustomcidGreaterThanOrEqualTo(Integer value) {
            addCriterion("CustomCID >=", value, "customcid");
            return (Criteria) this;
        }

        public Criteria andCustomcidLessThan(Integer value) {
            addCriterion("CustomCID <", value, "customcid");
            return (Criteria) this;
        }

        public Criteria andCustomcidLessThanOrEqualTo(Integer value) {
            addCriterion("CustomCID <=", value, "customcid");
            return (Criteria) this;
        }

        public Criteria andCustomcidIn(List<Integer> values) {
            addCriterion("CustomCID in", values, "customcid");
            return (Criteria) this;
        }

        public Criteria andCustomcidNotIn(List<Integer> values) {
            addCriterion("CustomCID not in", values, "customcid");
            return (Criteria) this;
        }

        public Criteria andCustomcidBetween(Integer value1, Integer value2) {
            addCriterion("CustomCID between", value1, value2, "customcid");
            return (Criteria) this;
        }

        public Criteria andCustomcidNotBetween(Integer value1, Integer value2) {
            addCriterion("CustomCID not between", value1, value2, "customcid");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("Tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("Tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("Tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("Tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("Tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("Tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("Tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("Tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("Tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("Tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("Tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("Tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("Tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("Tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andProducerIsNull() {
            addCriterion("Producer is null");
            return (Criteria) this;
        }

        public Criteria andProducerIsNotNull() {
            addCriterion("Producer is not null");
            return (Criteria) this;
        }

        public Criteria andProducerEqualTo(String value) {
            addCriterion("Producer =", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerNotEqualTo(String value) {
            addCriterion("Producer <>", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerGreaterThan(String value) {
            addCriterion("Producer >", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerGreaterThanOrEqualTo(String value) {
            addCriterion("Producer >=", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerLessThan(String value) {
            addCriterion("Producer <", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerLessThanOrEqualTo(String value) {
            addCriterion("Producer <=", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerLike(String value) {
            addCriterion("Producer like", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerNotLike(String value) {
            addCriterion("Producer not like", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerIn(List<String> values) {
            addCriterion("Producer in", values, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerNotIn(List<String> values) {
            addCriterion("Producer not in", values, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerBetween(String value1, String value2) {
            addCriterion("Producer between", value1, value2, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerNotBetween(String value1, String value2) {
            addCriterion("Producer not between", value1, value2, "producer");
            return (Criteria) this;
        }

        public Criteria andBrandidIsNull() {
            addCriterion("BrandID is null");
            return (Criteria) this;
        }

        public Criteria andBrandidIsNotNull() {
            addCriterion("BrandID is not null");
            return (Criteria) this;
        }

        public Criteria andBrandidEqualTo(Integer value) {
            addCriterion("BrandID =", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidNotEqualTo(Integer value) {
            addCriterion("BrandID <>", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidGreaterThan(Integer value) {
            addCriterion("BrandID >", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidGreaterThanOrEqualTo(Integer value) {
            addCriterion("BrandID >=", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidLessThan(Integer value) {
            addCriterion("BrandID <", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidLessThanOrEqualTo(Integer value) {
            addCriterion("BrandID <=", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidIn(List<Integer> values) {
            addCriterion("BrandID in", values, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidNotIn(List<Integer> values) {
            addCriterion("BrandID not in", values, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidBetween(Integer value1, Integer value2) {
            addCriterion("BrandID between", value1, value2, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidNotBetween(Integer value1, Integer value2) {
            addCriterion("BrandID not between", value1, value2, "brandid");
            return (Criteria) this;
        }

        public Criteria andPackIsNull() {
            addCriterion("Pack is null");
            return (Criteria) this;
        }

        public Criteria andPackIsNotNull() {
            addCriterion("Pack is not null");
            return (Criteria) this;
        }

        public Criteria andPackEqualTo(Integer value) {
            addCriterion("Pack =", value, "pack");
            return (Criteria) this;
        }

        public Criteria andPackNotEqualTo(Integer value) {
            addCriterion("Pack <>", value, "pack");
            return (Criteria) this;
        }

        public Criteria andPackGreaterThan(Integer value) {
            addCriterion("Pack >", value, "pack");
            return (Criteria) this;
        }

        public Criteria andPackGreaterThanOrEqualTo(Integer value) {
            addCriterion("Pack >=", value, "pack");
            return (Criteria) this;
        }

        public Criteria andPackLessThan(Integer value) {
            addCriterion("Pack <", value, "pack");
            return (Criteria) this;
        }

        public Criteria andPackLessThanOrEqualTo(Integer value) {
            addCriterion("Pack <=", value, "pack");
            return (Criteria) this;
        }

        public Criteria andPackIn(List<Integer> values) {
            addCriterion("Pack in", values, "pack");
            return (Criteria) this;
        }

        public Criteria andPackNotIn(List<Integer> values) {
            addCriterion("Pack not in", values, "pack");
            return (Criteria) this;
        }

        public Criteria andPackBetween(Integer value1, Integer value2) {
            addCriterion("Pack between", value1, value2, "pack");
            return (Criteria) this;
        }

        public Criteria andPackNotBetween(Integer value1, Integer value2) {
            addCriterion("Pack not between", value1, value2, "pack");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("Weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("Weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(BigDecimal value) {
            addCriterion("Weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(BigDecimal value) {
            addCriterion("Weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(BigDecimal value) {
            addCriterion("Weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(BigDecimal value) {
            addCriterion("Weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<BigDecimal> values) {
            addCriterion("Weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<BigDecimal> values) {
            addCriterion("Weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andShelflifeIsNull() {
            addCriterion("ShelfLife is null");
            return (Criteria) this;
        }

        public Criteria andShelflifeIsNotNull() {
            addCriterion("ShelfLife is not null");
            return (Criteria) this;
        }

        public Criteria andShelflifeEqualTo(Integer value) {
            addCriterion("ShelfLife =", value, "shelflife");
            return (Criteria) this;
        }

        public Criteria andShelflifeNotEqualTo(Integer value) {
            addCriterion("ShelfLife <>", value, "shelflife");
            return (Criteria) this;
        }

        public Criteria andShelflifeGreaterThan(Integer value) {
            addCriterion("ShelfLife >", value, "shelflife");
            return (Criteria) this;
        }

        public Criteria andShelflifeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ShelfLife >=", value, "shelflife");
            return (Criteria) this;
        }

        public Criteria andShelflifeLessThan(Integer value) {
            addCriterion("ShelfLife <", value, "shelflife");
            return (Criteria) this;
        }

        public Criteria andShelflifeLessThanOrEqualTo(Integer value) {
            addCriterion("ShelfLife <=", value, "shelflife");
            return (Criteria) this;
        }

        public Criteria andShelflifeIn(List<Integer> values) {
            addCriterion("ShelfLife in", values, "shelflife");
            return (Criteria) this;
        }

        public Criteria andShelflifeNotIn(List<Integer> values) {
            addCriterion("ShelfLife not in", values, "shelflife");
            return (Criteria) this;
        }

        public Criteria andShelflifeBetween(Integer value1, Integer value2) {
            addCriterion("ShelfLife between", value1, value2, "shelflife");
            return (Criteria) this;
        }

        public Criteria andShelflifeNotBetween(Integer value1, Integer value2) {
            addCriterion("ShelfLife not between", value1, value2, "shelflife");
            return (Criteria) this;
        }

        public Criteria andCommentcountIsNull() {
            addCriterion("CommentCount is null");
            return (Criteria) this;
        }

        public Criteria andCommentcountIsNotNull() {
            addCriterion("CommentCount is not null");
            return (Criteria) this;
        }

        public Criteria andCommentcountEqualTo(Integer value) {
            addCriterion("CommentCount =", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountNotEqualTo(Integer value) {
            addCriterion("CommentCount <>", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountGreaterThan(Integer value) {
            addCriterion("CommentCount >", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("CommentCount >=", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountLessThan(Integer value) {
            addCriterion("CommentCount <", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountLessThanOrEqualTo(Integer value) {
            addCriterion("CommentCount <=", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountIn(List<Integer> values) {
            addCriterion("CommentCount in", values, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountNotIn(List<Integer> values) {
            addCriterion("CommentCount not in", values, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountBetween(Integer value1, Integer value2) {
            addCriterion("CommentCount between", value1, value2, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountNotBetween(Integer value1, Integer value2) {
            addCriterion("CommentCount not between", value1, value2, "commentcount");
            return (Criteria) this;
        }

        public Criteria andFreightidIsNull() {
            addCriterion("FreightID is null");
            return (Criteria) this;
        }

        public Criteria andFreightidIsNotNull() {
            addCriterion("FreightID is not null");
            return (Criteria) this;
        }

        public Criteria andFreightidEqualTo(Integer value) {
            addCriterion("FreightID =", value, "freightid");
            return (Criteria) this;
        }

        public Criteria andFreightidNotEqualTo(Integer value) {
            addCriterion("FreightID <>", value, "freightid");
            return (Criteria) this;
        }

        public Criteria andFreightidGreaterThan(Integer value) {
            addCriterion("FreightID >", value, "freightid");
            return (Criteria) this;
        }

        public Criteria andFreightidGreaterThanOrEqualTo(Integer value) {
            addCriterion("FreightID >=", value, "freightid");
            return (Criteria) this;
        }

        public Criteria andFreightidLessThan(Integer value) {
            addCriterion("FreightID <", value, "freightid");
            return (Criteria) this;
        }

        public Criteria andFreightidLessThanOrEqualTo(Integer value) {
            addCriterion("FreightID <=", value, "freightid");
            return (Criteria) this;
        }

        public Criteria andFreightidIn(List<Integer> values) {
            addCriterion("FreightID in", values, "freightid");
            return (Criteria) this;
        }

        public Criteria andFreightidNotIn(List<Integer> values) {
            addCriterion("FreightID not in", values, "freightid");
            return (Criteria) this;
        }

        public Criteria andFreightidBetween(Integer value1, Integer value2) {
            addCriterion("FreightID between", value1, value2, "freightid");
            return (Criteria) this;
        }

        public Criteria andFreightidNotBetween(Integer value1, Integer value2) {
            addCriterion("FreightID not between", value1, value2, "freightid");
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

        public Criteria andIsownedIsNull() {
            addCriterion("IsOwned is null");
            return (Criteria) this;
        }

        public Criteria andIsownedIsNotNull() {
            addCriterion("IsOwned is not null");
            return (Criteria) this;
        }

        public Criteria andIsownedEqualTo(Boolean value) {
            addCriterion("IsOwned =", value, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedNotEqualTo(Boolean value) {
            addCriterion("IsOwned <>", value, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedGreaterThan(Boolean value) {
            addCriterion("IsOwned >", value, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsOwned >=", value, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedLessThan(Boolean value) {
            addCriterion("IsOwned <", value, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedLessThanOrEqualTo(Boolean value) {
            addCriterion("IsOwned <=", value, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedIn(List<Boolean> values) {
            addCriterion("IsOwned in", values, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedNotIn(List<Boolean> values) {
            addCriterion("IsOwned not in", values, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedBetween(Boolean value1, Boolean value2) {
            addCriterion("IsOwned between", value1, value2, "isowned");
            return (Criteria) this;
        }

        public Criteria andIsownedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsOwned not between", value1, value2, "isowned");
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

        public Criteria andPriceIsNull() {
            addCriterion("Price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("Price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Float value) {
            addCriterion("Price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Float value) {
            addCriterion("Price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Float value) {
            addCriterion("Price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("Price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Float value) {
            addCriterion("Price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Float value) {
            addCriterion("Price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Float> values) {
            addCriterion("Price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Float> values) {
            addCriterion("Price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Float value1, Float value2) {
            addCriterion("Price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Float value1, Float value2) {
            addCriterion("Price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPraiseIsNull() {
            addCriterion("Praise is null");
            return (Criteria) this;
        }

        public Criteria andPraiseIsNotNull() {
            addCriterion("Praise is not null");
            return (Criteria) this;
        }

        public Criteria andPraiseEqualTo(Integer value) {
            addCriterion("Praise =", value, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseNotEqualTo(Integer value) {
            addCriterion("Praise <>", value, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseGreaterThan(Integer value) {
            addCriterion("Praise >", value, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseGreaterThanOrEqualTo(Integer value) {
            addCriterion("Praise >=", value, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseLessThan(Integer value) {
            addCriterion("Praise <", value, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseLessThanOrEqualTo(Integer value) {
            addCriterion("Praise <=", value, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseIn(List<Integer> values) {
            addCriterion("Praise in", values, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseNotIn(List<Integer> values) {
            addCriterion("Praise not in", values, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseBetween(Integer value1, Integer value2) {
            addCriterion("Praise between", value1, value2, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseNotBetween(Integer value1, Integer value2) {
            addCriterion("Praise not between", value1, value2, "praise");
            return (Criteria) this;
        }

        public Criteria andMediumreviewIsNull() {
            addCriterion("MediumReview is null");
            return (Criteria) this;
        }

        public Criteria andMediumreviewIsNotNull() {
            addCriterion("MediumReview is not null");
            return (Criteria) this;
        }

        public Criteria andMediumreviewEqualTo(Integer value) {
            addCriterion("MediumReview =", value, "mediumreview");
            return (Criteria) this;
        }

        public Criteria andMediumreviewNotEqualTo(Integer value) {
            addCriterion("MediumReview <>", value, "mediumreview");
            return (Criteria) this;
        }

        public Criteria andMediumreviewGreaterThan(Integer value) {
            addCriterion("MediumReview >", value, "mediumreview");
            return (Criteria) this;
        }

        public Criteria andMediumreviewGreaterThanOrEqualTo(Integer value) {
            addCriterion("MediumReview >=", value, "mediumreview");
            return (Criteria) this;
        }

        public Criteria andMediumreviewLessThan(Integer value) {
            addCriterion("MediumReview <", value, "mediumreview");
            return (Criteria) this;
        }

        public Criteria andMediumreviewLessThanOrEqualTo(Integer value) {
            addCriterion("MediumReview <=", value, "mediumreview");
            return (Criteria) this;
        }

        public Criteria andMediumreviewIn(List<Integer> values) {
            addCriterion("MediumReview in", values, "mediumreview");
            return (Criteria) this;
        }

        public Criteria andMediumreviewNotIn(List<Integer> values) {
            addCriterion("MediumReview not in", values, "mediumreview");
            return (Criteria) this;
        }

        public Criteria andMediumreviewBetween(Integer value1, Integer value2) {
            addCriterion("MediumReview between", value1, value2, "mediumreview");
            return (Criteria) this;
        }

        public Criteria andMediumreviewNotBetween(Integer value1, Integer value2) {
            addCriterion("MediumReview not between", value1, value2, "mediumreview");
            return (Criteria) this;
        }

        public Criteria andBadreviewIsNull() {
            addCriterion("BadReview is null");
            return (Criteria) this;
        }

        public Criteria andBadreviewIsNotNull() {
            addCriterion("BadReview is not null");
            return (Criteria) this;
        }

        public Criteria andBadreviewEqualTo(Integer value) {
            addCriterion("BadReview =", value, "badreview");
            return (Criteria) this;
        }

        public Criteria andBadreviewNotEqualTo(Integer value) {
            addCriterion("BadReview <>", value, "badreview");
            return (Criteria) this;
        }

        public Criteria andBadreviewGreaterThan(Integer value) {
            addCriterion("BadReview >", value, "badreview");
            return (Criteria) this;
        }

        public Criteria andBadreviewGreaterThanOrEqualTo(Integer value) {
            addCriterion("BadReview >=", value, "badreview");
            return (Criteria) this;
        }

        public Criteria andBadreviewLessThan(Integer value) {
            addCriterion("BadReview <", value, "badreview");
            return (Criteria) this;
        }

        public Criteria andBadreviewLessThanOrEqualTo(Integer value) {
            addCriterion("BadReview <=", value, "badreview");
            return (Criteria) this;
        }

        public Criteria andBadreviewIn(List<Integer> values) {
            addCriterion("BadReview in", values, "badreview");
            return (Criteria) this;
        }

        public Criteria andBadreviewNotIn(List<Integer> values) {
            addCriterion("BadReview not in", values, "badreview");
            return (Criteria) this;
        }

        public Criteria andBadreviewBetween(Integer value1, Integer value2) {
            addCriterion("BadReview between", value1, value2, "badreview");
            return (Criteria) this;
        }

        public Criteria andBadreviewNotBetween(Integer value1, Integer value2) {
            addCriterion("BadReview not between", value1, value2, "badreview");
            return (Criteria) this;
        }

        public Criteria andSalescountIsNull() {
            addCriterion("SalesCount is null");
            return (Criteria) this;
        }

        public Criteria andSalescountIsNotNull() {
            addCriterion("SalesCount is not null");
            return (Criteria) this;
        }

        public Criteria andSalescountEqualTo(Integer value) {
            addCriterion("SalesCount =", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountNotEqualTo(Integer value) {
            addCriterion("SalesCount <>", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountGreaterThan(Integer value) {
            addCriterion("SalesCount >", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountGreaterThanOrEqualTo(Integer value) {
            addCriterion("SalesCount >=", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountLessThan(Integer value) {
            addCriterion("SalesCount <", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountLessThanOrEqualTo(Integer value) {
            addCriterion("SalesCount <=", value, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountIn(List<Integer> values) {
            addCriterion("SalesCount in", values, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountNotIn(List<Integer> values) {
            addCriterion("SalesCount not in", values, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountBetween(Integer value1, Integer value2) {
            addCriterion("SalesCount between", value1, value2, "salescount");
            return (Criteria) this;
        }

        public Criteria andSalescountNotBetween(Integer value1, Integer value2) {
            addCriterion("SalesCount not between", value1, value2, "salescount");
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