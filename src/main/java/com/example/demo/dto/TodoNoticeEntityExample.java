package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class TodoNoticeEntityExample {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public TodoNoticeEntityExample() {
		oredCriteria = new ArrayList<>();
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
			criteria = new ArrayList<>();
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

		public Criteria andNoticeIdIsNull() {
			addCriterion("notice_id is null");
			return (Criteria) this;
		}

		public Criteria andNoticeIdIsNotNull() {
			addCriterion("notice_id is not null");
			return (Criteria) this;
		}

		public Criteria andNoticeIdEqualTo(Integer value) {
			addCriterion("notice_id =", value, "noticeId");
			return (Criteria) this;
		}

		public Criteria andNoticeIdNotEqualTo(Integer value) {
			addCriterion("notice_id <>", value, "noticeId");
			return (Criteria) this;
		}

		public Criteria andNoticeIdGreaterThan(Integer value) {
			addCriterion("notice_id >", value, "noticeId");
			return (Criteria) this;
		}

		public Criteria andNoticeIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("notice_id >=", value, "noticeId");
			return (Criteria) this;
		}

		public Criteria andNoticeIdLessThan(Integer value) {
			addCriterion("notice_id <", value, "noticeId");
			return (Criteria) this;
		}

		public Criteria andNoticeIdLessThanOrEqualTo(Integer value) {
			addCriterion("notice_id <=", value, "noticeId");
			return (Criteria) this;
		}

		public Criteria andNoticeIdIn(List<Integer> values) {
			addCriterion("notice_id in", values, "noticeId");
			return (Criteria) this;
		}

		public Criteria andNoticeIdNotIn(List<Integer> values) {
			addCriterion("notice_id not in", values, "noticeId");
			return (Criteria) this;
		}

		public Criteria andNoticeIdBetween(Integer value1, Integer value2) {
			addCriterion("notice_id between", value1, value2, "noticeId");
			return (Criteria) this;
		}

		public Criteria andNoticeIdNotBetween(Integer value1, Integer value2) {
			addCriterion("notice_id not between", value1, value2, "noticeId");
			return (Criteria) this;
		}

		public Criteria andTodoIdIsNull() {
			addCriterion("todo_id is null");
			return (Criteria) this;
		}

		public Criteria andTodoIdIsNotNull() {
			addCriterion("todo_id is not null");
			return (Criteria) this;
		}

		public Criteria andTodoIdEqualTo(Integer value) {
			addCriterion("todo_id =", value, "todoId");
			return (Criteria) this;
		}

		public Criteria andTodoIdNotEqualTo(Integer value) {
			addCriterion("todo_id <>", value, "todoId");
			return (Criteria) this;
		}

		public Criteria andTodoIdGreaterThan(Integer value) {
			addCriterion("todo_id >", value, "todoId");
			return (Criteria) this;
		}

		public Criteria andTodoIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("todo_id >=", value, "todoId");
			return (Criteria) this;
		}

		public Criteria andTodoIdLessThan(Integer value) {
			addCriterion("todo_id <", value, "todoId");
			return (Criteria) this;
		}

		public Criteria andTodoIdLessThanOrEqualTo(Integer value) {
			addCriterion("todo_id <=", value, "todoId");
			return (Criteria) this;
		}

		public Criteria andTodoIdIn(List<Integer> values) {
			addCriterion("todo_id in", values, "todoId");
			return (Criteria) this;
		}

		public Criteria andTodoIdNotIn(List<Integer> values) {
			addCriterion("todo_id not in", values, "todoId");
			return (Criteria) this;
		}

		public Criteria andTodoIdBetween(Integer value1, Integer value2) {
			addCriterion("todo_id between", value1, value2, "todoId");
			return (Criteria) this;
		}

		public Criteria andTodoIdNotBetween(Integer value1, Integer value2) {
			addCriterion("todo_id not between", value1, value2, "todoId");
			return (Criteria) this;
		}

		public Criteria andMessageIsNull() {
			addCriterion("message is null");
			return (Criteria) this;
		}

		public Criteria andMessageIsNotNull() {
			addCriterion("message is not null");
			return (Criteria) this;
		}

		public Criteria andMessageEqualTo(String value) {
			addCriterion("message =", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotEqualTo(String value) {
			addCriterion("message <>", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageGreaterThan(String value) {
			addCriterion("message >", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageGreaterThanOrEqualTo(String value) {
			addCriterion("message >=", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageLessThan(String value) {
			addCriterion("message <", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageLessThanOrEqualTo(String value) {
			addCriterion("message <=", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageLike(String value) {
			addCriterion("message like", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotLike(String value) {
			addCriterion("message not like", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageIn(List<String> values) {
			addCriterion("message in", values, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotIn(List<String> values) {
			addCriterion("message not in", values, "message");
			return (Criteria) this;
		}

		public Criteria andMessageBetween(String value1, String value2) {
			addCriterion("message between", value1, value2, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotBetween(String value1, String value2) {
			addCriterion("message not between", value1, value2, "message");
			return (Criteria) this;
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

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}