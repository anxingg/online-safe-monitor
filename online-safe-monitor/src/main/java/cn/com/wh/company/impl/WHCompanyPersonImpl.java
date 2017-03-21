package cn.com.wh.company.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.company.dao.WHCompanyPersonDao;
import cn.com.wh.company.domain.WHCompanyPerson;
import cn.com.wh.company.service.IWHCompanyPerson;

/**
 * 功能: 安全管理人员/特种作业人员/单位法人 Impl
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-14
 * 修改日期: 
 * 修改列表:
 */
@Service
@Transactional
public class WHCompanyPersonImpl extends BaseServiceImpl<WHCompanyPerson> implements IWHCompanyPerson,Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(WHCompanyPersonImpl.class);
	
	/**
	 * 安全管理人员/特种作业人员/单位法人    DAO类
	 */
	@Autowired
	private WHCompanyPersonDao wHCompanyPersonDao;
	
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
	@Override
	public Page<WHCompanyPerson> findByPage(Pageable pageable ,Integer personType,String phone,String name,String workType,String groupName,Integer groupId,Integer personId,String minTime,String maxTime) {
		// TODO Auto-generated method stub
		return wHCompanyPersonDao.findByPage(pageable, personType, phone, name, workType, groupName,groupId,personId,minTime,maxTime);
	}

	@Override
	public WHCompanyPerson findWHCompanyPerson(Integer personId) {
		
		return wHCompanyPersonDao.findWHCompanyPerson(personId);
	}
	
	@Override
	public WHCompanyPerson findByGroupId(Integer groupId) {
		
		return wHCompanyPersonDao.findByGroupId(groupId);
	}
	

	@Override
	public int addWHCompanyPerson(WHCompanyPerson person) {
		
		wHCompanyPersonDao.saveOrUpdate(person);
		
		LOGGER.info("人员id"+person.getPersonId()+",添加安全管理人员/特种作业人员/单位法人"+person);
		return 1;
	}

	@Override
	public int updateWHCompanyPerson(WHCompanyPerson person) {
		
		wHCompanyPersonDao.saveOrUpdate(person);
		LOGGER.info("人员id"+person.getPersonId()+",修改安全管理人员/特种作业人员/单位法人"+person);
		return 1;
	}

	@Override
	public int deleteWHCompanyPerson(Integer personId) {
		
		wHCompanyPersonDao.deleteWHCompanyPerson(personId);
		LOGGER.info("删除安全管理人员/特种作业人员/单位法人,人员id"+personId);
		return 1;
	}
	
	

}
