package cn.com.wh.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.login.dao.WhUserDao;
import cn.com.wh.login.domain.WhUser;
import cn.com.wh.login.service.IWhUser;



/**
 * 
 * <br/>功能:客户端用户接口实现
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2016年5月1日
 * <br/>修改日期: 2016年5月1日
 * <br/>修改列表:
 */
@Service
@Transactional
public class WhUserImpl extends BaseServiceImpl<WhUser> implements IWhUser{

	//客户端用户表dao
	@Autowired
	private WhUserDao whUserDao;
	
	/**
	 * 通过用户编号获得用户信息
	 * @param code
	 * @return 
	 */
	public WhUser findByUserCode(String code) {
		return whUserDao.findByUserCode(code);
	}
}
