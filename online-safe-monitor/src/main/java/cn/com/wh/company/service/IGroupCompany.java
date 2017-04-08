package cn.com.wh.company.service;

import java.util.List;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.company.domain.GroupCompany;
import cn.com.wh.thresholdtemplate.domain.ThresholdTemplate;



/**
 * @版本: 1.0 
 * @开发人员:吴胜光
 * @功能 阈值模板接口类
 * @创建时间 2017-04-03
 * @修改时间
 * @修改列表
 */
public interface IGroupCompany extends BaseService<GroupCompany> {
	public String getCompanyIds(Integer groupId);
}
