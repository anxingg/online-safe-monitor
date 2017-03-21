package cn.com.wh.company.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.company.domain.WHCompanyProduct;

/**
 * 功能: 企业产品
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-25
 * 修改日期: 
 * 修改列表:
 */
@Repository
public class WHCompanyProductDao extends BaseDao<WHCompanyProduct, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;


	public Page<WHCompanyProduct> findByPage(Pageable pageable,Integer groupId,Integer materialType,String materialName){
		String hql = " isDelete = 0 ";
        List<Object> params = new ArrayList<Object>();
        
        if (groupId!=null && groupId!=-1)
        {
            hql += " and groupId = ?";
            params.add(groupId);
        }
       
        if (materialType!=null && materialType!=-1)
        {
            hql += " and materialType = ?";
            params.add(materialType);
        }
        
        if (!StringUtils.isEmpty(materialName))
        {
        	hql += " and materialName like ?";
        	params.add("%" + materialName + "%");
        }
        return super.findAll(hql, pageable, params.toArray());  
	}
}
