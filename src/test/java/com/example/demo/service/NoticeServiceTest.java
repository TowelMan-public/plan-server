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
import com.example.demo.response.NoticeResponse;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/UserTerminalServiceTest/pettern.xml")
public class NoticeServiceTest {
	@Autowired
	NoticeService service;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/NoticeServiceTest/expect/getUnacquiredNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void getUnacquiredNotice_1() {
		var userId = -1;
		var terminalName = "";
		List<NoticeResponse> expectList = new ArrayList<>();
		var expect = new NoticeResponse();
		//TODO
		
		assertThat(service.getUnacquiredNotice(userId, terminalName)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getUnacquiredNotice_2() {
		var userId = -1;
		var terminalName = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.getUnacquiredNotice(userId, terminalName));
	}
	
	@Transactional 
	@Test
	public void getNotice_1() {
		var userId = -1;
		List<NoticeResponse> expectList = new ArrayList<>();
		var expect = new NoticeResponse();
		//TODO
		
		assertThat(service.getNotice(userId)).containsExactlyElementsOf(expectList);
	}
}
