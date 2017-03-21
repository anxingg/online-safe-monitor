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
 * 危险化学品类
 * @author lilipo
 *
 */
@Entity
@Table(name="tb_wuhai_danger_chemicals_info")
public class DangerChemicalsInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4025236401934152128L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vid;//主键
	
	@Column(name="catalog_id")
	private Integer catalogId;//目录id
	
	@Column(name="group_id")
	private Integer groupId;//公司id
	
	@Column(name="company_name")
	private String companyName;//公司名称
	
	@Column(name="is_fork_group")
	private Integer isForkGroup;//数据权限
	
	@Column(name="MATERIAL_NAME")
	private String materialName;//物质名称
	
	@Column(name="MOLECULAR_FORMULA")
	private String molecularFormula;//分子式
	
	@Column(name="MELTING_POINT")
	private Double meltingPoint;//溶点
	
	@Column(name="BOILING_POINT")
	private Double boilingPoint;//沸点
	
	@Column(name="GRAVITY")
	private Double gravity;//比重
	
	@Column(name="SATURATED_VAPOR_PRESSURE")
	private Double saturatedVaporPressure;//饱和蒸气压
	
	@Column(name="VAPOR_DENSITY")
	private Double vaporDensity;//蒸气密度
	
	@Column(name="SOLUBILITY")
	private String solubility;//溶解性
	
	@Column(name="APPEARANCE")
	private String appearance;//外观与性状
	
	@Column(name="DANGEROUS_CHARACTERISTIC")
	private String dangerousCharacteristic;//危险特性
	
	@Column(name="FIRE_FIGHTING_METHODS")
	private String fireFightingMethods;//灭火方法
	
	@Column(name="STABILITY")
	private Integer stability;//稳定性（0、不稳定； 1、稳定）
	
	@Column(name="STABILITY_AVOID") 
	private String stabilityAvoid;//稳定性避免条件
	
	@Column(name="AGGREGATE_RISK") 
	private Integer aggregateRisk;//聚合危险性（0、不存在； 1、可能存在）
	
	@Column(name="AGGREGATE_RISK_AVOID")
	private String aggregateRiskAvoid;//聚合危险性避免条件
	
	@Column(name="TABOO")
	private String taboo;//禁忌物
	
	@Column(name="BREAKDOWN_PRODUCTS")
	private String breakdownProducts;//燃烧（分解）产物
	
	@Column(name="INTRUSIVE_WAY")
	private String intrusiveWay;//侵入途径（1、吸入； 2、皮肤； 3、口。用前后逗号分割开）
	
	@Column(name="TOXICITY_LD")
	private String toxicityLD;//LD50
	
	@Column(name="TOXICITY_LC")
	private String toxicityLC;//LC50
	
	@Column(name="HEALTH_RISK")
	private String healthRisk;//健康危害
	
	@Column(name="RESPIRATORY_PROTECTION")
	private String respiratoryProtection;//呼吸系统防护
	
	@Column(name="BODY_PROTECTION")
	private String bodyProtection;//身体防护
	
	@Column(name="HAND_PROTECTION")
	private String handProtection;//手防护
	
	@Column(name="EYE_PROTECTION")
	private String eyeProtection;//眼防护
	
	@Column(name="OTHER")
	private String other;//其它
	
	@DeleteState
	@Column(name="IS_DELETE")
	private Integer isDelete;//是否删除
	
	@Column(name="LEAKAGE_HANDLING")
	private String leakageHandling;//泄漏紧急处理
	
	@Column(name="STORAGE_TRANSPORTATION_ATTENTI")
	private String storageTransportationAttenti;//储运注意事项
	
	@Column(name="MAC")
	private String mac;//职业接触限值MAC
	
	@Column(name="ENGINEERING_CONTROL")
	private String engineeringControl;//工程控制
	
	@Column(name="CREATE_USER")
	private Integer createUser;//创建人ID
	
	@Column(name="CREATE_DATE")
	private Date createDate;//创建时间

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMolecularFormula() {
		return molecularFormula;
	}

	public void setMolecularFormula(String molecularFormula) {
		this.molecularFormula = molecularFormula;
	}

	public Double getMeltingPoint() {
		return meltingPoint;
	}

	public void setMeltingPoint(Double meltingPoint) {
		this.meltingPoint = meltingPoint;
	}

	public Double getBoilingPoint() {
		return boilingPoint;
	}

	public void setBoilingPoint(Double boilingPoint) {
		this.boilingPoint = boilingPoint;
	}

	public Double getGravity() {
		return gravity;
	}

	public void setGravity(Double gravity) {
		this.gravity = gravity;
	}

	public Double getSaturatedVaporPressure() {
		return saturatedVaporPressure;
	}

	public void setSaturatedVaporPressure(Double saturatedVaporPressure) {
		this.saturatedVaporPressure = saturatedVaporPressure;
	}

	public Double getVaporDensity() {
		return vaporDensity;
	}

	public void setVaporDensity(Double vaporDensity) {
		this.vaporDensity = vaporDensity;
	}

	public String getSolubility() {
		return solubility;
	}

	public void setSolubility(String solubility) {
		this.solubility = solubility;
	}

	public String getAppearance() {
		return appearance;
	}

	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}

	public String getDangerousCharacteristic() {
		return dangerousCharacteristic;
	}

	public void setDangerousCharacteristic(String dangerousCharacteristic) {
		this.dangerousCharacteristic = dangerousCharacteristic;
	}

	public String getFireFightingMethods() {
		return fireFightingMethods;
	}

	public void setFireFightingMethods(String fireFightingMethods) {
		this.fireFightingMethods = fireFightingMethods;
	}

	public Integer getStability() {
		return stability;
	}

	public void setStability(Integer stability) {
		this.stability = stability;
	}

	public String getStabilityAvoid() {
		return stabilityAvoid;
	}

	public void setStabilityAvoid(String stabilityAvoid) {
		this.stabilityAvoid = stabilityAvoid;
	}

	public Integer getAggregateRisk() {
		return aggregateRisk;
	}

	public void setAggregateRisk(Integer aggregateRisk) {
		this.aggregateRisk = aggregateRisk;
	}

	public String getAggregateRiskAvoid() {
		return aggregateRiskAvoid;
	}

	public void setAggregateRiskAvoid(String aggregateRiskAvoid) {
		this.aggregateRiskAvoid = aggregateRiskAvoid;
	}

	public String getTaboo() {
		return taboo;
	}

	public void setTaboo(String taboo) {
		this.taboo = taboo;
	}

	public String getBreakdownProducts() {
		return breakdownProducts;
	}

	public void setBreakdownProducts(String breakdownProducts) {
		this.breakdownProducts = breakdownProducts;
	}

	public String getIntrusiveWay() {
		return intrusiveWay;
	}

	public void setIntrusiveWay(String intrusiveWay) {
		this.intrusiveWay = intrusiveWay;
	}

	public String getToxicityLD() {
		return toxicityLD;
	}

	public void setToxicityLD(String toxicityLD) {
		this.toxicityLD = toxicityLD;
	}

	public String getToxicityLC() {
		return toxicityLC;
	}

	public void setToxicityLC(String toxicityLC) {
		this.toxicityLC = toxicityLC;
	}

	public String getHealthRisk() {
		return healthRisk;
	}

	public void setHealthRisk(String healthRisk) {
		this.healthRisk = healthRisk;
	}

	public String getRespiratoryProtection() {
		return respiratoryProtection;
	}

	public void setRespiratoryProtection(String respiratoryProtection) {
		this.respiratoryProtection = respiratoryProtection;
	}

	public String getBodyProtection() {
		return bodyProtection;
	}

	public void setBodyProtection(String bodyProtection) {
		this.bodyProtection = bodyProtection;
	}

	public String getHandProtection() {
		return handProtection;
	}

	public void setHandProtection(String handProtection) {
		this.handProtection = handProtection;
	}

	public String getEyeProtection() {
		return eyeProtection;
	}

	public void setEyeProtection(String eyeProtection) {
		this.eyeProtection = eyeProtection;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getLeakageHandling() {
		return leakageHandling;
	}

	public void setLeakageHandling(String leakageHandling) {
		this.leakageHandling = leakageHandling;
	}

	public String getStorageTransportationAttenti() {
		return storageTransportationAttenti;
	}

	public void setStorageTransportationAttenti(String storageTransportationAttenti) {
		this.storageTransportationAttenti = storageTransportationAttenti;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getEngineeringControl() {
		return engineeringControl;
	}

	public void setEngineeringControl(String engineeringControl) {
		this.engineeringControl = engineeringControl;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}
	
}
