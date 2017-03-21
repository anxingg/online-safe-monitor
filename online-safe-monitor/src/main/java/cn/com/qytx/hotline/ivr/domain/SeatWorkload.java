package cn.com.qytx.hotline.ivr.domain;

import java.io.Serializable;

/**
 * 
 * 功能:坐席工作量统计 版本: 1.0 开发人员: 李华伟 创建日期: 2014-2-12 修改日期: 2014-2-12 修改列表:
 */

public class SeatWorkload implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 9052681326095514336L;

	/**
	 * 坐席Id
	 */
	private int msiuserId;

	/**
	 * 坐席工号
	 */
	private String workNo;

	/**
	 * 坐席姓名
	 */
	private String name;

	/**
	 * 呼入数
	 */
	private int callIn;

	/**
	 * 接听数
	 */
	private int answercallIn;

	/**
	 * 呼入接听率 （%）
	 */
	private double answercallInRate;

	/**
	 * 平均振铃时长（秒）
	 */
	private int averageRingingSecond;

	/**
	 * 平均呼入通话时长（秒）
	 */
	private int averageCallInSecond;

	/**
	 * 呼入通话总时长（分）
	 */
	private int totalCallInMinute;

	/**
	 * 外呼数
	 */
	private int callOut;

	/**
	 * 外呼成功数
	 */
	private int callOutSuccess;

	/**
	 * 外呼成功率(%)
	 */
	private double callOutSuccessRate;

	/**
	 * 平均外呼通话时长（秒）
	 */
	private int averageCallOutSecond;

	/**
	 * 外呼通话总时长（分）
	 */
	private int totleCallOutMinute;

	/**
	 * 登录总时长（分）
	 */
	private int totleLoginMinute;

	/**
	 * 置忙总时长（分）
	 */
	private int totleBusyMinute;

	/**
	 * 空闲总时长（分
	 */
	private int totleFreeMinute;

	public int getMsiuserId() {
		return msiuserId;
	}

	public void setMsiuserId(int msiuserId) {
		this.msiuserId = msiuserId;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCallIn() {
		return callIn;
	}

	public void setCallIn(int callIn) {
		this.callIn = callIn;
	}

	public int getAnswercallIn() {
		return answercallIn;
	}

	public void setAnswercallIn(int answercallIn) {
		this.answercallIn = answercallIn;
	}

	public double getAnswercallInRate() {
		return answercallInRate;
	}

	public void setAnswercallInRate(double answercallInRate) {
		this.answercallInRate = answercallInRate;
	}

	public int getAverageRingingSecond() {
		return averageRingingSecond;
	}

	public void setAverageRingingSecond(int averageRingingSecond) {
		this.averageRingingSecond = averageRingingSecond;
	}

	public int getAverageCallInSecond() {
		return averageCallInSecond;
	}

	public void setAverageCallInSecond(int averageCallInSecond) {
		this.averageCallInSecond = averageCallInSecond;
	}

	public int getTotalCallInMinute() {
		return totalCallInMinute;
	}

	public void setTotalCallInMinute(int totalCallInMinute) {
		this.totalCallInMinute = totalCallInMinute;
	}

	public int getCallOut() {
		return callOut;
	}

	public void setCallOut(int callOut) {
		this.callOut = callOut;
	}

	public int getCallOutSuccess() {
		return callOutSuccess;
	}

	public void setCallOutSuccess(int callOutSuccess) {
		this.callOutSuccess = callOutSuccess;
	}

	public double getCallOutSuccessRate() {
		return callOutSuccessRate;
	}

	public void setCallOutSuccessRate(double callOutSuccessRate) {
		this.callOutSuccessRate = callOutSuccessRate;
	}

	public int getAverageCallOutSecond() {
		return averageCallOutSecond;
	}

	public void setAverageCallOutSecond(int averageCallOutSecond) {
		this.averageCallOutSecond = averageCallOutSecond;
	}

	public int getTotleCallOutMinute() {
		return totleCallOutMinute;
	}

	public void setTotleCallOutMinute(int totleCallOutMinute) {
		this.totleCallOutMinute = totleCallOutMinute;
	}

	public int getTotleLoginMinute() {
		return totleLoginMinute;
	}

	public void setTotleLoginMinute(int totleLoginMinute) {
		this.totleLoginMinute = totleLoginMinute;
	}

	public int getTotleBusyMinute() {
		return totleBusyMinute;
	}

	public void setTotleBusyMinute(int totleBusyMinute) {
		this.totleBusyMinute = totleBusyMinute;
	}

	public int getTotleFreeMinute() {
		return totleFreeMinute;
	}

	public void setTotleFreeMinute(int totleFreeMinute) {
		this.totleFreeMinute = totleFreeMinute;
	}

}
