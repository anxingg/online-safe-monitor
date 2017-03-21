package cn.com.wh.company.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 安全管理机构类
 * @author lilipo
 *
 */
@Entity
@Table(name="tb_wuhai_safety_institutions")
public class SafetyInstitutions implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3538930424288189269L;

	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vid;
    
    /**
     * 部门ID
     */
    @Column(name="group_id")
	private Integer groupId;
    
    /**
     * 部门名称
     */
    @Column(name="group_name")
    private String groupName;
    
    /**
     * 安委会成立原件ids（逗号前后分割）
     */
    @Column(name="create_file_ids")
    private String createFileIds;
    
    /**
     * 安全管理部门ids（逗号前后分割）
     */
    @Column(name="department_file_ids")
    private String departmentFileIds;
    
    /**
     * 组织机构（逗号前后分割）
     */
    @Column(name="group_file_ids")
    private String groupFileIds;
    
    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime;
    
    /**
     * 创建人
     */
    @Column(name="create_user_id")
    private Integer createUserId;
    
    /**
     * 数据权限
     */
    @Column(name="is_fork_group")
    private Integer isForkGroup;
    
    /**
     * 是否删除
     */
    @Column(name="is_delete")
    private Integer isDelete;

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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCreateFileIds() {
		return createFileIds;
	}

	public void setCreateFileIds(String createFileIds) {
		this.createFileIds = createFileIds;
	}

	public String getDepartmentFileIds() {
		return departmentFileIds;
	}

	public void setDepartmentFileIds(String departmentFileIds) {
		this.departmentFileIds = departmentFileIds;
	}

	public String getGroupFileIds() {
		return groupFileIds;
	}

	public void setGroupFileIds(String groupFileIds) {
		this.groupFileIds = groupFileIds;
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
    
	
}
