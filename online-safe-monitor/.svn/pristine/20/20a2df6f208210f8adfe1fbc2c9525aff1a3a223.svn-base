package cn.com.qytx.hotline.customercall.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.dict.domain.Dict;
import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.hotline.crm.service.ICRM;
import cn.com.qytx.hotline.customercall.domain.ComparatorLog;
import cn.com.qytx.hotline.customercall.domain.CustomerCallLog;
import cn.com.qytx.hotline.customercall.service.ICustomerCallLog;
import cn.com.qytx.hotline.ivr.domain.MsicallLog;
import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.service.IMsiUser;
import cn.com.qytx.hotline.util.PropertiesUtil;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroupUser;
import cn.com.qytx.platform.org.service.IRole;
import cn.com.qytx.platform.org.service.IRoleUser;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;

import com.google.gson.Gson;

/**
 * 功能:工单的action 通话记录 版本: 1.0 开发人员: 徐长江 创建日期: 2013-10-23 修改日期: 2013-10-23 修改列表:
 */
public class CustomerCallLogAction extends BaseActionSupport
{

    private static final long serialVersionUID = 1L;

    /**
     * /**用户信息
     */
    @Autowired
    transient IUser userService;
    /**
     * 数据字典实现类
     */
    @Autowired
    IDict dictImpl;
    /**
     * /*CRM的服务类
     */
    @Autowired
    transient ICRM crmimpl;

    @Autowired
    IGroupUser groupUserService;
    /**
     * 话务员
     */
    @Autowired
    transient IMsiUser msiuserService;
    /**
     * 角色人员信息
     */
    @Autowired
    transient IRoleUser roleUserService;
    /**
     * 工单服务
     */
    @Autowired
    private transient ICustomerCallLog customerCallLogService;
    /**
     * 角色
     */
    @Autowired
    IRole roleService;
    /** 分页开始条数 */
    private int iDisplayStart;

    /** 每页显示条数 */
    private int iDisplayLength;
    private CustomerCallLog customerCallLog;
    private Integer vid;
    // 座机号码,主叫号码
    private String phone;
    // 来电手机号码，被叫号码
    private String callPhone;
    // 判断保存或者已完结
    private Integer checkType;
    private String callTimeString = "";
    // 工单id
    private Integer cclId;
    // 坐席工号
    private String loginName;
    // 工单转入时间
    private String turnTime;
    // 1.回访接通 2.回访没接通
    private Integer chenggong;
    /**
     * 查询来源 seat坐席 system后台
     */
    private String fromPage;
    /**
     * 来电时间
     */
    private String callTimeStr;
    private Timestamp callTime;
    private String handUpTime;
    // 工单内容
    private String cclContent;
    private String seatWelcome;
    // 前台首页显示几行
    private Integer showRowCount;
    /**
     * 后台工单处理时的答复内容
     */
    private String answerContent;
    // 判断是否需要回访
    private Integer checkCallBack;

    // 判断是 转回访还是 办结
    private String forward;
    // 业务类型
    private Integer businessType;

