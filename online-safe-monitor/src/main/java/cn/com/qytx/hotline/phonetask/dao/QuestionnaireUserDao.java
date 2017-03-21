package cn.com.qytx.hotline.phonetask.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.qytx.hotline.phonetask.domain.QuestionnaireUser;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能:用户答复试卷dao
 * 版本: 1.0
 * 开发人员: 彭晓东
 * 创建日期: 2014-2-12
 * 修改日期: 2014-2-12
 * 修改列表:
 */
@Repository("questionnaireUserDao")
public class QuestionnaireUserDao extends BaseDao<QuestionnaireUser, Integer>
{
    /**
     * 功能：查询用户答复试卷
     * @param pageable
     * @param beginTime 查询开始时间
     * @param endTime 查询结束时间
     * @param searchkey 查询关键字
     * @param questionnaireId 试卷id
     * @param userinfo 执行坐席
     * @param state 状态
     * @return
     */
    public Page<QuestionnaireUser> totalList(Pageable pageable, String beginTime, String endTime, String searchkey,
            Integer questionnaireId, UserInfo userinfo, int state)
    {
        String hql = "isDelete = 0 ";
        if (beginTime != null && !beginTime.equals(""))
        {
            hql += " and endTime>='" + beginTime + " 00:00:00'";
        }
        if (endTime != null && !endTime.equals(""))
        {
            hql += " and endTime<='" + endTime + " 23:59:59'";
        }
        if (userinfo != null && userinfo.getUserId() != null)
        {
            hql += "and inquirerUser.userId=" + userinfo.getUserId();
        }
        if (searchkey != null && !searchkey.equals(""))
        {
            hql += " and ( inquirerUser.loginName like '%" + searchkey + "%' or surveyUser.phone like '%" + searchkey
                    + "%')";
        }
        hql += " and questionnaire.id=" + questionnaireId;
        if (state > 0)
        {
            hql += " and statue=" + state;
        }
        return super.dataFilter().findAll(hql, pageable);
    }

    /**
     * 功能：统计某个试卷回访的人数
     * @param seatId
     * @param questionnareId
     * @return
     */
    public Integer countquestionUser(Integer seatId, Integer questionnareId)
    {
        String hql = " isDelete = 0 and  questionnaire.id=?1";
        if (seatId != null && seatId > 0)
        {
            hql = hql + " and inquirerUser.userId=?2";
            return super.dataFilter().count(hql, questionnareId, seatId);
        }
        return super.dataFilter().count(hql, questionnareId);
    }

    /**
     * 功能：根据试卷id查询所有的答复用户
     * @param id
     * @return
     */
    public List<QuestionnaireUser> getUsersByQusetionireId(Integer id)
    {
        return super.dataFilter().findAll("questionnaire.id=?1 ", id);
    }

    /**
     * 功能：
     * @param userId
     * @param questionnareId
     * @return
     */
    public QuestionnaireUser getQuestionnaireUserBySurveyUserId(Integer userId, Integer questionnareId)
    {
        String hql = " isDelete = 0 and  questionnaire.id=?1 and inquirerUser.userId=?2";
        return super.findOne(hql, questionnareId, userId);

    }

}
