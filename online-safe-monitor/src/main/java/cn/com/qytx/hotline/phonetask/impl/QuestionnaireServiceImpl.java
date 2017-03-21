package cn.com.qytx.hotline.phonetask.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.phonetask.dao.QuestionnaireDao;
import cn.com.qytx.hotline.phonetask.domain.Question;
import cn.com.qytx.hotline.phonetask.domain.Questionnaire;
import cn.com.qytx.hotline.phonetask.service.IQuestionService;
import cn.com.qytx.hotline.phonetask.service.IQuestionnaireService;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

/**
 * 功能:试卷服务接口
 * 版本: 1.0
 * 开发人员: 彭小东
 * 创建日期: 2015-2-6
 * 修改日期: 2015-2-6
 * 修改列表:
 */
@Service("questionnaireService")
@Transactional
public class QuestionnaireServiceImpl extends BaseServiceImpl<Questionnaire> implements IQuestionnaireService
{

    private static final long serialVersionUID = 7196494430061274047L;
    /**
     * 试卷dao
     */
    @Resource(name = "questionnaireDao")
    transient QuestionnaireDao questionnaireDao;
    /**
     * 问题业务接口
     */
    @Resource(name = "questionService")
    IQuestionService questionService;

    /**
     * 保存和跟新试卷
     * 功能：
     * @param questionnaire
     * @return
     */
    @Override
    public Questionnaire saveOrUpdate(Questionnaire questionnaire)
    {
        if (questionnaire == null)
        {
            return null;
        }
        else if (questionnaire.getId() == null)
        {
            questionnaire.setIsDelete(0);
            return questionnaireDao.saveOrUpdate(questionnaire);
        }
        else
        {
            Questionnaire oldQN = load(questionnaire.getId());
            List<Question> oldQList = oldQN.getQuestions();
            if (oldQList != null)
            {
                for (Question oldQ : oldQList)
                {
                    questionService.deleteAndItemById(oldQ.getId());
                }
            }
            oldQN.setQuestions(questionnaire.getQuestions());
            return questionnaireDao.saveOrUpdate(oldQN);
        }
    }

    /**
     * 功能：查找试卷
     * @param id
     * @return
     */
    @Override
    public Questionnaire load(Integer id)
    {
        return questionnaireDao.load(id);
    }

    /**
     * 功能：更新试卷
     * @param qaire
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Questionnaire qaire, Integer id)
    {
        questionnaireDao.update(qaire, id);
    }

    /**
     * 功能：查找试卷
     * @param id
     * @return
     */
    @Override
    public Questionnaire serializeLoadQuestionnaire(Integer id)
    {
        return questionnaireDao.findOne(id);
    }

}
