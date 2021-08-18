package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublicProjectEntityExample {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public PublicProjectEntityExample() {
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

		public Criteria andPublicProjectIdIsNull() {
			addCriterion("public_project_id is null");
			return (Criteria) this;
		}

		public Criteria andPublicProjectIdIsNotNull() {
			addCriterion("public_project_id is not null");
			return (Criteria) this;
		}

		public Criteria andPublicProjectIdEqualTo(Integer value) {
			addCriterion("public_project_id =", value, "publicProjectId");
			return (Criteria) this;
		}

		public Criteria andPublicProjectIdNotEqualTo(Integer value) {
			addCriterion("public_project_id <>", value, "publicProjectId");
			return (Criteria) this;
		}

		public Criteria andPublicProjectIdGreaterThan(Integer value) {
			addCriterion("public_project_id >", value, "publicProjectId");
			return (Criteria) this;
		}

		public Criteria andPublicProjectIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("public_project_id >=", value, "publicProjectId");
			return (Criteria) this;
		}

		public Criteria andPublicProjectIdLessThan(Integer value) {
			addCriterion("public_project_id <", value, "publicProjectId");
			return (Criteria) this;
		}

		public Criteria andPublicProjectIdLessThanOrEqualTo(Integer value) {
			addCriterion("public_project_id <=", value, "publicProjectId");
			return (Criteria) this;
		}

		public Criteria andPublicProjectIdIn(List<Integer> values) {
			addCriterion("public_project_id in", values, "publicProjectId");
			return (Criteria) this;
		}

		public Criteria andPublicProjectIdNotIn(List<Integer> values) {
			addCriterion("public_project_id not in", values, "publicProjectId");
			return (Criteria) this;
		}

		public Criteria andPublicProjectIdBetween(Integer value1, Integer value2) {
			addCriterion("public_project_id between", value1, value2, "publicProjectId");
			return (Criteria) this;
		}

		public Criteria andPublicProjectIdNotBetween(Integer value1, Integer value2) {
			addCriterion("public_project_id not between", value1, value2, "publicProjectId");
			return (Criteria) this;
		}

		public Criteria andProjectNameIsNull() {
			addCriterion("project_name is null");
			return (Criteria) this;
		}

		public Criteria andProjectNameIsNotNull() {
			addCriterion("project_name is not null");
			return (Criteria) this;
		}

		public Criteria andProjectNameEqualTo(String value) {
			addCriterion("project_name =", value, "projectName");
			return (Criteria) this;
		}

		public Criteria andProjectNameNotEqualTo(String value) {
			addCriterion("project_name <>", value, "projectName");
			return (Criteria) this;
		}

		public Criteria andProjectNameGreaterThan(String value) {
			addCriterion("project_name >", value, "projectName");
			return (Criteria) this;
		}

		public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
			addCriterion("project_name >=", value, "projectName");
			return (Criteria) this;
		}

		public Criteria andProjectNameLessThan(String value) {
			addCriterion("project_name <", value, "projectName");
			return (Criteria) this;
		}

		public Criteria andProjectNameLessThanOrEqualTo(String value) {
			addCriterion("project_name <=", value, "projectName");
			return (Criteria) this;
		}

		public Criteria andProjectNameLike(String value) {
			addCriterion("project_name like", value, "projectName");
			return (Criteria) this;
		}

		public Criteria andProjectNameNotLike(String value) {
			addCriterion("project_name not like", value, "projectName");
			return (Criteria) this;
		}

		public Criteria andProjectNameIn(List<String> values) {
			addCriterion("project_name in", values, "projectName");
			return (Criteria) this;
		}

		public Criteria andProjectNameNotIn(List<String> values) {
			addCriterion("project_name not in", values, "projectName");
			return (Criteria) this;
		}

		public Criteria andProjectNameBetween(String value1, String value2) {
			addCriterion("project_name between", value1, value2, "projectName");
			return (Criteria) this;
		}

		public Criteria andProjectNameNotBetween(String value1, String value2) {
			addCriterion("project_name not between", value1, value2, "projectName");
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