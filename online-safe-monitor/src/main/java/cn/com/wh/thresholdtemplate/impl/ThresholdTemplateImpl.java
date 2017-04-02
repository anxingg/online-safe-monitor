package cn.com.wh.thresholdtemplate.impl;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.thresholdtemplate.dao.ThresholdTemplateDao;
import cn.com.wh.thresholdtemplate.domain.ThresholdTemplate;
import cn.com.wh.thresholdtemplate.service.IThresholdTemplate;

/**
 * 功能: 企业产品
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-25
 * 修改日期: 
 * 修改列表:
 */
@Service
public class ThresholdTemplateImpl extends BaseServiceImpl<ThresholdTemplate> implements IThresholdTemplate,Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(ThresholdTemplateImpl.class);
	
	@Autowired
	private ThresholdTemplateDao thresholdTemplateDao;
	
	@Override
	public Page<ThresholdTemplate> findThresholdTemplateByPage(
			Pageable pageable, String watchType, String keyWord) {
		// TODO Auto-generated method stub
		return thresholdTemplateDao.findThresholdTemplateByPage(pageable, watchType, keyWord);
	}

}
