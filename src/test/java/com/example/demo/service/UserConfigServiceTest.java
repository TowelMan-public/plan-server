package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.response.UserConfigResponse;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/UserConfigServiceTest/pettern.xml")
public class UserConfigServiceTest {
	@Autowired
	UserConfigService service;
	
	@Transactional 
	@Test
	public void get_1() {
		var userId = -1;		
		var expect = new UserConfigResponse();
		
		assertThat(service.get(userId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserConfigServiceTest/expect/updateBeforeDeadlineForProjectNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateBeforeDeadlineForProjectNotice_1() {
		var userId = -1;		
		var config = false;
		
		service.updateBeforeDeadlineForProjectNotice(userId, config);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserConfigServiceTest/expect/updateBeforeDeadlineForTodoNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateBeforeDeadlineForTodoNotice_1() {
		var userId = -1;		
		var config = false;
		
		service.updateBeforeDeadlineForTodoNotice(userId, config);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserConfigServiceTest/expect/updateIsPushSatrtedTodoNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateIsPushSatrtedTodoNotice_1() {
		var userId = -1;		
		var config = false;
		
		service.updateIsPushSatrtedTodoNotice(userId, config);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserConfigServiceTest/expect/updatePushInsertedTodoNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updatePushInsertedTodoNotice_1() {
		var userId = -1;		
		var config = false;
		
		service.updatePushInsertedTodoNotice(userId, config);
	}
}
