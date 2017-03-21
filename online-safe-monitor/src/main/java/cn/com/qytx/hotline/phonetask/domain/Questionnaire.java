package cn.com.qytx.hotline.phonetask.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.base.domain.DeleteState;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 试卷实体类
 * 
 * @author liyanlei
 * 
 * @date : Apr 15, 2014 3:12:09 PM
 * 
 * @version 1.0
 */
@Entity
@Table(name = "tb_questionnaire")
public class Questionnaire extends BaseDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	 * 试卷标题
	 */
	private String title;
	/**
	 * 试卷说明
	 */
	private String des;
	/**
	 * 结束时间
	 */
	@Column(name = "end_time")
	private Timestamp endTime;
	/**
	 * 是否参数抽奖 1：参与 0 不参与
	 */
	@Column(name = "is_award")
	private boolean isAward;
	/**
	 * 
	 * 新版本 1：草稿 2：进行中 4：已结束
	 */
	@Column(name = "state")
	private Integer state;

	/**
	 * 发布时间
	 */
	@Column(name = "publish_date")
	private Date publishDate;

	/**
	 * 匿名答题 1 是 0 是否
	 */
	@Column(name = "is_anonymity")
	private Integer isAnonymity;

	/**
	 * 是否已抽奖0，1
	 */
	@Column(name = "is_lottery")
	private Integer isLottery = 0;

	/**
	 * 试题项
	 */
	@OrderBy("orderList asc")
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "questionnaire")
	private List<Question> questions = new ArrayList<Question>();

	/**
	 * 调查人问卷
	 */
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "questionnaire")
	private List<QuestionnaireUser> questionnaireUsers = new ArrayList<QuestionnaireUser>();
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "questionnaire")
	private PhoneTask phoneTask;
	
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

	@Column(name = "title", length = 500)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "des", length = 500)
	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Column(name = "end_time")
	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "is_award")
	public boolean isAward() {
		return isAward;
	}

	public void setAward(boolean isAward) {
		this.isAward = isAward;
	}

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Column(name = "publish_date")
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public List<QuestionnaireUser> getQuestionnaireUsers() {
		return questionnaireUsers;
	}

	public void setQuestionnaireUsers(List<QuestionnaireUser> questionnaireUsers) {
		this.questionnaireUsers = questionnaireUsers;
	}

	public Integer getIsAnonymity() {
		return isAnonymity;
	}

	public void setIsAnonymity(Integer isAnonymity) {
		this.isAnonymity = isAnonymity;
	}

	public Integer getIsLottery() {
		return isLottery;
	}

	public void setIsLottery(Integer isLottery) {
		this.isLottery = isLottery;
	}

	/**
	 * @return the phoneTask
	 */
	public PhoneTask getPhoneTask() {
		return phoneTask;
	}

	/**
	 * @param phoneTask
	 *            the phoneTask to set
	 */
	public void setPhoneTask(PhoneTask phoneTask) {
		this.phoneTask = phoneTask;
	}
/**
 * 
 * 功能：组拼问题字符串
 * @return
 */
	public String getQuestionListStr(){
		StringBuffer sbf = new StringBuffer();
		if(questions!=null){
			int i = 1 ;
			for(Question q : questions){
				sbf.append(q.getQuestionStr(i));
				++i;
			}
		}
		return sbf.toString();
	}
}
