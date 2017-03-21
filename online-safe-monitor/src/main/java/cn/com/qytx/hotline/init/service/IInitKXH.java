package cn.com.qytx.hotline.init.service;

import cn.com.qytx.platform.base.service.BaseService;

@SuppressWarnings("rawtypes")
public interface IInitKXH extends BaseService {

	/**
	 * 根据公司ID，更新部门的is_fork_group字段，并返回部门ID
	 * @param companyId 公司ID
	 * @return Integer 部门ID
	 */
	Integer updateGroupInfoByCompnayId(Integer companyId);
	
	/**
	 * 根据公司ID，更新人员的is_fork_group字段，并返回人员ID
	 * @param companyId 公司ID
	 * @param isForkGroup
	 * @return Integer 人员ID
	 */
	Integer updateUserInfoByCompnayId(Integer companyId, Integer isForkGroup);
	
	/**
	 * 更新companyId和更新conditionJpql
	 * @param companyId
	 * @param groupId
	 */
	void updateDataFilterRuleByCompnayId(Integer companyId, Integer groupId);
}
