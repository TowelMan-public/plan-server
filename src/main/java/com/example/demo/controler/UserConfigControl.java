package com.example.demo.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfig;
import com.example.demo.form.UserConfigForm;
import com.example.demo.response.UserConfigResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserConfigService;

/**
 * ユーザーの設定に関するAPIのアクセスポイント
 * 
 * @version 1
 * @since 1
 * @author towelman
 *
 */
@RequestMapping(UrlConfig.ROOT_URL_V1 + "/user/config")
@RestController
public class UserConfigControl {
	@Autowired
	UserConfigService server;
	
	/**
	 * ユーザーの設定を取得する
	 * @param user
	 * @return ユーザーの設定
	 */
	@GetMapping
	public UserConfigResponse getUserConfig(@AuthenticationPrincipal UserDetailsImp user) {
		return server.get(user.getUserId());
	}
	
	/**
	 * ユーザーの設定を変更する
	 * @param user
	 * @param form
	 */
	public void updateUserConfig(@AuthenticationPrincipal UserDetailsImp user, @RequestBody UserConfigForm form){
		if(form.getBeforeDeadlineForProjectNotice() != null) {
			server.updateBeforeDeadlineForProjectNotice(user.getUserId(), form.getBeforeDeadlineForProjectNotice());
		}
		if(form.getBeforeDeadlineForTodoNotice() != null) {
			server.updateBeforeDeadlineForTodoNotice(user.getUserId(), form.getBeforeDeadlineForTodoNotice());
		}
		if(form.getIsPushSatrtedTodoNotice() != null) {
			server.updateIsPushSatrtedTodoNotice(user.getUserId(), form.getIsPushSatrtedTodoNotice());
		}
		if(form.getPushInsertedTodoNotice() != null) {
			server.updateIsPushInsertedTodoNotice(user.getUserId(), form.getPushInsertedTodoNotice());
		}
	}
}
