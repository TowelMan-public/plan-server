package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class UserConfigEntityExample {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;


	public UserConfigEntityExample() {
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

		public Criteria andUserIdIsNull() {
			addCriterion("user_id is null");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("user_id is not null");
			return (Criteria) this;
		}

		public Criteria andUserIdEqualTo(Integer value) {
			addCriterion("user_id =", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotEqualTo(Integer value) {
			addCriterion("user_id <>", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThan(Integer value) {
			addCriterion("user_id >", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("user_id >=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThan(Integer value) {
			addCriterion("user_id <", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThanOrEqualTo(Integer value) {
			addCriterion("user_id <=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdIn(List<Integer> values) {
			addCriterion("user_id in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotIn(List<Integer> values) {
			addCriterion("user_id not in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdBetween(Integer value1, Integer value2) {
			addCriterion("user_id between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
			addCriterion("user_id not between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForTodoNoticeIsNull() {
			addCriterion("before_deadline_for_todo_notice is null");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForTodoNoticeIsNotNull() {
			addCriterion("before_deadline_for_todo_notice is not null");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForTodoNoticeEqualTo(Integer value) {
			addCriterion("before_deadline_for_todo_notice =", value, "beforeDeadlineForTodoNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForTodoNoticeNotEqualTo(Integer value) {
			addCriterion("before_deadline_for_todo_notice <>", value, "beforeDeadlineForTodoNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForTodoNoticeGreaterThan(Integer value) {
			addCriterion("before_deadline_for_todo_notice >", value, "beforeDeadlineForTodoNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForTodoNoticeGreaterThanOrEqualTo(Integer value) {
			addCriterion("before_deadline_for_todo_notice >=", value, "beforeDeadlineForTodoNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForTodoNoticeLessThan(Integer value) {
			addCriterion("before_deadline_for_todo_notice <", value, "beforeDeadlineForTodoNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForTodoNoticeLessThanOrEqualTo(Integer value) {
			addCriterion("before_deadline_for_todo_notice <=", value, "beforeDeadlineForTodoNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForTodoNoticeIn(List<Integer> values) {
			addCriterion("before_deadline_for_todo_notice in", values, "beforeDeadlineForTodoNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForTodoNoticeNotIn(List<Integer> values) {
			addCriterion("before_deadline_for_todo_notice not in", values, "beforeDeadlineForTodoNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForTodoNoticeBetween(Integer value1, Integer value2) {
			addCriterion("before_deadline_for_todo_notice between", value1, value2, "beforeDeadlineForTodoNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForTodoNoticeNotBetween(Integer value1, Integer value2) {
			addCriterion("before_deadline_for_todo_notice not between", value1, value2, "beforeDeadlineForTodoNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForProjectNoticeIsNull() {
			addCriterion("before_deadline_for_project_notice is null");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForProjectNoticeIsNotNull() {
			addCriterion("before_deadline_for_project_notice is not null");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForProjectNoticeEqualTo(Integer value) {
			addCriterion("before_deadline_for_project_notice =", value, "beforeDeadlineForProjectNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForProjectNoticeNotEqualTo(Integer value) {
			addCriterion("before_deadline_for_project_notice <>", value, "beforeDeadlineForProjectNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForProjectNoticeGreaterThan(Integer value) {
			addCriterion("before_deadline_for_project_notice >", value, "beforeDeadlineForProjectNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForProjectNoticeGreaterThanOrEqualTo(Integer value) {
			addCriterion("before_deadline_for_project_notice >=", value, "beforeDeadlineForProjectNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForProjectNoticeLessThan(Integer value) {
			addCriterion("before_deadline_for_project_notice <", value, "beforeDeadlineForProjectNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForProjectNoticeLessThanOrEqualTo(Integer value) {
			addCriterion("before_deadline_for_project_notice <=", value, "beforeDeadlineForProjectNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForProjectNoticeIn(List<Integer> values) {
			addCriterion("before_deadline_for_project_notice in", values, "beforeDeadlineForProjectNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForProjectNoticeNotIn(List<Integer> values) {
			addCriterion("before_deadline_for_project_notice not in", values, "beforeDeadlineForProjectNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForProjectNoticeBetween(Integer value1, Integer value2) {
			addCriterion("before_deadline_for_project_notice between", value1, value2,
					"beforeDeadlineForProjectNotice");
			return (Criteria) this;
		}

		public Criteria andBeforeDeadlineForProjectNoticeNotBetween(Integer value1, Integer value2) {
			addCriterion("before_deadline_for_project_notice not between", value1, value2,
					"beforeDeadlineForProjectNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedTodoNoticeIsNull() {
			addCriterion("is_push_inserted_todo_notice is null");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedTodoNoticeIsNotNull() {
			addCriterion("is_push_inserted_todo_notice is not null");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedTodoNoticeEqualTo(Boolean value) {
			addCriterion("is_push_inserted_todo_notice =", value, "isPushInsertedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedTodoNoticeNotEqualTo(Boolean value) {
			addCriterion("is_push_inserted_todo_notice <>", value, "isPushInsertedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedTodoNoticeGreaterThan(Boolean value) {
			addCriterion("is_push_inserted_todo_notice >", value, "isPushInsertedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedTodoNoticeGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_push_inserted_todo_notice >=", value, "isPushInsertedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedTodoNoticeLessThan(Boolean value) {
			addCriterion("is_push_inserted_todo_notice <", value, "isPushInsertedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedTodoNoticeLessThanOrEqualTo(Boolean value) {
			addCriterion("is_push_inserted_todo_notice <=", value, "isPushInsertedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedTodoNoticeIn(List<Boolean> values) {
			addCriterion("is_push_inserted_todo_notice in", values, "isPushInsertedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedTodoNoticeNotIn(List<Boolean> values) {
			addCriterion("is_push_inserted_todo_notice not in", values, "isPushInsertedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedTodoNoticeBetween(Boolean value1, Boolean value2) {
			addCriterion("is_push_inserted_todo_notice between", value1, value2, "isPushInsertedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedTodoNoticeNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_push_inserted_todo_notice not between", value1, value2, "isPushInsertedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedStartedTodoNoticeIsNull() {
			addCriterion("is_push_inserted_started_todo_notice is null");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedStartedTodoNoticeIsNotNull() {
			addCriterion("is_push_inserted_started_todo_notice is not null");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedStartedTodoNoticeEqualTo(Boolean value) {
			addCriterion("is_push_inserted_started_todo_notice =", value, "isPushInsertedStartedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedStartedTodoNoticeNotEqualTo(Boolean value) {
			addCriterion("is_push_inserted_started_todo_notice <>", value, "isPushInsertedStartedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedStartedTodoNoticeGreaterThan(Boolean value) {
			addCriterion("is_push_inserted_started_todo_notice >", value, "isPushInsertedStartedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedStartedTodoNoticeGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_push_inserted_started_todo_notice >=", value, "isPushInsertedStartedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedStartedTodoNoticeLessThan(Boolean value) {
			addCriterion("is_push_inserted_started_todo_notice <", value, "isPushInsertedStartedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedStartedTodoNoticeLessThanOrEqualTo(Boolean value) {
			addCriterion("is_push_inserted_started_todo_notice <=", value, "isPushInsertedStartedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedStartedTodoNoticeIn(List<Boolean> values) {
			addCriterion("is_push_inserted_started_todo_notice in", values, "isPushInsertedStartedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedStartedTodoNoticeNotIn(List<Boolean> values) {
			addCriterion("is_push_inserted_started_todo_notice not in", values, "isPushInsertedStartedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedStartedTodoNoticeBetween(Boolean value1, Boolean value2) {
			addCriterion("is_push_inserted_started_todo_notice between", value1, value2,
					"isPushInsertedStartedTodoNotice");
			return (Criteria) this;
		}

		public Criteria andIsPushInsertedStartedTodoNoticeNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_push_inserted_started_todo_notice not between", value1, value2,
					"isPushInsertedStartedTodoNotice");
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