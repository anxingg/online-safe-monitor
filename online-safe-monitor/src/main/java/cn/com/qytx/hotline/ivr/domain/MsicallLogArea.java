package cn.com.qytx.hotline.ivr.domain;

/**
 * 功能:来电区域统计
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-19
 * 修改日期: 2014-12-19
 * 修改列表:
 */
public class MsicallLogArea {
	/**
	 * 区域
	 */
	private String area;

	/**
	 * 呼入量
	 */
	private Integer callInNum;
	/**
	 * 人工受理
	 */
	private Integer acceptNum;
	/**
	 * 放弃数
	 */
	private Integer giveUpNum;
	/**
	 * 自动查询
	 */
	private Integer autoQueryNum;
	
	
	
	public MsicallLogArea() {
	}

	public MsicallLogArea(String area, Integer callInNum, Integer acceptNum,
			Integer giveUpNum, Integer autoQueryNum) {
		super();
		this.area = area;
		this.callInNum = callInNum;
		this.acceptNum = acceptNum;
		this.giveUpNum = giveUpNum;
		this.autoQueryNum = autoQueryNum;
	}

	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getCallInNum() {
		return callInNum;
	}

	public void setCallInNum(Integer callInNum) {
		this.callInNum = callInNum;
	}

	public Integer getAcceptNum() {
		return acceptNum;
	}

	public void setAcceptNum(Integer acceptNum) {
		this.acceptNum = acceptNum;
	}

	public Integer getGiveUpNum() {
		return giveUpNum;
	}

	public void setGiveUpNum(Integer giveUpNum) {
		this.giveUpNum = giveUpNum;
	}

	public Integer getAutoQueryNum() {
		return autoQueryNum;
	}

	public void setAutoQueryNum(Integer autoQueryNum) {
		this.autoQueryNum = autoQueryNum;
	}
	
	
}
