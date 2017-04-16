/**
 * 
 */
package cn.com.wh.earlywarning.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.collectsensor.domain.CollectSensor;

/**
 * 功能: 
 * 版本: 1.0
 * 开发人员: 王刚
 * 创建日期: 2017年4月13日
 * 修改日期: 2017年4月13日
 * 修改列表: 
 */
@Entity
@Table(name="WARNINGLOG")
public class EarlyWarning implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 561543642756022653L;
	
	   //编号
		@Id
		@Column(name="ID")
		private Integer id;
		
		/**
	     * 传感器ID
	     */
	    @JoinColumn(name="SENSORID")
	    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
		private CollectSensor collectSensor;
		
		
		//预警标题
		@Column(name="WARNINGTITLE")
		private String waningTitle;
		
		//预警内容
		@Column(name="WARNINGCONTENT")
		private String waringContent;
		
		//预警级别 1，2，3级
		@Column(name="WARNINGLEVEL")
		private Integer waringLevel;
		//预警开始时间
		@Column(name="BEGINTIME")
		private Date beginTime;
		//预警结束时间
		@Column(name="ENDTIME")
		private Date endTime;
		//持续次数
		@Column(name="TIMES")
		private Integer times;
		//用户接受预警时间
		@Column(name="RECEIVETIME")
		private Date recelveTime;
		//接受预警用户
		@JoinColumn(name = "RECEIVEUSER")
		@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
		private UserInfo receiveUser;
		//用户处理时间，提交处理报告的时间
		@Column(name="SUBMITRESULTTIME")
		private Date submitResultTime;
		//用户处理报告
		@Column(name="RESULTCONTENT")
		private String resultContent;
		//处理报告填写用户
		@JoinColumn(name = "SUBMITRESULTUSER")
		@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
		private UserInfo createUserInfo;
		
		//状态，0：未处理，1：已接受，2：已处理
		@Column(name="STATUS")
		private Integer status;
		

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public CollectSensor getCollectSensor() {
			return collectSensor;
		}

		public void setCollectSensor(CollectSensor collectSensor) {
			this.collectSensor = collectSensor;
		}

		public String getWaningTitle() {
			return waningTitle;
		}

		public void setWaningTitle(String waningTitle) {
			this.waningTitle = waningTitle;
		}

		public String getWaringContent() {
			return waringContent;
		}

		public void setWaringContent(String waringContent) {
			this.waringContent = waringContent;
		}

		public Integer getWaringLevel() {
			return waringLevel;
		}

		public void setWaringLevel(Integer waringLevel) {
			this.waringLevel = waringLevel;
		}

		public Date getBeginTime() {
			return beginTime;
		}

		public void setBeginTime(Date beginTime) {
			this.beginTime = beginTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}

		public Integer getTimes() {
			return times;
		}

		public void setTimes(Integer times) {
			this.times = times;
		}

		public Date getRecelveTime() {
			return recelveTime;
		}

		public void setRecelveTime(Date recelveTime) {
			this.recelveTime = recelveTime;
		}

		public UserInfo getReceiveUser() {
			return receiveUser;
		}

		public void setReceiveUser(UserInfo receiveUser) {
			this.receiveUser = receiveUser;
		}

		public Date getSubmitResultTime() {
			return submitResultTime;
		}

		public void setSubmitResultTime(Date submitResultTime) {
			this.submitResultTime = submitResultTime;
		}

		public String getResultContent() {
			return resultContent;
		}

		public void setResultContent(String resultContent) {
			this.resultContent = resultContent;
		}

		public UserInfo getCreateUserInfo() {
			return createUserInfo;
		}

		public void setCreateUserInfo(UserInfo createUserInfo) {
			this.createUserInfo = createUserInfo;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

}
