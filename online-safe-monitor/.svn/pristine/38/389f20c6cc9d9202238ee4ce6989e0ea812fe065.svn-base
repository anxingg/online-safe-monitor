package cn.com.qytx.hotline.ivr.domain;

import java.io.Serializable;
import java.sql.Timestamp;

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
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import cn.com.qytx.hotline.customercall.domain.CustomerCallLog;
import cn.com.qytx.platform.base.domain.BaseDomain;

/**
 * 项目名称：wzerp 类名称：MsicallLog.java 类描述： 创建人：WangBin 创建时间：2013-1-7
 * 
 * @version
 */
@Entity
@Table(name = "MSICalllog")
public class MsicallLog extends BaseDomain implements Serializable{
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 通话记录表主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Vid")
	private Integer vid;

//	/**
//	 * 单位ID
//	 */
//	@Column(name = "CompanyId")
//	private Integer companyId;

	/**
	 * 客户号码
	 */
	@Column(name = "Call", length = 32)
	private String call;

	/**
	 * 被叫号码
	 */
	@Column(name = "Called", length = 32)
	private String called;

	/**
	 * 关联呼入号
	 */
	@Column(name = "LinkCalled", length = 32)
	private String linkCalled;

	/**
	 * 队列ID
	 */
	@Column(name = "ServiceId")
	private Integer serviceId;

	/**
	 * 话务员登记实体vid
	 */
	@Column(name = "MSIUserId")
	private Integer msiuserId;

	/**
	 * 呼叫进入时间
	 */
	@Column(name = "CallInTime")
	private Timestamp callInTime;

	/**
	 * 进入排队时间
	 */
	@Column(name = "InQueueTime")
	private Timestamp inQueueTime;

	/**
	 * 排队时长的秒数=0表示没有排队
	 */
	@Column(name = "InQueueSeconds")
	private Integer inQueueSeconds;

	/**
	 * 通话开始时间
	 */
	@Column(name = "TalkInTime")
	private Timestamp talkInTime;

	/**
	 * 通话结束时间
	 */
	@Column(name = "TalkEndTime")
	private Timestamp talkEndTime;

	/**
	 * 呼叫结束时间
	 */
	@Column(name = "CallEndTime")
	private Timestamp callEndTime;

	/**
	 * 秒数
	 */
	@Column(name = "Seconds")
	private Integer seconds;

	/**
	 * 分钟数
	 */
	@Column(name = "Minutes")
	private Integer minutes;

	/**
	 * 挂机方式0：主叫挂机 1：被叫挂机 endtype 新设计 呼损时 : 0 坐席拒接 1 用户挂断 2 振铃超时
	 */
	@Column(name = "EndType")
	private Integer endType;

	/**
	 * 呼叫方式 1：呼入 2：呼出
	 */
	@Column(name = "CallType")
	private Integer callType;

	/**
	 * 呼叫方式：这个表示是呼入系统还是呼出系统 1：呼入系统 2：呼出系统
	 */
	@Column(name = "CallSystemType")
	private Integer callSystemType;

	/**
	 * 客户端类型 1：电话 2：电脑坐席 3：IP坐席
	 */
	@Column(name = "ClientType")
	private Integer clientType;

	/**
	 * 其他信息
	 */
	@Column(name = "Info", length = 200)
	private String info;

	/**
	 * 语音文件
	 */
	@Column(name = "VoxFile")
	private String voxFile;

	/**
	 * 客户日志记录ID
	 */
	@Column(name = "CustomerCallLogId")
	private Integer customerCallLogId;

	/**
	 * 是否转接成功了（转分机）漏话部分
	 */
	@Column(name = "bTurn")
	private Integer bturn;

	/**
	 * 呼叫ID（发包）--（跟踪查询日志用的）
	 */
	@Column(name = "CallId")
	private String callId;

	/**
	 * 客户满意度
	 */
	@Column(name = "ConsumerSatisfactional")
	private Integer customerSatisfactional;

	/**
	 * 关联工单对象 many-to-one @ManyToOne
	 */
	@JoinColumn(name = "CustomerCallLogId", referencedColumnName = "vid", insertable = false, updatable = false)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private CustomerCallLog ccLog;
	/**
	 * 
	 */
	@Transient
	private String workNo;
	/**
	 * 
	 */
	@Transient
	private String msiuserName;

	/**
	 * 格式化后的通话时间秒数
	 */
	@Transient
	private String secondStr;

	/**
	 * 语音文件名称
	 */
	@Transient
	private String voxFileName;
	
	/**
     * 权限控制
     */
    @Column(name="is_fork_group")
    private Integer isForkGroup;

	public Integer getCustomerSatisfactional() {
		return customerSatisfactional;
	}

