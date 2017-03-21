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
import cn.com.wh.dangerchemicals.service.ChemicalsDirectoryService;
import cn.com.wh.safeaccident.util.Tool;

/**
 * 公司危险化学品
 * @author lilipo
 *
 */
public class ChemicalsDirectoryLoadAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4071930501313901709L;

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
	 * 公司危化品目录对象
	 */
	private ChemicalsDirectory chemicalsDirectory;
	
	private List<ChemicalsDirectory> chemicalsDirectoryList;
	
	/**
	 * 查询一个对象
	 * @return
	 */
	public String findOne(){
		if(chemicalsDirectory != null){
			if(chemicalsDirectory.getVid() != null){
				try{
					chemicalsDirectory = cdService.findOne(chemicalsDirectory.getVid());
				}catch(Exception e){
					LOGGER.error("通过主键查询危险化学品时出错", e);
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
			chemicalsDirectoryList = cdService.findAll();
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
		if(chemicalsDirectory != null){
			if(chemicalsDirectory.getVid() != null){
				try{
					chemicalsDirectory = cdService.findOne(chemicalsDirectory.getVid());
					chemicalsDirectory.setIsDelete(1);
					cdService.saveOrUpdate(chemicalsDirectory);
					result = 1;//1表示成功
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"删除危险化学品目录成功", 
							LogType.LOG_CHEMICALSDIR_DELETE, 
							chemicalsDirectory, 
							chemicalsDirectory.getVid()) );
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
