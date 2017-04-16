package cn.com.qytx.platform.utils;

import java.lang.reflect.Field;

import cn.com.qytx.platform.annotation.ForceCopy;
import cn.com.qytx.platform.annotation.ForceNotCopy;

/**
 * JavaBean对象的工具类
 * @author lilipo
 *
 */
public class JavaBeanUtils {

	/**
	 * 把源对象中有值属性的值， 复制给目标对象对应的属性。
	 * @param c 类型
	 * @param src 源对象
	 * @param des 目标对象
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> void copyValue(Class<T> c, T src, T des) 
			throws IllegalArgumentException, IllegalAccessException {
		/*
		 * 得到类中的所有属性集合
		 */
		Field[] fs = c.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			/* 获取强制复制的注释对象 */
			ForceNotCopy fnc = f.getAnnotation(ForceNotCopy.class);
			if(fnc != null){
				continue;
			}
			
			/* 设置些属性是可以访问的 */
			f.setAccessible(true);
			
			/* 获取强制复制的注释对象 */
			ForceCopy fc = f.getAnnotation(ForceCopy.class);
			
			/* 得到此属性的值 */
			Object val = f.get(src);


			/* 得到此属性的类型 */
			String type = f.getType().toString();
			if(fc == null){
				if (type.endsWith("String")) {
					if(val != null && !"".equals(val)){
						/* 给属性设值 */
						f.set(des, val);
					}
				} else {
					if(val != null){
						/* 给属性设值 */
						f.set(des, val);
					}
				}
			}else {
				/* 不管是否为空，都给属性设值 */
				f.set(des, val);
			}

		}
	}
}
