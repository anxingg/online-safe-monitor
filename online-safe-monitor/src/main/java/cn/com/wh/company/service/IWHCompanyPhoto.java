package cn.com.wh.company.service;

import java.util.List;

import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.company.domain.WHCompanyPhoto;



/**
 * @版本: 1.0 
 * @开发人员:吴胜光
 * @功能 IWHCompanyPhoto接口类
 * @创建时间 2015-08-24
 * @修改时间
 * @修改列表
 */
public interface IWHCompanyPhoto extends BaseService<WHCompanyPhoto> {

	
	/**
	 * 删除WHCompanyPerson 对象
	 * @param personId  人员ID
	 * @return  影响的行数
	 */
	public int deleteWHCompanyPerson(Integer personId);
	
	
	public int updatePhone(Integer photoType,WHCompanyPhoto photo,Integer groupId);
	
	public List<WHCompanyPhoto> findByGroupId(Integer groupId);
	
	public WHCompanyPhoto findByType(Integer photoType,Integer groupId);
	
	public void deleteImg(Integer photoType,Integer groupId);
}
