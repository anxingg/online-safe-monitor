package cn.com.qytx.hotline.workingtime.service;

import java.util.List;

import cn.com.qytx.hotline.workingtime.domain.WorkingTime;
import cn.com.qytx.platform.base.service.BaseService;
/**
 * 作者：李贺
 * 创建时间：2014年12月7日
 * 修改时间：2014年12月7日
 *
 */
public interface IWorkingTime extends BaseService<WorkingTime>{
	/**
	 * 初始化工作时间
	 * @return
	 */
	List<WorkingTime> findWorkingTime();
	
	WorkingTime findById(Integer id);
}
