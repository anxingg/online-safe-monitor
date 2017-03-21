package cn.com.wh.zhdwxy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.zhdwxy.dao.DangerSourcesGoodDao;
import cn.com.wh.zhdwxy.domain.DangerSourcesGood;

/**
 * 重大危险源危化品接口实现
 * @author lilipo
 *
 */
@Service
@Transactional
public class DangerSourcesGoodImpl implements IDangerSourcesGood {
	
	@Resource
	private DangerSourcesGoodDao dao;

	@Override
	public DangerSourcesGood saveOrUpdate(DangerSourcesGood dsg) {
		return dao.saveOrUpdate(dsg);
	}

	@Override
	public DangerSourcesGood findOne(Integer vid) {
		return dao.findOne(vid);
	}

	@Override
	public Page<DangerSourcesGood> findByPage(Pageable pageable,
			DangerSourcesGood dsg) {
		return dao.findByPage(pageable, dsg);
	}

	@Override
	public List<DangerSourcesGood> findAll() {
		return dao.findAll();
	}

}
