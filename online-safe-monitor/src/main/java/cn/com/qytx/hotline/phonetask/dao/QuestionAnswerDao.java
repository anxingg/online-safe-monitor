package cn.com.qytx.hotline.phonetask.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.com.qytx.hotline.phonetask.domain.PhoneTaskUser;
import cn.com.qytx.hotline.phonetask.domain.QuestionAnswer;
import cn.com.qytx.platform.base.dao.BaseDao;


/**
 * 功能:试题的dao
 * 版本: 1.0
 * 开发人员: 李立泼
 * 创建日期: 2014-9-19
 * 修改人: pxd
 * 修改日期: 2015-2-6
 * 修改列表:
 */
@Repository("questionAnswerDao")
public class QuestionAnswerDao extends BaseDao<QuestionAnswer, Integer>
{
    /**
     * 功能：获取试卷的回访答案
     * @param userId 回访人id
     * @param questionnaireId 试卷id
     * @return
     */
    
    public List<QuestionAnswer> getUserAnswers(Integer userId, Integer questionnaireId)
    {
        String hql = " isDelete = 0 and createUser.userId = ? and questionnaire.id = ? ";
        return super.findAll(hql, userId, questionnaireId);
    }

    /**
     * 功能：保存试卷的回访结果
     * @param list
     */
    public void saveAnswers(List<QuestionAnswer> list)
    {

        for (QuestionAnswer qa : list)
        {
            super.saveOrUpdate(qa);
        }
    }

    /**
     * 功能：返回某个回访对象对某个试卷的回访的答案
     * @param userInfo 被回访人
     * @param questionnaireId 试卷id
     * @return
     */
    public Map<String, String> findAnswerMap(PhoneTaskUser userInfo, Integer questionnaireId)
    {
        String hql = "select question.id,answer from QuestionAnswer where createUser.vid=?1 and questionnaire.id = ?2";
        @SuppressWarnings("unchecked")
        List<Object[]> list = super.entityManager.createQuery(hql).setParameter(1, userInfo.getVid()).setParameter(2, questionnaireId).getResultList();
        Map<String, String> returnMap = new HashMap<String, String>();
        if (list != null && list.size() > 0)
        {
            for (Object[] obj : list)
            {
                returnMap.put(obj[0].toString(), obj[1].toString());
            }
        }
        return returnMap;
    }
}
