package cn.com.qytx.workflow.action;

import java.lang.reflect.ParameterizedType;

import cn.com.qytx.platform.base.action.BaseActionSupport;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 抽象基类action
 * 
 * @param <T>
 *            保存参数的类
 */
@SuppressWarnings("serial")
public class BasePageAction<T> extends BaseActionSupport implements
		ModelDriven<T> {

	/**
	 * 封装参数类
	 */
	protected T param;

	@SuppressWarnings("unchecked")
	public BasePageAction() {
		try {
			param = (T) getEntityClassByIndex().newInstance();
		} catch (InstantiationException e) {
			LOGGER.error(e.getMessage());
//			e.printStackTrace();
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage());
//			e.printStackTrace();
		}
	}

	/**
	 * 获取声明的泛型类Class
	 */
	@SuppressWarnings("rawtypes")
	protected Class getEntityClassByIndex() {
		@SuppressWarnings("unchecked")
		Class entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return entityClass;
	}

	public T getParam() {
		return param;
	}

	public void setParam(T param) {
		this.param = param;
	}

	@Override
	public T getModel() {
		return param;
	}

}
