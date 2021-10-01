package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserEntity;
import com.example.demo.exception.AlreadyUsedUserNameException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.form.UserForm;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/UserServiceTest/pettern.xml")
public class UserServiceTest {
	@Autowired
	UserService service;
	
	@Transactional 
	@Test
	public void get_1() {
		var userName = "";
		var expect = new UserEntity();
		//TODO
		
		assertThat(service.get(userName)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_2() {
		var userName = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userName));
	}
	
	@Transactional 
	@Test
	public void get_3() {
		var userId = -1;
		var expect = new UserEntity();
		//TODO
		
		assertThat(service.get(userId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_4() {
		var userId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userId));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserServiceTest/expect/delete_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_1() {
		var userId = -1;
		service.delete(userId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserServiceTest/expect/insert_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_1() {
		var form = new UserForm();
		//TODO 
		
		service.insert(form);
	}
	
	@Transactional 
	@Test
	public void insert_2() {
		var form = new UserForm();
		//TODO 
		
		assertThrows(AlreadyUsedUserNameException.class ,
				() -> service.insert(form));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserServiceTest/expect/updatePassword_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updatePassword_1() {
		var userId = -1;
		var password = "";
		
		service.updatePassword(userId, password);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserServiceTest/expect/updateUserNickName_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateUserNickName_1() {
		var userId = -1;
		var userNickName = "";
		
		service.updateUserNickName(userId, userNickName);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserServiceTest/expect/updateUserName_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateUserName_1() {
		var userId = -1;
		var userName = "";
		
		service.updateUserName(userId, userName);
	}
	
	@Transactional 
	@Test
	public void updateUserName_2() {
		var userId = -1;
		var userName = "";
		
		assertThrows(AlreadyUsedUserNameException.class ,
				() -> service.updateUserName(userId, userName));
	}
}
