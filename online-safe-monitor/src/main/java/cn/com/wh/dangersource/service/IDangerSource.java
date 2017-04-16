package cn.com.wh.dangersource.service;

import java.util.List;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.dangersource.domain.DangerSource;
import cn.com.wh.dangersource.domain.DangerSourceQuery;

public interface IDangerSource extends BaseService<DangerSource>{

	/**
	 * 危险源分页查询
	 * @param pageable 分页信息
	 * @param dsq 危险源查询类
	 * @return Page 分页结果数据
	 */
	Page<DangerSource> findByPage(Pageable pageable, DangerSourceQuery dsq);
	/**
	 * 根据企业的groupId获得危险源，-1表示全部
	 * @param groupId
	 * @return
	 */
	public List<DangerSource> findByCompanyId(Integer groupId);

	
	
//	/**
//	 * 根据某部门的所有下级单位
//	 * @param parentId
//	 * @return
//	 */
//	public List<WHCompany> findWHCompany(Integer parentId);
//	
//	
//	/**
//	 * 根据分组id 查询所属公司信息
//	 * @param groupId
//	 * @return 公司
//	 */
//	public WHCompany findByGroupId(Integer groupId);
//	
//	
//	/**
//	 * 根据公司名称 查询所属公司信息
//	 * @param companyName
//	 * @return 公司
//	 */
//	public WHCompany findByCompanyName(String companyName);
//	
//	/**
//	 * 根据人员id删除所在公司
//	 * @param userIds
//	 * @param companyId
//	 */
//	public void deleteCompanyByUserIds(String userIds,Integer companyId);
//	
//	/**
//	 * 根据linkid 查询公司信息
//	 * @param linkId 
//	 * @return 公司
//	 */
//	public WHCompany findByLinkId(String linkId);
}
