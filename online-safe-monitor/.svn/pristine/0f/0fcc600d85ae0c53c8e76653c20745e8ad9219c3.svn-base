package cn.com.qytx.hotline.balck.domain;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import cn.com.qytx.oa.domain.OaConst;
import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.base.domain.DeleteState;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;

/**
 * 功能:黑名单实体类
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2013-8-19
 * 修改日期: 2013-8-19
 * 修改列表:
 */
@Entity
@Table(name="blacklist")
public class Blacklist extends BaseDomain implements java.io.Serializable
{
    /**
     * 序列号
     */
    private static final long serialVersionUID = 3471716881073761774L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vid")
    private Integer id;

    /**
     * 电话号码
     */
    @Column(name = "phone",length=500)
    private String phone;
    
    /**
     * 开始时间
     */
    @Column(name = "begintime")
    private Timestamp begintime;
  
    /**
     * 结束时间
     */
    @Column(name = "endtime")
    private Timestamp endtime;
    
    /**
     * 锁定类型  0表示永久  1表示自定义
     */
    @Column(name = "locktype")
    private Integer lockType;
    
    /**
     * 时长
     */
    @Column(name = "duration")
    private Integer duration;
    
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    
    /**
     * 创建人
     */
    @Column(name = "create_user_id")
    private Integer createUserId;
    
    /**
     * 创建时间
     */
    @Column(name = "createtime")
    private Timestamp createTime;
    
    /**
     * 删除标志
     */
    @DeleteState
    @Column(name = "isDelete")
    private Integer isDelete;  
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 开始时间字符串
     */
    @Transient
    private String begintimeStr;

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Timestamp getBegintime()
    {
        return begintime;
    }

    public void setBegintime(Timestamp begintime)
    {
        this.begintime = begintime;
    }

    public Timestamp getEndtime()
    {
        return endtime;
    }

    public void setEndtime(Timestamp endtime)
    {
        this.endtime = endtime;
    }

    public Integer getDuration()
    {
        return duration;
    }

    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getBegintimeStr()
    {
        return begintimeStr;
    }

    public void setBegintimeStr(String begintimeStr)
    {
        // 将字符串转换成时间格式
        if (!StringUtils.isEmpty(begintimeStr)){
            setBegintime(DateTimeUtil.stringToTimestamp(begintimeStr, OaConst.TIME_FORMAT_STR));
        }
        this.begintimeStr = begintimeStr;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getCreateUserId()
    {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId)
    {
        this.createUserId = createUserId;
    }

    public Timestamp getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime)
    {
        this.createTime = createTime;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete)
    {
        this.isDelete = isDelete;
    }

    public Integer getLockType()
    {
        return lockType;
    }

    public void setLockType(Integer lockType)
    {
        this.lockType = lockType;
    }
    /**
     * 功能：将对象转为map
     * @return
     */
    public Map<String, Object> blackListToMap(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	//标识符
    	map.put("id", id);
    	//电话号码
    	map.put("phone", StringUtils.defaultString(phone, "-"));
    	//开始时间
    	map.put("begintime", begintime==null?"-":DateTimeUtil.timestampToString(begintime, OaConst.TIME_FORMAT_STR));
    	//结束时间
    	map.put("endtime", endtime==null?"-":DateTimeUtil.timestampToString(endtime, OaConst.TIME_FORMAT_STR));
    	//锁定类型  0表示永久  1表示自定义
    	map.put("lockType", lockType==null?1:lockType);
    	//锁定时长
    	map.put("duration", duration==null?"-":duration);
    	//锁定时长str
    	String durationStr = "-";
    	if(lockType==0){
    		durationStr = "永久";
    	}else{
    		durationStr = duration.toString();
    	}
    	map.put("durationStr", durationStr);
    	//备注
    	map.put("remark", StringUtils.defaultString(remark, "-"));
    	//创建时间
    	map.put("createTime", createTime==null?"-":DateTimeUtil.timestampToString(createTime, OaConst.TIME_FORMAT_STR));
    	//删除标志0:未删除 1：删除
    	map.put("isDelete", isDelete==null?0:isDelete);
    	return map;
    	
    }
}