package cn.com.qytx.hotline.customercall.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.BaseDomain;
/**
 * 功能: 通话记录和工单对应表
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2014-1-6
 * 修改日期: 2014-1-6
 * 修改列表:
 */
@Entity
@Table(name="tb_ccl_msiLog")
public class CclMsiLog extends BaseDomain  implements Serializable
{
    /**
     * 描述含义
     */
    private static final long serialVersionUID = 7010378565187747868L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer vid;
    /**
     * 工单Id
     */
    @Column(name="cclId")
    private Integer cclId;
    /**
     * 话单Id
     */
    @Column(name="msiLogId")
    private Integer msiLogId;
    /**
     * 通话记录sessionId
     */
    @Column(name="msiSessionId")
    private String msiSessionId;
    
    @Column(name="is_fork_group")
 	private Integer isForkGroup;
    /**
     * msi坐席Id
     */
     @Column(name="msiUserId")
    private Integer msiUserId;   

    public Integer getVid()
    {
        return vid;
    }
    public void setVid(Integer vid)
    {
        this.vid = vid;
    }
    public Integer getCclId()
    {
        return cclId;
    }
    public void setCclId(Integer cclId)
    {
        this.cclId = cclId;
    }
    public Integer getMsiLogId()
    {
        return msiLogId;
    }
    public void setMsiLogId(Integer msiLogId)
    {
        this.msiLogId = msiLogId;
    }
    public String getMsiSessionId()
    {
        return msiSessionId;
    }
    public void setMsiSessionId(String msiSessionId)
    {
        this.msiSessionId = msiSessionId;
    }
    public Integer getMsiUserId()
    {
        return msiUserId;
    }
    public void setMsiUserId(Integer msiUserId)
    {
        this.msiUserId = msiUserId;
    }
	public Integer getIsForkGroup() {
		return isForkGroup;
	}
	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}
    /**
     * 功能：
     * @return
     */
    @Override
    public String toString()
    {
        return "CclMsiLog [vid=" + vid + ", cclId=" + cclId + ", msiLogId=" + msiLogId + ", msiSessionId="
                + msiSessionId + ", isForkGroup=" + isForkGroup + ", msiUserId=" + msiUserId + "]";
    }
	
	
}
