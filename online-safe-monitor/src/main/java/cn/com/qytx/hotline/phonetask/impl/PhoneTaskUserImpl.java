package cn.com.qytx.hotline.phonetask.impl;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.phonetask.dao.PhoneTaskUserDao;
import cn.com.qytx.hotline.phonetask.dao.QuestionnaireUserDao;
import cn.com.qytx.hotline.phonetask.domain.PhoneTask;
import cn.com.qytx.hotline.phonetask.domain.PhoneTaskUser;
import cn.com.qytx.hotline.phonetask.domain.QuestionnaireUser;
import cn.com.qytx.hotline.phonetask.service.IPhoneTaskUser;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能:用户任务实现类
 * 版本: 1.0
 * 开发人员: 徐长江
 * 创建日期: 2014-2-12
 * 修改日期: 2014-2-12
 * 修改列表:
 */
@Service("phoneTaskUser")
@Transactional
public class PhoneTaskUserImpl extends BaseServiceImpl<PhoneTaskUser> implements IPhoneTaskUser
{
    /**
     * 电话任务dao
     */
    @Resource(name = "phoneTaskUserDao")
    private PhoneTaskUserDao phoneTaskUserDao;
    /**
     * 试卷用户dao
     */
    @Resource(name = "questionnaireUserDao")
    private QuestionnaireUserDao questionnaireUserDao;

    /**
     * 功能：根据crm保存 phoetaskUser
     * @param crm
     */
    public void add(CRM crm, PhoneTask phoneTask)
    {
        PhoneTaskUser phoneUser = phoneTaskUserDao
                .findPhoneTaskUser(phoneTask.getVid(), crm.getMobile(), crm.getName());
        if (phoneUser == null)
        {
            PhoneTaskUser puser = new PhoneTaskUser();
            puser.setTaskId(phoneTask);
            puser.setCrmId(crm.getVid());
            puser.setAddress(crm.getAddress());
            puser.setUserName(crm.getName());
            puser.setTelephone(crm.getBackPhone());
            puser.setSex(crm.getGender().toString());
            puser.setPhone(crm.getMobile());
            puser.setUserType(crm.getPersonType());
            puser.setNotice("");
            puser.setIsForkGroup(phoneTask.getIsForkGroup());
            puser.setCompanyId(phoneTask.getCompanyId());
            phoneTaskUserDao.saveOrUpdate(puser);
        }
    }

    /**
     * 功能：更新电话任务用户
     * @param taskUserId
     * @param phoneTask
     */

    public void update(String taskUserId, PhoneTask phoneTask)
    {
        PhoneTaskUser phoneUser = phoneTaskUserDao.findById(Integer.valueOf(taskUserId));
        if (phoneUser != null)
        {
            phoneUser = phoneTaskUserDao.findPhoneTaskUser(phoneTask.getVid(), phoneUser.getPhone(),
                    phoneUser.getUserName());
            if (phoneUser == null)
            {
                phoneTaskUserDao.update(taskUserId, phoneTask.getVid(), phoneTask.getCompanyId());
            }
        }

    }

    /**
     * 功能：清空电话任务用户的任务信息
     * @param vid
     */
    public void clearTaskUser(Integer vid)
    {
        String hql = "update PhoneTaskUser set  taskId.vid=null where taskId.vid=?";
        phoneTaskUserDao.executeQuery(hql, vid);
    }

    /**
     * 功能：更新电话任务对象的任务信息
     * @param phoneUserIds
     * @param vid
     * @param companyId
     */
    public void updateUsers(String phoneUserIds, Integer vid, Integer companyId)
    {
        if (phoneUserIds.startsWith(","))
        {
            phoneUserIds = phoneUserIds.substring(1);
        }
        if (phoneUserIds.endsWith(","))
        {
            phoneUserIds = phoneUserIds.substring(0, phoneUserIds.length() - 1);
        }
        if (phoneUserIds.trim().length() > 0)
        {
            String hql = "update PhoneTaskUser set  taskId.vid=?,companyId=?  where vid in( " + phoneUserIds + ")";

            phoneTaskUserDao.executeQuery(hql, vid, companyId);
        }

    }

    /**
     * 功能：获取一个执行对象
     * @param inquirerUser 执行坐席
     * @param phoneTaskid 外呼任务id
     * @param phoneUserid 电话任务用户id
     * @param isInit 是否初始化 （重呼的时候为false）
     * @return
     */
    public PhoneTaskUser findFirst(UserInfo inquirerUser, Integer phoneTaskid, Integer phoneUserid, boolean isInit)
    {
        PhoneTaskUser taskUser = null;
        if (phoneUserid != null && phoneTaskid > 0)
        {
            taskUser = findOne(phoneUserid);
        }
        else
        {
            // 获取自己正在执行的用户
            taskUser = phoneTaskUserDao.findFirst(inquirerUser, phoneTaskid);
            if (taskUser != null)
            {
                isInit = false;
            }
            else
            {
                // 获取未被分配的执行对象
                taskUser = phoneTaskUserDao.findFirstPhoneTask(inquirerUser, phoneTaskid);
            }
        }

        // 初始化问题用户
        if (isInit && taskUser != null)
        {
            QuestionnaireUser querst = new QuestionnaireUser();
            querst.setStatue(10);
            querst.setTaskId(taskUser.getTaskId());
            querst.setInquirerUser(inquirerUser);
            querst.setSurveyUser(taskUser);
            querst.setCreateTime(new Timestamp(new Date().getTime()));
            querst.setIsForkGroup(taskUser.getIsForkGroup());
            querst.setCompanyId(inquirerUser.getCompanyId());
            questionnaireUserDao.saveOrUpdate(querst);
            taskUser.setSurveyUser(querst);
        }
        return taskUser;

    }
}
