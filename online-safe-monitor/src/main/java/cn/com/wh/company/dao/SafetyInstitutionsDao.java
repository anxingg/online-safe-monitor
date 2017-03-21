package cn.com.wh.company.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.wh.company.domain.SafetyInstitutions;

/**
 * 安全管理机构DAO
 * @author lilipo
 *
 */
@Repository
public class SafetyInstitutionsDao extends
		BaseDao<SafetyInstitutions, Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5513774556393849942L;

	public SafetyInstitutions saveOrUpdate(SafetyInstitutions sis) {
		return super.saveOrUpdate(sis);
	}
	
	public SafetyInstitutions findByGroupId(Integer groupId) {
		if(groupId == null){
			return null;
		}
		String hql = " isDelete = 0 and groupId = ?";
		return super.findOne(hql, groupId);
	}
	
}
