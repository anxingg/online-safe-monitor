package cn.com.wh.plans.service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.plans.domain.Plans;



/**
 * @版本: 1.0 
 * @开发人员:吴胜光
 * @功能 应急预案接口类
 * @创建时间 2015-08-27
 * @修改时间
 * @修改列表
 */
public interface IPlans extends BaseService<Plans> {

	/**
	 * 分页查询 应急预案
	 * @param pageable
	 * @param groupId
	 * @param prepareTime 备案时间
	 * @param prepareEndTime 备案到期时间
	 * @param planType 预案类型
	 * @return
	 */
	public Page<Plans> findPlansByPage(Pageable pageable,Integer groupId,String prepareTime,String prepareEndTime,Integer planType);

}
