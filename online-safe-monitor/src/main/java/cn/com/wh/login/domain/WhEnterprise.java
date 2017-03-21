package cn.com.wh.login.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * <br/>功能:客户企业表
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2016年5月1日
 * <br/>修改日期: 2016年5月1日
 * <br/>修改列表:
 */
@Entity
@Table(name="SYSENTERPRISE")
public class WhEnterprise  implements java.io.Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -2020291820511867809L;

	
	//企业代码
	@Id
	@Column(name="CODE")
	private String code;
	
	//企业名称
	@Column(name="EnterpriseName")
	private String enterpriseName;
	
	//工商注册地
	@Column(name="RegisteredAddress")
	private String registeredAddress;
	
	//省市县
	@Column(name="AreaCode")
	private String areaCode;
	
	//成立日期
	@Column(name="SetUpDate")
	private Date setUpDate;
	
	//单位性质
	@Column(name="Nature")
	private String nature;
	
	//营业执照号码
	@Column(name="LicenseCode")
	private String licenseCode;
	
	//营业执照生产范围
	@Column(name="ProductionRange")
	private String productionRange;
	
	//企业法人
	@Column(name="LegalPerson")
	private String legalPerson;
	
	//经济类型
	@Column(name="EconomyType")
	private String economyType;
	
	//经济类型二级
	@Column(name="EconomySubType")
	private String economySubType;
	
	//组织机构代码
	@Column(name="BaseOrgCode")
	private String baseOrgCode;
	
	//生产场所地址
	@Column(name="ProductionAddress")
	private String productionAddress;
	
	//企业网址
	@Column(name="WebUrl")
	private String webUrl;
	
	//邮编
	@Column(name="PostCode")
	private String postCode;
	
	//行业类型
	@Column(name="IndustryTypes")
	private String industryTypes;
	
	//是否在园
	@Column(name="IsInGarden")
	private Integer IsInGarden;
	
	//精度
	@Column(name="Longitude")
	private Double longitude;
	
	//维度
	@Column(name="Latitude")
	private Double latitude;
	
	//是否禁用
	@Column(name="Disabled")
	private Integer Disabled;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Date getSetUpDate() {
		return setUpDate;
	}

	public void setSetUpDate(Date setUpDate) {
		this.setUpDate = setUpDate;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getProductionRange() {
		return productionRange;
	}

	public void setProductionRange(String productionRange) {
		this.productionRange = productionRange;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getEconomyType() {
		return economyType;
	}

	public void setEconomyType(String economyType) {
		this.economyType = economyType;
	}

	public String getEconomySubType() {
		return economySubType;
	}

	public void setEconomySubType(String economySubType) {
		this.economySubType = economySubType;
	}

	public String getBaseOrgCode() {
		return baseOrgCode;
	}

	public void setBaseOrgCode(String baseOrgCode) {
		this.baseOrgCode = baseOrgCode;
	}

	public String getProductionAddress() {
		return productionAddress;
	}

	public void setProductionAddress(String productionAddress) {
		this.productionAddress = productionAddress;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getIndustryTypes() {
		return industryTypes;
	}

	public void setIndustryTypes(String industryTypes) {
		this.industryTypes = industryTypes;
	}

	public Integer getIsInGarden() {
		return IsInGarden;
	}

	public void setIsInGarden(Integer isInGarden) {
		IsInGarden = isInGarden;
	}

	public Integer getDisabled() {
		return Disabled;
	}

	public void setDisabled(Integer disabled) {
		Disabled = disabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	

}