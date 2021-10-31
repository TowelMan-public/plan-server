package com.example.demo.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfig;
import com.example.demo.exception.AlreadyJoinedPublicProjectException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.exception.ValidateException;
import com.example.demo.form.SubscriberInProjectForm;
import com.example.demo.response.SubscriberInPublicProjectResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.SubscriberProjectService;

/**
 * パブリックプロジェクトの参加者に関するAPIのアクセスポイント
 * 
 * @version 1
 * @since 1
 * @author towelman
 *
 */
@RequestMapping(UrlConfig.ROOT_URL_V1 + "/project/{publicProjectId}/subscriber")
@RestController
public class SubscriberProjectControl {
	@Autowired
	SubscriberProjectService service;
	
	/**
	 * 参画者に勧誘する
	 * @param user
	 * @param publicProjectId
	 * @param form
	 * @throws ValidateException
	 * @throws NotFoundValueException
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws AlreadyJoinedPublicProjectException 
	 */
	@PostMapping
	public void insertSubscriber(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("publicProjectId") Integer publicProjectId,
			@RequestBody SubscriberInProjectForm form) throws ValidateException, NotFoundValueException, NotHaveAuthorityToOperateProjectException, AlreadyJoinedPublicProjectException {
		form.validatePost();
		service.insert(user.getUserId(), publicProjectId, form.getUserName());
	}
	
	/**
	 * 参画者リストを取得する
	 * @param user
	 * @param publicProjectId
	 * @return 参画者リスト
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	@GetMapping
	public List<SubscriberInPublicProjectResponse> getSubscriberList(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId)
			throws NotFoundValueException, NotJoinedPublicProjectException {
		return service.getList(user.getUserId(), publicProjectId);
	}
	
	/**
	 * 参画者の権限を変更する
	 * @param user
	 * @param publicProjectId
	 * @param form
	 * @throws ValidateException
	 * @throws NotFoundValueException
	 * @throws BadRequestException
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws NotJoinedPublicProjectException
	 */
	@PutMapping
	public void updateSubscriber(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("publicProjectId") Integer publicProjectId,
			@RequestBody SubscriberInProjectForm form) throws ValidateException, NotFoundValueException, BadRequestException, NotHaveAuthorityToOperateProjectException, NotJoinedPublicProjectException {
		form.validatePut();
		service.update(user.getUserId(), publicProjectId, form.getUserName(), form.getAuthorityId());
	}
	
	/**
	 * 参画者を除外する
	 * @param user
	 * @param publicProjectId
	 * @param form
	 * @throws ValidateException
	 * @throws BadRequestException
	 * @throws NotFoundValueException
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws NotJoinedPublicProjectException
	 */
	@DeleteMapping
	public void deleteSubscriber(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("publicProjectId") Integer publicProjectId,
			@RequestBody SubscriberInProjectForm form) throws ValidateException, BadRequestException, NotFoundValueException, NotHaveAuthorityToOperateProjectException, NotJoinedPublicProjectException {
		form.validateDelete();
		
		service.delete(user.getUserId(), publicProjectId, form.getUserName());
	}
	
	/**
	 * 勧誘を受け入れる
	 * @param user
	 * @param publicProjectId
	 * @throws NotFoundValueException
	 * @throws BadRequestException
	 */
	@PostMapping("accept")
	public void accept(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId)
			throws NotFoundValueException, BadRequestException {
		service.accept(user.getUserId(), publicProjectId);
	}
	
	/**
	 * 勧誘を断る
	 * @param user
	 * @param publicProjectId
	 * @throws NotFoundValueException
	 * @throws BadRequestException
	 */
	@PostMapping("block")
	public void block(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId)
			throws NotFoundValueException, BadRequestException {
		service.bloak(user.getUserId(), publicProjectId);
	}
	
	/**
	 * 参画者をやめる
	 * @param user
	 * @param publicProjectId
	 * @throws BadRequestException
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	@PostMapping("exit")
	public void exitProject(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId)
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {
		service.exit(user.getUserId(), publicProjectId);
	}
}
