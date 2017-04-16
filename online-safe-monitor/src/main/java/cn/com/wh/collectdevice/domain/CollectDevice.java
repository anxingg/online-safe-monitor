package cn.com.wh.collectdevice.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import cn.com.wh.company.domain.WHCompany;

/**
 * 采集设备实体类
 * @author 吴胜光
 * 创建时间： 2017年04月03日
 * 
 */
@Entity
@Table(name="COLLECTDEVICE")
public class CollectDevice implements java.io.Serializable {

	private static final long serialVersionUID = 4705025193958385486L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer vid;//id
	
	/**
     * 企业id
     */
    @JoinColumn(name="company_id")
    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private WHCompany company;
    
	@Column(name="INSTALLPOSITION")
	private String installPosition;
	
	@Column(name="DEVICEMODEL")
	private Integer deviceModel;//型号
	
	@Column(name="CHANNELCOUNT")
	private Integer channelCount;
	
	@Column(name="DEVICEADDRESS")
	private String deviceAddress;
	
	@Column(name="DEVICESTATUS")
	private Integer deviceStatus;
	
	@Column(name="DEVICETYPE")
	private Integer deviceType;
	
	@Column(name="INSTALLPERSON")
	private String installPerson;
	
	@Column(name="INSTALLDATE")
	private Date installDate;
	
	@Column(name="MEMO")
	private String memo;

	@Column(name="CREATETIME")
	private Timestamp createTime;//创建时间
	
	//为了前段页面显示，单位对应的区域ID
	private Integer areaId;
	
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public WHCompany getCompany() {
		return company;
	}

	public void setCompany(WHCompany company) {
		this.company = company;
	}

	public String getInstallPosition() {
		return installPosition;
	}

	public void setInstallPosition(String installPosition) {
		this.installPosition = installPosition;
	}

	public Integer getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(Integer deviceModel) {
		this.deviceModel = deviceModel;
	}

	public Integer getChannelCount() {
		return channelCount;
	}

	public void setChannelCount(Integer channelCount) {
		this.channelCount = channelCount;
	}

	public String getDeviceAddress() {
		return deviceAddress;
	}

	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public String getInstallPerson() {
		return installPerson;
	}

	public void setInstallPerson(String installPerson) {
		this.installPerson = installPerson;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
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

	public CollectDevice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}