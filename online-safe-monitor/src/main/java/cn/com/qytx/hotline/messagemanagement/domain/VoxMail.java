package cn.com.qytx.hotline.messagemanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.BaseDomain;

/**
 * 功能:留言表实体
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-3
 * 修改日期: 2014-12-3
 * 修改列表: 2014-04-8  升级兼容cbb2.0.1版本 继承BaseDomain
 */

@Entity
@Table(name="tb_VoxMail")
public class VoxMail extends BaseDomain implements Serializable {
	/** 描述含义*/
	private static final long serialVersionUID = 568603258547550754L;
	/**
	 * 主键vid
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vid;
	
	/**
	 * 来电号码
	 */
	@Column(name="phone")
	private String phone;
	/**
	 * 留言开始时间
	 */
	@Column(name="beginTime")
	private Timestamp beginTime;
	/**
	 * 留言结束时间（结束时间-开始时间=留言时长）
	 */
	@Column(name="endTime")
	private Timestamp endTime;
	/**
	 * 录音文件
	 */
	@Column(name="voxInfo")
	private String voxInfo;
	
	/**
	 * 状态1未处理，2已处理，3无效
	 */
	@Column(name="state")
	private Integer state;
	/**
	 * 区域限制权限
	 */
	@Column(name="is_fork_group")
	private Integer isForkGroup;
	
	/*----------------get/set-----------------*/
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getVoxInfo() {
		return voxInfo;
	}
	public void setVoxInfo(String voxInfo) {
		this.voxInfo = voxInfo;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getIsForkGroup() {
		return isForkGroup;
	}
	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}
	
	
}
