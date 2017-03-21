package cn.com.wh.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.login.dao.WhEnterpriseDao;
import cn.com.wh.login.domain.WhEnterprise;
import cn.com.wh.login.service.IWhEnterprise;



/**
 * 
 * <br/>功能:客户端企业接口实现
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2016年5月1日
 * <br/>修改日期: 2016年5月1日
 * <br/>修改列表:
 */
@Service
@Transactional
public class WhEnterpriseImpl extends BaseServiceImpl<WhEnterprise> implements IWhEnterprise{

	//客户端企业表dao
	@Autowired
	private WhEnterpriseDao whEnterpriseDao;
	
	/**
	 * 通过企业编号获得企业信息
	 * @param code
	 * @return 
	 */
	public WhEnterprise findByEnterpriseCode(String code) {
		return whEnterpriseDao.findByEnterpriseCode(code);
	}
	
	/**
	 * 通过企业id字符串获得所有企业列表
	 * @param codes
	 * @return
	 */
	public List<WhEnterprise> findListByEnterpriseCode(String codes){
		return whEnterpriseDao.findListByEnterpriseCode(codes);
	}
}
