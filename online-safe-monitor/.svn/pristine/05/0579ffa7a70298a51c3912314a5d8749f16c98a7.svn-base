package cn.com.wh.process.dao;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.process.domain.TechnologicalProcess;
import cn.com.wh.reliefgoods.domain.SearchVo;

@Component
public class TechnologicalProcessDao extends BaseDao<TechnologicalProcess, Integer>{
	
	/**
	 * 
	 * 功能：工艺流程分页
	 * @param page
	 * @param search
	 * @return
	 */
	public Page<TechnologicalProcess> findByPage(Pageable page,SearchVo search) {
		StringBuffer condition=new StringBuffer(" 1=1");
	    if(search!=null){
	    	if(StringUtils.isNotBlank(search.getTitle())){
				condition.append(" and title like '%"+search.getTitle()+"%'");
			}
	    	
	    	if (null!=search.getGroupId()) {
				condition.append(" and groupId="+search.getGroupId() );
			}
	    }
		return unDeleted().findAll(condition.toString(), page);
	}
	
}
