package cn.com.qytx.hotline.messagemanagement.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.messagemanagement.dao.VoxMailDao;
import cn.com.qytx.hotline.messagemanagement.domain.VoxMail;
import cn.com.qytx.hotline.messagemanagement.service.IVoxMail;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

/**
 * 功能:留言管理实现类
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-3
 * 修改日期: 2014-12-3
 * 修改列表:
 */

@Service
@Transactional
public class VoxMailImpl extends BaseServiceImpl<VoxMail> implements IVoxMail,Serializable {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -8449352152401537619L;
	@Autowired
	private VoxMailDao voxMailDao;
	/**
	 * 功能：分页查询留言列表
	 * @param page：分页
	 * @param vm：留言表实体
	 * @return
	 */
	public Page<VoxMail> findByPage(Pageable page, VoxMail vm) {
		return voxMailDao.findByPage(page, vm);
	}
	

}
