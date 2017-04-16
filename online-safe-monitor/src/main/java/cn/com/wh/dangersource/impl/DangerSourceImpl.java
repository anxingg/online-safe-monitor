package cn.com.wh.dangersource.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.dao.GroupDao;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.wh.company.dao.WHCompanyDao;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.dangersource.dao.DangerSourceDao;
import cn.com.wh.dangersource.domain.DangerSource;
import cn.com.wh.dangersource.domain.DangerSourceQuery;
import cn.com.wh.dangersource.service.IDangerSource;

@Service("dangerSourceImpl")
@Transactional
public class DangerSourceImpl extends BaseServiceImpl<DangerSource> 
		implements IDangerSource ,Serializable{

	private static final long serialVersionUID = -5443442130361309154L;

	@Resource
	private DangerSourceDao dao;
	
	/** 用户信息 */
    @Resource(name = "userService")
    private IUser userService;

    @Resource
    private GroupDao groupDao;
    
    @Resource
    private WHCompanyDao whCompanyDao;
    
//	/**
//	 * 分页查询  公司信息 
//	 * @param pageable 分页信息
//	 * @param groupId 部门
//	 * @return Page 分页结果数据
//	 */
//	@Override
//	public Page<WHCompany> findWHCompanyByPage(Pageable pageable,
//			Integer groupId,Integer parentId,String groupIds) {
//		String inStr=null;
//		if(parentId!=null){
//			inStr=groupDao.getSubGroupIds(parentId);
//		}
//		Page<WHCompany> page  = wHCompanyDao.findWHCompanyByPage(pageable, 
//				groupId,inStr,groupIds);
//		return page;
//	}
//	
//	@Override
//	public List<WHCompany> findWHCompany(Integer parentId)
//	{
//		String inStr=groupDao.getSubGroupIds(parentId);
//		return wHCompanyDao.findWHCompany(inStr);
//	}
//	@Override
//	public WHCompany findByGroupId(Integer groupId) {
//		WHCompany cpy = wHCompanyDao.findByGroupId(groupId);
//		return cpy;
//	}
//	
//	/**
//	 * 根据公司名称 查询所属公司信息
//	 * @param companyName
//	 * @return 公司
//	 */
//	@Override
//	public WHCompany findByCompanyName(String companyName){
//		
//		return wHCompanyDao.findByCompanyName(companyName);
//	}
//	/**
//	 * 根据人员id删除所在公司
//	 * @param userIds
//	 * @param companyId
//	 */
//	public void deleteCompanyByUserIds(String userIds,Integer companyId){
//		wHCompanyDao.deleteCompanyByUserIds(userIds);
//		userService.deleteUserByIds(userIds, false, companyId);
//	}
//	
//	/**
//	 * 根据linkid 查询公司信息
//	 * @param linkId 
//	 * @return 公司
//	 */
//	public WHCompany findByLinkId(String linkId){
//		return wHCompanyDao.findByLinkId(linkId);
//	}

	@Override
	public Page<DangerSource> findByPage(Pageable pageable,
			DangerSourceQuery dsq) {
		return dao.findByPage(pageable, dsq);
	}

	@Override
	public List<DangerSource> findByCompanyId(Integer groupId){
		Integer companyId=-1;
		if((groupId!=null)&& groupId!=-1){
			WHCompany whCompany=whCompanyDao.findByGroupId(groupId);
			companyId=whCompany.getCompanyId();
		}
		return dao.findByCompanyId(companyId);
	}

	
}
