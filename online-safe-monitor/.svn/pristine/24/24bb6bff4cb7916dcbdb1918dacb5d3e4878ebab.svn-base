/** 
 * @author 作者姓名  E-mail: email地址 
 * @version 创建时间：2015-8-27 下午03:00:06 
 * 类说明 
 */
package cn.com.wh.yjyl.service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.yjyl.domain.WHContingencyPlansExe;


/**
 * @ClassName:     YjylService.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-27 下午03:00:06 
 */
public interface YjylService extends BaseService<WHContingencyPlansExe>{
	public Page<WHContingencyPlansExe> queryByConPage(Pageable page, String groupId,String exercise_name, int plan_type,int plan_no);
	
	public WHContingencyPlansExe queryDetail(int vid);
	
}
