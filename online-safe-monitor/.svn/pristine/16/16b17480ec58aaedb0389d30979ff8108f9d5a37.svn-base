package cn.com.qytx.hotline.phonetask.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.phonetask.domain.PhoneTaskUser;
import cn.com.qytx.hotline.phonetask.domain.Question;
import cn.com.qytx.hotline.phonetask.domain.QuestionAnswer;
import cn.com.qytx.hotline.phonetask.domain.QuestionItem;
import cn.com.qytx.hotline.phonetask.domain.Questionnaire;
import cn.com.qytx.hotline.phonetask.domain.QuestionnaireUser;
import cn.com.qytx.hotline.phonetask.service.IPhoneTaskUser;
import cn.com.qytx.hotline.phonetask.service.IQuestionAnswerService;
import cn.com.qytx.hotline.phonetask.service.IQuestionnaireService;
import cn.com.qytx.hotline.phonetask.service.IQuestionnaireUserService;
import cn.com.qytx.hotline.util.ExportExcel;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能:外呼任务结果查看以及导出
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-9-19
 * 修改人: pxd
 * 修改日期: 2015-2-6
 * 修改列表:
 */
public class PhoneTaskDetailResultAction extends BaseActionSupport
{
    /**
     * 描述含义
     */
    private static final long serialVersionUID = 1L;
    /**
     * log4j日志
     */
    private final static MonitorLogger logger =new Log4jImpl(PhoneTaskDetailResultAction.class);

    private static final String QUEST = "quest_";

    private static final String ITEM = "item_";

    /**
     * 试卷用户发布服务类接口
     */
    @Autowired
    private IQuestionnaireUserService questionnaireUserServiceImpl;
    /**
     * 试卷服务接口
     */
    @Resource(name = "questionnaireService")
    private IQuestionnaireService questionnaireService;
    /**
     * 调查回答接口
     */
    @Resource(name = "questionAnswerServiceImpl")
    private transient IQuestionAnswerService questionAnswerService;
    /**
     * 回访用户接口
     */
    @Resource(name = "phoneTaskUser")
    private transient IPhoneTaskUser phoneTaskUserService;

    /**
     * 问卷ID
     */
    private Integer questionnaireId;
    /**
     * 回访结果值
     */

    private Integer statue;
    /**
     * 关键字(包含电话号码/坐席工号)
     */

    private String searchkey;//

    /**
     * 试卷内容
     */
    public Questionnaire naire;//
    /**
     * 试卷id
     */
    public int paperId = 1;
    /**
     * 试卷对象
     */
    private Questionnaire questionnaire;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 1:为前台查看
     */
    private Integer checkDetain;

    /**
     * 功能：结果查看列表
     * @return
     */
    public String list()
    {
        try
        {
            UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
            if (userInfo != null)
            {
                Page<QuestionnaireUser> pageInfo = questionnaireUserServiceImpl.totalList(getPageable(new Sort(
                        Direction.DESC, "createTime")), null, null, searchkey, questionnaireId, null, statue);
                // 得到结果
                List<QuestionnaireUser> qnuList = pageInfo.getContent();
                // 封装map
                List<Map<String, Object>> mapList = analyzeResult(qnuList);
                this.ajaxPage(pageInfo, mapList);
            }
        }
        catch (Exception e)
        {
            logger.error("外呼任务结果查看列表出错,当前操作人："+getLoginUser().getUserName()+"(ID:"+getLoginUser().getUserId()+")", e);
        }
        return null;
    }

    /**
     * 功能：将用户回访结果试卷 转化为map形式
     * @param qnuList
     *            回访用户试卷
     * @return
     */
    private List<Map<String, Object>> analyzeResult(List<QuestionnaireUser> qnuList)
    {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (qnuList != null)
        {
            int i = getPageable().getPageNumber() * getPageable().getPageSize() + 1;
            for (QuestionnaireUser qnu : qnuList)
            {
                mapList.add(qnu.getQuestionnaireUserMap(i));
                i++;
            }
        }
        return mapList;
    }

