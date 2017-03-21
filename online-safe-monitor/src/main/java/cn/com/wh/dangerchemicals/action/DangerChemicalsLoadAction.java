package cn.com.wh.dangerchemicals.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.dangerchemicals.domain.ChemicalsDirectory;
import cn.com.wh.dangerchemicals.domain.DangerChemicalsInfo;
import cn.com.wh.dangerchemicals.service.ChemicalsDirectoryService;
import cn.com.wh.dangerchemicals.service.DangerChemicalsService;
import cn.com.wh.safeaccident.util.Tool;

/**
 * 公司危险化学品
 * @author lilipo
 *
 */
public class DangerChemicalsLoadAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4201805059394628544L;
	
	/**
	 * 公司危化品接品
	 */
	@Autowired
	private DangerChemicalsService dcService;
	
	/**
	 * 公司危化品目录接品
	 */
	@Autowired
	private ChemicalsDirectoryService cdService;
	
    /**
     * 系统日志接口
     */
    @Resource
    private ILog logService;
	
//	/**
//	 * 附件接品
//	 */
//	@Autowired
//	private IAttachment attachmentService;

	/**
	 * 公司危化品对象
	 */
	private DangerChemicalsInfo dangerChemicalsInfo;
	
	private List<DangerChemicalsInfo> dangerChemicalsInfoList;
	
	/**
	 * 公司危化品目录对象
	 */
	private ChemicalsDirectory chemicalsDirectory;
	
	private List<ChemicalsDirectory> chemicalsDirectoryList;
	
	/**
	 * 查询一个对象
	 * @return
	 */
	public String findOne(){
		if(dangerChemicalsInfo != null){
			if(dangerChemicalsInfo.getVid() != null){
				try{
					dangerChemicalsInfo = dcService.findOne(dangerChemicalsInfo.getVid());
				}catch(Exception e){
					LOGGER.error("通过主键查询危险化学品时出错", e);
				}
				
			}
		}
		// 危险化学品的目录ID
		Integer catalogId = dangerChemicalsInfo == null ? null : dangerChemicalsInfo.getCatalogId();
		
		//获取 所有目录ID的集合 以及 当前的危险化学品目录对象
		chemicalsDirectoryList = cdService.findAll();
		if(catalogId != null && chemicalsDirectoryList != null && !chemicalsDirectoryList.isEmpty()){
			for(ChemicalsDirectory cd: chemicalsDirectoryList){
				if(cd.getVid().intValue() == catalogId.intValue()){
					chemicalsDirectory = cd;
					break;
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
			dangerChemicalsInfoList = dcService.findAll();
		}catch(Exception e){
			LOGGER.error("通过主键查询危险化学品时出错", e);
		}
		return "success";
	}
	
	
	/**
	 * 删除一个对象
	 * @return
	 */
	public String deleteOne() {
		int result = 0;//0表示失败
		if(dangerChemicalsInfo != null){
			if(dangerChemicalsInfo.getVid() != null){
				try{
					dangerChemicalsInfo = dcService.findOne(dangerChemicalsInfo.getVid());
					dangerChemicalsInfo.setIsDelete(1);
					dcService.saveOrUpdate(dangerChemicalsInfo);
					result = 1;//1表示成功
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"删除危险化学品成功", 
							LogType.LOG_DANGERH_DELETE, 
							dangerChemicalsInfo, 
							dangerChemicalsInfo.getVid()) );
				}catch(Exception e){
					LOGGER.error("通过主键删除危险化学品时出错", e);
				}
			}
		}
		try {
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error("删除危险化学品后，往页面返回数据时出错", e);
		}
		return null;
	}


	public DangerChemicalsInfo getDangerChemicalsInfo() {
		return dangerChemicalsInfo;
	}

	public void setDangerChemicalsInfo(DangerChemicalsInfo dangerChemicalsInfo) {
		this.dangerChemicalsInfo = dangerChemicalsInfo;
	}

	public List<DangerChemicalsInfo> getDangerChemicalsInfoList() {
		return dangerChemicalsInfoList;
	}

	public void setDangerChemicalsInfoList(
			List<DangerChemicalsInfo> dangerChemicalsInfoList) {
		this.dangerChemicalsInfoList = dangerChemicalsInfoList;
	}

	public ChemicalsDirectory getChemicalsDirectory() {
		return chemicalsDirectory;
	}

	public void setChemicalsDirectory(ChemicalsDirectory chemicalsDirectory) {
		this.chemicalsDirectory = chemicalsDirectory;
	}

	public List<ChemicalsDirectory> getChemicalsDirectoryList() {
		return chemicalsDirectoryList;
	}

	public void setChemicalsDirectoryList(
			List<ChemicalsDirectory> chemicalsDirectoryList) {
		this.chemicalsDirectoryList = chemicalsDirectoryList;
	}
	
}
