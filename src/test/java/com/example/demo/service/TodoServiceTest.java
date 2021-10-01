package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotSelectedAsTodoResponsibleException;
import com.example.demo.form.TodoForm;
import com.example.demo.response.TodoOnProjectResponse;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/UserTerminalServiceTest/pettern.xml")
public class TodoServiceTest {
	@Autowired
	TodoService service;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/insert_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_1() {
		var userId = -1;
		var form = new TodoForm();
		var expectTodoId = -1;
		
		assertThat(service.insert(userId, form)).isEqualTo(expectTodoId);
	}
	
	@Transactional 
	@Test
	public void insert_2() {//project
		var userId = -1;
		var form = new TodoForm();
		//TODO
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void insert_3() {
		var userId = -1;
		var form = new TodoForm();
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void insert_4() {//期間がprojectを超えてる
		var userId = -1;
		var form = new TodoForm();
		
		assertThrows(BadRequestException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void insert_5() {//private_project in user
		var userId = -1;
		var form = new TodoForm();
		//TODO
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/get_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void get_1() {
		var userId = -1;
		var todoOnProjectId = -1;
		var expect = new TodoOnProjectResponse();
		//TODO
		
		assertThat(service.get(userId, todoOnProjectId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_2() {
		var userId = -1;
		var todoOnProjectId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	public void get_3() {
		var userId = -1;
		var todoOnProjectId = -1;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.get(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/getList_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void getList_1(){//指定なし
		var userId = -1;
		var form = new TodoForm();
		//TODO
		List<TodoOnProjectResponse> expectList = new ArrayList<>();
		var expect = new TodoOnProjectResponse();
		//TODO
		
		assertThat(service.getList(userId, form)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/getList_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void getList_2(){//指定あり
		var userId = -1;
		var form = new TodoForm();
		//TODO
		List<TodoOnProjectResponse> expectList = new ArrayList<>();
		var expect = new TodoOnProjectResponse();
		//TODO
		
		assertThat(service.getList(userId, form)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/updateIsCopyContentsToResponsible_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateIsCopyContentsToResponsible_1() {//権限者
		var userId = -1;
		var todoOnProjectId = -1;
		var isCopyContentsToResponsible = false;
		
		service.updateIsCopyContentsToResponsible(userId, todoOnProjectId, isCopyContentsToResponsible);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/updateIsCopyContentsToResponsible_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateIsCopyContentsToResponsible_2() {//権限はないけど担当者である
		var userId = -1;
		var todoOnProjectId = -1;
		var isCopyContentsToResponsible = false;
		
		service.updateIsCopyContentsToResponsible(userId, todoOnProjectId, isCopyContentsToResponsible);
	}
	
	@Transactional 
	@Test
	public void updateIsCopyContentsToResponsible_3() {
		var userId = -1;
		var todoOnProjectId = -1;
		var isCopyContentsToResponsible = false;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateIsCopyContentsToResponsible(userId, todoOnProjectId, isCopyContentsToResponsible));
	}
	
	@Transactional 
	@Test
	public void updateIsCopyContentsToResponsible_4() {
		var userId = -1;
		var todoOnProjectId = -1;
		var isCopyContentsToResponsible = false;
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.updateIsCopyContentsToResponsible(userId, todoOnProjectId, isCopyContentsToResponsible));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/updateTodoName_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateTodoName_1() {
		var userId = -1;
		var todoOnProjectId = -1;
		var todoName = "";
		
		service.updateTodoName(userId, todoOnProjectId, todoName);
	}
	
	@Transactional 
	@Test
	public void updateTodoName_2() {
		var userId = -1;
		var todoOnProjectId = -1;
		var todoName = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateTodoName(userId, todoOnProjectId, todoName));
	}
	
	@Transactional 
	@Test
	public void updateTodoName_3() {
		var userId = -1;
		var todoOnProjectId = -1;
		var todoName = "";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateTodoName(userId, todoOnProjectId, todoName));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/updateStartDate_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateStartDate_1() {
		var userId = -1;
		var todoOnProjectId = -1;
		var startDate = new Date();
		
		service.updateStartDate(userId, todoOnProjectId, startDate);
	}
	
	@Transactional 
	@Test
	public void updateStartDate_2() {
		var userId = -1;
		var todoOnProjectId = -1;
		var startDate = new Date();
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateStartDate(userId, todoOnProjectId, startDate));
	}
	
	@Transactional 
	@Test
	public void updateStartDate_3() {
		var userId = -1;
		var todoOnProjectId = -1;
		var startDate = new Date();
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateStartDate(userId, todoOnProjectId, startDate));
	}
	
	@Transactional 
	@Test
	public void updateStartDate_4() {//todo的な意味
		var userId = -1;
		var todoOnProjectId = -1;
		var startDate = new Date();
		
		assertThrows(BadRequestException.class ,
				() -> service.updateStartDate(userId, todoOnProjectId, startDate));
	}
	
	@Transactional 
	@Test
	public void updateStartDate_5() {//project的な意味
		var userId = -1;
		var todoOnProjectId = -1;
		var startDate = new Date();
		
		assertThrows(BadRequestException.class ,
				() -> service.updateStartDate(userId, todoOnProjectId, startDate));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/updateFinishDate_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateFinishDate_1() {
		var userId = -1;
		var todoOnProjectId = -1;
		var finishDate = new Date();
		
		service.updateFinishDate(userId, todoOnProjectId, finishDate);
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_2() {
		var userId = -1;
		var todoOnProjectId = -1;
		var finishDate = new Date();
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateFinishDate(userId, todoOnProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_3() {
		var userId = -1;
		var todoOnProjectId = -1;
		var finishDate = new Date();
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateFinishDate(userId, todoOnProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_4() {//todo的な意味
		var userId = -1;
		var todoOnProjectId = -1;
		var finishDate = new Date();
		
		assertThrows(BadRequestException.class ,
				() -> service.updateFinishDate(userId, todoOnProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_5() {//project的な意味
		var userId = -1;
		var todoOnProjectId = -1;
		var finishDate = new Date();
		
		assertThrows(BadRequestException.class ,
				() -> service.updateFinishDate(userId, todoOnProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/delete_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_1() {
		var userId = -1;
		var todoOnProjectId = -1;
		
		service.delete(userId, todoOnProjectId);
	}
	
	@Transactional 
	@Test
	public void delete_2() {
		var userId = -1;
		var todoOnProjectId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	public void delete_3() {
		var userId = -1;
		var todoOnProjectId = -1;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.delete(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/setIsCompleted_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_1() {
		var userId = -1;
		var todoOnProjectId = -1;
		var isCompleted = true;
		
		service.setIsCompleted(userId, todoOnProjectId, isCompleted);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/setIsCompleted_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_2() {
		var userId = -1;
		var todoOnProjectId = -1;
		var isCompleted = false;
		
		service.setIsCompleted(userId, todoOnProjectId, isCompleted);
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_3() {
		var userId = -1;
		var todoOnProjectId = -1;
		var isCompleted = true;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.setIsCompleted(userId, todoOnProjectId, isCompleted));
	} 

	@Transactional 
	@Test
	public void setIsCompleted_4() {
		var userId = -1;
		var todoOnProjectId = -1;
		var isCompleted = true;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.setIsCompleted(userId, todoOnProjectId, isCompleted));
	} 
}