    /**
     * 功能：查看回答人的调查结果
     * @return
     */
    public String view()
    {
        PhoneTaskUser phoneTaskUser = phoneTaskUserService.findOne(userId);
        questionnaire = questionnaireService.serializeLoadQuestionnaire(questionnaireId);
        Map<String, String> map = questionAnswerService.findAnswerMap(phoneTaskUser, questionnaireId);
        for (Question question : questionnaire.getQuestions())
        {
            if (question.getTypeId().equals(Integer.parseInt("3")))
            { // 问答题
                question.setAnswer(map.get(String.valueOf(question.getId())));
            }
            else if (question.getTypeId().equals(Integer.parseInt("2")))
            { // 多选题
                String values = map.get(String.valueOf(question.getId()));
                for (QuestionItem questionItem : question.getItems())
                {
                    if (values != null && values.contains(String.valueOf(questionItem.getId())))
                    {
                        questionItem.setAnswer(true);
                    }
                }
            }
            else if (question.getTypeId().equals(Integer.parseInt("1")))
            {
                String values = map.get(String.valueOf(question.getId()));
                for (QuestionItem questionItem : question.getItems())
                {
                    if (values != null && values.contains(String.valueOf(questionItem.getId())))
                    {
                        questionItem.setAnswer(true);
                        break;
                    }
                }
            }
        }
        return "success";
    }

    /**
     * 功能：导出
     * @return
     */
    public void exporting()
    {
        HttpServletResponse response = this.getResponse();
        response.setContentType("application/vnd.ms-excel");
        OutputStream outp = null;
        try
        {
            UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
            if (userInfo != null)
            {
                this.setIDisplayStart(0);
                this.setIDisplayLength(Integer.MAX_VALUE);
                Page<QuestionnaireUser> pageInfo = questionnaireUserServiceImpl.totalList(getPageable(new Sort(
                        Direction.DESC, "createTime")), null, null, searchkey, questionnaireId, null, statue);
                // 得到结果
                List<QuestionnaireUser> qnuList = pageInfo.getContent();
                // 把报表信息填充到map里面
                response.addHeader("Content-Disposition", "attachment;filename="
                        + new String("结果查看.xls".getBytes(), "iso8859-1"));// 解决中文

                outp = response.getOutputStream();

                List<Map<String, Object>> mapList = analyzeResult(qnuList);
                Map<String, String> mapAnswer = getQuestionAnswer(qnuList);
                Questionnaire question = questionnaireService.findOne(questionnaireId);
                List<Question> questions = question.getQuestions();
                ExportExcel exportExcel = new ExportExcel(outp, getExportHeadList(), mapList, getExportKeyList(),
                        questions, mapAnswer, question.getTitle());

                exportExcel.export();
                logger.info("外呼任务结果查看列表导出成功,当前操作人："+getLoginUser().getUserName()+"(ID:"+getLoginUser().getUserId()+")");
            }
        }
        catch (Exception e)
        {
        	logger.error("外呼任务结果查看列表导出出错,当前操作人："+getLoginUser().getUserName()+"(ID:"+getLoginUser().getUserId()+")", e);
        }
    }

