package cn.com.wh.fee.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.company.dao.WHCompanyDao;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.fee.dao.FeeUsedDao;
import cn.com.wh.fee.domain.FeeUsed;
import cn.com.wh.fee.domain.SearchVo;
import cn.com.wh.fee.service.IFeeUsed;


@Service
@Transactional
public class FeeUsedImpl extends BaseServiceImpl<FeeUsed> implements IFeeUsed{

	//救援物资dao
	@Autowired
	private FeeUsedDao feeUsedDao;
	
	@Resource
	WHCompanyDao wHCompanyDao;

	/**
	 * 
	 * 功能：公司年度使用统计
	 * @param search
	 * @return
	 */
	public Map<Integer,Map<Integer, Double>> findStatistic(SearchVo search){
		return feeUsedDao.findStatistic(search);
	}
	
	
	public FeeUsed saveOrUpdate(FeeUsed feeUsed) {
		FeeUsed fu = feeUsedDao.saveOrUpdate(feeUsed);
		Integer plus = fu.getPlus();
		//修改公司的总额和余额
		WHCompany whcompany = wHCompanyDao.findByGroupId(feeUsed.getGroupId());
		Double safetyConsumerMoney = whcompany.getSafetyConsumerMoney();//总消耗
		//设置公司余额
		whcompany.setSafetySurplusMoney(fu.getRemainingSum());
		
		if(plus == 0){
			//设置公司总消耗
			whcompany.setSafetyConsumerMoney((safetyConsumerMoney == null ? 0 : safetyConsumerMoney) + fu.getUseMoney());
		}else {
			//设置公司总消耗
			whcompany.setSafetyConsumerMoney((safetyConsumerMoney == null ? 0 : safetyConsumerMoney) - fu.getUseMoney());
		}
		//总额 ＝ 余额 + 总消耗
		whcompany.setSafetyAllMoney(whcompany.getSafetySurplusMoney() + whcompany.getSafetyConsumerMoney());
		wHCompanyDao.saveOrUpdate(whcompany);
		
        return fu;
    }


	@Override
	public Page<FeeUsed> findByPage(Pageable pageable, SearchVo sv) {
		return feeUsedDao.findByPage(pageable, sv);
	}
}
