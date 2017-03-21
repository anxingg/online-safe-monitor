/**
 * 
 */
package cn.com.qytx.hotline.util;

import java.lang.reflect.Field;


/**
 * 功能:把对象转换为字符串
 * 版本: 1.0
 * 开发人员: 彭小东
 * 创建日期: 2015-3-3
 * 修改日期: 2015-3-3
 * 修改列表: 
 */
public class ObjectToString
{


  
    public static String getString(Object obj) {
        StringBuilder sb=new StringBuilder();
        Field[] objFields = obj.getClass().getDeclaredFields();
        for (Field field : objFields) {
            String fieldName = field.getName();
System.out.println("======================================="+fieldName);            
            String methodNamePart = fieldName.substring(0, 1).toUpperCase()
                    + fieldName.substring(1);
            String getMethodName = "get" + methodNamePart;
            // 从新对象中执行get方法获取新的值
            Object value;
            try
            {
                value = obj.getClass()
                        .getMethod(getMethodName, new Class[] {}).invoke(obj);
                if (value != null) {
                    sb.append(fieldName).append("=").append(value).append(";");
                }
            }catch (Exception e) {
                // TODO: handle exception
            }
           
           
           
        }
        return sb.toString();
    }
    /**
     * 功能：
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }

}
