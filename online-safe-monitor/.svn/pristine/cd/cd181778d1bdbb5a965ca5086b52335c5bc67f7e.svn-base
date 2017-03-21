package cn.com.wh.zhdwxy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.zhdwxy.domain.DangerSourcesGood;
import cn.com.wh.zhdwxy.service.IDangerSourcesGood;

/**
 * 类
 * @author lilipo
 *
 */
public class DangerSourcesGoodLoadAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -700207467937573674L;

	/**
	 * 接口
	 */
	@Autowired
	private IDangerSourcesGood dsgService;
	
    /**
     * 系统日志接口
     */
    @Resource
    private ILog logService;
	
//	/**
//	 * 附件接口
//	 */
//	@Autowired
//	private IAttachment attachmentService;

	/**
	 * 对象
	 */
	private DangerSourcesGood dangerSourcesGood;
	
	private List<DangerSourcesGood> dangerSourcesGoodList;
	
    /**
     * 危险性类别数据字典的Map
     */
    private Map<Integer, String> dictMap;
    
    private String infoType;
    
    private Integer operation;
    
	
	/**
	 * 查询一个对象
	 * @return
	 */
	public String findOne(){
		//获取危险性类别数据字典
		dictMap = Tool.loadaccidentCharacterTypemap(infoType == null ? "dangerObjType" : infoType);
		
		if(dangerSourcesGood != null){
			if(dangerSourcesGood.getVid() != null){
				try{
					dangerSourcesGood = dsgService.findOne(dangerSourcesGood.getVid());
					
					if(dictMap != null){
						dangerSourcesGood.setDictIdName(dictMap.get(dangerSourcesGood.getDictId()));
					}
					
				}catch(Exception e){
					LOGGER.error("通过主键查询重大危险源危化品时出错", e);
				}
			}
		}
		
		return "success";
	}
	
	/**
	 * 查询所有对象
	 * @return
	 */
	public String findAll(){
		try{
			dangerSourcesGoodList = dsgService.findAll();
		}catch(Exception e){
			LOGGER.error("通过主键查询重大危险源危化品目录对象时出错", e);
		}
		return "success";
	}
	
	
	/**
	 * 删除一个对象
	 * @return
	 */
	public String deleteOne() {
		int result = 0;//0表示失败
		if(dangerSourcesGood != null){
			if(dangerSourcesGood.getVid() != null){
				try{
					dangerSourcesGood = dsgService.findOne(dangerSourcesGood.getVid());
					dangerSourcesGood.setIsDelete(1);
					dsgService.saveOrUpdate(dangerSourcesGood);
					result = 1;//1表示成功
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"删除重大危险源危化品目录对象成功", 
							LogType.LOG_DSG_DELETE, 
							dangerSourcesGood, 
							dangerSourcesGood.getVid()) );
				}catch(Exception e){
					LOGGER.error("删除重大危险源危化品目录对象时出错", e);
				}
			}
		}
		try {
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error("删除重大危险源危化品目录对象后，往页面返回数据时出错", e);
		}
		return null;
	}

	public DangerSourcesGood getDangerSourcesGood() {
		return dangerSourcesGood;
	}

	public void setDangerSourcesGood(DangerSourcesGood dangerSourcesGood) {
		this.dangerSourcesGood = dangerSourcesGood;
	}

	public List<DangerSourcesGood> getDangerSourcesGoodList() {
		return dangerSourcesGoodList;
	}

	public void setDangerSourcesGoodList(
			List<DangerSourcesGood> dangerSourcesGoodList) {
		this.dangerSourcesGoodList = dangerSourcesGoodList;
	}

	public Map<Integer, String> getDictMap() {
		return dictMap;
	}

	public void setDictMap(Map<Integer, String> dictMap) {
		this.dictMap = dictMap;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

}
