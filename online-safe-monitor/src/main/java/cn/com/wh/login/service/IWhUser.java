package cn.com.wh.login.service;

import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.login.domain.WhUser;

/**
 * 
 * <br/>功能:客户端用户接口
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2016年5月1日
 * <br/>修改日期: 2016年5月1日
 * <br/>修改列表:
 */
public interface IWhUser extends BaseService<WhUser>{

	/**
	 * 通过用户编号获得用户信息
	 * @param code
	 * @return 
	 */
	public WhUser findByUserCode(String code);
}
