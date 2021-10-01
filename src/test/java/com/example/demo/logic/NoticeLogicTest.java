package com.example.demo.logic;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/logic/NoticeLogicTest/pettern.xml")
public class NoticeLogicTest {	
	@Autowired
	NoticeLogic logic;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createInvitationPublicProjectNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createInvitationPublicProjectNotice_1() {
		var userId =-1;
		var publicProjectId = -1;
		
		logic.createInvitationPublicProjectNotice(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createInvitationPublicProjectNotice_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createInvitationPublicProjectNotice_2() {
		var userId =-1;
		var publicProjectId = -1;
		
		logic.createInvitationPublicProjectNotice(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createCompletedTodoNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createCompletedTodoNotice_1() {
		var userId =-1;
		var todoId = -1;
		
		logic.createCompletedTodoNotice(userId, todoId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createCompletedTodoNotice_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createCompletedTodoNotice_2() {
		var userId =-1;
		var todoId = -1;
		
		logic.createCompletedTodoNotice(userId, todoId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createApproachingPeriodTodoNoticeAuto_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createApproachingPeriodTodoNoticeAuto_1() {
		var lastRunDate = new Date();
		var nowRunDate = new Date();
		
		logic.createApproachingPeriodTodoNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createApproachingPeriodTodoNoticeAuto_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createApproachingPeriodTodoNoticeAuto_2() {
		var lastRunDate = new Date();
		var nowRunDate = new Date();
		
		logic.createApproachingPeriodTodoNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createDelayApproachingTodoNoticeAuto_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createDelayApproachingTodoNoticeAuto_1() {
		var lastRunDate = new Date();
		var nowRunDate = new Date();
		
		logic.createDelayApproachingTodoNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createDelayApproachingTodoNoticeAuto_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createDelayApproachingTodoNoticeAuto_2() {
		var lastRunDate = new Date();
		var nowRunDate = new Date();
		
		logic.createDelayApproachingTodoNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createStartedTodoNoticeAuto_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createStartedTodoNoticeAuto_1() {
		var lastRunDate = new Date();
		var nowRunDate = new Date();
		
		logic.createStartedTodoNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/createStartedTodoNoticeAuto_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void createStartedTodoNoticeAuto_2() {
		var lastRunDate = new Date();
		var nowRunDate = new Date();
		
		logic.createStartedTodoNoticeAuto(lastRunDate, nowRunDate);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/deletePublicProjectNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void deletePublicProjectNotice_1() {
		var userId =-1;
		var publicProjectId = -1;
		
		logic.deletePublicProjectNotice(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/deletePublicProjectNotice_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void deletePublicProjectNotice_2() {
		var userId =-1;
		var publicProjectId = -1;
		
		logic.deletePublicProjectNotice(userId, publicProjectId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/deleteTodoNotice_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void deleteTodoNotice_1() {
		var userId =-1;
		var todoId = -1;
		
		logic.deleteTodoNotice(userId, todoId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/deleteTodoNotice_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void deleteTodoNotice_2() {
		var userId =-1;
		var todoId = -1;
		
		logic.deleteTodoNotice(userId, todoId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/deletePublicProjectNoticeAuto.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void deletePublicProjectNoticeAuto() {
		logic.deletePublicProjectNoticeAuto();
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/deleteTodoNoticeAuto.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void deleteTodoNoticeAuto() {
		logic.deletePublicProjectNoticeAuto();
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/NoticeLogicTest/expect/deleteUnNeededNoticeIdAuto.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void deleteUnNeededNoticeIdAuto() {
		logic.deletePublicProjectNoticeAuto();
	}
}