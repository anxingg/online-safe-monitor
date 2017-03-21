package cn.com.qytx.hotline.ivr.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
/**
 * 功能:坐席监控实体类
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2015-2-15
 * 修改日期: 2015-2-15
 * 修改列表:
 */
public class SeatMonitor {
	/**
	 * 正在外呼 数量
	 */
	@Expose
	private int outCalling = 0;

	/**
	 * 正在接听 数量
	 */
	@Expose
	private int inCalling = 0;

	/**
	 * 空闲 数量
	 */
	@Expose
	private int free = 0;

	/**
	 * 置忙 数量
	 */
	@Expose
	private int busy = 0;

	/**
	 * 签入 数量
	 */
	@Expose
	private int onLine = 0;

	/**
	 * 当天系统总排队次数
	 */
	@Expose
	private int todayAllInQueue = 0;

	/**
	 * 当天系统总呼损次数
	 */
	@Expose
	private int todayAllCallLose = 0;

	/**
	 * 当天呼入电话数
	 */
	@Expose
	private int totleCallInNum = 0;

	/**
	 * 当天呼入时长
	 */
	@Expose
	private int totleCallInSecond = 0;

	/**
	 * 当天呼入时长 分钟数
	 */
	@Expose
	private int totleCallInMinute = 0;

	/**
	 * 当天外呼电话数
	 */
	@Expose
	private int totleCallOutNum = 0;

	/**
	 * 当天外呼时长
	 */
	@Expose
	private int totleCallOutSecond = 0;

	/**
	 * 当天外呼时长 分钟数
	 */
	@Expose
	private int totleCallOutMinute = 0;

	/**
	 * 当天系统总呼损次数
	 */
	@Expose
	private int totleCallLose = 0;

	/**
	 * 内线坐席列表
	 */
	@Expose
	private List<Msiuser> insideUserList = new ArrayList<Msiuser>();

	/**
	 * 外线坐席列表
	 */
	@Expose
	private List<Msiuser> outsideUserList = new ArrayList<Msiuser>();

	public int getOutCalling() {
		return outCalling;
	}

	public void setOutCalling(int outCalling) {
		this.outCalling = outCalling;
	}

	public int getInCalling() {
		return inCalling;
	}

	public void setInCalling(int inCalling) {
		this.inCalling = inCalling;
	}

	public int getFree() {
		return free;
	}

	public void setFree(int free) {
		this.free = free;
	}

	public int getBusy() {
		return busy;
	}

	public void setBusy(int busy) {
		this.busy = busy;
	}

	public int getOnLine() {
		return onLine;
	}

	public void setOnLine(int onLine) {
		this.onLine = onLine;
	}

	public int getTodayAllInQueue() {
		return todayAllInQueue;
	}

	public void setTodayAllInQueue(int todayAllInQueue) {
		this.todayAllInQueue = todayAllInQueue;
	}

	public int getTodayAllCallLose() {
		return todayAllCallLose;
	}

	public void setTodayAllCallLose(int todayAllCallLose) {
		this.todayAllCallLose = todayAllCallLose;
	}

	public int getTotleCallInNum() {
		return totleCallInNum;
	}

	public void setTotleCallInNum(int totleCallInNum) {
		this.totleCallInNum = totleCallInNum;
	}

	public int getTotleCallInSecond() {
		return totleCallInSecond;
	}

	public void setTotleCallInSecond(int totleCallInSecond) {
		if (0 != totleCallInSecond) {
			this.setTotleCallInMinute((int) (Math
					.floor(totleCallInSecond / 60d)));
		}
		this.totleCallInSecond = totleCallInSecond;
	}

	public int getTotleCallInMinute() {
		return totleCallInMinute;
	}

	public void setTotleCallInMinute(int totleCallInMinute) {
		this.totleCallInMinute = totleCallInMinute;
	}

	public int getTotleCallOutNum() {
		return totleCallOutNum;
	}

	public void setTotleCallOutNum(int totleCallOutNum) {
		this.totleCallOutNum = totleCallOutNum;
	}

	public int getTotleCallOutSecond() {
		return totleCallOutSecond;
	}

	public void setTotleCallOutSecond(int totleCallOutSecond) {
		if (0 != totleCallOutSecond) {
			this.setTotleCallOutMinute((int) (Math
					.floor(totleCallOutSecond / 60d)));
		}
		this.totleCallOutSecond = totleCallOutSecond;
	}

	public int getTotleCallOutMinute() {
		return totleCallOutMinute;
	}

	public void setTotleCallOutMinute(int totleCallOutMinute) {
		this.totleCallOutMinute = totleCallOutMinute;
	}

	public int getTotleCallLose() {
		return totleCallLose;
	}

	public void setTotleCallLose(int totleCallLose) {
		this.totleCallLose = totleCallLose;
	}

	public List<Msiuser> getInsideUserList() {
		return insideUserList;
	}

	public void setInsideUserList(List<Msiuser> insideUserList) {
		this.insideUserList = insideUserList;
	}

	public List<Msiuser> getOutsideUserList() {
		return outsideUserList;
	}

	public void setOutsideUserList(List<Msiuser> outsideUserList) {
		this.outsideUserList = outsideUserList;
	}
	
	/**
	 * 功能：将对象转为Map
	 * @return
	 */
	public Map<String, Object> seatMonitorToMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		//正在外呼 数量
		map.put("outCalling", outCalling);
		//正在接听 数量
		map.put("inCalling", inCalling);
		//空闲 数量
		map.put("free", free);
		//置忙 数量
		map.put("busy", busy);
		//签入 数量
		map.put("onLine", onLine);
		//当天系统总排队次数
		map.put("todayAllInQueue", todayAllInQueue);
		//当天系统总呼损次数
		map.put("todayAllCallLose", todayAllCallLose);
		//当天呼入电话数
		map.put("totleCallInNum", totleCallInNum);
		//当天呼入时长
		map.put("totleCallInSecond", totleCallInSecond);
		//当天呼入时长 分钟数
		map.put("totleCallInMinute", totleCallInMinute);
		//当天外呼电话数
		map.put("totleCallOutNum", totleCallOutNum);
		//当天外呼时长
		map.put("totleCallOutSecond", totleCallOutSecond);
		//当天外呼时长 分钟数
		map.put("totleCallOutMinute", totleCallOutMinute);
		//当天系统总呼损次数
		map.put("totleCallLose", totleCallLose);
		return map;
	}
	
	
}
