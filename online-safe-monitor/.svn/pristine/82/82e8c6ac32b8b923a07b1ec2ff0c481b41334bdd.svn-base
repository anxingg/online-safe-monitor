package cn.com.wh.plans.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.qytx.platform.base.domain.DeleteState;

/**
 * 应急预案实体类
 * @author 吴胜光
 * 创建时间： 2015年08月27日
 * 
 */
@Entity
@Table(name="tb_wuhai_contingency_plans")
public class Plans implements java.io.Serializable {

	private static final long serialVersionUID = 4705025193958385486L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vid;//id
	
	@Column(name="group_id")
	private Integer groupId;//部门id
	
	@Column(name="plan_no")
	private String planNo;//预案编号
	
	@Column(name="phone")
	private String phone;//企业电话
	
	@Column(name="plan_type")
	private Integer planType;//预案类型
	
	@Column(name="agent")
	private String agent;//经办人
	
	@Column(name="prepare_time")
	private Timestamp prepareTime;//备案时间
	
	@Column(name="prepare_end_time")
	private Timestamp prepareEndTime;//备案到期时间
	
	@Column(name="attachment_ids")
	private String attachmentIds;//附件id
	
	@Column(name="path")
	private String path;//附件路径
	
	@Column(name="attachName")
	private String attachName;//附件名称
	
	@Column(name="is_fork_group")
	private Integer isForkGroup;//数据权限
	
	@Column(name="create_time")
	private Timestamp createTime;//创建时间
	
	@DeleteState
	@Column(name="is_delete")
	private Integer isDelete;//是否删除
	
	@Transient
	private String prepareDate;//备案时间格式化形式
	@Transient
	private String prepareEndDate;//备案到期时间格式化形式
	@Transient
	private String planTypeName;//预案类型名称


	/** default constructor */
	public Plans() {
	}


	/** full constructor */
	public Plans(Integer vid, Integer groupId, String planNo, Integer planType,
			String agent, Timestamp prepareTime, Timestamp prepareEndTime,
			String attachmentIds, String path, Integer isForkGroup,
			Timestamp createTime, Integer isDelete, String phone) {
		super();
		this.vid = vid;
		this.groupId = groupId;
		this.planNo = planNo;
		this.planType = planType;
		this.agent = agent;
		this.prepareTime = prepareTime;
		this.prepareEndTime = prepareEndTime;
		this.attachmentIds = attachmentIds;
		this.path = path;
		this.isForkGroup = isForkGroup;
		this.createTime = createTime;
		this.isDelete = isDelete;
		this.phone = phone;
	}


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


	public String getPlanNo() {
		return planNo;
	}


	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}


	public Integer getPlanType() {
		return planType;
	}


	public void setPlanType(Integer planType) {
		this.planType = planType;
	}


	public String getAgent() {
		return agent;
	}


	public void setAgent(String agent) {
		this.agent = agent;
	}


	public Timestamp getPrepareTime() {
		return prepareTime;
	}


	public void setPrepareTime(Timestamp prepareTime) {
		this.prepareTime = prepareTime;
	}


	public Timestamp getPrepareEndTime() {
		return prepareEndTime;
	}


	public void setPrepareEndTime(Timestamp prepareEndTime) {
		this.prepareEndTime = prepareEndTime;
	}


	public String getAttachmentIds() {
		return attachmentIds;
	}


	public void setAttachmentIds(String attachmentIds) {
		this.attachmentIds = attachmentIds;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public Integer getIsForkGroup() {
		return isForkGroup;
	}


	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	public Integer getIsDelete() {
		return isDelete;
	}


	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAttachName() {
		return attachName;
	}


	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}


	public String getPrepareDate() {
		return prepareDate;
	}


	public void setPrepareDate(String prepareDate) {
		this.prepareDate = prepareDate;
	}


	public String getPrepareEndDate() {
		return prepareEndDate;
	}


	public void setPrepareEndDate(String prepareEndDate) {
		this.prepareEndDate = prepareEndDate;
	}


	public String getPlanTypeName() {
		return planTypeName;
	}


	public void setPlanTypeName(String planTypeName) {
		this.planTypeName = planTypeName;
	}

	
	
}