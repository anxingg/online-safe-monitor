package cn.com.wh.company.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.wh.company.domain.WHCompanyPhoto;

/**
 * 功能: 企业证照
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-24
 * 修改日期: 
 * 修改列表:
 */
@Repository
public class WHCompanyPhotoDao extends BaseDao<WHCompanyPhoto, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;


	/**
	 * @功能 根据人员ID  删除人员
	 * @param personId
	 * @return 
	 */
	public void deleteWHCompanyPerson(Integer personId) {
		
//		String sql = "update tb_wuhai_company_person set is_delete = 1 where person_id = " + personId;
//		javax.persistence.Query query = super.entityManager.createNativeQuery(sql);
//		query.executeUpdate();
	}
	
	public void deletePhoto(Integer photoType,Integer groupId){
		String sql = "update tb_wuhai_company_photo set is_delete = 1 where photo_type = " + photoType +" and group_id = "+groupId;
		javax.persistence.Query query = super.entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	/**
	 * 根据groupId查询公司证照
	 * @param groupId
	 * @return
	 */
	public List<WHCompanyPhoto> findByGroupId(Integer groupId){
		
		if(groupId==null){
			return null;
		}
		String hql = " isDelete = 0 ";
        List<Object> params = new ArrayList<Object>();
        
        hql += " and groupId = ?";
    	params.add(groupId);
        
    	return super.findAll(hql,new Sort(new Sort.Order(Sort.Direction.ASC, "photoType")), params.toArray());
		
	}
	
	
	
	
	public WHCompanyPhoto findByType(Integer photoType,Integer groupId){
		if(groupId==null){
			return null;
		}
		String hql = " isDelete = 0 ";
        List<Object> params = new ArrayList<Object>();
        
        hql += " and groupId = ?";
    	params.add(groupId);
    	if(photoType!=null){
    		hql += " and photoType = ?";
        	params.add(photoType);
		}
    	
    	return super.findOne(hql, params.toArray());
    	
	}
	

}
