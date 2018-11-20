package com.dimple.bean;

import java.util.ArrayList;
import java.util.List;

public class LinksExample {
    /**
     * links
     */
    protected String orderByClause;

    /**
     * links
     */
    protected boolean distinct;

    /**
     * links
     */
    protected List<Criteria> oredCriteria;

    public LinksExample() {
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
     * links 2018-11-20
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

        public Criteria andLinkIdIsNull() {
            addCriterion("link_id is null");
            return (Criteria) this;
        }

        public Criteria andLinkIdIsNotNull() {
            addCriterion("link_id is not null");
            return (Criteria) this;
        }

        public Criteria andLinkIdEqualTo(Integer value) {
            addCriterion("link_id =", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotEqualTo(Integer value) {
            addCriterion("link_id <>", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdGreaterThan(Integer value) {
            addCriterion("link_id >", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_id >=", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdLessThan(Integer value) {
            addCriterion("link_id <", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdLessThanOrEqualTo(Integer value) {
            addCriterion("link_id <=", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdIn(List<Integer> values) {
            addCriterion("link_id in", values, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotIn(List<Integer> values) {
            addCriterion("link_id not in", values, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdBetween(Integer value1, Integer value2) {
            addCriterion("link_id between", value1, value2, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotBetween(Integer value1, Integer value2) {
            addCriterion("link_id not between", value1, value2, "linkId");
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

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andLinkDisplayIsNull() {
            addCriterion("link_display is null");
            return (Criteria) this;
        }

        public Criteria andLinkDisplayIsNotNull() {
            addCriterion("link_display is not null");
            return (Criteria) this;
        }

        public Criteria andLinkDisplayEqualTo(Boolean value) {
            addCriterion("link_display =", value, "linkDisplay");
            return (Criteria) this;
        }

        public Criteria andLinkDisplayNotEqualTo(Boolean value) {
            addCriterion("link_display <>", value, "linkDisplay");
            return (Criteria) this;
        }

        public Criteria andLinkDisplayGreaterThan(Boolean value) {
            addCriterion("link_display >", value, "linkDisplay");
            return (Criteria) this;
        }

        public Criteria andLinkDisplayGreaterThanOrEqualTo(Boolean value) {
            addCriterion("link_display >=", value, "linkDisplay");
            return (Criteria) this;
        }

        public Criteria andLinkDisplayLessThan(Boolean value) {
            addCriterion("link_display <", value, "linkDisplay");
            return (Criteria) this;
        }

        public Criteria andLinkDisplayLessThanOrEqualTo(Boolean value) {
            addCriterion("link_display <=", value, "linkDisplay");
            return (Criteria) this;
        }

        public Criteria andLinkDisplayIn(List<Boolean> values) {
            addCriterion("link_display in", values, "linkDisplay");
            return (Criteria) this;
        }

        public Criteria andLinkDisplayNotIn(List<Boolean> values) {
            addCriterion("link_display not in", values, "linkDisplay");
            return (Criteria) this;
        }

        public Criteria andLinkDisplayBetween(Boolean value1, Boolean value2) {
            addCriterion("link_display between", value1, value2, "linkDisplay");
            return (Criteria) this;
        }

        public Criteria andLinkDisplayNotBetween(Boolean value1, Boolean value2) {
            addCriterion("link_display not between", value1, value2, "linkDisplay");
            return (Criteria) this;
        }

        public Criteria andLinkHandleIsNull() {
            addCriterion("link_handle is null");
            return (Criteria) this;
        }

        public Criteria andLinkHandleIsNotNull() {
            addCriterion("link_handle is not null");
            return (Criteria) this;
        }

        public Criteria andLinkHandleEqualTo(Boolean value) {
            addCriterion("link_handle =", value, "linkHandle");
            return (Criteria) this;
        }

        public Criteria andLinkHandleNotEqualTo(Boolean value) {
            addCriterion("link_handle <>", value, "linkHandle");
            return (Criteria) this;
        }

        public Criteria andLinkHandleGreaterThan(Boolean value) {
            addCriterion("link_handle >", value, "linkHandle");
            return (Criteria) this;
        }

        public Criteria andLinkHandleGreaterThanOrEqualTo(Boolean value) {
            addCriterion("link_handle >=", value, "linkHandle");
            return (Criteria) this;
        }

        public Criteria andLinkHandleLessThan(Boolean value) {
            addCriterion("link_handle <", value, "linkHandle");
            return (Criteria) this;
        }

        public Criteria andLinkHandleLessThanOrEqualTo(Boolean value) {
            addCriterion("link_handle <=", value, "linkHandle");
            return (Criteria) this;
        }

        public Criteria andLinkHandleIn(List<Boolean> values) {
            addCriterion("link_handle in", values, "linkHandle");
            return (Criteria) this;
        }

        public Criteria andLinkHandleNotIn(List<Boolean> values) {
            addCriterion("link_handle not in", values, "linkHandle");
            return (Criteria) this;
        }

        public Criteria andLinkHandleBetween(Boolean value1, Boolean value2) {
            addCriterion("link_handle between", value1, value2, "linkHandle");
            return (Criteria) this;
        }

        public Criteria andLinkHandleNotBetween(Boolean value1, Boolean value2) {
            addCriterion("link_handle not between", value1, value2, "linkHandle");
            return (Criteria) this;
        }

        public Criteria andLinkWeightIsNull() {
            addCriterion("link_weight is null");
            return (Criteria) this;
        }

        public Criteria andLinkWeightIsNotNull() {
            addCriterion("link_weight is not null");
            return (Criteria) this;
        }

        public Criteria andLinkWeightEqualTo(Integer value) {
            addCriterion("link_weight =", value, "linkWeight");
            return (Criteria) this;
        }

        public Criteria andLinkWeightNotEqualTo(Integer value) {
            addCriterion("link_weight <>", value, "linkWeight");
            return (Criteria) this;
        }

        public Criteria andLinkWeightGreaterThan(Integer value) {
            addCriterion("link_weight >", value, "linkWeight");
            return (Criteria) this;
        }

        public Criteria andLinkWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_weight >=", value, "linkWeight");
            return (Criteria) this;
        }

        public Criteria andLinkWeightLessThan(Integer value) {
            addCriterion("link_weight <", value, "linkWeight");
            return (Criteria) this;
        }

        public Criteria andLinkWeightLessThanOrEqualTo(Integer value) {
            addCriterion("link_weight <=", value, "linkWeight");
            return (Criteria) this;
        }

        public Criteria andLinkWeightIn(List<Integer> values) {
            addCriterion("link_weight in", values, "linkWeight");
            return (Criteria) this;
        }

        public Criteria andLinkWeightNotIn(List<Integer> values) {
            addCriterion("link_weight not in", values, "linkWeight");
            return (Criteria) this;
        }

        public Criteria andLinkWeightBetween(Integer value1, Integer value2) {
            addCriterion("link_weight between", value1, value2, "linkWeight");
            return (Criteria) this;
        }

        public Criteria andLinkWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("link_weight not between", value1, value2, "linkWeight");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }
    }

    /**
     * links
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * links 2018-11-20
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