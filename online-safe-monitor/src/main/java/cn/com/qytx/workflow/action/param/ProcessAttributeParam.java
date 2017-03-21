package cn.com.qytx.workflow.action.param;

import cn.com.qytx.workflow.domain.HotProcessAttribute;


/**
 * Created by izerui.com on 14-4-29.
 */
public class ProcessAttributeParam {


    private Integer processAttributeId;
    private HotProcessAttribute processAttribute;
 //   private List<FormCategory> formCategories;
	private String processName;
	private String option;
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	/*public List<FormCategory> getFormCategories() {
		return formCategories;
	}
	public void setFormCategories(List<FormCategory> formCategories) {
		this.formCategories = formCategories;
	}*/
	public HotProcessAttribute getProcessAttribute() {
		return processAttribute;
	}
	public void setProcessAttribute(HotProcessAttribute processAttribute) {
		this.processAttribute = processAttribute;
	}

	public Integer getProcessAttributeId() {
        return processAttributeId;
    }

    public void setProcessAttributeId(Integer processAttributeId) {
        this.processAttributeId = processAttributeId;
    }
	
	public String getOption() {
		return option;
	}
	
	public void setOption(String option) {
		this.option = option;
	}
}
