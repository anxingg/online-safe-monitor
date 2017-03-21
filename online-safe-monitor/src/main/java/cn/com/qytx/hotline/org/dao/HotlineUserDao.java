package cn.com.qytx.hotline.org.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 用户操作实体类 User: 黄普友 Date: 13-2-17 Time: 下午2:58
 */
@SuppressWarnings("serial")
@Repository("hotlineuserDao")
public class HotlineUserDao<T extends UserInfo> extends
		BaseDao<UserInfo, Integer> implements Serializable {

	/**
	 * 功能：根据角色代码和公司ID查询该部门下的人员列表 作者：jiayongqiang 参数： 输出：
	 */
	public List<UserInfo> findUsersByRoleCodeAndGroup(String roleCode,
			Integer companyId) {
		roleCode = roleCode.replace(",", "','");
		String hql = " companyId = ? and  userId in (select userId from RoleUser where roleId in (select roleId from RoleInfo where roleCode in ('"
				+ roleCode + "')))";
		return super.dataFilter().findAll(hql, companyId);
	}
	/**
	 * 功能：更具电话查询用户信息
	 * 开发人员:张东领
	 * 创建日期:2015年4月9日
	 * 修改人员:
	 * 修改日期:
	 * @param phone
	 * @return
	 */
	public UserInfo findByPhone(String phone){
		String hql = " isDelete = 0 ";
		if(StringUtils.isNotBlank(phone)){
			hql += " and phone = '"+phone+"' ";
		}
		return super.findOne(hql);
	}
}
