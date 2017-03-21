package cn.com.wh.dangerchemicals.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.DeleteState;

/**
 * 公司危险化学品类型
 * @author lilipo
 *
 */
@Entity
@Table(name="tb_wuhai_company_danger_chemic")
public class CompanyDangerChemicals implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 526328870346703544L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="vid")
	private Integer vid;//主键
	
	@Column(name="GROUP_ID")
	private Integer groupId;//公司ID

	@Column(name="DANGER_ID")
	private Integer dangerId;//危险化学品ID
	
	@Column(name="MATERIAL_NAME")
	private String materialName;//物质名称（危险化学品名）

	@Column(name="STORAGE_PLACE")
	private String storagePlace;//存放地点

	@Column(name="NUM")
	private Double num;//数量

	@Column(name="USER_PLACE")
	private String userPlace;//使用地点

	@Column(name="RISK_TYPE")
	private String riskType;//危险性分类

	@Column(name="RISK_NUM")
	private String riskNum;//危规号

	@Column(name="PACKAGING_CATEGORY")
	private String packagingCategory;//包装类型

	@Column(name="REGISTRATION_NO")
	private String registrationNO;//登记号

	@Column(name="TECHNICAL_NAME")
	private String technicalName;//技术说明书名称
	
	@Column(name="TECHNICAL_PATH")
	private String technicalPath;//技术说明书路径

	@Column(name="TECHNICAL_ID")
	private String technicalId;//技术说明书附件ID

	@Column(name="SECURITY_NAME")
	private String securityName;//安全标签名称
	
	@Column(name="SECURITY_PATH")
	private String securityPath;//安全标签路径

	@Column(name="SECURITY_ID")
	private String securityId;//安全标签附件ID

	@Column(name="MEMO")
	private String memo;//备注

	@Column(name="IS_FORK_GROUP")
	private Integer isForkGroup;//数据权限

	@Column(name="CREATE_TIME")
	private Date createTime;//创建时间

	@DeleteState
	@Column(name="IS_DELETE")
	private Integer isDelete;//是否删除

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

	public Integer getDangerId() {
		return dangerId;
	}

	public void setDangerId(Integer dangerId) {
		this.dangerId = dangerId;
	}

	public String getStoragePlace() {
		return storagePlace;
	}

	public void setStoragePlace(String storagePlace) {
		this.storagePlace = storagePlace;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public String getUserPlace() {
		return userPlace;
	}

	public void setUserPlace(String userPlace) {
		this.userPlace = userPlace;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getRiskNum() {
		return riskNum;
	}

	public void setRiskNum(String riskNum) {
		this.riskNum = riskNum;
	}

	public String getPackagingCategory() {
		return packagingCategory;
	}

	public void setPackagingCategory(String packagingCategory) {
		this.packagingCategory = packagingCategory;
	}

	public String getRegistrationNO() {
		return registrationNO;
	}

	public void setRegistrationNO(String registrationNO) {
		this.registrationNO = registrationNO;
	}

	public String getTechnicalPath() {
		return technicalPath;
	}

	public void setTechnicalPath(String technicalPath) {
		this.technicalPath = technicalPath;
	}

	public String getTechnicalId() {
		return technicalId;
	}

	public void setTechnicalId(String technicalId) {
		this.technicalId = technicalId;
	}

	public String getSecurityPath() {
		return securityPath;
	}

	public void setSecurityPath(String securityPath) {
		this.securityPath = securityPath;
	}

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getTechnicalName() {
		return technicalName;
	}

	public void setTechnicalName(String technicalName) {
		this.technicalName = technicalName;
	}

	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
}
