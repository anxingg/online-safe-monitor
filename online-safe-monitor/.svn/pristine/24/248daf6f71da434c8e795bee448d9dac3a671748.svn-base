package cn.com.wh.fee.service;

import java.util.Map;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.fee.domain.FeeUsed;
import cn.com.wh.fee.domain.SearchVo;

public interface IFeeUsed extends BaseService<FeeUsed>{

	/**
	 * 
	 * 功能：公司年度使用统计
	 * @param search
	 * @return
	 */
	public Map<Integer,Map<Integer, Double>> findStatistic(SearchVo search);
	
	Page<FeeUsed> findByPage(Pageable pageable, SearchVo sv);
}
