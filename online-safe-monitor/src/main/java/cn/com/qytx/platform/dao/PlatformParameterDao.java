package cn.com.qytx.platform.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.com.qytx.cbb.notify.domain.PlatformParameter;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;

import com.google.gson.Gson;

/**
 * @author 严正
 * 平台化系统参数的持久层操作
 * 
 */
@SuppressWarnings("deprecation")
@Repository("hotlinePlatformParameterDao")
public class PlatformParameterDao extends BaseDao<PlatformParameter, Integer> implements Serializable{
	
    private final static MonitorLogger logger =new Log4jImpl(PlatformParameterDao.class);
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -8959821935372289403L;



	/**
	 * @param vId 
	 * 
	 * @return 按照记录的ID来查某个实体
	 */
	public PlatformParameter findById(int vId){
		
		 String hql = "from PlatformParameter where vId = ?  and isDelete = 0";
	        return (PlatformParameter) super.findUnique(hql, vId);
	}
	public PlatformParameter findByClassName(String className){
		 String hql = "from PlatformParameter where parItems = ?  and isDelete = 0";
		return (PlatformParameter) super.findUnique(hql, className);
	}
	
	/**
	 * @param vId  删除某个参数实体
	 */
	public void deletePlatformParameter(int vId){
		
		 String hql = "update PlatformParameter set isDelete = 1 where vId = ?";
		 super.bulkUpdate(hql, vId);
	}
	
