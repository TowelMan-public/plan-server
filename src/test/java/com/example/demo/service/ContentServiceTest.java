package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.exception.NotSelectedAsTodoResponsibleException;
import com.example.demo.form.ContentForm;
import com.example.demo.response.ContentResponse;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DatabaseSetup("/dbunit/service/ContentServiceTest/pettern.xml")
public class ContentServiceTest {
	@Autowired
	ContentService service;
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/insert_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_2() {//todpOnProjet in publicProject 権限者
		var userId = -1;
		var contentId = -1;
		var form = new ContentForm();
		//TODO
		
		assertThat(service.insert(userId, form)).isEqualTo(contentId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/insert_3.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_3() {//todpOnProjet in privateProject
		var userId = -1;
		var contentId = -1;
		var form = new ContentForm();
		//TODO
		
		assertThat(service.insert(userId, form)).isEqualTo(contentId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/insert_4.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void insert_4() {//todpOnResponsible in publicProject
		var userId = -1;
		var contentId = -1;
		var form = new ContentForm();
		//TODO
		
		assertThat(service.insert(userId, form)).isEqualTo(contentId);
	}
	
	@Transactional 
	@Test
	public void insert_5() {
		var userId = -1;
		var form = new ContentForm();
		//TODO
		
		assertThrows(NotFoundValueException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void insert_6() {//publicProject
		var userId = -1;
		var form = new ContentForm();
		//TODO
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void insert_7() {
		var userId = -1;
		var form = new ContentForm();
		//TODO
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void insert_8() {//privateProject
		var userId = -1;
		var form = new ContentForm();
		//TODO
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.insert(userId, form));
	}
	
	@Transactional 
	@Test
	public void getList_1() {//publicProject 加入者
		var userId = -1;
		var todoId = -1;
		List<ContentResponse> expectList = new ArrayList<>();
		var expect = new ContentResponse();
		
		assertThat(service.getList(userId, todoId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getList_2() {//privateProject
		var userId = -1;
		var todoId = -1;
		List<ContentResponse> expectList = new ArrayList<>();
		var expect = new ContentResponse();
		
		assertThat(service.getList(userId, todoId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getList_3() {// todoOnResponsible 自分のみ
		var userId = -1;
		var todoId = -1;
		List<ContentResponse> expectList = new ArrayList<>();
		var expect = new ContentResponse();
		
		assertThat(service.getList(userId, todoId)).containsExactlyElementsOf(expectList);
	}
	
	@Transactional 
	@Test
	public void getList_4() {
		var userId = -1;
		var todoId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.getList(userId, todoId));
	}
	
	@Transactional 
	@Test
	public void getList_5() {
		var userId = -1;
		var todoId = -1;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.getList(userId, todoId));
	}
	
	@Transactional 
	@Test
	public void getList_6() {
		var userId = -1;
		var todoId = -1;
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.getList(userId, todoId));
	}
	
	@Transactional 
	@Test
	public void getList_7() {
		var userId = -1;
		var todoId = -1;
		
		assertThrows(NotJoinedPublicProjectException.class ,
				() -> service.getList(userId, todoId));
	}
	
	@Transactional 
	@Test
	public void get_1() {//publicProject 加入者
		var userId = -1;
		var contentId = -1;
		var expect = new ContentResponse();
		//TODO
		
		assertThat(service.get(userId, contentId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	void get_2() {//privateProject
		var userId = -1;
		var contentId = -1;
		var expect = new ContentResponse();
		//TODO
		
		assertThat(service.get(userId, contentId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_3() {// todoOnResponsible 自分のみ
		var userId = -1;
		var contentId = -1;
		var expect = new ContentResponse();
		//TODO
		
		assertThat(service.get(userId, contentId)).isEqualTo(expect);
	}
	
	@Transactional 
	@Test
	public void get_4() {
		var userId = -1;
		var contentId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.get(userId, contentId));
	}
	
	@Transactional 
	@Test
	public void get_5() {
		var userId = -1;
		var contentId = -1;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.get(userId, contentId));
	}
	
	@Transactional 
	@Test
	public void get_6() {
		var userId = -1;
		var contentId = -1;
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.get(userId, contentId));
	}
	
	@Transactional 
	@Test
	public void get_7() {
		var userId = -1;
		var contentId = -1;
		
		assertThrows(NotJoinedPublicProjectException.class ,
				() -> service.get(userId, contentId));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/updateTitle_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateTitle_1() {//todpOnProjet in publicProject 権限者
		var userId = -1;
		var contentId = -1;
		var contentTitle = "";
		
		service.updateTitle(userId, contentId, contentTitle);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/updateTitle_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateTitle_2() {//todpOnProjet in privateProject
		var userId = -1;
		var contentId = -1;
		var contentTitle = "";
		
		service.updateTitle(userId, contentId, contentTitle);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/updateTitle_3.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateTitle_3() {//todpOnResponsible in publicProject
		var userId = -1;
		var contentId = -1;
		var contentTitle = "";
		
		service.updateTitle(userId, contentId, contentTitle);
	}
	
	@Transactional 
	@Test
	public void updateTitle_4() {
		var userId = -1;
		var contentId = -1;
		var contentTitle = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateTitle(userId, contentId, contentTitle));
	}
	
	@Transactional 
	@Test
	public void updateTitle_5() {//publicProject
		var userId = -1;
		var contentId = -1;
		var contentTitle = "";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateTitle(userId, contentId, contentTitle));
	}
	
	@Transactional 
	@Test
	public void updateTitle_6() {
		var userId = -1;
		var contentId = -1;
		var contentTitle = "";
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.updateTitle(userId, contentId, contentTitle));
	}
	
	@Transactional 
	@Test
	public void updateTitle_7() {//privateProject
		var userId = -1;
		var contentId = -1;
		var contentTitle = "";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateTitle(userId, contentId, contentTitle));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/updateExplanation_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateExplanation_1() {//todpOnProjet in publicProject 権限者
		var userId = -1;
		var contentId = -1;
		var contentExplanation = "";
		
		service.updateExplanation(userId, contentId, contentExplanation);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/updateExplanation_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateExplanation_2() {//todpOnProjet in privateProject
		var userId = -1;
		var contentId = -1;
		var contentExplanation = "";
		
		service.updateExplanation(userId, contentId, contentExplanation);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/updateExplanation_3.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateExplanation_3() {//todpOnResponsible in publicProject
		var userId = -1;
		var contentId = -1;
		var contentExplanation = "";
		
		service.updateExplanation(userId, contentId, contentExplanation);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/updateExplanation_4.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void updateExplanation_4() {
		var userId = -1;
		var contentId = -1;
		var contentExplanation = "";
		
		assertThrows(NotFoundValueException.class ,
				() -> service.updateExplanation(userId, contentId, contentExplanation));
	}
	
	@Transactional 
	@Test
	public void updateExplanation_5() {//publicProject
		var userId = -1;
		var contentId = -1;
		var contentExplanation = "";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateExplanation(userId, contentId, contentExplanation));
	}
	
	@Transactional 
	@Test
	public void updateExplanation_6() {
		var userId = -1;
		var contentId = -1;
		var contentExplanation = "";
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.updateExplanation(userId, contentId, contentExplanation));
	}
	
	@Transactional 
	@Test
	public void updateExplanation_7() {//privateProject
		var userId = -1;
		var contentId = -1;
		var contentExplanation = "";
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.updateExplanation(userId, contentId, contentExplanation));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/delete_1.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_1() {//todpOnProjet in publicProject 権限者
		var userId = -1;
		var contentId = -1;
		
		service.delete(userId, contentId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/delete_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_2() {//todpOnProjet in privateProject
		var userId = -1;
		var contentId = -1;
		
		service.delete(userId, contentId);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/delete_3.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void delete_3() {//todpOnResponsible in publicProject
		var userId = -1;
		var contentId = -1;
		
		service.delete(userId, contentId);
	}
	
	@Transactional 
	@Test
	public void delete_4() {
		var userId = -1;
		var contentId = -1;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.delete(userId, contentId));
	}
	
	@Transactional 
	@Test
	public void delete_5() {//publicProject
		var userId = -1;
		var contentId = -1;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.delete(userId, contentId));
	}
	
	@Transactional 
	@Test
	public void delete_6() {
		var userId = -1;
		var contentId = -1;
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.delete(userId, contentId));
	}
	
	@Transactional 
	@Test
	public void delete_7() {//privateProject
		var userId = -1;
		var contentId = -1;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.delete(userId, contentId));
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/setIsCompleted.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_1() {//todpOnProjet in publicProject 権限者
		var userId = -1;
		var contentId = -1;
		var isCompleted = true;
		
		service.setIsCompleted(userId, contentId, isCompleted);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/setIsCompleted_2.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_2() {//todpOnProjet in privateProject
		var userId = -1;
		var contentId = -1;
		var isCompleted = true;
		
		service.setIsCompleted(userId, contentId, isCompleted);
	}
	
	@Transactional 
	@Test
	@ExpectedDatabase(
		    value="/dbunit/service/ContentServiceTest/expect/setIsCompleted_3.xml",
		    assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
	)
	public void setIsCompleted_3() {//todpOnResponsible in publicProject
		var userId = -1;
		var contentId = -1;
		var isCompleted = true;
		
		service.setIsCompleted(userId, contentId, isCompleted);
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_4() {
		var userId = -1;
		var contentId = -1;
		var isCompleted = true;
		
		assertThrows(NotFoundValueException.class ,
				() -> service.setIsCompleted(userId, contentId, isCompleted));
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_5() {//publicProject
		var userId = -1;
		var contentId = -1;
		var isCompleted = true;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.setIsCompleted(userId, contentId, isCompleted));
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_6() {
		var userId = -1;
		var contentId = -1;
		var isCompleted = true;
		
		assertThrows(NotSelectedAsTodoResponsibleException.class ,
				() -> service.setIsCompleted(userId, contentId, isCompleted));
	}
	
	@Transactional 
	@Test
	public void setIsCompleted_7() {//privateProject
		var userId = -1;
		var contentId = -1;
		var isCompleted = true;
		
		assertThrows(NotHaveAuthorityToOperateProjectException.class ,
				() -> service.setIsCompleted(userId, contentId, isCompleted));
	}
}
