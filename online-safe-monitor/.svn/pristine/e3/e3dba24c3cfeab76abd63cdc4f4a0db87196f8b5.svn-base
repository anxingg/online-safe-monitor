package cn.com.qytx.hotline.ivr.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.ivr.domain.Msiservice;
import cn.com.qytx.hotline.ivr.service.IMsiservice;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能: 坐席队列查询具体的队列信息
 * 版本: 1.0
 * 开发人员: 
 * 创建日期: 
 * 修改人员：李立泼
 * 修改日期: 2015-03-03
 * 修改列表:
 */
public class SeatQueueFindAction extends BaseActionSupport {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * log4j日志对象
     */
	 private final static MonitorLogger logger =new Log4jImpl(SeatQueueFindAction.class);
    
	// 输入
    
	/**
	 * 坐席队列接口
	 */
	@Autowired
	private transient IMsiservice msiservice;
	
	/**
	 * 坐席队列vid
	 */
	private Integer vid;
	
	// 输出
	
	/**
	 * 坐席队列对象
	 */
	private Msiservice msObj;
	
	/**
	 * 坐席队列中人员的姓名集合
	 */
	private String userNameList;
	
	/**
	 * 坐席队列中人员的ID集合
	 */
	private String userIdList;

	/**
	 * 
	 * @return
	 */
	public String findById() {
		UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
		try {
			if (userInfo != null) {
				msObj = msiservice.findById(vid);
				List<UserInfo> userInfoList = msObj.getUserInfoList();
				StringBuffer sbfUserName = new StringBuffer();
				StringBuffer sbfUserId = new StringBuffer();
				if (userInfoList != null && userInfoList.size() > 0) {
					sbfUserId.append(",");
					for (UserInfo u : userInfoList) {
						sbfUserName.append(u.getUserName());
						sbfUserName.append(",");
						sbfUserId.append(u.getUserId());
						sbfUserId.append(",");
					}
					sbfUserName.subSequence(0, sbfUserName.length() - 1);
					if (",".equals(sbfUserId.toString())) {
						sbfUserId.substring(0, sbfUserId.length() - 1);
					}
				}
				userNameList = sbfUserName.toString();
				userIdList = sbfUserId.toString();
			}
		} catch (Exception e) {
			logger.error("坐席队列模块  findById方法 error. 当前操作人：" + userInfo.getUserName() + "( ID: " + userInfo.getUserId() +" ) ",e);
		}
		return "success";
	}
	
	/**
	 * 功能：根据队列id查询队列说明--张东领添加
	 * @return
	 */
	public String findNameById(){
		String queueName="";
		try {
			if(vid!=null){
				msObj = msiservice.findById(vid);
				if(msObj!=null&&msObj.getServiceName()!=null){
					queueName = msObj.getServiceName();
				}
			}
			ajax(queueName);
		} catch (Exception e) {
			logger.error("坐席队列模块  findNameById方法 error. ",e);
		}
		return null;
	}

	/* getter setter */
	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Msiservice getMsObj() {
		return msObj;
	}

	public void setMsObj(Msiservice msObj) {
		this.msObj = msObj;
	}

	public String getUserNameList() {
		return userNameList;
	}

	public void setUserNameList(String userNameList) {
		this.userNameList = userNameList;
	}

	public String getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(String userIdList) {
		this.userIdList = userIdList;
	}

}
