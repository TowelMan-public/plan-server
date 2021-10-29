package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.AlreadyUsedUserNameException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.form.UserForm;
import com.example.demo.response.UserResponse;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/UserServiceTest/pettern.xml")
@TestExecutionListeners({
	  DependencyInjectionTestExecutionListener.class,
	  DirtiesContextTestExecutionListener.class,
	  TransactionalTestExecutionListener.class,
	  DbUnitTestExecutionListener.class
	})
public class UserServiceTest {
	@Autowired
	UserService service;
	@Autowired
	OriginalUserDetailsService originalUserDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional 
	@Test
	public void get_1() throws NotFoundValueException {
		var userName = "tester1";
		var expect = new UserResponse();
		expect.setUserName("tester1");
		expect.setUserNickname("n1");
		
		assertThat(service.get(userName)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_2() {
		var userName = "tester4";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userName));
	}
	
	@Transactional 
	@Test
	public void get_3() {
		var userId = 1;
		var expect = new UserResponse();
		expect.setUserName("tester1");
		expect.setUserNickname("n1");
		
		assertThat(service.get(userId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserServiceTest/expect/delete_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_1() {
		var userId = 2;
		service.delete(userId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserServiceTest/expect/insert_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_1() throws NotFoundValueException, AlreadyUsedUserNameException {
		var form = new UserForm();
		form.setPassword("password");
		form.setUserName("tester_new");
		form.setUserNickName("new");
		
		service.insert(form);
		
		var entity1 = originalUserDetailsService.loadUserByUserName("tester_new");
		var entity2 = service.get(form.getUserName());
		assertThat(entity2.getUserNickname()).isEqualTo(form.getUserNickName());
		assertThat(passwordEncoder.matches("password", entity1.getPassword())).isTrue();
	}
	
	@Transactional 
	@Test
	public void insert_2() {
		var form = new UserForm();
		form.setPassword("password");
		form.setUserName("tester4");
		form.setUserNickName("new");
		
		assertThrows(AlreadyUsedUserNameException.class ,
				() -> service.insert(form));
	}
	
	@Transactional 
	@Test
	public void updatePassword_1() throws NotFoundValueException {
		var userId = 1;
		var password = "password";
		
		service.updatePassword(userId, password);
		var enitty = originalUserDetailsService.loadUserByUserName("tester1");	
		assertThat(passwordEncoder.matches("password", enitty.getPassword())).isTrue();
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserServiceTest/expect/updateUserNickName_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateUserNickName_1() {
		var userId = 1;
		var userNickName = "test";
		
		service.updateUserNickName(userId, userNickName);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserServiceTest/expect/updateUserName_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateUserName_1() throws AlreadyUsedUserNameException {
		var userId = 1;
		var userName = "tester1_new-ver";
		
		service.updateUserName(userId, userName);
	}
	
	@Transactional 
	@Test
	public void updateUserName_2() {
		var userId = 1;
		var userName = "tester3";
		
		assertThrows(AlreadyUsedUserNameException.class ,
				() -> service.updateUserName(userId, userName));
	}
}
