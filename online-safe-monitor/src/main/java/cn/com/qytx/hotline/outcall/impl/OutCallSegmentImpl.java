package cn.com.qytx.hotline.outcall.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.qytx.hotline.outcall.dao.OutCallSegmentDao;
import cn.com.qytx.hotline.outcall.domain.OutCallSegment;
import cn.com.qytx.hotline.outcall.service.IOutCallSegment;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

/**
 * 
 * 功能:外呼号码段得接口实现类 版本: 1.0 开发人员: 徐长江 创建日期: 2014-3-6 修改日期: 2014-3-6 修改列表:
 */
@SuppressWarnings("serial")
@Service
public class OutCallSegmentImpl extends BaseServiceImpl<OutCallSegment>
		implements IOutCallSegment, Serializable {

	@Resource(name = "outCallSegmentDao")
	private OutCallSegmentDao outCallSegmentDao;

	/**
	 * 功能：根据号码段得id得到号码端实体类
	 * 
	 * @param vid
	 * @return
	 */
	public OutCallSegment findOutCallSegmentById(Integer vid) {
		return outCallSegmentDao.findOutCallSegmentById(vid);
	}

	/**
	 * 
	 * 功能：根据号码段得电话得到号码端实体类
	 * 
	 * @param vid
	 * @return
	 */
	public OutCallSegment findOutCallSegmentByPhone(String phone) {
		return outCallSegmentDao.findOutCallSegmentByPhone(phone);
	}

	/**
	 * 
	 * 功能：保存 号码段记录
	 * 
	 * @param outCallSegment
	 */
	public void saveOutCallSegment(OutCallSegment outCallSegment) {
		outCallSegment.setCheckState(0);
		outCallSegmentDao.saveOutCallSegment(outCallSegment);
	}

	/**
	 * 
	 * 功能：删除号码段记录
	 * 
	 * @param outCallSegment
	 */
	public void deleteOutCallSegmentById(Integer vid) {
		outCallSegmentDao.deleteOutCallSegmentById(vid);
	}

	/**
	 * 
	 * 功能：分页查询
	 * 
	 * @param page
	 * @param phone
	 * @return
	 */
	public Page<OutCallSegment> findOutCallSegmentPageList(Pageable pageInfo,
			String phone) {
		return outCallSegmentDao.findOutCallSegmentPageList(pageInfo, phone);
	}

}

