package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.configurer.AuthorityListInPublicProject;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.form.ProjectForm;
import com.example.demo.response.PublicProjectResponse;
import com.example.demo.utility.CommonUtility;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/PublicProjectServiceTest/pettern.xml")
@TestExecutionListeners({
	  DependencyInjectionTestExecutionListener.class,
	  DirtiesContextTestExecutionListener.class,
	  TransactionalTestExecutionListener.class,
	  DbUnitTestExecutionListener.class
	})
public class PublicProjectServiceTest {
	@Autowired
	PublicProjectService service;
	@Autowired
	CommonUtility utillity;
		
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/PublicProjectServiceTest/expect/insert_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_1() {
		var userId = 3;
		var publicProjectId = 11;
		var form = new ProjectForm();
		form.setProjectName("new_public");
		form.setStartDate(utillity.stringToDate("2021-9-1"));
		form.setFinishDate(utillity.stringToDate("2021-12-1"));
		
		assertThat(service.insert(userId, form)).isEqualTo(publicProjectId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(value="/dbunit/service/PublicProjectServiceTest/expect/get_1.xml")
	public void get_1() {
		var userId = 1;
		var publicProjectId = 5;
		var expect = new PublicProjectResponse();
		expect.setProjectName("TEST_1");
		expect.setPublicProjectId(5);
		expect.setStartDate(utillity.stringToDate("2021-10-1"));
		expect.setFinishDate(utillity.stringToDate("2021-11-1"));
		expect.setIsCompleted(false);
		expect.setProjectAuthority(AuthorityListInPublicProject.SUPER);
		
		assertThat(service.get(userId, publicProjectId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_2() {
		var userId = 1;
		var publicProjectId = 100;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userId, publicProjectId));
	}
	
	@Transactional 
	@Test
	public void get_3() {
		var userId = 2;
		var publicProjectId = 5;
		
		assertThrows(NotJoinedPublicProjectException.class ,
				() -> service.get(userId, publicProjectId));
	}
	
	@Transactional 
	@Test
	public void getList_1() {
		var userId = 3;
		List<PublicProjectResponse> expectList = new ArrayList<>();
		var expect = new PublicProjectResponse();
		expect.setProjectName("TEST_3");
		expect.setPublicProjectId(7);
		expect.setStartDate(utillity.stringToDate("2021-8-1"));
		expect.setFinishDate(utillity.stringToDate("2021-8-30"));
		expect.setIsCompleted(false);
		expect.setProjectAuthority(AuthorityListInPublicProject.NORMAL);
		expectList.add(expect);
		
		assertThat(service.getList(userId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase( value="/dbunit/service/PublicProjectServiceTest/expect/updateProjectName_1.xml"	)
	public void updateProjectName_1() {
		var userId = 1;
		var publicProjectId = 5;
		var projectName = "newer_project";
		
		service.updateProjectName(userId, publicProjectId, projectName);
	}
	
	@Transactional 
	@Test
	public void updateProjectName_2() {
		var userId = 3;
		var publicProjectId = 880;
		var projectName = "test";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateProjectName(userId, publicProjectId, projectName));
	}
	
	@Transactional 
	@Test
	public void updateProjectName_3() {//加入なし
		var userId = 3;
		var publicProjectId = 5;
		var projectName = "test";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateProjectName(userId, publicProjectId, projectName));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase( value="/dbunit/service/PublicProjectServiceTest/expect/updateFinishDate_1.xml")
	public void updateFinishDate_1() {
		var userId = 1;
		var publicProjectId = 5;
		var finishDate = utillity.stringToDate("2021-10-25");
		
		service.updateFinishDate(userId, publicProjectId, finishDate);
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_2() {
		var userId = 3;
		var publicProjectId = 5;
		var finishDate = utillity.stringToDate("2021-8-1");
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateFinishDate(userId, publicProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_3() {//権限レベル
		var userId = 1;
		var publicProjectId = 6;
		var finishDate = utillity.stringToDate("2021-8-1");
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateFinishDate(userId, publicProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	public void updateFinishDate_4() {
		var userId = 1;
		var publicProjectId = 5;
		var finishDate = utillity.stringToDate("2021-8-1");
		
		assertThrows(BadRequestException.class ,
				() -> service.updateFinishDate(userId, publicProjectId, finishDate));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(value="/dbunit/service/PublicProjectServiceTest/expect/updateStartDate_1.xml")
	public void updateStartDate_1() {
		var userId = 1;
		var publicProjectId = 5;
		var startDate = utillity.stringToDate("2021-8-1");
		
		service.updateStartDate(userId, publicProjectId, startDate);
	}
	
	@Transactional 
	@Test
	public void updateStartDate_2() {
		var userId = 3;
		var publicProjectId = 1000;
		var startDate = utillity.stringToDate("2021-8-1");
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateStartDate(userId, publicProjectId, startDate));
	}
	
	@Transactional 
	@Test
	public void updateStartDate_3() {
		var userId = 1;
		var publicProjectId = 6;
		var startDate = utillity.stringToDate("2021-8-1");
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateStartDate(userId, publicProjectId, startDate));
	}
	
	@Transactional 
	@Test
	public void updateStartDate_4() {
		var userId = 1;
		var publicProjectId = 5;
		var startDate = utillity.stringToDate("2021-12-1");
		
		assertThrows(BadRequestException.class ,
				() -> service.updateStartDate(userId, publicProjectId, startDate));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(value="/dbunit/service/PublicProjectServiceTest/expect/delete_1.xml")
	public void delete_1() {
		var userId = 1;
		var publicProjectId = 5;
		
		service.delete(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	public void delete_2() {
		var userId = 2;
		var publicProjectId = 721;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, publicProjectId));
	}
	
	@Transactional 
	@Test
	public void delete_3() {
		var userId = 1;
		var publicProjectId = 6;
		
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
		var userId = 1;
		var publicProjectId = 5;
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
		var userId = 2;
		var publicProjectId = 9;
		var isCompleted = false;
		
		service.setIsCompleted(userId, publicProjectId, isCompleted);
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_3() {
		var userId = 1;
		var publicProjectId = 500;
		var isCompleted = true;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.setIsCompleted(userId, publicProjectId, isCompleted));
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_4() {
		var userId = 1;
		var publicProjectId = 6;
		var isCompleted = true;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.setIsCompleted(userId, publicProjectId, isCompleted));
	}
	
	@Transactional 
	@Test
	public void getSolicited_1() {
		var userId = 3;
		List<PublicProjectResponse> expectList = new ArrayList<>();
		var expect = new PublicProjectResponse();
		expect.setProjectName("TEST_2");
		expect.setPublicProjectId(6);
		expect.setStartDate(utillity.stringToDate("2021-9-1"));
		expect.setFinishDate(utillity.stringToDate("2021-11-1"));
		expect.setIsCompleted(false);
		expect.setProjectAuthority(AuthorityListInPublicProject.TENTATIVE);
		expectList.add(expect);
		
		assertThat(service.getSolicited(userId)).containsExactlyElementsOf(expectList);
	}
}
