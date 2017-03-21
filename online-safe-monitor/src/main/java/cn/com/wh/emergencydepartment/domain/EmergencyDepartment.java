package cn.com.wh.emergencydepartment.domain;

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
 * <br/>功能:应急机构
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年9月28日
 * <br/>修改日期: 2015年9月28日
 * <br/>修改列表:
 */
@Entity
@Table(name = "tb_wuhai_emergency_department")
public class EmergencyDepartment implements java.io.Serializable {
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
	 * 机构名称
	 */
	@Column(name="depart_name")
	private String departName;
	
	/**
	 * 机构类型 1.人员2.机构3.政府端添加
	 */
	@Column(name="depart_type")
	private Integer departType;
	
	/**
	 * 联系电话
	 */
	@Column(name="phone")
	private String phone;
	
	/**
	 * 集团号码
	 */
	@Column(name="group_number")
	private String groupNumber;
	
	/**
	 * 职务
	 */
	@Column(name="job")
	private String job;
	
	/**
	 * 是否在列表中显示1展示。0不展示；
	 */
	@Column(name="is_show")
	private Integer isShow;
	
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

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Integer getDepartType() {
		return departType;
	}

	public void setDepartType(Integer departType) {
		this.departType = departType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
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
	
	
	
	
}