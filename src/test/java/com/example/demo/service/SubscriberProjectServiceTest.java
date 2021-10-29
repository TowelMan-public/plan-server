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

import com.example.demo.configurer.AuthorityListInPublicProject;
import com.example.demo.exception.AlreadyJoinedPublicProjectException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.form.SubscriberInProjectForm;
import com.example.demo.response.SubscriberInPublicProjectResponse;
import com.example.demo.utility.CommonUtility;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/SubscriberProjectServiceTest/pettern.xml")
@TestExecutionListeners({
	  DependencyInjectionTestExecutionListener.class,
	  DirtiesContextTestExecutionListener.class,
	  TransactionalTestExecutionListener.class,
	  DbUnitTestExecutionListener.class
	})
public class SubscriberProjectServiceTest {
	@Autowired
	SubscriberProjectService service;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/SubscriberProjectServiceTest/expect/insert_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_1() {
		var userId = 1;
		var publicProjectId = 5;
		var userName = "tester2";
		
		service.insert(userId, publicProjectId, userName);
	}
	
	@Transactional 
	@Test
	public void isnert_2() {//project
		var userId = -1;
		var publicProjectId = 500;
		var userName = "tester2";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, publicProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void isnert_3() {
		var userId = 3;
		var publicProjectId = 7;
		var userName = "tester2";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.insert(userId, publicProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void isnert_4() {
		var userId = 2;
		var publicProjectId = 6;
		var userName = "tester3";
		
		assertThrows(AlreadyJoinedPublicProjectException.class ,
				() -> service.insert(userId, publicProjectId, userName));
	}
	
	@Transactional 
	@Test
	public void isnert_5() {//userName
		var userId = 1;
		var publicProjectId = 5;
		var userName = "nothing";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, publicProjectId, userName));
	}

	@Transactional 
	@Test
	public void getList_1() {
		var userId = 3;
		var publicProjectId = 6;
		List<SubscriberInPublicProjectResponse> expectList = new ArrayList<>();
		var expect = new SubscriberInPublicProjectResponse();
		expect.setPublicProjectId(6);
		expect.setUserName("tester1");
		expect.setAuthorityId(AuthorityListInPublicProject.NORMAL);
		
		expect = new SubscriberInPublicProjectResponse();
		expect.setPublicProjectId(6);
		expect.setUserName("tester2");
		expect.setAuthorityId(AuthorityListInPublicProject.SUPER);
		
		expect = new SubscriberInPublicProjectResponse();
		expect.setPublicProjectId(6);
		expect.setUserName("tester3");
		expect.setAuthorityId(AuthorityListInPublicProject.TENTATIVE);
		
		assertThat(service.getList(userId, publicProjectId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getList_2() {
		var userId = 2;
		var publicProjectId = 2;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.getList(userId, publicProjectId));
	}
	
	@Transactional 
	@Test
	public void getList_3() {
		var userId = 3;
		var publicProjectId = 5;
		
		assertThrows(NotJoinedPublicProjectException.class ,
				() -> service.getList(userId, publicProjectId));
	}
	
	@Transactional 
	@Test
	public void getList_4() {
		var userId = 3;
		var publicProjectId = 500;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.getList(userId, publicProjectId));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/SubscriberProjectServiceTest/expect/update_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void update_1() {
		var userId = 2;
		var publicProjectId = 6;
		var userName = "tester1";
		var authorityId = AuthorityListInPublicProject.SUPER;
		
		service.update(userId, publicProjectId, userName, authorityId);		
	}
	
	@Transactional 
	@Test
	public void update_2() {//project
		var userId = 1;
		var publicProjectId = 4;
		var userName = "tester2";
		var authorityId = AuthorityListInPublicProject.SUPER;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.update(userId, publicProjectId, userName, authorityId));	
	}
	
	@Transactional 
	@Test
	public void update_3() {//userName in project
		var userId = 2;
		var publicProjectId = 6;
		var userName = "tester4";
		var authorityId = AuthorityListInPublicProject.SUPER;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.update(userId, publicProjectId, userName, authorityId));	
	}
	
	@Transactional 
	@Test
	public void update_4() {
		var userId = 1;
		var publicProjectId = 6;
		var userName = "tester1";
		var authorityId = AuthorityListInPublicProject.SUPER;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.update(userId, publicProjectId, userName, authorityId));	
	}
	
