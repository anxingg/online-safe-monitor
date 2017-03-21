package cn.com.qytx.hotline.phonetask.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.phonetask.dao.PhoneTaskUserDao;
import cn.com.qytx.hotline.phonetask.dao.QuestionAnswerDao;
import cn.com.qytx.hotline.phonetask.dao.QuestionDao;
import cn.com.qytx.hotline.phonetask.dao.QuestionnaireUserDao;
import cn.com.qytx.hotline.phonetask.domain.PhoneTaskUser;
import cn.com.qytx.hotline.phonetask.domain.QuestionAnswer;
import cn.com.qytx.hotline.phonetask.domain.QuestionAnswerVO;
import cn.com.qytx.hotline.phonetask.domain.Questionnaire;
import cn.com.qytx.hotline.phonetask.domain.QuestionnaireUser;
import cn.com.qytx.hotline.phonetask.service.IPhoneTask;
import cn.com.qytx.hotline.phonetask.service.IQuestionAnswerService;
import cn.com.qytx.hotline.phonetask.service.IQuestionnaireService;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.domain.UserInfo;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

@Service
@Transactional
public class QuestionAnswerServiceImpl extends BaseServiceImpl<QuestionAnswer> implements IQuestionAnswerService
{
    /**
     * 外呼任务接口
     */
    @Autowired
    IPhoneTask phoneTaskService;
    /**
     * 问题回答结果dao
     */
    @Resource(name = "questionAnswerDao")
    QuestionAnswerDao questionAnswerDao;
    /**
     * 电话任务用户dao
     */
    @Resource(name = "phoneTaskUserDao")
    private PhoneTaskUserDao phoneTaskUserDao;
    /**
     * 试卷处理接口
     */
    @Resource(name = "questionnaireService")
    IQuestionnaireService questionnaireService;
    /**
     * 试卷dao
     */
    @Resource(name = "questionnaireUserDao")
    private QuestionnaireUserDao questionnaireUserDao;
    /**
     * 问题dao
     */
    @Resource(name = "questionDao")
    private QuestionDao questionDao;

    /**
     * 功能：获取试卷的回访答案
     * @param userId 回访人id
     * @param questionnaireId 试卷id
     * @return
     */

    public List<QuestionAnswer> getUserAnswers(Integer userId, Integer questionnaireId)
    {
        return questionAnswerDao.getUserAnswers(userId, questionnaireId);
    }


    /**
     * 功能：返回某个回访对象对某个试卷的回访的答案
     * @param userInfo 被回访人
     * @param questionnaireId 试卷id
     * @return
     */
    @Transactional(readOnly = true)
    public Map<String, String> findAnswerMap(PhoneTaskUser userInfo, Integer questionnaireId)
    {
        return questionAnswerDao.findAnswerMap(userInfo, questionnaireId);
    }

    /**
     * 功能：保存问题答案
     * @param list 问题答案
     * @param questionnaireId 试卷id
     * @param userInfo 执行坐席
     */
    @Override
    public void saveAllAnswer(List<QuestionAnswer> list, Integer questionnaireId, UserInfo userInfo)
    {

        if (list == null || list.isEmpty())
        {
            ;
        }
        else
        {
            for (QuestionAnswer qa : list)
            {
                qa.setIsDelete(0);
                qa.setUpdateUser(userInfo);
                qa.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                Questionnaire questionnaire = questionnaireService.load(questionnaireId);
                qa.setQuestionnaire(questionnaire);
            }
            questionAnswerDao.saveAnswers(list);
        }
    }

    /**
     * 功能：保存问题答案
     * @param phoneUserid 电话任务用户id
     * @param questionAnswerArrayJSONStr 问题答案json串
     * @param userInfo 执行坐席
     * @param questionnaireId 试卷id
     * @param phontTaskId 外呼任务id
     * @param phoneState 电话任务状态
     * @param qnu 试卷用户
     */
    public void saveAllAnswer(Integer phoneUserid, String questionAnswerArrayJSONStr, UserInfo userInfo,
            Integer questionnaireId, Integer phontTaskId, int phoneState, QuestionnaireUser qnu)
    {
        PhoneTaskUser ptu = phoneTaskUserDao.findOne(phoneUserid);
        // 解析答案
        if (questionAnswerArrayJSONStr != null && !"".equals(questionAnswerArrayJSONStr))
        {
            Gson gson = new Gson();
            @SuppressWarnings("serial")
            List<QuestionAnswerVO> answerlist = gson.fromJson(questionAnswerArrayJSONStr,
                    new TypeToken<List<QuestionAnswerVO>>()
                    {
                    }.getType());
            List<QuestionAnswer> qaList = new ArrayList<QuestionAnswer>();
            if (answerlist != null && !answerlist.isEmpty())
            {
                for (QuestionAnswerVO qav : answerlist)
                {
                    QuestionAnswer qa = new QuestionAnswer();
                    qa.setQuestion(questionDao.findOne(qav.getQuestionId()));
                    qa.setAnswer(qav.getAnswer());
                    qa.setCreateUser(ptu);
                    qaList.add(qa);
                }
                this.saveAllAnswer(qaList, questionnaireId, userInfo);// 更新试卷答案
            }

        }
        phoneTaskService.updatePhoneTaskCount(phontTaskId, phoneState);// 更新外呼任务
        qnu.setSurveyUser(ptu);
        questionnaireUserDao.saveOrUpdate(qnu);// 更新外呼用户信息

    }

}
