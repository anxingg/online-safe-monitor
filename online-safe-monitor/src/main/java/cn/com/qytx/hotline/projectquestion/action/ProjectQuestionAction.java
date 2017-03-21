package cn.com.qytx.hotline.projectquestion.action;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.projectquestion.domain.ProjectQuestion;
import cn.com.qytx.hotline.projectquestion.domain.SMSMTRecord;
import cn.com.qytx.hotline.projectquestion.service.IProjectQuestion;
import cn.com.qytx.hotline.projectquestion.service.ISMSMTRecord;
import cn.com.qytx.hotline.util.PropertiesUtil;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能:问题上报action
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-20
 * 修改日期: 2014-12-20
 * 修改列表:
 */
public class ProjectQuestionAction extends BaseActionSupport {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -1743893833031399469L;
	/**
	 * 问题上报接口
	 */
	@Autowired
	IProjectQuestion projectqService;
	/**
	 * 发送短信接口
	 */
	@Autowired
	ISMSMTRecord smsmService;

	/**
	 * 问题描述
	 */
	private String contentInfo;

	/**
	 * 功能：问题上报提交
	 * @return
	 */
	public Integer submitQuestion(){
		try {
			UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
			if(userInfo!=null){
				ProjectQuestion pq = new ProjectQuestion();
				if(StringUtils.isNotEmpty(contentInfo)){
					contentInfo = contentInfo+"/r/n"
							+"上报人："+getLoginUser().getUserName()+"/r/n"
							+"上报人电话："+getLoginUser().getPhone();
					pq.setContentInfo(contentInfo);
				}
				pq.setCreateUserId(getLoginUser().getUserId());
				pq.setCreateTime(new Timestamp(System.currentTimeMillis()));
				pq.setIsForkGroup(getLoginUser().getIsForkGroup());
				projectqService.saveOrUpdate(pq);
				//发送短信到电话
				SMSMTRecord sr = new SMSMTRecord();
				String sendPhone = PropertiesUtil.getPropertiesValue("sendMsgPhone.properties", "phone");
				if(StringUtils.isNotBlank(sendPhone)){
					sr.setPhone(sendPhone);
				}
				sr.setContent(contentInfo);
				sr.setInsertTime(new Timestamp(System.currentTimeMillis()));
				sr.setSendTime(new Timestamp(System.currentTimeMillis()));
				sr.setSendState(0);
				sr.setRegisteredDelivery(1);
				sr.setSrcId("106573029168");
				sr.setSystemName("省12333");
				sr.setModuleName("问题上报");
				sr.setSystemId("25");
				sr.setMsgFmt(1);
				smsmService.saveOrUpdate(sr);
				ajax(0);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public String getContentInfo() {
		return contentInfo;
	}

	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
	}
}
