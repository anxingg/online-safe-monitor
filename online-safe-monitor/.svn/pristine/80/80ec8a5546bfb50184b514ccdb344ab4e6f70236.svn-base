package cn.com.wh.company.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbWuhaiCompanyPhoto entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="tb_wuhai_company_photo")
public class WHCompanyPhoto implements java.io.Serializable {

	private static final long serialVersionUID = 4705025293958385486L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vid;//id
	
	@Column(name="group_id")
	private Integer groupId;//部门id
	
	@Column(name="photo_type")
	private Integer photoType;//图片类型  123456
	
	@Column(name="attachment_path")
	private String attachmentPath;//图片相对路径
	
	@Column(name="attachment_id")
	private Integer attachmentId;//图片附件ID
	
	@Column(name="photo_name")
	private String photoName;//图片名称
	
	@Column(name="is_fork_group")
	private Integer isForkGroup;//数据权限
	
	@Column(name="create_time")
	private Timestamp createTime;//创建时间
	
	@Column(name="create_user_id")
	private Integer createUserId;//创建人
	
	@Column(name="last_update_time")
	private Timestamp lastUpdateTime;//最后修改时间
	
	@Column(name="last_update_user_id")
	private Integer lastUpdateUserId;//最后修改人
	
	@Column(name="is_delete")
	private Integer isDelete;//是否删除

	// Constructors

	/** default constructor */
	public WHCompanyPhoto() {
	}

	/** full constructor */
	


	public WHCompanyPhoto(Integer vid, Integer groupId, Integer photoType,
			String attachmentPath, Integer attachmentId, String photoName,
			Integer isForkGroup, Timestamp createTime, Integer createUserId,
			Timestamp lastUpdateTime, Integer lastUpdateUserId, Integer isDelete) {
		super();
		this.vid = vid;
		this.groupId = groupId;
		this.photoType = photoType;
		this.attachmentPath = attachmentPath;
		this.attachmentId = attachmentId;
		this.photoName = photoName;
		this.isForkGroup = isForkGroup;
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.lastUpdateTime = lastUpdateTime;
		this.lastUpdateUserId = lastUpdateUserId;
		this.isDelete = isDelete;
	}


	// Property accessors

	public Integer getVid() {
		return this.vid;
	}
	
	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getPhotoType() {
		return this.photoType;
	}

	public void setPhotoType(Integer photoType) {
		this.photoType = photoType;
	}

	public Integer getIsForkGroup() {
		return this.isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Timestamp getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getLastUpdateUserId() {
		return this.lastUpdateUserId;
	}

	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public Integer getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}
	
}