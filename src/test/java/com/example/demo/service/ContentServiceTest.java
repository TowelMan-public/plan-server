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

import com.example.demo.DatabaseTest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.form.ContentForm;
import com.example.demo.response.ContentResponse;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/ContentServiceTest/pettern.xml")
@TestExecutionListeners({
	  DependencyInjectionTestExecutionListener.class,
	  DirtiesContextTestExecutionListener.class,
	  TransactionalTestExecutionListener.class,
	  DbUnitTestExecutionListener.class
	})
public class ContentServiceTest extends DatabaseTest {
	@Autowired
	ContentService service;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/insert_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_2()
			throws BadRequestException, NotHaveAuthorityToOperateProjectException, 
			NotFoundValueException, NotJoinedPublicProjectException {//todpOnProjet in publicProject 権限者
		var userId = 2;
		var contentId = 11;
		var form = new ContentForm();
		form.setTodoId(6);
		form.setContentTitle("test");
		form.setContentExplanation("test");
		
		assertThat(service.insert(userId, form)).isEqualTo(contentId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/insert_3.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_3() 
			throws BadRequestException, NotHaveAuthorityToOperateProjectException,
			NotFoundValueException, NotJoinedPublicProjectException {//todpOnProjet in privateProject
		var userId = 1;
		var contentId = 11;
		var form = new ContentForm();
		form.setTodoId(16);
		form.setContentTitle("test");
		form.setContentExplanation("test");
		
		assertThat(service.insert(userId, form)).isEqualTo(contentId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/insert_4.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_4() 
			throws BadRequestException, NotHaveAuthorityToOperateProjectException, 
			NotFoundValueException, NotJoinedPublicProjectException {//todpOnResponsible in publicProject
		var userId = 1;
		var contentId = 11;
		var form = new ContentForm();
		form.setTodoId(11);
		form.setContentTitle("test");
		form.setContentExplanation("test");
		
		assertThat(service.insert(userId, form)).isEqualTo(contentId);
	}
	
	@Transactional 
	@Test
	public void insert_5() {
		var userId = 3;
		var form = new ContentForm();
		form.setTodoId(400);
		form.setContentTitle("test");
		form.setContentExplanation("test");
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void insert_6() {//publicProject
		var userId = 1;
		var form = new ContentForm();
		form.setTodoId(4);
		form.setContentTitle("test");
		form.setContentExplanation("test");
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void insert_7() {
		var userId = 2;
		var form = new ContentForm();
		form.setTodoId(11);
		form.setContentTitle("test");
		form.setContentExplanation("test");
		
		assertThrows(BadRequestException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void insert_8() {//privateProject
		var userId = 2;
		var form = new ContentForm();
		form.setTodoId(1);
		form.setContentTitle("test");
		form.setContentExplanation("test");
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void getList_1() throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {//publicProject 加入者
		var userId = 1;
		var todoId = 7;
		List<ContentResponse> expectList = new ArrayList<>();
		var expect = new ContentResponse();
		expect.setTodoId(todoId);
		expect.setContentId(5);
		expect.setContentTitle("content_5");
		expect.setContentExplanation("content_explanation_5_for_project6");
		expect.setIsCompleted(false);
		expectList.add(expect);
		
		expect = new ContentResponse();
		expect.setTodoId(todoId);
		expect.setContentId(7);
		expect.setContentTitle("content_7");
		expect.setContentExplanation("content_explanation_7_for_project6");
		expect.setIsCompleted(true);
		expectList.add(expect);
		
		expect = new ContentResponse();
		expect.setTodoId(todoId);
		expect.setContentId(8);
		expect.setContentTitle("content_8");
		expect.setContentExplanation("content_explanation_8_for_project6");
		expect.setIsCompleted(false);
		expectList.add(expect);
		
		assertThat(service.getList(userId, todoId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getList_2() throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {//privateProject
		var userId = 1;
		var todoId = 16;
		List<ContentResponse> expectList = new ArrayList<>();
		var expect = new ContentResponse();
		expect.setTodoId(todoId);
		expect.setContentId(10);
		expect.setContentTitle("private_1");
		expect.setContentExplanation("private_1_explanation_1");
		expect.setIsCompleted(false);
		expectList.add(expect);
		
		assertThat(service.getList(userId, todoId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getList_3() throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {// todoOnResponsible 自分のみ
		var userId = 1;
		var todoId = 13;
		List<ContentResponse> expectList = new ArrayList<>();
		
		assertThat(service.getList(userId, todoId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getList_4() {
		var userId = 1;
		var todoId = 400;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.getList(userId, todoId));
	}
	
	@Transactional 
	@Test
	public void getList_5() {//privateProject
		var userId = 2;
		var todoId = 1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.getList(userId, todoId));
	}
	
	@Transactional 
	@Test
	public void getList_6() {
		var userId = 2;
		var todoId = 11;
		
		assertThrows(BadRequestException.class ,
				() -> service.getList(userId, todoId));
	}
	
	@Transactional 
	@Test
	public void getList_7() {
		var userId = 5;
		var todoId = 4;
		
		assertThrows(NotJoinedPublicProjectException.class ,
				() -> service.getList(userId, todoId));
	}
	
	@Transactional 
	@Test
	public void get_1() throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {//publicProject 加入者
		var userId = 1;
		var contentId = 8;
		var expect = new ContentResponse();
		expect.setTodoId(7);
		expect.setContentId(contentId);
		expect.setContentTitle("content_8");
		expect.setContentExplanation("content_explanation_8_for_project6");
		expect.setIsCompleted(false);
		
		assertThat(service.get(userId, contentId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	void get_2() throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {//privateProject
		var userId = 1;
		var contentId = 10;
		var expect = new ContentResponse();
		expect.setTodoId(16);
		expect.setContentId(contentId);
		expect.setContentTitle("private_1");
		expect.setContentExplanation("private_1_explanation_1");
		expect.setIsCompleted(false);
		
		assertThat(service.get(userId, contentId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_3() throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {// todoOnResponsible 自分のみ
		var userId = 1;
		var contentId = 9;
		var expect = new ContentResponse();
		expect.setTodoId(15);
		expect.setContentId(contentId);
		expect.setContentTitle("content_9");
		expect.setContentExplanation("content_9");
		expect.setIsCompleted(false);
		
		assertThat(service.get(userId, contentId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_4() {
		var userId = 1;
		var contentId = 400;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userId, contentId));
	}
	
	@Transactional 
	@Test
	public void get_5() {
		var userId = 2;
		var contentId = 1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userId, contentId));
	}
	
	@Transactional 
	@Test
	public void get_6() {
		var userId = 2;
		var contentId = 9;
		
		assertThrows(BadRequestException.class ,
				() -> service.get(userId, contentId));
	}
	
	@Transactional 
	@Test
	public void get_7() {
		var userId = 5;
		var contentId = 8;
		
		assertThrows(NotJoinedPublicProjectException.class ,
				() -> service.get(userId, contentId));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/updateTitle_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateTitle_1()
			throws BadRequestException, NotFoundValueException, 
			NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {//todpOnProjet in publicProject 権限者
		var userId = 2;
		var contentId = 5;
		var contentTitle = "test";
		
		service.updateTitle(userId, contentId, contentTitle);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/updateTitle_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateTitle_2()
			throws BadRequestException, NotFoundValueException, 
			NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {//todpOnProjet in privateProject
		var userId = 1;
		var contentId = 10;
		var contentTitle = "test";
		
		service.updateTitle(userId, contentId, contentTitle);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/updateTitle_3.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateTitle_3() 
			throws BadRequestException, NotFoundValueException, 
			NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {//todpOnResponsible in publicProject
		var userId = 1;
		var contentId = 9;
		var contentTitle = "test";
		
		service.updateTitle(userId, contentId, contentTitle);
	}
	
	@Transactional 
	@Test
	public void updateTitle_4() {
		var userId = 1;
		var contentId = 50;
		var contentTitle = "test";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateTitle(userId, contentId, contentTitle));
	}
	
	@Transactional 
	@Test
	public void updateTitle_5() {//publicProject
		var userId = 1;
		var contentId = 4;
		var contentTitle = "test";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateTitle(userId, contentId, contentTitle));
	}
	
	@Transactional 
	@Test
	public void updateTitle_6() {
		var userId = 2;
		var contentId = 9;
		var contentTitle = "test";
		
		assertThrows(BadRequestException.class ,
				() -> service.updateTitle(userId, contentId, contentTitle));
	}
	
	@Transactional 
	@Test
	public void updateTitle_7() {//privateProject
		var userId = 2;
		var contentId = 1;
		var contentTitle = "test";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateTitle(userId, contentId, contentTitle));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/updateExplanation_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateExplanation_1() 
			throws BadRequestException, NotFoundValueException, 
			NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {//todpOnProjet in publicProject 権限者
		var userId = 2;
		var contentId = 5;
		var contentExplanation = "test";
		
		service.updateExplanation(userId, contentId, contentExplanation);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/updateExplanation_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateExplanation_2() 
			throws BadRequestException, NotFoundValueException,
			NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {//todpOnProjet in privateProject
		var userId = 1;
		var contentId = 10;
		var contentExplanation = "test";
		
		service.updateExplanation(userId, contentId, contentExplanation);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/updateExplanation_3.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateExplanation_3() 
			throws BadRequestException, NotFoundValueException,
			NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {//todpOnResponsible in publicProject
		var userId = 1;
		var contentId = 9;
		var contentExplanation = "test";
		
		service.updateExplanation(userId, contentId, contentExplanation);
	}

	public void updateExplanation_4() {
		var userId = 1;
		var contentId = 500;
		var contentExplanation = "test";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateExplanation(userId, contentId, contentExplanation));
	}
	
	@Transactional 
	@Test
	public void updateExplanation_5() {//publicProject
		var userId = 1;
		var contentId = 5;
		var contentExplanation = "test";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateExplanation(userId, contentId, contentExplanation));
	}
	
	@Transactional 
	@Test
	public void updateExplanation_6() {
		var userId = 2;
		var contentId = 9;
		var contentExplanation = "test";
		
		assertThrows(BadRequestException.class ,
				() -> service.updateExplanation(userId, contentId, contentExplanation));
	}
	
	@Transactional 
	@Test
	public void updateExplanation_7() {//privateProject
		var userId = 2;
		var contentId = 1;
		var contentExplanation = "test";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateExplanation(userId, contentId, contentExplanation));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/delete_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_1() 
			throws BadRequestException, NotFoundValueException,
			NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {//todpOnProjet in publicProject 権限者
		var userId = 2;
		var contentId = 5;
		
		service.delete(userId, contentId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/delete_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_2()
			throws BadRequestException, NotFoundValueException,
			NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {//todpOnProjet in privateProject
		var userId = 1;
		var contentId = 10;
		
		service.delete(userId, contentId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/delete_3.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_3() 
			throws BadRequestException, NotFoundValueException, 
			NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {//todpOnResponsible in publicProject
		var userId = 1;
		var contentId = 9;
		
		service.delete(userId, contentId);
	}
	
	@Transactional 
	@Test
	public void delete_4() {
		var userId = 1;
		var contentId = 500;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, contentId));
	}
	
	@Transactional 
	@Test
	public void delete_5() {//publicProject
		var userId = 1;
		var contentId = 4;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.delete(userId, contentId));
	}
	
	@Transactional 
	@Test
	public void delete_6() {
		var userId = 2;
		var contentId = 9;
		
		assertThrows(BadRequestException.class ,
				() -> service.delete(userId, contentId));
	}
	
	@Transactional 
	@Test
	public void delete_7() {//privateProject
		var userId = 2;
		var contentId = 1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, contentId));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/setIsCompleted.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_1() 
			throws BadRequestException, NotFoundValueException, 
			NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {//todpOnProjet in publicProject 権限者
		var userId = 2;
		var contentId = 5;
		var isCompleted = true;
		
		service.setIsCompleted(userId, contentId, isCompleted);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/setIsCompleted_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_2() 
			throws BadRequestException, NotFoundValueException,
			NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {//todpOnProjet in privateProject
		var userId = 1;
		var contentId = 10;
		var isCompleted = true;
		
		service.setIsCompleted(userId, contentId, isCompleted);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/setIsCompleted_3.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_3() 
			throws BadRequestException, NotFoundValueException, 
			NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {//todpOnResponsible in publicProject
		var userId = 1;
		var contentId = 9;
		var isCompleted = true;
		
		service.setIsCompleted(userId, contentId, isCompleted);
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_4() {
		var userId = 1;
		var contentId = 500;
		var isCompleted = true;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.setIsCompleted(userId, contentId, isCompleted));
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_5() {//publicProject
		var userId = 1;
		var contentId = 5;
		var isCompleted = true;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.setIsCompleted(userId, contentId, isCompleted));
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_6() {
		var userId = 2;
		var contentId = 9;
		var isCompleted = true;
		
		assertThrows(BadRequestException.class ,
				() -> service.setIsCompleted(userId, contentId, isCompleted));
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_7() {//privateProject
		var userId = 2;
		var contentId = 1;
		var isCompleted = true;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.setIsCompleted(userId, contentId, isCompleted));
	}
}
