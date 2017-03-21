package cn.com.wh.process.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.qytx.cbb.file.domain.Attachment;
import cn.com.qytx.platform.base.domain.DeleteState;


/**
 * 
 * <br/>功能:工艺流程
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年9月30日
 * <br/>修改日期: 2015年9月30日
 * <br/>修改列表:
 */
@Entity
@Table(name = "tb_wuhai_process")
public class TechnologicalProcess implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VID",unique=true,nullable=false)
	private Integer id;//主键
	
	/**
	 * 公司id（部门id）
	 */
	@Column(name="group_id")
	private Integer groupId;
	
	/**
	 * 公司名称
	 */
	@Column(name="group_name")
	private String groupName;
	
	/**
	 * 工艺名称
	 */
	@Column(name="title")
	private String title;
	
	/**
	 * 工艺介绍
	 */
	@Column(name="introduction")
	private String introduction;
	
	/**
	 * 工艺介绍html
	 */
	@Column(name="introduction_html")
	private String introductionHtml;
	
	/**
	 * 附件
	 */
	@Column(name="file_ids")
	private String fileIds;
	
	/**
	 * 创建时间
	 */
	@Column(name="Create_Time")
	private Date createTime;
	
	/**
	 * 创建人id
	 */
	@Column(name="create_user_id")
	private Integer createUserId;
	
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
	
	@Transient
	private List<Attachment> attachmentList = null;

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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
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

	public String getIntroductionHtml() {
		return introductionHtml;
	}

	public void setIntroductionHtml(String introductionHtml) {
		this.introductionHtml = introductionHtml;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
	
	
	
}