package cn.com.wh.process.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.process.dao.TechnologicalProcessDao;
import cn.com.wh.process.domain.TechnologicalProcess;
import cn.com.wh.process.service.ITechnologicalProcess;
import cn.com.wh.reliefgoods.domain.SearchVo;


@Service
@Transactional
public class TechnologicalProcessImpl extends BaseServiceImpl<TechnologicalProcess> implements ITechnologicalProcess{

	//工艺流程dao
	@Autowired
	private TechnologicalProcessDao technologicalProcessDao;

	@Override
	public Page<TechnologicalProcess> findByPage(Pageable page,
			SearchVo search) {
		return technologicalProcessDao.findByPage(page, search);
	}

	
}
