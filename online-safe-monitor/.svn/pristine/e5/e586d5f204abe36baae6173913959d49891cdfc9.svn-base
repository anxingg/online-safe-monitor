package cn.com.qytx.hotline.phonetask.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.dict.domain.Dict;
import cn.com.qytx.hotline.phonetask.domain.PhoneTask;
import cn.com.qytx.hotline.phonetask.domain.PhoneTaskUser;
import cn.com.qytx.hotline.phonetask.domain.Questionnaire;
import cn.com.qytx.hotline.phonetask.service.IPhoneTask;
import cn.com.qytx.hotline.phonetask.service.IQuestionnaireService;
import cn.com.qytx.hotline.phonetask.service.IQuestionnaireUserService;
import cn.com.qytx.hotline.util.DictMapUtil;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IUser;

/**
 * 功能:外呼任务
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-9-19
 * 修改人: pxd
 * 修改日期: 2015-2-6
 * 修改列表:
 */
public class PhoneTaskAction extends BaseActionSupport
{

    /**
     * 描述含义
     */
    private static final long serialVersionUID = 1L;

    /**
     * log4j日志
     */
    private final static MonitorLogger logger =new Log4jImpl(PhoneTaskAction.class);
    
    private static final String ID = "(ID:";
    
    private static final String TASKTYPE = "taskType";
    
    private static final String QUESTIONNAIREDID = "questionnaireId";
    /**
     * 外呼任务接口
     */
    @Autowired
    transient IPhoneTask phoneTaskService;
    /**
     * 试卷接口
     */
    @Autowired
    private IQuestionnaireService naireService;
    /**
     * 用户接口
     */
    @Autowired
    transient IUser userService;

    /**
     * 试卷用户服务类接口
     */
    @Autowired
    IQuestionnaireUserService questionnaireUserServiceImpl;
    /**
     * 外呼任务实体
     */
    private PhoneTask phoneTask;

    private Integer paperId;
    /**
     * 试卷实体
     */
    private Questionnaire naire;
    /**
     * 外呼任务状态
     */
    private Integer state;
    /**
     * 前台=seat,后台back
     */
    private String fromPage;

    /**
     * 外呼任务id
     */
    private Integer vid;
    /**
     * 坐席名称
     */
    private String seatName;
    /**
     * 坐席数量
     */
    private Integer seatNum;
    /**
     * 问题列表
     */
    private String questionListStr;
    /**
     * 任务类型
     */
    private String taskTypeName;
    /**
     * 前台首页
     */
    private String seatWelcome;
    /**
     * 前台首页显示几行
     */
    private Integer showRowCount;

    /**
     * 前台=seat,后台back
     */
    /**
     * 功能：获取外呼任务所有类型
     * @return
     */
    public String getTaskType()
    {
        List<Dict> dicList = DictMapUtil.findListDict(TASKTYPE);
        ajax(dicList);
        return null;
    }

    /**
     * 查询电话任务列表
     */
    public String list()
    {
        try
        {
            UserInfo userInfo = getLoginUser();
            Integer userId = 0;
            if (userInfo != null)
            {
                if ("true".equals(seatWelcome))
                {// 是否是前台首页调用
                    this.setIDisplayStart(1);
                    if (showRowCount != null)
                    {// 前台首页显示多少条记录
                        this.setIDisplayLength(showRowCount);
                    }
                    else
                    {
                        this.setIDisplayLength(8);
                    }
                    userId = userInfo.getUserId();
                }
                if ("seat".equals(fromPage))
                {
                    userId = userInfo.getUserId();
                }
                Page<PhoneTask> pageInfo = phoneTaskService.findPageByVo(getPageable(new Sort(Direction.DESC,
                        "createTime")), userId, phoneTask);
                // 得到结果
                List<PhoneTask> ptList = pageInfo.getContent();
                List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
                if (ptList != null)
                {
                    mapList = parseList(ptList);
                }
                this.ajaxPage(pageInfo, mapList);
            }
        }
        catch (Exception e)
        {
             logger.error("查询电话任务列表出错,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")",e);
        }
        return null;
    }

