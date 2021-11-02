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
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.exception.ValidateException;
import com.example.demo.form.ContentForm;
import com.example.demo.response.ContentResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.ContentService;

/**
 * 内容に関するAPIのアクセスポイント
 * 
 * @version 1
 * @since 1
 * @author towelman
 *
 */
@RequestMapping(UrlConfig.ROOT_URL_V1 + "/content")
@RestController
public class ConteotControl {
	@Autowired
	ContentService service;
	
	/**
	 * 内容の新規登録
	 * @param user
	 * @param form
	 * @return 新しい内容ID
	 * @throws ValidateException
	 * @throws BadRequestException
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	@PostMapping
	public Integer insertContent(@AuthenticationPrincipal UserDetailsImp user, @RequestBody ContentForm form) 
			throws ValidateException, BadRequestException, NotHaveAuthorityToOperateProjectException, NotFoundValueException, NotJoinedPublicProjectException {
		form.validatePost();
		return service.insert(user.getUserId(), form);
	}
	
	/**
	 * 「やること」の内容たちを取得
	 * @param user
	 * @param form
	 * @return 内容リスト
	 * @throws ValidateException
	 * @throws BadRequestException
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	@GetMapping
	public List<ContentResponse> getContentList(@AuthenticationPrincipal UserDetailsImp user, ContentForm form)
			throws ValidateException, BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {
		form.validateGet();
		return service.getList(user.getUserId(), form.getTodoId());
	}
	
	/**
	 * 内容の取得
	 * @param user
	 * @param contentId
	 * @return 内容
	 * @throws BadRequestException
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	@GetMapping("{contentId}")
	public ContentResponse getContentList(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("contentId") Integer contentId)
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {
		return service.get(user.getUserId(), contentId);
	}
	
	/**
	 * 内容に変更を加える
	 * @param user
	 * @param form
	 * @param contentId
	 * @throws ValidateException
	 * @throws BadRequestException
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@PutMapping("{contentId}")
	public void updateContent(@AuthenticationPrincipal UserDetailsImp user, 
			@RequestBody ContentForm form,
			@PathVariable("contentId") Integer contentId) 
					throws ValidateException, BadRequestException, NotFoundValueException,
					NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException{
		if(form.getContentTitle() != null && !form.getContentTitle().isBlank()) {
			service.updateTitle(user.getUserId(), contentId, form.getContentTitle());
		}
		if(form.getContentExplanation() != null && !form.getContentExplanation().isBlank()) {
			service.updateExplanation(user.getUserId(), contentId, form.getContentExplanation());
		}
	}
	
	/**
	 * 内容を削除する
	 * @param user
	 * @param contentId
	 * @throws BadRequestException
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@DeleteMapping("{contentId}")
	public void deleteContent(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("contentId") Integer contentId)
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		service.delete(user.getUserId(), contentId);
	}
	
	/**
	 * 内容に完了状況をセットする
	 * @param user
	 * @param form
	 * @param contentId
	 * @throws ValidateException
	 * @throws BadRequestException
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@PutMapping("{contentId}/is_completed")
	public void setIsCompleted(@AuthenticationPrincipal UserDetailsImp user, 
			@RequestBody ContentForm form,
			@PathVariable("contentId") Integer contentId) 
					throws ValidateException, BadRequestException, NotFoundValueException, 
					NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException{
		form.validatePutIsCompleted();
		service.setIsCompleted(user.getUserId(), contentId, form.getIsCompleted());
	}
}