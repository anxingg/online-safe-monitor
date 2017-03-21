package cn.com.wh.company.domain;

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
 * 功能：企业信息
 * 作者：李贺
 * 时间：2015年8月14日
 */
@Entity
@Table(name="tb_wuhai_company")
public class WHCompany implements java.io.Serializable {

	private static final long serialVersionUID = -1341651984286181258L;
	/**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="company_Id")
	private Integer companyId;
    /**
     * 企业名称
     */
    @Column(name="company_name")
	private String companyName;
	/**
	 * 部门id
	 */
    @Column(name="group_id")
	private Integer groupId;
	/**
	 * 工商注册地址
	 */
    @Column(name="registration_address")
	private String registrationAddress;
	/**
	 * 单位代码
	 */
    @Column(name="company_code")
	private String companyCode;
	/**
	 * 城市id
	 */
    @Column(name="city_id")
	private String cityId;
	/**
	 * 企业性质
	 */
    @Column(name="company_property")
	private String companyProperty;
	/**
	 * 成立时间
	 */
    @Column(name="establishment_time")
	private Timestamp establishmentTime;
	/**
	 * 工商营业执照注册号
	 */
    @Column(name="business_licence")
	private String businessLicence;
	/**
	 * 工商营业执照生产范围
	 */
    @Column(name="production_scope")
	private String productionScope;
	/**
	 * 法定代表人
	 */
    @Column(name="legal_representative")
	private String legalRepresentative;
	/**
	 * 经济类型
	 * 国有经济 集体经济 私营经济 个体经济 联营经济 股份制 外商投资 港澳台投资 其它经济
	 */
    @Column(name="economic_type")
	private String economicType;
    
	/**
	 * 组织机构代码
	 */
    @Column(name="unit_code")
	private String unitCode;
	/**
	 * 生产场所地址
	 */
    @Column(name="product_address")
	private String productAddress;
	/**
	 * 企业网站
	 */
	@Column(name="website")
	private String website;
	/**
	 * 邮政编码
	 */
	@Column(name="postalcode")
	private String postalcode;
	/**
	 * 行业分类
	 */
	@Column(name="industry_classification")
	private String industryClassification;
	/**
	 * 专职安全生产管理人员人数
	 */
	@Column(name="safe_manage_user_num")
	private Integer safeManageUserNum;
	/**
	 * 安全生产管理机构负责人姓名
	 */
	@Column(name="safe_manage_user_name")
	private String safeManageUserName;
	/**
	 * 安全生产管理机构负责人办公电话
	 */
	@Column(name="safe_manage_user_tel")
	private String safeManageUserTel;
	/**
	 * 安全生产管理机构负责人移动电话
	 */
	@Column(name="safe_manage_user_phone")
	private String safeManageUserPhone;
	/**
	 * 安全生产管理机构负责人电子邮箱
	 */
	@Column(name="safe_manage_user_email")
	private String safeManageUserEmail;
	/**
	 * 特种作业人员人数
	 */
	@Column(name="special_user_num")
	private Integer specialUserNum;
	/**
	 * 安全生产标准等级
	 */
	@Column(name="safe_product_grade")
	private Integer safeProductGrade;
	/**
	 * 应急咨询服务号码
	 */
	@Column(name="emergency_phone")
	private String emergencyPhone;
	/**
	 * 安全值班电话
	 */
	@Column(name="safe_duty_phone")
	private String safeDutyPhone;
	/**
	 * 进口企业资质证明名称
	 */
	@Column(name="company_qualification")
	private Integer importCompanyQualification;
	/**
	 * 进口企业资质证明编号
	 */
	@Column(name="company_qualification_num")
	private String importCompanyQualificationNum;
	/**
	 * 主要产品及生产规模
	 */
	@Column(name="product")
	private String product;
	/**
	 * 企业简介
	 */
	@Column(name="introduction")
	private String introduction;
	/**
	 * 厂区边界外1000米范围内的单位或设备情况
	 */
	@Column(name="outside_situation")
	private String outsideSituation;
	/**
	 * 行业分类及行业代码
	 */
	@Column(name="industry_code")
	private Integer industryCode;
	/**
	 * 企业经度
	 */
	@Column(name="precision")
	private Double precision;
	/**
	 * 企业纬度
	 */
	@Column(name="dimension")
	private Double dimension;
	/**
	 * 销售收入
	 */
	@Column(name="sales")
	private Integer sales;
	/**
	 * 职工人数
	 */
	@Column(name="workers_num")
	private Integer workersNum;
	/**
	 * 企业规模
	 * */
	@Column(name="enterprise_scale")
	private Integer enterpriseScale;
	/**
	 * 是否在工业园区
	 */
	@Column(name="is_in")
	private Integer isIn;
	/**
	 * 数据权限
	 */
	@Column(name="is_fork_group")
	private Integer isForkGroup;
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Timestamp createTime;
	/**
	 * 创建人
	 */
	@Column(name="create_user_id")
	private Integer createUserId;
	/**
	 * 最后修改时间
	 */
	@Column(name="last_update_time")
	private Timestamp lastUpdateTime;
	/**
	 * 最后修改人
	 */
	@Column(name="last_update_user_id")
	private Integer lastUpdateUserId;
	/**
	 * 是否删除
	 */
	@DeleteState
	@Column(name="is_delete")
	private Integer isDelete;
	
