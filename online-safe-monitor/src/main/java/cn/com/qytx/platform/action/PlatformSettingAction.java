package cn.com.qytx.platform.action;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.domain.BlacklistFunction;
import cn.com.qytx.platform.service.IPlatformParameterService;
import com.google.gson.Gson;


public class PlatformSettingAction extends BaseActionSupport {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 5695446261597879068L;
	/**
     * log4j日志对象
     */
	private final static MonitorLogger logger =new Log4jImpl(PlatformSettingAction.class);
    
	/**
	 * 黑名单设置实体
	 */
	private BlacklistFunction blacklistFun;
	@Autowired
	private transient IPlatformParameterService parmsService;
	

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
	
	public BlacklistFunction getBlacklistFun() {
		return blacklistFun;
	}

	public void setBlacklistFun(BlacklistFunction blacklistFun) {
		this.blacklistFun = blacklistFun;
	}
	
}
