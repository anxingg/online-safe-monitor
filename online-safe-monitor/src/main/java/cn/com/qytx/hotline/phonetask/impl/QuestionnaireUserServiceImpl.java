package cn.com.qytx.hotline.phonetask.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.phonetask.dao.QuestionnaireUserDao;
import cn.com.qytx.hotline.phonetask.domain.QuestionnaireUser;
import cn.com.qytx.hotline.phonetask.service.IQuestionnaireUserService;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能:试卷用户接口
 * 版本: 1.0
 * 开发人员: 彭小东
 * 创建日期: 2015-2-6
 * 修改日期: 2015-2-6
 * 修改列表:
 */
@Service("questionnaireUserServiceImpl")
@Transactional
public class QuestionnaireUserServiceImpl extends BaseServiceImpl<QuestionnaireUser> implements
        IQuestionnaireUserService
{

    private static final long serialVersionUID = 1L;
    /**
     * 试卷用户dao
     */
    @Resource(name = "questionnaireUserDao")
    private transient QuestionnaireUserDao questionnaireUserDao;

    /**
     * 功能： 分页查询时间调查用户表
     * @param pageable
     * @param beginTime   开始时间
     * @param endTime   结束时间
     * @param searchkey   关键字
     * @param questionnaireId    试卷id
     * @param userinfo    用户
     * @return
     */
    @Override
    public Page<QuestionnaireUser> totalList(Pageable pageable, String beginTime, String endTime, String searchkey,
            Integer questionnaireId, UserInfo userinfo, int state)
    {
        return questionnaireUserDao
                .totalList(pageable, beginTime, endTime, searchkey, questionnaireId, userinfo, state);
    }

    /**
     * 功能：统计某个执行坐席对 某个调查问卷的回访量
     * @param userId 执行坐席id
     * @param questionnareId 试卷id
     * @return
     */
    public Integer countquestionUser(Integer seatId, Integer questionnareId)
    {
        return questionnaireUserDao.countquestionUser(seatId, questionnareId);
    };

    /**
     * 功能: 获取用户调查表
     * @param id 试卷id
     * @return
     */
    public List<QuestionnaireUser> getUsersByQusetionireId(Integer id)
    {
        return questionnaireUserDao.getUsersByQusetionireId(id);
    }


    /**
     * 功能： 获取用户调查表
     * @param userId    根据本调查人id
     * @param questionnareId      试卷id
     * @return
     */
    public QuestionnaireUser getQuestionnaireUserBySurveyUserId(Integer userId, Integer questionnareId)
    {
        return questionnaireUserDao.getQuestionnaireUserBySurveyUserId(userId, questionnareId);
    };

}
