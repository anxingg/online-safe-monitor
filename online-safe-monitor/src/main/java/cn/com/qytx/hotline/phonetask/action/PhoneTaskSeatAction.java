package cn.com.qytx.hotline.phonetask.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.hotline.mis.dao.PhoneAttributionDao;
import cn.com.qytx.hotline.mis.domain.PhoneAttribution;
import cn.com.qytx.hotline.phonetask.domain.PhoneTask;
import cn.com.qytx.hotline.phonetask.domain.PhoneTaskUser;
import cn.com.qytx.hotline.phonetask.domain.Questionnaire;
import cn.com.qytx.hotline.phonetask.domain.QuestionnaireUser;
import cn.com.qytx.hotline.phonetask.service.IPhoneTask;
import cn.com.qytx.hotline.phonetask.service.IPhoneTaskUser;
import cn.com.qytx.hotline.phonetask.service.IQuestionAnswerService;
import cn.com.qytx.hotline.phonetask.service.IQuestionService;
import cn.com.qytx.hotline.phonetask.service.IQuestionnaireService;
import cn.com.qytx.hotline.phonetask.service.IQuestionnaireUserService;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能:坐席端外呼任务-跳转到执行页面,执行提交
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-9-22
 * 修改人: pxd
 * 修改日期: 2015-2-6
 * 修改列表:
 */
public class PhoneTaskSeatAction extends BaseActionSupport
{

    /**
     * 描述含义
     */
    private static final long serialVersionUID = 1L;
    
    private static final String ID = "(ID:";
    
    /**
     * log4j日志
     */
    private final static MonitorLogger logger =new Log4jImpl(PhoneTaskSeatAction.class);
    /**
     * 外呼任务用户接口
     */
    @Autowired
    transient IPhoneTaskUser phoneTaskUserService;
    /**
     * 外呼任务接口
     */
    @Autowired
    transient IPhoneTask phoneTaskService;
    /**
     * 答案接口
     */
    @Autowired
    transient IQuestionAnswerService questionAnswerService;
    /**
     * 试卷用户接口
     */
    @Autowired
    IQuestionnaireUserService questionUserService;
    /**
     * 试卷接口
     */
    @Autowired
    IQuestionnaireService questionnaireService;
    /**
     * 试题接口
     */
    @Autowired
    IQuestionService questionService;
    /**
     * 号码归属地接口
     */
    @Autowired
    PhoneAttributionDao phoneAttributionDao;
    /**
     * 数据字典接口
     */
    @Autowired
    IDict dictService;
    /**
     * 外呼任务id
     */
    private Integer vid;
    /**
     * 任务用户ID
     */
    private Integer phoneUserid;
    /**
     * 试卷用户id
     */
    private Integer questionnaireUserId;
    /**
     * 外呼任务用户
     */
    private PhoneTaskUser ptu;
    /**
     * 外呼任务实体
     */
    private PhoneTask pt;
    /**
     * 号码归属地
     */
    private String mobileArea;
    /**
     * 用户类型名称
     */
    private String userTypeName;
    /**
     * 答案json
     */
    private String questionAnswerArrayJSONStr;
    /**
     * 回访状态
     */
    private Integer statue;
    /**
     * 执行/重呼状态
     */
    private Integer state;
    /**
     * 试卷id
     */
    private Integer questionnaireId;
    /**
     * 外呼时间
     */
    private String outCallTime;
    /**
     * 时长
     */
    private Integer callLength;

    /**
     * 功能：跳转到执行页面
     * @return
     */
    public String toPhoneTaskPerform()
    {
        try
        {
            UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
            if (userInfo != null)
            {
                if (phoneUserid != null)
                {// 重呼
                    ptu = phoneTaskUserService.findFirst(userInfo, vid, phoneUserid, false);
                    logger.info("跳转到重呼页面成功！当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")");
                }
                else
                {// 执行
                    ptu = phoneTaskUserService.findFirst(userInfo, vid, phoneUserid, true);
                    logger.info("跳转到执行页面成功！当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")");
                }
                // 号码归属地
                if (ptu != null && StringUtils.isNotBlank(ptu.getPhone()))
                {
                    // 电话归属地
                    PhoneAttribution pb = phoneAttributionDao.findByPhone(ptu.getPhone());
                    if (pb != null)
                    {
                        mobileArea = pb.getMobileArea();// 归属区域
                    }
                    else
                    {
                        mobileArea = "未知";// 归属区域
                    }
                }
                if (ptu != null)
                {
                    pt = ptu.getTaskId();
                }

            }
        }
        catch (Exception e)
        {
            logger.error("跳转到执行页面出错,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")", e);
        }
        return SUCCESS;
    }

