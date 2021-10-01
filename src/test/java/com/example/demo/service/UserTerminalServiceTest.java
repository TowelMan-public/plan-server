package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.AlreadyUsedTerminalNameException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.response.TerminalResponse;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/UserTerminalServiceTest/pettern.xml")
public class UserTerminalServiceTest {
	@Autowired
	UserTerminalService service;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserTerminalServiceTest/expect/insert_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_1() {
		var userId = -1;
		var terminalName = "";
		
		service.insert(userId, terminalName);
	}
	
	@Transactional 
	@Test
	public void insert_2() {
		var userId = -1;
		var terminalName = "";
		
		assertThrows(AlreadyUsedTerminalNameException.class ,
				() -> service.insert(userId, terminalName));
	}
	
	@Transactional 
	@Test
	public void getTerminalList_1() {
		var userId = -1;
		List<TerminalResponse> expectList = new ArrayList<>();
		var expect = new TerminalResponse();
		//TODO
		
		assertThat(service.getTerminalList(userId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserTerminalServiceTest/expect/updateTerminalName_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateTerminalName_1() {
		var userId = -1;
		var oldTerminalName = "";
		var newTerminalName = "";
		
		service.updateTerminalName(userId, oldTerminalName, newTerminalName);
	}
	
	@Transactional 
	@Test
	public void updateTerminalName_2() {
		var userId = -1;
		var oldTerminalName = "";
		var newTerminalName = "";
		
		assertThrows(AlreadyUsedTerminalNameException.class ,
				() -> service.updateTerminalName(userId, oldTerminalName, newTerminalName));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserTerminalServiceTest/expect/delete_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_1() {
		var userId = -1;
		var terminalName = "";
		
		service.delete(userId, terminalName);
	}
	
	@Transactional 
	@Test
	public void delete_2() {
		var userId = -1;
		var terminalName = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, terminalName));
	}
}
