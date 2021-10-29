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

import com.example.demo.exception.AlreadyUsedTerminalNameException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.response.TerminalResponse;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/UserTerminalServiceTest/pettern.xml")
@TestExecutionListeners({
	  DependencyInjectionTestExecutionListener.class,
	  DirtiesContextTestExecutionListener.class,
	  TransactionalTestExecutionListener.class,
	  DbUnitTestExecutionListener.class
	})
public class UserTerminalServiceTest {
	@Autowired
	UserTerminalService service;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserTerminalServiceTest/expect/insert_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_1() throws AlreadyUsedTerminalNameException {
		var userId = 1;
		var terminalName = "new_terminal";
		
		service.insert(userId, terminalName);
	}
	
	@Transactional 
	@Test
	public void insert_2() {
		var userId = 1;
		var terminalName = "terminal1";
		
		assertThrows(AlreadyUsedTerminalNameException.class ,
				() -> service.insert(userId, terminalName));
	}
	
	@Transactional 
	@Test
	public void getTerminalList_1() {
		var userId = 1;
		List<TerminalResponse> expectList = new ArrayList<>();
		var expect = new TerminalResponse();
		expect.setTerminalName("terminal1");
		expectList.add(expect);
		
		expect = new TerminalResponse();
		expect.setTerminalName("terminal2");
		expectList.add(expect);
		
		expect = new TerminalResponse();
		expect.setTerminalName("terminal3");
		expectList.add(expect);
		
		assertThat(service.getTerminalList(userId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserTerminalServiceTest/expect/updateTerminalName_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateTerminalName_1() throws AlreadyUsedTerminalNameException, NotFoundValueException {
		var userId = 1;
		var oldTerminalName = "terminal1";
		var newTerminalName = "terminal1_newwer";
		
		service.updateTerminalName(userId, oldTerminalName, newTerminalName);
	}
	
	@Transactional 
	@Test
	public void updateTerminalName_2() {
		var userId = 1;
		var oldTerminalName = "terminal1";
		var newTerminalName = "terminal2";
		
		assertThrows(AlreadyUsedTerminalNameException.class ,
				() -> service.updateTerminalName(userId, oldTerminalName, newTerminalName));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/UserTerminalServiceTest/expect/delete_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_1() throws NotFoundValueException {
		var userId = 1;
		var terminalName = "terminal1";
		
		service.delete(userId, terminalName);
	}
	
	@Transactional 
	@Test
	public void delete_2() {
		var userId = 1;
		var terminalName = "terminal72";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, terminalName));
	}
}
