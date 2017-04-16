package cn.com.wh.collectdevice.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.collectdevice.domain.CollectDevice;
/**
 * 功能: 采集设备
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-27
 * 修改日期: 
 * 修改列表:
 */
@Repository
public class CollectDeviceDao extends BaseDao<CollectDevice, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 获得采集设备列表
	 * @param pageable
	 * @param deviceAddress 设备地址
	 * @param companyName 企业名称
	 * @return
	 */
	public Page<CollectDevice> findCollectDeviceList(Pageable pageable,
			String keyWord) {
		String hql=" 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(keyWord)){
			hql+=" and (company.companyName like ? or deviceAddress like ?)";
			keyWord="%"+keyWord+"%";
			params.add(keyWord);
			params.add(keyWord);
		}
		return this.findAll(hql,pageable,params.toArray());
	}
}
