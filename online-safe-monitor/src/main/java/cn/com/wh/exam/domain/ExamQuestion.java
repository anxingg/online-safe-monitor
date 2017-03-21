package cn.com.wh.exam.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.qytx.platform.base.domain.DeleteState;


/**
 * 
 * 功能:题库
 * 版本: 1.0
 * 开发人员: wangy
 * 创建日期: 2015年8月19日
 * 修改日期: 2015年8月19日
 * 修改列表:
 */
@Entity
@Table(name = "tb_wuhai_question")
public class ExamQuestion implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VID",unique=true,nullable=false)
	private Integer id;//实体
	
	/**
	 * 群组id
	 */
	@Column(name="Group_Id",length=100)
	private Integer groupId;
	
	/**
	 * 题目名称
	 */
	@Column(name="Title",length=100)
	private String title;
	
	/**
	 * 题目类型 0单选1多选
	 */
	@Column(name="Question_Type")
	private Integer questionType;
	
	/**
	 * 试题类型 1.岗前培训  2.月度培训 3.特种作业人员 4.年度培训
	 */
	@Column(name="Dict_ID")
	private Integer titleType;
	
	/**
	 * 分值
	 */
	@Column(name="Score")
	private Integer score;
	
	/**
	 * 正确答案
	 */
	@Column(name="Right_Answer")
	private String rightAnswer;
	
	
	/**
	 * 创建时间
	 */
	@Column(name="Create_Time")
	private Timestamp createTime;
	
	/**
	 * 排序号
	 */
	@Column(name="Order_Level")
	private Integer orderLevel;
	
	
	/**
	 * 删除状态
	 */
	@DeleteState
	@Column(name="Is_Delete")
	private Integer isDelete = 0;
	
	/**
	 * 数据权限
	 */
	@Column(name="IS_FORK_GROUP")
	private Integer isForkGroup;
	
	/**
	 * 最后更新时间
	 */
	@Column(name="LAST_UPDATE_TIME")
	private Timestamp lastUpdateTime;
	
	/**
	 * 状态 0生效 1失效
	 */
	@Column(name="state")
	private Integer state = 0;
	
	@Transient
	private List<ExamQuestionItem> questionItems= new ArrayList<ExamQuestionItem>();
	
	@Transient
	private Integer paperQuestionScore;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public Integer getTitleType() {
		return titleType;
	}

	public void setTitleType(Integer titleType) {
		this.titleType = titleType;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getOrderLevel() {
		return orderLevel;
	}

	public void setOrderLevel(Integer orderLevel) {
		this.orderLevel = orderLevel;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}



	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}



	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public List<ExamQuestionItem> getQuestionItems() {
		return questionItems;
	}

	public void setQuestionItems(List<ExamQuestionItem> questionItems) {
		this.questionItems = questionItems;
	}

	public Integer getPaperQuestionScore() {
		return paperQuestionScore;
	}

	public void setPaperQuestionScore(Integer paperQuestionScore) {
		this.paperQuestionScore = paperQuestionScore;
	}
	
	
	
}