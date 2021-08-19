package com.example.demo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.UserEntity;

import lombok.Data;

/**
 * ユーザー情報を認証処理等で使うときのためのユーザー情報オブジェクト<br>
 * UserDetailsを実装している
 * @see org.springframework.security.core.userdetails.UserDetails
 */
@Data
public class UserDetailsImp implements UserDetails {
	//シリアライズ
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private Integer userId;
	private boolean isEnabled;
	
	/**
	 * UserEntityとの互換用のコンストラクタ
	 * @param entity ユーザー情報の入ったUserEntity
	 * @see com.example.demo.entity.UserEntity
	 */
	public UserDetailsImp(UserEntity entity) {
		this.setPassword(entity.getPassword());
		this.setUserId(entity.getUserId());
		this.setUsername(entity.getUserName());
		this.setEnabled(true);
	}
	
	/**
	 * テスト用
	 */
	public UserDetailsImp() {}

	//権限(これは関係ない)
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("USER");
	}
	
	//アカウントの有効期限の状態を判定
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//アカウントのロック状態を判定
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	//資格情報の有効期限の状態を判定
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}