    /**
     * 解析外呼任务集合 封装为map集合
     * @param ptList
     *            外呼任务集合
     * @return
     */
    public List<Map<String, Object>> parseList(List<PhoneTask> ptList)
    {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, String> dictMap = DictMapUtil.getMapDict(TASKTYPE);
        UserInfo user = getLoginUser();
        int i = getPageable().getPageNumber() * getPageable().getPageSize() + 1;
        for (PhoneTask pt : ptList)
        {
            Map<String, Object> map = pt.PhoneTaskToMap(i);
            map.put("taskTypeStr", pt.getTaskType() == null ? "" : (dictMap.get("key_" + pt.getTaskType()) == null ? ""
                    : dictMap.get("key_" + pt.getTaskType())));
            // 我回访的
            Integer myCallNum = 0;
            Questionnaire qn = pt.getQuestionnaire();
            if (qn != null)
            {
                Integer qnId = qn.getId();// 试卷id
                if (qnId != null)
                {
                    myCallNum = questionnaireUserServiceImpl.countquestionUser(user.getUserId(), qnId);
                }
                // 问卷id
                Integer questionnaireId = qn.getId();
                map.put("questionnaireId", questionnaireId == null ? "" : questionnaireId);
            }
            map.put("myCallNum", myCallNum);
            i++;
            mapList.add(map);
        }

        return mapList;
    }

    /**
     * 开始或暂停外呼任务
     */
    public String phoneTaskStartOrPause()
    {
        try
        {
            if (phoneTask.getVid() != null && state != null)
            {
                phoneTaskService.updatePhoneTaskStast(phoneTask.getVid(), state);
                ajax("0");
                if(state==2){
                	logger.info("暂停外呼任务成功,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")");
                }else{
                	logger.info("开始外呼任务成功,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")");
                }
            }
            else
            {
                ajax("1");
                logger.info("开始或暂停外呼任务失败,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")");
            }
        }
        catch (Exception e)
        {
        	logger.error("开始或暂停外呼任务出错,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")",e);
        }
        return null;
    }

    /**
     * 根据id查询外呼任务状态
     * @return
     */
    public String findById()
    {
        try
        {
            PhoneTask pt = phoneTaskService.findOne(phoneTask.getVid());
            // 任务状态:1草稿 2 未开始 3 正在进行 4 暂停 5 已结束
            ajax(pt.getTaskState().toString());
        }
        catch (Exception e)
        {
        	logger.error("根据id查询外呼任务状态出错,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")",e);
        }
        return null;
    }

    /** 删除外呼任务 */
    public String phoneTaskDel()
    {
        try
        {
            if (phoneTask.getVid() != null)
            {
                phoneTaskService.delete(phoneTask.getVid(), false);
                ajax("0");
                logger.info("删除外呼任务成功,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")");
            }
            else
            {
                ajax("1");
                logger.info("删除外呼任务失败,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")");
            }
        }
        catch (Exception e)
        {
            logger.error("删除外呼任务出错,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")",e);
        }
        return null;
    }

