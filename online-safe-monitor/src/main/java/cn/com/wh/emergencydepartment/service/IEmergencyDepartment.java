package cn.com.wh.emergencydepartment.service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.emergencydepartment.domain.EmergencyDepartment;
import cn.com.wh.emergencydepartment.domain.SearchVo;

public interface IEmergencyDepartment extends BaseService<EmergencyDepartment>{

	/**
	 * 功能：分页查询试题信息
	 * @return
	 */
	public Page<EmergencyDepartment> findByPage(Pageable page,SearchVo search);
	
	/**
	 * 保存
	 * @return 0失败1成功
	 */
	public int saveEmergency(EmergencyDepartment info);
	
	/**
	 * 删除应急机构
	 * @return 0失败1成功
	 */
	public int deleteEmergency(Integer emergencyId);
	
	
}
