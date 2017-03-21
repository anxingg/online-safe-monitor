package cn.com.qytx.platform.domain;

import java.io.Serializable;

public class OutcallFunction implements Serializable {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 封装外呼功能设置的相关信息
	 * 
	 * 2014-04-07 蔡明雪
	 */
	//是否开启外呼功能   1：不开启，2：开启
	private Integer isEnableOutcall;
	//是否支持自定义脚本  0：不开启，1：开启
	private Integer customScript;
	//是否支持导入自定义资料  0：不开启 1：开启
	private Integer customData;
	//是否支持号码手动分配  0：不开启 1：开启
	private Integer definePhone;
	//是否支持预测拨号功能  0：不开启 1：开启
	private Integer forecastPhone;
	
	//参数描述
	private String parDescribe;
	//参数项
	private String parItems;


	
	//默认的构造函数
	public OutcallFunction() {
		
	}



	public Integer getIsEnableOutcall() {
		return isEnableOutcall;
	}



	public void setIsEnableOutcall(Integer isEnableOutcall) {
		this.isEnableOutcall = isEnableOutcall;
	}



	public Integer getCustomScript() {
		return customScript;
	}



	public void setCustomScript(Integer customScript) {
		this.customScript = customScript;
	}



	public Integer getCustomData() {
		return customData;
	}



	public void setCustomData(Integer customData) {
		this.customData = customData;
	}



	public Integer getDefinePhone() {
		return definePhone;
	}



	public void setDefinePhone(Integer definePhone) {
		this.definePhone = definePhone;
	}



	public Integer getForecastPhone() {
		return forecastPhone;
	}



	public void setForecastPhone(Integer forecastPhone) {
		this.forecastPhone = forecastPhone;
	}



	public String getParDescribe() {
		return parDescribe;
	}



	public void setParDescribe(String parDescribe) {
		this.parDescribe = parDescribe;
	}



	public String getParItems() {
		return parItems;
	}



	public void setParItems(String parItems) {
		this.parItems = parItems;
	}
	
	
	
}
