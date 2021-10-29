package com.example.demo.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfig;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.form.NoticeForm;
import com.example.demo.response.NoticeResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.NoticeService;

/**
 * 通知に関するAPIのアクセスポイント
 * 
 * @version 1
 * @since 1
 * @author towelman
 *
 */
@RequestMapping(UrlConfig.ROOT_URL_V1 + "/notice")
@RestController
public class NoticeControl {
	@Autowired
	NoticeService service;
	
	/**
	 * 通知リストを取得する
	 * @param user
	 * @param form
	 * @return 通知リスト
	 * @throws NotFoundValueException 
	 */
	@GetMapping
	public List<NoticeResponse> getNoticeList(@AuthenticationPrincipal UserDetailsImp user, NoticeForm form) throws NotFoundValueException {
		if(form.getTerminalName() != null && !form.getTerminalName().isBlank()) {
			return service.getUnsendedNotice(user.getUserId(), form.getTerminalName());
		}else {
			return service.getNotice(user.getUserId());
		}
	}
}