    /**
     * 新建/修改外呼任务
     * @return
     */
    public String addPhoneTask()
    {
        UserInfo userInfo = getLoginUser();
        if (userInfo != null)
        {
            PhoneTask pt = null;
            if (phoneTask != null && phoneTask.getVid() != null)
            {
                pt = phoneTaskService.findOne(phoneTask.getVid());
            }
            if (phoneTask != null)
            {
                phoneTask.setCreateTime(new Timestamp(System.currentTimeMillis()));
                phoneTask.setCreateUser(userInfo);
                phoneTask.setIsDelete(0);
                phoneTask.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                phoneTask.setUpdateUser(userInfo);
                phoneTask.setCompanyId(userInfo.getCompanyId());
                if (phoneTask.getTaskState() == 2)
                {
                    phoneTask.setTaskState(1);
                }
                if (phoneTask.getVid() != null)
                {
                    phoneTask.setIsForkGroup(pt.getIsForkGroup());
                    logger.info("修改外呼任务成功,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")");
                }
                else
                {
                    phoneTask.setIsForkGroup(getLoginUser().getIsForkGroup());
                    logger.info("新增外呼任务成功,当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")");
                }
                phoneTaskService.saveOrUpdatePhoneTask(phoneTask, userInfo);

                Map<String, String> map = new HashMap<String, String>();
                map.put("phoneTaskId", String.valueOf(phoneTask.getVid()));
                map.put(QUESTIONNAIREDID, String.valueOf(phoneTask.getQuestionnaire().getId()));
                ajax(map);
            }
        }
        return null;
    }

    /**
     * 加载任务信息
     * @return
     */
    public String loadPhoneTask()
    {
        Map<String, Object> taskMap = new HashMap<String, Object>();
        String phoneTaskId = this.getRequest().getParameter("phoneTaskId");
        PhoneTask task = phoneTaskService.getPhoneTaskByVid(Integer.parseInt(phoneTaskId));
        if (task != null)
        {
            taskMap.put("taskName", task.getTaskName());// 任务名称
            taskMap.put(TASKTYPE, task.getTaskType());// 任务类型
            taskMap.put("taskExplain", task.getTaskExplain());// 任务说明
            taskMap.put("seatUserIds", task.getSeatUserIds());// 坐席ID集合
            String seatStr = getSeatUserNames(task.getSeatUserIds(), 1);
            taskMap.put("seatCount", task.getSeatUserIds().split(",").length - 1);// 坐席数量
            taskMap.put("seatStr", seatStr);// 坐席内容

            List<PhoneTaskUser> task_users = task.getTask_user();
            StringBuffer crmIds = new StringBuffer();// 外呼对象中包含的用户
            StringBuffer taskUserIds = new StringBuffer();// 外呼对象id集合
            StringBuffer taskUserStr = new StringBuffer();// 外呼对象内容
            int taskUserCount = 0;// 外呼对象数量
            for (int j = 0; j < task_users.size(); j++)
            {
                PhoneTaskUser taskUser = task_users.get(j);
                if (taskUser.getCrmId() > 0)
                {
                    crmIds.append(taskUser.getCrmId());
                    crmIds.append(",");
                    taskUserStr.append("<li>" + taskUser.getUserName() + "(" + taskUser.getPhone()
                            + ")<a class=\"x\" href=\"javascript:void(0);\" name=\"crm\" value=\""
                            + taskUser.getCrmId() + "\" ></a></li>");
                }
                else
                {
                    taskUserIds.append(taskUser.getVid());
                    taskUserIds.append(",");
                    taskUserStr.append("<li>" + taskUser.getUserName() + "(" + taskUser.getPhone()
                            + ")<a class=\"x\" href=\"javascript:void(0);\" name=\"taskUser\" value=\""
                            + taskUser.getVid() + "\" ></a></li>");
                }
                taskUserCount++;
            }
            taskMap.put("taskUserCount", taskUserCount);
            taskMap.put("crmIds", crmIds.toString());
            taskMap.put("taskUserIds", taskUserIds.toString());
            taskMap.put("taskUserStr", taskUserStr.toString());

        }
        ajax(taskMap);

        return null;
    }

