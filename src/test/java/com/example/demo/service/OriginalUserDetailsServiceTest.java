package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.NotFoundValueException;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@SpringBootTest
@DatabaseSetup("/dbunit/service/OriginalUserDetailsServiceTest/pettern.xml")
public class OriginalUserDetailsServiceTest {
	@Autowired
	OriginalUserDetailsService service;
	
	@Transactional 
	@Test
	public void loadUserByUserName_1() throws NotFoundValueException {
		var userName = "";
		var userId = -1;
		
		var userDetails = service.loadUserByUserName(userName);
		assertThat(userDetails.getUserId()).isEqualTo(userId);
		assertThat(userDetails.getUsername()).isEqualTo(userName);
	}
	
	@Transactional 
	@Test
	public void loadUserByUserName_2() throws NotFoundValueException {
		var userName = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.loadUserByUserName(userName));
	}
	
	@Transactional 
	@Test
	public void loadUserByUserId_1() throws NotFoundValueException {
		var userName = "";
		var userId = -1;
		
		var userDetails = service.loadUserByUserId(userId);
		assertThat(userDetails.getUserId()).isEqualTo(userId);
		assertThat(userDetails.getUsername()).isEqualTo(userName);
	}
	
	@Transactional 
	@Test
	public void loadUserByUserId_2() throws NotFoundValueException {
		var userId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.loadUserByUserId(userId));
	}
}
