package cn.com.wh.company.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.company.domain.WHCompanyPerson;

/**
 * 功能: 安全管理人员/特种作业人员/单位法人 dao
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-14
 * 修改日期: 
 * 修改列表:
 */
@Repository
public class WHCompanyPersonDao extends BaseDao<WHCompanyPerson, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 分页查询  安全管理人员/特种作业人员/单位法人 
	 * @param pageable 分页信息
	 * @param personType 人员类型
	 * @param phone 联系方式
	 * @param name 人员姓名
	 * @param workType 工种
	 * @param groupName 部门
	 * @param groupId 部门ID
	 * @param minTime 
	 * @param maxTime 
	 * @return Page 分页结果数据
	 */
	public Page<WHCompanyPerson> findByPage(Pageable pageable ,Integer personType,String phone,String name,String workType,String groupName,Integer groupId,Integer personId,String minTime,String maxTime) {
		String hql = " isDelete = 0 ";
        List<Object> params = new ArrayList<Object>();
        
        hql += " and personType = ?";
    	params.add(personType);
        
    	if (groupId!=null && groupId!=-1)
        {
            hql += " and groupId = ?";
            params.add(groupId);
        }
    	
    	if (personId!=null)
        {
            hql += " and personId = ?";
            params.add(personId);
        }
    	
        if (!StringUtils.isEmpty(name))
        {
        	hql += " and name like ?";
        	params.add("%" + name + "%");
        }
        
        if (!StringUtils.isEmpty(phone))
        {
        	hql += " and phone like ?";
        	params.add("%" + phone + "%");
        }
        
        if (!StringUtils.isEmpty(workType))
        {
        	hql += " and workType like ?";
        	params.add("%" + workType + "%");
        }
        
        if (!StringUtils.isEmpty(groupName))
        {
            hql += " and groupName like ?";
            params.add("%" + groupName + "%");
        }
        
        if (!StringUtils.isEmpty(minTime))
        {
            hql += " and replaceDate >= ?";
            params.add(Timestamp.valueOf(minTime+" 00:00:00"));
        }
        
        if (!StringUtils.isEmpty(maxTime))
        {
            hql += " and replaceDate <= ?";
            params.add(Timestamp.valueOf(maxTime+" 00:00:00"));
        }
       
        return super.findAll(hql, pageable, params.toArray());
	}

	
	/**
	 * @功能 根据人员ID获取人员信息
	 * @param personId
	 * @return WHCompanyPerson
	 */
	public WHCompanyPerson findWHCompanyPerson(Integer personId) {
		
		return super.findOne(" isDelete = 0 and personId = ? ",personId);
	}
	
	/**
	 * @功能 根据GroupID获取人员信息
	 * @param personId
	 * @return WHCompanyPerson
	 */
	public WHCompanyPerson findByGroupId(Integer groupId) {
		
		return super.findOne(" isDelete = 0 and personType=1 and groupId = ? ",groupId);
	}
	


	/**
	 * @功能 根据人员ID  删除人员
	 * @param personId
	 * @return 
	 */
	public void deleteWHCompanyPerson(Integer personId) {
		
		String sql = "update tb_wuhai_company_person set is_delete = 1 where person_id = " + personId;
		javax.persistence.Query query = super.entityManager.createNativeQuery(sql);
		query.executeUpdate();
		//entityManager.createQuery(sql);
	}
	

	
	/**
	 * 根据分组id 查询所属公司的公司名
	 * @param groupId
	 * @return 公司名
	 */
	public String getCompanyName(Integer groupId){
		if(groupId==null){
			return "";
		}
		String sql = "select company_name from tb_wuhai_company_info where group_id="+groupId;
		Query query = entityManager.createNativeQuery(sql);
		Object result = query.getSingleResult();
		if(result!=null){
			return result.toString();
		}
		return "";
	}

}
