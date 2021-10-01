package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.NotFoundValueException;
import com.example.demo.response.PrivateProjectResponse;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/PrivateProjectServiceTest/pettern.xml")
public class PrivateProjectServiceTest {
	@Autowired
	PrivateProjectService service;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PrivateProjectServiceTest/expect/insert_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_1() {
		var userId = -1;
		var projectId = -1;
		var projectName = "";
		
		assertThat(service.insert(userId, projectName)).isEqualTo(projectId);
	}
	
	@Transactional 
	@Test
	public void getList_1() {
		var userId = -1;
		List<PrivateProjectResponse> expectList = new ArrayList<>();
		var expect = new PrivateProjectResponse();
		//TODO
		
		assertThat(service.getList(userId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void get_1() {
		var userId = -1;
		var privateProjectId = -1;
		var expect = new PrivateProjectResponse();
		//TODO
		
		assertThat(service.get(userId, privateProjectId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_2() {//プロジェクト
		var userId = -1;
		var privateProjectId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userId, privateProjectId));
	}
	
	@Transactional 
	@Test
	public void get_3() {//ユーザー
		var userId = -1;
		var privateProjectId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userId, privateProjectId));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PrivateProjectServiceTest/expect/updateProjectName_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateProjectName_1() {
		var userId = -1;
		var privateProjectId = -1;
		var projectName = "";
		
		service.updateProjectName(userId, privateProjectId, projectName);
	}
	
	@Transactional 
	@Test
	public void updateProjectName_2() {//プロジェクト
		var userId = -1;
		var privateProjectId = -1;
		var projectName = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateProjectName(userId, privateProjectId, projectName));
	}
	
	@Transactional 
	@Test
	public void updateProjectName_3() {//ユーザー
		var userId = -1;
		var privateProjectId = -1;
		var projectName = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateProjectName(userId, privateProjectId, projectName));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PrivateProjectServiceTest/expect/delete_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_1() {
		var userId = -1;
		var privateProjectId = -1;
		
		service.delete(userId, privateProjectId);
	}
	
	@Transactional 
	@Test
	public void delete_2() {//プロジェクト
		var userId = -1;
		var privateProjectId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, privateProjectId));
	}
	
	@Transactional 
	@Test
	public void delete_3() {//ユーザー
		var userId = -1;
		var privateProjectId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, privateProjectId));
	}
}