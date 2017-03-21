package cn.com.wh.safeaccident.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.qytx.platform.base.domain.DeleteState;

/**
 * 安全事故实体类
 * @author 李立泼
 * 创建时间： 2015年08月21日
 * 
 */
@Entity
@Table(name="tb_wuhai_safe_accident")
public class SafeAccident implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2067875761109939118L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="vid")
	private Integer vid;//主键
	
	@Column(name="group_id")
	private Integer groupId;//企业ID
	
	@Column(name="accident_name")
	private String accidentName;//安全事故名称
	
	@Column(name="occurred_time")
	private Date occurredTime;//发生时间
	
	@Transient
	private String occurredTimeStr;//发生时间字符串（传参用）
	
	@Column(name="occurred_address")
	private String occurredAddress;//发生地点
	
	@Column(name="responsible")
	private String responsible;//责任人（对应事调查人员/部门）
	
	@Column(name="responsible_phone")
	private String responsiblePhone;//责任人电话
	
	@Column(name="accident_character")
	private Integer accidentCharacter;//事故性质
	
	@Column(name="occurred_description")
	private String occurredDescription;//事故简介
	
	@Column(name="occurred_reason")
	private String occurredReason;//事故原因
	
	@Column(name="occurred_consequence")
	private String occurredConsequence;//事故后果
	
	@Column(name="process_condition")
	private String processCondition;//事故处理情况
	
	@Column(name="memo")
	private String memo;//备注
	
	@Column(name="report_id")
	private String reportId;//事故报告ID
	
	@Column(name="report_path")
	private String reportPath;//事故报告
	
	@Column(name="report_name")
	private String reportName;//事故报告的名称
	
	@Column(name="data_source")
	private Integer dataSource;//数据来源（1、政府； 2、企业）
	
	@Column(name="can_see")
	private Integer canSee;//企业端是否可见（0/NULL、不可见； 1、可见）
	
	@Column(name="is_fork_group")
	private Integer isForkGroup;//数据权限
	
	@Column(name="create_time")
	private Date createTime;//创建时间
	
	@Column(name="create_user")
	private Integer createUser;//创建人
	
	@Column(name="last_update_user")
	private Integer lastUpdateUser;//最后修改人
	
	@Column(name="last_update_time")
	private Date lastUpdateTime;//最后修改时间
	
	@DeleteState
	@Column(name="is_delete")
	private Integer isDelete;//是否删除
	
	@Transient
	private String keyword;//关键字（传参用）


	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getAccidentName() {
		return accidentName;
	}

	public void setAccidentName(String accidentName) {
		this.accidentName = accidentName;
	}

	public Date getOccurredTime() {
		return occurredTime;
	}

	public void setOccurredTime(Date occurredTime) {
		this.occurredTime = occurredTime;
	}

	public String getOccurredAddress() {
		return occurredAddress;
	}

	public void setOccurredAddress(String occurredAddress) {
		this.occurredAddress = occurredAddress;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getResponsiblePhone() {
		return responsiblePhone;
	}

	public void setResponsiblePhone(String responsiblePhone) {
		this.responsiblePhone = responsiblePhone;
	}

	public Integer getAccidentCharacter() {
		return accidentCharacter;
	}

	public void setAccidentCharacter(Integer accidentCharacter) {
		this.accidentCharacter = accidentCharacter;
	}

	public String getOccurredDescription() {
		return occurredDescription;
	}

	public void setOccurredDescription(String occurredDescription) {
		this.occurredDescription = occurredDescription;
	}

	public String getOccurredReason() {
		return occurredReason;
	}

	public void setOccurredReason(String occurredReason) {
		this.occurredReason = occurredReason;
	}

	public String getOccurredConsequence() {
		return occurredConsequence;
	}

	public void setOccurredConsequence(String occurredConsequence) {
		this.occurredConsequence = occurredConsequence;
	}

	public String getProcessCondition() {
		return processCondition;
	}

	public void setProcessCondition(String processCondition) {
		this.processCondition = processCondition;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public Integer getDataSource() {
		return dataSource;
	}

	public void setDataSource(Integer dataSource) {
		this.dataSource = dataSource;
	}

	public Integer getCanSee() {
		return canSee;
	}

	public void setCanSee(Integer canSee) {
		this.canSee = canSee;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(Integer lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getOccurredTimeStr() {
		return occurredTimeStr;
	}

	public void setOccurredTimeStr(String occurredTimeStr) {
		this.occurredTimeStr = occurredTimeStr;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}