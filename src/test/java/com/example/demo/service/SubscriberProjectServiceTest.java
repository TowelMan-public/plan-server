package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.AlreadyJoinedPublicProjectException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.form.SubscriberInProjectForm;
import com.example.demo.response.SubscriberInPublicProjectResponse;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/SubscriberProjectServiceTest/pettern.xml")
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
		var userId = -1;
		var publicProjectId = -1;
		var form = new SubscriberInProjectForm();
		
		service.insert(userId, publicProjectId, form);
	}
	
	@Transactional 
	@Test
	public void isnert_2() {//project
		var userId = -1;
		var publicProjectId = -1;
		var form = new SubscriberInProjectForm();
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, publicProjectId, form));
	}
	
	@Transactional 
	@Test
	public void isnert_3() {
		var userId = -1;
		var publicProjectId = -1;
		var form = new SubscriberInProjectForm();
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.insert(userId, publicProjectId, form));
	}
	
	@Transactional 
	@Test
	public void isnert_4() {
		var userId = -1;
		var publicProjectId = -1;
		var form = new SubscriberInProjectForm();
		
		assertThrows(AlreadyJoinedPublicProjectException.class ,
				() -> service.insert(userId, publicProjectId, form));
	}
	
	@Transactional 
	@Test
	public void isnert_5() {//userName
		var userId = -1;
		var publicProjectId = -1;
		var form = new SubscriberInProjectForm();
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, publicProjectId, form));
	}

	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/SubscriberProjectServiceTest/expect/getList_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void getList_1() {
		var userId = -1;
		var publicProjectId = -1;
		List<SubscriberInPublicProjectResponse> expectList = new ArrayList<>();
		var expect = new SubscriberInPublicProjectResponse();
		
		assertThat(service.getList(userId, publicProjectId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getList_2() {
		var userId = -1;
		var publicProjectId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.getList(userId, publicProjectId));
	}
	
	@Transactional 
	@Test
	public void getList_3() {
		var userId = -1;
		var publicProjectId = -1;
		
		assertThrows(NotJoinedPublicProjectException.class ,
				() -> service.getList(userId, publicProjectId));
	}
	
	@Transactional 
	@Test
	public void getList_4() {
		var userId = -1;
		var publicProjectId = -1;
		
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
		var userId = -1;
		var publicProjectId = -1;
		var userName = "";
		var authorityId = -1;
		
		service.update(userId, publicProjectId, userName, authorityId);		
	}
	
	@Transactional 
	@Test
	public void update_2() {//project
		var userId = -1;
		var publicProjectId = -1;
		var userName = "";
		var authorityId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.update(userId, publicProjectId, userName, authorityId));	
	}
	
	@Transactional 
	@Test
	public void update_3() {//userName in project
		var userId = -1;
		var publicProjectId = -1;
		var userName = "";
		var authorityId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.update(userId, publicProjectId, userName, authorityId));	
	}
	
	@Transactional 
	@Test
	public void update_4() {
		var userId = -1;
		var publicProjectId = -1;
		var userName = "";
		var authorityId = -1;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.update(userId, publicProjectId, userName, authorityId));	
	}
	
	@Transactional 
	@Test
	public void update_5() {//MASTER権限系が一人もいなくなる
		var userId = -1;
		var publicProjectId = -1;
		var userName = "";
		var authorityId = -1;
		
		assertThrows(BadRequestException.class ,
				() -> service.update(userId, publicProjectId, userName, authorityId));	
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/SubscriberProjectServiceTest/expect/delete_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_1() {
		var userId = -1;
		var publicProjectId = -1;
		var userName = "";
		
		service.delete(userId, publicProjectId, userName);
	}
	
	@Transactional 
	@Test
	public void delete_2() {//project
		var userId = -1;
		var publicProjectId = -1;
		var userName = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, publicProjectId, userName));	
	}
	
	@Transactional 
	@Test
	public void delete_3() {//userName in project
		var userId = -1;
		var publicProjectId = -1;
		var userName = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, publicProjectId, userName));	
	}
	
	@Transactional 
	@Test
	public void delete_4() {
		var userId = -1;
		var publicProjectId = -1;
		var userName = "";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.delete(userId, publicProjectId, userName));	
	}

	@Transactional 
	@Test
	public void delete_5() {//MASTER権限系が一人もいなくなる
		var userId = -1;
		var publicProjectId = -1;
		var userName = "";
		
		assertThrows(BadRequestException.class ,
				() -> service.delete(userId, publicProjectId, userName));	
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/SubscriberProjectServiceTest/expect/accept_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void accept_1() {
		var userId = -1;
		var publicProjectId = -1;
		
		service.accept(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	public void accept_2() {
		var userId = -1;
		var publicProjectId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.accept(userId, publicProjectId));	
	}
	
	@Transactional 
	@Test
	public void accept_3() {//勧誘されている状況に置かれていない
		var userId = -1;
		var publicProjectId = -1;
		
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
		var userId = -1;
		var publicProjectId = -1;
		
		service.bloak(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	public void block_2() {
		var userId = -1;
		var publicProjectId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.bloak(userId, publicProjectId));	
	}
	
	@Transactional 
	@Test
	public void block_3() {//勧誘されている状況に置かれていない
		var userId = -1;
		var publicProjectId = -1;
		
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
		var userId = -1;
		var publicProjectId = -1;
		
		service.exit(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	public void exit_2() {
		var userId = -1;
		var publicProjectId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.exit(userId, publicProjectId));	
	}
	
	@Transactional 
	@Test
	public void exit_3() {
		var userId = -1;
		var publicProjectId = -1;
		
		assertThrows(NotJoinedPublicProjectException.class ,
				() -> service.exit(userId, publicProjectId));	
	}
}
