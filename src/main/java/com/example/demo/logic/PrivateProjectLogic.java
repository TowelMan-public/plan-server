package com.example.demo.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.repository.PrivateProjectEntityMapper;

/**
 * プライベートプロジェクトの加入者の共通処理
 * @author towelman
 *
 */
@Component
public class PrivateProjectLogic {
	@Autowired
	PrivateProjectEntityMapper privateProjectEntityMapper;
	
	/**
	 * プロジェクトIDが、プライベートプロジェクトであるかどうかを調べる
	 * @param projectId プロジェクトID
	 * @return プライベートプロジェクトであればtrue、そうでなければfalse
	 */
	public boolean isPrivateProject(Integer projectId) {
		return privateProjectEntityMapper.selectByPrimaryKey(projectId) != null;
	}
	
}
