package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.response.UserConfigResponse;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestExecutionListeners({
	  DependencyInjectionTestExecutionListener.class,
	  DirtiesContextTestExecutionListener.class,
	  TransactionalTestExecutionListener.class,
	  DbUnitTestExecutionListener.class
	})
@DatabaseSetup("/dbunit/service/UserConfigServiceTest/pettern.xml")
public class UserConfigServiceTest {
	@Autowired
	UserConfigService service;
	
	@Transactional 
	@Test
	public void get_1() {
		var userId = 1;		
		var expect = new UserConfigResponse();
		expect.setBeforeDeadlineForProjectNotice(0);
		expect.setBeforeDeadlineForTodoNotice(0);
		expect.setIsPushInsertedStartedTodoNotice(false);
		expect.setIsPushInsertedTodoNotice(false);
		
		assertThat(service.get(userId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
			value="/dbunit/service/UserConfigServiceTest/expect/updateBeforeDeadlineForProjectNotice_1.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateBeforeDeadlineForProjectNotice_1() {
		var userId = 1;		
		var config = 500;
		
		service.updateBeforeDeadlineForProjectNotice(userId, config);		
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserConfigServiceTest/expect/updateBeforeDeadlineForTodoNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateBeforeDeadlineForTodoNotice_1() {
		var userId = 1;		
		var config = 500;
		
		service.updateBeforeDeadlineForTodoNotice(userId, config);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserConfigServiceTest/expect/updateIsPushSatrtedTodoNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateIsPushSatrtedTodoNotice_1() {
		var userId = 1;		
		var config = true;
		
		service.updateIsPushSatrtedTodoNotice(userId, config);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserConfigServiceTest/expect/updatePushInsertedTodoNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updatePushInsertedTodoNotice_1() {
		var userId = 1;		
		var config = true;
		
		service.updatePushInsertedTodoNotice(userId, config);
	}
}
