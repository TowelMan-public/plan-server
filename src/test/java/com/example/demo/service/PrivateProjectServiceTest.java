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
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.response.PrivateProjectResponse;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/PrivateProjectServiceTest/pettern.xml")
@TestExecutionListeners({
	  DependencyInjectionTestExecutionListener.class,
	  DirtiesContextTestExecutionListener.class,
	  TransactionalTestExecutionListener.class,
	  DbUnitTestExecutionListener.class
	})
public class PrivateProjectServiceTest extends DatabaseTest {
	@Autowired
	PrivateProjectService service;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PrivateProjectServiceTest/expect/insert_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_1() {
		var userId = 3;
		var projectId = 11;
		var projectName = "new_test";
		
		assertThat(service.insert(userId, projectName)).isEqualTo(projectId);
	}
	
	@Transactional 
	@Test
	public void getList_1() {
		var userId = 1;
		List<PrivateProjectResponse> expectList = new ArrayList<>();
		
		var expect = new PrivateProjectResponse();
		expect.setProjectId(2);
		expect.setProjectName("TEST_2");
		expectList.add(expect);
		
		expect = new PrivateProjectResponse();
		expect.setProjectId(3);
		expect.setProjectName("TEST_3");
		expectList.add(expect);
		
		assertThat(service.getList(userId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void get_1() throws NotFoundValueException {
		var userId = 2;
		var privateProjectId = 4;
		var expect = new PrivateProjectResponse();
		expect.setProjectId(privateProjectId);
		expect.setProjectName("TEST_4");
		
		assertThat(service.get(userId, privateProjectId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_2() {//プロジェクト
		var userId = 1;
		var privateProjectId = 1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userId, privateProjectId));
	}
	
	@Transactional 
	@Test
	public void get_3() {//ユーザー
		var userId = 1;
		var privateProjectId = 4;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userId, privateProjectId));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PrivateProjectServiceTest/expect/updateProjectName_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateProjectName_1() throws NotFoundValueException {
		var userId = 2;
		var privateProjectId = 4;
		var projectName = "new";
		
		service.updateProjectName(userId, privateProjectId, projectName);
	}
	
	@Transactional 
	@Test
	public void updateProjectName_2() {//プロジェクト
		var userId = 1;
		var privateProjectId = 1;
		var projectName = "project";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateProjectName(userId, privateProjectId, projectName));
	}
	
	@Transactional 
	@Test
	public void updateProjectName_3() {//ユーザー
		var userId = 1;
		var privateProjectId = 4;
		var projectName = "user";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateProjectName(userId, privateProjectId, projectName));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PrivateProjectServiceTest/expect/delete_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_1() throws NotFoundValueException {
		var userId = 2;
		var privateProjectId = 4;
		
		service.delete(userId, privateProjectId);
	}
	
	@Transactional 
	@Test
	public void delete_2() {//プロジェクト
		var userId = 1;
		var privateProjectId = 1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, privateProjectId));
	}
	
	@Transactional 
	@Test
	public void delete_3() {//ユーザー
		var userId = 1;
		var privateProjectId = 4;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, privateProjectId));
	}
}