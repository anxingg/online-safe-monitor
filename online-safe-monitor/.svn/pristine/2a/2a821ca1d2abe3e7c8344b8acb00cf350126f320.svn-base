package cn.com.qytx.platform.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.balck.action.BlacklistImportAction;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.domain.BlacklistFunction;
import cn.com.qytx.platform.domain.IvrAndACDSet;
import cn.com.qytx.platform.domain.OutcallFunction;
import cn.com.qytx.platform.domain.SeatFunction;
import cn.com.qytx.platform.service.IModuleForHotline;
import cn.com.qytx.platform.service.IPlatformParameterService;

import com.google.gson.Gson;
import com.sun.star.uno.RuntimeException;

public class PlatformSettingAction extends BaseActionSupport {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 5695446261597879068L;
	/**
     * log4j日志对象
     */
	 private final static MonitorLogger logger =new Log4jImpl(BlacklistImportAction.class);
    
	private SeatFunction seatFun;
	private IvrAndACDSet ivrSet;
	private OutcallFunction outcallFun;
	/**
	 * 黑名单设置实体
	 */
	private BlacklistFunction blacklistFun;
	@Autowired
	private transient IPlatformParameterService parmsService;
	
	@Autowired
	private IModuleForHotline moduleForHotlineService;
	
	//保存坐席端功能设置
	public String saveSeatFunction(){
		
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();

			SeatFunction seatfunction = new SeatFunction();

			seatfunction.setParDescribe("系统基础设置");
			seatfunction.setParItems("cn.com.qytx.platform.domain.SeatFunction");
			
			seatfunction.setIsThreeCall(seatFun.getIsThreeCall());
			seatfunction.setFreeSeatThreeCall(seatFun.getFreeSeatThreeCall());//
			seatfunction.setAddressBookThreeCall(seatFun.getAddressBookThreeCall());//
			seatfunction.setDefinePhoneThreeCall(seatFun.getDefinePhoneThreeCall());//
			seatfunction.setIsTransferCall(seatFun.getIsTransferCall());
			seatfunction.setTransferWay(seatFun.getTransferWay());
			seatfunction.setFreeSeatTransfer(seatFun.getFreeSeatTransfer());//
			seatfunction.setAddressBookTransfer(seatFun.getAddressBookTransfer());//
			seatfunction.setDefinePhoneTransfer(seatFun.getDefinePhoneTransfer());//
			seatfunction.setTrunQueueTransFer(seatFun.getTrunQueueTransFer());//转队列
			seatfunction.setIsOutCallManual(seatFun.getIsOutCallManual());
			seatfunction.setIsKeepOrRecall(seatFun.getIsKeepOrRecall());
			seatfunction.setIsHangUp(seatFun.getIsHangUp());
			seatfunction.setIsBusyOrIdle(seatFun.getIsBusyOrIdle());
			seatfunction.setIsMonitor(seatFun.getIsMonitor());
			seatfunction.setIsAfterCallDeal(seatFun.getIsAfterCallDeal());
			seatfunction.setDealLength(seatFun.getDealLength());
			seatfunction.setIsAddressBook(seatFun.getIsAddressBook());
			seatfunction.setIsKnowledge(seatFun.getIsKnowledge());
			seatfunction.setIsNotice(seatFun.getIsNotice());
			seatfunction.setIsNewWorkOrder(seatFun.getIsNewWorkOrder());


			
//			PlatformParameter platformParam = new PlatformParameter();
			//设置参数描述
//			platformParam.setParDescribe(seatfunction.getDesc());
			//设置参数项
//			platformParam.setParItems(seatfunction.getItem());
			//将设置好的坐席段设置保存进数据库
			this.parmsService.saveObj(seatfunction);
			//更新application中的设置
			ServletActionContext.getServletContext().setAttribute("seatFunction", seatfunction);
			

			out.print("1");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	
	//保存ivr和acd功能设置
	
	public String saveIVRSet(){
		
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();

			IvrAndACDSet ivrAndAcdSet = new IvrAndACDSet();

			ivrAndAcdSet.setParDescribe("ACD和IVR设置");
			ivrAndAcdSet.setParItems("cn.com.qytx.platform.domain.IvrAndACDSet");
			
			ivrAndAcdSet.setIsSeatRecord(ivrSet.getIsSeatRecord());
			ivrAndAcdSet.setIsCustomVoice(ivrSet.getIsCustomVoice());
			ivrAndAcdSet.setRemotePhoneZero(ivrSet.getRemotePhoneZero());
			ivrAndAcdSet.setIsLeaveMessage(ivrSet.getIsLeaveMessage());
			ivrAndAcdSet.setIsSatifyCheck(ivrSet.getIsSatifyCheck());
			ivrAndAcdSet.setIsMultiAccessNum(ivrSet.getIsMultiAccessNum());
			ivrAndAcdSet.setIsBlack(ivrSet.getIsBlack());
			
			

			

			
//			PlatformParameter platformParam = new PlatformParameter();
			//设置参数描述
//			platformParam.setParDescribe(seatfunction.getDesc());
			//设置参数项
//			platformParam.setParItems(seatfunction.getItem());
			//将ivr设置保存进数据库
			this.parmsService.saveObj(ivrAndAcdSet);
			
			
			if(ivrSet.getIsBlack()==0){
				//如果不支持黑名单功能，则逻辑删除后台的黑名单管理功能模块
				this.moduleForHotlineService.updateModuleByURL("logined/black/jsp/blacklist_list.jsp",0);
	
			}else{
				//如果支持黑名单功能，则逻辑恢复后台的黑名单管理功能模块
				this.moduleForHotlineService.updateModuleByURL("logined/black/jsp/blacklist_list.jsp",1);
			}
			
			if(ivrSet.getRemotePhoneZero()==0){
				//如果不支持 外呼异地手机时加拨0，逻辑删除
				this.moduleForHotlineService.updateModuleByURL("/logined/localsegment/localWeihu.jsp",0);
			}else{
				//如果支持 外呼异地手机时加拨0，逻辑恢复
				this.moduleForHotlineService.updateModuleByURL("/logined/localsegment/localWeihu.jsp",1);
			}
			
			
			//更新application中的设置
			ServletActionContext.getServletContext().setAttribute("ivrAndACDSet", ivrAndAcdSet);
			
			

			out.print("1");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}

	
	//保存外呼功能设置
	public String saveOutcallFunction(){
		
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();

			OutcallFunction outcallfunction = new OutcallFunction();

			outcallfunction.setParDescribe("外呼功能设置");
			outcallfunction.setParItems("cn.com.qytx.platform.domain.OutcallFunction");
			
			outcallfunction.setIsEnableOutcall(outcallFun.getIsEnableOutcall());
			outcallfunction.setCustomScript(outcallFun.getCustomScript());
			outcallfunction.setCustomData(outcallFun.getCustomData());
			outcallfunction.setDefinePhone(outcallFun.getDefinePhone());
			outcallfunction.setForecastPhone(outcallFun.getForecastPhone());
			
			this.parmsService.saveObj(outcallfunction);
			//更新application中的设置
			ServletActionContext.getServletContext().setAttribute("outcallFunction", outcallfunction);
			

			out.print("1");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	/**
	 * 功能：保存黑名单设置
	 * @return
	 */
	public String saveBlacklistSet(){
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			//创建黑名单设置对象
			BlacklistFunction blacklistFuntion = new BlacklistFunction();
			//参数描述
			blacklistFuntion.setParDescribe("黑名单设置");
			//参数项
			blacklistFuntion.setParItems("cn.com.qytx.platform.domain.BlacklistFunction");
			//是否启用 1：不启用 2：启用
			blacklistFuntion.setIsEnableBlacklist(blacklistFun.getIsEnableBlacklist());
			//保存设置
			this.parmsService.saveObj(blacklistFuntion);
			//更新application中的设置
			ServletActionContext.getServletContext().setAttribute("blacklistFunction", blacklistFuntion);
			out.print("1");
		} catch (Exception e) {
			logger.error("saveBlacklistSet error", e);
		}
		return null;
	}

//坐席端功能初始化
	public String initSeatFunction(){
		try {
			SeatFunction obj = (SeatFunction)ServletActionContext.getServletContext().getAttribute("seatFunction");
			
//			System.err.println(obj);
			Map<String,Object> map = new HashMap<String,Object>();
			if(obj!=null){
				
				map.put("isThreeCall", obj.getIsThreeCall());
				map.put("freeSeatThreeCall", obj.getFreeSeatThreeCall());
				map.put("addressBookThreeCall", obj.getAddressBookThreeCall());
				map.put("definePhoneThreeCall", obj.getDefinePhoneThreeCall());
				map.put("isTransferCall", obj.getIsTransferCall());
				map.put("transferWay", obj.getTransferWay());
				map.put("freeSeatTransfer", obj.getFreeSeatTransfer());
				map.put("addressBookTransfer", obj.getAddressBookTransfer());
				map.put("definePhoneTransfer", obj.getDefinePhoneTransfer());
				map.put("trunQueueTransFer", obj.getTrunQueueTransFer());//转队列
				map.put("isOutCallManual", obj.getIsOutCallManual());
				map.put("isKeepOrRecall", obj.getIsKeepOrRecall());
				map.put("isHangUp", obj.getIsHangUp());
				map.put("isBusyOrIdle", obj.getIsBusyOrIdle());
				map.put("isMonitor", obj.getIsMonitor());
				map.put("isAfterCallDeal", obj.getIsAfterCallDeal());
				map.put("dealLength", obj.getDealLength());
				map.put("isAddressBook", obj.getIsAddressBook());
				map.put("isKnowledge", obj.getIsKnowledge());
				map.put("isNotice", obj.getIsNotice());
				map.put("isNewWorkOrder", obj.getIsNewWorkOrder());
			}
			
			Gson json = new Gson();
            String jsons = json.toJson(map);
            PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
            writer.print(jsons);
            writer.flush();
            writer.close();
			
			
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		return null;
	}
	
	//ivr acd 初始化
	public String initIVRSet(){
		try {
			IvrAndACDSet obj = (IvrAndACDSet)ServletActionContext.getServletContext().getAttribute("ivrAndACDSet");
//			System.err.println(obj);
			Map<String,Object> map = new HashMap<String,Object>();
			if(obj!=null){
				map.put("isSeatRecord",obj.getIsSeatRecord() );
				map.put("isCustomVoice", obj.getIsCustomVoice());
				map.put("remotePhoneZero", obj.getRemotePhoneZero());
				map.put("isLeaveMessage", obj.getIsLeaveMessage());
				map.put("isSatifyCheck", obj.getIsSatifyCheck());
				map.put("isMultiAccessNum", obj.getIsMultiAccessNum());
				map.put("isBlack", obj.getIsBlack());
			}
			
			
			Gson json = new Gson();
            String jsons = json.toJson(map);
            PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
            writer.print(jsons);
            writer.flush();
            writer.close();
			
			
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		return null;
	}
	
	//外呼功能初始化设置
	public String initOutcallFunction(){
		try {
			OutcallFunction obj = (OutcallFunction)ServletActionContext.getServletContext().getAttribute("outcallFunction");
			Map<String,Object> map = new HashMap<String,Object>();
			if(obj!=null){
				map.put("isEnableOutcall", obj.getIsEnableOutcall());
				map.put("customScript", obj.getCustomScript());
				map.put("customData", obj.getCustomData());
				map.put("definePhone", obj.getDefinePhone());
				map.put("forecastPhone", obj.getForecastPhone());
			}
			
			Gson json = new Gson();
            String jsons = json.toJson(map);
            PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
            writer.print(jsons);
            writer.flush();
            writer.close();
			
			
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		return null;
	}
	/**
	 * 功能：初始化黑名单设置
	 * @return
	 */
	public String initBlacklistFun(){
		try {
			BlacklistFunction obj = (BlacklistFunction) ServletActionContext.getServletContext().getAttribute("blacklistFunction");
			Map<String,Object> map = new HashMap<String,Object>();
			if(obj!=null){
				map.put("isEnableBlacklist", obj.getIsEnableBlacklist());
			}
			Gson json = new Gson();
	        String jsons = json.toJson(map);
	        PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
	        writer.print(jsons);
	        writer.flush();
	        writer.close();
		} catch (Exception e) {
			logger.error("initBlacklistFun error",e);
		}
		return null;
	}
	

	public SeatFunction getSeatFun() {
		return seatFun;
	}

	public void setSeatFun(SeatFunction seatFun) {
		this.seatFun = seatFun;
	}

	public IvrAndACDSet getIvrSet() {
		return ivrSet;
	}

	public void setIvrSet(IvrAndACDSet ivrSet) {
		this.ivrSet = ivrSet;
	}

	public OutcallFunction getOutcallFun() {
		return outcallFun;
	}

	public void setOutcallFun(OutcallFunction outcallFun) {
		this.outcallFun = outcallFun;
	}

	public BlacklistFunction getBlacklistFun() {
		return blacklistFun;
	}

	public void setBlacklistFun(BlacklistFunction blacklistFun) {
		this.blacklistFun = blacklistFun;
	}
	
}
