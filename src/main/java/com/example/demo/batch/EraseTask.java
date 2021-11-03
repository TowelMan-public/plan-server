package com.example.demo.batch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ContentEntityExample;
import com.example.demo.dto.PrivateProjectEntityExample;
import com.example.demo.dto.PublicProjectEntityExample;
import com.example.demo.dto.SubscriberInPublicProjectEntityExample;
import com.example.demo.dto.TerminalEntityExample;
import com.example.demo.dto.TodoOnProjectEntityExample;
import com.example.demo.dto.TodoOnResponsibleEntityExample;
import com.example.demo.dto.UserEntityExample;
import com.example.demo.entity.ContentEntity;
import com.example.demo.entity.PrivateProjectEntity;
import com.example.demo.entity.PublicProjectEntity;
import com.example.demo.entity.TodoOnProjectEntity;
import com.example.demo.entity.TodoOnResponsibleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.logic.NoticeLogic;
import com.example.demo.repository.ContentEntityMapper;
import com.example.demo.repository.OriginalForEraseAndDeleteRepository;
import com.example.demo.repository.PrivateProjectEntityMapper;
import com.example.demo.repository.ProjectEntityMapper;
import com.example.demo.repository.PublicProjectEntityMapper;
import com.example.demo.repository.SubscriberInPublicProjectEntityMapper;
import com.example.demo.repository.TerminalEntityMapper;
import com.example.demo.repository.TodoEntityMapper;
import com.example.demo.repository.TodoOnProjectEntityMapper;
import com.example.demo.repository.TodoOnResponsibleEntityMapper;
import com.example.demo.repository.UserConfigEntityMapper;
import com.example.demo.repository.UserEntityMapper;

/**
 * データベース上の、物理的に残っていて論理削除されたものを削除するクラス
 * @author towelman
 *
 */
@Component
public class EraseTask {	
	@Autowired
	UserConfigEntityMapper userConfigEntityMapper;
	@Autowired
	TerminalEntityMapper terminalEntityMapper;
	@Autowired
	NoticeLogic noticeLogic;
	@Autowired
	ContentEntityMapper contentEntityMapper;
	@Autowired
	TodoOnProjectEntityMapper todoOnProjectEntityMapper;
	@Autowired
	TodoEntityMapper todoEntityMapper;
	@Autowired
	TodoOnResponsibleEntityMapper todoOnResponsibleEntityMapper;
	@Autowired
	SubscriberInPublicProjectEntityMapper subscriberInPublicProjectEntityMapper;
	@Autowired
	ProjectEntityMapper projectEntityMapper;
	@Autowired
	PublicProjectEntityMapper publicProjectEntityMapper;
	@Autowired
	UserEntityMapper userEntityMapper;
	@Autowired
	PrivateProjectEntityMapper privateProjectEntityMapper;
	@Autowired
	OriginalForEraseAndDeleteRepository originalForEraseAndDeleteRepository;
	
	/**
	 * 定期実行したい処理たち
	 */
	@Scheduled(cron="0 0 0 * * *")
	@Transactional 
	public void runEraseTask() {
		var deletedUserList = this.getDeletedUser();
		this.deletePrivateProject(deletedUserList);
		this.deletePublicProject();
		this.deleteSubscriberByDeletedUser(deletedUserList);
		this.deletePublicProject();
		var publicProjectList = this.deleteSubscriber();
		this.deleteTodoOnProject(publicProjectList);
		var todoOnProjectList = this.deleteTodoOnResponsible(deletedUserList);
		this.deleteContent(todoOnProjectList);
		this.eraseNotice();
		this.eraseContent();
		this.eraseTodoOnResponsible();
		this.eraseTodoOnProject();
		this.eraseTodo();
		this.erasePrivateProejct();
		this.erasePublicProject();
		this.eraseProject();
		this.eraseAroundUser(deletedUserList);
	}
	
	private List<UserEntity> getDeletedUser(){
		var dto = new UserEntityExample();
		dto.or()
			.andIsDeletedEqualTo(true);
		
		return userEntityMapper.selectByExample(dto);
	}
	
	private void deletePrivateProject(List<UserEntity> userList) {
		var entity = new PrivateProjectEntity();
		entity.setIsDeleted(true);
		
		for(var user: userList) {
			var dto = new PrivateProjectEntityExample();
			dto.or()
				.andUserIdEqualTo(user.getUserId());			
			
			privateProjectEntityMapper.updateByExampleSelective(entity, dto);
		}
	}
	
	private void deletePublicProject() {//SQL必要
		originalForEraseAndDeleteRepository.deletePublicProject();
	}
	
	private void deleteSubscriberByDeletedUser(List<UserEntity> userList) {
		for(var user: userList) {
			var dto = new SubscriberInPublicProjectEntityExample();
			dto.or()
				.andUserIdEqualTo(user.getUserId());			
			
			subscriberInPublicProjectEntityMapper.deleteByExample(dto);
		}
	}
	
	private List<PublicProjectEntity> deleteSubscriber() {
		var publicProjectDto = new PublicProjectEntityExample();
		publicProjectDto.or()
			.andIsDeletedEqualTo(true);
		
		var publicProjectList = publicProjectEntityMapper.selectByExample(publicProjectDto);
		
		for(var publicProject: publicProjectList) {
			var dto = new SubscriberInPublicProjectEntityExample();
			dto.or()
				.andPublicProjectIdEqualTo(publicProject.getPublicProjectId());
			
			subscriberInPublicProjectEntityMapper.deleteByExample(dto);
		}
		
		return publicProjectList;
	}
	
