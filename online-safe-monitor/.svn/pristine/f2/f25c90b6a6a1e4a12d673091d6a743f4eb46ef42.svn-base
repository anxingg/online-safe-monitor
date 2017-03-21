package cn.com.wh.emergencydepartment.dao;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.wh.emergencydepartment.domain.EmergencyDepartment;
import cn.com.wh.emergencydepartment.domain.SearchVo;

@Component
public class EmergencyDepartmentDao extends BaseDao<EmergencyDepartment, Integer>{
	
	/**
	 * 
	 * 功能：应急机构分页
	 * @param page
	 * @param search
	 * @return
	 */
	public Page<EmergencyDepartment> findByPage(Pageable page,SearchVo search) {
		StringBuffer condition=new StringBuffer(" 1=1");
	    if(search!=null){
	    	if(StringUtils.isNotBlank(search.getTitle())){
				condition.append(" and departName like '%"+search.getTitle()+"%'");
			}
	    	
	    	if (search.getIsShow() != null) {
				condition.append(" and isShow="+search.getIsShow());
				if (null!=search.getGroupId()) {
					condition.append(" and groupId="+search.getGroupId() );
				}
			}else{
				if (null!=search.getGroupId()) {
					condition.append(" and (groupId="+search.getGroupId()+" or departType=3)" );
				}
			}
			
			if (StringUtils.isNotBlank(search.getDepartType())) {
				condition.append(" and departType in ("+search.getDepartType()+")");
			}
	    }
		return unDeleted().findAll(condition.toString(), page);
	}
	
	/**
	 * 
	 * 功能：应急机构列表
	 * @param search
	 * @return
	 */
	public List<EmergencyDepartment> findList(SearchVo search,Sort sort) {
		StringBuffer condition=new StringBuffer(" 1=1");
	    if(search!=null){
	    	if(StringUtils.isNotBlank(search.getTitle())){
				condition.append(" and departName like '%"+search.getTitle()+"%'");
			}
		
			if (null!=search.getGroupId()) {
				condition.append(" and groupId="+search.getGroupId());
			}
			
			if (search.getIsShow() != null) {
				condition.append(" and isShow="+search.getIsShow());
			}
			
			if (StringUtils.isNotBlank(search.getDepartType())) {
				condition.append(" and departType in ("+search.getDepartType()+")");
			}
	    }
		return unDeleted().findAll(condition.toString(),sort);
	}
	
	/**
	 * 保存实体类
	 * @param paper
	 * @return
	 */
	public void saveED(EmergencyDepartment info){
		super.saveOrUpdate(info);
	}
	
	/**
	 * 更新应急机构是否在列表中显示
	 * @param paperId
	 * @param state
	 */
	public void updateShow(Integer id,Integer isShow){
		String hql = "update EmergencyDepartment set isShow=?1 where id=?2";
		executeQuery(hql,isShow,id);
	}
	
	
}