	@Transactional 
	@Test
	public void update_5() {//MASTER権限系が一人もいなくなる
		var userId = 2;
		var publicProjectId = 6;
		var userName = "tester2";
		var authorityId = AuthorityListInPublicProject.NORMAL;
		
		assertThrows(BadRequestException.class ,
				() -> service.update(userId, publicProjectId, userName, authorityId));	
	}
	
	@Transactional 
	@Test
	public void update_6() {//勧誘中である。よって変更不可、削除可能
		var userId = 2;
		var publicProjectId = 6;
		var userName = "tester3";
		var authorityId = AuthorityListInPublicProject.NORMAL;
		
		assertThrows(BadRequestException.class ,
				() -> service.update(userId, publicProjectId, userName, authorityId));	
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/SubscriberProjectServiceTest/expect/delete_1.xml"
	)
	public void delete_1() {
		var userId = 2;
		var publicProjectId = 6;
		var userName = "tester3";
		
		service.delete(userId, publicProjectId, userName);
	}
	
	@Transactional 
	@Test
	public void delete_2() {//project
		var userId = 1;
		var publicProjectId = 400;
		var userName = "tester3";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, publicProjectId, userName));	
	}
	
	@Transactional 
	@Test
	public void delete_3() {//userName in project
		var userId = 2;
		var publicProjectId = 6;
		var userName = "tester_nothing";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, publicProjectId, userName));	
	}
	
	@Transactional 
	@Test
	public void delete_4() {
		var userId = 1;
		var publicProjectId = 6;
		var userName = "tester3";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.delete(userId, publicProjectId, userName));	
	}

	@Transactional 
	@Test
	public void delete_5() {//自分を消そうとしている
		var userId = 2;
		var publicProjectId = 6;
		var userName = "tester2";
		
		assertThrows(BadRequestException.class ,
				() -> service.delete(userId, publicProjectId, userName));	
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/SubscriberProjectServiceTest/expect/accept_1.xml"
	)
	public void accept_1() {
		var userId = 3;
		var publicProjectId = 6;
		
		service.accept(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	public void accept_2() {
		var userId = 3;
		var publicProjectId = 2;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.accept(userId, publicProjectId));	
	}
	
	@Transactional 
	@Test
	public void accept_3() {//勧誘されている状況に置かれていない
		var userId = 3;
		var publicProjectId = 5;
		
		assertThrows(BadRequestException.class ,
				() -> service.accept(userId, publicProjectId));	
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/SubscriberProjectServiceTest/expect/block_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void block_1() {
		var userId = 3;
		var publicProjectId = 6;
		
		service.bloak(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	public void block_2() {
		var userId = 3;
		var publicProjectId = 2;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.bloak(userId, publicProjectId));	
	}
	
	@Transactional 
	@Test
	public void block_3() {//勧誘されている状況に置かれていない
		var userId = 3;
		var publicProjectId = 5;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.bloak(userId, publicProjectId));	
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/SubscriberProjectServiceTest/expect/exit_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void exit_1() {
		var userId = 1;
		var publicProjectId = 6;
		
		service.exit(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	public void exit_2() {
		var userId = 3;
		var publicProjectId = 500;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.exit(userId, publicProjectId));	
	}
	
	@Transactional 
	@Test
	public void exit_3() {
		var userId = 3;
		var publicProjectId = 6;
		
		assertThrows(NotJoinedPublicProjectException.class ,
				() -> service.exit(userId, publicProjectId));	
	}
	
	@Transactional 
	@Test
	public void exit_4() {//MASTER権限者がいなくなる
		var userId = 2;
		var publicProjectId = 6;
		
		assertThrows(BadRequestException.class ,
				() -> service.exit(userId, publicProjectId));	
	}
}
