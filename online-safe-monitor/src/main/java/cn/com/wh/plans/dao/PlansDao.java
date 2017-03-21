package cn.com.wh.plans.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.plans.domain.Plans;

/**
 * 功能: 应急预案
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-27
 * 修改日期: 
 * 修改列表:
 */
@Repository
public class PlansDao extends BaseDao<Plans, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;

	public Page<Plans> findPlansByPage(Pageable pageable,Integer groupId,String prepareTime,String prepareEndTime,Integer planType){
		String hql = " isDelete = 0 ";
        List<Object> params = new ArrayList<Object>();
        
    	if (groupId!=null && groupId!=-1)
        {
            hql += " and groupId = ?";
            params.add(groupId);
        }
    	
    	if (planType!=null && planType!=-1)
        {
            hql += " and planType = ?";
            params.add(planType);
        }
    	
    	if (!StringUtils.isEmpty(prepareTime))
        {
    		
            hql += " and prepareTime <= ?";
            params.add(Timestamp.valueOf(prepareTime+" 00:00:00"));
        }
    	
    	if (!StringUtils.isEmpty(prepareEndTime))
        {
            hql += " and prepareEndTime <= ?";
            params.add(Timestamp.valueOf(prepareEndTime+" 00:00:00"));
        }
       
        return super.findAll(hql, pageable, params.toArray());
		
	}
}
