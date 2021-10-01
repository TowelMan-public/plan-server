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
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.form.ProjectForm;
import com.example.demo.response.PublicProjectResponse;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/PublicProjectServiceTest/pettern.xml")
public class PublicProjectServiceTest {
	@Autowired
	PublicProjectService service;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PublicProjectServiceTest/expect/insert_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_1() {
		var userId = -1;
		var publicProjectId = -1;
		var form = new ProjectForm();
		//TODO
		
		assertThat(service.insert(userId, form)).isEqualTo(publicProjectId);
	}
	
	@Transactional 
	@Test
	public void get_1() {
		var userId = -1;
		var publicProjectId = -1;
		var expect = new PublicProjectResponse();
		
		assertThat(service.get(userId, publicProjectId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_2() {
		var userId = -1;
		var publicProjectId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userId, publicProjectId));
	}
	
	@Transactional 
	@Test
	public void get_3() {
		var userId = -1;
		var publicProjectId = -1;
		
		assertThrows(NotJoinedPublicProjectException.class ,
				() -> service.get(userId, publicProjectId));
	}
	
	@Transactional 
	@Test
	public void getList_1() {
		var userId = -1;
		List<PublicProjectResponse> expectList = new ArrayList<>();
		var expect = new PublicProjectResponse();
		//TODO
		
		assertThat(service.getList(userId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PublicProjectServiceTest/expect/updateProjectName_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateProjectName_1() {
		var userId = -1;
		var publicProjectId = -1;
		var projectName = "";
		
		service.updateProjectName(userId, publicProjectId, projectName);
	}
	
	@Transactional 
	@Test
	public void updateProjectName_2() {
		var userId = -1;
		var publicProjectId = -1;
		var projectName = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateProjectName(userId, publicProjectId, projectName));
	}
	
	@Transactional 
	@Test
	public void updateProjectName_3() {//加入なし
		var userId = -1;
		var publicProjectId = -1;
		var projectName = "";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateProjectName(userId, publicProjectId, projectName));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PublicProjectServiceTest/expect/updateFinishDate_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateFinishDate_1() {
		var userId = -1;
		var publicProjectId = -1;
		var finishDate = new Date();
		
		service.updateFinishDate(userId, publicProjectId, finishDate);
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_2() {
		var userId = -1;
		var publicProjectId = -1;
		var finishDate = new Date();
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateFinishDate(userId, publicProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_3() {//権限レベル
		var userId = -1;
		var publicProjectId = -1;
		var finishDate = new Date();
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateFinishDate(userId, publicProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_4() {
		var userId = -1;
		var publicProjectId = -1;
		var finishDate = new Date();
		
		assertThrows(BadRequestException.class ,
				() -> service.updateFinishDate(userId, publicProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PublicProjectServiceTest/expect/updateStartDate_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateStartDate_1() {
		var userId = -1;
		var publicProjectId = -1;
		var startDate = new Date();
		
		service.updateStartDate(userId, publicProjectId, startDate);
	}
	
	@Transactional 
	@Test
	public void updateStartDate_2() {
		var userId = -1;
		var publicProjectId = -1;
		var startDate = new Date();
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateStartDate(userId, publicProjectId, startDate));
	}
	
	@Transactional 
	@Test
	public void updateStartDate_3() {
		var userId = -1;
		var publicProjectId = -1;
		var startDate = new Date();
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateStartDate(userId, publicProjectId, startDate));
	}
	
	@Transactional 
	@Test
	public void updateStartDate_4() {
		var userId = -1;
		var publicProjectId = -1;
		var startDate = new Date();
		
		assertThrows(BadRequestException.class ,
				() -> service.updateStartDate(userId, publicProjectId, startDate));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PublicProjectServiceTest/expect/delete_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_1() {
		var userId = -1;
		var publicProjectId = -1;
		
		service.delete(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	public void delete_2() {
		var userId = -1;
		var publicProjectId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, publicProjectId));
	}
	
	@Transactional 
	@Test
	public void delete_3() {
		var userId = -1;
		var publicProjectId = -1;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.delete(userId, publicProjectId));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PublicProjectServiceTest/expect/setIsCompleted_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_1() {
		var userId = -1;
		var publicProjectId = -1;
		var isCompleted = true;
		
		service.setIsCompleted(userId, publicProjectId, isCompleted);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PublicProjectServiceTest/expect/setIsCompleted_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_2() {
		var userId = -1;
		var publicProjectId = -1;
		var isCompleted = false;
		
		service.setIsCompleted(userId, publicProjectId, isCompleted);
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_3() {
		var userId = -1;
		var publicProjectId = -1;
		var isCompleted = true;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.setIsCompleted(userId, publicProjectId, isCompleted));
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_4() {
		var userId = -1;
		var publicProjectId = -1;
		var isCompleted = true;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.setIsCompleted(userId, publicProjectId, isCompleted));
	}
}
