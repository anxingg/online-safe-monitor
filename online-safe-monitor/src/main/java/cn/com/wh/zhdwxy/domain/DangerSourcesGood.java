package cn.com.wh.zhdwxy.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 重大危险源危化品
 * @author lilipo
 *
 */
@Entity
@Table(name="tb_wh_danger_sources_good")
public class DangerSourcesGood implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7935284627566213974L;

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
	 * 重大危险源ID
	 */
	@Column(name="danger_id")
	private Integer dangerId;
	
	/**
	 * 危化品名称
	 */
	@Column(name="danger_good_name")
	private String dangerGoodName;
	
	/**
	 * 危险性类别（数据字典）
	 */
	@Column(name="dict_id")
	private Integer dictId;
	
	/**
	 * 危险性类别 对应的数据字典中的字符串（传参用）
	 */
	@Transient
	private String dictIdName;
	
	/**
	 * UN编号
	 */
	@Column(name="un_code")
	private String unCode;
	
	/**
	 * 生产用途
	 */
	@Column(name="purpose")
	private String purpose;
	
	/**
	 * 生产工艺
	 */
	@Column(name="process")
	private String process;
	
	/**
	 * 物理状态
	 */
	@Column(name="physical_state")
	private String physicalState;
	
	/**
	 * 操作温度
	 */
	@Column(name="operation_temp")
	private Double operationTemp;
	
	/**
	 * 操作压力
	 */
	@Column(name="operation_pressure")
	private Double operationPressure;
	
	/**
	 * 单个存量
	 */
	@Column(name="simple_stock")
	private Double simpleStock;
	
	/**
	 * 单元内化学品存量
	 */
	@Column(name="unit_stock")
	private Double unitStock;
	
	/**
	 * 临界量
	 */
	@Column(name="critical_mass")
	private Double criticalMass;
	
	/**
	 * 添加时间
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 最后更新时间
	 */
	@Column(name="last_update_time")
	private Date lastUpdateTime;
	
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
	
	@Transient
	private Integer whroletype;

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

	public String getDangerGoodName() {
		return dangerGoodName;
	}

	public void setDangerGoodName(String dangerGoodName) {
		this.dangerGoodName = dangerGoodName;
	}

	public Integer getDictId() {
		return dictId;
	}

	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}

	public String getDictIdName() {
		return dictIdName;
	}

	public void setDictIdName(String dictIdName) {
		this.dictIdName = dictIdName;
	}

	public String getUnCode() {
		return unCode;
	}

	public void setUnCode(String unCode) {
		this.unCode = unCode;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getPhysicalState() {
		return physicalState;
	}

	public void setPhysicalState(String physicalState) {
		this.physicalState = physicalState;
	}

	public Double getOperationTemp() {
		return operationTemp;
	}

	public void setOperationTemp(Double operationTemp) {
		this.operationTemp = operationTemp;
	}

	public Double getOperationPressure() {
		return operationPressure;
	}

	public void setOperationPressure(Double operationPressure) {
		this.operationPressure = operationPressure;
	}

	public Double getSimpleStock() {
		return simpleStock;
	}

	public void setSimpleStock(Double simpleStock) {
		this.simpleStock = simpleStock;
	}

	public Double getUnitStock() {
		return unitStock;
	}

	public void setUnitStock(Double unitStock) {
		this.unitStock = unitStock;
	}

	public Double getCriticalMass() {
		return criticalMass;
	}

	public void setCriticalMass(Double criticalMass) {
		this.criticalMass = criticalMass;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
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

	public Integer getWhroletype() {
		return whroletype;
	}

	public void setWhroletype(Integer whroletype) {
		this.whroletype = whroletype;
	}
	
}