	private void deleteTodoOnProject(List<PublicProjectEntity> publicProjectList) {
		var privateDto = new PrivateProjectEntityExample();
		privateDto.or()
			.andIsDeletedEqualTo(true);
		var privateProjectList = privateProjectEntityMapper.selectByExample(privateDto);
		
		var entity = new TodoOnProjectEntity();
		entity.setIsDeleted(true);
		
		for(var privateProject: privateProjectList) {
			var dto = new TodoOnProjectEntityExample();
			dto.or()
				.andProjectIdEqualTo(privateProject.getProjectId());
			
			todoOnProjectEntityMapper.updateByExampleSelective(entity, dto);
		}
		
		for(var publicProject: publicProjectList) {
			var dto = new TodoOnProjectEntityExample();
			dto.or()
				.andProjectIdEqualTo(publicProject.getPublicProjectId());
			
			todoOnProjectEntityMapper.updateByExampleSelective(entity, dto);
		}
	}
	
	private List<TodoOnProjectEntity> deleteTodoOnResponsible(List<UserEntity> userList) {
		var todoOnProjectDto = new TodoOnProjectEntityExample();
		todoOnProjectDto.or()
			.andIsDeletedEqualTo(true);
		var todoOnProjectList = todoOnProjectEntityMapper.selectByExample(todoOnProjectDto);
		
		var entity = new TodoOnResponsibleEntity();
		entity.setIsDeleted(true);
		
		for(var user: userList) {
			var dto = new TodoOnResponsibleEntityExample();
			dto.or()
				.andUserIdEqualTo(user.getUserId());
			
			todoOnResponsibleEntityMapper.updateByExampleSelective(entity, dto);
		}
		
		for(var todoOnProject: todoOnProjectList) {
			var dto = new TodoOnResponsibleEntityExample();
			dto.or()
				.andTodoOnProjectIdEqualTo(todoOnProject.getTodoOnProjectId());
			
			todoOnResponsibleEntityMapper.updateByExampleSelective(entity, dto);
		}
		
		return todoOnProjectList;
	}
	
	private void deleteContent(List<TodoOnProjectEntity> todoOnProjectList) {
		var todoOnResponsibleDto = new TodoOnResponsibleEntityExample();
		todoOnResponsibleDto.or()
			.andIsDeletedEqualTo(true);
		
		var todoOnResponsibleList = todoOnResponsibleEntityMapper.selectByExample(todoOnResponsibleDto);
		
		var entity = new ContentEntity();
		entity.setIsDeleted(true);
		
		for(var todo: todoOnProjectList) {
			var dto = new ContentEntityExample();
			dto.or()
				.andTodoIdEqualTo(todo.getTodoOnProjectId());
			
			contentEntityMapper.updateByExampleSelective(entity, dto);
		}
		
		for(var todo: todoOnResponsibleList) {
			var dto = new ContentEntityExample();
			dto.or()
				.andTodoIdEqualTo(todo.getTodoOnResponsibleId());
			
			contentEntityMapper.updateByExampleSelective(entity, dto);
		}
	}
	
	private void eraseNotice() {
		noticeLogic.eraseTodoNoticeAuto();
		noticeLogic.erasePublicProjectNoticeAuto();
		noticeLogic.eraseUnNeededNoticeIdAuto();
	}
	
	private void eraseContent() {
		var dto = new ContentEntityExample();
		dto.or()
			.andIsDeletedEqualTo(true);
		
		contentEntityMapper.deleteByExample(dto);
	}
	
	private void eraseTodoOnResponsible() {
		var dto = new TodoOnResponsibleEntityExample();
		dto.or()
			.andIsDeletedEqualTo(true);
		
		todoOnResponsibleEntityMapper.deleteByExample(dto);
	}
	
	private void eraseTodoOnProject() {
		var dto = new TodoOnProjectEntityExample();
		dto.or()
			.andIsDeletedEqualTo(true);
		
		todoOnProjectEntityMapper.deleteByExample(dto);
	}
	
	private void eraseTodo() {//SQL必要
		originalForEraseAndDeleteRepository.eraseTodo();
	}
	
	private void erasePrivateProejct() {
		var dto = new PrivateProjectEntityExample();
		dto.or()
			.andIsDeletedEqualTo(true);
		
		privateProjectEntityMapper.deleteByExample(dto);
	}
	
	private void erasePublicProject() {
		var dto = new PublicProjectEntityExample();
		dto.or()
			.andIsDeletedEqualTo(true);
		
		publicProjectEntityMapper.deleteByExample(dto);
	}
	
	private void eraseProject() {//SQL必要
		originalForEraseAndDeleteRepository.eraseProject();
	}
	
	private void eraseAroundUser(List<UserEntity> userList) {
		for(var user: userList) {
			var dto = new TerminalEntityExample();
			dto.or()
				.andUserIdEqualTo(user.getUserId());
			terminalEntityMapper.deleteByExample(dto);
			userConfigEntityMapper.deleteByPrimaryKey(user.getUserId());
		}
		
		var userDto = new UserEntityExample();
		userDto.or()
			.andIsDeletedEqualTo(true);
		
		userEntityMapper.deleteByExample(userDto);
	}
}