	/**
	 * @param pp 保存参数实体
	 */
	@SuppressWarnings("rawtypes")
	public void savePlatformParameter(Object obj){
		
		try {
			HashMap<String ,String> parMap=new HashMap<String,String>();
		Class cl=obj.getClass();
		 Method[] methods = cl.getDeclaredMethods();
	       for(int i = 0; i < methods.length; i++){
	           Method method = methods[i];
	           if(method.getName().startsWith("get")){
//	              System.out.print("methodName:"+method.getName()+"\t");
	              String fieldName=conversionField(method.getName());
//	              System.out.println("value:"+method.invoke(obj));//得到get 方法的值
	              parMap.put(fieldName, String.valueOf(method.invoke(obj)));
	           }
	       }
	       Method methodTem1=obj.getClass().getMethod("getParDescribe");
	       Method methodTem2=obj.getClass().getMethod("getParItems");
	    String parDescribe= String.valueOf(methodTem1.invoke(obj)) ;
	    String parItems=    String.valueOf(methodTem2.invoke(obj)) ;
		PlatformParameter pp=new PlatformParameter();
		pp.setIsDelete(0);
		pp.setModuleName("");
		pp.setParDescribe(parDescribe);
		pp.setParItems(parItems);
		 Gson gson = new Gson();
		 String parValueColl=gson.toJson(parMap);
		pp.setParValueColl(parValueColl);
//		pp.setpId(0);
		pp.setInstenceid(0);
		PlatformParameter pfpTem=	findByClassName(parItems);
	if(pfpTem!=null){
		//如果库中已经存在对象 update
		pfpTem.setParValueColl(parValueColl);
		super.saveOrUpdate(pfpTem);
		
	}else{
		super.saveOrUpdate(pp);
	}
		
		}catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		}  catch (Exception e) {
			logger.error(e.getMessage());
		}
	} 
	
	/**
	 * @param name get方法名称
	 * @return  属性名称
	 */
	private String conversionField(String name) {
		name=	name.replace("get", "");//去掉get
		String fisetChar=name.substring(0,1);
		name=fisetChar.toLowerCase()+name.substring(1);
		return name;
	}
	private String conversionMethod(String name) {
		String fisetChar=name.substring(0,1);
		name=fisetChar.toUpperCase()+name.substring(1);
		name="set"+name;
		return name;
	}
	/**
	 * @param pp 修改参数实体
	 */
	public void updatePlatformParameter(PlatformParameter pp){
		
//		super.getHibernateTemplate().update(pp);
		super.saveOrUpdate(pp);
	}
	
	/**
	 * @param moduleName 模块/集合的名称
	 *  
	 * @return  返回按模块/集合的名称来列表参数实体的集合
	 */
	public  List<PlatformParameter> listByModuleName(String moduleName){
		String hql = "from PlatformParameter where moduleName = ?  and isDelete = 0";
		 return super.findAll(hql,moduleName);
	}
	
	  /**
     * 功能：分页参数页面
     * @param page
     * @return
     */
    public Page<PlatformParameter> findPageByMd(Pageable page,String moduleName)
    {
//    	String hql = "from PlatformParameter h where h.moduleName = ?  and  h.isDelete = 0 order by h.vId desc";
//    	return super.findByPage(page, hql,moduleName);
    	StringBuffer sb=new StringBuffer(" isDelete=0 ");
		List<Object> params = new ArrayList<Object>();  
		if(moduleName!=null){
			sb.append(" and moduleName = ?");
			params.add(moduleName);
		}
		return super.findAll(sb.toString(), page, params.size()>0?params.toArray():null);
    	
    }
	
	/**
	 * @param parItems 参数名称
	 * @return 根据参数名称返回参数实体
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public   Object findByParItems(String parItems){
		try {
			
		
		 String hql = "from PlatformParameter where parItems = ?  and isDelete=0";
		 PlatformParameter pp=	 (PlatformParameter) super.findUnique(hql, parItems);
		 //获取对象类名称  如：cn.com.qytx.platform.domain.PlatformParameter
		if(pp!=null){
			 String className=pp.getParItems();
			 Class cl=Class.forName(className);
			
			
			 Gson gson = new Gson();
			 Object obj = gson.fromJson(pp.getParValueColl(), cl);
			// Object ot=ctrateParEntity(clObj,pp);

			 return obj;
		}
	
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * @param cl   实体类
	 * @param pp   PlatformParameter参数基类
	 * @return  根据实体 类和参数基类来返回填充好的参数实体类
	 */
	@SuppressWarnings("unused")
	private Object ctrateParEntity(Object clObj, PlatformParameter pp) {
		try {
	
		String parValueColl =pp.getParValueColl();
		//解析jason字符串
		if(parValueColl!=null&&parValueColl.contains("{")){
			parValueColl=parValueColl.replace("{", "");
		}
		if(parValueColl!=null&&parValueColl.contains("}")){
			parValueColl=parValueColl.replace("}", "");
		}
		parValueColl=parValueColl.trim();
		
		String[]parArr=parValueColl.split(",");
		if(parArr!=null){
			for(int i=0;i<parArr.length;i++){
				String tem=parArr[i];
				String fieldName=tem.split(":")[0]; //"key"
				fieldName=fieldName.replaceAll("\"","");
				String fieldValue=tem.split(":")[1];//"value"
				fieldValue=fieldValue.replaceAll("\"","");
				 String methodName=conversionMethod(fieldName); //获取set方法
			Method md=	clObj.getClass().getMethod(methodName,fieldValue.getClass());
			md.invoke(clObj, fieldValue);
			}
		}
		return clObj;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * @return  返回所有在库的参数项的实体
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public  List<Object> getAllPar(){
		List<Object> objList=new ArrayList<Object>();
		//取出所有的平台化参数		
//		String hql = "from PlatformParameter where  isDelete = 0";
		String hql = " isDelete = 0 ";
		List<PlatformParameter> ppList = super.findAll(hql);
		if(ppList!=null&&ppList.size()>0){
			for(int i=0;i<ppList.size();i++){
				//try catch如果在获取某一个对象出现异常的时不影响其他对象的加载
				try {
					PlatformParameter pp=ppList.get(i);
					String className=pp.getParItems();
					Class cl=Class.forName(className);
					Object clObj= cl.newInstance();
					Gson gson = new Gson();
					//将jason格式的字符串转化成对应的对象
					Object obj = gson.fromJson(pp.getParValueColl(), cl);
				//	Object ot=ctrateParEntity(clObj,pp);
					objList.add(obj);
				} 
				catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}	
		return objList;
		
	}
	
	
	
	public static void main(String[] args) {
		
		PlatformParameterDao pp=new PlatformParameterDao();
	Method[]arr=	pp.getClass().getMethods();
	for(int i=0;i<arr.length;i++){
		
//		System.out.println(arr[i].getName());
		
		
	}
	
//		System.out.println(pp.conversionField("getTtring"));
//		
//		 Notice nt =new Notice();
//		 PlatformParameter pf =new PlatformParameter();
//		 pf.setParValueColl("{title:1,contentInfo:3}");
//		 
//		 System.out.println(pp.ctrateParEntity(nt, pf));
		
	}
	
	
}
