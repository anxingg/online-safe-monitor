package cn.com.qytx.hotline.customercall.domain;

import java.io.Serializable;
import java.text.DecimalFormat;
/**
 * 功能: 工单编号记录维护
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2013-10-23
 * 修改日期: 2013-10-23
 * 修改列表:
 */
public class CclSn implements Serializable
{
    private static final long serialVersionUID = 3062449216140835749L;
    /**
     * 工单对应月份
     */
    private String yearMonth;
    /**
     * 工单来源
     */
    private String source;
    /**
     * 工单编号
     */
    private String sn;
    
    private static final String STR_FORMAT = "0000"; 
    public static String haoAddOne(String liuShuiHao){
        Integer intHao = Integer.parseInt(liuShuiHao);
        intHao++;
        DecimalFormat df = new DecimalFormat(STR_FORMAT);
        return df.format(intHao);
    }
    public String getYearMonth()
    {
        return yearMonth;
    }
    public void setYearMonth(String yearMonth)
    {
        this.yearMonth = yearMonth;
    }
    public String getSource()
    {
        return source;
    }
    public void setSource(String source)
    {
        this.source = source;
    }
    public String getSn()
    {
        return sn;
    }
    public void setSn(String sn)
    {
        this.sn = sn;
    }
}
