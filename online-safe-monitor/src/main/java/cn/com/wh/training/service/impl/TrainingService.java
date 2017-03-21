package cn.com.wh.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.training.dao.TrainingDao;
import cn.com.wh.training.domain.SearchVo;
import cn.com.wh.training.domain.Training;
import cn.com.wh.training.service.ITraining;

@Service
@Transactional
public class TrainingService extends BaseServiceImpl<Training> implements ITraining{

	@Autowired
	private TrainingDao trainingDao;
	@Override
	public Page<Training> findByPage(Pageable page, SearchVo search) {
		// TODO Auto-generated method stub
		return trainingDao.findByPage(page, search);
	}

}
