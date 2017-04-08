package cn.com.wh.company.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.company.domain.GroupCompany;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.thresholdtemplate.domain.ThresholdTemplate;

/**
 * 功能: 应急预案
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-27
 * 修改日期: 
 * 修改列表:
 */
@Repository
public class GroupCompanyDao extends BaseDao<GroupCompany, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 获得组织绑定的企业列表
	 * @param groupId
	 * @return
	 */
	public List<GroupCompany> findGroupCompany(Integer groupId){
		String hql = " isDelete = 0 ";
		List<Object> params = new ArrayList<Object>();
        hql += " and groupId = ?";
        params.add(groupId);
		return super.findAll(hql,params.toArray());
	}
}
