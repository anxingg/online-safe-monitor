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
import javax.persistence.Transient;

import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.base.domain.DeleteState;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 试题项
 * 
 * @author liyanlei
 * 
 * @date : Apr 15, 2014 3:28:45 PM
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tb_question_item")
public class QuestionItem extends BaseDomain implements Serializable {
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
	 * 创建人
	 */
	@JoinColumn(name = "create_user_id", updatable = false)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private UserInfo createUser;
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
	 * 试题内容
	 */
	private String content;
	/**
	 * 排序
	 */
	@Column(name = "order_list")
	private Integer orderList;
	/**
	 * 是否答案
	 */
	@Column(name = "is_answer")
	private boolean isAnswer;
	/**
	 * 所属试题
	 */
	@JoinColumn(name = "question_id")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Question question;
	/**
	 * 总人数
	 */
	@Transient
	private Integer totalCount;

	/**
	 * 所占比例
	 */
	@Transient
	private Integer scale;

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

	public UserInfo getCreateUser() {
		return createUser;
	}

	public void setCreateUser(UserInfo createUser) {
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

	@Column(name = "content", length = 500)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "order_list")
	public Integer getOrderList() {
		return orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

	@Column(name = "is_answer")
	public boolean isAnswer() {
		return isAnswer;
	}

	public void setAnswer(boolean isAnswer) {
		this.isAnswer = isAnswer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Transient
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Transient
	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

}
