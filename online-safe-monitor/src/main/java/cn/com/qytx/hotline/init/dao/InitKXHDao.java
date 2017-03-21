package cn.com.qytx.hotline.init.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.datafilterrule.domain.DataFilterRule;
import cn.com.qytx.platform.base.dao.BaseDao;

@SuppressWarnings("rawtypes")
@Component
public class InitKXHDao extends BaseDao {

	/**
	 * 根据公司ID，更新部门的is_fork_group字段，并返回部门ID
	 * @param companyId 公司ID
	 * @return Integer 部门ID
	 */
	public Integer updateGroupInfoByCompnayId(Integer companyId) {
		//通过公司ID，查询部门。
		String sql = "select group_id from tb_group_info where company_id = ?1";
		javax.persistence.Query query = super.entityManager.createNativeQuery(sql);
		query.setParameter(1, companyId);
		Object obj = getSingleResult(query);
		Integer groupId = null;
		if(obj != null){
			groupId = Integer.parseInt(obj.toString());
		}
		
		//更新部门的is_fork_group字段
		if(groupId != null){
			String sql2 = "update tb_group_info set is_delete=0 , is_fork_group = ?1 , path=?2 where group_id = ?3";
			javax.persistence.Query query2 = super.entityManager.createNativeQuery(sql2);
			query2.setParameter(1, groupId);
			query2.setParameter(2, groupId.toString());
			query2.setParameter(3, groupId);
			query2.executeUpdate();
		}
		
		return groupId;
	}
	
	/**
	 * 根据公司ID，更新人员的is_fork_group字段，并返回人员ID
	 * @param companyId 公司ID
	 * @param isForkGroup
	 * @return Integer 人员ID
	 */
	public Integer updateUserInfoByCompnayId(Integer companyId, Integer isForkGroup) {
		//通过公司ID，查询人员。
		String sql = "select user_id from view_user_info where company_id = ?1";
		javax.persistence.Query query = super.entityManager.createNativeQuery(sql);
		query.setParameter(1, companyId);
		Object obj = getSingleResult(query);
		Integer userId = null;
		if(obj != null){
			userId = Integer.parseInt(obj.toString());
		}
		
		//更新人员的is_fork_group字段
		if(userId != null){
			String sql2 = "update tb_user_info set is_fork_group = ?1 where user_id = ?2";
			javax.persistence.Query query2 = super.entityManager.createNativeQuery(sql2);
			query2.setParameter(1, isForkGroup);
			query2.setParameter(2, userId);
			query2.executeUpdate();
		}
		
		return userId;
	}
	
	@SuppressWarnings("unchecked")
	public List<DataFilterRule> findDataFilterRuleByCompnayId(Integer companyId) {
		//通过公司ID，查询DataFilterRule集合。
		String sql = "from DataFilterRule where companyId = ?1";
		javax.persistence.Query query = super.entityManager.createQuery(sql);
		query.setParameter(1, companyId);
		return query.getResultList();
	}
	
	public void updateDataFilterRuleById(DataFilterRule dfr) {
		//插入DataFilterRule
		super.entityManager.persist(dfr);
	}
	
	/**
	 * 查询单个对象，若查询不到，返回 null 。
	 * @param query
	 * @return
	 */
	private Object getSingleResult(javax.persistence.Query query){
		Object obj = null;
		try {
			obj = query.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			
		} catch (javax.persistence.NonUniqueResultException ex) {
			obj = query.getResultList();
			obj = ((List<?>) obj).get(0);
		}
		return obj;
	}

}