    /**
     * 功能：查看电话任务详情
     * @return
     */
    public String phoneTaskDetail()
    {
        try
        {
            UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
            if (userInfo != null && vid != null)
            {
                phoneTask = phoneTaskService.getPhoneTaskByVid(vid);
                if (phoneTask != null)
                {
                    // 坐席名称和数量
                    String seatUserIds = phoneTask.getSeatUserIds();
                    seatName = getSeatUserNames(seatUserIds, 0);
                    seatNum = seatName == "" ? 0 : seatName.split("、").length;
                    // 任务类型
                    Dict dict = DictMapUtil.loadByTypeTagValue(TASKTYPE, 1, phoneTask.getTaskType());
                    taskTypeName = dict != null ? dict.getName() : "";
                    // 问题列表
                    Questionnaire qn = phoneTask.getQuestionnaire();
                    questionListStr = qn != null ? qn.getQuestionListStr() : "";
                }
            }
        }
        catch (Exception e)
        {
            logger.error("查看电话任务详情出错！当前操作人："+getLoginUser().getUserName()+ID+getLoginUser().getUserId()+")", e);
        }
        return "phoneTaskDetail";
    }

    /**
     * 编辑任务信息试题和试题项
     * @return
     */
    public String editNaire()
    {
        naire = naireService.serializeLoadQuestionnaire(paperId);
        return SUCCESS;
    }

    /**
     * 功能：查询坐席名称
     * @param seatUserIds
     *            :坐席id,以","分割
     * @param type
     *            :返回类型 1 特殊组拼 0 名称组拼
     * @return
     */
    private String getSeatUserNames(String seatUserIds, Integer type)
    {
        if (seatUserIds == null || "".equals(seatUserIds))
        {
            return "";
        }
        StringBuffer result = new StringBuffer("");
        List<UserInfo> userInfoList = userService.findUsersByIds(seatUserIds);
        if (type == 0)
        {
            for (UserInfo u : userInfoList)
            {
                result.append(u.getUserName());
                result.append("、");
            }
            if (userInfoList.size() > 0)
            {
                result.deleteCharAt(result.length() - 1);
            }
            return result.toString();
        }
        else
        {
            for (UserInfo seat : userInfoList)
            {
                result.append("<li>" + seat.getUserName() + "(" + seat.getPhone()
                        + ")<a class=\"x\" href=\"javascript:void(0);\" name=\"seat\" value=\"" + seat.getUserId()
                        + "\" ></a></li>");
            }
            return result.toString();
        }

    }

    public PhoneTask getPhoneTask()
    {
        return phoneTask;
    }

    public void setPhoneTask(PhoneTask phoneTask)
    {
        this.phoneTask = phoneTask;
    }

    public Integer getPaperId()
    {
        return paperId;
    }

    public void setPaperId(Integer paperId)
    {
        this.paperId = paperId;
    }

    public Questionnaire getNaire()
    {
        return naire;
    }

    public void setNaire(Questionnaire naire)
    {
        this.naire = naire;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

    public String getFromPage()
    {
        return fromPage;
    }

    public void setFromPage(String fromPage)
    {
        this.fromPage = fromPage;
    }

    public Integer getVid()
    {
        return vid;
    }

    public void setVid(Integer vid)
    {
        this.vid = vid;
    }

    public String getSeatName()
    {
        return seatName;
    }

    public void setSeatName(String seatName)
    {
        this.seatName = seatName;
    }

    public Integer getSeatNum()
    {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum)
    {
        this.seatNum = seatNum;
    }

    public String getQuestionListStr()
    {
        return questionListStr;
    }

    public void setQuestionListStr(String questionListStr)
    {
        this.questionListStr = questionListStr;
    }

    public String getTaskTypeName()
    {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName)
    {
        this.taskTypeName = taskTypeName;
    }

    /**
     * @return the seatWelcome
     */
    public String getSeatWelcome()
    {
        return seatWelcome;
    }

    /**
     * @param seatWelcome
     *            the seatWelcome to set
     */
    public void setSeatWelcome(String seatWelcome)
    {
        this.seatWelcome = seatWelcome;
    }

    /**
     * @return the showRowCount
     */
    public Integer getShowRowCount()
    {
        return showRowCount;
    }

    /**
     * @param showRowCount
     *            the showRowCount to set
     */
    public void setShowRowCount(Integer showRowCount)
    {
        this.showRowCount = showRowCount;
    }

}
