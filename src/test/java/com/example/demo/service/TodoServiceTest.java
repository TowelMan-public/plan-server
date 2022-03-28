package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DatabaseTest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.exception.NotSelectedAsTodoResponsibleException;
import com.example.demo.form.TodoForm;
import com.example.demo.response.TodoOnProjectResponse;
import com.example.demo.utility.CommonUtility;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/TodoServiceTest/pettern.xml")
@TestExecutionListeners({
	  DependencyInjectionTestExecutionListener.class,
	  DirtiesContextTestExecutionListener.class,
	  TransactionalTestExecutionListener.class,
	  DbUnitTestExecutionListener.class
	})
public class TodoServiceTest extends DatabaseTest {
	@Autowired
	TodoService service;
	@Autowired
	CommonUtility utillity;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/insert_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_1() throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException, BadRequestException {
		var userId = 2;
		var form = new TodoForm();
		form.setProjectId(4);
		form.setStartDate(utillity.stringToDate("2021-10-5"));
		form.setFinishDate(utillity.stringToDate("2021-10-6"));
		form.setTodoName("newTodo");
		form.setIsCopyContentsToResponsible(false);
		var expectTodoId = 16;
		
		assertThat(service.insert(userId, form)).isEqualTo(expectTodoId);
	}
	
