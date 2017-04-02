package cn.com.wh.thresholdtemplate.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.thresholdtemplate.domain.ThresholdTemplate;

/**
 * 功能: 应急预案
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-27
 * 修改日期: 
 * 修改列表:
 */
@Repository
public class ThresholdTemplateDao extends BaseDao<ThresholdTemplate, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;

	public Page<ThresholdTemplate> findThresholdTemplateByPage(Pageable pageable,String watchType,String keyWord)
	{
		String hql = " isDelete = 0 ";
        List<Object> params = new ArrayList<Object>();
        
    	if (watchType!=null && watchType.compareTo("-1")!=0)
        {
            hql += " and watchType = ?";
            params.add(watchType);
        }
    	
    	if (!StringUtils.isEmpty(keyWord))
        {
            hql += " and templateName = ?";
            params.add(keyWord);
        }
   
        return super.findAll(hql, pageable, params.toArray());		
	}
}
