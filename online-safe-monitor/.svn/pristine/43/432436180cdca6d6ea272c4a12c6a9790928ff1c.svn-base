package cn.com.wh.plans.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.plans.dao.PlansDao;
import cn.com.wh.plans.domain.Plans;
import cn.com.wh.plans.service.IPlans;

/**
 * 功能: 企业产品
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-25
 * 修改日期: 
 * 修改列表:
 */
@Service
public class PlanstImpl extends BaseServiceImpl<Plans> implements IPlans,Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(PlanstImpl.class);
	
	@Autowired
	private PlansDao plansDao;
	
	public Page<Plans> findPlansByPage(Pageable pageable,Integer groupId,String prepareTime,String prepareEndTime,Integer planType){
		
		return plansDao.findPlansByPage(pageable,groupId,prepareTime,prepareEndTime,planType);
	
	}

}
