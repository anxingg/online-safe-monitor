package cn.com.wh.fee.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.fee.domain.FeeExtract;
import cn.com.wh.fee.domain.SearchVo;

/**
 * 费用提取的DAO
 * @author lilipo
 *
 */
@Repository
public class FeeExtractDao extends BaseDao<FeeExtract, Integer>{
	
	/**
	 * 分页查询
	 * @param pageable
	 * @param sv
	 * @return
	 */
	public Page<FeeExtract> findByPage(Pageable pageable,
			SearchVo sv) {

		String hql = " isDelete = 0 ";
		List<Object> params = new ArrayList<Object>();

		Integer groupId = sv == null ? null : sv.getGroupId();
		String searchTimeStart = sv == null ? null : sv.getSearchTimeStart();
		String searchTimeEnd = sv == null ? null : sv.getSearchTimeEnd();
		
		// 企业ID
		if (groupId != null && groupId != 0) {
			hql += " and groupId = ?";
			params.add(groupId);
		}
		
		//查询开始时间
		if(StringUtils.isNotBlank(searchTimeStart)){
			hql += " and extractTime >= ?";
			params.add(searchTimeStart);
		}
		
		//查询结束时间
		if(StringUtils.isNotBlank(searchTimeEnd)){
			hql += " and extractTime <= ?";
			params.add(searchTimeEnd);
		}
		
		//过滤已经删除的公司
		hql +=" and groupId not in (select groupId from WHCompany where isDelete = 1) ";

		return super.findAll(hql, pageable, params.toArray());
	}
	
	/**
	 * 新增或修改
	 */
	public FeeExtract saveOrUpdate(FeeExtract feeExtract){
		return super.saveOrUpdate(feeExtract);
	}
	
	/**
	 * 查询一个
	 */
	public FeeExtract findOne(Integer id) {
		return super.findOne(id);
	}
}
