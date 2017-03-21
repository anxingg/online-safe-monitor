package cn.com.qytx.hotline.projectquestion.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SMSMTRecord")
public class SMSMTRecord implements Serializable {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -1352296358396409728L;

	/**
	 * 主键vid
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vid;
	/**
	 * 电话
	 */
	@Column(name="phone")
    private String phone;
    /**
     * 内容
     */
	@Column(name="content")
    private String content;
    /**
     * 插入时间
     */
	@Column(name="insertTime")
    private Timestamp insertTime;
    /**
     * 发送时间
     */
	@Column(name="sendTime")
    private Timestamp sendTime;
    /**
     * 发送状态
     */
	@Column(name="sendState")
    private Integer sendState;
    /**
     * 是否要求返回状态确认报告
     */
	@Column(name="registeredDelivery")
    private Integer registeredDelivery;
    
    /**
     * 显示号码
     */
	@Column(name="srcId")
    private String srcId;
    
    /**
     * 系统名称
     */
	@Column(name="systemName")
    private String systemName;
    /**
     * 模块名称
     */
	@Column(name="moduleName")
    private String moduleName;
    
    /**
     * 系统ID
     */
	@Column(name="systemId")
    private String systemId;
    /**
     * 消息ID
     */
	@Column(name="msgId")
    private Long msgId;
    /**
     * 对应上行表中LinkId
     */
	@Column(name="linkId")
    private String linkId;
    /**
     * 短信格式 1：短信  2：WapPush
     */
	@Column(name="msgFmt")
    private Integer msgFmt;
//    /**
//     * 
//     */
//	@Column(name="signName")
//    private String signName;
    
	@Column(name="logId")
    private Integer logId;
    
    /**
     * 创建人id
     */
	@Column(name="signName")
    private Integer createUserId;
    /**
     * 创建time
     */
	@Column(name="createTime")
    private Timestamp createTime;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getSendState() {
		return sendState;
	}
	public void setSendState(Integer sendState) {
		this.sendState = sendState;
	}
	public Integer getRegisteredDelivery() {
		return registeredDelivery;
	}
	public void setRegisteredDelivery(Integer registeredDelivery) {
		this.registeredDelivery = registeredDelivery;
	}
	public String getSrcId() {
		return srcId;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public Long getMsgId() {
		return msgId;
	}
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public Integer getMsgFmt() {
		return msgFmt;
	}
	public void setMsgFmt(Integer msgFmt) {
		this.msgFmt = msgFmt;
	}
//	public String getSignName() {
//		return signName;
//	}
//	public void setSignName(String signName) {
//		this.signName = signName;
//	}
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}
