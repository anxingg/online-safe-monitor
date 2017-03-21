package cn.com.qytx.hotline.customercall.domain;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

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
import org.apache.struts2.ServletActionContext;

import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.ivr.domain.MsicallLog;
import cn.com.qytx.oa.domain.OaConst;
import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.base.domain.DeleteState;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;

/**
 * 功能:坐席任务记录 （CustomerCallLog) 版本: 1.0 开发人员: 徐长江 创建日期: 2013-7-17 修改日期: 2013-7-17
 * 修改列表:
 */
@Entity
@Table(name="tb_customerCallLog")
public class CustomerCallLog extends BaseDomain implements Serializable {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 标识符
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vid;
	
	/**
	 * 工作流ID
	 */
	@Column(name = "workflowId")
	private String workflowId;
	

	/**
	 * 工单号 工单编号就按照年+月+四位序号
	 */
	@Column(name = "ccl_sn")
	private String cclSn;

	
	/**
	 * 是否删除 0未删除 1删除
	 */
	@DeleteState
	@Column(name = "is_delete")
	private Integer isDelete;

	/**
	 * 最后修改人
	 */
	@Transient
	private UserInfo lastUpdateUser;

	/**
	 * 最后修改时间
	 */
	@Column(name = "last_update_time")
	private Timestamp lastUpdateTime;

	/**
	 * 来电手机号码
	 */
	@Column(name = "phone")
	private String phone;

	/**
	 * 来电人姓名
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 来电人性别 1 男 0 女
	 */
	@Column(name = "sex")
	private Integer sex;
	
	/**
	 * 来电人年龄
	 */
	@Column(name = "age")
	private Integer age;
	
	

	/**
	 * 坐席对应手机号，被叫号码
	 */
	@Column(name = "userMobile")
	private String userMobile;

	/**
	 * 来电座机号
	 */
	@Column(name = "telephone")
	private String telephone;

	/**
	 * 用户地址
	 */
	@Column(name = "address")
	private String address;

	/**
	 * 用户类别 对应维表的levelType
	 */
	@Column(name = "userLevel")
	private Integer userLevel;


	/**
	 * 工单回访时间
	 */
	@Column(name = "callBackTime")
	private Timestamp callBackTime;
	
	/**
	 * 工单回访人id
	 */
	@Column(name = "callback_user_id")
	private Integer callBackUserId;
	
	/**
	 * 被举报坐席id
	 */
	@Column(name = "report_seat_user_id")
	private Integer reportSeatUserId;

	/**
	 * 工单内容
	 */
	@Column(name = "logInfo")
	private String logInfo;
	/**
	 * 工单内容
	 */
	@Column(name = "seat_replay")
	private String seatReplay;

	/**
	 * 工单类型 写死 1咨询 2投诉 3建议
	 */
	@Column(name = "type")
	private Integer type;
	/**
	 * 受理方式    写死  1 电话  2短信  3 录音
	 */
	@Column(name = "access_type")
	private Integer accessType;
	/**
	 * 受理来源ID
	 */
	@Column(name="access_source_id")
	private Integer accessSourceId;
	/**
	 * 业务类别   数据字典维护
	 */
	@Column(name = "business_type")
	private Integer businessType;

	/**
	 * 涉及范围 维表维护 demands_scope
	 */
	@Column(name = "scope")
	private Integer scope;

	/**
	 * 最后修改人ID
	 */
	@Column(name="last_update_user_id")
	private Integer lastUpdateUserId;

	@Column(name="is_fork_group")
	private Integer isForkGroup;
	/**
	 * 当前责任人  curr_deal_user_id
	 */
	@JoinColumn(name = "curr_deal_user_id")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private UserInfo toDealUser;
	@JoinColumn(name = "crm_Id")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private CRM crm;

	/**
	 * 工单状态 state
	 * 
	 * 1 已受理 2 待回访 3 待办结 4 已办结
	 * 
	 */
	@Column(name = "state")
	private Integer state;

	/**
	 * 记录人
	 */
	@JoinColumn(name = "record_user_id")
	@ManyToOne( fetch = FetchType.LAZY)
	private UserInfo recordUser;

	/**
	 * 记录时间
	 */
	@Column(name = "recordTime")
	private Timestamp recordTime;

	/**
	 * 处理时限 默认24小时 -1表示超时 0 表示小于1小时
	 */
	@Column(name = "time_limit")
	private Integer timeLimit;
	/**
	 * 回访意见
	 */
	/**
	 * 后台工单处理时的答复内容 蔡明雪改
	 * 
	 * @return
	 */
	@Column(name = "replyInfo",length=5000)
	private String replyInfo;