    /**
     * 功能：根据问题用户查询问题答案
     * @param qnuList
     * @return
     */
    private Map<String, String> getQuestionAnswer(List<QuestionnaireUser> qnuList)
    {
        PhoneTaskUser puser = null;
        Map<String, String> answerMap = new HashMap<String, String>();
        Map<String, Map<String, Integer>> mapQuest = new HashMap<String, Map<String, Integer>>();
        // 遍历所有的答卷人
        for (QuestionnaireUser quser : qnuList)
        {
            puser = quser.getSurveyUser();
            if (puser != null)
            {
                List<QuestionAnswer> listAnswer = puser.getQuestionAnswer();
                // 遍历每个答卷人的所有答案
                for (QuestionAnswer answer : listAnswer)
                {
                    Question ques = answer.getQuestion();
                    String result = answer.getAnswer();
                    // 如果是问答题
                    if (ques.getTypeId() == 3)
                    {
                        answerMap.put(puser.getVid() + "_" + ques.getId(), "3=" + result);
                        // 如果是多选题
                    }
                    else if (ques.getTypeId() == 2)
                    {
                        String[] re = result.split(",");
                        Map<String, Integer> mapItem = mapQuest.get(QUEST + ques.getId());
                        // 如果不存在问题的选项 保存问题的选项
                        if (mapItem == null)
                        {
                            mapItem = this.getItemOrder(ques.getItems());
                            mapQuest.put(QUEST + ques.getId(), mapItem);
                        }
                        // 获取保存问题的顺序
                        StringBuilder sbItem = new StringBuilder();
                        for (int i = 0; i < re.length; i++)
                        {
                            if (re[i] != null && re[i].length() > 0)
                            {
                                sbItem.append(mapItem.get(ITEM + re[i])).append("_");
                            }
                        }
                        // 保存多选题的答案
                        answerMap.put(puser.getVid() + "_" + ques.getId(), "2=" + sbItem.toString());
                        // 如果是单选题
                    }
                    else
                    {
                        Map<String, Integer> mapItem = mapQuest.get(QUEST + ques.getId());
                        if (mapItem == null)
                        {
                            mapItem = this.getItemOrder(ques.getItems());
                            mapQuest.put(QUEST + ques.getId(), mapItem);
                        }
                        int res = mapItem.get(ITEM + result) == null ? -1 : mapItem.get(ITEM + result);
                        answerMap.put(puser.getVid() + "_" + ques.getId(), "1=" + res);
                    }

                }
            }

        }
        return answerMap;
    }

    /**
     * 功能：获取问题的排列顺序
     * @param items
     * @return
     */
    private Map<String, Integer> getItemOrder(List<QuestionItem> items)
    {
        Map<String, Integer> mapItem = new HashMap<String, Integer>();
        for (QuestionItem Item : items)
        {
            mapItem.put(ITEM + Item.getId(), Item.getOrderList());
        }
        return mapItem;
    }

    private List<String> getExportKeyList()
    {
        List<String> exportKey = new ArrayList<String>();
        exportKey.add("no");// 0
        exportKey.add("outCallObject");// 1
        exportKey.add("phone");// 2
        exportKey.add("seatName");// 3
        exportKey.add("workNo");// 4
        exportKey.add("callTimeStr");// 5
        exportKey.add("callResult");// 6

        return exportKey;
    }

    private List<String> getExportHeadList()
    {
        List<String> headList = new ArrayList<String>();
        headList.add("序号");// 0
        headList.add("外呼对象");// 1
        headList.add("电话号码");// 2
        headList.add("执行坐席");// 3
        headList.add("坐席工号");// 4
        headList.add("回访时间");// 5
        headList.add("回访结果");// 6
        return headList;
    }

    public Integer getQuestionnaireId()
    {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId)
    {
        this.questionnaireId = questionnaireId;
    }

    public Integer getStatue()
    {
        return statue;
    }

    public void setStatue(Integer statue)
    {
        this.statue = statue;
    }

    public String getSearchkey()
    {
        return searchkey;
    }

    public void setSearchkey(String searchkey)
    {
        this.searchkey = searchkey;
    }

    public Questionnaire getNaire()
    {
        return naire;
    }

    public void setNaire(Questionnaire naire)
    {
        this.naire = naire;
    }

    public int getPaperId()
    {
        return paperId;
    }

    public void setPaperId(int paperId)
    {
        this.paperId = paperId;
    }

    public Questionnaire getQuestionnaire()
    {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire)
    {
        this.questionnaire = questionnaire;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getCheckDetain()
    {
        return checkDetain;
    }

    public void setCheckDetain(Integer checkDetain)
    {
        this.checkDetain = checkDetain;
    }

}
