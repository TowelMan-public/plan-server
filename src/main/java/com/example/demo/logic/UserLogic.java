package com.example.demo.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.UserEntityExample;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.AlreadyUsedUserNameException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.repository.UserEntityMapper;

/**
 * ユーザーの共通処理
 * @author towelman
 *
 */
@Component
public class UserLogic {
	@Autowired
	UserEntityMapper userEntityMapper;

	/**
	 * ユーザー名からユーザーを取得する
	 * @param userName ユーザー名
	 * @return ユーザー
	 * @throws NotFoundValueException ユーザー名が見つからなかった時に投げられる
	 */
	public UserEntity getUserByUserName(String userName) throws NotFoundValueException {
		var dto = new UserEntityExample();
		dto.or()
			.andIsDeletedEqualTo(false)
			.andUserNameEqualTo(userName);
		
		var entityList = userEntityMapper.selectByExample(dto);
		
		if(entityList.isEmpty())
			throw new NotFoundValueException("userName", userName);
		else
			return entityList.get(0);
	}

	/**
	 * ユーザーIDからユーザーを取得する<br>
	 * 例外を投げないため、存在しないユーザーIDが指定される恐れがある場合は要注意！
	 * @param userId ユーザーID
	 * @return ユーザー
	 */
	public UserEntity getUserByUserId(Integer userId) {
		var entity = userEntityMapper.selectByPrimaryKey(userId);
		
		if(entity == null || entity.getIsDeleted().booleanValue())
			return null;
		else
			return entity;
	}

	/**
	 * ユーザーを削除する
	 * @param userId ユーザーID
	 */
	public void delete(Integer userId) {
		var entity = new UserEntity();
		entity.setUserId(userId);
		entity.setIsDeleted(true);
		
		userEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * ユーザーを作成する
	 * @param userName ユーザー名
	 * @param userNickName ユーザーのニックネーム
	 * @param password 暗号化されたパスワード
	 * @return 新しく作成されたユーザーのID
	 */
	public Integer insert(String userName, String userNickName, String password) {
		var entity = new UserEntity();
		entity.setUserName(userName);
		entity.setUserNickname(userNickName);
		entity.setPassword(password);
		entity.setIsDeleted(false);
		
		userEntityMapper.insertSelective(entity);
		return entity.getUserId();
	}

	/**
	 * ユーザー名が使われていないことを検証する
	 * @param userName ユーザー名
	 * @throws AlreadyUsedUserNameException 既にユーザー名が使われているときに投げられる
	 */
	public void validateUserNameNotUsed(String userName) throws AlreadyUsedUserNameException {
		var dto = new UserEntityExample();
		dto.or()
			.andUserNameEqualTo(userName);
		
		var entityList = userEntityMapper.selectByExample(dto);
		
		if(!entityList.isEmpty())
			throw new AlreadyUsedUserNameException(userName);
	}

	/**
	 * パスワードの変更をする
	 * @param userId ユーザーID
	 * @param password　暗号化されたパスワード
	 */
	public void updatePasswrod(Integer userId, String password) {
		var entity = new UserEntity();
		entity.setUserId(userId);
		entity.setPassword(password);
		
		userEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * ユーザーのニックネームを変える
	 * @param userId ユーザーID
	 * @param userNickName ユーザーニックネーム
	 */
	public void updateNickName(Integer userId, String userNickName) {
		var entity = new UserEntity();
		entity.setUserId(userId);
		entity.setUserNickname(userNickName);
		
		userEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * ユーザー名を変える
	 * @param userId ユーザーID
	 * @param userName ユーザー名
	 */
	public void updateName(Integer userId, String userName) {
		var entity = new UserEntity();
		entity.setUserId(userId);
		entity.setUserName(userName);
		
		userEntityMapper.updateByPrimaryKeySelective(entity);
	}
	
}