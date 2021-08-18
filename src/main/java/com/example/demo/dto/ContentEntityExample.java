package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class ContentEntityExample {

	protected String orderByClause;
	
	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public ContentEntityExample() {
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

		public Criteria andContentIdIsNull() {
			addCriterion("content_id is null");
			return (Criteria) this;
		}

		public Criteria andContentIdIsNotNull() {
			addCriterion("content_id is not null");
			return (Criteria) this;
		}

		public Criteria andContentIdEqualTo(Integer value) {
			addCriterion("content_id =", value, "contentId");
			return (Criteria) this;
		}

		public Criteria andContentIdNotEqualTo(Integer value) {
			addCriterion("content_id <>", value, "contentId");
			return (Criteria) this;
		}

		public Criteria andContentIdGreaterThan(Integer value) {
			addCriterion("content_id >", value, "contentId");
			return (Criteria) this;
		}

		public Criteria andContentIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("content_id >=", value, "contentId");
			return (Criteria) this;
		}

		public Criteria andContentIdLessThan(Integer value) {
			addCriterion("content_id <", value, "contentId");
			return (Criteria) this;
		}

		public Criteria andContentIdLessThanOrEqualTo(Integer value) {
			addCriterion("content_id <=", value, "contentId");
			return (Criteria) this;
		}

		public Criteria andContentIdIn(List<Integer> values) {
			addCriterion("content_id in", values, "contentId");
			return (Criteria) this;
		}

		public Criteria andContentIdNotIn(List<Integer> values) {
			addCriterion("content_id not in", values, "contentId");
			return (Criteria) this;
		}

		public Criteria andContentIdBetween(Integer value1, Integer value2) {
			addCriterion("content_id between", value1, value2, "contentId");
			return (Criteria) this;
		}

		public Criteria andContentIdNotBetween(Integer value1, Integer value2) {
			addCriterion("content_id not between", value1, value2, "contentId");
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

		public Criteria andContentTitleIsNull() {
			addCriterion("content_title is null");
			return (Criteria) this;
		}

		public Criteria andContentTitleIsNotNull() {
			addCriterion("content_title is not null");
			return (Criteria) this;
		}

		public Criteria andContentTitleEqualTo(String value) {
			addCriterion("content_title =", value, "contentTitle");
			return (Criteria) this;
		}

		public Criteria andContentTitleNotEqualTo(String value) {
			addCriterion("content_title <>", value, "contentTitle");
			return (Criteria) this;
		}

		public Criteria andContentTitleGreaterThan(String value) {
			addCriterion("content_title >", value, "contentTitle");
			return (Criteria) this;
		}

		public Criteria andContentTitleGreaterThanOrEqualTo(String value) {
			addCriterion("content_title >=", value, "contentTitle");
			return (Criteria) this;
		}

		public Criteria andContentTitleLessThan(String value) {
			addCriterion("content_title <", value, "contentTitle");
			return (Criteria) this;
		}

		public Criteria andContentTitleLessThanOrEqualTo(String value) {
			addCriterion("content_title <=", value, "contentTitle");
			return (Criteria) this;
		}

		public Criteria andContentTitleLike(String value) {
			addCriterion("content_title like", value, "contentTitle");
			return (Criteria) this;
		}

		public Criteria andContentTitleNotLike(String value) {
			addCriterion("content_title not like", value, "contentTitle");
			return (Criteria) this;
		}

		public Criteria andContentTitleIn(List<String> values) {
			addCriterion("content_title in", values, "contentTitle");
			return (Criteria) this;
		}

		public Criteria andContentTitleNotIn(List<String> values) {
			addCriterion("content_title not in", values, "contentTitle");
			return (Criteria) this;
		}

		public Criteria andContentTitleBetween(String value1, String value2) {
			addCriterion("content_title between", value1, value2, "contentTitle");
			return (Criteria) this;
		}

		public Criteria andContentTitleNotBetween(String value1, String value2) {
			addCriterion("content_title not between", value1, value2, "contentTitle");
			return (Criteria) this;
		}

		public Criteria andContentExplanationIsNull() {
			addCriterion("content_explanation is null");
			return (Criteria) this;
		}

		public Criteria andContentExplanationIsNotNull() {
			addCriterion("content_explanation is not null");
			return (Criteria) this;
		}

		public Criteria andContentExplanationEqualTo(String value) {
			addCriterion("content_explanation =", value, "contentExplanation");
			return (Criteria) this;
		}

		public Criteria andContentExplanationNotEqualTo(String value) {
			addCriterion("content_explanation <>", value, "contentExplanation");
			return (Criteria) this;
		}

		public Criteria andContentExplanationGreaterThan(String value) {
			addCriterion("content_explanation >", value, "contentExplanation");
			return (Criteria) this;
		}

		public Criteria andContentExplanationGreaterThanOrEqualTo(String value) {
			addCriterion("content_explanation >=", value, "contentExplanation");
			return (Criteria) this;
		}

		public Criteria andContentExplanationLessThan(String value) {
			addCriterion("content_explanation <", value, "contentExplanation");
			return (Criteria) this;
		}

		public Criteria andContentExplanationLessThanOrEqualTo(String value) {
			addCriterion("content_explanation <=", value, "contentExplanation");
			return (Criteria) this;
		}

		public Criteria andContentExplanationLike(String value) {
			addCriterion("content_explanation like", value, "contentExplanation");
			return (Criteria) this;
		}

		public Criteria andContentExplanationNotLike(String value) {
			addCriterion("content_explanation not like", value, "contentExplanation");
			return (Criteria) this;
		}

		public Criteria andContentExplanationIn(List<String> values) {
			addCriterion("content_explanation in", values, "contentExplanation");
			return (Criteria) this;
		}

		public Criteria andContentExplanationNotIn(List<String> values) {
			addCriterion("content_explanation not in", values, "contentExplanation");
			return (Criteria) this;
		}

		public Criteria andContentExplanationBetween(String value1, String value2) {
			addCriterion("content_explanation between", value1, value2, "contentExplanation");
			return (Criteria) this;
		}

		public Criteria andContentExplanationNotBetween(String value1, String value2) {
			addCriterion("content_explanation not between", value1, value2, "contentExplanation");
			return (Criteria) this;
		}

		public Criteria andIsCompletedIsNull() {
			addCriterion("is_completed is null");
			return (Criteria) this;
		}

		public Criteria andIsCompletedIsNotNull() {
			addCriterion("is_completed is not null");
			return (Criteria) this;
		}

		public Criteria andIsCompletedEqualTo(Boolean value) {
			addCriterion("is_completed =", value, "isCompleted");
			return (Criteria) this;
		}

		public Criteria andIsCompletedNotEqualTo(Boolean value) {
			addCriterion("is_completed <>", value, "isCompleted");
			return (Criteria) this;
		}

		public Criteria andIsCompletedGreaterThan(Boolean value) {
			addCriterion("is_completed >", value, "isCompleted");
			return (Criteria) this;
		}

		public Criteria andIsCompletedGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_completed >=", value, "isCompleted");
			return (Criteria) this;
		}

		public Criteria andIsCompletedLessThan(Boolean value) {
			addCriterion("is_completed <", value, "isCompleted");
			return (Criteria) this;
		}

		public Criteria andIsCompletedLessThanOrEqualTo(Boolean value) {
			addCriterion("is_completed <=", value, "isCompleted");
			return (Criteria) this;
		}

		public Criteria andIsCompletedIn(List<Boolean> values) {
			addCriterion("is_completed in", values, "isCompleted");
			return (Criteria) this;
		}

		public Criteria andIsCompletedNotIn(List<Boolean> values) {
			addCriterion("is_completed not in", values, "isCompleted");
			return (Criteria) this;
		}

		public Criteria andIsCompletedBetween(Boolean value1, Boolean value2) {
			addCriterion("is_completed between", value1, value2, "isCompleted");
			return (Criteria) this;
		}

		public Criteria andIsCompletedNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_completed not between", value1, value2, "isCompleted");
			return (Criteria) this;
		}

		public Criteria andIsDeletedIsNull() {
			addCriterion("is_deleted is null");
			return (Criteria) this;
		}

		public Criteria andIsDeletedIsNotNull() {
			addCriterion("is_deleted is not null");
			return (Criteria) this;
		}

		public Criteria andIsDeletedEqualTo(Boolean value) {
			addCriterion("is_deleted =", value, "isDeleted");
			return (Criteria) this;
		}

		public Criteria andIsDeletedNotEqualTo(Boolean value) {
			addCriterion("is_deleted <>", value, "isDeleted");
			return (Criteria) this;
		}

		public Criteria andIsDeletedGreaterThan(Boolean value) {
			addCriterion("is_deleted >", value, "isDeleted");
			return (Criteria) this;
		}

		public Criteria andIsDeletedGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_deleted >=", value, "isDeleted");
			return (Criteria) this;
		}

		public Criteria andIsDeletedLessThan(Boolean value) {
			addCriterion("is_deleted <", value, "isDeleted");
			return (Criteria) this;
		}

		public Criteria andIsDeletedLessThanOrEqualTo(Boolean value) {
			addCriterion("is_deleted <=", value, "isDeleted");
			return (Criteria) this;
		}

		public Criteria andIsDeletedIn(List<Boolean> values) {
			addCriterion("is_deleted in", values, "isDeleted");
			return (Criteria) this;
		}

		public Criteria andIsDeletedNotIn(List<Boolean> values) {
			addCriterion("is_deleted not in", values, "isDeleted");
			return (Criteria) this;
		}

		public Criteria andIsDeletedBetween(Boolean value1, Boolean value2) {
			addCriterion("is_deleted between", value1, value2, "isDeleted");
			return (Criteria) this;
		}

		public Criteria andIsDeletedNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_deleted not between", value1, value2, "isDeleted");
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