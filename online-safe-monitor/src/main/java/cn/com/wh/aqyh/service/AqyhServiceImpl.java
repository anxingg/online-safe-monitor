/**
 * 
 */
package cn.com.wh.aqyh.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.aqyh.dao.WuhaiSafeDangerDao;
import cn.com.wh.aqyh.domain.WuhaiSafeDanger;

/**
 * @ClassName:     AqyhServiceImpl.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-21 上午11:20:46 
 */
@Service("aqyhServiceImpl")
public class AqyhServiceImpl extends BaseServiceImpl<WuhaiSafeDanger> implements AqyhService {
	private static Log logger = LogFactory.getLog(AqyhServiceImpl.class);
	//公司服务接口
	@Autowired
    private WuhaiSafeDangerDao wuhaiSafeDangerDao;

	public Page<WuhaiSafeDanger> queryByConPage(Pageable page,String group_id,String reportTime,String happyTime,String dept, String deptor,int data_source){
		Page<WuhaiSafeDanger> _page = null;
		try{
			_page = wuhaiSafeDangerDao.queryByConPage(page,group_id,reportTime,happyTime,dept,deptor,data_source);
		}catch(Exception e){
			logger.error("分页查询安全隐患列表出现异常："+e.toString());
			return null;
		}
		return _page;
	}
	
	public WuhaiSafeDanger queryDetail(String vid){
		WuhaiSafeDanger wuhaiSafeDanger = new WuhaiSafeDanger();
		try{
			wuhaiSafeDanger = wuhaiSafeDangerDao.findOne(Integer.parseInt(vid));
		}catch(Exception e){
			logger.error("查询安全隐患出现异常：vid="+vid+" 异常信息："+e.toString());
		}
		return wuhaiSafeDanger;
	}
	
	public int saveorup(WuhaiSafeDanger wuhaiSafeDanger){
		WuhaiSafeDanger _wuhaiSafeDanger = new WuhaiSafeDanger();
		try{
			_wuhaiSafeDanger = wuhaiSafeDangerDao.saveOrUpdate(wuhaiSafeDanger);
		}catch(Exception e){
			logger.error("新增安全隐患出现异常："+e.toString());
			return -1;
		}
		return 0;
	}
}
