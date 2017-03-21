package cn.com.wh.zhdwxy.service;

import java.util.List;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.zhdwxy.domain.DangerSourcesGood;

/**
 * 重大危险源危化品接口
 * @author lilipo
 *
 */
public interface IDangerSourcesGood {

	DangerSourcesGood saveOrUpdate(DangerSourcesGood dsg);
	
	DangerSourcesGood findOne(Integer vid);
	
	Page<DangerSourcesGood> findByPage(Pageable pageable, DangerSourcesGood dsg);
	
	List<DangerSourcesGood> findAll();
}
