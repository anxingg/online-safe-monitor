package cn.com.qytx.hotline.workingtime.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.workingtime.dao.WorkingTimeDao;
import cn.com.qytx.hotline.workingtime.domain.WorkingTime;
import cn.com.qytx.hotline.workingtime.service.IWorkingTime;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
/**
 * 作者：李贺
 * 创建时间：2014年12月7日
 * 修改时间：2014年12月7日
 *
 */
@Service
@Transactional
public class WorkingTimeImpl extends BaseServiceImpl<WorkingTime> implements IWorkingTime ,Serializable{
	
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -8449352152401537619L;
	@Autowired
	private WorkingTimeDao workingTimeDao;

	@Override
	public List<WorkingTime> findWorkingTime() {
		return workingTimeDao.findWorkingTime();
	}

	@Override
	public WorkingTime findById(Integer id) {
		return workingTimeDao.findById(id);
	}

}
