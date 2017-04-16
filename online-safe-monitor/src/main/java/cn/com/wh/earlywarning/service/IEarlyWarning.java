/**
 * 
 */
package cn.com.wh.earlywarning.service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.earlywarning.domain.EarlyWarning;
import cn.com.wh.earlywarning.domain.EarlyWarningVo;

/**
 * 功能: 
 * 版本: 1.0
 * 开发人员: 王刚
 * 创建日期: 2017年4月13日
 * 修改日期: 2017年4月13日
 * 修改列表: 
 */
public interface IEarlyWarning extends BaseService<EarlyWarning>{
	/**
	 * 获得预警信息列表
	 * @return
	 */
	public Page<EarlyWarning> pageInfo(Pageable pageable,EarlyWarningVo earlyWarningVo);
}
