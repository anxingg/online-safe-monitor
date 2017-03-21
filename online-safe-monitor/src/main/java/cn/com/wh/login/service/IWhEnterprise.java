package cn.com.wh.login.service;

import java.util.List;

import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.login.domain.WhEnterprise;
import cn.com.wh.login.domain.WhUser;

/**
 * 
 * <br/>功能:客户端企业接口
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2016年5月1日
 * <br/>修改日期: 2016年5月1日
 * <br/>修改列表:
 */
public interface IWhEnterprise extends BaseService<WhEnterprise>{

	/**
	 * 通过企业编号获得企业信息
	 * @param code
	 * @return 
	 */
	public WhEnterprise findByEnterpriseCode(String code);
	
	/**
	 * 通过企业id字符串获得所有企业列表
	 * @param codes
	 * @return
	 */
	public List<WhEnterprise> findListByEnterpriseCode(String codes);
}
