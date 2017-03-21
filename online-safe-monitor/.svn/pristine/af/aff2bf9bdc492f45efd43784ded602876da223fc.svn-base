package cn.com.wh.company.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbWuhaiCompanyPerson entity. @author MyEclipse Persistence Tools
 *  安全管理人员/特种作业人员/单位法人    公用实体类
 */

@Entity
@Table(name="tb_wuhai_company_person")
public class WHCompanyPerson implements java.io.Serializable {

	private static final long serialVersionUID = -2104023445751981458L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="person_id")
	private Integer personId;//人员ID
	
	@Column(name="group_id")
	private Integer groupId;//部门ID
	
	/**
     * 企业名称
     */
    @Column(name="company_name")
	private String companyName;
    
    
    @Column(name="card_type")
	private Integer cardType;
	
	@Column(name="person_type")
	private Integer personType;//人员类型 1.法人2.安全管理员3.特种作业人员
	
	@Column(name="name")
	private String name;//姓名
	
	@Column(name="sex")
	private Integer sex;//性别
	
	@Column(name="group_name")
	private String groupName;//部门
	
	@Column(name="phone")
	private String phone;//联系电话
	
	@Column(name="job")
	private String job;//职务
	
	@Column(name="id_number")
	private String idNumber;//身份证号
	
	@Column(name="education_degree")
	private String educationDegree;//文化程度
	
	@Column(name="work_type")
	private String workType;//工种
	
	@Column(name="job_title")
	private String jobTitle;//职称
	
	@Column(name="start_time")
	private Timestamp startTime;//上岗时间
	
	@Column(name="issuing_authority")
	private String issuingAuthority;//发证机关
	
	@Column(name="certificate_num")
	private String certificateNum;//证件号码
	
	@Column(name="certificate_get_time")
	private Timestamp certificateGetTime;//取证日期
	
	@Column(name="replace_date")
	private Timestamp replaceDate;//换证日期
	
	@Column(name="memo")
	private String memo;//备注
	
	@Column(name="is_fork_group")
	private Integer isForkGroup;//数据权限
	
	@Column(name="photo")
	private String photo;//照片
	
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
	public WHCompanyPerson() {
	}

	/** full constructor */
	public WHCompanyPerson(Integer groupId, Integer personType,
			String name, Integer sex, String groupName, String phone,
			String job, String idNumber, String educationDegree,String jobTitle,
			String workType, Timestamp startTime, String issuingAuthority,
			String certificateNum, Timestamp certificateGetTime, Timestamp replaceDate,
			String memo, Integer isForkGroup, String photo,
			Timestamp createTime, Integer createUserId,
			Timestamp lastUpdateTime, Integer lastUpdateUserId, Integer isDelete,String companyName) {
		this.groupId = groupId;
		this.personType = personType;
		this.name = name;
		this.sex = sex;
		this.groupName = groupName;
		this.phone = phone;
		this.job = job;
		this.jobTitle = jobTitle;
		this.idNumber = idNumber;
		this.educationDegree = educationDegree;
		this.workType = workType;
		this.startTime = startTime;
		this.issuingAuthority = issuingAuthority;
		this.certificateNum = certificateNum;
		this.certificateGetTime = certificateGetTime;
		this.replaceDate = replaceDate;
		this.memo = memo;
		this.isForkGroup = isForkGroup;
		this.photo = photo;
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.lastUpdateTime = lastUpdateTime;
		this.lastUpdateUserId = lastUpdateUserId;
		this.isDelete = isDelete;
		this.companyName = companyName;
	}

	// Property accessors

	public Integer getPersonId() {
		return this.personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getPersonType() {
		return this.personType;
	}

	public void setPersonType(Integer personType) {
		this.personType = personType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEducationDegree() {
		return this.educationDegree;
	}

	public void setEducationDegree(String educationDegree) {
		this.educationDegree = educationDegree;
	}

	
	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public String getIssuingAuthority() {
		return this.issuingAuthority;
	}

	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}

	public String getCertificateNum() {
		return this.certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public Date getCertificateGetTime() {
		return this.certificateGetTime;
	}

	public void setCertificateGetTime(Timestamp certificateGetTime) {
		this.certificateGetTime = certificateGetTime;
	}

	public Date getReplaceDate() {
		return this.replaceDate;
	}

	public void setReplaceDate(Timestamp replaceDate) {
		this.replaceDate = replaceDate;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getIsForkGroup() {
		return this.isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

}