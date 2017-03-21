package cn.com.wh.fee.service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.fee.domain.FeeExtract;
import cn.com.wh.fee.domain.SearchVo;

public interface IFeeExtract {

	Page<FeeExtract> findByPage(Pageable pageable, SearchVo sv);
	
	FeeExtract saveOrUpdate(FeeExtract feeExtract);
	
	FeeExtract findOne(Integer id);
}
