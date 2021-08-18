package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class TerminalEntityExample {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public TerminalEntityExample() {
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

		public Criteria andTerminalIdIsNull() {
			addCriterion("terminal_id is null");
			return (Criteria) this;
		}

		public Criteria andTerminalIdIsNotNull() {
			addCriterion("terminal_id is not null");
			return (Criteria) this;
		}

		public Criteria andTerminalIdEqualTo(Integer value) {
			addCriterion("terminal_id =", value, "terminalId");
			return (Criteria) this;
		}

		public Criteria andTerminalIdNotEqualTo(Integer value) {
			addCriterion("terminal_id <>", value, "terminalId");
			return (Criteria) this;
		}

		public Criteria andTerminalIdGreaterThan(Integer value) {
			addCriterion("terminal_id >", value, "terminalId");
			return (Criteria) this;
		}

		public Criteria andTerminalIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("terminal_id >=", value, "terminalId");
			return (Criteria) this;
		}

		public Criteria andTerminalIdLessThan(Integer value) {
			addCriterion("terminal_id <", value, "terminalId");
			return (Criteria) this;
		}

		public Criteria andTerminalIdLessThanOrEqualTo(Integer value) {
			addCriterion("terminal_id <=", value, "terminalId");
			return (Criteria) this;
		}

		public Criteria andTerminalIdIn(List<Integer> values) {
			addCriterion("terminal_id in", values, "terminalId");
			return (Criteria) this;
		}

		public Criteria andTerminalIdNotIn(List<Integer> values) {
			addCriterion("terminal_id not in", values, "terminalId");
			return (Criteria) this;
		}

		public Criteria andTerminalIdBetween(Integer value1, Integer value2) {
			addCriterion("terminal_id between", value1, value2, "terminalId");
			return (Criteria) this;
		}

		public Criteria andTerminalIdNotBetween(Integer value1, Integer value2) {
			addCriterion("terminal_id not between", value1, value2, "terminalId");
			return (Criteria) this;
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

		public Criteria andTerminalNameIsNull() {
			addCriterion("terminal_name is null");
			return (Criteria) this;
		}

		public Criteria andTerminalNameIsNotNull() {
			addCriterion("terminal_name is not null");
			return (Criteria) this;
		}

		public Criteria andTerminalNameEqualTo(String value) {
			addCriterion("terminal_name =", value, "terminalName");
			return (Criteria) this;
		}

		public Criteria andTerminalNameNotEqualTo(String value) {
			addCriterion("terminal_name <>", value, "terminalName");
			return (Criteria) this;
		}

		public Criteria andTerminalNameGreaterThan(String value) {
			addCriterion("terminal_name >", value, "terminalName");
			return (Criteria) this;
		}

		public Criteria andTerminalNameGreaterThanOrEqualTo(String value) {
			addCriterion("terminal_name >=", value, "terminalName");
			return (Criteria) this;
		}

		public Criteria andTerminalNameLessThan(String value) {
			addCriterion("terminal_name <", value, "terminalName");
			return (Criteria) this;
		}

		public Criteria andTerminalNameLessThanOrEqualTo(String value) {
			addCriterion("terminal_name <=", value, "terminalName");
			return (Criteria) this;
		}

		public Criteria andTerminalNameLike(String value) {
			addCriterion("terminal_name like", value, "terminalName");
			return (Criteria) this;
		}

		public Criteria andTerminalNameNotLike(String value) {
			addCriterion("terminal_name not like", value, "terminalName");
			return (Criteria) this;
		}

		public Criteria andTerminalNameIn(List<String> values) {
			addCriterion("terminal_name in", values, "terminalName");
			return (Criteria) this;
		}

		public Criteria andTerminalNameNotIn(List<String> values) {
			addCriterion("terminal_name not in", values, "terminalName");
			return (Criteria) this;
		}

		public Criteria andTerminalNameBetween(String value1, String value2) {
			addCriterion("terminal_name between", value1, value2, "terminalName");
			return (Criteria) this;
		}

		public Criteria andTerminalNameNotBetween(String value1, String value2) {
			addCriterion("terminal_name not between", value1, value2, "terminalName");
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