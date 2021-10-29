package com.example.demo.logic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.utility.CommonUtility;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/logic/NoticeLogicTest/pettern.xml")
public class NoticeLogicTest {	
	@Autowired
	NoticeLogic logic;
	@Autowired
	CommonUtility utillity;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createApproachingPeriodTodoNoticeAuto_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createApproachingPeriodTodoNoticeAuto_1() {
		var lastRunDate = utillity.stringToDate("2021-9-14");
		var nowRunDate = utillity.stringToDate("2021-9-20");
		
		logic.createApproachingPeriodTodoNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createApproachingPeriodTodoNoticeAuto_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createApproachingPeriodTodoNoticeAuto_2() {
		var lastRunDate = utillity.stringToDate("2021-7-5");
		var nowRunDate = utillity.stringToDate("2021-7-30");
				
		logic.createApproachingPeriodTodoNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createDelayApproachingTodoNoticeAuto_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createDelayApproachingTodoNoticeAuto_1() {
		var lastRunDate = utillity.stringToDate("2021-9-17");
		var nowRunDate = utillity.stringToDate("2021-9-21");
		
		logic.createDelayApproachingTodoNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createDelayApproachingTodoNoticeAuto_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createDelayApproachingTodoNoticeAuto_2() {//no action
		var lastRunDate = utillity.stringToDate("2021-9-1");
		var nowRunDate = utillity.stringToDate("2021-10-10");
		
		logic.createDelayApproachingTodoNoticeAuto(lastRunDate, nowRunDate);
	}

	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createApproachingPubliceriodProjectNoticeAuto_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createApproachingPubliceriodProjectNoticeAuto_1() {
		var lastRunDate = utillity.stringToDate("2021-10-20");
		var nowRunDate = utillity.stringToDate("2021-10-31");
		
		logic.createApproachingPubliceriodProjectNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createApproachingPubliceriodProjectNoticeAuto_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createApproachingPubliceriodProjectNoticeAuto_2() {
		var lastRunDate = utillity.stringToDate("2021-11-2");
		var nowRunDate = utillity.stringToDate("2021-12-25");
		
		logic.createApproachingPubliceriodProjectNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createDealyPeriodPublicProjectNoticeAuto_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createDealyPeriodPublicProjectNoticeAuto_1() {
		var lastRunDate = utillity.stringToDate("2021-10-5");
		var nowRunDate = utillity.stringToDate("2021-11-2");
		
		logic.createDealyPeriodPublicProjectNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createDealyPeriodPublicProjectNoticeAuto_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createDealyPeriodPublicProjectNoticeAuto_2() {
		var lastRunDate = utillity.stringToDate("2021-8-31");
		var nowRunDate = utillity.stringToDate("2021-10-1");
		
		logic.createDealyPeriodPublicProjectNoticeAuto(lastRunDate, nowRunDate);
	}

	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createStartedTodoNoticeAuto_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createStartedTodoNoticeAuto_1() {
		var lastRunDate = utillity.stringToDate("2021-9-9");
		var nowRunDate = utillity.stringToDate("2021-9-11");
		
		logic.createStartedTodoNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createStartedTodoNoticeAuto_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createStartedTodoNoticeAuto_2() {
		var lastRunDate = utillity.stringToDate("2021-10-1");
		var nowRunDate = utillity.stringToDate("2021-10-15");
		
		logic.createStartedTodoNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/erasePublicProjectNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void erasePublicProjectNotice_1() {
		var userId = 7;
		var publicProjectId = 1;
		
		logic.erasePublicProjectNotice(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/erasePublicProjectNotice_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void erasePublicProjectNotice_2() {//no action
		var userId = 2;
		var publicProjectId = 1;
		
		logic.erasePublicProjectNotice(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/eraseTodoNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void eraseTodoNotice_1() {
		var userId = 1;
		var todoId = 5;
		
		logic.eraseTodoNotice(userId, todoId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/eraseTodoNotice_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void eraseTodoNotice_2() {//no action
		var userId = 2;
		var todoId = 1;
		
		logic.eraseTodoNotice(userId, todoId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/erasePublicProjectNoticeAuto.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void erasePublicProjectNoticeAuto() {
		logic.erasePublicProjectNoticeAuto();
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/eraseTodoNoticeAuto.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void eraseTodoNoticeAuto() {
		logic.eraseTodoNoticeAuto();
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/eraseUnNeededNoticeIdAuto.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void eraseUnNeededNoticeIdAuto() {
		logic.eraseUnNeededNoticeIdAuto();
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/eraseNoticeOfDeletedUser.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void eraseNoticeOfDeletedUser() {
		logic.eraseNoticeOfDeletedUser();
	}
}