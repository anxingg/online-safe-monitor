package cn.com.wh.login.dao;

import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.wh.login.domain.WhUser;

/**
 * 
 * <br/>功能:客户端用户表数据库操作类DAO
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2016年5月1日
 * <br/>修改日期: 2016年5月1日
 * <br/>修改列表:
 */
@Repository("whUserDao")
public class WhUserDao extends BaseDao<WhUser,String>{

	public WhUser findByUserCode(String userCode){
		WhUser whUser = super.findOne("code=?", userCode);
		return whUser;
	}
}
