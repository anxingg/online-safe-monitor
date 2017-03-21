package cn.com.wh.company.service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.company.domain.WHCompanyPerson;



/**
 * @版本: 1.0 
 * @开发人员:吴胜光
 * @功能 IWHCompanyPerson接口类
 * @创建时间 2015-08-14
 * @修改时间
 * @修改列表
 */
public interface IWHCompanyPerson extends BaseService<WHCompanyPerson> {

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
	public Page<WHCompanyPerson> findByPage(Pageable pageable,Integer personType,String phone,String name,String workType,String groupName,Integer groupId,Integer personId,String minTime,String maxTime);
	
	
	/**
	 * 根据personId查询WHCompanyPerson对象
	 * @param personId  人员ID
	 * @return
	 */
	public WHCompanyPerson findWHCompanyPerson(Integer personId);
	
	/**
	 * 根据groupId查询WHCompanyPerson对象
	 * @param groupId  
	 * @return
	 */
	public WHCompanyPerson findByGroupId(Integer groupId);
	
	/**
	 * 添加WHCompanyPerson 对象
	 * @param WHCompanyPerson
	 * @return
	 */
	public int addWHCompanyPerson(WHCompanyPerson person);
	
	
	/**
	 * 修改WHCompanyPerson 对象
	 * @param WHCompanyPerson
	 * @return 影响的行数
	 */
	public int updateWHCompanyPerson(WHCompanyPerson person);
	
	
	/**
	 * 删除WHCompanyPerson 对象
	 * @param personId  人员ID
	 * @return  影响的行数
	 */
	public int deleteWHCompanyPerson(Integer personId);
	
	
	
}
