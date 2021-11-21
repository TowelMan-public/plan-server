package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseTest {
	@Autowired
	TestMapper testMapper;
	
	@BeforeEach
	public void reset() {
		testMapper.runSql("notice");
		testMapper.runSql("unsent_notice");		
		testMapper.runSql("users");
		testMapper.runSql("have_terminal_list");
		testMapper.runSql("project");
		testMapper.runSql("todo");
		testMapper.runSql("content");
	}
}
