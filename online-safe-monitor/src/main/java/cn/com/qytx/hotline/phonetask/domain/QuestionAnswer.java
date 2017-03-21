package cn.com.qytx.hotline.phonetask.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.base.domain.DeleteState;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 答案试题
 * 
 * @author liyanlei
 * 
 * @date : Apr 15, 2014 3:40:49 PM
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tb_question_answer")
public class QuestionAnswer extends BaseDomain implements Serializable {
	/**
	 * 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 创建时间设置当前时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime = new Timestamp(Calendar.getInstance()
			.getTimeInMillis());
	/**
	 * 修改时间设置当前时间
	 */
	@Column(name = "update_time")
	private Timestamp updateTime = new Timestamp(Calendar.getInstance()
			.getTimeInMillis());

	/**
	 * 答卷 人
	 */
	@JoinColumn(name = "create_user_id", updatable = false)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private PhoneTaskUser createUser;
	/**
	 * 修改人
	 */
	@JoinColumn(name = "last_update_user_id")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private UserInfo updateUser;
	/**
	 * 删除状态
	 */
	@DeleteState
	@Column(name = "is_delete")
	private Integer isDelete = 0;

	/**
	 * 公司ID
	 */
	@Column(name = "compy_id")
	private Integer compyId;
	/**
	 * 试卷答案
	 */
	private String answer;
	/**
	 * 所属试题
	 */
	@JoinColumn(name = "question_id")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Question question;
	/**
	 * 所属试卷
	 */
	@JoinColumn(name = "info_id")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Questionnaire questionnaire;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "create_time", updatable = false)
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "last_update_time")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public PhoneTaskUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(PhoneTaskUser createUser) {
		this.createUser = createUser;
	}

	public UserInfo getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(UserInfo updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "compy_id")
	public Integer getCompyId() {
		return compyId;
	}

	public void setCompyId(Integer compyId) {
		this.compyId = compyId;
	}

	@Column(name = "answer", length = 500)
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

}