	/**
	 * 通话记录sessionId
	 */
	
	@Column(name = "msiSessionId")
	private String msiSessionId;

	/**
	 * msi坐席Id
	 */
	@Column(name = "msiUserId")
	private Integer msiUserId;
	/**
	 * 话后处理时长
	 */
	@Column(name = "handup_size")
	private Integer handupSize;

	/**
	 * 回访结果 0表示成功 1表示不成功
	 */
	@Column(name = "visitResult")
	private Integer visitResult;

	/**
	 * 回访失败原因 1.无人接听 2.电话正忙 3.电话秘书 4.电话关机 5.电话停机 6.空号 7.用户拒访
	 */
	@Column(name = "visitFaildReason")
	private Integer visitFaildReason;

	/**
	 * ==========================以下为前台查询或者返回字段==================================
	 */
	
	/**
	 * 来电时间
	 */
	@Transient
	private Timestamp callTime;
	
	/**
	 * 工单来源
	 */
	@Transient
	private String source;

	
	/**
	 * 关联话单Id
	 */
	@Transient
	private List<MsicallLog> msicallLogList;

	/**
	 * 查询列表状态 1 来电弹屏历史工单页面
	 */
	@Transient
	private Integer listVo;

	/**
	 * 工单处理状态 1 已受理 2 待回访 3 待办结 4 已办结
	 */
	@Transient
	private Integer updateStateVo;

	/**
	 * 关键字查询
	 */
	@Transient
	private String searchKey;

	/**
	 * 显示范围 -1表示全部 1表示与我相关
	 */
	@Transient
	private Integer searchScope;

	/**
	 * 受理开始时间
	 */
	@Transient
	private Timestamp beginTime;

	/**
	 * 受理结束时间
	 */
	@Transient
	private Timestamp endTime;

	/**
	 * 受理开始时间字符串
	 */
	@Transient
	private String beginTimeStr;

	/**
	 * 受理结束时间字符串
	 */
	@Transient
	private String endTimeStrLong;
	@Transient
	private String beginTimeStrLong;
	
	/**
	 * 受理结束时间字符串
	 */
	@Transient
	private String endTimeStr;

	/**
	 * 受理人姓名
	 */
	@Transient
	private String recordUserName;

	/**
	 * 来电归属地
	 */
	@Transient
	private String mobileArea;

	/**
	 * 咨询类别名称
	 */
	@Transient
	private String categoryName;

	/**
	 * 用户类别 对应维表的levelType 名称
	 */
	@Transient
	private String userLevelName;

	/**
	 * 投诉级别名称
	 */
	@Transient
	private String levelName;

	/**
	 * 与我有关的工单
	 */
	@Transient
	private String cllOwns;

	/**
	 * 工单内容
	 */
	@Transient
	private String cclContent;

	/**
	 * 通话时长
	 */
	@Transient
	private Integer dealLength;
	/**
	 * 办结时间
	 */
	@Column(name = "dealOverTime")
	private Timestamp dealOverTime;

	/**
	 * 回复时间
	 */
	@Column(name = "HandleTime")
	private Timestamp answerTime;

	/**
	 * 回访成功的结果
	 * 
	 * @return
	 */
	@Column(name = "callBackSuccessResult")
	private String callBackSuccessResult;

