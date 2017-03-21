package cn.com.qytx.cbb.myapply.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.cbb.myapply.dao.MyStartedDao;
import cn.com.qytx.cbb.myapply.domain.MyStarted;
import cn.com.qytx.cbb.myapply.service.IMyStarted;
import cn.com.qytx.cbb.myapply.service.MyApplyService.ModuleCode;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

@Service
@Transactional
public class MyStartedImpl extends BaseServiceImpl<MyStarted> implements IMyStarted{
    @Autowired
    MyStartedDao myStartedDao;
    
	@Override
	public Page<MyStarted> findListByUserId(Pageable pageable,Integer userId) {
		return myStartedDao.findListByUserId(pageable, userId);
	}

	@Override
	public MyStarted findByInstanceId(ModuleCode code,String instanceId) {
		// TODO Auto-generated method stub
		return myStartedDao.findByModuleCodeAndInstanceId(code, instanceId);
	}
	
}
