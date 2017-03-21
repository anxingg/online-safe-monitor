package cn.com.wh.professor.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.DeleteState;

/**
 * 安全生产专家实体类
 * @author 吴胜光
 * 创建时间： 2015年09月25日
 * 
 */
@Entity
@Table(name="tb_wuhai_safety_professor")

public class Professor implements java.io.Serializable {

	
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5547196364346810719L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vid;//主键
	
	@Column(name="name")
	private String name;//姓名
	
	@Column(name="professor_type")
	private Integer professorType;//专业类别(1.职业卫生专家2.非煤矿山专家3.危险化学品专家)
	
	@Column(name="specialties")
	private String specialties;//专业特长
	
	@Column(name="title")
	private String title;//技术职称
	
	@Column(name="work_company")
	private String workCompany;//工作单位
	
	@Column(name="phone")
	private String phone;//联系电话
	
	@Column(name="memo")
	private String memo;//备注
	
	@Column(name="create_time")
	private Timestamp createTime;//创建时间
	
	@Column(name="create_user_id")
	private Integer createUserId;//创建人
	
	@Column(name="is_fork_group")
	private Integer isForkGroup;//数据权限
	
	@DeleteState
	@Column(name="is_delete")
	private Integer isDelete;//是否删除
	

	/** default constructor */
	public Professor() {
	}


	public Integer getVid() {
		return vid;
	}


	public void setVid(Integer vid) {
		this.vid = vid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getProfessorType() {
		return professorType;
	}


	public void setProfessorType(Integer professorType) {
		this.professorType = professorType;
	}


	public String getSpecialties() {
		return specialties;
	}


	public void setSpecialties(String specialties) {
		this.specialties = specialties;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getWorkCompany() {
		return workCompany;
	}


	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Timestamp createTime) {
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