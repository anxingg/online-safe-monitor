package cn.com.qytx.hotline.ivr.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 
 * 功能: 呼入队列表 版本: 1.0 开发人员: 李华伟 创建日期: 2013-11-11 修改日期: 2013-11-11 修改列表:
 */
@Entity
@Table(name = "MSIService")
public class Msiservice extends BaseDomain implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vid;

	/**
	 * 队列对应的热线号码
	 */
	@Column(name = "serviceLeadPhone")
	private String serviceLeadPhone;

	/**
	 * 队列说明
	 */
	@Column(name = "serviceName")
	private String serviceName;

	/**
	 * 队列状态 0或者空表示可用 1不可用
	 */
	@Column(name = "isEnable")
	private Integer isEnable;

	/**
	 * 对应按键
	 */
	@Column(name = "content")
	private String content;
	/**
     * 权限控制
     */
    @Column(name="is_fork_group")
    private Integer isForkGroup;
    
    /**
     * 队列类型（0表示普通队列；1表示专家队列；）监察队列和稽查队列属于普通队列。
     */
    @Column(name="service_type")
    private Integer serviceType;
    

	@Override
	public String toString() {
		return "Msiservice [vid=" + vid + ", serviceLeadPhone="
				+ serviceLeadPhone + ", serviceName=" + serviceName
				+ ", isEnable=" + isEnable + ", content=" + content
				+ ", isForkGroup=" + isForkGroup + ", serviceType="
				+ serviceType + ", msiUserIds=" + msiUserIds + "]";
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 队列成员在员工表中的记录
	 */
	@Transient
	private List<UserInfo> userInfoList;
	/**
	 * 队列对应的坐席Id 用,分割
	 */
	@Transient
	private String msiUserIds;
	
	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getServiceLeadPhone() {
		return serviceLeadPhone;
	}

	public void setServiceLeadPhone(String serviceLeadPhone) {
		this.serviceLeadPhone = serviceLeadPhone;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsiUserIds() {
		return msiUserIds;
	}

	public void setMsiUserIds(String msiUserIds) {
		this.msiUserIds = msiUserIds;
	}

	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	
}
