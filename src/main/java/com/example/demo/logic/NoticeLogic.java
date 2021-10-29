package com.example.demo.logic;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.NoticeEntityExample;
import com.example.demo.dto.ProjectNoticeEntityExample;
import com.example.demo.dto.TerminalEntityExample;
import com.example.demo.dto.TodoNoticeEntityExample;
import com.example.demo.entity.NoticeEntity;
import com.example.demo.entity.ProjectNoticeEntity;
import com.example.demo.entity.TodoNoticeEntity;
import com.example.demo.entity.UnsentNoticeEntity;
import com.example.demo.repository.NoticeEntityMapper;
import com.example.demo.repository.OriginalForNoticeMapper;
import com.example.demo.repository.ProjectNoticeEntityMapper;
import com.example.demo.repository.TerminalEntityMapper;
import com.example.demo.repository.TodoNoticeEntityMapper;
import com.example.demo.repository.UnsentNoticeEntityMapper;
import com.example.demo.repository.UserConfigEntityMapper;

/**
 * 通知周りの共通処理クラス
 * @author towelman
 *
 */
@Component
public class NoticeLogic {
	private static final String PUBLIC_PROJECT_NAME = "{{PUBLIC_PROJECT_NAME}}";
	private static final String TODO_TITLE = "{{TODO_TITLE}}";
	
	@Autowired
	NoticeEntityMapper noticeEntityMapper;
	@Autowired
	UnsentNoticeEntityMapper unsentNoticeEntityMapper;
	@Autowired
	TerminalEntityMapper terminalEntityMapper;
	@Autowired
	ProjectNoticeEntityMapper projectNoticeEntityMapper;
	@Autowired
	TodoNoticeEntityMapper todoNoticeEntityMapper;
	@Autowired
	OriginalForNoticeMapper originalForNoticeMapper;
	@Autowired
	UserConfigEntityMapper userConfigEntityMapper;
	
	/**
	 * パブリックプロジェクトに勧誘されたときの通知の作成
	 * 
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 */
	public void createInvitationPublicProjectNotice(int userId, int publicProjectId) {
		var message = PUBLIC_PROJECT_NAME + "に勧誘されました";
		createPublicProjectNotice(userId, publicProjectId, message);
	}
	
	/**
	 * パブリックプロジェクトが無事に終わった通知の作成
	 * 
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 */
	public void createCompletedPublicProjectNotice(int userId, int publicProjectId) {
		var message = PUBLIC_PROJECT_NAME + "が完了しました！おめでとうございます！";
		createPublicProjectNotice(userId, publicProjectId, message);
	}
	
	/**
	 * パブリックプロジェクトが終わってなかった通知の作成
	 * 
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 */
	public void createUnCompletedPublicProjectNotice(int userId, int publicProjectId) {
		var message = PUBLIC_PROJECT_NAME + "が完了してなかったです！";
		createPublicProjectNotice(userId, publicProjectId, message);
	}
	
	/**
	 * パブリックプロジェクトの締め切りが迫ったときの通知の作成
	 * 
	 * @param lastRunDate 最後にこの処理が実行された時刻
	 * @param nowRunDate 現在の時刻
	 */
	public void createApproachingPubliceriodProjectNoticeAuto(Date lastRunDate, Date nowRunDate) {
		var message = PUBLIC_PROJECT_NAME + "の締め切りが迫りました";
		
		//subscriberInPublicProjectEntityList取得
		var subscriberInPublicProjectEntityList = originalForNoticeMapper.getApproachingPubliceriodProject(lastRunDate, nowRunDate);
		
		//通知作成
		subscriberInPublicProjectEntityList.forEach(subscriberEntity ->{
			createPublicProjectNotice(subscriberEntity.getUserId(), subscriberEntity.getPublicProjectId(),message);
		});
	}
	
	/**
	 * パブリックプロジェクトの締め切りを過ぎてしまったときの通知の作成
	 * 
	 * @param lastRunDate 最後にこの処理が実行された時刻
	 * @param nowRunDate 現在の時刻
	 */
	public void createDealyPeriodPublicProjectNoticeAuto(Date lastRunDate, Date nowRunDate) {
		var message = PUBLIC_PROJECT_NAME + "の締め切りを過ぎました！！";
		
		//subscriberInPublicProjectEntityList取得
		var subscriberInPublicProjectEntityList = originalForNoticeMapper.getDealyPeriodPublicProject(lastRunDate, nowRunDate);
		
		//通知作成
		subscriberInPublicProjectEntityList.forEach(subscriberEntity ->{
			createPublicProjectNotice(subscriberEntity.getUserId(), subscriberEntity.getPublicProjectId(),message);
		});
	}
	
