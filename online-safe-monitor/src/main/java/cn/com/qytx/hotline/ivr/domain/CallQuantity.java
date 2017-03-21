package cn.com.qytx.hotline.ivr.domain;

import java.sql.Timestamp;

import cn.com.qytx.platform.utils.datetime.DateTimeUtil;

/**
 * 功能:话务量综合统计表bean 版本: 1.0 开发人员: 李华伟 创建日期: 2014-1-15 修改日期: 2014-1-15 修改列表:
 */
public class CallQuantity implements Comparable<CallQuantity> {
	/**
	 * 日期
	 */
	private String callDate;

	/**
	 * 呼入量
	 */
	private int callInNum;

	/**
	 * 呼出量
	 */
	private int callOutNum;

	/**
	 * 总话务量
	 */
	private int totleCallNum;
	/**
	 * 呼损总时长
	 */
	private int totleCallLoss;
	/**
	 * 呼入通话总时长
	 */
	private int totleCallInTime;
	/**
	 * 呼出通话总时长
	 */
	private int totleCallOutTime;
	/**
	 * 登陆总时长
	 */
	private int totleLoginTime;
	/**
	 * 置忙总时长
	 */
	private int totleBosyTime;
	/**
	 * 置闲总时长
	 */
	private int totleNoBosyTime;
	
	public String getCallDate() {
		return callDate;
	}

	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}

	public int getCallInNum() {
		return callInNum;
	}

	public void setCallInNum(int callInNum) {
		this.callInNum = callInNum;
	}

	public int getCallOutNum() {
		return callOutNum;
	}

	public void setCallOutNum(int callOutNum) {
		this.callOutNum = callOutNum;
	}

	public int getTotleCallNum() {
		return totleCallNum;
	}

	public void setTotleCallNum(int totleCallNum) {
		this.totleCallNum = totleCallNum;
	}

	@Override
	public int compareTo(CallQuantity o) {
		Timestamp firstcallDate = DateTimeUtil.stringToTimestamp(this.callDate,
				CallCenterConst.DATE_FORMAT_STR);
		Timestamp secondcallDate = DateTimeUtil.stringToTimestamp(
				o.getCallDate(), CallCenterConst.DATE_FORMAT_STR);
		if (firstcallDate.before(secondcallDate)) {
			return -1;
		} else if (firstcallDate.after(secondcallDate)) {
			return 1;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((callDate == null) ? 0 : callDate.hashCode());
		result = prime * result + callInNum;
		result = prime * result + callOutNum;
		result = prime * result + totleCallNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		CallQuantity other = (CallQuantity) obj;
		if (callDate == null) {
			if (other.callDate != null){
				return false;
			}
		} else if (!callDate.equals(other.callDate)){
			return false;
		}
		if (callInNum != other.callInNum){
			return false;
		}
		if (callOutNum != other.callOutNum){
			return false;
		}
		if (totleCallNum != other.totleCallNum){
			return false;
		}
		return true;
	}

	public int getTotleCallLoss() {
		return totleCallLoss;
	}

	public void setTotleCallLoss(int totleCallLoss) {
		this.totleCallLoss = totleCallLoss;
	}

	public int getTotleCallInTime() {
		return totleCallInTime;
	}

	public void setTotleCallInTime(int totleCallInTime) {
		this.totleCallInTime = totleCallInTime;
	}

	public int getTotleCallOutTime() {
		return totleCallOutTime;
	}

	public void setTotleCallOutTime(int totleCallOutTime) {
		this.totleCallOutTime = totleCallOutTime;
	}

	public int getTotleLoginTime() {
		return totleLoginTime;
	}

	public void setTotleLoginTime(int totleLoginTime) {
		this.totleLoginTime = totleLoginTime;
	}

	public int getTotleBosyTime() {
		return totleBosyTime;
	}

	public void setTotleBosyTime(int totleBosyTime) {
		this.totleBosyTime = totleBosyTime;
	}

	public int getTotleNoBosyTime() {
		return totleNoBosyTime;
	}

	public void setTotleNoBosyTime(int totleNoBosyTime) {
		this.totleNoBosyTime = totleNoBosyTime;
	}

	
}
