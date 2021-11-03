package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

/**
 * データベース上の物理削除等をするための生SQL
 * @author towelman
 *
 */
@Mapper
public interface OriginalForEraseAndDeleteRepository {

	void deletePublicProject();

	void eraseTodo();

	void eraseProject();
	
}
