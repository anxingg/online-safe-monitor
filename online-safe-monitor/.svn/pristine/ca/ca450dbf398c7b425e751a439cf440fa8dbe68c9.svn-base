package cn.com.wh.reliefgoods.service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.reliefgoods.domain.ReliefGoods;
import cn.com.wh.reliefgoods.domain.SearchVo;

public interface IReliefGoods extends BaseService<ReliefGoods>{

	/**
	 * 功能：分页查询
	 * @return
	 */
	public Page<ReliefGoods> findByPage(Pageable page,SearchVo search);
	
	/**
	 * 保存
	 * @return 0失败1成功
	 */
	public int saveGoods(ReliefGoods info);
	
	/**
	 * 删除
	 * @return 0失败1成功
	 */
	public int deleteGoods(Integer goodsId);
	
	
}
