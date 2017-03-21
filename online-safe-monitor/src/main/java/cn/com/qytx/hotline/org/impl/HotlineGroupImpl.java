package cn.com.qytx.hotline.org.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.org.dao.HotlineGroupDao;
import cn.com.qytx.hotline.org.service.IHotlineGroup;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.domain.GroupInfo;

/**
 * 功能:部门实现类
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2015年4月9日
 * 修改人员：
 * 修改日期: 
 * 修改列表:
 */
@Service
@Transactional
public class HotlineGroupImpl extends BaseServiceImpl<GroupInfo> implements IHotlineGroup {

	@Autowired
	private HotlineGroupDao hotlineGroupDao;
	@Override
	public GroupInfo findByName(String groupName) {
		return hotlineGroupDao.findByName(groupName);
	}

}
