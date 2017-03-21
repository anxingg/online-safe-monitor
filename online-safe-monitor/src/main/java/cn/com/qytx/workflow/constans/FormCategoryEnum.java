package cn.com.qytx.workflow.constans;

/**
 * Created by izerui.com on 14-4-29.
 */
public enum FormCategoryEnum {
    FORM(1),WORKFLOW(2),GONG_WEN(3);


    private Integer value;

    FormCategoryEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
