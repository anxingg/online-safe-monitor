package cn.com.qytx.workflow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.workflow.dao.HotFormCategoryDao;
import cn.com.qytx.workflow.domain.HotFormCategory;
import cn.com.qytx.workflow.service.FormCategoryService;

/**
 * Created by izerui.com on 14-4-29.
 */
@Service("hotformCategoryService")
@Transactional
public class FormCategoryServiceImpl extends BaseServiceImpl<HotFormCategory> implements FormCategoryService {

    /**分类设置 */
    @Resource(name = "hotformCategoryDao")
    HotFormCategoryDao formCategoryDao;




    @Override
    public List<HotFormCategory> findByTypeCompanyId(Integer companyId, Integer type) {
        return formCategoryDao.findAll(companyId, type);
    }
}
