package cn.com.qytx.hotline.messagemanagement.service;

import cn.com.qytx.hotline.messagemanagement.domain.VoxMail;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
/**
 * 功能:留言管理接口
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-3
 * 修改日期: 2014-12-3
 * 修改列表:
 */
public interface IVoxMail extends BaseService<VoxMail> {

	/**
	 * 功能：分页查询留言列表
	 * @param page：分页
	 * @param vm：留言表实体
	 * @return
	 */
	Page<VoxMail> findByPage(Pageable page,VoxMail vm);
	
}
