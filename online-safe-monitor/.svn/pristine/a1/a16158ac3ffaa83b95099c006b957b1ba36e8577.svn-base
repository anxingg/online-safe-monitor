package cn.com.qytx.hotline.customercall.action;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.projectquestion.domain.SMSMTRecord;
import cn.com.qytx.hotline.projectquestion.service.ISMSMTRecord;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能:发送短信
 */
public class SendMessageAction extends BaseActionSupport {
	private static final long serialVersionUID = -2938173103555916487L;
	/**
	 * 发送短信接口
	 */
	@Autowired
	transient ISMSMTRecord smsmService;
	/**
	 * 发送到电话
	 */
	private String sendPhone;
	/**
	 * 发送内容
	 */
	private String contentInfo;
	/**
	 * 模块名称
	 */
	private String moduleName;
	/**
	 * 功能：发送短信到手机
	 */
	public Integer sendMessage(){
		try {
			UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
			if(userInfo!=null){
				//发送短信到电话
				SMSMTRecord sr = new SMSMTRecord();
				if(StringUtils.isNotBlank(sendPhone)){
					sr.setPhone(sendPhone);
				}
				if(StringUtils.isNotBlank(contentInfo)){
					sr.setContent(contentInfo);
				}
				sr.setInsertTime(new Timestamp(System.currentTimeMillis()));
				sr.setSendTime(new Timestamp(System.currentTimeMillis()));
				sr.setSendState(0);
				sr.setRegisteredDelivery(1);
				sr.setSrcId("106573029168");
				sr.setSystemName("呼叫中心");
				sr.setModuleName(moduleName);
				sr.setSystemId("25");
				sr.setMsgFmt(1);
				smsmService.saveOrUpdate(sr);
				ajax(0);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	public String getSendPhone() {
		return sendPhone;
	}
	public void setSendPhone(String sendPhone) {
		this.sendPhone = sendPhone;
	}
	public String getContentInfo() {
		return contentInfo;
	}
	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
