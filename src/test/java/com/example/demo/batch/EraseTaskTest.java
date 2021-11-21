package com.example.demo.batch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DatabaseTest;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/batch/EraseTaskTest/pettern.xml")
public class EraseTaskTest extends DatabaseTest{
	@Autowired
	EraseTask task;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/logic/EraseTaskTest/expect/runEraseTask.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	void runEraseTask() {
		task.runEraseTask();
	}
}
