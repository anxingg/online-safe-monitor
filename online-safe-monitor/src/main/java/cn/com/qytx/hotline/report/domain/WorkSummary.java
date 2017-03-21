package cn.com.qytx.hotline.report.domain;
/**
 * 功能:工作汇总表实体
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-1
 * 修改日期: 2014-12-1
 * 修改列表:
 */
public class WorkSummary {

	/**
	 * 业务类型
	 */
	private String businessName;
	/**
	 * 合计数量
	 */
	private Integer allCount;
	/**
	 * 电话咨询数量
	 */
	private Integer phoneNum;
	/**
	 * 短信平台数量
	 */
	private Integer messageNum;
	/**
	 * 语音留言数量
	 */
	private Integer soundNum;
	
	public WorkSummary() {
	}
	
	public WorkSummary(String businessName, Integer allCount, Integer phoneNum,
			Integer messageNum, Integer soundNum) {
		super();
		this.businessName = businessName;
		this.allCount = allCount;
		this.phoneNum = phoneNum;
		this.messageNum = messageNum;
		this.soundNum = soundNum;
	}



	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public Integer getAllCount() {
		return allCount;
	}
	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}
	public Integer getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(Integer phoneNum) {
		this.phoneNum = phoneNum;
	}
	public Integer getMessageNum() {
		return messageNum;
	}
	public void setMessageNum(Integer messageNum) {
		this.messageNum = messageNum;
	}
	public Integer getSoundNum() {
		return soundNum;
	}
	public void setSoundNum(Integer soundNum) {
		this.soundNum = soundNum;
	}
	
}
