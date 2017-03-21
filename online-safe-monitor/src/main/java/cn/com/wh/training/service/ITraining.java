package cn.com.wh.training.service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.training.domain.SearchVo;
import cn.com.wh.training.domain.Training;

public interface ITraining extends BaseService<Training>{

	/**
	 * 
	 * 功能：培训列表
	 * @param page
	 * @param search
	 * @return
	 */
	public Page<Training> findByPage(Pageable page,SearchVo search);
}
