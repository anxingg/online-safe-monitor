package cn.com.qytx.hotline.phonetask.domain;

import java.io.Serializable;
import java.util.LinkedList;
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

/**
 * 
 * 功能:外呼任务用户 版本: 1.0 开发人员: 徐长江 创建日期: 2014-2-11 修改日期: 2014-2-11 修改列表:
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tb_phone_task_user")
public class PhoneTaskUser extends BaseDomain implements Serializable{
	/**
	 * 主键 唯一标识
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vid;
	/**
	 * 任务Id
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id")
	private PhoneTask taskId;
	/**
	 * 用户姓名
	 */
	@Column(name = "user_name")
	private String userName;
	/**
	 * 备用号码
	 */
	@Column(name = "tele_phone")
	private String telephone;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 用户性别
	 */
	private String sex;
	/**
	 * 用户地址
	 */
	private String address;

	@Column(name = "crm_id")
	private int crmId;

	/**
	 * 用户类型
	 */
	@Column(name = "user_type")
	private Integer userType;
	/**
	 * 被调查用户
	 */
	@JoinColumn(name = "survey_id")
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private QuestionnaireUser surveyUser;

	@OrderBy("id asc")
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "createUser")
	private List<QuestionAnswer> questionAnswer = new LinkedList<QuestionAnswer>();
	/**
	 * 备注
	 */
	private String notice;
	
	
	/**
     * 权限控制
     */
    @Column(name="is_fork_group")
    private Integer isForkGroup;

	/**
	 * @return the vid
	 */
	public Integer getVid() {
		return vid;
	}

	/**
	 * @param vid
	 *            the vid to set
	 */
	public void setVid(Integer vid) {
		this.vid = vid;
	}

	/**
	 * @return the taskId
	 */
	public PhoneTask getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(PhoneTask taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the userType
	 */
	public Integer getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * @return the notice
	 */
	public String getNotice() {
		return notice;
	}

	/**
	 * @param notice
	 *            the notice to set
	 */
	public void setNotice(String notice) {
		this.notice = notice;
	}

	/**
	 * @return the crmId
	 */
	public int getCrmId() {
		return crmId;
	}

	/**
	 * @param crmId
	 *            the crmId to set
	 */
	public void setCrmId(int crmId) {
		this.crmId = crmId;
	}

	/**
	 * @return the surveyUser
	 */
	public QuestionnaireUser getSurveyUser() {
		return surveyUser;
	}

	/**
	 * @param surveyUser
	 *            the surveyUser to set
	 */
	public void setSurveyUser(QuestionnaireUser surveyUser) {
		this.surveyUser = surveyUser;
	}

	/**
	 * @return the questionAnswer
	 */
	public List<QuestionAnswer> getQuestionAnswer() {
		return questionAnswer;
	}

	/**
	 * @param questionAnswer
	 *            the questionAnswer to set
	 */
	public void setQuestionAnswer(List<QuestionAnswer> questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + crmId;
		result = prime * result
				+ ((isForkGroup == null) ? 0 : isForkGroup.hashCode());
		result = prime * result + ((notice == null) ? 0 : notice.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result
				+ ((questionAnswer == null) ? 0 : questionAnswer.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result
				+ ((surveyUser == null) ? 0 : surveyUser.hashCode());
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		result = prime * result
				+ ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result
				+ ((userType == null) ? 0 : userType.hashCode());
		result = prime * result + ((vid == null) ? 0 : vid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (!super.equals(obj)){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		PhoneTaskUser other = (PhoneTaskUser) obj;
		
		if (telephone == null) {
			if (other.telephone != null){
				return false;
			}
		} else if (!telephone.equals(other.telephone)){
			return false;
		}
		if (userName == null) {
			if (other.userName != null){
				return false;
			}
		} else if (!userName.equals(other.userName)){
			return false;
		}
		if (userType == null) {
			if (other.userType != null){
				return false;
			}
		} else if (!userType.equals(other.userType)){
			return false;
		}
		if (vid == null) {
			if (other.vid != null){
				return false;
			}
		} else if (!vid.equals(other.vid)){
			return false;
		}
		if (taskId == null) {
			if (other.taskId != null){
				return false;
			}
		} else if (!taskId.equals(other.taskId)){
			return false;
		}
		return true && sub01(other);
	}

	public boolean sub01(PhoneTaskUser other){
		if (address == null) {
			if (other.address != null){
				return false;
			}
		} else if (!address.equals(other.address)){
			return false;
		}
		if (crmId != other.crmId){
			return false;
		}
		if (isForkGroup == null) {
			if (other.isForkGroup != null){
				return false;
			}
		} else if (!isForkGroup.equals(other.isForkGroup)){
			return false;
		}
		if (notice == null) {
			if (other.notice != null){
				return false;
			}
		} else if (!notice.equals(other.notice)){
			return false;
		}
		if (phone == null) {
			if (other.phone != null){
				return false;
			}
		} else if (!phone.equals(other.phone)){
			return false;
		}
		if (questionAnswer == null) {
			if (other.questionAnswer != null){
				return false;
			}
		} else if (!questionAnswer.equals(other.questionAnswer)){
			return false;
		}
		if (sex == null) {
			if (other.sex != null){
				return false;
			}
		} else if (!sex.equals(other.sex)){
			return false;
		}
		if (surveyUser == null) {
			if (other.surveyUser != null){
				return false;
			}
		} else if (!surveyUser.equals(other.surveyUser)){
			return false;
		}
		
		
		return true;
	}


	

}
