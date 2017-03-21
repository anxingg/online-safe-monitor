package cn.com.wh.fee.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.company.dao.WHCompanyDao;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.fee.dao.FeeExtractDao;
import cn.com.wh.fee.domain.FeeExtract;
import cn.com.wh.fee.domain.SearchVo;
import cn.com.wh.fee.service.IFeeExtract;


@Service
@Transactional
public class FeeExtractImpl implements IFeeExtract {

	@Resource 
	FeeExtractDao dao;
	
	@Resource
	WHCompanyDao wHCompanyDao;

	@Override
	public Page<FeeExtract> findByPage(Pageable pageable, SearchVo sv) {
		return dao.findByPage(pageable, sv);
	}

	@Override
	public FeeExtract saveOrUpdate(FeeExtract feeExtract) {
		FeeExtract fe = dao.saveOrUpdate(feeExtract);
		//修改公司的总额和余额
		WHCompany whcompany = wHCompanyDao.findByGroupId(feeExtract.getGroupId());
		Double safetyConsumerMoney = whcompany.getSafetyConsumerMoney();//总消耗
		//设置公司余额
		whcompany.setSafetySurplusMoney(fe.getRemainingSum());
		//总额 ＝ 余额 + 总消耗
		whcompany.setSafetyAllMoney(whcompany.getSafetySurplusMoney() + (safetyConsumerMoney == null ? 0 : safetyConsumerMoney));
		wHCompanyDao.saveOrUpdate(whcompany);
		
		return fe;
	}

	@Override
	public FeeExtract findOne(Integer id) {
		return dao.findOne(id);
	}
}
