package cn.com.qytx.cbb.org.action.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.qytx.cbb.org.service.MobileUserService;
import cn.com.qytx.platform.org.service.IUser;

/**
 * 部门管理手机接口
 * 
 * @author jiayongqiang
 * 
 */
@RequestMapping("/org/mobile/user")
@Controller
public class UserManagerAction extends BaseOrgController {

	protected final static Logger logger = LoggerFactory
			.getLogger(UserManagerAction.class);

	@Autowired
	private MobileUserService mobileUserService;


	@Autowired
	IUser userService;

	/**
	 * 添加人员 org/mobile/user/add.action
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/add.c")
	public String add(
			@RequestParam(value = "companyId", required = true) Integer companyId,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "userJson", required = true) String userJson)
			throws Exception {
		logger.info("接收到请求,method=addUsersInfo,companyId=" + companyId
				+ ",userId=" + userId + ",userName=" + userName + ",userJson="
				+ userJson);
		Integer result = mobileUserService.addUsersInfo(companyId, userId,
				userName, userJson);
		if (result != null && result.intValue() != 0) {
			sendPush(companyId);
		}
		return SUCCESS(result);
	}

	/**
	 * 修改人员 /org/mobile/user/update.action
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/update.c")
	public String update(
			@RequestParam(value = "companyId", required = true) Integer companyId,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "userJson", required = true) String userJson)
			throws Exception {
		logger.info("接收到请求,method=updateUsersInfo,companyId=" + companyId
				+ ",userId=" + userId + ",userName=" + userName + ",userJson="
				+ userJson);
		Integer result = mobileUserService.updateUsersInfo(companyId, userId,
				userName, userJson);
		if (result != null && result.intValue() != 0) {
			sendPush(companyId);
		}
		return SUCCESS(result);
	}

	/**
	 * 删除人员 /org/mobile/user/delete.action
	 * 
	 * @throws Exception
	 */

	@ResponseBody
	@RequestMapping("delete.c")
	public String delete(
			@RequestParam(value = "companyId", required = true) Integer companyId,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "userIds", required = true) String userIds,
			@RequestParam(value = "userName", required = true) String userName)
			throws Exception {

		logger.info("接收到请求,method=deleteUsersInfo,companyId=" + companyId
				+ ",userId=" + userId + ",userName=" + userName + ",userIds="
				+ userIds);
		userService.deleteUserByIds(userIds, false, companyId);
		sendPush(companyId);
		return SUCCESS("删除成功");
	}

	/**
	 * 人员排序 /org/mobile/user/order.action
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/order.c")
	public String order(
			@RequestParam(value = "companyId", required = true) Integer companyId,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "sortList", required = true) String sortList)
			throws Exception {

		logger.info("接收到请求,method=userOrder,companyId=" + companyId
				+ ",userId=" + userId + ",userName=" + userName + ",sortList="
				+ sortList);
		mobileUserService.userOrder(companyId, userId, userName, sortList);
		return SUCCESS("人员排序成功");
	}


}