	/**
	 * 办结结果
	 * 
	 * @return
	 */
	@Column(name = "dealOverResult")
	private String dealOverResult;
    /**
	 * 工单办结人信息
	 * @return
	 */
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name = "dealover_user_id")
	private UserInfo dealoverUser;
	
	public UserInfo getDealoverUser() {
		return dealoverUser;
	}

	public void setDealoverUser(UserInfo dealoverUser) {
		this.dealoverUser = dealoverUser;
	}
    

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCallBackSuccessResult() {
		return callBackSuccessResult;
	}

	public void setCallBackSuccessResult(String callBackSuccessResult) {
		this.callBackSuccessResult = callBackSuccessResult;
	}

	public String getDealOverResult() {
		return dealOverResult;
	}

	public void setDealOverResult(String dealOverResult) {
		this.dealOverResult = dealOverResult;
	}

	public Timestamp getCallBackTime() {
		return callBackTime;
	}

	public void setCallBackTime(Timestamp callBackTime) {
		this.callBackTime = callBackTime;
	}

	public Timestamp getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Timestamp answerTime) {
		this.answerTime = answerTime;
	}

	public Timestamp getDealOverTime() {
		return dealOverTime;
	}

	public void setDealOverTime(Timestamp dealOverTime) {
		this.dealOverTime = dealOverTime;
	}

	public String getCclContent() {
		return cclContent;
	}

	public void setCclContent(String cclContent) {
		this.cclContent = cclContent;
	}

	public Integer getDealLength() {
		return dealLength;
	}

	public void setDealLength(Integer dealLength) {
		this.dealLength = dealLength;
	}

	public String getCllOwns() {
		return cllOwns;
	}

	public void setCllOwns(String cllOwns) {
		this.cllOwns = cllOwns;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getCclSn() {
		return cclSn;
	}

	public void setCclSn(String cclSn) {
		this.cclSn = cclSn;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public UserInfo getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(UserInfo lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCallTime() {
		return callTime;
	}

	public void setCallTime(Timestamp callTime) {
		this.callTime = callTime;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getScope() {
		return scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public UserInfo getRecordUser() {
		return recordUser;
	}

	public void setRecordUser(UserInfo recordUser) {
		this.recordUser = recordUser;
	}

	public Timestamp getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Timestamp recordTime) {
		this.recordTime = recordTime;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getUpdateStateVo() {
		return updateStateVo;
	}

	public void setUpdateStateVo(Integer updateStateVo) {
		this.updateStateVo = updateStateVo;
	}

	public String getReplyInfo() {
		return replyInfo;
	}

	public void setReplyInfo(String replyInfo) {
		this.replyInfo = replyInfo;
	}

	public Integer getMsiUserId() {
		return msiUserId;
	}

	public void setMsiUserId(Integer msiUserId) {
		this.msiUserId = msiUserId;
	}

	public String getMsiSessionId() {
		return msiSessionId;
	}

	public void setMsiSessionId(String msiSessionId) {
		this.msiSessionId = msiSessionId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public UserInfo getToDealUser() {
		return toDealUser;
	}

	public void setToDealUser(UserInfo toDealUser) {
		this.toDealUser = toDealUser;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Integer getSearchScope() {
		return searchScope;
	}

	public void setSearchScope(Integer searchScope) {
		this.searchScope = searchScope;
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

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String startTime) {
		if (!StringUtils.isEmpty(startTime)) {
			beginTimeStr += " 00:00:00.000";
			setBeginTime(DateTimeUtil.stringToTimestamp(beginTimeStr,
					OaConst.TIME_ALL_FORMAT_STR));
		}
		this.beginTimeStr = startTime;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTime) {
		if (!StringUtils.isEmpty(endTime)) {
			endTimeStr += " 23:59:59.999";
			setEndTime(DateTimeUtil.stringToTimestamp(endTimeStr,
					OaConst.TIME_ALL_FORMAT_STR));
		}
		this.endTimeStr = endTime;
	}

	public Integer getListVo() {
		return listVo;
	}

	public void setListVo(Integer listVo) {
		this.listVo = listVo;
	}

	public String getRecordUserName() {
		return recordUserName;
	}

	public void setRecordUserName(String recordUserName) {
		this.recordUserName = recordUserName;
	}

	public List<MsicallLog> getMsicallLogList() {
		return msicallLogList;
	}

	public void setMsicallLogList(List<MsicallLog> msicallLogList) {
		this.msicallLogList = msicallLogList;
	}

	public String getMobileArea() {
		return mobileArea;
	}

	public void setMobileArea(String mobileArea) {
		this.mobileArea = mobileArea;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUserLevelName() {
		return userLevelName;
	}

	public void setUserLevelName(String userLevelName) {
		this.userLevelName = userLevelName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Integer getVisitResult() {
		return visitResult;
	}

	public void setVisitResult(Integer visitResult) {
		this.visitResult = visitResult;
	}

	public Integer getVisitFaildReason() {
		return visitFaildReason;
	}

	public void setVisitFaildReason(Integer visitFaildReason) {
		this.visitFaildReason = visitFaildReason;
	}

    public String getWorkflowId()
    {
        return workflowId;
    }

    public void setWorkflowId(String workflowId)
    {
        this.workflowId = workflowId;
    }


	/**
	 * @return the crm
	 */
	public CRM getCrm() {
		return crm;
	}

	public void setCrm(CRM crm) {
		this.crm = crm;
	}
	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	public Integer getCallBackUserId() {
		return callBackUserId;
	}
	public void setCallBackUserId(Integer callBackUserId) {
		this.callBackUserId = callBackUserId;
	}
	public Integer getReportSeatUserId() {
		return reportSeatUserId;
	}
	public void setReportSeatUserId(Integer reportSeatUserId) {
		this.reportSeatUserId = reportSeatUserId;
	}
	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSeatReplay() {
		return seatReplay;
	}

	public void setSeatReplay(String seatReplay) {
		this.seatReplay = seatReplay;
	}

	public Integer getAccessType() {
		return accessType;
	}
	public void setAccessType(Integer accessType) {
		this.accessType = accessType;
	}
	public Integer getAccessSourceId() {
		return accessSourceId;
	}

	public void setAccessSourceId(Integer accessSourceId) {
		this.accessSourceId = accessSourceId;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public Integer getHandupSize() {
		return handupSize;
	}

	public void setHandupSize(Integer handupSize) {
		this.handupSize = handupSize;
	}
	public String getEndTimeStrLong() {
		return endTimeStrLong;
	}
	public void setEndTimeStrLong(String endTimeStrLong) {
		if (!StringUtils.isEmpty(endTimeStrLong)) {
			setEndTime(DateTimeUtil.stringToTimestamp(endTimeStrLong+".999",
					OaConst.TIME_ALL_FORMAT_STR));
		}
		this.endTimeStrLong = endTimeStrLong;
	}
	public String getBeginTimeStrLong() {
		return beginTimeStrLong;
	}
	public void setBeginTimeStrLong(String beginTimeStrLong) {
		if (!StringUtils.isEmpty(beginTimeStrLong)) {
			setBeginTime(DateTimeUtil.stringToTimestamp(beginTimeStrLong+".000",
					OaConst.TIME_FORMAT_STR));
		}
		this.beginTimeStrLong = beginTimeStrLong;
	}

    /**
     * 功能：
     * @return
     */
    @Override
    public String toString()
    {
        UserInfo  user=(UserInfo) ServletActionContext.getRequest().getSession().getAttribute("adminUser");
        return "处理人"+user.getUserName()+"CustomerCallLog [ workflowId=" + workflowId + ", cclSn=" + cclSn + ", isDelete="
                + isDelete + ", lastUpdateUser=" + lastUpdateUser + ", lastUpdateTime=" + lastUpdateTime + ", phone="
                + phone + ", name=" + name + ", sex=" + sex + ", age=" + age + ", userMobile=" + userMobile
                + ", telephone=" + telephone + ", address=" + address + ", userLevel=" + userLevel + ", callBackTime="
                + callBackTime + ", callBackUserId=" + callBackUserId + ", reportSeatUserId=" + reportSeatUserId
                + ", logInfo=" + logInfo + ", seatReplay=" + seatReplay + ", type=" + type + ", accessType="
                + accessType + ", accessSourceId=" + accessSourceId + ", businessType=" + businessType + ", scope="
                + scope + ", lastUpdateUserId=" + lastUpdateUserId + ", isForkGroup=" + isForkGroup + ", toDealUser="
               + toDealUser +  ", state=" + state + ", recordUser=" + recordUser + ", recordTime="
                + recordTime + ", timeLimit=" + timeLimit + ", replyInfo=" + replyInfo + ", msiSessionId="
                + msiSessionId + ", msiUserId=" + msiUserId + ", handupSize=" + handupSize + ", visitResult="
                + visitResult + ", visitFaildReason=" + visitFaildReason + ", callTime=" + callTime + ", source="
                + source +  ", updateStateVo="
                + updateStateVo + ", searchKey=" + searchKey + ", searchScope=" + searchScope + ", beginTime="
                + beginTime + ", endTime=" + endTime + ", beginTimeStr=" + beginTimeStr + ", endTimeStrLong="
                + endTimeStrLong + ", beginTimeStrLong=" + beginTimeStrLong + ", endTimeStr=" + endTimeStr
                + ", recordUserName=" + recordUserName + ", mobileArea=" + mobileArea + ", categoryName="
                + categoryName + ", userLevelName=" + userLevelName + ", levelName=" + levelName + ", cllOwns="
                + cllOwns + ", cclContent=" + cclContent + ", dealLength=" + dealLength + ", dealOverTime="
                + dealOverTime + ", answerTime=" + answerTime + ", callBackSuccessResult=" + callBackSuccessResult
                + ", dealOverResult=" + dealOverResult + ", dealoverUser=" + dealoverUser + "]";
    }
	
	
	
	
}
