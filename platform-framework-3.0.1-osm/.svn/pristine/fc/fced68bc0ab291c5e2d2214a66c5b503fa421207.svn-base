package cn.com.qytx.platform.base.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.qytx.platform.base.query.Page;

import com.google.gson.Gson;

public abstract class BaseControllerSupport {
	protected static final String SPLIT = "||";
	//成功状态码
	private static final Integer SUCCESS_FLAG = 100;
	//服务/错误/异常
	private static final Integer SERVER_ERROR = 101;
	//参数错误
	private static final Integer PARAMETER_ERROR = 102;
    /**
     * 以datatable插件能是被的格式输出分页查询结果
     * @param page 分页查询结果
     */
    public String SUCCESS_PAGE(Page page){
        return SUCCESS_PAGE(page,null);
    }

    /**
     * 以datatable插件能是被的格式输出分页查询结果
     * @param page 分页对象
     * @param list 分页查询记录集
     */
    public String SUCCESS_PAGE(Page page,List list){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("iTotalRecords", page.getTotalElements());
        jsonMap.put("iTotalDisplayRecords", page.getTotalElements());
        jsonMap.put("aaData", list!=null?list:page.getContent());
        Gson gson = new Gson();
        return SUCCESS_FLAG+SPLIT+gson.toJson(list); 
    }
	/**
	 * 成功的时候返回的数据
	 * @param obj
	 * @return
	 */
	public String SUCCESS(Object obj){
		if(obj==null){
			obj = "";
		}
		
		Gson gson = new Gson();
		if(obj instanceof String){
			return SUCCESS_FLAG+SPLIT+obj.toString();
		}
		return SUCCESS_FLAG+SPLIT+gson.toJson(obj);
	}
	
	/**
	 * 默认返回时间戳
	 * @return
	 */
	public String SUCCESS(){
		return SUCCESS(null);
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public String SERVER_ERROR(Object obj){
		if(obj==null){
			obj = "";
		}
		
		if(obj instanceof String){
			return SERVER_ERROR+SPLIT+obj.toString();
		}
		return SERVER_ERROR+SPLIT+new Gson().toJson(obj);
	}
	
	public String SERVER_ERROR(){
		return SERVER_ERROR(null);
	}
	
	private String PARAMETER_ERROR(){
		return PARAMETER_ERROR+SPLIT+"参数错误";
	}
	
	@ExceptionHandler
	@ResponseBody
	public String exeception(HttpServletRequest request, Exception ex){
		if(ex instanceof MissingServletRequestParameterException){
			return PARAMETER_ERROR();
		}
		ex.printStackTrace();
		return SERVER_ERROR();
	}
	
}
