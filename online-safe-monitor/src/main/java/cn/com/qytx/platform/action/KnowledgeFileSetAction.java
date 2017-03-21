package cn.com.qytx.platform.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.domain.KnowledgeFileSet;

public class KnowledgeFileSetAction extends BaseActionSupport{

	private static final long serialVersionUID = 1L;
	
	private KnowledgeFileSet kfs;

	public KnowledgeFileSet getKfs() {
		return kfs;
	}

	public void setKfs(KnowledgeFileSet kfs) {
		this.kfs = kfs;
	}
	
	/**
	 * 保存操作
	 * @return
	 */
	public String knowledgeFileSetSave(){
		PrintWriter out = null;
		InputStream fis = null;
		FileOutputStream fos = null;
		try {
			out = this.getResponse().getWriter();
			//初始化输入流
			String filePath = Thread.currentThread().getContextClassLoader().getResource("voxFilePath.properties").getPath();
			File file = new File(filePath);
			fis = new FileInputStream(file);
			Properties prop = new Properties();
			//加载properties
			prop.load(fis);
			//设置properties属性
			prop.setProperty("lunceneRoot", kfs.getLunceneRoot());
			prop.setProperty("lunceneInitFile", kfs.getLunceneInitFile());
			prop.setProperty("knowledgeUpload", kfs.getKnowledgeUpload());
			
			//关闭输入流 
			fis.close();  
			// 文件输出流
			File fileOut=new File(filePath);
			fos = new FileOutputStream(fileOut); 
			// 将Properties集合保存到流中 
			prop.store(fos, "Copyright (c) "); 
			// 关闭流 
			fos.close();
			
			out.print(1);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}finally{
			if(out!=null){
			    out.close();
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}	
			}
			if(fos!=null){
				
				try {
					fos.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
		}
		
		return null;
	}
	
	public String knowledgeFileSetView(){
		InputStream fis = null;
		try {
			//文件输入流
			String fileSt=Thread.currentThread().getContextClassLoader().getResource("voxFilePath.properties").getPath();
			File file= new File(fileSt);
			  fis = new FileInputStream(file);   
			Properties properties = new Properties();
			//加载文件
			properties.load(fis);
			//取出属性
			String lunceneRoot=	properties.getProperty("lunceneRoot");
			String lunceneInitFile=	properties.getProperty("lunceneInitFile");
			String knowledgeUpload=	properties.getProperty("knowledgeUpload");
			KnowledgeFileSet kfsTem=new KnowledgeFileSet();
			kfsTem.setLunceneRoot(lunceneRoot);
			kfsTem.setLunceneInitFile(lunceneInitFile);
			kfsTem.setKnowledgeUpload(knowledgeUpload);
			
			this.getRequest().setAttribute("kfs", kfsTem);
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}finally{
				if(fis!=null){
					try {
						fis.close();
					} catch (IOException e) {
						LOGGER.error(e.getMessage());
					}
				}
			}
		
		return SUCCESS;
		
	}

}
