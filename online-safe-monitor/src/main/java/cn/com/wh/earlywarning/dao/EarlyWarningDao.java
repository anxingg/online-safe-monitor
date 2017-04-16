/**
 * 
 */
package cn.com.wh.earlywarning.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
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
@Repository("earlyWarningDao")
public class EarlyWarningDao extends BaseDao<EarlyWarning, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 获得预警信息列表
	 * @return
	 */
	public Page<EarlyWarning> pageInfo(Pageable pageable,EarlyWarningVo earlyWarningVo){
		String hql = " 1=1";
		
		//hql +=" ORDER BY waringLevel asc,status asc, beginTime desc";
		return super.findAll(hql, pageable,null);
	}

}
