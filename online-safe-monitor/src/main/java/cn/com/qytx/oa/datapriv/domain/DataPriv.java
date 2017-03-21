package cn.com.qytx.oa.datapriv.domain;

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
 * 功能:数据权限表
 * 版本: 1.0
 * 开发人员: zhangjingjing
 * 创建日期: 2014-6-26
 * 修改日期: 2014-6-26
 * 修改列表: 
 */
@Entity
@Table(name="tb_cbb_dataPriv")
public class DataPriv{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    @Column(name = "createUserId")
	private int createUserId;
    @Column(name="createTime")
	private Timestamp createTime;
    @Column(name="lastUpdateTime")
	private Timestamp lastUpdateTime;
    @Column(name="lastUpdateUserId")
	private int lastUpdateUserId;
    @Column(name="compyId")
	private int compyId;
	@Column(name="moduleName",length=200)
	private String moduleName;//模块名称
	@Column(name="subModuleName",length=100)
	private String subModuleName;//子模块名称
	@Column(name="refId")
	private Integer refId;//数据ID
	@Column(name="userIds",length=5000)
	private String userIds;//人员ID集合
	@Column(name="groupIds",length=5000)
	private String groupIds;//群组ID集合
	@Column(name="roleIds",length=5000)
	private String roleIds;//角色ID集合
	@Column(name="groupNames",length=5000)
	private String groupNames;//部门名
	@Column(name="roleNames",length=5000)
	private String roleNames;//角色名
	@Column(name="userNames",length=5000)
	private String userNames;// 用户名
	@Column(name="refName",length=50)
	private String refName;//数据名称
	@DeleteState
	@Column(name = "IsDelete")
	private int isDelete;
	@Transient
	private String param1;
	
	
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getCompyId() {
		return compyId;
	}
	public void setCompyId(int compyId) {
		this.compyId = compyId;
	}
	
	public int getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	public void setLastUpdateUserId(int lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getSubModuleName() {
		return subModuleName;
	}
	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}
	public Integer getRefId() {
		return refId;
	}
	public void setRefId(Integer refId) {
		this.refId = refId;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public String getGroupNames() {
		return groupNames;
	}
	public void setGroupNames(String groupNames) {
		this.groupNames = groupNames;
	}
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public String getUserNames() {
		return userNames;
	}
	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}
	

}