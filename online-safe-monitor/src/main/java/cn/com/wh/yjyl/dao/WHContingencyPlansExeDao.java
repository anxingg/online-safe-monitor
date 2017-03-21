/** 
 * @author 作者姓名  E-mail: email地址 
 * @version 创建时间：2015-8-27 下午02:34:12 
 * 类说明 
 */
package cn.com.wh.yjyl.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.yjyl.domain.WHContingencyPlansExe;

/**
 * @ClassName:     WHContingencyPlansExeDao.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-27 下午02:34:12 
 */
@Repository("wHContingencyPlansExeDao")
public class WHContingencyPlansExeDao extends BaseDao<WHContingencyPlansExe,Integer> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * exercise_name  应急演练名称
	 */
	public Page<WHContingencyPlansExe> queryByConPage(Pageable page, String groupId,String exercise_name, int plan_type,int plan_no){
		StringBuffer hql = new StringBuffer();
		hql.append(" 1=1");
		if(groupId.length()>0){
			hql = hql.append(" and group_id =").append(groupId);
		}

		if(exercise_name.length()>0){
			hql = hql.append(" and exercise_program like '%").append(exercise_name).append("%'");
		}
		if(plan_type!=-1){
			hql = hql.append(" and plan_type =").append(plan_type);
		}
		if(plan_no!=-1){
			hql = hql.append(" and plan_id =").append(plan_no);
		}
		return  this.findAll(hql.toString(), page);
		
	}
}