    /**
     * 功能：从数据字典中获取类型
     * @return
     */
    public String getbusinessType()
    {
        List<Dict> dicList = dictImpl.findList("businessType", 1);
        ajax(dicList);
        return null;
    }
    /**
     * 功能：从数据字典中获取类型名称
     * @return
     */
    public String getbusinessTypeName()
    {
        Dict dict = dictImpl.loadByTypeTagValue("businessType", 1, businessType);
        String name = "";
        if (dict != null)
        {
            name = dict.getName();
        }
        ajax(name);
        return null;
    }
    /**
     * 功能：得到通话记录
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getPhoneRecord()
    {
            UserInfo user = getLoginUser();
            if (user != null)
            {
                String basePath = "";
                if ("".equals(PropertiesUtil.get("fileIp", "")))
                {// 语音文件在远程
                    basePath = this.getRequest().getScheme() + "://"
                            + this.getRequest().getServerName() + ":"
                            + this.getRequest().getServerPort() + "/";
                }
                else
                {
                    basePath = this.getRequest().getScheme() + "://"
                            + PropertiesUtil.get("fileIp", "") + ":"
                            + PropertiesUtil.get("filePort", "") + "/";
                }
                int pageNum = (int) (Math.ceil((double) iDisplayStart / (double) iDisplayLength)) + 1;
                /** 用户的标识符 */
                CustomerCallLog customercall = customerCallLogService.findOne(customerCallLog.getVid());
                /** 得到结果 */
                List<MsicallLog> listMsicall = new ArrayList<MsicallLog>();
                listMsicall=customercall.getMsicallLogList();
                ComparatorLog comparator = new ComparatorLog();
                if (null!=listMsicall) {
                	 Collections.sort(listMsicall, comparator);
				}
                /** 封装list */
                List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
                if (listMsicall != null)
                {
                    int i = (pageNum - 1) * iDisplayLength + 1;
                    for (MsicallLog msicallLog : listMsicall)
                    {
                        Map<String, Object> map = new HashMap<String, Object>();
                        putMistCallLogOne(i, msicallLog, map);
                        putMistCallLogTwo(basePath, msicallLog, map);
                        mapList.add(map);
                        i++;
                    }
                }
                Map<String, Object> jsonMap = new HashMap<String, Object>();
                jsonMap.put("aaData", mapList);
                Gson json = new Gson();
                String jsons = json.toJson(jsonMap);
                ajax(jsons);
            }
        return null;
    }
    /**
     * 功能：
     * @param basePath
     * @param msicallLog
     * @param map
     */
    private void putMistCallLogTwo(String basePath, MsicallLog msicallLog, Map<String, Object> map)
    {
        // 呼叫类型 呼叫方式 1：呼入 2：呼出
        Integer callType = msicallLog.getCallType();
        // 对方号码
        String otherPhone_callType1 = msicallLog.getCall();
        String otherPhone_callType2 = msicallLog.getCalled();
        if (callType != null)
        {
            map.put("callType", callType);
            map.put("callTypeStr", getCallTypeName(callType));

            if (callType == 1 && StringUtils.isNotEmpty(otherPhone_callType1))
            {
                // 呼入
                map.put("otherPhone_callType1", otherPhone_callType1);
            }
            else if (callType == 2 && StringUtils.isNotEmpty(otherPhone_callType2))
            {
                // 呼出
                map.put("otherPhone_callType1", otherPhone_callType2);
            }
            else
            {
                map.put("otherPhone_callType1", "-");
            }
        }
        else
        {
            map.put("callTypeStr", "");
            map.put("callType", "");
        }
        // 接通时间
        Timestamp talkInTime = msicallLog.getTalkInTime();
        map.put("talkInTime", talkInTime != null?DateTimeUtil.timestampToString(talkInTime, "yyyy-MM-dd HH:mm"):"");
        // 分钟数
        Integer callLength = msicallLog.getSeconds();
        map.put("callLength", callLength!=null?callLength:"");
        if (callLength == null)
        {
            map.put("voxFile", "-");
            // 语音文件名称
            map.put("voxFileName", "-");
        }
        else
        {
            String voxFile = basePath + PropertiesUtil.get("filepath", "") + "user/"
                    +(StringUtils.isNoneBlank(msicallLog.getVoxFile())?msicallLog.getVoxFile():"");
            map.put("voxFile", voxFile);
            if (StringUtils.isNotEmpty(msicallLog.getVoxFile()))
            {
                String[] files = msicallLog.getVoxFile().split("/");
                map.put("voxFileName", files[files.length - 1]);
            }
            else
            {
                map.put("voxFileName", "--");
            }
        }
    }
    /**
     * 功能：
     * @param i
     * @param msicallLog
     * @param map
     */
    private void putMistCallLogOne(int i, MsicallLog msicallLog, Map<String, Object> map)
    {
        /** 标识符 */
        map.put("id", msicallLog.getVid());
        /** 序号 */
        map.put("no", i);
        /** 坐席的工号 */
        Msiuser msiuser = getByMsiuserId(msicallLog.getMsiuserId());
        if (msiuser != null&&StringUtils.isNotEmpty(msiuser.getWorkNo()))
        {
        	map.put("workNo", msiuser.getWorkNo());
        }
        else
        {
            map.put("workNo", "-");
        }
        // 坐席的姓名
        if (msiuser != null&&StringUtils.isNotEmpty(msiuser.getName()))
        {
        	 map.put("userName", msiuser.getName());
        }
        else
        {
            map.put("userName", "-");
        }
        // 服务评价，即客户满意度 1,非常满意 2,满意 3,一般 4,不满意 5,非常不满意
        Integer customercallSatisfy = msicallLog.getCustomerSatisfactional();
        String customerResult = "满意";
        if (customercallSatisfy != null)
        {
            switch (customercallSatisfy)
            {
            case 1:
                customerResult = "满意";
                break;
            case 2:
                customerResult = "基本满意";
                break;
            case 3:
                customerResult = "不满意";
                break;
            default:
                customerResult = "满意";
                break;
            }
        }
        map.put("customercallSatisfy", customerResult);
    }

    /**
     * 功能：得到坐席的人员
     * @param msiUserId
     * @return
     */
    public Msiuser getByMsiuserId(Integer msiUserId)
    {
        Msiuser misiuser = msiuserService.findById(msiUserId);
        return misiuser;
    }
    /**
     * 得到通话分钟数
     */
    @SuppressWarnings("unused")
    private String secondToComplateTimeStr(Integer seconds)
    {
        if (seconds == null)
        {
            return "";
        }
        if (seconds >= 3600)
        {
            long hour = seconds / 3600;
            long min = (seconds - hour * 3600) / 60;
            long secound = seconds - hour * 3600 - min * 60;
            return hour + "时" + min + "分" + secound + "秒";
        }
        else if (seconds >= 60)
        {
            long min = seconds / 60;
            long secound = seconds - min * 60;
            return min + "分" + secound + "秒";
        }
        else
        {
            return seconds + "秒";
        }

    }

    /**
     * 得到呼叫类型
     */
    public String getCallTypeName(Integer callType)
    {
        if (callType != null)
        {
            if (callType == 1)
            {
                return "呼入";
            }
            else if (callType == 2)
            {
                return "呼出";
            }
            else
            {
                return "呼入";
            }
        }
        else
        {
            return "呼入";
        }

    }
    /**
     * 求时间差字符串
     * @param timestampBebin
     *            开始时间
     * @param timestampEnd
     *            结束时间
     * @return 返回字符串
     */
    @SuppressWarnings("unused")
    private static String getTimeDiffStrBy24Timestamp(Timestamp timestampBebin,
            Timestamp timestampEnd)
    {
        if (timestampBebin == null || timestampEnd == null)
        {
            return "";
        }
        boolean chaoshi = false;
        long timeBegin = timestampBebin.getTime();
        long timeEnd = timestampEnd.getTime();
        long secondLength = (timeEnd - timeBegin) % 1000 == 0 ? (timeEnd - timeBegin) / 1000
                : ((timeEnd - timeBegin) / 1000) + 1;
        secondLength = 24 * 60 * 60 - secondLength;
        if (secondLength < 0)
        {
            chaoshi = true;
            secondLength = Math.abs(secondLength);
        }
        if (secondLength >= 3600)
        {
            long hour = secondLength / 3600;
            long min = (secondLength - hour * 3600) / 60;
            long secound = secondLength - hour * 3600 - min * 60;
            if (chaoshi)
            {
                return "已超时" + hour + ":" + min + ":" + secound;
            }
            return hour + ":" + min + ":" + secound;
        }
        else if (secondLength >= 60)
        {
            long min = secondLength / 60;
            long secound = secondLength - min * 60;
            if (chaoshi)
            {
                return "已超时00:" + min + ":" + secound;
            }
            return "00:" + min + ":" + secound;
        }
        else
        {
            if (chaoshi)
            {
                return "已超时00:00:" + secondLength;
            }
            return "00:00:" + secondLength;
        }
    }
    /**
     * 功能：得到状态字符串
     * @param state
     * @return
     */
    public String getStateName(int state)
    {
        if (state == 1)
        {
            return "已受理";
        }
        else if (state == 2)
        {
            return "待回访";
        }
        else if (state == 3)
        {
            return "待办结";
        }
        else if (state == 4)
        {
            return "已办结";
        }
        return null;
    }
    public CustomerCallLog getCustomerCallLog()
    {
        return customerCallLog;
    }

    public void setCustomerCallLog(CustomerCallLog customerCallLog)
    {
        this.customerCallLog = customerCallLog;
    }

    public Integer getVid()
    {
        return vid;
    }

    public void setVid(Integer vid)
    {
        this.vid = vid;
    }

    public Integer getCheckType()
    {
        return checkType;
    }

    public void setCheckType(Integer checkType)
    {
        this.checkType = checkType;
    }

    public String getCallTimeString()
    {
        return callTimeString;
    }

    public void setCallTimeString(String callTimeString)
    {
        this.callTimeString = callTimeString;
    }

    public Integer getCclId()
    {
        return cclId;
    }

    public void setCclId(Integer cclId)
    {
        this.cclId = cclId;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getTurnTime()
    {
        return turnTime;
    }

    public void setTurnTime(String turnTime)
    {
        this.turnTime = turnTime;
    }

    public Integer getChenggong()
    {
        return chenggong;
    }

    public void setChenggong(Integer chenggong)
    {
        this.chenggong = chenggong;
    }

    public String getFromPage()
    {
        return fromPage;
    }

    public void setFromPage(String fromPage)
    {
        this.fromPage = fromPage;
    }
    public Integer getBusinessType()
    {
        return businessType;
    }

    public void setBusinessType(Integer businessType)
    {
        this.businessType = businessType;
    }

    public String getForward()
    {
        return forward;
    }

    public void setForward(String forward)
    {
        this.forward = forward;
    }

    public Integer getCheckCallBack()
    {
        return checkCallBack;
    }

    public void setCheckCallBack(Integer checkCallBack)
    {
        this.checkCallBack = checkCallBack;
    }

    public String getAnswerContent()
    {
        return answerContent;
    }

    public void setAnswerContent(String answerContent)
    {
        this.answerContent = answerContent;
    }

    public Integer getShowRowCount()
    {
        return showRowCount;
    }

    public void setShowRowCount(Integer showRowCount)
    {
        this.showRowCount = showRowCount;
    }

    public String getSeatWelcome()
    {
        return seatWelcome;
    }

    public void setSeatWelcome(String seatWelcome)
    {
        this.seatWelcome = seatWelcome;
    }

    public String getCallPhone()
    {
        return callPhone;
    }

    public void setCallPhone(String callPhone)
    {
        this.callPhone = callPhone;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getCclContent()
    {
        return cclContent;
    }

    public void setCclContent(String cclContent)
    {
        this.cclContent = cclContent;
    }

    public String getHandUpTime()
    {
        return handUpTime;
    }

    public void setHandUpTime(String handUpTime)
    {
        this.handUpTime = handUpTime;
    }

    public Timestamp getCallTime()
    {
        return callTime;
    }

    public void setCallTime(Timestamp callTime)
    {
        this.callTime = callTime;
    }

    public String getCallTimeStr()
    {
        return callTimeStr;
    }

    public void setCallTimeStr(String callTimeStr)
    {
        this.callTimeStr = callTimeStr;
    }
}