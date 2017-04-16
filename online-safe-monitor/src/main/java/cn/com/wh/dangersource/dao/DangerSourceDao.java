package cn.com.wh.dangersource.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.dangersource.domain.DangerSource;
import cn.com.wh.dangersource.domain.DangerSourceQuery;

@Component
public class DangerSourceDao extends BaseDao<DangerSource, Serializable> implements Serializable{

	private static final long serialVersionUID = 1580954590142207584L;
	

	/**
	 * 分页查询
	 * @param pageable
	 * @param dsq 危险源查询类
	 * @return
	 */
	public Page<DangerSource> findByPage(Pageable pageable, DangerSourceQuery dsq) {
		StringBuffer hql = new StringBuffer();
		/* 查询未删除的数据 */
		hql.append(" isDelete = 0 ");
        List<Object> params = new ArrayList<Object>();
        
        /* 危险源级别 */
        if (dsq.getDangerLevel() != null && dsq.getDangerLevel() != -1) {
            hql.append("and level = ? ");
            params.add(dsq.getDangerLevelStrStr());
        }
       
        /* 关键字(危险源名称、企业名称) */
        if (StringUtils.isNotBlank(dsq.getKeyword())) {	
        	hql.append("and (dangerSourceName like ? or company.companyName like ?) ");
            params.add(dsq.getKeyword());
            params.add(dsq.getKeyword());
        }
        
        return super.findAll(hql.toString(), pageable, params.toArray());        	
        
	}

	/**
	 * 根据单位ID获得危险源列表
	 * @param companyId
	 * @return
	 */
	public List<DangerSource> findByCompanyId(Integer companyId)
	{
		String hql=" isDelete=0 ";
		if((companyId!=null)&& companyId!=-1){
			hql+=" and company.companyId="+companyId;
		}
		return this.findAll(hql);
	}
//	public List<WHCompany> findWHCompany(String groupIds)
//	{
//		String hql = " isDelete = 0 ";
//       
//        if (groupIds!=null)
//        {	
//            hql += " and groupId in ("+groupIds+")";
//        }
//        return this.findAll(hql);
//	}
//	
//	/**
//	 * 根据分组id 查询所属公司的公司名
//	 * @param groupId
//	 * @return 公司名
//	 */
//	public String getCompanyName(Integer groupId){
//		String hql = " isDelete = 0 ";
//		List<Object> params = new ArrayList<Object>();
//        
//        if (groupId!=null && groupId!=-1)
//        {
//            hql += " and groupId = ?";
//            params.add(groupId);
//        }
//		
//		WHCompany com = super.findOne(hql,params.toArray());
//		if(com!=null){
//			return com.getCompanyName();
//		}
//		return "";
//	}
//	
//	
//	
//	/**
//	 * 根据分组id 查询所属公司信息
//	 * @param groupId
//	 * @return 公司
//	 */
//	public WHCompany findByGroupId(Integer groupId) {
//		String hql = " 1=1 ";
//		List<Object> params = new ArrayList<Object>();
//        
//        if (groupId!=null && groupId!=-1)
//        {
//            hql += " and groupId = ?";
//            params.add(groupId);
//        }
//		
//		return super.findOne(hql,params.toArray());
//	}
//	
//	
//	/**
//	 * 根据公司名称 查询所属公司信息
//	 * @param companyName
//	 * @return 公司
//	 */
//	public WHCompany findByCompanyName(String companyName){
//		String hql = " isDelete = 0 ";
//		List<Object> params = new ArrayList<Object>();
//        
//		if (StringUtils.isEmpty(companyName)){
//			return null;
//		}
//        
//        hql += " and companyName = ?";
//        params.add(companyName);
//        
//		
//		return super.findOne(hql,params.toArray());
//	}
//	
//	/**
//	 * 根据人员id删除人员所在的公司
//	 * @param groupType
//	 * @return
//	 */
//	public void deleteCompanyByUserIds(String userIds){
//		String hql = "update WHCompany set isDelete = ? where groupId in (select groupId from UserInfo where userId in ("+userIds+"))";
//		super.bulkUpdate(hql, 1);
//	}
//	
//	/**
//	 * 根据linkid 查询公司信息
//	 * @param linkId 
//	 * @return 公司
//	 */
//	public WHCompany findByLinkId(String linkId){
//		return super.findOne(" linkId = ? ",linkId);
//	}
}
