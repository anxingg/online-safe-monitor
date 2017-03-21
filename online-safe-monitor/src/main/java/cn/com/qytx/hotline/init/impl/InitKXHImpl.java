package cn.com.qytx.hotline.init.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.datafilterrule.domain.DataFilterRule;
import cn.com.qytx.hotline.init.dao.InitKXHDao;
import cn.com.qytx.hotline.init.service.IInitKXH;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.utils.enumeration.DataFilterType;

@Service
@Transactional
public class InitKXHImpl implements IInitKXH {
	@Resource
	private InitKXHDao initKXHDao;

	@Override
	public Integer updateGroupInfoByCompnayId(Integer companyId) {
		return initKXHDao.updateGroupInfoByCompnayId(companyId);
	}
	
	@Override
	public Integer updateUserInfoByCompnayId(Integer companyId, Integer isForkGroup) {
		return initKXHDao.updateUserInfoByCompnayId(companyId, isForkGroup);
	}
	
	@Override
	public void updateDataFilterRuleByCompnayId(Integer companyId, Integer groupId) {
		List<DataFilterRule> list = initKXHDao.findDataFilterRuleByCompnayId(-1);
		if(list != null && !list.isEmpty()){
			for(DataFilterRule dfr : list){
				DataFilterRule newdfr = new DataFilterRule();
				
				newdfr.setCompanyId(companyId);
				newdfr.setConditionJpql(dfr.getConditionJpql());
				newdfr.setModelClassName(dfr.getModelClassName());
				newdfr.setName(dfr.getName());
				newdfr.setOperationType(dfr.getOperationType());
				
				String conditionJpql = dfr.getConditionJpql();
				if(StringUtils.isNotBlank(conditionJpql) && "isForkGroup is not null".equals(conditionJpql.trim())){
					newdfr.setRelationId("group_"+groupId);
				}else{
					newdfr.setRelationId(dfr.getRelationId());
				}
				initKXHDao.updateDataFilterRuleById(newdfr);
			}
		}
	}

	@Override
	public BaseDao companyId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseDao companyId(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long count(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BaseDao dataFilter(DataFilterType arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Object arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(String arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deleteByIds(Iterable arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByIds(boolean arg0, Iterable arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAll(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAll(String arg0, Sort arg1, Object... arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page findAll(String arg0, Pageable arg1, Object... arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findOne(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findOne(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseDao isDeleted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object saveOrUpdate(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List saveOrUpdate(Iterable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseDao unDeleted() {
		// TODO Auto-generated method stub
		return null;
	}

}
