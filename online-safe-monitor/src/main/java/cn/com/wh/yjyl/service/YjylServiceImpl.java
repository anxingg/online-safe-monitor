/** 
 * @author 作者姓名  E-mail: email地址 
 * @version 创建时间：2015-8-27 下午03:01:07 
 * 类说明 
 */
package cn.com.wh.yjyl.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.yjyl.dao.WHContingencyPlansExeDao;
import cn.com.wh.yjyl.domain.WHContingencyPlansExe;


/**
 * @ClassName:     YjylServiceImpl.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-27 下午03:01:07 
 */
@Service("yjylServiceImpl")
public class YjylServiceImpl extends BaseServiceImpl<WHContingencyPlansExe> implements YjylService {
	
	@Autowired
    public WHContingencyPlansExeDao wHContingencyPlansExeDao;
	
	private static Log logger = LogFactory.getLog(YjylServiceImpl.class);
	/* 
	 * @see cn.com.wh.yjyl.service.YjylService#queryByConPage(cn.com.qytx.platform.base.query.Pageable, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public Page<WHContingencyPlansExe> queryByConPage(Pageable page,
			String groupId, String exerciseName, int planType, int planNo) {
		// TODO Auto-generated method stub
		Page<WHContingencyPlansExe> _page = null;
		try{
			_page = wHContingencyPlansExeDao.queryByConPage(page, groupId,exerciseName, planType,planNo);
		}catch(Exception e){
			logger.error("分页查询应急演练列表出现异常："+e.toString());
			return null;
		}
		return _page;
	}
	
	
	public WHContingencyPlansExe queryDetail(int vid){
		WHContingencyPlansExe wHContingencyPlansExe = new WHContingencyPlansExe();
		try{
			wHContingencyPlansExe = wHContingencyPlansExeDao.findOne(vid);
		}catch(Exception e){
			logger.error("查询应急演练列表出现异常："+e.toString());
			return null;
		}
		return wHContingencyPlansExe;
	};

}
