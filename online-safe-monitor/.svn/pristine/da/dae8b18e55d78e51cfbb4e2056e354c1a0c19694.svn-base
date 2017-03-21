package cn.com.qytx.hotline.customercall.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.org.domain.UserInfo;
/**
 * 功能:坐席任务解决记录表
 * 版本: 1.0
 * 开发人员: 徐长江
 * 创建日期: 2013-7-17
 * 修改日期: 2013-7-17
 * 修改列表:
 */
@Entity
@Table(name = "tb_CustomerCallDealHistory")
public class CustomerCallDealHistory extends BaseDomain implements Serializable
{
    /**
     * 描述含义
     */
    private static final long serialVersionUID = -1936638715976818297L;
    /**
     * 标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vid;
    /**
     * 记录ID CusertomerCallLog表ID
     */
    @Transient
    private int logId;

    /**
     * 工单记录
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "logId")
    private CustomerCallLog customerCallLog;
    /**
     * 处理记录
     */
    @Column(name = "dealInfo")
    private String dealInfo;
    /**
     * 其他信息
     */
    @Column(name = "otherInfo")
    private String otherInfo;
    /**
     * 记录人ID
     */
    @Transient
    private int recordUserId;

    /**
     * 记录人
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "record_user_id")
    private UserInfo recordUser;

    /**
     * 记录人类型 1.座席2.后台用户
     */
    @Column(name = "record_user_type")
    private int recordUserType;
    /**
     * 记录时间
     */
    @Column(name = "recordTime")
    private Timestamp recordTime;

    /**
     * 工单处理状态
     * 1 受理
     * 2 转办
     * 3 派修
     * 4 再次派修
     * 5 维修结果录入
     * 6 维修回访
     * 7 咨询投诉办结
     */
    @Column(name = "action")
    private int action;

    /**
     * 下一步接受人
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "next_user_id")
    private UserInfo nextUser;

    public int getVid()
    {
        return vid;
    }

    public void setVid(int vid)
    {
        this.vid = vid;
    }

    public String getDealInfo()
    {
        return dealInfo;
    }

    public void setDealInfo(String dealInfo)
    {
        this.dealInfo = dealInfo;
    }

    public String getOtherInfo()
    {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo)
    {
        this.otherInfo = otherInfo;
    }

    public Timestamp getRecordTime()
    {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime)
    {
        this.recordTime = recordTime;
    }

    public int getAction()
    {
        return action;
    }

    public void setAction(int action)
    {
        this.action = action;
    }

    public int getLogId()
    {
        return logId;
    }

    public void setLogId(int logId)
    {
        this.logId = logId;
    }

    public int getRecordUserId()
    {
        return recordUserId;
    }

    public void setRecordUserId(int recordUserId)
    {
        this.recordUserId = recordUserId;
    }

    public int getRecordUserType()
    {
        return recordUserType;
    }

    public void setRecordUserType(int recordUserType)
    {
        this.recordUserType = recordUserType;
    }

    public UserInfo getRecordUser()
    {
        return recordUser;
    }

    public void setRecordUser(UserInfo recordUser)
    {
        this.recordUser = recordUser;
    }

    public CustomerCallLog getCustomerCallLog()
    {
        return customerCallLog;
    }

    public void setCustomerCallLog(CustomerCallLog customerCallLog)
    {
        this.customerCallLog = customerCallLog;
    }

    public UserInfo getNextUser()
    {
        return nextUser;
    }

    public void setNextUser(UserInfo nextUser)
    {
        this.nextUser = nextUser;
    }

    /**
     * 功能：
     * @return
     */
    @Override
    public String toString()
    {
        return "CustomerCallDealHistory [vid=" + vid + ", logId=" + logId + ", customerCallLog=" + customerCallLog
                + ", dealInfo=" + dealInfo + ", otherInfo=" + otherInfo + ", recordUserId=" + recordUserId
                + ", recordUser=" + recordUser + ", recordUserType=" + recordUserType + ", recordTime=" + recordTime
                + ", action=" + action + ", nextUser=" + nextUser + "]";
    }

}