package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoOnProjectEntityExample {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public TodoOnProjectEntityExample() {
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

		public Criteria andTodoOnProjectIdIsNull() {
			addCriterion("todo_on_project_id is null");
			return (Criteria) this;
		}

		public Criteria andTodoOnProjectIdIsNotNull() {
			addCriterion("todo_on_project_id is not null");
			return (Criteria) this;
		}

		public Criteria andTodoOnProjectIdEqualTo(Integer value) {
			addCriterion("todo_on_project_id =", value, "todoOnProjectId");
			return (Criteria) this;
		}

		public Criteria andTodoOnProjectIdNotEqualTo(Integer value) {
			addCriterion("todo_on_project_id <>", value, "todoOnProjectId");
			return (Criteria) this;
		}

		public Criteria andTodoOnProjectIdGreaterThan(Integer value) {
			addCriterion("todo_on_project_id >", value, "todoOnProjectId");
			return (Criteria) this;
		}

		public Criteria andTodoOnProjectIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("todo_on_project_id >=", value, "todoOnProjectId");
			return (Criteria) this;
		}

		public Criteria andTodoOnProjectIdLessThan(Integer value) {
			addCriterion("todo_on_project_id <", value, "todoOnProjectId");
			return (Criteria) this;
		}

		public Criteria andTodoOnProjectIdLessThanOrEqualTo(Integer value) {
			addCriterion("todo_on_project_id <=", value, "todoOnProjectId");
			return (Criteria) this;
		}

		public Criteria andTodoOnProjectIdIn(List<Integer> values) {
			addCriterion("todo_on_project_id in", values, "todoOnProjectId");
			return (Criteria) this;
		}

		public Criteria andTodoOnProjectIdNotIn(List<Integer> values) {
			addCriterion("todo_on_project_id not in", values, "todoOnProjectId");
			return (Criteria) this;
		}

		public Criteria andTodoOnProjectIdBetween(Integer value1, Integer value2) {
			addCriterion("todo_on_project_id between", value1, value2, "todoOnProjectId");
			return (Criteria) this;
		}

		public Criteria andTodoOnProjectIdNotBetween(Integer value1, Integer value2) {
			addCriterion("todo_on_project_id not between", value1, value2, "todoOnProjectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdIsNull() {
			addCriterion("project_id is null");
			return (Criteria) this;
		}

		public Criteria andProjectIdIsNotNull() {
			addCriterion("project_id is not null");
			return (Criteria) this;
		}

		public Criteria andProjectIdEqualTo(Integer value) {
			addCriterion("project_id =", value, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdNotEqualTo(Integer value) {
			addCriterion("project_id <>", value, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdGreaterThan(Integer value) {
			addCriterion("project_id >", value, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("project_id >=", value, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdLessThan(Integer value) {
			addCriterion("project_id <", value, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
			addCriterion("project_id <=", value, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdIn(List<Integer> values) {
			addCriterion("project_id in", values, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdNotIn(List<Integer> values) {
			addCriterion("project_id not in", values, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdBetween(Integer value1, Integer value2) {
			addCriterion("project_id between", value1, value2, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
			addCriterion("project_id not between", value1, value2, "projectId");
			return (Criteria) this;
		}

		public Criteria andTodoNameIsNull() {
			addCriterion("todo_name is null");
			return (Criteria) this;
		}

		public Criteria andTodoNameIsNotNull() {
			addCriterion("todo_name is not null");
			return (Criteria) this;
		}

		public Criteria andTodoNameEqualTo(String value) {
			addCriterion("todo_name =", value, "todoName");
			return (Criteria) this;
		}

		public Criteria andTodoNameNotEqualTo(String value) {
			addCriterion("todo_name <>", value, "todoName");
			return (Criteria) this;
		}

		public Criteria andTodoNameGreaterThan(String value) {
			addCriterion("todo_name >", value, "todoName");
			return (Criteria) this;
		}

		public Criteria andTodoNameGreaterThanOrEqualTo(String value) {
			addCriterion("todo_name >=", value, "todoName");
			return (Criteria) this;
		}

		public Criteria andTodoNameLessThan(String value) {
			addCriterion("todo_name <", value, "todoName");
			return (Criteria) this;
		}

		public Criteria andTodoNameLessThanOrEqualTo(String value) {
			addCriterion("todo_name <=", value, "todoName");
			return (Criteria) this;
		}

		public Criteria andTodoNameLike(String value) {
			addCriterion("todo_name like", value, "todoName");
			return (Criteria) this;
		}

		public Criteria andTodoNameNotLike(String value) {
			addCriterion("todo_name not like", value, "todoName");
			return (Criteria) this;
		}

		public Criteria andTodoNameIn(List<String> values) {
			addCriterion("todo_name in", values, "todoName");
			return (Criteria) this;
		}

		public Criteria andTodoNameNotIn(List<String> values) {
			addCriterion("todo_name not in", values, "todoName");
			return (Criteria) this;
		}

		public Criteria andTodoNameBetween(String value1, String value2) {
			addCriterion("todo_name between", value1, value2, "todoName");
			return (Criteria) this;
		}

		public Criteria andTodoNameNotBetween(String value1, String value2) {
			addCriterion("todo_name not between", value1, value2, "todoName");
			return (Criteria) this;
		}

		public Criteria andStartDateIsNull() {
			addCriterion("start_date is null");
			return (Criteria) this;
		}

		public Criteria andStartDateIsNotNull() {
			addCriterion("start_date is not null");
			return (Criteria) this;
		}

		public Criteria andStartDateEqualTo(Date value) {
			addCriterion("start_date =", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateNotEqualTo(Date value) {
			addCriterion("start_date <>", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateGreaterThan(Date value) {
			addCriterion("start_date >", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
			addCriterion("start_date >=", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateLessThan(Date value) {
			addCriterion("start_date <", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateLessThanOrEqualTo(Date value) {
			addCriterion("start_date <=", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateIn(List<Date> values) {
			addCriterion("start_date in", values, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateNotIn(List<Date> values) {
			addCriterion("start_date not in", values, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateBetween(Date value1, Date value2) {
			addCriterion("start_date between", value1, value2, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateNotBetween(Date value1, Date value2) {
			addCriterion("start_date not between", value1, value2, "startDate");
			return (Criteria) this;
		}

		public Criteria andFinishDateIsNull() {
			addCriterion("finish_date is null");
			return (Criteria) this;
		}

		public Criteria andFinishDateIsNotNull() {
			addCriterion("finish_date is not null");
			return (Criteria) this;
		}

		public Criteria andFinishDateEqualTo(Date value) {
			addCriterion("finish_date =", value, "finishDate");
			return (Criteria) this;
		}

		public Criteria andFinishDateNotEqualTo(Date value) {
			addCriterion("finish_date <>", value, "finishDate");
			return (Criteria) this;
		}

		public Criteria andFinishDateGreaterThan(Date value) {
			addCriterion("finish_date >", value, "finishDate");
			return (Criteria) this;
		}

		public Criteria andFinishDateGreaterThanOrEqualTo(Date value) {
			addCriterion("finish_date >=", value, "finishDate");
			return (Criteria) this;
		}

		public Criteria andFinishDateLessThan(Date value) {
			addCriterion("finish_date <", value, "finishDate");
			return (Criteria) this;
		}

		public Criteria andFinishDateLessThanOrEqualTo(Date value) {
			addCriterion("finish_date <=", value, "finishDate");
			return (Criteria) this;
		}

		public Criteria andFinishDateIn(List<Date> values) {
			addCriterion("finish_date in", values, "finishDate");
			return (Criteria) this;
		}

		public Criteria andFinishDateNotIn(List<Date> values) {
			addCriterion("finish_date not in", values, "finishDate");
			return (Criteria) this;
		}

		public Criteria andFinishDateBetween(Date value1, Date value2) {
			addCriterion("finish_date between", value1, value2, "finishDate");
			return (Criteria) this;
		}

		public Criteria andFinishDateNotBetween(Date value1, Date value2) {
			addCriterion("finish_date not between", value1, value2, "finishDate");
			return (Criteria) this;
		}

		public Criteria andIsCopyContentsToUsersIsNull() {
			addCriterion("is_copy_contents_to_users is null");
			return (Criteria) this;
		}

		public Criteria andIsCopyContentsToUsersIsNotNull() {
			addCriterion("is_copy_contents_to_users is not null");
			return (Criteria) this;
		}

		public Criteria andIsCopyContentsToUsersEqualTo(Boolean value) {
			addCriterion("is_copy_contents_to_users =", value, "isCopyContentsToUsers");
			return (Criteria) this;
		}

		public Criteria andIsCopyContentsToUsersNotEqualTo(Boolean value) {
			addCriterion("is_copy_contents_to_users <>", value, "isCopyContentsToUsers");
			return (Criteria) this;
		}

		public Criteria andIsCopyContentsToUsersGreaterThan(Boolean value) {
			addCriterion("is_copy_contents_to_users >", value, "isCopyContentsToUsers");
			return (Criteria) this;
		}

		public Criteria andIsCopyContentsToUsersGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_copy_contents_to_users >=", value, "isCopyContentsToUsers");
			return (Criteria) this;
		}

		public Criteria andIsCopyContentsToUsersLessThan(Boolean value) {
			addCriterion("is_copy_contents_to_users <", value, "isCopyContentsToUsers");
			return (Criteria) this;
		}

		public Criteria andIsCopyContentsToUsersLessThanOrEqualTo(Boolean value) {
			addCriterion("is_copy_contents_to_users <=", value, "isCopyContentsToUsers");
			return (Criteria) this;
		}

		public Criteria andIsCopyContentsToUsersIn(List<Boolean> values) {
			addCriterion("is_copy_contents_to_users in", values, "isCopyContentsToUsers");
			return (Criteria) this;
		}

		public Criteria andIsCopyContentsToUsersNotIn(List<Boolean> values) {
			addCriterion("is_copy_contents_to_users not in", values, "isCopyContentsToUsers");
			return (Criteria) this;
		}

		public Criteria andIsCopyContentsToUsersBetween(Boolean value1, Boolean value2) {
			addCriterion("is_copy_contents_to_users between", value1, value2, "isCopyContentsToUsers");
			return (Criteria) this;
		}

		public Criteria andIsCopyContentsToUsersNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_copy_contents_to_users not between", value1, value2, "isCopyContentsToUsers");
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