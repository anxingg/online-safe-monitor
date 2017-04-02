package cn.com.wh.thresholdtemplate.service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.thresholdtemplate.domain.ThresholdTemplate;



/**
 * @版本: 1.0 
 * @开发人员:吴胜光
 * @功能 阈值模板接口类
 * @创建时间 2017-04-03
 * @修改时间
 * @修改列表
 */
public interface IThresholdTemplate extends BaseService<ThresholdTemplate> {

	/**
	 * 分页查询 阈值模板
	 * @param pageable
	 * @param watchType 监控类型
	 * @param keyWord 关键字
	 * @return
	 */
	public Page<ThresholdTemplate> findThresholdTemplateByPage(Pageable pageable,String watchType,String keyWord);

}
