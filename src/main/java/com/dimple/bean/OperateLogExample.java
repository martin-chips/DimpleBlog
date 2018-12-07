package com.dimple.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperateLogExample {
    /**
     * operate_log
     */
    protected String orderByClause;

    /**
     * operate_log
     */
    protected boolean distinct;

    /**
     * operate_log
     */
    protected List<Criteria> oredCriteria;

    public OperateLogExample() {
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

    /**
     * operate_log 2018-12-06
     */
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

        public Criteria andOperateIdIsNull() {
            addCriterion("operate_id is null");
            return (Criteria) this;
        }

        public Criteria andOperateIdIsNotNull() {
            addCriterion("operate_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperateIdEqualTo(Integer value) {
            addCriterion("operate_id =", value, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdNotEqualTo(Integer value) {
            addCriterion("operate_id <>", value, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdGreaterThan(Integer value) {
            addCriterion("operate_id >", value, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("operate_id >=", value, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdLessThan(Integer value) {
            addCriterion("operate_id <", value, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdLessThanOrEqualTo(Integer value) {
            addCriterion("operate_id <=", value, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdIn(List<Integer> values) {
            addCriterion("operate_id in", values, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdNotIn(List<Integer> values) {
            addCriterion("operate_id not in", values, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdBetween(Integer value1, Integer value2) {
            addCriterion("operate_id between", value1, value2, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("operate_id not between", value1, value2, "operateId");
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

        public Criteria andOperateTypeIsNull() {
            addCriterion("operate_type is null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNotNull() {
            addCriterion("operate_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeEqualTo(Integer value) {
            addCriterion("operate_type =", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotEqualTo(Integer value) {
            addCriterion("operate_type <>", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThan(Integer value) {
            addCriterion("operate_type >", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("operate_type >=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThan(Integer value) {
            addCriterion("operate_type <", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThanOrEqualTo(Integer value) {
            addCriterion("operate_type <=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIn(List<Integer> values) {
            addCriterion("operate_type in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotIn(List<Integer> values) {
            addCriterion("operate_type not in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeBetween(Integer value1, Integer value2) {
            addCriterion("operate_type between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("operate_type not between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andMethodIsNull() {
            addCriterion("method is null");
            return (Criteria) this;
        }

        public Criteria andMethodIsNotNull() {
            addCriterion("method is not null");
            return (Criteria) this;
        }

        public Criteria andMethodEqualTo(String value) {
            addCriterion("method =", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotEqualTo(String value) {
            addCriterion("method <>", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThan(String value) {
            addCriterion("method >", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThanOrEqualTo(String value) {
            addCriterion("method >=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThan(String value) {
            addCriterion("method <", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThanOrEqualTo(String value) {
            addCriterion("method <=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLike(String value) {
            addCriterion("method like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotLike(String value) {
            addCriterion("method not like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodIn(List<String> values) {
            addCriterion("method in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotIn(List<String> values) {
            addCriterion("method not in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodBetween(String value1, String value2) {
            addCriterion("method between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotBetween(String value1, String value2) {
            addCriterion("method not between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andOperateUrlIsNull() {
            addCriterion("operate_url is null");
            return (Criteria) this;
        }

        public Criteria andOperateUrlIsNotNull() {
            addCriterion("operate_url is not null");
            return (Criteria) this;
        }

        public Criteria andOperateUrlEqualTo(String value) {
            addCriterion("operate_url =", value, "operateUrl");
            return (Criteria) this;
        }

        public Criteria andOperateUrlNotEqualTo(String value) {
            addCriterion("operate_url <>", value, "operateUrl");
            return (Criteria) this;
        }

        public Criteria andOperateUrlGreaterThan(String value) {
            addCriterion("operate_url >", value, "operateUrl");
            return (Criteria) this;
        }

        public Criteria andOperateUrlGreaterThanOrEqualTo(String value) {
            addCriterion("operate_url >=", value, "operateUrl");
            return (Criteria) this;
        }

        public Criteria andOperateUrlLessThan(String value) {
            addCriterion("operate_url <", value, "operateUrl");
            return (Criteria) this;
        }

        public Criteria andOperateUrlLessThanOrEqualTo(String value) {
            addCriterion("operate_url <=", value, "operateUrl");
            return (Criteria) this;
        }

        public Criteria andOperateUrlLike(String value) {
            addCriterion("operate_url like", value, "operateUrl");
            return (Criteria) this;
        }

        public Criteria andOperateUrlNotLike(String value) {
            addCriterion("operate_url not like", value, "operateUrl");
            return (Criteria) this;
        }

        public Criteria andOperateUrlIn(List<String> values) {
            addCriterion("operate_url in", values, "operateUrl");
            return (Criteria) this;
        }

        public Criteria andOperateUrlNotIn(List<String> values) {
            addCriterion("operate_url not in", values, "operateUrl");
            return (Criteria) this;
        }

        public Criteria andOperateUrlBetween(String value1, String value2) {
            addCriterion("operate_url between", value1, value2, "operateUrl");
            return (Criteria) this;
        }

        public Criteria andOperateUrlNotBetween(String value1, String value2) {
            addCriterion("operate_url not between", value1, value2, "operateUrl");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIsNull() {
            addCriterion("operator_name is null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIsNotNull() {
            addCriterion("operator_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameEqualTo(String value) {
            addCriterion("operator_name =", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotEqualTo(String value) {
            addCriterion("operator_name <>", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThan(String value) {
            addCriterion("operator_name >", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("operator_name >=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThan(String value) {
            addCriterion("operator_name <", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThanOrEqualTo(String value) {
            addCriterion("operator_name <=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLike(String value) {
            addCriterion("operator_name like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotLike(String value) {
            addCriterion("operator_name not like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIn(List<String> values) {
            addCriterion("operator_name in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotIn(List<String> values) {
            addCriterion("operator_name not in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameBetween(String value1, String value2) {
            addCriterion("operator_name between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotBetween(String value1, String value2) {
            addCriterion("operator_name not between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperateIpIsNull() {
            addCriterion("operate_ip is null");
            return (Criteria) this;
        }

        public Criteria andOperateIpIsNotNull() {
            addCriterion("operate_ip is not null");
            return (Criteria) this;
        }

        public Criteria andOperateIpEqualTo(String value) {
            addCriterion("operate_ip =", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpNotEqualTo(String value) {
            addCriterion("operate_ip <>", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpGreaterThan(String value) {
            addCriterion("operate_ip >", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpGreaterThanOrEqualTo(String value) {
            addCriterion("operate_ip >=", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpLessThan(String value) {
            addCriterion("operate_ip <", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpLessThanOrEqualTo(String value) {
            addCriterion("operate_ip <=", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpLike(String value) {
            addCriterion("operate_ip like", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpNotLike(String value) {
            addCriterion("operate_ip not like", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpIn(List<String> values) {
            addCriterion("operate_ip in", values, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpNotIn(List<String> values) {
            addCriterion("operate_ip not in", values, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpBetween(String value1, String value2) {
            addCriterion("operate_ip between", value1, value2, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpNotBetween(String value1, String value2) {
            addCriterion("operate_ip not between", value1, value2, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateLocationIsNull() {
            addCriterion("operate_location is null");
            return (Criteria) this;
        }

        public Criteria andOperateLocationIsNotNull() {
            addCriterion("operate_location is not null");
            return (Criteria) this;
        }

        public Criteria andOperateLocationEqualTo(String value) {
            addCriterion("operate_location =", value, "operateLocation");
            return (Criteria) this;
        }

        public Criteria andOperateLocationNotEqualTo(String value) {
            addCriterion("operate_location <>", value, "operateLocation");
            return (Criteria) this;
        }

        public Criteria andOperateLocationGreaterThan(String value) {
            addCriterion("operate_location >", value, "operateLocation");
            return (Criteria) this;
        }

        public Criteria andOperateLocationGreaterThanOrEqualTo(String value) {
            addCriterion("operate_location >=", value, "operateLocation");
            return (Criteria) this;
        }

        public Criteria andOperateLocationLessThan(String value) {
            addCriterion("operate_location <", value, "operateLocation");
            return (Criteria) this;
        }

        public Criteria andOperateLocationLessThanOrEqualTo(String value) {
            addCriterion("operate_location <=", value, "operateLocation");
            return (Criteria) this;
        }

        public Criteria andOperateLocationLike(String value) {
            addCriterion("operate_location like", value, "operateLocation");
            return (Criteria) this;
        }

        public Criteria andOperateLocationNotLike(String value) {
            addCriterion("operate_location not like", value, "operateLocation");
            return (Criteria) this;
        }

        public Criteria andOperateLocationIn(List<String> values) {
            addCriterion("operate_location in", values, "operateLocation");
            return (Criteria) this;
        }

        public Criteria andOperateLocationNotIn(List<String> values) {
            addCriterion("operate_location not in", values, "operateLocation");
            return (Criteria) this;
        }

        public Criteria andOperateLocationBetween(String value1, String value2) {
            addCriterion("operate_location between", value1, value2, "operateLocation");
            return (Criteria) this;
        }

        public Criteria andOperateLocationNotBetween(String value1, String value2) {
            addCriterion("operate_location not between", value1, value2, "operateLocation");
            return (Criteria) this;
        }

        public Criteria andOperateParamIsNull() {
            addCriterion("operate_param is null");
            return (Criteria) this;
        }

        public Criteria andOperateParamIsNotNull() {
            addCriterion("operate_param is not null");
            return (Criteria) this;
        }

        public Criteria andOperateParamEqualTo(String value) {
            addCriterion("operate_param =", value, "operateParam");
            return (Criteria) this;
        }

        public Criteria andOperateParamNotEqualTo(String value) {
            addCriterion("operate_param <>", value, "operateParam");
            return (Criteria) this;
        }

        public Criteria andOperateParamGreaterThan(String value) {
            addCriterion("operate_param >", value, "operateParam");
            return (Criteria) this;
        }

        public Criteria andOperateParamGreaterThanOrEqualTo(String value) {
            addCriterion("operate_param >=", value, "operateParam");
            return (Criteria) this;
        }

        public Criteria andOperateParamLessThan(String value) {
            addCriterion("operate_param <", value, "operateParam");
            return (Criteria) this;
        }

        public Criteria andOperateParamLessThanOrEqualTo(String value) {
            addCriterion("operate_param <=", value, "operateParam");
            return (Criteria) this;
        }

        public Criteria andOperateParamLike(String value) {
            addCriterion("operate_param like", value, "operateParam");
            return (Criteria) this;
        }

        public Criteria andOperateParamNotLike(String value) {
            addCriterion("operate_param not like", value, "operateParam");
            return (Criteria) this;
        }

        public Criteria andOperateParamIn(List<String> values) {
            addCriterion("operate_param in", values, "operateParam");
            return (Criteria) this;
        }

        public Criteria andOperateParamNotIn(List<String> values) {
            addCriterion("operate_param not in", values, "operateParam");
            return (Criteria) this;
        }

        public Criteria andOperateParamBetween(String value1, String value2) {
            addCriterion("operate_param between", value1, value2, "operateParam");
            return (Criteria) this;
        }

        public Criteria andOperateParamNotBetween(String value1, String value2) {
            addCriterion("operate_param not between", value1, value2, "operateParam");
            return (Criteria) this;
        }

        public Criteria andOperateStatusIsNull() {
            addCriterion("operate_status is null");
            return (Criteria) this;
        }

        public Criteria andOperateStatusIsNotNull() {
            addCriterion("operate_status is not null");
            return (Criteria) this;
        }

        public Criteria andOperateStatusEqualTo(Boolean value) {
            addCriterion("operate_status =", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusNotEqualTo(Boolean value) {
            addCriterion("operate_status <>", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusGreaterThan(Boolean value) {
            addCriterion("operate_status >", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("operate_status >=", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusLessThan(Boolean value) {
            addCriterion("operate_status <", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("operate_status <=", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusIn(List<Boolean> values) {
            addCriterion("operate_status in", values, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusNotIn(List<Boolean> values) {
            addCriterion("operate_status not in", values, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("operate_status between", value1, value2, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("operate_status not between", value1, value2, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIsNull() {
            addCriterion("error_msg is null");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIsNotNull() {
            addCriterion("error_msg is not null");
            return (Criteria) this;
        }

        public Criteria andErrorMsgEqualTo(String value) {
            addCriterion("error_msg =", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotEqualTo(String value) {
            addCriterion("error_msg <>", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgGreaterThan(String value) {
            addCriterion("error_msg >", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgGreaterThanOrEqualTo(String value) {
            addCriterion("error_msg >=", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLessThan(String value) {
            addCriterion("error_msg <", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLessThanOrEqualTo(String value) {
            addCriterion("error_msg <=", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLike(String value) {
            addCriterion("error_msg like", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotLike(String value) {
            addCriterion("error_msg not like", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIn(List<String> values) {
            addCriterion("error_msg in", values, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotIn(List<String> values) {
            addCriterion("error_msg not in", values, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgBetween(String value1, String value2) {
            addCriterion("error_msg between", value1, value2, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotBetween(String value1, String value2) {
            addCriterion("error_msg not between", value1, value2, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNull() {
            addCriterion("operate_time is null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNotNull() {
            addCriterion("operate_time is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeEqualTo(Date value) {
            addCriterion("operate_time =", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotEqualTo(Date value) {
            addCriterion("operate_time <>", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThan(Date value) {
            addCriterion("operate_time >", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operate_time >=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThan(Date value) {
            addCriterion("operate_time <", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThanOrEqualTo(Date value) {
            addCriterion("operate_time <=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIn(List<Date> values) {
            addCriterion("operate_time in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotIn(List<Date> values) {
            addCriterion("operate_time not in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeBetween(Date value1, Date value2) {
            addCriterion("operate_time between", value1, value2, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotBetween(Date value1, Date value2) {
            addCriterion("operate_time not between", value1, value2, "operateTime");
            return (Criteria) this;
        }
    }

    /**
     * operate_log
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * operate_log 2018-12-06
     */
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