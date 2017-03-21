package cn.com.qytx.hotline.outcall.service;

import cn.com.qytx.hotline.outcall.domain.OutCallSegment;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;


/**
 * 
 * 功能:外呼号码段service层
 * 版本: 1.0
 * 开发人员: 徐长江
 * 创建日期: 2014-3-6
 * 修改日期: 2014-3-6
 * 修改列表:
 */
public interface IOutCallSegment extends BaseService<OutCallSegment>
{
	 /**
	    * 
	    * 功能：根据号码段得id得到号码端实体类
	    * @param vid
	    * @return
	    */
		OutCallSegment findOutCallSegmentById(Integer vid);
	/**
	    * 
	    * 功能：根据号码段得电话得到号码端实体类
	    * @param vid
	    * @return
	    */
		OutCallSegment findOutCallSegmentByPhone(String phone);
		
		/**
		 * 
		 * 功能：保存 号码段记录
		 * @param outCallSegment
		 */
	    void saveOutCallSegment(OutCallSegment outCallSegment);
	   
		/**
		 * 
		 * 功能：删除号码段记录
		 * @param outCallSegment
		 */
	    void deleteOutCallSegmentById(Integer vid);
	   
        /**
         * 
         * 功能：分页查询
         * @param page
         * @param phone
         * @return
         */
		Page<OutCallSegment> findOutCallSegmentPageList(Pageable pageInfo,String phone);
			
		
}
