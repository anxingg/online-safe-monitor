package cn.com.wh.training.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.DeleteState;

/**
 * 
 * 功能:培训表
 * 版本: 1.0
 * 开发人员: wangy
 * 创建日期: 2015年8月27日
 * 修改日期: 2015年8月27日
 * 修改列表:
 */
@Entity
@Table(name = "tb_wuhai_training")
public class Training implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VID",unique=true,nullable=false)
	private Integer id;//主键
	
	@Column(name="GROUP_ID")
	private Integer groupId;//组名称
	
	@Column(name="COMPANY_NAME")
	private String companyName;//公司名称
	
	@Column(name="TRAIN_YEAR")
	private Integer  trainYear;//培训年度
	
	@Column(name="TRAIN_RATE")
	private String trainRate;//培训时间（频率）
	
	@Column(name="SUBJECT")
	private String subject;//培训名称
	
	@Column(name="DETAILS",columnDefinition="text")
	private String details;//培训内容
	
	@Column(name="CHARGE")
	private String charge;//负责人
	
	@Column(name="TRAIN_TIME")
	private Date trainTime;//培训时间
	
	@Column(name="TRAIN_OBJECT")
	private String trainObject;//培训对象
	
	@Column(name="TRAIN_FORM")
	private String trainForm;//培训形式
	
	@Column(name="MEMO")
	private String memo;//备注
	
	@Column(name="SPEAKER")
	private String speaker;//主讲人
	
	@Column(name="ADDRESS")
	private String address;//培训地点
	
	@Column(name="SCHOOL")
	private Integer school;//培训学时
	
	@Column(name="EFFECT")
	private String effect;//效果评价和改进措施
	
	@Column(name="TRAIN_TYPE")
	private Integer trainType;//培训类型 0.计划管理   1.记录
	
	@Column(name="CREATE_TIME")
	private Date createTime;//创建时间
	
	@Column(name="LAST_UPDATE_TIME")
	private Date updateTime;//修改时间
	
	@Column(name="attachment_ids")
	private String attachmentIds;//附件id
	
	@Column(name="attachName")
	private String attachName;//附件名称
	
	/**
	 * 数据权限
	 */
	@Column(name="IS_FORK_GROUP")
	private Integer isForkGroup;
	
	/**
	 * 删除状态
	 */
	@DeleteState
	@Column(name="Is_Delete")
	private Integer isDelete = 0;
	
	@Column(name="num")
	private Integer num;//培训人数

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getTrainYear() {
		return trainYear;
	}

	public void setTrainYear(Integer trainYear) {
		this.trainYear = trainYear;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public Date getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(Date trainTime) {
		this.trainTime = trainTime;
	}

	public String getTrainObject() {
		return trainObject;
	}

	public void setTrainObject(String trainObject) {
		this.trainObject = trainObject;
	}

	public String getTrainForm() {
		return trainForm;
	}

	public void setTrainForm(String trainForm) {
		this.trainForm = trainForm;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSchool() {
		return school;
	}

	public void setSchool(Integer school) {
		this.school = school;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public Integer getTrainType() {
		return trainType;
	}

	public void setTrainType(Integer trainType) {
		this.trainType = trainType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getTrainRate() {
		return trainRate;
	}

	public void setTrainRate(String trainRate) {
		this.trainRate = trainRate;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getAttachmentIds() {
		return attachmentIds;
	}

	public void setAttachmentIds(String attachmentIds) {
		this.attachmentIds = attachmentIds;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	
	
}
