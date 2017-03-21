package cn.com.qytx.hotline.customercall.domain;

import java.util.List;
import java.util.Map;

public class TotleReport
{
    /**
     * 类型 电话、短信、网站、邮箱 对应 A,B,C,D  对应数量
     */
    private List<Map<String, Long>> quantityList;
    /**
     * 办结数量
     */
    private Long finishNum;
    /**
     * 办结率
     */
    private Double finishRate;

    public List<Map<String, Long>> getQuantityList()
    {
        return quantityList;
    }

    public void setQuantityList(List<Map<String, Long>> quantityList)
    {
        this.quantityList = quantityList;
    }

    public Long getFinishNum()
    {
        return finishNum;
    }
    public void setFinishNum(Long finishNum)
    {
        this.finishNum = finishNum;
    }
    public Double getFinishRate()
    {
        return finishRate;
    }
    public void setFinishRate(Double finishRate)
    {
        this.finishRate = finishRate;
    }
}
