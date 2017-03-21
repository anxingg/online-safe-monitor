package cn.com.qytx.hotline.phonetask.dao;

import org.springframework.stereotype.Repository;

import cn.com.qytx.hotline.phonetask.domain.Questionnaire;
import cn.com.qytx.platform.base.dao.BaseDao;

/**
 * 功能:试卷的dao
 * 版本: 1.0
 * 开发人员: 李立泼
 * 创建日期: 2014-9-19
 * 修改人: pxd
 * 修改日期: 2015-2-6
 * 修改列表:
 */
@Repository("questionnaireDao")
public class QuestionnaireDao extends BaseDao<Questionnaire, Integer>
{
    /**
     * 功能：加载试卷
     * @param id 试卷id
     * @return
     */
    public Questionnaire load(Integer id)
    {
        return super.findOne(id);
    }

    /**
     * 功能：保存试卷
     * @param questionnaire 试卷实体
     */
    public void save(Questionnaire questionnaire)
    {
        super.saveOrUpdate(questionnaire);
    }

    /**
     * 功能:更新试卷
     * @param qaire 试卷对象
     * @param id 试卷id
     */
    public void update(Questionnaire qaire, Integer id)
    {
        String hql = "update Questionnaire set title=?1 ,des =?2,state=?3 where id = ?4";
        super.executeQuery(hql, qaire.getTitle(), qaire.getDes(), qaire.getState(), id);
    }

}
