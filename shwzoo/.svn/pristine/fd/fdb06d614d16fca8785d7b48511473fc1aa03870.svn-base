package com.yinlian.wssc.web.po;

import java.util.ArrayList;
import java.util.List;

public class UsersExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UsersExample() {
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

        public Criteria andUsernameIsNull() {
            addCriterion("UserName is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("UserName is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("UserName =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("UserName <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("UserName >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("UserName >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("UserName <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("UserName <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("UserName like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("UserName not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("UserName in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("UserName not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("UserName between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("UserName not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("Password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("Password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("Password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("Password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("Password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("Password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("Password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("Password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("Password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("Password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("Password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("Password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("Password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("Password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("NickName is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("NickName is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("NickName =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("NickName <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("NickName >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("NickName >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("NickName <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("NickName <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("NickName like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("NickName not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("NickName in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("NickName not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("NickName between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("NickName not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNull() {
            addCriterion("RealName is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("RealName is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("RealName =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("RealName <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("RealName >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("RealName >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("RealName <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("RealName <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("RealName like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("RealName not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("RealName in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("RealName not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("RealName between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("RealName not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("Mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("Mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("Mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("Mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("Mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("Mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("Mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("Mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("Mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("Mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("Mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("Mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("Mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("Mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("Email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("Email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("Email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("Email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("Email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("Email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("Email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("Email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("Email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("Email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("Email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("Email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("Email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("Email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andIsmobilecheckIsNull() {
            addCriterion("IsMobileCheck is null");
            return (Criteria) this;
        }

        public Criteria andIsmobilecheckIsNotNull() {
            addCriterion("IsMobileCheck is not null");
            return (Criteria) this;
        }

        public Criteria andIsmobilecheckEqualTo(Boolean value) {
            addCriterion("IsMobileCheck =", value, "ismobilecheck");
            return (Criteria) this;
        }

        public Criteria andIsmobilecheckNotEqualTo(Boolean value) {
            addCriterion("IsMobileCheck <>", value, "ismobilecheck");
            return (Criteria) this;
        }

        public Criteria andIsmobilecheckGreaterThan(Boolean value) {
            addCriterion("IsMobileCheck >", value, "ismobilecheck");
            return (Criteria) this;
        }

        public Criteria andIsmobilecheckGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsMobileCheck >=", value, "ismobilecheck");
            return (Criteria) this;
        }

        public Criteria andIsmobilecheckLessThan(Boolean value) {
            addCriterion("IsMobileCheck <", value, "ismobilecheck");
            return (Criteria) this;
        }

        public Criteria andIsmobilecheckLessThanOrEqualTo(Boolean value) {
            addCriterion("IsMobileCheck <=", value, "ismobilecheck");
            return (Criteria) this;
        }

        public Criteria andIsmobilecheckIn(List<Boolean> values) {
            addCriterion("IsMobileCheck in", values, "ismobilecheck");
            return (Criteria) this;
        }

        public Criteria andIsmobilecheckNotIn(List<Boolean> values) {
            addCriterion("IsMobileCheck not in", values, "ismobilecheck");
            return (Criteria) this;
        }

        public Criteria andIsmobilecheckBetween(Boolean value1, Boolean value2) {
            addCriterion("IsMobileCheck between", value1, value2, "ismobilecheck");
            return (Criteria) this;
        }

        public Criteria andIsmobilecheckNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsMobileCheck not between", value1, value2, "ismobilecheck");
            return (Criteria) this;
        }

        public Criteria andIsemailcheckIsNull() {
            addCriterion("IsEmailCheck is null");
            return (Criteria) this;
        }

        public Criteria andIsemailcheckIsNotNull() {
            addCriterion("IsEmailCheck is not null");
            return (Criteria) this;
        }

        public Criteria andIsemailcheckEqualTo(Boolean value) {
            addCriterion("IsEmailCheck =", value, "isemailcheck");
            return (Criteria) this;
        }

        public Criteria andIsemailcheckNotEqualTo(Boolean value) {
            addCriterion("IsEmailCheck <>", value, "isemailcheck");
            return (Criteria) this;
        }

        public Criteria andIsemailcheckGreaterThan(Boolean value) {
            addCriterion("IsEmailCheck >", value, "isemailcheck");
            return (Criteria) this;
        }

        public Criteria andIsemailcheckGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsEmailCheck >=", value, "isemailcheck");
            return (Criteria) this;
        }

        public Criteria andIsemailcheckLessThan(Boolean value) {
            addCriterion("IsEmailCheck <", value, "isemailcheck");
            return (Criteria) this;
        }

        public Criteria andIsemailcheckLessThanOrEqualTo(Boolean value) {
            addCriterion("IsEmailCheck <=", value, "isemailcheck");
            return (Criteria) this;
        }

        public Criteria andIsemailcheckIn(List<Boolean> values) {
            addCriterion("IsEmailCheck in", values, "isemailcheck");
            return (Criteria) this;
        }

        public Criteria andIsemailcheckNotIn(List<Boolean> values) {
            addCriterion("IsEmailCheck not in", values, "isemailcheck");
            return (Criteria) this;
        }

        public Criteria andIsemailcheckBetween(Boolean value1, Boolean value2) {
            addCriterion("IsEmailCheck between", value1, value2, "isemailcheck");
            return (Criteria) this;
        }

        public Criteria andIsemailcheckNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsEmailCheck not between", value1, value2, "isemailcheck");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeIsNull() {
            addCriterion("IDCardType is null");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeIsNotNull() {
            addCriterion("IDCardType is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeEqualTo(Integer value) {
            addCriterion("IDCardType =", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeNotEqualTo(Integer value) {
            addCriterion("IDCardType <>", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeGreaterThan(Integer value) {
            addCriterion("IDCardType >", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("IDCardType >=", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeLessThan(Integer value) {
            addCriterion("IDCardType <", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeLessThanOrEqualTo(Integer value) {
            addCriterion("IDCardType <=", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeIn(List<Integer> values) {
            addCriterion("IDCardType in", values, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeNotIn(List<Integer> values) {
            addCriterion("IDCardType not in", values, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeBetween(Integer value1, Integer value2) {
            addCriterion("IDCardType between", value1, value2, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("IDCardType not between", value1, value2, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNull() {
            addCriterion("IDCard is null");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNotNull() {
            addCriterion("IDCard is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardEqualTo(String value) {
            addCriterion("IDCard =", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotEqualTo(String value) {
            addCriterion("IDCard <>", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThan(String value) {
            addCriterion("IDCard >", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("IDCard >=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThan(String value) {
            addCriterion("IDCard <", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThanOrEqualTo(String value) {
            addCriterion("IDCard <=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLike(String value) {
            addCriterion("IDCard like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotLike(String value) {
            addCriterion("IDCard not like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardIn(List<String> values) {
            addCriterion("IDCard in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotIn(List<String> values) {
            addCriterion("IDCard not in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardBetween(String value1, String value2) {
            addCriterion("IDCard between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotBetween(String value1, String value2) {
            addCriterion("IDCard not between", value1, value2, "idcard");
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

        public Criteria andLevelidIsNull() {
            addCriterion("LevelID is null");
            return (Criteria) this;
        }

        public Criteria andLevelidIsNotNull() {
            addCriterion("LevelID is not null");
            return (Criteria) this;
        }

        public Criteria andLevelidEqualTo(Integer value) {
            addCriterion("LevelID =", value, "levelid");
            return (Criteria) this;
        }

        public Criteria andLevelidNotEqualTo(Integer value) {
            addCriterion("LevelID <>", value, "levelid");
            return (Criteria) this;
        }

        public Criteria andLevelidGreaterThan(Integer value) {
            addCriterion("LevelID >", value, "levelid");
            return (Criteria) this;
        }

        public Criteria andLevelidGreaterThanOrEqualTo(Integer value) {
            addCriterion("LevelID >=", value, "levelid");
            return (Criteria) this;
        }

        public Criteria andLevelidLessThan(Integer value) {
            addCriterion("LevelID <", value, "levelid");
            return (Criteria) this;
        }

        public Criteria andLevelidLessThanOrEqualTo(Integer value) {
            addCriterion("LevelID <=", value, "levelid");
            return (Criteria) this;
        }

        public Criteria andLevelidIn(List<Integer> values) {
            addCriterion("LevelID in", values, "levelid");
            return (Criteria) this;
        }

        public Criteria andLevelidNotIn(List<Integer> values) {
            addCriterion("LevelID not in", values, "levelid");
            return (Criteria) this;
        }

        public Criteria andLevelidBetween(Integer value1, Integer value2) {
            addCriterion("LevelID between", value1, value2, "levelid");
            return (Criteria) this;
        }

        public Criteria andLevelidNotBetween(Integer value1, Integer value2) {
            addCriterion("LevelID not between", value1, value2, "levelid");
            return (Criteria) this;
        }

        public Criteria andPointsIsNull() {
            addCriterion("Points is null");
            return (Criteria) this;
        }

        public Criteria andPointsIsNotNull() {
            addCriterion("Points is not null");
            return (Criteria) this;
        }

        public Criteria andPointsEqualTo(Integer value) {
            addCriterion("Points =", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotEqualTo(Integer value) {
            addCriterion("Points <>", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsGreaterThan(Integer value) {
            addCriterion("Points >", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsGreaterThanOrEqualTo(Integer value) {
            addCriterion("Points >=", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsLessThan(Integer value) {
            addCriterion("Points <", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsLessThanOrEqualTo(Integer value) {
            addCriterion("Points <=", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsIn(List<Integer> values) {
            addCriterion("Points in", values, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotIn(List<Integer> values) {
            addCriterion("Points not in", values, "points");
            return (Criteria) this;
        }

        public Criteria andPointsBetween(Integer value1, Integer value2) {
            addCriterion("Points between", value1, value2, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotBetween(Integer value1, Integer value2) {
            addCriterion("Points not between", value1, value2, "points");
            return (Criteria) this;
        }

        public Criteria andTotalpointsIsNull() {
            addCriterion("TotalPoints is null");
            return (Criteria) this;
        }

        public Criteria andTotalpointsIsNotNull() {
            addCriterion("TotalPoints is not null");
            return (Criteria) this;
        }

        public Criteria andTotalpointsEqualTo(Integer value) {
            addCriterion("TotalPoints =", value, "totalpoints");
            return (Criteria) this;
        }

        public Criteria andTotalpointsNotEqualTo(Integer value) {
            addCriterion("TotalPoints <>", value, "totalpoints");
            return (Criteria) this;
        }

        public Criteria andTotalpointsGreaterThan(Integer value) {
            addCriterion("TotalPoints >", value, "totalpoints");
            return (Criteria) this;
        }

        public Criteria andTotalpointsGreaterThanOrEqualTo(Integer value) {
            addCriterion("TotalPoints >=", value, "totalpoints");
            return (Criteria) this;
        }

        public Criteria andTotalpointsLessThan(Integer value) {
            addCriterion("TotalPoints <", value, "totalpoints");
            return (Criteria) this;
        }

        public Criteria andTotalpointsLessThanOrEqualTo(Integer value) {
            addCriterion("TotalPoints <=", value, "totalpoints");
            return (Criteria) this;
        }

        public Criteria andTotalpointsIn(List<Integer> values) {
            addCriterion("TotalPoints in", values, "totalpoints");
            return (Criteria) this;
        }

        public Criteria andTotalpointsNotIn(List<Integer> values) {
            addCriterion("TotalPoints not in", values, "totalpoints");
            return (Criteria) this;
        }

        public Criteria andTotalpointsBetween(Integer value1, Integer value2) {
            addCriterion("TotalPoints between", value1, value2, "totalpoints");
            return (Criteria) this;
        }

        public Criteria andTotalpointsNotBetween(Integer value1, Integer value2) {
            addCriterion("TotalPoints not between", value1, value2, "totalpoints");
            return (Criteria) this;
        }

        public Criteria andPaypassIsNull() {
            addCriterion("PayPass is null");
            return (Criteria) this;
        }

        public Criteria andPaypassIsNotNull() {
            addCriterion("PayPass is not null");
            return (Criteria) this;
        }

        public Criteria andPaypassEqualTo(String value) {
            addCriterion("PayPass =", value, "paypass");
            return (Criteria) this;
        }

        public Criteria andPaypassNotEqualTo(String value) {
            addCriterion("PayPass <>", value, "paypass");
            return (Criteria) this;
        }

        public Criteria andPaypassGreaterThan(String value) {
            addCriterion("PayPass >", value, "paypass");
            return (Criteria) this;
        }

        public Criteria andPaypassGreaterThanOrEqualTo(String value) {
            addCriterion("PayPass >=", value, "paypass");
            return (Criteria) this;
        }

        public Criteria andPaypassLessThan(String value) {
            addCriterion("PayPass <", value, "paypass");
            return (Criteria) this;
        }

        public Criteria andPaypassLessThanOrEqualTo(String value) {
            addCriterion("PayPass <=", value, "paypass");
            return (Criteria) this;
        }

        public Criteria andPaypassLike(String value) {
            addCriterion("PayPass like", value, "paypass");
            return (Criteria) this;
        }

        public Criteria andPaypassNotLike(String value) {
            addCriterion("PayPass not like", value, "paypass");
            return (Criteria) this;
        }

        public Criteria andPaypassIn(List<String> values) {
            addCriterion("PayPass in", values, "paypass");
            return (Criteria) this;
        }

        public Criteria andPaypassNotIn(List<String> values) {
            addCriterion("PayPass not in", values, "paypass");
            return (Criteria) this;
        }

        public Criteria andPaypassBetween(String value1, String value2) {
            addCriterion("PayPass between", value1, value2, "paypass");
            return (Criteria) this;
        }

        public Criteria andPaypassNotBetween(String value1, String value2) {
            addCriterion("PayPass not between", value1, value2, "paypass");
            return (Criteria) this;
        }

        public Criteria andPaypassstatusIsNull() {
            addCriterion("PayPassStatus is null");
            return (Criteria) this;
        }

        public Criteria andPaypassstatusIsNotNull() {
            addCriterion("PayPassStatus is not null");
            return (Criteria) this;
        }

        public Criteria andPaypassstatusEqualTo(Integer value) {
            addCriterion("PayPassStatus =", value, "paypassstatus");
            return (Criteria) this;
        }

        public Criteria andPaypassstatusNotEqualTo(Integer value) {
            addCriterion("PayPassStatus <>", value, "paypassstatus");
            return (Criteria) this;
        }

        public Criteria andPaypassstatusGreaterThan(Integer value) {
            addCriterion("PayPassStatus >", value, "paypassstatus");
            return (Criteria) this;
        }

        public Criteria andPaypassstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("PayPassStatus >=", value, "paypassstatus");
            return (Criteria) this;
        }

        public Criteria andPaypassstatusLessThan(Integer value) {
            addCriterion("PayPassStatus <", value, "paypassstatus");
            return (Criteria) this;
        }

        public Criteria andPaypassstatusLessThanOrEqualTo(Integer value) {
            addCriterion("PayPassStatus <=", value, "paypassstatus");
            return (Criteria) this;
        }

        public Criteria andPaypassstatusIn(List<Integer> values) {
            addCriterion("PayPassStatus in", values, "paypassstatus");
            return (Criteria) this;
        }

        public Criteria andPaypassstatusNotIn(List<Integer> values) {
            addCriterion("PayPassStatus not in", values, "paypassstatus");
            return (Criteria) this;
        }

        public Criteria andPaypassstatusBetween(Integer value1, Integer value2) {
            addCriterion("PayPassStatus between", value1, value2, "paypassstatus");
            return (Criteria) this;
        }

        public Criteria andPaypassstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("PayPassStatus not between", value1, value2, "paypassstatus");
            return (Criteria) this;
        }

        public Criteria andTotalbeansIsNull() {
            addCriterion("TotalBeans is null");
            return (Criteria) this;
        }

        public Criteria andTotalbeansIsNotNull() {
            addCriterion("TotalBeans is not null");
            return (Criteria) this;
        }

        public Criteria andTotalbeansEqualTo(Integer value) {
            addCriterion("TotalBeans =", value, "totalbeans");
            return (Criteria) this;
        }

        public Criteria andTotalbeansNotEqualTo(Integer value) {
            addCriterion("TotalBeans <>", value, "totalbeans");
            return (Criteria) this;
        }

        public Criteria andTotalbeansGreaterThan(Integer value) {
            addCriterion("TotalBeans >", value, "totalbeans");
            return (Criteria) this;
        }

        public Criteria andTotalbeansGreaterThanOrEqualTo(Integer value) {
            addCriterion("TotalBeans >=", value, "totalbeans");
            return (Criteria) this;
        }

        public Criteria andTotalbeansLessThan(Integer value) {
            addCriterion("TotalBeans <", value, "totalbeans");
            return (Criteria) this;
        }

        public Criteria andTotalbeansLessThanOrEqualTo(Integer value) {
            addCriterion("TotalBeans <=", value, "totalbeans");
            return (Criteria) this;
        }

        public Criteria andTotalbeansIn(List<Integer> values) {
            addCriterion("TotalBeans in", values, "totalbeans");
            return (Criteria) this;
        }

        public Criteria andTotalbeansNotIn(List<Integer> values) {
            addCriterion("TotalBeans not in", values, "totalbeans");
            return (Criteria) this;
        }

        public Criteria andTotalbeansBetween(Integer value1, Integer value2) {
            addCriterion("TotalBeans between", value1, value2, "totalbeans");
            return (Criteria) this;
        }

        public Criteria andTotalbeansNotBetween(Integer value1, Integer value2) {
            addCriterion("TotalBeans not between", value1, value2, "totalbeans");
            return (Criteria) this;
        }

        public Criteria andAvailablebeansIsNull() {
            addCriterion("AvailableBeans is null");
            return (Criteria) this;
        }

        public Criteria andAvailablebeansIsNotNull() {
            addCriterion("AvailableBeans is not null");
            return (Criteria) this;
        }

        public Criteria andAvailablebeansEqualTo(Integer value) {
            addCriterion("AvailableBeans =", value, "availablebeans");
            return (Criteria) this;
        }

        public Criteria andAvailablebeansNotEqualTo(Integer value) {
            addCriterion("AvailableBeans <>", value, "availablebeans");
            return (Criteria) this;
        }

        public Criteria andAvailablebeansGreaterThan(Integer value) {
            addCriterion("AvailableBeans >", value, "availablebeans");
            return (Criteria) this;
        }

        public Criteria andAvailablebeansGreaterThanOrEqualTo(Integer value) {
            addCriterion("AvailableBeans >=", value, "availablebeans");
            return (Criteria) this;
        }

        public Criteria andAvailablebeansLessThan(Integer value) {
            addCriterion("AvailableBeans <", value, "availablebeans");
            return (Criteria) this;
        }

        public Criteria andAvailablebeansLessThanOrEqualTo(Integer value) {
            addCriterion("AvailableBeans <=", value, "availablebeans");
            return (Criteria) this;
        }

        public Criteria andAvailablebeansIn(List<Integer> values) {
            addCriterion("AvailableBeans in", values, "availablebeans");
            return (Criteria) this;
        }

        public Criteria andAvailablebeansNotIn(List<Integer> values) {
            addCriterion("AvailableBeans not in", values, "availablebeans");
            return (Criteria) this;
        }

        public Criteria andAvailablebeansBetween(Integer value1, Integer value2) {
            addCriterion("AvailableBeans between", value1, value2, "availablebeans");
            return (Criteria) this;
        }

        public Criteria andAvailablebeansNotBetween(Integer value1, Integer value2) {
            addCriterion("AvailableBeans not between", value1, value2, "availablebeans");
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