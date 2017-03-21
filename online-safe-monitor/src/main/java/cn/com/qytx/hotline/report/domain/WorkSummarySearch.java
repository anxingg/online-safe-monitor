package cn.com.qytx.hotline.report.domain;
/**
 * 功能:工作汇总查询实体类
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-1
 * 修改日期: 2014-12-1
 * 修改列表:
 */
public class WorkSummarySearch {

	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 所属地市
	 */
	private Integer isForkGroup;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getIsForkGroup() {
		return isForkGroup;
	}
	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}
	
}