	/**
	 * 备注
	 */
	@Column(name="memo")
	private String memo;
	
	/**
	 *生产状况 
	 */
	@Column(name="product_type")
	private Integer productType;
	
	/**
	 * 生产投入提取标准
	 */
	@Column(name="extract_description")
	private String extractDescription;
	
	/**
	 * 安全生产资金余额
	 */
	@Column(name="safety_surplus_money")
	private Double safetySurplusMoney;
	
	/**
	 * 安全生产资金总额
	 */
	@Column(name="safety_all_money")
	private Double safetyAllMoney;
	
	/**
	 * 安全生产资金总消耗
	 */
	@Column(name="safety_consumer_money")
	private Double safetyConsumerMoney;
	
	/**
	 * 关联客户端企业表id
	 */
	@Column(name="linkId")
	private String linkId;
	

	// Constructors

	/** default constructor */
	public WHCompany() {
	}

	// Property accessors

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getRegistrationAddress() {
		return this.registrationAddress;
	}

	public void setRegistrationAddress(String registrationAddress) {
		this.registrationAddress = registrationAddress;
	}

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}



	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCompanyProperty() {
		return this.companyProperty;
	}

	public void setCompanyProperty(String companyProperty) {
		this.companyProperty = companyProperty;
	}

	public Timestamp getEstablishmentTime() {
		return this.establishmentTime;
	}

	public void setEstablishmentTime(Timestamp establishmentTime) {
		this.establishmentTime = establishmentTime;
	}

	public String getBusinessLicence() {
		return this.businessLicence;
	}

	public void setBusinessLicence(String businessLicence) {
		this.businessLicence = businessLicence;
	}

	public String getProductionScope() {
		return this.productionScope;
	}

	public void setProductionScope(String productionScope) {
		this.productionScope = productionScope;
	}

	public String getLegalRepresentative() {
		return this.legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getEconomicType() {
		return economicType;
	}

	public void setEconomicType(String economicType) {
		this.economicType = economicType;
	}

	public String getUnitCode() {
		return this.unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getProductAddress() {
		return this.productAddress;
	}

	public void setProductAddress(String productAddress) {
		this.productAddress = productAddress;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getIndustryClassification() {
		return this.industryClassification;
	}

	public void setIndustryClassification(String industryClassification) {
		this.industryClassification = industryClassification;
	}

	public Integer getSafeManageUserNum() {
		return this.safeManageUserNum;
	}

	public void setSafeManageUserNum(Integer safeManageUserNum) {
		this.safeManageUserNum = safeManageUserNum;
	}

	public String getSafeManageUserName() {
		return this.safeManageUserName;
	}

	public void setSafeManageUserName(String safeManageUserName) {
		this.safeManageUserName = safeManageUserName;
	}

	public String getSafeManageUserTel() {
		return this.safeManageUserTel;
	}

	public void setSafeManageUserTel(String safeManageUserTel) {
		this.safeManageUserTel = safeManageUserTel;
	}

	public String getSafeManageUserPhone() {
		return this.safeManageUserPhone;
	}

	public void setSafeManageUserPhone(String safeManageUserPhone) {
		this.safeManageUserPhone = safeManageUserPhone;
	}

	public String getSafeManageUserEmail() {
		return this.safeManageUserEmail;
	}

	public void setSafeManageUserEmail(String safeManageUserEmail) {
		this.safeManageUserEmail = safeManageUserEmail;
	}

	public Integer getSpecialUserNum() {
		return this.specialUserNum;
	}

	public void setSpecialUserNum(Integer specialUserNum) {
		this.specialUserNum = specialUserNum;
	}

	public Integer getSafeProductGrade() {
		return this.safeProductGrade;
	}

	public void setSafeProductGrade(Integer safeProductGrade) {
		this.safeProductGrade = safeProductGrade;
	}

	public String getEmergencyPhone() {
		return this.emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getSafeDutyPhone() {
		return this.safeDutyPhone;
	}

	public void setSafeDutyPhone(String safeDutyPhone) {
		this.safeDutyPhone = safeDutyPhone;
	}

	public Integer getImportCompanyQualification() {
		return this.importCompanyQualification;
	}

	public void setImportCompanyQualification(Integer importCompanyQualification) {
		this.importCompanyQualification = importCompanyQualification;
	}

	public String getImportCompanyQualificationNum() {
		return this.importCompanyQualificationNum;
	}

	public void setImportCompanyQualificationNum(
			String importCompanyQualificationNum) {
		this.importCompanyQualificationNum = importCompanyQualificationNum;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getOutsideSituation() {
		return this.outsideSituation;
	}

	public void setOutsideSituation(String outsideSituation) {
		this.outsideSituation = outsideSituation;
	}

	public Integer getIndustryCode() {
		return this.industryCode;
	}

	public void setIndustryCode(Integer industryCode) {
		this.industryCode = industryCode;
	}

	public Double getPrecision() {
		return this.precision;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}

	public Double getDimension() {
		return this.dimension;
	}

	public void setDimension(Double dimension) {
		this.dimension = dimension;
	}

	public Integer getSales() {
		return this.sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getWorkersNum() {
		return this.workersNum;
	}

	public void setWorkersNum(Integer workersNum) {
		this.workersNum = workersNum;
	}

	public Integer getIsIn() {
		return this.isIn;
	}

	public void setIsIn(Integer isIn) {
		this.isIn = isIn;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getEnterpriseScale() {
		return enterpriseScale;
	}

	public void setEnterpriseScale(Integer enterpriseScale) {
		this.enterpriseScale = enterpriseScale;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getExtractDescription() {
		return extractDescription;
	}

	public void setExtractDescription(String extractDescription) {
		this.extractDescription = extractDescription;
	}

	public Double getSafetySurplusMoney() {
		return safetySurplusMoney;
	}

	public void setSafetySurplusMoney(Double safetySurplusMoney) {
		this.safetySurplusMoney = safetySurplusMoney;
	}

	public Double getSafetyAllMoney() {
		return safetyAllMoney;
	}

	public void setSafetyAllMoney(Double safetyAllMoney) {
		this.safetyAllMoney = safetyAllMoney;
	}

	public Double getSafetyConsumerMoney() {
		return safetyConsumerMoney;
	}

	public void setSafetyConsumerMoney(Double safetyConsumerMoney) {
		this.safetyConsumerMoney = safetyConsumerMoney;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

}