package com.example.demo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TestMapper {
	@Update("ALTER TABLE ${tableName} AUTO_INCREMENT = 1")
	void runSql(String tableName);
}