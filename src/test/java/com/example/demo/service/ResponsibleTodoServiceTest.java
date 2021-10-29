package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.AlreadySelectedAsTodoResponsibleException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotSelectedAsTodoResponsibleException;
import com.example.demo.form.ResponsibleTodoForm;
import com.example.demo.response.TodoOnResponsibleResponse;
import com.example.demo.response.UserInTodoOnResponsibleResponse;
import com.example.demo.utility.CommonUtility;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/ResponsibleTodoServiceTest/pettern.xml")
@TestExecutionListeners({
	  DependencyInjectionTestExecutionListener.class,
	  DirtiesContextTestExecutionListener.class,
	  TransactionalTestExecutionListener.class,
	  DbUnitTestExecutionListener.class
	})
public class ResponsibleTodoServiceTest {
	@Autowired
	ResponsibleTodoService service;
	@Autowired
	CommonUtility utillity;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ResponsibleTodoServiceTest/expect/insert_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_1() {
		var userId = 2;
		var todoOnProjectId = 5;
		var userName = "tester2";
		
		service.insert(userId, todoOnProjectId, userName);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ResponsibleTodoServiceTest/expect/insert_5.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_5() {//contentのコピーあり
		var userId = 2;
		var todoOnProjectId = 7;
		var userName = "tester2";
		
		service.insert(userId, todoOnProjectId, userName);
	}
	
	@Transactional 
	@Test
	public void insert_2() {
		var userId = 2;
		var todoOnProjectId = 4;
		var userName = "tester1";
		
		assertThrows(AlreadySelectedAsTodoResponsibleException.class ,
				() -> service.insert(userId, todoOnProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void insert_3() {//userName
		var userId = 2;
		var todoOnProjectId = 5;
		var userName = "tester500";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, todoOnProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void insert_7() {//todo_on_project
		var userId = 2;
		var todoOnProjectId = 15;
		var userName = "tester1";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, todoOnProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void insert_4() {
		var userId = 1;
		var todoOnProjectId = 6;
		var userName = "tester2";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.insert(userId, todoOnProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void insert_6() {//public_project is completed
		var userId = 2;
		var todoOnProjectId = 8;
		var userName = "tester1";
		
		assertThrows(BadRequestException.class ,
				() -> service.insert(userId, todoOnProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void getList_1() {//指定有
		var userId = -1;
		var form = new ResponsibleTodoForm();
		form.setPublicProjectId(6);
		form.setStartDate(utillity.stringToDate("2021-09-1"));
		form.setFinishDate(utillity.stringToDate("2021-10-1"));
		
		List<TodoOnResponsibleResponse> expectList = new ArrayList<>();
		var expect = new TodoOnResponsibleResponse();
		expect.setProjectId(6);
		expect.setTodoName("todo_1");
		expect.setTodoOnResponsibleId(12);
		expect.setStartDate(utillity.stringToDate("2021-09-8"));
		expect.setFinishDate(utillity.stringToDate("2021-09-16"));
		expect.setIsCompleted(false);
		expect.setTodoOnProjectId(4);
		expectList.add(expect);
		
		expect = new TodoOnResponsibleResponse();
		expect.setProjectId(6);
		expect.setTodoName("todo_2");
		expect.setTodoOnResponsibleId(13);
		expect.setStartDate(utillity.stringToDate("2021-09-10"));
		expect.setFinishDate(utillity.stringToDate("2021-09-20"));
		expect.setIsCompleted(false);
		expect.setTodoOnProjectId(5);
		expectList.add(expect);
		
		assertThat(service.getList(userId, form)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getList_2() {//指定なし
		var userId = 1;
		var form = new ResponsibleTodoForm();
		List<TodoOnResponsibleResponse> expectList = new ArrayList<>();
		var expect = new TodoOnResponsibleResponse();
		expect.setProjectId(6);
		expect.setTodoName("todo_1");
		expect.setTodoOnResponsibleId(12);
		expect.setStartDate(utillity.stringToDate("2021-09-8"));
		expect.setFinishDate(utillity.stringToDate("2021-09-16"));
		expect.setIsCompleted(false);
		expect.setTodoOnProjectId(4);
		expectList.add(expect);
		
		expect = new TodoOnResponsibleResponse();
		expect.setProjectId(6);
		expect.setTodoName("todo_2");
		expect.setTodoOnResponsibleId(13);
		expect.setStartDate(utillity.stringToDate("2021-09-10"));
		expect.setFinishDate(utillity.stringToDate("2021-09-20"));
		expect.setIsCompleted(false);
		expect.setTodoOnProjectId(5);
		expectList.add(expect);
		
		expect = new TodoOnResponsibleResponse();
		expect.setProjectId(6);
		expect.setTodoName("todo_3");
		expect.setTodoOnResponsibleId(14);
		expect.setStartDate(utillity.stringToDate("2021-09-1"));
		expect.setFinishDate(utillity.stringToDate("2021-10-16"));
		expect.setIsCompleted(false);
		expect.setTodoOnProjectId(6);
		expectList.add(expect);
		
		expect = new TodoOnResponsibleResponse();
		expect.setProjectId(6);
		expect.setTodoName("todo_4");
		expect.setTodoOnResponsibleId(15);
		expect.setStartDate(utillity.stringToDate("2021-10-5"));
		expect.setFinishDate(utillity.stringToDate("2021-10-9"));
		expect.setIsCompleted(false);
		expect.setTodoOnProjectId(7);
		expectList.add(expect);
		
		assertThat(service.getList(userId, form)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ResponsibleTodoServiceTest/expect/get_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void get_1() {
		var userId = 1;
		var todoOnProjectId = 5;
		var expect = new TodoOnResponsibleResponse();
		expect.setProjectId(6);
		expect.setTodoName("todo_2");
		expect.setTodoOnResponsibleId(13);
		expect.setStartDate(utillity.stringToDate("2021-09-10"));
		expect.setFinishDate(utillity.stringToDate("2021-09-20"));
		expect.setIsCompleted(false);
		expect.setTodoOnProjectId(todoOnProjectId);
		
		assertThat(service.get(userId, todoOnProjectId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_2() {
		var userId = 1;
		var todoOnProjectId = 15;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	public void get_3() {
		var userId = 2;
		var todoOnProjectId = 5;
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.get(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	public void getResponsiblePeopleList_1() {//担当者
		var userId = 1;
		var todoOnProjectId = 5;
		List<UserInTodoOnResponsibleResponse> expectList = new ArrayList<>();
		var expect = new UserInTodoOnResponsibleResponse();
		expect.setUserName("tester1");
		expect.setTodoOnProjectId(5);
		expect.setIsCompleted(false);
		expectList.add(expect);
		
		assertThat(service.getResponsiblePeopleList(userId, todoOnProjectId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getResponsiblePeopleList_2() {//担当者じゃないけど権限者
		var userId = 2;
		var todoOnProjectId = 5;
		List<UserInTodoOnResponsibleResponse> expectList = new ArrayList<>();
		var expect = new UserInTodoOnResponsibleResponse();
		expect.setUserName("tester1");
		expect.setTodoOnProjectId(5);
		expect.setIsCompleted(false);
		expectList.add(expect);
		
		assertThat(service.getResponsiblePeopleList(userId, todoOnProjectId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getResponsiblePeopleList_3() {
		var userId = 3;
		var todoOnProjectId = 5;
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.getResponsiblePeopleList(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	public void getResponsiblePeopleList_4() {
		var userId = 2;
		var todoOnProjectId = 15;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.getResponsiblePeopleList(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ResponsibleTodoServiceTest/expect/delete_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_1() {
		var userId = 2;
		var todoOnProjectId = 5;
		var userName = "tester1";
		
		service.delete(userId, todoOnProjectId, userName);
	}
	
	@Transactional 
	@Test
	public void delete_2() {//userName in todoOnProject
		var userId = 2;
		var todoOnProjectId = 5;
		var userName = "tester2";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, todoOnProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void delete_3() {//todoOnProjectId
		var userId = 2;
		var todoOnProjectId = 15;
		var userName = "tester1";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, todoOnProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void delete_4() {
		var userId = 1;
		var todoOnProjectId = 4;
		var userName = "tester2";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.delete(userId, todoOnProjectId, userName));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ResponsibleTodoServiceTest/expect/exit_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void exit_1() {
		var userId = 1;
		var todoOnProjectId = 4;
		
		service.exit(userId, todoOnProjectId);
	}
	
	@Transactional 
	@Test
	public void exit_2() {
		var userId = 1;
		var todoOnProjectId = 14;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.exit(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	public void exit_3() {
		var userId = 2;
		var todoOnProjectId = 6;
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.exit(userId, todoOnProjectId));
	}

	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ResponsibleTodoServiceTest/expect/setIsCompletedAll_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompletedAll_1() {
		var userId = 2;
		var todoOnProjectId = 4;
		var isCompleted = true;
		
		service.setIsCompletedAll(userId, todoOnProjectId, isCompleted);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ResponsibleTodoServiceTest/expect/setIsCompletedAll_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompletedAll_2() {
		var userId = 2;
		var todoOnProjectId = 4;
		var isCompleted = false;
		
		service.setIsCompletedAll(userId, todoOnProjectId, isCompleted);
	}
	
	@Transactional 
	@Test
	public void setIsCompletedAll_3() {
		var userId = 2;
		var todoOnProjectId = 3;
		var isCompleted = true;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.setIsCompletedAll(userId, todoOnProjectId, isCompleted));
	}
	
	@Transactional 
	@Test
	public void setIsCompletedAll_4() {
		var userId = 1;
		var todoOnProjectId = 5;
		var isCompleted = true;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.setIsCompletedAll(userId, todoOnProjectId, isCompleted));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ResponsibleTodoServiceTest/expect/setIsCompleted_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_1() {//通知なし
		var userId = 1;
		var todoOnProjectId = 6;
		var isCompleted = true;
		
		service.setIsCompleted(userId, todoOnProjectId, isCompleted);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ResponsibleTodoServiceTest/expect/setIsCompleted_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_2() {//通知なし
		var userId = 1;
		var todoOnProjectId = 6;
		var isCompleted = false;
		
		service.setIsCompleted(userId, todoOnProjectId, isCompleted);
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_3() {
		var userId = 1;
		var todoOnProjectId = 14;
		var isCompleted = true;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.setIsCompleted(userId, todoOnProjectId, isCompleted));
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_4() {
		var userId = 2;
		var todoOnProjectId = 6;
		var isCompleted = true;
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.setIsCompleted(userId, todoOnProjectId, isCompleted));
	}
}
