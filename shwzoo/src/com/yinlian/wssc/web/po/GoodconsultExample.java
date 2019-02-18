package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodconsultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodconsultExample() {
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

        public Criteria andSelleridIsNull() {
            addCriterion("SellerID is null");
            return (Criteria) this;
        }

        public Criteria andSelleridIsNotNull() {
            addCriterion("SellerID is not null");
            return (Criteria) this;
        }

        public Criteria andSelleridEqualTo(Integer value) {
            addCriterion("SellerID =", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridNotEqualTo(Integer value) {
            addCriterion("SellerID <>", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridGreaterThan(Integer value) {
            addCriterion("SellerID >", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridGreaterThanOrEqualTo(Integer value) {
            addCriterion("SellerID >=", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridLessThan(Integer value) {
            addCriterion("SellerID <", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridLessThanOrEqualTo(Integer value) {
            addCriterion("SellerID <=", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridIn(List<Integer> values) {
            addCriterion("SellerID in", values, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridNotIn(List<Integer> values) {
            addCriterion("SellerID not in", values, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridBetween(Integer value1, Integer value2) {
            addCriterion("SellerID between", value1, value2, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridNotBetween(Integer value1, Integer value2) {
            addCriterion("SellerID not between", value1, value2, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSpuidIsNull() {
            addCriterion("SPUID is null");
            return (Criteria) this;
        }

        public Criteria andSpuidIsNotNull() {
            addCriterion("SPUID is not null");
            return (Criteria) this;
        }

        public Criteria andSpuidEqualTo(Integer value) {
            addCriterion("SPUID =", value, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidNotEqualTo(Integer value) {
            addCriterion("SPUID <>", value, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidGreaterThan(Integer value) {
            addCriterion("SPUID >", value, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidGreaterThanOrEqualTo(Integer value) {
            addCriterion("SPUID >=", value, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidLessThan(Integer value) {
            addCriterion("SPUID <", value, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidLessThanOrEqualTo(Integer value) {
            addCriterion("SPUID <=", value, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidIn(List<Integer> values) {
            addCriterion("SPUID in", values, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidNotIn(List<Integer> values) {
            addCriterion("SPUID not in", values, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidBetween(Integer value1, Integer value2) {
            addCriterion("SPUID between", value1, value2, "spuid");
            return (Criteria) this;
        }

        public Criteria andSpuidNotBetween(Integer value1, Integer value2) {
            addCriterion("SPUID not between", value1, value2, "spuid");
            return (Criteria) this;
        }

        public Criteria andConsultidIsNull() {
            addCriterion("ConsultID is null");
            return (Criteria) this;
        }

        public Criteria andConsultidIsNotNull() {
            addCriterion("ConsultID is not null");
            return (Criteria) this;
        }

        public Criteria andConsultidEqualTo(Integer value) {
            addCriterion("ConsultID =", value, "consultid");
            return (Criteria) this;
        }

        public Criteria andConsultidNotEqualTo(Integer value) {
            addCriterion("ConsultID <>", value, "consultid");
            return (Criteria) this;
        }

        public Criteria andConsultidGreaterThan(Integer value) {
            addCriterion("ConsultID >", value, "consultid");
            return (Criteria) this;
        }

        public Criteria andConsultidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ConsultID >=", value, "consultid");
            return (Criteria) this;
        }

        public Criteria andConsultidLessThan(Integer value) {
            addCriterion("ConsultID <", value, "consultid");
            return (Criteria) this;
        }

        public Criteria andConsultidLessThanOrEqualTo(Integer value) {
            addCriterion("ConsultID <=", value, "consultid");
            return (Criteria) this;
        }

        public Criteria andConsultidIn(List<Integer> values) {
            addCriterion("ConsultID in", values, "consultid");
            return (Criteria) this;
        }

        public Criteria andConsultidNotIn(List<Integer> values) {
            addCriterion("ConsultID not in", values, "consultid");
            return (Criteria) this;
        }

        public Criteria andConsultidBetween(Integer value1, Integer value2) {
            addCriterion("ConsultID between", value1, value2, "consultid");
            return (Criteria) this;
        }

        public Criteria andConsultidNotBetween(Integer value1, Integer value2) {
            addCriterion("ConsultID not between", value1, value2, "consultid");
            return (Criteria) this;
        }

        public Criteria andReplyidIsNull() {
            addCriterion("ReplyID is null");
            return (Criteria) this;
        }

        public Criteria andReplyidIsNotNull() {
            addCriterion("ReplyID is not null");
            return (Criteria) this;
        }

        public Criteria andReplyidEqualTo(Integer value) {
            addCriterion("ReplyID =", value, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidNotEqualTo(Integer value) {
            addCriterion("ReplyID <>", value, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidGreaterThan(Integer value) {
            addCriterion("ReplyID >", value, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ReplyID >=", value, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidLessThan(Integer value) {
            addCriterion("ReplyID <", value, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidLessThanOrEqualTo(Integer value) {
            addCriterion("ReplyID <=", value, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidIn(List<Integer> values) {
            addCriterion("ReplyID in", values, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidNotIn(List<Integer> values) {
            addCriterion("ReplyID not in", values, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidBetween(Integer value1, Integer value2) {
            addCriterion("ReplyID between", value1, value2, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidNotBetween(Integer value1, Integer value2) {
            addCriterion("ReplyID not between", value1, value2, "replyid");
            return (Criteria) this;
        }

        public Criteria andConsulttypeIsNull() {
            addCriterion("ConsultType is null");
            return (Criteria) this;
        }

        public Criteria andConsulttypeIsNotNull() {
            addCriterion("ConsultType is not null");
            return (Criteria) this;
        }

        public Criteria andConsulttypeEqualTo(Integer value) {
            addCriterion("ConsultType =", value, "consulttype");
            return (Criteria) this;
        }

        public Criteria andConsulttypeNotEqualTo(Integer value) {
            addCriterion("ConsultType <>", value, "consulttype");
            return (Criteria) this;
        }

        public Criteria andConsulttypeGreaterThan(Integer value) {
            addCriterion("ConsultType >", value, "consulttype");
            return (Criteria) this;
        }

        public Criteria andConsulttypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ConsultType >=", value, "consulttype");
            return (Criteria) this;
        }

        public Criteria andConsulttypeLessThan(Integer value) {
            addCriterion("ConsultType <", value, "consulttype");
            return (Criteria) this;
        }

        public Criteria andConsulttypeLessThanOrEqualTo(Integer value) {
            addCriterion("ConsultType <=", value, "consulttype");
            return (Criteria) this;
        }

        public Criteria andConsulttypeIn(List<Integer> values) {
            addCriterion("ConsultType in", values, "consulttype");
            return (Criteria) this;
        }

        public Criteria andConsulttypeNotIn(List<Integer> values) {
            addCriterion("ConsultType not in", values, "consulttype");
            return (Criteria) this;
        }

        public Criteria andConsulttypeBetween(Integer value1, Integer value2) {
            addCriterion("ConsultType between", value1, value2, "consulttype");
            return (Criteria) this;
        }

        public Criteria andConsulttypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ConsultType not between", value1, value2, "consulttype");
            return (Criteria) this;
        }

        public Criteria andConsultcontentIsNull() {
            addCriterion("ConsultContent is null");
            return (Criteria) this;
        }

        public Criteria andConsultcontentIsNotNull() {
            addCriterion("ConsultContent is not null");
            return (Criteria) this;
        }

        public Criteria andConsultcontentEqualTo(String value) {
            addCriterion("ConsultContent =", value, "consultcontent");
            return (Criteria) this;
        }

        public Criteria andConsultcontentNotEqualTo(String value) {
            addCriterion("ConsultContent <>", value, "consultcontent");
            return (Criteria) this;
        }

        public Criteria andConsultcontentGreaterThan(String value) {
            addCriterion("ConsultContent >", value, "consultcontent");
            return (Criteria) this;
        }

        public Criteria andConsultcontentGreaterThanOrEqualTo(String value) {
            addCriterion("ConsultContent >=", value, "consultcontent");
            return (Criteria) this;
        }

        public Criteria andConsultcontentLessThan(String value) {
            addCriterion("ConsultContent <", value, "consultcontent");
            return (Criteria) this;
        }

        public Criteria andConsultcontentLessThanOrEqualTo(String value) {
            addCriterion("ConsultContent <=", value, "consultcontent");
            return (Criteria) this;
        }

        public Criteria andConsultcontentLike(String value) {
            addCriterion("ConsultContent like", value, "consultcontent");
            return (Criteria) this;
        }

        public Criteria andConsultcontentNotLike(String value) {
            addCriterion("ConsultContent not like", value, "consultcontent");
            return (Criteria) this;
        }

        public Criteria andConsultcontentIn(List<String> values) {
            addCriterion("ConsultContent in", values, "consultcontent");
            return (Criteria) this;
        }

        public Criteria andConsultcontentNotIn(List<String> values) {
            addCriterion("ConsultContent not in", values, "consultcontent");
            return (Criteria) this;
        }

        public Criteria andConsultcontentBetween(String value1, String value2) {
            addCriterion("ConsultContent between", value1, value2, "consultcontent");
            return (Criteria) this;
        }

        public Criteria andConsultcontentNotBetween(String value1, String value2) {
            addCriterion("ConsultContent not between", value1, value2, "consultcontent");
            return (Criteria) this;
        }

        public Criteria andReplycontentIsNull() {
            addCriterion("ReplyContent is null");
            return (Criteria) this;
        }

        public Criteria andReplycontentIsNotNull() {
            addCriterion("ReplyContent is not null");
            return (Criteria) this;
        }

        public Criteria andReplycontentEqualTo(String value) {
            addCriterion("ReplyContent =", value, "replycontent");
            return (Criteria) this;
        }

        public Criteria andReplycontentNotEqualTo(String value) {
            addCriterion("ReplyContent <>", value, "replycontent");
            return (Criteria) this;
        }

        public Criteria andReplycontentGreaterThan(String value) {
            addCriterion("ReplyContent >", value, "replycontent");
            return (Criteria) this;
        }

        public Criteria andReplycontentGreaterThanOrEqualTo(String value) {
            addCriterion("ReplyContent >=", value, "replycontent");
            return (Criteria) this;
        }

        public Criteria andReplycontentLessThan(String value) {
            addCriterion("ReplyContent <", value, "replycontent");
            return (Criteria) this;
        }

        public Criteria andReplycontentLessThanOrEqualTo(String value) {
            addCriterion("ReplyContent <=", value, "replycontent");
            return (Criteria) this;
        }

        public Criteria andReplycontentLike(String value) {
            addCriterion("ReplyContent like", value, "replycontent");
            return (Criteria) this;
        }

        public Criteria andReplycontentNotLike(String value) {
            addCriterion("ReplyContent not like", value, "replycontent");
            return (Criteria) this;
        }

        public Criteria andReplycontentIn(List<String> values) {
            addCriterion("ReplyContent in", values, "replycontent");
            return (Criteria) this;
        }

        public Criteria andReplycontentNotIn(List<String> values) {
            addCriterion("ReplyContent not in", values, "replycontent");
            return (Criteria) this;
        }

        public Criteria andReplycontentBetween(String value1, String value2) {
            addCriterion("ReplyContent between", value1, value2, "replycontent");
            return (Criteria) this;
        }

        public Criteria andReplycontentNotBetween(String value1, String value2) {
            addCriterion("ReplyContent not between", value1, value2, "replycontent");
            return (Criteria) this;
        }

        public Criteria andConsultdateIsNull() {
            addCriterion("ConsultDate is null");
            return (Criteria) this;
        }

        public Criteria andConsultdateIsNotNull() {
            addCriterion("ConsultDate is not null");
            return (Criteria) this;
        }

        public Criteria andConsultdateEqualTo(Date value) {
            addCriterion("ConsultDate =", value, "consultdate");
            return (Criteria) this;
        }

        public Criteria andConsultdateNotEqualTo(Date value) {
            addCriterion("ConsultDate <>", value, "consultdate");
            return (Criteria) this;
        }

        public Criteria andConsultdateGreaterThan(Date value) {
            addCriterion("ConsultDate >", value, "consultdate");
            return (Criteria) this;
        }

        public Criteria andConsultdateGreaterThanOrEqualTo(Date value) {
            addCriterion("ConsultDate >=", value, "consultdate");
            return (Criteria) this;
        }

        public Criteria andConsultdateLessThan(Date value) {
            addCriterion("ConsultDate <", value, "consultdate");
            return (Criteria) this;
        }

        public Criteria andConsultdateLessThanOrEqualTo(Date value) {
            addCriterion("ConsultDate <=", value, "consultdate");
            return (Criteria) this;
        }

        public Criteria andConsultdateIn(List<Date> values) {
            addCriterion("ConsultDate in", values, "consultdate");
            return (Criteria) this;
        }

        public Criteria andConsultdateNotIn(List<Date> values) {
            addCriterion("ConsultDate not in", values, "consultdate");
            return (Criteria) this;
        }

        public Criteria andConsultdateBetween(Date value1, Date value2) {
            addCriterion("ConsultDate between", value1, value2, "consultdate");
            return (Criteria) this;
        }

        public Criteria andConsultdateNotBetween(Date value1, Date value2) {
            addCriterion("ConsultDate not between", value1, value2, "consultdate");
            return (Criteria) this;
        }

        public Criteria andReplydateIsNull() {
            addCriterion("ReplyDate is null");
            return (Criteria) this;
        }

        public Criteria andReplydateIsNotNull() {
            addCriterion("ReplyDate is not null");
            return (Criteria) this;
        }

        public Criteria andReplydateEqualTo(Date value) {
            addCriterion("ReplyDate =", value, "replydate");
            return (Criteria) this;
        }

        public Criteria andReplydateNotEqualTo(Date value) {
            addCriterion("ReplyDate <>", value, "replydate");
            return (Criteria) this;
        }

        public Criteria andReplydateGreaterThan(Date value) {
            addCriterion("ReplyDate >", value, "replydate");
            return (Criteria) this;
        }

        public Criteria andReplydateGreaterThanOrEqualTo(Date value) {
            addCriterion("ReplyDate >=", value, "replydate");
            return (Criteria) this;
        }

        public Criteria andReplydateLessThan(Date value) {
            addCriterion("ReplyDate <", value, "replydate");
            return (Criteria) this;
        }

        public Criteria andReplydateLessThanOrEqualTo(Date value) {
            addCriterion("ReplyDate <=", value, "replydate");
            return (Criteria) this;
        }

        public Criteria andReplydateIn(List<Date> values) {
            addCriterion("ReplyDate in", values, "replydate");
            return (Criteria) this;
        }

        public Criteria andReplydateNotIn(List<Date> values) {
            addCriterion("ReplyDate not in", values, "replydate");
            return (Criteria) this;
        }

        public Criteria andReplydateBetween(Date value1, Date value2) {
            addCriterion("ReplyDate between", value1, value2, "replydate");
            return (Criteria) this;
        }

        public Criteria andReplydateNotBetween(Date value1, Date value2) {
            addCriterion("ReplyDate not between", value1, value2, "replydate");
            return (Criteria) this;
        }

        public Criteria andVaildflagIsNull() {
            addCriterion("VaildFlag is null");
            return (Criteria) this;
        }

        public Criteria andVaildflagIsNotNull() {
            addCriterion("VaildFlag is not null");
            return (Criteria) this;
        }

        public Criteria andVaildflagEqualTo(Integer value) {
            addCriterion("VaildFlag =", value, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagNotEqualTo(Integer value) {
            addCriterion("VaildFlag <>", value, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagGreaterThan(Integer value) {
            addCriterion("VaildFlag >", value, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("VaildFlag >=", value, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagLessThan(Integer value) {
            addCriterion("VaildFlag <", value, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagLessThanOrEqualTo(Integer value) {
            addCriterion("VaildFlag <=", value, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagIn(List<Integer> values) {
            addCriterion("VaildFlag in", values, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagNotIn(List<Integer> values) {
            addCriterion("VaildFlag not in", values, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagBetween(Integer value1, Integer value2) {
            addCriterion("VaildFlag between", value1, value2, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andVaildflagNotBetween(Integer value1, Integer value2) {
            addCriterion("VaildFlag not between", value1, value2, "vaildflag");
            return (Criteria) this;
        }

        public Criteria andDeldateIsNull() {
            addCriterion("DelDate is null");
            return (Criteria) this;
        }

        public Criteria andDeldateIsNotNull() {
            addCriterion("DelDate is not null");
            return (Criteria) this;
        }

        public Criteria andDeldateEqualTo(Date value) {
            addCriterion("DelDate =", value, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateNotEqualTo(Date value) {
            addCriterion("DelDate <>", value, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateGreaterThan(Date value) {
            addCriterion("DelDate >", value, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateGreaterThanOrEqualTo(Date value) {
            addCriterion("DelDate >=", value, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateLessThan(Date value) {
            addCriterion("DelDate <", value, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateLessThanOrEqualTo(Date value) {
            addCriterion("DelDate <=", value, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateIn(List<Date> values) {
            addCriterion("DelDate in", values, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateNotIn(List<Date> values) {
            addCriterion("DelDate not in", values, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateBetween(Date value1, Date value2) {
            addCriterion("DelDate between", value1, value2, "deldate");
            return (Criteria) this;
        }

        public Criteria andDeldateNotBetween(Date value1, Date value2) {
            addCriterion("DelDate not between", value1, value2, "deldate");
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