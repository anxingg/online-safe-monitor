package cn.com.qytx.workflow.service;

import java.util.List;

import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.workflow.domain.HotFormCategory;

/**
 * Created by izerui.com on 14-4-29.
 */

public interface FormCategoryService extends BaseService<HotFormCategory>{

    /**
     * 通过公司id和分类类别获取所有分类信息
     * @param companyId 公司id
     * @param type 分类类别 1，表单分类       2，流程分类
     * @return  分类list
     */
   List<HotFormCategory> findByTypeCompanyId(Integer companyId,Integer type);
}
