package cn.com.qytx.cbb.affairsRemind.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.affairs.domain.Affairs;
import cn.com.qytx.cbb.affairs.service.IAffairs;
import cn.com.qytx.cbb.affairs.service.IPushMobile;
import cn.com.qytx.cbb.affairsRemind.domain.AffairsRemind;
import cn.com.qytx.cbb.affairsRemind.service.IAffairsRemind;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.application.SpringContextHolder;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IUser;

public class AffairsRemindExecut extends BaseActionSupport {

	@Autowired
	IAffairsRemind affairsRemindService;
	
	@Autowired
	IUser userService;
	
	@Autowired
	IAffairs affairsService;
	
	/**
	 * 功能：08:45 定时器
	 */
	public void execute0845(){
		System.out.println("08:45 定时器已经启动");
		AffairsRemind affairsRemind = affairsRemindService.getRemind();
		if(affairsRemind!=null && affairsRemind.getStatus().equals(1))
		run();
	}
	
	/**
	 * 功能：11:30 定时器
	 */
	public void execute1130(){
		System.out.println("11:30 定时器已经启动");
		AffairsRemind affairsRemind = affairsRemindService.getRemind();
		if(affairsRemind!=null && affairsRemind.getStatus().equals(1) && affairsRemind.getTimes().equals(4)){
			run();
		}
	}
	
	/**
	 * 功能：14:45 定时器
	 */
	public void execute1445(){
		System.out.println("14:45 定时器已经启动");
		AffairsRemind affairsRemind = affairsRemindService.getRemind();
		if(affairsRemind!=null && affairsRemind.getStatus().equals(1) && (affairsRemind.getTimes().equals(4)||affairsRemind.getTimes().equals(2))){
			run();
		}
	}
	
	/**
	 * 功能： 17:30 定时器
	 */
	public void execute1730(){
		System.out.println("17:30 定时器已经启动");
		AffairsRemind affairsRemind = affairsRemindService.getRemind();
		if(affairsRemind!=null && affairsRemind.getStatus().equals(1) && affairsRemind.getTimes().equals(4)){
			run();
		}
	}
	
	/**
	 * 功能：推送主方法
	 */
	private void run(){
		List<UserInfo> list = userService.findAll();
		if(list!=null && list.size()>0){
			for(UserInfo user:list){
				if(user.getIsDelete()==0){
					// 查询所有未确认的事务提醒信息
			        List<Affairs> listAffairs = affairsService.findUnReadByUserId(user.getUserId());
			        if (null != listAffairs && !listAffairs.isEmpty())
			        {
			        	sendPush(user, "您有"+listAffairs.size()+"条待办事项", "", "事务", user.getUserId().toString(), "", "", "", "");
			        }
				}
			}
		}
	}
	
	
	/**
	 * 手机推送
	 * @param fromUserInfo 发送人
	 * @param pushSubject  推送标题
	 * @param pushContent 推送内容
	 * @param moduleName 模块名称
	 * @param toids  被推送人ids集合
	 * @param recordId 记录ID
	 * @param pushUrl 推送链接
	 * @throws IOException 
	 * @throws HttpException 
	 */
	private int  sendPush(UserInfo fromUserInfo ,String pushSubject,String pushContent, String moduleName,
			String toids,String recordId,String pushUrl,String pushType,String attmentIds){
		//发送推送
		return SpringContextHolder.getBean(IPushMobile.class).sendPush(fromUserInfo.getUserId().toString(), pushSubject, pushContent, 
				moduleName, toids, recordId, pushUrl,pushType,attmentIds);
//		return 0;
	}
}
