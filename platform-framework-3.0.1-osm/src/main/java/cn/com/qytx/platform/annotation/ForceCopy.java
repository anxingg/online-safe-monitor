package cn.com.qytx.platform.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 强制复制注解类
 * 应用场景：配合JavaBeanUtils.copyValue() 方法使用。 一般用在非必填字段上。
 * @author lilipo
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ForceCopy {

}