    /**
     * 功能：执行操作提交(接通、未接通)
     * questionAnswerArrayJSONStr为 null 则是未接通
     * @return
     */
    public String phoneTaskPerform()
    {
        try
        {
            UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
            if (userInfo != null)
            {
                pt = phoneTaskService.getPhoneTaskByVid(vid);
                if (pt.getTaskState() == 4)
                {
                    ajax("2");
                    return null;
                }
                // 更新试卷用户
                QuestionnaireUser qnu = questionUserService.findOne(questionnaireUserId);
                qnu.setUpdateUser(userInfo);
                if (outCallTime != null && !"".equals(outCallTime) && callLength != null && callLength != 0)
                {
                	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                	long endTimeLong = sdf.parse(outCallTime).getTime()+callLength*1000;
                    qnu.setEndTime(new Timestamp(endTimeLong));
                }
                qnu.setInquirerUser(userInfo);
                qnu.setStatue(statue);
                Questionnaire qn = questionnaireService.findOne(questionnaireId);
                qnu.setQuestionnaire(qn);
                qnu.setTaskId(pt);
                qnu.setIsForkGroup(getLoginUser().getIsForkGroup());
                if (callLength != null && callLength != 0)
                {
                    qnu.setCallLength(callLength);
                }
                if (outCallTime != null && !"".equals(outCallTime))
                {
                    qnu.setCallTime(Timestamp.valueOf(outCallTime));
                }
                questionAnswerService.saveAllAnswer(phoneUserid, questionAnswerArrayJSONStr, userInfo, questionnaireId,
                        vid, state, qnu);
                ajax("0");
                logger.info("提交执行结果成功,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")");
            }
            else
            {
                ajax("1");
                logger.info("提交执行结果失败,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")");
            }
        }
        catch (Exception e)
        {
        	logger.error("提交执行结果出错,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")", e);
        }
        return null;
    }

    /*-------get set方法-------*/
    public Integer getVid()
    {
        return vid;
    }

    public void setVid(Integer vid)
    {
        this.vid = vid;
    }

    public Integer getPhoneUserid()
    {
        return phoneUserid;
    }

    public void setPhoneUserid(Integer phoneUserid)
    {
        this.phoneUserid = phoneUserid;
    }

    public PhoneTaskUser getPtu()
    {
        return ptu;
    }

    public void setPtu(PhoneTaskUser ptu)
    {
        this.ptu = ptu;
    }

    public PhoneTask getPt()
    {
        return pt;
    }

    public void setPt(PhoneTask pt)
    {
        this.pt = pt;
    }

    public String getMobileArea()
    {
        return mobileArea;
    }

    public void setMobileArea(String mobileArea)
    {
        this.mobileArea = mobileArea;
    }

    public String getUserTypeName()
    {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName)
    {
        this.userTypeName = userTypeName;
    }

    public String getQuestionAnswerArrayJSONStr()
    {
        return questionAnswerArrayJSONStr;
    }

    public void setQuestionAnswerArrayJSONStr(String questionAnswerArrayJSONStr)
    {
        this.questionAnswerArrayJSONStr = questionAnswerArrayJSONStr;
    }

    public Integer getStatue()
    {
        return statue;
    }

    public void setStatue(Integer statue)
    {
        this.statue = statue;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

    public Integer getQuestionnaireId()
    {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId)
    {
        this.questionnaireId = questionnaireId;
    }

    public String getOutCallTime()
    {
        return outCallTime;
    }

    public void setOutCallTime(String outCallTime)
    {
        this.outCallTime = outCallTime;
    }

    public Integer getCallLength()
    {
        return callLength;
    }

    public void setCallLength(Integer callLength)
    {
        this.callLength = callLength;
    }

    public Integer getQuestionnaireUserId()
    {
        return questionnaireUserId;
    }

    public void setQuestionnaireUserId(Integer questionnaireUserId)
    {
        this.questionnaireUserId = questionnaireUserId;
    }

}