	@Transactional 
	@Test
	public void insert_2() {//project
		var userId = 3;
		var form = new TodoForm();
		form.setProjectId(400);
		form.setStartDate(utillity.stringToDate("2021-10-5"));
		form.setFinishDate(utillity.stringToDate("2021-10-6"));
		form.setTodoName("newTodo");
		form.setIsCopyContentsToResponsible(false);
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void insert_3() {
		var userId = 1;
		var form = new TodoForm();
		form.setProjectId(6);
		form.setStartDate(utillity.stringToDate("2021-10-5"));
		form.setFinishDate(utillity.stringToDate("2021-10-6"));
		form.setTodoName("newTodo");
		form.setIsCopyContentsToResponsible(false);
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void insert_4() {//期間がprojectを超えてる
		var userId = 2;
		var form = new TodoForm();
		form.setProjectId(6);
		form.setStartDate(utillity.stringToDate("2021-1-5"));
		form.setFinishDate(utillity.stringToDate("2021-12-31"));
		form.setTodoName("newTodo");
		form.setIsCopyContentsToResponsible(false);
		
		assertThrows(BadRequestException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void insert_5() {//private_project in user
		var userId = 2;
		var form = new TodoForm();
		form.setProjectId(1);
		form.setStartDate(utillity.stringToDate("2021-1-5"));
		form.setFinishDate(utillity.stringToDate("2021-12-31"));
		form.setTodoName("newTodo");
		form.setIsCopyContentsToResponsible(false);
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void insert_6() {//public_project is completed
		var userId = 2;
		var form = new TodoForm();
		form.setProjectId(9);
		form.setStartDate(utillity.stringToDate("2021-1-5"));
		form.setFinishDate(utillity.stringToDate("2021-12-31"));
		form.setTodoName("newTodo");
		form.setIsCopyContentsToResponsible(false);
		
		assertThrows(BadRequestException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/get_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void get_1() throws NotFoundValueException, NotJoinedPublicProjectException {
		var userId = 1;
		var todoOnProjectId = 5;
		var expect = new TodoOnProjectResponse();
		expect.setProjectId(6);
		expect.setTodoName("todo_2");
		expect.setTodoOnProjectId(todoOnProjectId);
		expect.setStartDate(utillity.stringToDate("2021-09-10"));
		expect.setFinishDate(utillity.stringToDate("2021-09-20"));
		expect.setIsCompleted(false);
		expect.setIsCopyContentsToUsers(false);
		
		assertThat(service.get(userId, todoOnProjectId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/get_4.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void get_4() throws NotFoundValueException, NotJoinedPublicProjectException {
		var userId = 3;
		var todoOnProjectId = 5;
		var expect = new TodoOnProjectResponse();
		expect.setProjectId(6);
		expect.setTodoName("todo_2");
		expect.setTodoOnProjectId(todoOnProjectId);
		expect.setStartDate(utillity.stringToDate("2021-09-10"));
		expect.setFinishDate(utillity.stringToDate("2021-09-20"));
		expect.setIsCompleted(false);
		expect.setIsCopyContentsToUsers(false);
		
		assertThat(service.get(userId, todoOnProjectId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_2() {
		var userId = 1;
		var todoOnProjectId = 500;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	public void get_3() {
		var userId = 2;
		var todoOnProjectId = 10;
		
		assertThrows(NotJoinedPublicProjectException.class ,
				() -> service.get(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	public void getList_1() throws NotFoundValueException, NotJoinedPublicProjectException{//指定なし
		var userId = 1;
		var form = new TodoForm();
		List<TodoOnProjectResponse> expectList = new ArrayList<>();
		var expect = new TodoOnProjectResponse();
		expect.setProjectId(6);
		expect.setTodoOnProjectId(4);
		expect.setTodoName("todo_1");
		expect.setStartDate(utillity.stringToDate("2021-09-8"));
		expect.setFinishDate(utillity.stringToDate("2021-09-16"));
		expect.setIsCopyContentsToUsers(false);
		expect.setIsCompleted(false);
		expectList.add(expect);
		
		expect = new TodoOnProjectResponse();
		expect.setProjectId(6);
		expect.setTodoName("todo_2");
		expect.setTodoOnProjectId(5);
		expect.setStartDate(utillity.stringToDate("2021-09-10"));
		expect.setFinishDate(utillity.stringToDate("2021-09-20"));
		expect.setIsCompleted(false);
		expect.setIsCopyContentsToUsers(false);
		expectList.add(expect);
		
		expect = new TodoOnProjectResponse();
		expect.setProjectId(6);
		expect.setTodoOnProjectId(6);
		expect.setTodoName("todo_3");
		expect.setStartDate(utillity.stringToDate("2021-09-1"));
		expect.setFinishDate(utillity.stringToDate("2021-10-16"));
		expect.setIsCopyContentsToUsers(false);
		expect.setIsCompleted(false);
		expectList.add(expect);
		
		expect = new TodoOnProjectResponse();
		expect.setProjectId(6);
		expect.setTodoOnProjectId(7);
		expect.setTodoName("todo_4");
		expect.setStartDate(utillity.stringToDate("2021-10-5"));
		expect.setFinishDate(utillity.stringToDate("2021-10-9"));
		expect.setIsCopyContentsToUsers(true);
		expect.setIsCompleted(false);
		expectList.add(expect);
		
		assertThat(service.getList(userId, form)).containsExactlyInAnyOrderElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getList_2() throws NotFoundValueException, NotJoinedPublicProjectException{//指定あり
		var userId = 1;
		var form = new TodoForm();
		form.setProjectId(6);
		form.setStartDate(utillity.stringToDate("2021-9-1"));
		form.setFinishDate(utillity.stringToDate("2021-10-1"));
		
		List<TodoOnProjectResponse> expectList = new ArrayList<>();
		var expect = new TodoOnProjectResponse();
		expect = new TodoOnProjectResponse();
		expect.setProjectId(6);
		expect.setTodoOnProjectId(4);
		expect.setTodoName("todo_1");
		expect.setStartDate(utillity.stringToDate("2021-09-8"));
		expect.setFinishDate(utillity.stringToDate("2021-09-16"));
		expect.setIsCopyContentsToUsers(false);
		expect.setIsCompleted(false);
		expectList.add(expect);
		
		expect = new TodoOnProjectResponse();
		expect.setProjectId(6);
		expect.setTodoName("todo_2");
		expect.setTodoOnProjectId(5);
		expect.setStartDate(utillity.stringToDate("2021-09-10"));
		expect.setFinishDate(utillity.stringToDate("2021-09-20"));
		expect.setIsCompleted(false);
		expect.setIsCopyContentsToUsers(false);
		expectList.add(expect);

		expect = new TodoOnProjectResponse();
		expect.setProjectId(6);
		expect.setTodoName("todo_3");
		expect.setTodoOnProjectId(6);
		expect.setStartDate(utillity.stringToDate("2021-09-1"));
		expect.setFinishDate(utillity.stringToDate("2021-10-16"));
		expect.setIsCompleted(false);
		expect.setIsCopyContentsToUsers(false);
		expectList.add(expect);
				
		assertThat(service.getList(userId, form)).containsExactlyInAnyOrderElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/updateIsCopyContentsToResponsible_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateIsCopyContentsToResponsible_1()
			throws NotFoundValueException, NotJoinedPublicProjectException, NotSelectedAsTodoResponsibleException {//権限者
		var userId = 2;
		var todoOnProjectId = 5;
		var isCopyContentsToResponsible = true;
		
		service.updateIsCopyContentsToResponsible(userId, todoOnProjectId, isCopyContentsToResponsible);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/updateIsCopyContentsToResponsible_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateIsCopyContentsToResponsible_2() 
			throws NotFoundValueException, NotJoinedPublicProjectException, NotSelectedAsTodoResponsibleException {//権限はないけど担当者である
		var userId = 1;
		var todoOnProjectId = 4;
		var isCopyContentsToResponsible = true;
		
		service.updateIsCopyContentsToResponsible(userId, todoOnProjectId, isCopyContentsToResponsible);
	}
	
	@Transactional 
	@Test
	public void updateIsCopyContentsToResponsible_3() {
		var userId = 2;
		var todoOnProjectId = 14;
		var isCopyContentsToResponsible = false;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateIsCopyContentsToResponsible(userId, todoOnProjectId, isCopyContentsToResponsible));
	}
	
	@Transactional 
	@Test
	public void updateIsCopyContentsToResponsible_4() {
		var userId = 3;
		var todoOnProjectId = 7;
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
	public void updateTodoName_1() throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		var userId = 2;
		var todoOnProjectId = 5;
		var todoName = "newer";
		
		service.updateTodoName(userId, todoOnProjectId, todoName);
	}
	
	@Transactional 
	@Test
	public void updateTodoName_2() {
		var userId = 2;
		var todoOnProjectId = 14;
		var todoName = "test";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateTodoName(userId, todoOnProjectId, todoName));
	}
	
	@Transactional 
	@Test
	public void updateTodoName_3() {
		var userId = 1;
		var todoOnProjectId = 5;
		var todoName = "test";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateTodoName(userId, todoOnProjectId, todoName));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/updateStartDate_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateStartDate_1() throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		var userId = 2;
		var todoOnProjectId = 5;
		var startDate = utillity.stringToDate("2021-9-5");
		
		service.updateStartDate(userId, todoOnProjectId, startDate);
	}
	
	@Transactional 
	@Test
	public void updateStartDate_2() {
		var userId = 2;
		var todoOnProjectId = 14;
		var startDate = new Date();
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateStartDate(userId, todoOnProjectId, startDate));
	}
	
	@Transactional 
	@Test
	public void updateStartDate_3() {
		var userId = 1;
		var todoOnProjectId = 5;
		var startDate = new Date();
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateStartDate(userId, todoOnProjectId, startDate));
	}
	
	@Transactional 
	@Test
	public void updateStartDate_4() {//todo的な意味
		var userId = 2;
		var todoOnProjectId = 5;
		var startDate = utillity.stringToDate("2021-10-5");
		
		assertThrows(BadRequestException.class ,
				() -> service.updateStartDate(userId, todoOnProjectId, startDate));
	}
	
	@Transactional 
	@Test
	public void updateStartDate_5() {//project的な意味
		var userId = 2;
		var todoOnProjectId = 5;
		var startDate = utillity.stringToDate("2021-1-5");
		
		assertThrows(BadRequestException.class ,
				() -> service.updateStartDate(userId, todoOnProjectId, startDate));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/updateFinishDate_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateFinishDate_1() throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		var userId = 2;
		var todoOnProjectId = 5;
		var finishDate = utillity.stringToDate("2021-9-18");
		
		service.updateFinishDate(userId, todoOnProjectId, finishDate);
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_2() {
		var userId = 2;
		var todoOnProjectId = 14;
		var finishDate = new Date();
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateFinishDate(userId, todoOnProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_3() {
		var userId = 1;
		var todoOnProjectId = 5;
		var finishDate = new Date();
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateFinishDate(userId, todoOnProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_4() {//todo的な意味
		var userId = 2;
		var todoOnProjectId = 5;
		var finishDate = utillity.stringToDate("2021-9-1");
		
		assertThrows(BadRequestException.class ,
				() -> service.updateFinishDate(userId, todoOnProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_5() {//project的な意味
		var userId = 2;
		var todoOnProjectId = 5;
		var finishDate = utillity.stringToDate("2022-1-5");
		
		assertThrows(BadRequestException.class ,
				() -> service.updateFinishDate(userId, todoOnProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/delete_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_1() throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		var userId = 2;
		var todoOnProjectId = 6;
		
		service.delete(userId, todoOnProjectId);
	}
	
	@Transactional 
	@Test
	public void delete_2() {
		var userId = 2;
		var todoOnProjectId = 15;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	public void delete_3() {
		var userId = 1;
		var todoOnProjectId = 7;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.delete(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/setIsCompleted_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_1() throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		var userId = 2;
		var todoOnProjectId = 4;
		var isCompleted = true;
		
		service.setIsCompleted(userId, todoOnProjectId, isCompleted);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/TodoServiceTest/expect/setIsCompleted_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_2() throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		var userId = 2;
		var todoOnProjectId = 8;
		var isCompleted = false;
		
		service.setIsCompleted(userId, todoOnProjectId, isCompleted);
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_3() {
		var userId = 2;
		var todoOnProjectId = 14;
		var isCompleted = true;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.setIsCompleted(userId, todoOnProjectId, isCompleted));
	} 

	@Transactional 
	@Test
	public void setIsCompleted_4() {
		var userId = 3;
		var todoOnProjectId = 4;
		var isCompleted = true;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.setIsCompleted(userId, todoOnProjectId, isCompleted));
	} 
}