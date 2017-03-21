package cn.com.qytx.workflow.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.workflow.domain.HotFormCategory;

/**
 * 分类Dao
 * 版本: 1.0
 * 开发人员: 吴洲
 * 创建日期: 2013-3-25
 * 修改日期: 2013-3-25
 * 修改列表:
 */
@Repository("hotformCategoryDao")
public class HotFormCategoryDao extends BaseDao<HotFormCategory, Integer> implements Serializable{
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 通过分类id查找分类信息
	 * @param id 分类id
	 * @return 表单属性对象
	 */
	public HotFormCategory findById(final Integer id){
		return findOne("categoryId=?", id);
	}
	/**
	 * 通过公司id和分类类别查找所有分类
	 * @param companyId 公司id
	 * @param type 分类类别
	 * @return 分类对象list
	 */
	public List<HotFormCategory> findAll(final Integer companyId,final Integer type){
	    if(companyId!=null){
	        return findAll("companyId=? and type=?",new Sort(new Sort.Order(Direction.ASC, "orderIndex")), companyId ,type);
	    }else{
	        javax.persistence.Query query = super.entityManager.createQuery("from HotFormCategory where  type=?1 order by orderIndex desc");
	        query.setParameter(1, type);
	        return query.getResultList();
	    }
	}
	/**
	 * 通过分类id删除分类信息
	 * @param id 分类id
	 */
	@SuppressWarnings("deprecation")
	public void delete(final Integer id){
		//String hql = "delete from HotFormCategory where categoryId=?";
		super.bulkDelete("delete from HotFormCategory where categoryId=?", id);
	}
	/**
	 * 通过公司id和分类类别得到分页
	 * @param page
	 * @param companyId 公司id
	 * @param type 分类类别
	 * @return 分页
	 */
	@SuppressWarnings("deprecation")
	public Page<HotFormCategory> findByPage(final Pageable page, final Integer companyId, final Integer type){
		return super.findByPage(page,"select f from HotFormCategory f where companyId=? and type=? order by orderIndex",companyId,type);
	}
	/**
	 * 通过公司id和分类类别得到分类总数
	 * @param companyId 公司id
	 * @param type 分类类别
	 * @return 分类数量
	 */
	public Integer getTotalCount(final Integer companyId, final Integer type){
		return	count("companyId=? and type=?", companyId,type);
	}
	/**
	 * 通过公司id，分类类别和分类名称查找分类
	 * @param companyId 公司id
	 * @param type  分类类别 1，表单分类     2.流程分类
	 * @param categoryName 分类名称
	 * @return 分类list
	 */
	public List<HotFormCategory> findCategoryByCategoryName(final Integer companyId,
			final Integer type,final String categoryName){
		
		List<HotFormCategory> list = null;
		if(type != null && type.intValue()>0){
			list =  findAll("companyId=? and type=? and categoryName=?",companyId,type,categoryName);
		}
		
		return list;
	}
}
