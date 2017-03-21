package cn.com.wh.company.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.company.dao.WHCompanyPhotoDao;
import cn.com.wh.company.domain.WHCompanyPhoto;
import cn.com.wh.company.service.IWHCompanyPhoto;

/**
 * 功能: 企业证照
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-24
 * 修改日期: 
 * 修改列表:
 */
@Service
public class WHCompanyPhotoImpl extends BaseServiceImpl<WHCompanyPhoto> implements IWHCompanyPhoto,Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(WHCompanyPhotoImpl.class);
	
	/**
	 *  企业证照   DAO类
	 */
	@Autowired
	private WHCompanyPhotoDao wHCompanyPhotoDao;
	
	
	@Override
	public int deleteWHCompanyPerson(Integer personId) {
		
		wHCompanyPhotoDao.deleteWHCompanyPerson(personId);
		LOGGER.info("删除安全管理人员/特种作业人员/单位法人,人员id"+personId);
		return 1;
	}


	@Override
	public int updatePhone(Integer photoType,WHCompanyPhoto photo,Integer groupId) {
		try {
			if(photoType!=null){
				wHCompanyPhotoDao.deletePhoto(photoType,groupId);
				LOGGER.info("删除企业企业证照，photoType="+photoType+",groupId="+groupId);
			}
			wHCompanyPhotoDao.saveOrUpdate(photo);
			LOGGER.info("保存企业证照成功，photoId="+photo.getVid());
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public List<WHCompanyPhoto> findByGroupId(Integer groupId) {
		return wHCompanyPhotoDao.findByGroupId(groupId);
	}
	
	public WHCompanyPhoto findByType(Integer photoType,Integer groupId){
		return wHCompanyPhotoDao.findByType(photoType,groupId);
	}
	
	public void deleteImg(Integer photoType,Integer groupId){
		
		wHCompanyPhotoDao.deletePhoto(photoType,groupId);
		LOGGER.info("删除企业企业证照，photoType="+photoType+",groupId="+groupId);
		
	}

}
