package cn.com.qytx.hotline.phonetask.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.com.qytx.hotline.phonetask.domain.Question;
import cn.com.qytx.hotline.phonetask.domain.QuestionItem;
import cn.com.qytx.hotline.phonetask.impl.QuestionnaireServiceImpl;
import cn.com.qytx.hotline.phonetask.service.IQuestionService;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;

/**
 * 功能:外呼任务问题处理action
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-9-22
 * 修改人: pxd
 * 修改日期: 2015-2-6
 * 修改列表:
 */
public class QuestionAction extends BaseActionSupport
{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1906406243438891193L;
	/**
     * log4j日志
     */
	 private final static MonitorLogger logger =new Log4jImpl(QuestionAction.class);
	
	/**
     * 试题接口
     */
    @Resource(name = "questionService")
    private IQuestionService questionService;
    /**
     * 调查问卷接口
     */
    @Resource(name = "questionnaireService")
    QuestionnaireServiceImpl questionnaireService;
    /**
     * 问卷调查id
     */
    private String tjId;
    /**
     * 问题id
     */
    public int questionId;
    /**
     * 问题列表
     */
    private List<Question> list;
    /**
     * 试卷id
     */
    private Integer questionnareId;

    /**
     * 外呼任务状态
     */
    private Integer statue;

    /**
     * 功能：保存试卷中的问题
     */
    public void save()
    {
        if (list != null && !list.isEmpty())
        {
            for (Question question : list)
            {
                question.setCreateUser(getLoginUser());
                question.setUpdateUser(getLoginUser());
                question.setCompanyId(getLoginUser().getCompanyId());
                if (question.getItems() != null && !list.isEmpty())
                {
                    for (QuestionItem item : question.getItems())
                    {
                        item.setCreateUser(getLoginUser());
                        item.setUpdateUser(getLoginUser());
                        item.setQuestion(question);
                        item.setCompanyId(getLoginUser().getCompanyId());
                    }
                }
            }
        }
        questionService.save(list, questionnareId, statue);
        ajax(true);
        logger.info("保存试卷中的问题成功！当前操作人："+getLoginUser().getUserName()+"(ID:"+getLoginUser().getUserId()+")");
    }

    /**
     * 功能：删除问题
     */
    public void delQuestion()
    {
        questionService.deleteAndItemById(questionId);
        ajax(true);
        logger.info("删除问题成功！当前操作人："+getLoginUser().getUserName()+"(ID:"+getLoginUser().getUserId()+")");
    }

    /**
     * 功能：删除调查问卷
     * @param ids
     */
    public void deleteQuestion()
    {
        questionnaireService.delete(Integer.parseInt(tjId), false);
        ajax(1);
        logger.info("删除调查问卷成功！当前操作人："+getLoginUser().getUserName()+"(ID:"+getLoginUser().getUserId()+")");
    }

    public List<Question> getList()
    {
        return list;
    }

    public void setList(List<Question> list)
    {
        this.list = list;
    }

    public Integer getQuestionnareId()
    {
        return questionnareId;
    }

    public void setQuestionnareId(Integer questionnareId)
    {
        this.questionnareId = questionnareId;
    }

    public Integer getStatue()
    {
        return statue;
    }

    public void setStatue(Integer statue)
    {
        this.statue = statue;
    }

    public String getTjId()
    {
        return tjId;
    }

    public void setTjId(String tjId)
    {
        this.tjId = tjId;
    }
}
