package cn.com.wh.fee.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.fee.domain.FeeUsed;
import cn.com.wh.fee.domain.SearchVo;

@Component
public class FeeUsedDao extends BaseDao<FeeUsed, Integer>{
	
	
	/**
	 * 
	 * 功能：公司年度使用统计
	 * @param search
	 * @return
	 */
	public Map<Integer,Map<Integer, Double>> findStatistic(SearchVo search) {
		Map<Integer,Map<Integer, Double>> map = new HashMap<Integer, Map<Integer, Double>>();
		String jpql = "select new map(useDirection as useDirection,sum(useMoney) as totalCount,quarter as quarter) from "+this.getEntityClass().getSimpleName()+" where year=? and groupId=? group by useDirection,quarter";
		List<Map<String,Object>> list = super.find(jpql,search.getYear(),search.getGroupId());
		for(int i=0; i<list.size(); i++){
			Map<String,Object> temp = list.get(i);
			int useDirection = temp.get("useDirection")==null?0:Integer.parseInt(temp.get("useDirection")+"");
			Double totalCount = temp.get("totalCount")==null?0:Double.valueOf(temp.get("totalCount").toString());
			int quarter = temp.get("quarter")==null?1:Integer.parseInt(temp.get("quarter")+"");
			Map<Integer, Double> quarterMap = null;
			if (map==null||map.get(useDirection)==null) {
				quarterMap = new HashMap<Integer, Double>();
				quarterMap.put(quarter, totalCount);
			}else {
				quarterMap = map.get(useDirection);
				quarterMap.put(quarter, totalCount);
			}
			map.put(useDirection, quarterMap);
		}
		return map;
	}
	
	public Page<FeeUsed> findByPage(Pageable pageable, SearchVo sv) {
		String hql = " isDelete = 0 ";
		List<Object> params = new ArrayList<Object>();

		Integer groupId = sv == null ? null : sv.getGroupId();
		String searchTimeStart = sv == null ? null : sv.getSearchTimeStart();
		String searchTimeEnd = sv == null ? null : sv.getSearchTimeEnd();
		String keyword = sv.getKeyword();
		
		// 企业ID
		if (groupId != null && groupId != 0) {
			hql += " and groupId = ?";
			params.add(groupId);
		}
		
		//查询开始时间
		if(StringUtils.isNotBlank(searchTimeStart)){
			hql += " and useTime >= ?";
			params.add(searchTimeStart);
		}
		
		//查询结束时间
		if(StringUtils.isNotBlank(searchTimeEnd)){
			hql += " and useTime <= ?";
			params.add(searchTimeEnd);
		}
		
		//输入框
		if(StringUtils.isNotBlank(keyword)){
			hql += " and useDirection in (select id from Dict where isDelete = 0 and sysTag = 1 and infoType = 'feeType' and name like ?)";
			params.add("%"+keyword+"%");
		}
		
		//过滤已经删除的公司
		hql +=" and groupId not in (select groupId from WHCompany where isDelete = 1) ";

		return super.findAll(hql, pageable, params.toArray());
	}
}
