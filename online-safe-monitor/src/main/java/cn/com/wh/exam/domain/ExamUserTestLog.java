package cn.com.wh.exam.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 
 * 功能:答题日志表
 * 版本: 1.0
 * 开发人员: wangy
 * 创建日期: 2015年8月25日
 * 修改日期: 2015年8月25日
 * 修改列表:
 */
@Entity
@Table(name = "tb_wuhai_user_test_log")
public class ExamUserTestLog implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VID",unique=true,nullable=false)
	private Integer id;//主键
	
	@Column(name="USER_TEST_ID")
	private Integer userTestId;//人员成绩对应id
	
	@Column(name="QUESTION_ID")
	private Integer questionId;//试题id
	
	@Column(name="QUESTION_NAME")
	private String questName;//试题名称
	
	@Column(name="ALL_ITEM")
	private String allItem;//试题选项
	
	@Column(name="USER_CHECK_ITEM")
	private String userCheckItem;//用户选择答案
	
	@Column(name="RIGHT_ITEM")
	private String rightItem;//正确选项
	
	@Transient
	private List<ExamQuestionItem> examQuestionItemList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserTestId() {
		return userTestId;
	}

	public void setUserTestId(Integer userTestId) {
		this.userTestId = userTestId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestName() {
		return questName;
	}

	public void setQuestName(String questName) {
		this.questName = questName;
	}

	public String getAllItem() {
		return allItem;
	}

	public void setAllItem(String allItem) {
		this.allItem = allItem;
	}

	public String getUserCheckItem() {
		return userCheckItem;
	}

	public void setUserCheckItem(String userCheckItem) {
		this.userCheckItem = userCheckItem;
	}

	public String getRightItem() {
		return rightItem;
	}

	public void setRightItem(String rightItem) {
		this.rightItem = rightItem;
	}

	public List<ExamQuestionItem> getExamQuestionItemList() {
		return examQuestionItemList;
	}

	public void setExamQuestionItemList(List<ExamQuestionItem> examQuestionItemList) {
		this.examQuestionItemList = examQuestionItemList;
	}
	
	
}
