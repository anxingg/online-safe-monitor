package cn.com.wh.dangersource.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.qytx.platform.annotation.ForceCopy;
import cn.com.qytx.platform.annotation.ForceNotCopy;
import cn.com.qytx.platform.base.domain.DeleteState;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.dangersourceobject.domain.DangerSourceObject;

/**
 * 功能：危险源类
 * 作者：李立泼
 * 时间：2017年04月10日
 */
@Entity
@Table(name="tb_danger_source")
public class DangerSource implements java.io.Serializable {
	
	/**
	 * 
	 */
	@ForceNotCopy
	private static final long serialVersionUID = 4513214879438291848L;

	/**
     * 主键id
     */
	@ForceNotCopy
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
	private Integer Id;
    
    /**
     * 危险源名称
     */
    @Column(name="danger_source_name")
	private String dangerSourceName;
    
    /**
     * 县区ID
     */
    @Column(name="region_id")
    private String regionId;
    
    /**
     * 企业id
     */
    @JoinColumn(name="company_id")
    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private WHCompany company;
    
    /**
     * 危险源级别（一级1、二级2、三级3、四级4、其他10）
     */
    @Column(name="danger_level")
    private Integer dangerLevel;
    
    /**
     * R值
     * 一级     R≥100
     * 二级     100>R≥50
     * 三级     50>R≥10
     * 四级     0<R<10
     * 其他     0
     */
    @Column(name="r_value")
    private Integer rvalue;
    
    /**
     * 评价机构
     */
    @ForceCopy
    @Column(name="evalute_org")
    private String evaluteOrg;
    
    /**
     * 最新评价时间
     */
    @ForceCopy
    @Column(name="evalute_time")
    private Date evaluteTime;
    
    @Transient
    private String evaluteTimeStr;
    
    /**
     * 投用日期
     */
    @ForceCopy
    @Column(name="begin_use_time")
    private Date beginUseTime;
    
    @Transient
    private String beginUseTimeStr;
    
    /**
     * 重大危险源所在地址
     */
    @Column(name="address")
    private String address;
    
    /**
     * 纬度
     */
    @Column(name="latitude")
    private Double latitude;
    
    /**
     * 经度
     */
    @Column(name="longitude")
    private Double longitude;
    
    /**
     * 生产规模
     */
    @Column(name="product_scale")
    private String productScale;
    
    
//    /**
//     * 工业园区类
//     */
//    @JoinColumn(name="industry_area_id",updatable=false)
//    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
//    private IndustryArea industryArea;
    
    /**
     * 最近防护距离
     */
    @Column(name="min_distance")
    private Double minDistance;
    
    /**
     * 500米范围内人数估算值
     */
    @ForceCopy
    @Column(name="estimate_people_count")
    private Integer estimatePeopleCount;
    
    /**
     * 近三年事故情况
     */
    @Column(name="accident_desc")
    private String accidentDesc;
    
    /**
     * 监控对象集合
     */
    @JoinColumn(name="DANGERSOURCEID", updatable=false)
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<DangerSourceObject> monitoredObjects;
    
//    /**
//     * 附件对象集合
//     */
//    @JoinColumn(name="create_user_id",updatable=false)
//	  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//    private Set<MonitoredObject> monitoredObjects;
    
    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime;
    
	/**
	 * 创建人
	 */
	@JoinColumn(name="create_user_id",updatable=false)
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private UserInfo createUser;
	
	/**
	 * 最后修改时间
	 */
	@Column(name="last_update_time")
	private Date lastUpdateTime;
	
	/**
	 * 最后修改人
	 */
	@JoinColumn(name="last_update_user_id",updatable=false)
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private UserInfo lastUpdateUser;
	
	/**
	 * 是否删除(0否， 1是)
	 */
	@DeleteState
	@Column(name="is_delete")
	private Integer isDelete;
    
	/**
	 * 数据权限
	 */
	@Column(name="is_fork_group")
	private Integer isForkGroup;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getDangerSourceName() {
		return dangerSourceName;
	}

	public void setDangerSourceName(String dangerSourceName) {
		this.dangerSourceName = dangerSourceName;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public WHCompany getCompany() {
		return company;
	}

	public void setCompany(WHCompany company) {
		this.company = company;
	}
	
	public static String dangerLevelStr(Integer level) {
		if(level == null){
			return null;
		}
		
		switch(level){
			case 1:
				return "一级";
			case 2:
				return "二级";
			case 3:
				return "三级";
			case 4:
				return "四级";
			case 10:
				return "其他";
			default:
				return null;
		}
	}
	
	public String getDangerLevelStrStr() {
		return DangerSource.dangerLevelStr(dangerLevel);
	}

	public Integer getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(Integer dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public Integer getRvalue() {
		return rvalue;
	}

	public void setRvalue(Integer rvalue) {
		this.rvalue = rvalue;
	}

	public String getEvaluteOrg() {
		return evaluteOrg;
	}

	public void setEvaluteOrg(String evaluteOrg) {
		this.evaluteOrg = evaluteOrg;
	}

	public Date getEvaluteTime() {
		return evaluteTime;
	}

	public void setEvaluteTime(Date evaluteTime) {
		this.evaluteTime = evaluteTime;
	}

	public String getEvaluteTimeStr() {
		return evaluteTimeStr;
	}

	public void setEvaluteTimeStr(String evaluteTimeStr) {
		this.evaluteTimeStr = evaluteTimeStr;
	}

	public Date getBeginUseTime() {
		return beginUseTime;
	}

	public void setBeginUseTime(Date beginUseTime) {
		this.beginUseTime = beginUseTime;
	}

	public String getBeginUseTimeStr() {
		return beginUseTimeStr;
	}

	public void setBeginUseTimeStr(String beginUseTimeStr) {
		this.beginUseTimeStr = beginUseTimeStr;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getProductScale() {
		return productScale;
	}

	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}

	public Double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(Double minDistance) {
		this.minDistance = minDistance;
	}

	public Integer getEstimatePeopleCount() {
		return estimatePeopleCount;
	}

	public void setEstimatePeopleCount(Integer estimatePeopleCount) {
		this.estimatePeopleCount = estimatePeopleCount;
	}

	public String getAccidentDesc() {
		return accidentDesc;
	}

	public void setAccidentDesc(String accidentDesc) {
		this.accidentDesc = accidentDesc;
	}

	public Set<DangerSourceObject> getMonitoredObjects() {
		return monitoredObjects;
	}

	public void setMonitoredObjects(Set<DangerSourceObject> monitoredObjects) {
		this.monitoredObjects = monitoredObjects;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public UserInfo getCreateUser() {
		return createUser;
	}

	public void setCreateUser(UserInfo createUser) {
		this.createUser = createUser;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public UserInfo getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(UserInfo lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
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