	/**
	 * 
	 * 「やること」の担当に抜擢されたときの通知の作成
	 * 
	 * @param userId ユーザーID
	 * @param todoId 「やること」のID
	 */
	public void createInsertedTodoNotice(int userId, int todoId) {
		var userConfigEntity = userConfigEntityMapper.selectByPrimaryKey(userId);
		
		if(userConfigEntity.getIsPushInsertedTodoNotice().booleanValue()) {
			var message = TODO_TITLE + "の担当になりました。頑張ってください！";
			createTodoNotice(userId, todoId, message);
		}
	}
	
	/**
	 * 「やること」が無事に完了したときの通知作成
	 * 
	 * @param userId ユーザーID
	 * @param todoId 「やること」のID
	 */
	public void createCompletedTodoNotice(int userId, int todoId) {
		var message = TODO_TITLE + "が完了しました。頑張りましたね！";
		createTodoNotice(userId, todoId, message);
	}
	
	/**
	 * 「やること」が終わっていなかった時の通知作成
	 * 
	 * @param userId ユーザーID
	 * @param todoId 「やること」のID
	 */
	public void createUnCompletedTodoNotice(int userId, int todoId) {
		var message = TODO_TITLE + "が完了してなかったです！";
		createTodoNotice(userId, todoId, message);
	}
	
	/**
	 * 「やること」の締め切りが迫った時の通知作成
	 * 
	 * @param lastRunDate 最後にこの処理が実行された時刻
	 * @param nowRunDate 現在の時刻
	 */
	public void createApproachingPeriodTodoNoticeAuto(Date lastRunDate, Date nowRunDate) {
		var message = TODO_TITLE + "の締め切りが迫りました";
		
		//subscriberInPublicProjectEntityList取得
		var todoOnResponsibleEntityList = originalForNoticeMapper.getApproachingPeriodTodo(lastRunDate, nowRunDate);
		
		//通知作成
		todoOnResponsibleEntityList.forEach(todoOnResponsEntity ->{
			createTodoNotice(todoOnResponsEntity.getUserId(), todoOnResponsEntity.getTodoOnProjectId(), message);
		});
	}
	
	/**
	 * 「やること」の締め切りを過ぎてしまったときの通知の作成
	 * 
	 * @param lastRunDate 最後にこの処理が実行された時刻
	 * @param nowRunDate 現在の時刻
	 */
	public void createDelayApproachingTodoNoticeAuto(Date lastRunDate, Date nowRunDate) {
		var message = TODO_TITLE + "の締め切りを過ぎました！！";
		
		//subscriberInPublicProjectEntityList取得
		var todoOnResponsibleEntityList = originalForNoticeMapper.getDelayApproachingTodo(lastRunDate, nowRunDate);
		
		//通知作成
		todoOnResponsibleEntityList.forEach(todoOnResponsEntity ->{
			createTodoNotice(todoOnResponsEntity.getUserId(), todoOnResponsEntity.getTodoOnProjectId(), message);
		});
	}
	
	/**
	 * 「やること」をやる期間が来たことの通知を作成する
	 * 
	 * @param lastRunDate 最後にこの処理が実行された時刻
	 * @param nowRunDate 現在の時刻
	 */
	public void createStartedTodoNoticeAuto(Date lastRunDate, Date nowRunDate) {
		var message = TODO_TITLE + "が始まりました！頑張ってください！";
		
		//subscriberInPublicProjectEntityList取得
		var todoOnResponsibleEntityList = originalForNoticeMapper.getStartedTodo(lastRunDate, nowRunDate);
		
		//通知作成
		todoOnResponsibleEntityList.forEach(todoOnResponsEntity ->{
			createTodoNotice(todoOnResponsEntity.getUserId(), todoOnResponsEntity.getTodoOnProjectId(), message);
		});
	}
	
	/**
	 * パブリックプロジェクトの関する通知を削除する
	 * 
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 */
	public void erasePublicProjectNotice(int userId, int publicProjectId) {
		//noticeEntityList取得
		var noticeDto = new NoticeEntityExample();
		noticeDto
			.or()
				.andUserIdEqualTo(userId);
		
		var noticeEntityList = noticeEntityMapper.selectByExample(noticeDto);
		
		//削除処理
		noticeEntityList.forEach(noticeEntity ->{
			var projectNoticeDto = new ProjectNoticeEntityExample();
			projectNoticeDto
				.or()
					.andNoticeIdEqualTo(noticeEntity.getNoticeId())
					.andProjectIdEqualTo(publicProjectId);
			
			projectNoticeEntityMapper.deleteByExample(projectNoticeDto);
		});
	}
	
