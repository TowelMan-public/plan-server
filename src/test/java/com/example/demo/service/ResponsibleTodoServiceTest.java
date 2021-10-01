package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.AlreadySelectedAsTodoResponsibleException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotSelectedAsTodoResponsibleException;
import com.example.demo.form.ResponsibleTodoForm;
import com.example.demo.response.TerminalResponse;
import com.example.demo.response.TodoOnResponsibleResponse;
import com.example.demo.response.UserInTodoOnResponsibleResponse;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/ResponsibleTodoServiceTest/pettern.xml")
public class ResponsibleTodoServiceTest {
	@Autowired
	ResponsibleTodoService service;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ResponsibleTodoServiceTest/expect/insert_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_1() {
		var userId = -1;
		var todoOnProjectId = -1;
		var userName = "";
		
		service.insert(userId, todoOnProjectId, userName);
	}
	
	@Transactional 
	@Test
	public void insert_2() {
		var userId = -1;
		var todoOnProjectId = -1;
		var userName = "";
		
		assertThrows(AlreadySelectedAsTodoResponsibleException.class ,
				() -> service.insert(userId, todoOnProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void insert_3() {
		var userId = -1;
		var todoOnProjectId = -1;
		var userName = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, todoOnProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void insert_4() {
		var userId = -1;
		var todoOnProjectId = -1;
		var userName = "";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.insert(userId, todoOnProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void getList_1() {//指定有
		var userId = -1;
		var form = new ResponsibleTodoForm();
		//TODO
		List<TodoOnResponsibleResponse> expectList = new ArrayList<>();
		var expect = new TerminalResponse();
		//TODO
		
		assertThat(service.getList(userId, form)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getList_2() {//指定なし
		var userId = -1;
		var form = new ResponsibleTodoForm();
		//TODO
		List<TodoOnResponsibleResponse> expectList = new ArrayList<>();
		var expect = new TodoOnResponsibleResponse();
		//TODO
		
		assertThat(service.getList(userId, form)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void get_1() {
		var userId = -1;
		var todoOnProjectId = -1;
		var expect = new TodoOnResponsibleResponse();
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
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.get(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	public void getResponsiblePeopleList_1() {//担当者
		var userId = -1;
		var todoOnProjectId = -1;
		List<UserInTodoOnResponsibleResponse> expectList = new ArrayList<>();
		var expect = new UserInTodoOnResponsibleResponse();
		//TODO
		
		assertThat(service.getResponsiblePeopleList(userId, todoOnProjectId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getResponsiblePeopleList_2() {//担当者じゃないけど権限者
		var userId = -1;
		var todoOnProjectId = -1;
		List<UserInTodoOnResponsibleResponse> expectList = new ArrayList<>();
		var expect = new UserInTodoOnResponsibleResponse();
		//TODO
		
		assertThat(service.getResponsiblePeopleList(userId, todoOnProjectId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getResponsiblePeopleList_3() {
		var userId = -1;
		var todoOnProjectId = -1;
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.getResponsiblePeopleList(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	public void getResponsiblePeopleList_4() {
		var userId = -1;
		var todoOnProjectId = -1;
		
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
		var userId = -1;
		var todoOnProjectId = -1;
		var userName = "";
		
		service.delete(userId, todoOnProjectId, userName);
	}
	
	@Transactional 
	@Test
	public void delete_2() {//userName in todoOnProject
		var userId = -1;
		var todoOnProjectId = -1;
		var userName = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, todoOnProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void delete_3() {//todoOnProjectId
		var userId = -1;
		var todoOnProjectId = -1;
		var userName = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, todoOnProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void delete_4() {
		var userId = -1;
		var todoOnProjectId = -1;
		var userName = "";
		
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
		var userId = -1;
		var todoOnProjectId = -1;
		
		service.exit(userId, todoOnProjectId);
	}
	
	@Transactional 
	@Test
	public void exit_2() {
		var userId = -1;
		var todoOnProjectId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.exit(userId, todoOnProjectId));
	}
	
	@Transactional 
	@Test
	public void exit_3() {
		var userId = -1;
		var todoOnProjectId = -1;
		
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
		var userId = -1;
		var todoOnProjectId = -1;
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
		var userId = -1;
		var todoOnProjectId = -1;
		var isCompleted = false;
		
		service.setIsCompletedAll(userId, todoOnProjectId, isCompleted);
	}
	
	@Transactional 
	@Test
	public void setIsCompletedAll_3() {
		var userId = -1;
		var todoOnProjectId = -1;
		var isCompleted = true;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.setIsCompletedAll(userId, todoOnProjectId, isCompleted));
	}
	
	@Transactional 
	@Test
	public void setIsCompletedAll_4() {
		var userId = -1;
		var todoOnProjectId = -1;
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
	public void setIsCompleted_1() {
		var userId = -1;
		var todoOnProjectId = -1;
		var isCompleted = true;
		
		service.setIsCompleted(userId, todoOnProjectId, isCompleted);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ResponsibleTodoServiceTest/expect/setIsCompleted_2.xml",
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
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.setIsCompleted(userId, todoOnProjectId, isCompleted));
	}
}
