package cn.com.qytx.hotline.phonetask.dao;

import org.springframework.stereotype.Repository;

import cn.com.qytx.hotline.phonetask.domain.Question;
import cn.com.qytx.platform.base.dao.BaseDao;

/**
 * 
 * 功能:试题处理dao 
 * 版本: 1.0 
 * 开发人员: 彭晓东 
 * 创建日期: 2014-2-12 
 * 修改日期: 2014-2-12 
 * 修改列表:
 */
@Repository("questionDao")
public class QuestionDao extends BaseDao<Question, Integer>
{
    /**
     * 功能：根据试卷id删除所有的问题 
     * @param questionnareId
     */
    public void deleteByQuestionnaireId(Integer questionnareId)
    {
        String hql = "delete from Question where questionnaire.id = ?1";
        super.executeQuery(hql, questionnareId);
    }
/**
 * 
 * 功能：假删除问题
 * @param id
 */
    public void delete(int id)
    {
        super.delete(id, true);
    }
}
