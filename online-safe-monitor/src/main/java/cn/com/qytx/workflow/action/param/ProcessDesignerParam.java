package cn.com.qytx.workflow.action.param;

/**
 * Created by izerui.com on 14-4-29.
 */
public class ProcessDesignerParam {

    private Integer processAttributeId;
    //json格式的JPDL定义
    private String jsonTypeJpdl;

    public Integer getProcessAttributeId() {
        return processAttributeId;
    }

    public void setProcessAttributeId(Integer processAttributeId) {
        this.processAttributeId = processAttributeId;
    }

    public String getJsonTypeJpdl() {
        return jsonTypeJpdl;
    }

    public void setJsonTypeJpdl(String jsonTypeJpdl) {
        this.jsonTypeJpdl = jsonTypeJpdl;
    }
}
