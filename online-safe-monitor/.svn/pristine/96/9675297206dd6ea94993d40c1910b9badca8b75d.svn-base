package cn.com.wh.training.dao;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.training.domain.PreserviceTraining;
import cn.com.wh.training.domain.SearchVo;

@Component
public class PreserviceTrainingDao extends BaseDao<PreserviceTraining, Integer>{
	
	/**
	 * 
	 * 功能：岗前三级培训列表
	 * @param page
	 * @param search
	 * @return
	 */
	public Page<PreserviceTraining> findByPage(Pageable page,SearchVo search) {
		StringBuffer condition=new StringBuffer(" 1=1");
	    if(search!=null){
	    	if(StringUtils.isNotBlank(search.getUserName())){
				condition.append(" and userName like '%"+search.getUserName()+"%'");
			}
		
			if (null!=search.getSex()) {
				condition.append(" and sex="+search.getSex());
			}
			
			if (StringUtils.isNotBlank(search.getCheckerName())) {
				condition.append(" and checker like '%"+search.getCheckerName()+"%'");
			}
			
			if (search.getGroupId()!=null) {
				condition.append(" and groupId="+search.getGroupId());
			}
	    }
		return unDeleted().findAll(condition.toString(), page);
	}
	
}
