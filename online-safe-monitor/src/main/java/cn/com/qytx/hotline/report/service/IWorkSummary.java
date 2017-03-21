package cn.com.qytx.hotline.report.service;

import java.util.List;

import cn.com.qytx.hotline.report.domain.WorkSummary;
import cn.com.qytx.hotline.report.domain.WorkSummarySearch;
import cn.com.qytx.platform.base.service.BaseService;

/**
 * 功能:工作汇总表service 
 * 版本: 1.0 
 * 开发人员: 张东领 
 * 创建日期: 2014-12-1 
 * 修改日期: 2014-12-1 
 * 修改列表:
 */
public interface IWorkSummary extends BaseService<WorkSummary>
{

    /**
     * 功能：查询工作汇总表
     * @param wss
     * @return
     */
    List<WorkSummary> workSummaryNumber(WorkSummarySearch wss ,Integer companyId);
}
