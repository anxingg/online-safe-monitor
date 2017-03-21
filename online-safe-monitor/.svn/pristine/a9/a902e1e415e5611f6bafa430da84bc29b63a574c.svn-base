/**
 * 
 */
package cn.com.wh.aqyh.service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.aqyh.domain.WuhaiSafeDanger;

/** 
 * @author 作者姓名  E-mail: email地址 
 * @version 创建时间：2015-8-21 上午11:20:27 
 * 类说明 
 */
/**
 * @ClassName:     AqyhService.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-21 上午11:20:27 
 */
public interface AqyhService extends BaseService<WuhaiSafeDanger>{
	
	public Page<WuhaiSafeDanger> queryByConPage(Pageable page,String group_id,String reportTime,String happyTime,String dept, String deptor,int data_source);
	
	
	public WuhaiSafeDanger queryDetail(String vid);
	
	public int saveorup(WuhaiSafeDanger wuhaiSafeDanger);
}
