package cn.com.wh.company.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.company.domain.WHCompany;

@Component
public class WHCompanyDao extends BaseDao<WHCompany, Serializable> implements Serializable{

	private static final long serialVersionUID = 1580954590142207584L;
	

	public Page<WHCompany> findWHCompanyByPage(Pageable pageable,
			Integer groupId, String groupIds,String  companyIds) {
		String hql = " isDelete = 0 ";
        List<Object> params = new ArrayList<Object>();
        
        if (groupId!=null && groupId!=-1)
        {
            hql += " and groupId = ?";
            params.add(groupId);
        }
       
        if (!StringUtils.isEmpty(groupIds))
        {	
            hql += " and groupId in ("+groupIds+")";
        }
        
        if(!StringUtils.isEmpty(companyIds))
        {
        	hql +=" and companyId in ("+companyIds+")";
        }
        return super.findAll(hql, pageable, params.toArray());        	
        
	}
	public List<WHCompany> findWHCompany(String groupIds)
	{
		String hql = " isDelete = 0 ";
       
        if (groupIds!=null)
        {	
            hql += " and groupId in ("+groupIds+")";
        }
        return this.findAll(hql);
	}
	
	/**
	 * 根据分组id 查询所属公司的公司名
	 * @param groupId
	 * @return 公司名
	 */
	public String getCompanyName(Integer groupId){
		String hql = " isDelete = 0 ";
		List<Object> params = new ArrayList<Object>();
        
        if (groupId!=null && groupId!=-1)
        {
            hql += " and groupId = ?";
            params.add(groupId);
        }
		
		WHCompany com = super.findOne(hql,params.toArray());
		if(com!=null){
			return com.getCompanyName();
		}
		return "";
	}
	
	
	
	/**
	 * 根据分组id 查询所属公司信息
	 * @param groupId
	 * @return 公司
	 */
	public WHCompany findByGroupId(Integer groupId) {
		String hql = " 1=1 ";
		List<Object> params = new ArrayList<Object>();
        
        if (groupId!=null && groupId!=-1)
        {
            hql += " and groupId = ?";
            params.add(groupId);
        }
		
		return super.findOne(hql,params.toArray());
	}
	
	
	/**
	 * 根据公司名称 查询所属公司信息
	 * @param companyName
	 * @return 公司
	 */
	public WHCompany findByCompanyName(String companyName){
		String hql = " isDelete = 0 ";
		List<Object> params = new ArrayList<Object>();
        
		if (StringUtils.isEmpty(companyName)){
			return null;
		}
        
        hql += " and companyName = ?";
        params.add(companyName);
        
		
		return super.findOne(hql,params.toArray());
	}
	
	/**
	 * 根据人员id删除人员所在的公司
	 * @param groupType
	 * @return
	 */
	public void deleteCompanyByUserIds(String userIds){
		String hql = "update WHCompany set isDelete = ? where groupId in (select groupId from UserInfo where userId in ("+userIds+"))";
		super.bulkUpdate(hql, 1);
	}
	
	/**
	 * 根据linkid 查询公司信息
	 * @param linkId 
	 * @return 公司
	 */
	public WHCompany findByLinkId(String linkId){
		return super.findOne(" linkId = ? ",linkId);
	}
}
