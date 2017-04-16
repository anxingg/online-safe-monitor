/**
 * 
 */
package cn.com.wh.earlywarning.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.earlywarning.dao.EarlyWarningDao;
import cn.com.wh.earlywarning.domain.EarlyWarning;
import cn.com.wh.earlywarning.domain.EarlyWarningVo;
import cn.com.wh.earlywarning.service.IEarlyWarning;

/**
 * 功能: 
 * 版本: 1.0
 * 开发人员: 王刚
 * 创建日期: 2017年4月13日
 * 修改日期: 2017年4月13日
 * 修改列表: 
 */
@Service
@Transactional
public class EarlyWarningImpl extends BaseServiceImpl<EarlyWarning> implements IEarlyWarning{

	@Autowired
	private EarlyWarningDao earlyWarningDao;
	@Override
	public Page<EarlyWarning> pageInfo(Pageable pageable,
			EarlyWarningVo earlyWarningVo) {
		// TODO Auto-generated method stub
		return earlyWarningDao.pageInfo(pageable, earlyWarningVo);
	}

}
