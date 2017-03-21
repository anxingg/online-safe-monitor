/** 
 * @author 作者姓名  E-mail: email地址 
 * @version 创建时间：2015-8-25 上午10:31:36 
 * 类说明 
 */
package cn.com.wh.zhdwxy.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.zhdwxy.domain.WhDangerSources;

/**
 * @ClassName:     WhDangerSourcesDao.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-25 上午10:31:36 
 */
@Repository("whDangerSourcesDao")
public class WhDangerSourcesDao extends BaseDao<WhDangerSources,Integer> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Page<WhDangerSources> queryByConPage(Pageable page, String groupId,String dangerSourcesName, String safetyMeasures,Integer grade){
		StringBuffer hql = new StringBuffer();
		hql.append(" 1=1");
		if(groupId.length()>0){
			hql = hql.append(" and group_id =").append(groupId);
		}

		if(dangerSourcesName.length()>0){
			hql = hql.append(" and danger_sources_name like '%").append(dangerSourcesName).append("%'");
		}
		if(safetyMeasures.length()>0){
			hql = hql.append(" and safety_measures like '%").append(safetyMeasures).append("%'");
		}
		if(grade!=null){
			hql = hql.append(" and grade = ").append(grade);
		}
		return  this.findAll(hql.toString(), page);
		
	}
}
