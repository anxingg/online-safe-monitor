package cn.com.qytx.hotline.ivr.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.qytx.platform.base.domain.BaseDomain;

@Entity
@Table(name = "MSIWorkload")
public class MsiWorkload extends BaseDomain {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -3930434650900815510L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vid;

	@Column(name = "MSIUserID")
	private Integer msiUserID;

	@Column(name = "MSIState")
	private Integer msiState;

	@Column(name = "BeginTime")
	private Timestamp beginTime;

	@Column(name = "EndTime")
	private Timestamp endTime;

	@Column(name = "Length")
	private Long length;

	@Transient
	private Long countlength;
	
	@Column(name="is_fork_group")
    private Integer isForkGroup;
	
	/**
	 * 2015-01-21马恺添加 新坐席考勤报表
	 * 在线时长
	 */
	@Column(name="onlineTime")
	private Integer onlineTime;
	/**
	 * 置忙时长
	 */
	@Column(name="busyTime")
	private Integer busyTime;
	/**
	 * 空闲时长
	 */
	@Column(name="restTime")
	private Integer restTime;
	/**
	 * 置忙次数
	 */
	@Column(name="changeBusyTimes")
	private Integer changeBusyTimes;
	
	/**
	 * 数据库本地时间
	 */
	@Transient
	private Timestamp nativeTime;

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Integer getMsiUserID() {
		return msiUserID;
	}

	public void setMsiUserID(Integer msiUserID) {
		this.msiUserID = msiUserID;
	}

	public Integer getMsiState() {
		return msiState;
	}

	public void setMsiState(Integer msiState) {
		this.msiState = msiState;
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

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	/**
	 * @return the countlength
	 */
	public Long getCountlength() {
		return countlength;
	}

	/**
	 * @param countlength
	 *            the countlength to set
	 */
	public void setCountlength(Long countlength) {
		this.countlength = countlength;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	public Integer getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Integer onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getBusyTime() {
		return busyTime;
	}

	public void setBusyTime(Integer busyTime) {
		this.busyTime = busyTime;
	}

	public Integer getRestTime() {
		return restTime;
	}

	public void setRestTime(Integer restTime) {
		this.restTime = restTime;
	}

	public Integer getChangeBusyTimes() {
		return changeBusyTimes;
	}

	public void setChangeBusyTimes(Integer changeBusyTimes) {
		this.changeBusyTimes = changeBusyTimes;
	}

	public Timestamp getNativeTime() {
		return nativeTime;
	}

	public void setNativeTime(Timestamp nativeTime) {
		this.nativeTime = nativeTime;
	}

}
