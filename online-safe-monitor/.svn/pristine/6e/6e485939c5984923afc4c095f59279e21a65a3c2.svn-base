package cn.com.wh.aqyh.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.aqyh.domain.WuhaiSafeDanger;

/**
 * @ClassName:     WuhaiSafeDangerDao.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-21 上午11:29:45 
 */
@Repository("wuhaiSafeDangerDao")
public class WuhaiSafeDangerDao extends BaseDao<WuhaiSafeDanger,Integer> implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<WuhaiSafeDanger> queryByCon(Pageable page,String group_id,String reportTime,String happyTime,String dept, String deptor){
		StringBuffer hql = new StringBuffer();
		
		hql = hql.append("1=1");
		if(reportTime.length()>0){
			hql = hql.append(" and to_char(review_time,'yyyymmdd') =").append(reportTime);
		}
		if(group_id.length()>0){
			hql = hql.append(" and group_id =").append(reportTime);
		}
		if(happyTime.length()>0){
			hql = hql.append(" and to_char(checkDate,'yyyymmdd') =").append(happyTime);
		}
		if(dept.length()>0){
			hql = hql.append(" and responsible_department like %").append(reportTime).append("%");
		}
		if(deptor.length()>0){
			hql = hql.append(" and responsible like %").append(deptor).append("%");
		}
		
		List<WuhaiSafeDanger> retList = (List<WuhaiSafeDanger>) this.findAll(hql.toString(), page);
		
		return retList;
		
	}
	
	
	
	public Page<WuhaiSafeDanger> queryByConPage(Pageable page,String group_id,String reportTime,String happyTime,String dept, String deptor,int data_source){
		StringBuffer hql = new StringBuffer();
		
		hql = hql.append(" 1=1 and group_id in (select groupId from WHCompany where isDelete=0)");
		if(reportTime.length()>0){
			hql = hql.append(" and to_char(review_time,'yyyymmdd') ='").append(reportTime).append("'");
		}
		if(group_id.length()>0){
			hql = hql.append(" and group_id =").append(group_id);
		}
		if(happyTime.length()>0){
			hql = hql.append(" and to_char(checkDate,'yyyymmdd') ='").append(happyTime).append("'");
		}
		if(dept.length()>0){
			hql = hql.append(" and responsible_department like '%").append(dept).append("%'");
		}
		if(deptor.length()>0){
			hql = hql.append(" and responsible like '%").append(deptor).append("%'");
		}
		if(data_source==2){//企业端
			hql = hql.append(" and can_see=1");
		}
		
		
		return  this.findAll(hql.toString(), page);
		
	}
	
	
}
