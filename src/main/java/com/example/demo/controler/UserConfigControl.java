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
import com.example.demo.service.UserConfigServer;

@RequestMapping(UrlConfig.ROOT_URL_V1 + "/user/config")
@RestController
public class UserConfigControl {
	@Autowired
	UserConfigServer server;
	
	@GetMapping
	public UserConfigResponse getUserConfig(@AuthenticationPrincipal UserDetailsImp user) {
		return server.get(user.getUserId());
	}
	
	
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
			server.updatePushInsertedTodoNotice(user.getUserId(), form.getPushInsertedTodoNotice());
		}
	}
}
