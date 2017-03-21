package cn.com.qytx.hotline.ivr.domain;

import java.sql.Timestamp;

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

import cn.com.qytx.hotline.customercall.domain.CustomerCallLog;
import cn.com.qytx.oa.domain.OaBaseEntity;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 
 * 功能:短信发送功能 版本: 1.0 开发人员: 徐长江 创建日期: 2013-7-27 修改日期: 2013-7-27 修改列表:
 */
@Entity
@Table(name = "SMSSUMRecord")
public class SmsSumRecord extends OaBaseEntity {
	/**
	 * 用户标识
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vid;
	/**
	 * 电话
	 */
	@Column(name = "phone")
	private String phone;
	/**
	 * 短信内容
	 */
	@Column(name = "content")
	private String content;
	/**
	 * 插入时间
	 */
	@Column(name = "insertTime")
	private Timestamp insertTime;
	/**
	 * 发送时间
	 */
	@Column(name = "sendtime")
	private Timestamp sendTime;
	/**
	 * 发送状态 0发送失败1发送成功
	 */
	@Column(name = "sendstate")
	private Integer sendState;
	/**
	 * //是否需要状态 0不需要状态报告1需要状态报告
	 */
	@Column(name = "registereddelivery")
	private Integer registeredDelivery;
	/**
	 * 10658258
	 */
	@Column(name = "srcid")
	private String srcId;
	/**
	 * 什么系统 mayorHotline
	 */
	@Column(name = "systemname")
	private String systemName;
	/**
	 * 什么模块 backCustomerCall
	 */
	@Column(name = "modulename")
	private String moduleName;
	/**
	 * 需要发送短信这个工单的标识
	 */
	@Column(name = "systemid")
	private String systemId;
	/**
	 * 给个默认值 0
	 */
	@Column(name = "msgid")
	private String msgId;
	/**
	 * 
	 */
	@Column(name = "linkid")
	private String linkId;
	/**
	 * 给个默认值0
	 */
	@Column(name = "msgfmt")
	private Integer msgFmt;

	@Column(name = "createtime")
	private Timestamp createTime;

	// @Column(name = "createuserid")
	// private Integer createUserId;
	@Transient
	private CustomerCallLog customerCallLog;

	@JoinColumn(name = "createuserid")
	@ManyToOne(fetch = FetchType.LAZY)
	private UserInfo userInfo;
	/**
	 * 
	 */
	private String signName;

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

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public CustomerCallLog getCustomerCallLog() {
		return customerCallLog;
	}

	public void setCustomerCallLog(CustomerCallLog customerCallLog) {
		this.customerCallLog = customerCallLog;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	// public Integer getCreateUserId() {
	// return createUserId;
	// }
	// public void setCreateUserId(Integer createUserId) {
	// this.createUserId = createUserId;
	// }
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((customerCallLog == null) ? 0 : customerCallLog.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result + ((linkId == null) ? 0 : linkId.hashCode());
		result = prime * result
				+ ((moduleName == null) ? 0 : moduleName.hashCode());
		result = prime * result + ((msgFmt == null) ? 0 : msgFmt.hashCode());
		result = prime * result + ((msgId == null) ? 0 : msgId.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime
				* result
				+ ((registeredDelivery == null) ? 0 : registeredDelivery
						.hashCode());
		result = prime * result
				+ ((sendState == null) ? 0 : sendState.hashCode());
		result = prime * result
				+ ((sendTime == null) ? 0 : sendTime.hashCode());
		result = prime * result
				+ ((signName == null) ? 0 : signName.hashCode());
		result = prime * result + ((srcId == null) ? 0 : srcId.hashCode());
		result = prime * result
				+ ((systemId == null) ? 0 : systemId.hashCode());
		result = prime * result
				+ ((systemName == null) ? 0 : systemName.hashCode());
		result = prime * result
				+ ((userInfo == null) ? 0 : userInfo.hashCode());
		result = prime * result + ((vid == null) ? 0 : vid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (!super.equals(obj)){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		SmsSumRecord other = (SmsSumRecord) obj;
		if (content == null) {
			if (other.content != null){
				return false;
			}
		} else if (!content.equals(other.content)){
			return false;
		}
		if (createTime == null) {
			if (other.createTime != null){
				return false;
			}
		} else if (!createTime.equals(other.createTime)){
			return false;
		}
		if (customerCallLog == null) {
			if (other.customerCallLog != null){
				return false;
			}
		} else if (!customerCallLog.equals(other.customerCallLog)){
			return false;
		}
		if (insertTime == null) {
			if (other.insertTime != null){
				return false;
			}
		} else if (!insertTime.equals(other.insertTime)){
			return false;
		}
		if (linkId == null) {
			if (other.linkId != null){
				return false;
			}
		} else if (!linkId.equals(other.linkId)){
			return false;
		}
		if (moduleName == null) {
			if (other.moduleName != null){
				return false;
			}
		} else if (!moduleName.equals(other.moduleName)){
			return false;
		}
		
		boolean check = true;
		check = equals01 (other);
		check = equals02 (other);
		return check;
	}
	
	public boolean equals01(SmsSumRecord other){
		boolean check = true;
		if (msgFmt == null) {
			if (other.msgFmt != null){
				check = false;
			}
		} else if (!msgFmt.equals(other.msgFmt)){
			check = false;
		}
		if (msgId == null) {
			if (other.msgId != null){
				check = false;
			}
		} else if (!msgId.equals(other.msgId)){
			check = false;
		}
		if (phone == null) {
			if (other.phone != null){
				check = false;
			}
		} else if (!phone.equals(other.phone)){
			check = false;
		}
		if (registeredDelivery == null) {
			if (other.registeredDelivery != null){
				check = false;
			}
		} else if (!registeredDelivery.equals(other.registeredDelivery)){
			check = false;
		}
		if (sendState == null) {
			if (other.sendState != null){
				check = false;
			}
		} else if (!sendState.equals(other.sendState)){
			check = false;
		}
		return check ;
	}
	
	public boolean equals02 (SmsSumRecord other){
		boolean check = true;
		if (sendTime == null) {
			if (other.sendTime != null){
				check = false;
			}
		} else if (!sendTime.equals(other.sendTime)){
			check = false;
		}
		if (signName == null) {
			if (other.signName != null){
				check = false;
			}
		} else if (!signName.equals(other.signName)){
			check = false;
		}
		if (srcId == null) {
			if (other.srcId != null){
				check = false;
			}
		} else if (!srcId.equals(other.srcId)){
			check = false;
		}
		if (systemId == null) {
			if (other.systemId != null){
				check = false;
			}
		} else if (!systemId.equals(other.systemId)){
			check = false;
		}
		if (systemName == null) {
			if (other.systemName != null){
				check = false;
			}
		} else if (!systemName.equals(other.systemName)){
			check = false;
		}
		if (userInfo == null) {
			if (other.userInfo != null){
				check = false;
			}
		} else if (!userInfo.equals(other.userInfo)){
			check = false;
		}
		if (vid == null) {
			if (other.vid != null){
				check = false;
			}
		} else if (!vid.equals(other.vid)){
			check = false;
		}
		return check;
	}

}