	/**
	 * 「やること」に関する通知を削除する
	 * 
	 * @param userId ユーザーID
	 * @param todoId 「やること」のID
	 */
	public void eraseTodoNotice(int userId, int todoId) {
		//noticeEntityList取得
		var noticeDto = new NoticeEntityExample();
		noticeDto
			.or()
				.andUserIdEqualTo(userId);
		
		var noticeEntityList = noticeEntityMapper.selectByExample(noticeDto);
		
		//削除処理
		noticeEntityList.forEach(noticeEntity ->{
			var todoNoticeDto = new TodoNoticeEntityExample();
			todoNoticeDto
				.or()
					.andNoticeIdEqualTo(noticeEntity.getNoticeId())
					.andTodoIdEqualTo(todoId);
			
			todoNoticeEntityMapper.deleteByExample(todoNoticeDto);
		});
	}
	
	/**
	 * 不要なパブリックプロジェクトの通知を削除する
	 */
	public void erasePublicProjectNoticeAuto() {
		originalForNoticeMapper.erasePublicProjectNoticeAuto();
	}
	
	/**
	 * 不要な「やること」の通知を削除する
	 */
	public void eraseTodoNoticeAuto() {
		originalForNoticeMapper.eraseTodoNoticeAuto();
	}
	
	/**
	 * deleteされているUserの通知を削除する
	 */
	public void eraseNoticeOfDeletedUser() {
		originalForNoticeMapper.eraseProjectNoticeOfDeletedUser();
		originalForNoticeMapper.eraseTodoNoticeOfDeletedUser();
	}
	
	
	/**
	 * 必要ない通知ID等を削除する
	 */
	public void eraseUnNeededNoticeIdAuto() {
		originalForNoticeMapper.eraseUnNeededNotice();
		originalForNoticeMapper.eraseNoticeParent();
	}
	
	/**
	 * パブリックプロジェクトについての通知を作る
	 * 
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @param message メッセージ
	 */
	private void createPublicProjectNotice(int userId, int publicProjectId, String message) {
		var noticeId = createNotice(userId);
		
		var projectNoticeEntity = new ProjectNoticeEntity();
		projectNoticeEntity.setNoticeId(noticeId);
		projectNoticeEntity.setProjectId(publicProjectId);
		projectNoticeEntity.setMessage(message);
		
		projectNoticeEntityMapper.insert(projectNoticeEntity);
	}
	
	/**
	 * 「やること」についての通知を作る
	 * 
	 * @param userId ユーザーID
	 * @param todoId 「やること」のID
	 * @param message メッセージ
	 */
	private void createTodoNotice(int userId, int todoId, String message) {
		var noticeId = createNotice(userId);
		
		var todoNoticeEntity = new TodoNoticeEntity();
		todoNoticeEntity.setNoticeId(noticeId);
		todoNoticeEntity.setTodoId(todoId);
		todoNoticeEntity.setMessage(message);
		
		todoNoticeEntityMapper.insert(todoNoticeEntity);
	}
	
	/**
	 * 通知ID等を作る<br>
	 * これをやれば各機種へのpush通知も大丈夫
	 * 
	 * @param userId ユーザーID
	 * @return 新しい通知ID
	 */
	private int createNotice(int userId) {
		//noticeId取得
		var noticeEntity = new NoticeEntity();
		noticeEntity.setUserId(userId);
		noticeEntityMapper.insertSelective(noticeEntity);
		var noticeId = noticeEntity.getNoticeId();
		
		//terminalEntityList取得
		var terminalDto = new TerminalEntityExample();
		terminalDto
			.or()
				.andUserIdEqualTo(userId);		
		var terminalEntityList = terminalEntityMapper.selectByExample(terminalDto);
		
		//未送信通知リスト作成
		terminalEntityList.forEach(terminalEntity ->{
			var unsentNoticeEntity = new UnsentNoticeEntity();
			unsentNoticeEntity.setNoticeId(noticeId);
			unsentNoticeEntity.setTerminalId(terminalEntity.getTerminalId());
			
			unsentNoticeEntityMapper.insertSelective(unsentNoticeEntity);
		});
		
		return noticeId;
	}
}
