package cn.com.qytx.hotline.org.service;

import java.util.List;

import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.tree.TreeNode;

/**
 * 用户操作接口类 User: 黄普友 Date: 13-2-16 Time: 下午2:48
 */
public interface IHotlineUser extends BaseService<UserInfo> {

	/**
	 * 过滤没有权限的List，删除掉没有权限的
	 * 
	 * @param treeNodes
	 *            树接点bean
	 * @param userId
	 *            当前登录用户Id
	 * @param companyId
	 *            公司Id
	 * @param roleCode
	 *            角色代码 多个用，分割
	 * @return 清理过的List
	 */
	List<TreeNode> removeNoRole(List<TreeNode> treeNodes,
			Integer userId, Integer companyId, String roleCode);
	/**
	 * 功能：更具电话查询用户信息
	 * 开发人员:张东领
	 * 创建日期:2015年4月9日
	 * 修改人员:
	 * 修改日期:
	 * @param phone
	 * @return
	 */
	UserInfo findByPhone(String phone);
}
