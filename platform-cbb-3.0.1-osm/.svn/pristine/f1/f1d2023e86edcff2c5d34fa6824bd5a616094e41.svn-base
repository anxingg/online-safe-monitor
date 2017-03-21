package cn.com.qytx.cbb.org.action.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.qytx.cbb.org.service.MobileSetService;

/**
 * 群组管理手机接口
 * 
 * @author jiayongqiang
 * 
 */
@RequestMapping("/org/mobile/set")
@Controller
public class SetManagerAction extends BaseOrgController {
	protected final static Logger logger = LoggerFactory
			.getLogger(SetManagerAction.class);

	@Autowired
	MobileSetService mobileSetService;
	
	/**
	 * 功能：新增群组人员
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addGroupUser.c")
	public String addGroupUser(
			@RequestParam(value = "companyId", required = true) Integer companyId,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "groupId", required = true) Integer groupId,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "userIds", required = true) String userIds)
			throws Exception {

		logger.info("接收到请求,method=addGroupUsers,companyId=" + companyId
				+ ",userId=" + userId + ",userName=" + userName + ",groupId="
				+ groupId + ",userIds=" + userIds);
		String result = mobileSetService.addSetUsers(companyId, userId,
				userName, groupId, userIds);
		if (result != null) {
			sendPush(companyId);
		}
		return SUCCESS(result);
	}

	/**
	 * 功能：删除群组人员
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteGroupUser.c")
	public String deleteGroupUser(
			@RequestParam(value = "companyId", required = true) Integer companyId,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "groupId", required = true) Integer groupId,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "userIds", required = true) String userIds)
			throws Exception {
		logger.info("接收到请求,method=deleteGroupUsers,companyId=" + companyId
				+ ",userId=" + userId + ",userName=" + userName + ",groupId="
				+ groupId + ",userIds=" + userIds);
		String result = mobileSetService.deleteSetUsers(companyId, userId,
				userName, groupId, userIds);
		if (result != null) {
			sendPush(companyId);
		}
		return SUCCESS(result);
	}
}
