package cn.com.wh.company.service;

import java.util.List;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.company.domain.WHCompany;

public interface IWHCompany extends BaseService<WHCompany>{

	/**
	 * 分页查询  公司信息 
	 * @param pageable 分页信息
	 * @param groupId 部门
	 * @return Page 分页结果数据
	 */
	public Page<WHCompany> findWHCompanyByPage(Pageable pageable,Integer groupId,Integer parentId);

	/**
	 * 根据某部门的所有下级单位
	 * @param parentId
	 * @return
	 */
	public List<WHCompany> findWHCompany(Integer parentId);
	
	
	/**
	 * 根据分组id 查询所属公司信息
	 * @param groupId
	 * @return 公司
	 */
	public WHCompany findByGroupId(Integer groupId);
	
	
	/**
	 * 根据公司名称 查询所属公司信息
	 * @param companyName
	 * @return 公司
	 */
	public WHCompany findByCompanyName(String companyName);
	
	/**
	 * 根据人员id删除所在公司
	 * @param userIds
	 * @param companyId
	 */
	public void deleteCompanyByUserIds(String userIds,Integer companyId);
	
	/**
	 * 根据linkid 查询公司信息
	 * @param linkId 
	 * @return 公司
	 */
	public WHCompany findByLinkId(String linkId);
}