	public void setCustomerSatisfactional(Integer customerSatisfactional) {
		this.customerSatisfactional = customerSatisfactional;
	}

	/**
	 * @return the vid
	 */
	public Integer getVid() {
		return vid;
	}

	/**
	 * @param vid
	 *            the vid to set
	 */
	public void setVid(Integer vid) {
		this.vid = vid;
	}

//	/**
//	 * @return the companyId
//	 */
//	public Integer getCompanyId() {
//		return companyId;
//	}
//
//	/**
//	 * @param companyId
//	 *            the companyId to set
//	 */
//	public void setCompanyId(Integer companyId) {
//		this.companyId = companyId;
//	}

	/**
	 * @return the call
	 */
	public String getCall() {
		return call;
	}

	/**
	 * @param call
	 *            the call to set
	 */
	public void setCall(String call) {
		this.call = call;
	}

	/**
	 * @return the called
	 */
	public String getCalled() {
		return called;
	}

	/**
	 * @param called
	 *            the called to set
	 */
	public void setCalled(String called) {
		this.called = called;
	}

	/**
	 * @return the linkCalled
	 */
	public String getLinkCalled() {
		return linkCalled;
	}

	/**
	 * @param linkCalled
	 *            the linkCalled to set
	 */
	public void setLinkCalled(String linkCalled) {
		this.linkCalled = linkCalled;
	}

	/**
	 * @return the serviceId
	 */
	public Integer getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId
	 *            the serviceId to set
	 */
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	// public Msiuser getMsiuser() {
	// return msiuser;
	// }
	//
	// public void setMsiuser(Msiuser msiuser) {
	// this.msiuser = msiuser;
	// }

	/**
	 * @return the callInTime
	 */
	public Timestamp getCallInTime() {
		return callInTime;
	}

	/**
	 * @param callInTime
	 *            the callInTime to set
	 */
	public void setCallInTime(Timestamp callInTime) {
		this.callInTime = callInTime;
	}

	/**
	 * @return the inQueueTime
	 */
	public Timestamp getInQueueTime() {
		return inQueueTime;
	}

	/**
	 * @param inQueueTime
	 *            the inQueueTime to set
	 */
	public void setInQueueTime(Timestamp inQueueTime) {
		this.inQueueTime = inQueueTime;
	}

	/**
	 * @return the inQueueSeconds
	 */
	public Integer getInQueueSeconds() {
		return inQueueSeconds;
	}

	/**
	 * @param inQueueSeconds
	 *            the inQueueSeconds to set
	 */
	public void setInQueueSeconds(Integer inQueueSeconds) {
		this.inQueueSeconds = inQueueSeconds;
	}

	/**
	 * @return the talkInTime
	 */
	public Timestamp getTalkInTime() {
		return talkInTime;
	}

	/**
	 * @param talkInTime
	 *            the talkInTime to set
	 */
	public void setTalkInTime(Timestamp talkInTime) {
		this.talkInTime = talkInTime;
	}

	/**
	 * @return the talkEndTime
	 */
	public Timestamp getTalkEndTime() {
		return talkEndTime;
	}

	/**
	 * @param talkEndTime
	 *            the talkEndTime to set
	 */
	public void setTalkEndTime(Timestamp talkEndTime) {
		this.talkEndTime = talkEndTime;
	}

	/**
	 * @return the callEndTime
	 */
	public Timestamp getCallEndTime() {
		return callEndTime;
	}

	/**
	 * @param callEndTime
	 *            the callEndTime to set
	 */
	public void setCallEndTime(Timestamp callEndTime) {
		this.callEndTime = callEndTime;
	}

	/**
	 * @return the seconds
	 */
	public Integer getSeconds() {
		return seconds;
	}

	/**
	 * @param seconds
	 *            the seconds to set
	 */
	public void setSeconds(Integer seconds) {
		if (null != seconds) {
			this.setSecondStr(formatDuration(Long.parseLong(seconds.toString())));
		}
		this.seconds = seconds;
	}

	/**
	 * @return the minutes
	 */
	public Integer getMinutes() {
		return minutes;
	}

	private String formatDuration(Long duration) {
		StringBuilder timeStr = new StringBuilder();
		if (null != duration) {
			long tempDuration = duration.longValue();
//			@SuppressWarnings("unused")
//			long tempDurationOld = tempDuration;

			// 计算天数
			if (tempDuration >= 24 * 60 * 60) {
				timeStr.append(tempDuration / (24 * 60 * 60) + "天");
				tempDuration = tempDuration % (24 * 60 * 60);
			}

			// 计算小时数
			if (tempDuration >= 60 * 60) {
				timeStr.append(tempDuration / (60 * 60) + "小时");
				tempDuration = tempDuration % (60 * 60);
			}

			// 计算分钟数
			if (tempDuration >= 60) {
				timeStr.append(tempDuration / 60 + "分");
				tempDuration = tempDuration % 60;
			}

			// 计算秒 && tempDurationOld < 60
			if (tempDuration > 0) {
				timeStr.append(tempDuration + "秒");
			}
			return timeStr.toString();
		}
		return "-";
	}

