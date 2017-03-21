package cn.com.qytx.hotline.customercall.domain;
/**
 * 
 * 功能:统计
 * 版本: 1.0
 * 开发人员: 徐长江
 * 创建日期: 2013-7-29
 * 修改日期: 2013-7-29
 * 修改列表:
 */
public class CustomerTotal {
    /**
     * 坐席Id
     */
    private Integer workVid;
	/**
	 * 坐席工号
	 */
	private String workNo;
	/**
	 * 基本业务的数量
	 */
	private Integer baseTotal;
	/**
	 * 服务的数量
	 */
	private Integer serviceTotal;
    /**
     * 急救的数量
     */
    private Integer emergencyTotal;
	/**
	 * 总的数量
	 */
	private Integer total;
	public String getWorkNo() {
		return workNo;
	}
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
    public Integer getBaseTotal()
    {
        return baseTotal;
    }
    public void setBaseTotal(Integer baseTotal)
    {
        this.baseTotal = baseTotal;
    }
    public Integer getServiceTotal()
    {
        return serviceTotal;
    }
    public void setServiceTotal(Integer serviceTotal)
    {
        this.serviceTotal = serviceTotal;
    }
    public Integer getEmergencyTotal()
    {
        return emergencyTotal;
    }
    public void setEmergencyTotal(Integer emergencyTotal)
    {
        this.emergencyTotal = emergencyTotal;
    }
    public Integer getWorkVid()
    {
        return workVid;
    }
    public void setWorkVid(Integer workVid)
    {
        this.workVid = workVid;
    }
}
