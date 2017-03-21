package cn.com.qytx.hotline.report.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.report.dao.WorkSummaryDao;
import cn.com.qytx.hotline.report.domain.WorkSummary;
import cn.com.qytx.hotline.report.domain.WorkSummarySearch;
import cn.com.qytx.hotline.report.service.IWorkSummary;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
@Service
@Transactional
public class WorkSummaryImpl extends BaseServiceImpl<WorkSummary> implements IWorkSummary,Serializable {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 8835094324392610150L;
	
	/**
	 * 工作汇总Dao
	 */
	@Autowired
	private WorkSummaryDao workSummaryDao;

	@Override
	public List<WorkSummary> workSummaryNumber(WorkSummarySearch wss,Integer companyId) {
		return workSummaryDao.workSummaryNumber(wss,companyId);
	}

}
