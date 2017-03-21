package cn.com.qytx.hotline.org.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.org.dao.HotlineUserDao;
import cn.com.qytx.hotline.org.service.IHotlineUser;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.tree.TreeNode;

/**
 * 用户管理实现类
 * User: 黄普友
 * Date: 13-2-16
 * Time: 下午3:00
 */
@Transactional
@Service("hotlineuserService")
public class HotlineUserImpl   extends BaseServiceImpl<UserInfo> implements IHotlineUser {
    @SuppressWarnings("unused")
    private final static MonitorLogger logger =new Log4jImpl(HotlineUserImpl.class);
	@SuppressWarnings("rawtypes")
	@Resource(name="hotlineuserDao")
    private HotlineUserDao hotlineuserDao;

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
	public List<TreeNode> removeNoRole(List<TreeNode> treeNodes, Integer userId,
			Integer companyId, String roleCode) {
	 // 根据权限过滤
    String userIds = this.findHasRoleUserIds(companyId, roleCode);
  
    for (int i = 0; i < treeNodes.size(); i++)
    {
        String treeNodeId = treeNodes.get(i).getId();
        if (treeNodeId.contains("uid_"))
        {
            treeNodeId = treeNodeId.substring(4);
            //如果没有符合条件的用户  直接返回null
            if (userIds == null)
            {
            	 treeNodes.remove(i--);
            }else if (!userIds.contains("," +treeNodeId + ","))
            {
                treeNodes.remove(i--);
            }
        }
    }
    return treeNodes;
	}

	/**
	 * 功能：根据公司id  角色编码  查询用户id
	 * @param userId
	 * @param companyId
	 * @param roleName
	 * @return
	 */
	private String findHasRoleUserIds( Integer companyId,
			String roleCode) {
		@SuppressWarnings("unchecked")
		List<UserInfo> users=hotlineuserDao.findUsersByRoleCodeAndGroup(roleCode, companyId);
	        if (StringUtils.isNotEmpty(roleCode)&&users.size()>0)
	        {
	            
	             StringBuilder userIdsSB = new StringBuilder(",");
	             for (UserInfo userInfo : users)
	             {
	                 userIdsSB.append(userInfo.getUserId());
	                 userIdsSB.append(",");
	             }
	            
	             return userIdsSB.toString();
	        }
	        return null;
	}

	@Override
	public UserInfo findByPhone(String phone) {
		return hotlineuserDao.findByPhone(phone);
	}
}
