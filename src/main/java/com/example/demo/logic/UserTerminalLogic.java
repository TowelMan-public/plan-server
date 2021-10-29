package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.TerminalEntityExample;
import com.example.demo.entity.TerminalEntity;
import com.example.demo.exception.AlreadyUsedTerminalNameException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.repository.TerminalEntityMapper;

/**
 * ユーザーの機種名の共通処理
 * @author towelman
 *
 */
@Component
public class UserTerminalLogic {
	@Autowired
	TerminalEntityMapper terminalEntityMapper;

	/**
	 * 機種の追加
	 * @param userId ユーザーID
	 * @param terminalName 機種名
	 */
	public void insert(Integer userId, String terminalName) {
		var entity = new TerminalEntity();
		entity.setUserId(userId);
		entity.setTerminalName(terminalName);
		
		terminalEntityMapper.insertSelective(entity);
	}

	/**
	 * 機種名が使われていないことを検証する
	 * @param userId ユーザーID
	 * @param terminalName 機種名
	 * @throws AlreadyUsedTerminalNameException 機種名が既に使われているときに投げられる
	 */
	public void validateTerminalNameNotUsed(Integer userId, String terminalName) throws AlreadyUsedTerminalNameException{
		var dto = new TerminalEntityExample();
		dto.or()
			.andUserIdEqualTo(userId)
			.andTerminalNameEqualTo(terminalName);
		
		var entityList = terminalEntityMapper.selectByExample(dto);
		
		if(!entityList.isEmpty())
			throw new AlreadyUsedTerminalNameException(terminalName);
	}

	/**
	 * 機種リストを取得する
	 * @param userId ユーザーID
	 * @return 機種リスト
	 */
	public List<TerminalEntity> getTerminalList(Integer userId) {
		var dto = new TerminalEntityExample();
		dto.or()
			.andUserIdEqualTo(userId);
		
		return terminalEntityMapper.selectByExample(dto);
	}

	/**
	 * 機種名を変更する
	 * @param terminalId 機種ID
	 * @param terminalName 機種名
	 */
	public void updateTerminalName(Integer terminalId, String terminalName) {
		var entity = new TerminalEntity();
		entity.setTerminalId(terminalId);
		entity.setTerminalName(terminalName);
		
		terminalEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 機種を取得する
	 * @param userId ユーザーID
	 * @param terminalName 機種名
	 * @return 機種
	 * @throws NotFoundValueException 指定された機種名が見つからないときに投げられる
	 */
	public TerminalEntity get(Integer userId, String terminalName) throws NotFoundValueException {
		var dto = new TerminalEntityExample();
		dto.or()
			.andUserIdEqualTo(userId)
			.andTerminalNameEqualTo(terminalName);
		
		var entityList = terminalEntityMapper.selectByExample(dto);
		
		if(entityList.isEmpty())
			throw new NotFoundValueException("terminalName", terminalName);
		else
			return entityList.get(0);
	}

	/**
	 * 機種を削除する
	 * @param terminalId 機種ID
	 */
	public void delete(Integer terminalId) {
		terminalEntityMapper.deleteByPrimaryKey(terminalId);
	}
}
