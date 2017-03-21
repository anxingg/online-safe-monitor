package cn.com.qytx.hotline.customercall.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import cn.com.qytx.hotline.customercall.domain.CclMsiLog;
import cn.com.qytx.platform.base.dao.BaseDao;

/**
 * 功能: 话单与工单关联Dao
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2014-1-6
 * 修改日期: 2014-1-6
 * 修改列表:
 */
@Repository
public class CclMsiLogDao extends BaseDao<CclMsiLog, Integer> implements Serializable
{
	private static final long serialVersionUID = 1L;
}