	/**
	 * @param minutes
	 *            the minutes to set
	 */
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	/**
	 * @return the endType
	 */
	public Integer getEndType() {
		return endType;
	}

	/**
	 * @param endType
	 *            the endType to set
	 */
	public void setEndType(Integer endType) {
		this.endType = endType;
	}

	/**
	 * @return the callType
	 */
	public Integer getCallType() {
		return callType;
	}

	/**
	 * @param callType
	 *            the callType to set
	 */
	public void setCallType(Integer callType) {
		this.callType = callType;
	}

	/**
	 * @return the callSystemType
	 */
	public Integer getCallSystemType() {
		return callSystemType;
	}

	/**
	 * @param callSystemType
	 *            the callSystemType to set
	 */
	public void setCallSystemType(Integer callSystemType) {
		this.callSystemType = callSystemType;
	}

	/**
	 * @return the clientType
	 */
	public Integer getClientType() {
		return clientType;
	}

	/**
	 * @param clientType
	 *            the clientType to set
	 */
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the voxFile
	 */
	public String getVoxFile() {
		return voxFile;
	}

	/**
	 * @param voxFile
	 *            the voxFile to set
	 */
	public void setVoxFile(String voxFile) {
		if (!StringUtils.isEmpty(voxFile)) {
			String[] files = voxFile.split("/");
			this.setVoxFileName(files[files.length - 1]);
		}
		this.voxFile = voxFile;
	}

	/**
	 * @return the customerCallLogId
	 */
	public Integer getCustomerCallLogId() {
		return customerCallLogId;
	}

	/**
	 * @param customerCallLogId
	 *            the customerCallLogId to set
	 */
	public void setCustomerCallLogId(Integer customerCallLogId) {
		this.customerCallLogId = customerCallLogId;
	}

	/**
	 * @return the bturn
	 */
	public Integer getBturn() {
		return bturn;
	}

	/**
	 * @param bturn
	 *            the bturn to set
	 */
	public void setBturn(Integer bturn) {
		this.bturn = bturn;
	}

	/**
	 * @return the callId
	 */
	public String getCallId() {
		return callId;
	}

	/**
	 * @param callId
	 *            the callId to set
	 */
	public void setCallId(String callId) {
		this.callId = callId;
	}

	public Integer getMsiuserId() {
		return msiuserId;
	}

	public void setMsiuserId(Integer msiuserId) {
		this.msiuserId = msiuserId;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getMsiuserName() {
		return msiuserName;
	}

	public void setMsiuserName(String msiuserName) {
		this.msiuserName = msiuserName;
	}

	public String getSecondStr() {
		return secondStr;
	}

	public void setSecondStr(String secondStr) {
		this.secondStr = secondStr;
	}

	public String getVoxFileName() {
		return voxFileName;
	}

	public void setVoxFileName(String voxFileName) {
		this.voxFileName = voxFileName;
	}

	public CustomerCallLog getCcLog() {
		return ccLog;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	public void setCcLog(CustomerCallLog ccLog) {
		this.ccLog = ccLog;
	}

    /**
     * 功能：
     * @return
     */
    @Override
    public String toString()
    {
        return "MsicallLog [vid=" + vid + ", companyId=" + super.getCompanyId() + ", call=" + call + ", called=" + called
                + ", linkCalled=" + linkCalled + ", serviceId=" + serviceId + ", msiuserId=" + msiuserId
                + ", callInTime=" + callInTime + ", inQueueTime=" + inQueueTime + ", inQueueSeconds=" + inQueueSeconds
                + ", talkInTime=" + talkInTime + ", talkEndTime=" + talkEndTime + ", callEndTime=" + callEndTime
                + ", seconds=" + seconds + ", minutes=" + minutes + ", endType=" + endType + ", callType=" + callType
                + ", callSystemType=" + callSystemType + ", clientType=" + clientType + ", info=" + info + ", voxFile="
                + voxFile + ", customerCallLogId=" + customerCallLogId + ", bturn=" + bturn + ", callId=" + callId
                + ", customerSatisfactional=" + customerSatisfactional + ", ccLog=" + ccLog + ", workNo=" + workNo
                + ", msiuserName=" + msiuserName + ", secondStr=" + secondStr + ", voxFileName=" + voxFileName
                + ", isForkGroup=" + isForkGroup + "]";
    }
	

}