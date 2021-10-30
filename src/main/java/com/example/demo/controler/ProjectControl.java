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
import com.example.demo.form.ProjectForm;
import com.example.demo.response.PublicProjectResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.PublicProjectService;

/**
 * パブリックプロジェクトに関するAPIのアクセスポイント
 * 
 * @version 1
 * @since 1
 * @author towelman
 *
 */
@RequestMapping(UrlConfig.ROOT_URL_V1 + "/project")
@RestController
public class ProjectControl {
	@Autowired
	PublicProjectService service;
	
	/**
	 * パブリックプロジェクトの新規作成
	 * @param user
	 * @param form
	 * @return 新しいパブリックプロジェクトのID
	 * @throws ValidateException
	 */
	@PostMapping
	public Integer insertProject(@AuthenticationPrincipal UserDetailsImp user, @RequestBody ProjectForm form)
			throws ValidateException {
		form.validatePost();
		return service.insert(user.getUserId(), form);
	}
	
	/**
	 * パブリックプロジェクト取得
	 * @param user
	 * @param publicProjectId
	 * @return パブリックプロジェクト
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	@GetMapping("{publicProjectId}")
	public PublicProjectResponse getPublicProject(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId) 
			throws NotFoundValueException, NotJoinedPublicProjectException {
		return service.get(user.getUserId(), publicProjectId);
	}
	
	/**
	 * 自分が参画しているパブリックプロジェクトの取得
	 * @param user
	 * @return 自分が参画しているパブリックプロジェクト
	 */
	@GetMapping
	public List<PublicProjectResponse> getPublicProjectList(@AuthenticationPrincipal UserDetailsImp user) {
		return service.getList(user.getUserId());
	}
	
	/**
	 * パブリックプロジェクトに対し、変更を加える
	 * @param user
	 * @param publicProjectId
	 * @param form
	 * @throws NotFoundValueException
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws BadRequestException
	 */
	@PutMapping("{publicProjectId}")
	public void updatePublicProject(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId, @RequestBody ProjectForm form)
			throws NotFoundValueException, NotHaveAuthorityToOperateProjectException, BadRequestException {
		if(form.getProjectName() != null && !form.getProjectName().isBlank()) {
			service.updateProjectName(user.getUserId(), publicProjectId, form.getProjectName());
		}
		if(form.getStartDate() != null) {
			service.updateStartDate(user.getUserId(), publicProjectId, form.getStartDate());
		}
		if(form.getFinishDate() != null) {
			service.updateFinishDate(user.getUserId(), publicProjectId, form.getFinishDate());	
		}
	}
	
	/**
	 * パブリックプロジェクトを削除する
	 * @param user
	 * @param publicProjectId
	 * @throws NotFoundValueException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@DeleteMapping("{publicProjectId}")
	public void deletePublicProject(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId)
			throws NotFoundValueException, NotHaveAuthorityToOperateProjectException {
		service.delete(user.getUserId(), publicProjectId);
	}
	
	/**
	 * パブリックプロジェクトに完了状況をセットする
	 * @param user
	 * @param publicProjectId
	 * @param form
	 * @throws ValidateException
	 * @throws NotFoundValueException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@PostMapping("{publicProjectId}/is_completed")
	public void setIsCompleted(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId, @RequestBody ProjectForm form)
			throws ValidateException, NotFoundValueException, NotHaveAuthorityToOperateProjectException {
		form.validatePutIsCompleted();
		service.setIsCompleted(user.getUserId(), publicProjectId, form.getIsCompleted());
	}
	
	
	@GetMapping("solicited")
	public List<PublicProjectResponse> getSolicitedPublicProjectList(@AuthenticationPrincipal UserDetailsImp user) {
		return service.getSolicited(user.getUserId());
	}
}
