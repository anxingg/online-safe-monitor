package cn.com.wh.company.impl;

import java.io.Serializable;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.wh.company.dao.WHCompanyDao;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.util.DataInitUtil;

@Service("companyImpl")
@Transactional
public class WHCompanyImpl extends BaseServiceImpl<WHCompany> implements IWHCompany ,Serializable{

	private static final long serialVersionUID = -5443442130361309154L;

	@Resource
	WHCompanyDao wHCompanyDao;
	/** 用户信息 */
    @Resource(name = "userService")
    IUser userService;

	/**
	 * 分页查询  公司信息 
	 * @param pageable 分页信息
	 * @param groupId 部门
	 * @return Page 分页结果数据
	 */
	@Override
	public Page<WHCompany> findWHCompanyByPage(Pageable pageable, Integer groupId) {
		Page<WHCompany> page  = wHCompanyDao.findWHCompanyByPage(pageable, groupId);
		return page;
	}
	

	@Override
	public WHCompany findByGroupId(Integer groupId) {
		WHCompany cpy = wHCompanyDao.findByGroupId(groupId);
		return cpy;
	}
	
	/**
	 * 根据公司名称 查询所属公司信息
	 * @param companyName
	 * @return 公司
	 */
	@Override
	public WHCompany findByCompanyName(String companyName){
		
		return wHCompanyDao.findByCompanyName(companyName);
	}
	/**
	 * 根据人员id删除所在公司
	 * @param userIds
	 * @param companyId
	 */
	public void deleteCompanyByUserIds(String userIds,Integer companyId){
		wHCompanyDao.deleteCompanyByUserIds(userIds);
		userService.deleteUserByIds(userIds, false, companyId);
	}
	
	/**
	 * 根据linkid 查询公司信息
	 * @param linkId 
	 * @return 公司
	 */
	public WHCompany findByLinkId(String linkId){
		return wHCompanyDao.findByLinkId(linkId);
	}
	
}
