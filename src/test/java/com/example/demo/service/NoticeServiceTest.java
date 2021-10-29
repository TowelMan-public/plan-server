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

import com.example.demo.exception.NotFoundValueException;
import com.example.demo.response.NoticeResponse;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/NoticeServiceTest/pettern.xml")
@TestExecutionListeners({
	  DependencyInjectionTestExecutionListener.class,
	  DirtiesContextTestExecutionListener.class,
	  TransactionalTestExecutionListener.class,
	  DbUnitTestExecutionListener.class
	})
public class NoticeServiceTest {
	@Autowired
	NoticeService service;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/NoticeServiceTest/expect/getUnsendedNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void getUnsendedNotice_1() throws NotFoundValueException {
		var userId = 1;
		var terminalName = "terminal1";
		List<NoticeResponse> expectList = new ArrayList<>();
		
		var expect = new NoticeResponse();
		expect.setId(2);
		expect.setMessage("TEST_Bの締め切りを過ぎました！！");
		expect.setNoticeType(NoticeResponse.PROJECT_NOTICE);
		expectList.add(expect);
		
		expect = new NoticeResponse();
		expect.setId(2);
		expect.setMessage("TEST_Bが完了しました。頑張りましたね！");
		expect.setNoticeType(NoticeResponse.TODO_NOTICE);
		expectList.add(expect);
				
		expect = new NoticeResponse();
		expect.setId(3);
		expect.setMessage("TEST_Cが完了しました！おめでとうございます！");
		expect.setNoticeType(NoticeResponse.PROJECT_NOTICE);
		expectList.add(expect);
		
		expect = new NoticeResponse();
		expect.setId(3);
		expect.setMessage("TEST_Cの締め切りが迫りました");
		expect.setNoticeType(NoticeResponse.TODO_NOTICE);
		expectList.add(expect);
		
		assertThat(service.getUnsendedNotice(userId, terminalName)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getUnsendedNotice_2() {
		var userId = 1;
		var terminalName = "nothing_year!";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.getUnsendedNotice(userId, terminalName));
	}
	
	@Transactional 
	@Test
	public void getNotice_1() {
		var userId = 1;
		List<NoticeResponse> expectList = new ArrayList<>();
		
		var expect = new NoticeResponse();
		expect.setId(1);
		expect.setMessage("TEST_Aの締め切りが迫りました");
		expect.setNoticeType(NoticeResponse.PROJECT_NOTICE);
		expectList.add(expect);
		
		expect = new NoticeResponse();
		expect.setId(1);
		expect.setMessage("TEST_Aの締め切りが迫りました");
		expect.setNoticeType(NoticeResponse.TODO_NOTICE);
		expectList.add(expect);
				
		expect = new NoticeResponse();
		expect.setId(2);
		expect.setMessage("TEST_Bの締め切りを過ぎました！！");
		expect.setNoticeType(NoticeResponse.PROJECT_NOTICE);
		expectList.add(expect);
		
		expect = new NoticeResponse();
		expect.setId(2);
		expect.setMessage("TEST_Bが完了しました。頑張りましたね！");
		expect.setNoticeType(NoticeResponse.TODO_NOTICE);
		expectList.add(expect);
				
		expect = new NoticeResponse();
		expect.setId(3);
		expect.setMessage("TEST_Cが完了しました！おめでとうございます！");
		expect.setNoticeType(NoticeResponse.PROJECT_NOTICE);
		expectList.add(expect);
		
		expect = new NoticeResponse();
		expect.setId(3);
		expect.setMessage("TEST_Cの締め切りが迫りました");
		expect.setNoticeType(NoticeResponse.TODO_NOTICE);
		expectList.add(expect);
		
		assertThat(service.getNotice(userId)).containsExactlyElementsOf(expectList);
	}
}
