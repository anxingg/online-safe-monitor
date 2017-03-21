package cn.com.qytx.hotline.sms.action;

import java.util.Properties;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 
 * 功能:短信通重定向到
 *    提供的接口,调用短信模块
 * 版本: 1.0
 * 开发人员: 马恺
 * 创建日期: 2014-12-15
 * 修改日期: 2014-12-15
 * 修改列表:
 */
public class ForwardSmsAction extends BaseActionSupport{
	private static final long serialVersionUID = 1L;
	
	public String forwardSms(){
		UserInfo adminUser = null;
		Object admin = this.getSession().getAttribute("adminUser");
		if(admin != null){
			Integer isForkGroup = getLoginUser().getIsForkGroup();
			adminUser = (UserInfo)admin;
			String phone = adminUser.getPhone();
			String userId = adminUser.getUserId().toString();
			
			String url = "";
			Properties prop = new Properties();
			try {
				//读取forwardSms.properties配置文件中的内容
//				prop.load(ForwardSmsAction.class.getClassLoader().getResourceAsStream("forwardSms.properties"));
				prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("forwardSms.properties"));
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
			//读取forwardSms.properties中key值，获得url
			url = prop.getProperty("forwardUrl");
			Integer toWhere = Integer.parseInt(this.getRequest().getParameter("toWhere"));
			switch(toWhere){
				case 1: url = url + "/sms/to.action?toWhere=1&companyId=1";break;
				case 2: url = url + "/sms/to.action?toWhere=2&companyId=1";break;
				case 3: url = url + "/sms/to.action?toWhere=3&companyId=1";break;
				case 4: url = url + "/sms/to.action?toWhere=4&companyId=1";break;
				case 5: url = url + "/sms/to.action?toWhere=5&companyId=1";break;
				case 6: url = url + "/sms/to.action?toWhere=6&companyId=1";break;
			}
			url = url + "&masterPhone=" + phone + "&userId=" + userId+"&isForkGroup="+isForkGroup;
			this.getSession().setAttribute("forwardUrl", url);
			return "forwardUrl";
		}
		
		return null;
	}
}
