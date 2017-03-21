package cn.com.qytx.hotline.util;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.com.qytx.platform.base.action.BaseActionSupport;

/**
 * 文件传送客户端:获取远程文件
 */
@SuppressWarnings("serial")
public class GetRemoteFile_Client_GoOn extends BaseActionSupport {
	public GetRemoteFile_Client_GoOn() {
	}

	@SuppressWarnings("unused")
	private boolean FileExist(String pathAndFile) // 确定文件是否已经下载，但没有下载完成
	{
		File file = new File(pathAndFile);
		if (file.exists()){
			return true;
		}
		else{
			return false;
		}
	}

	private long FileSize(String pathAndFile) // 确定已经下载了的文件大小
	{
		File file = new File(pathAndFile);
		return file.length();
	}

	private void FileRename(String fName, String nName) // 将下载完全的文件更名，去掉.tp名
	{
		File file = new File(fName);
		try {
			//添加boolean原因，findBugs报错
			boolean renameBoolean = file.renameTo(new File(nName));
			if(!renameBoolean){
				LOGGER.info("文件更名失败");
			}
			boolean fileDelete = file.delete();
			if(!fileDelete){
				LOGGER.info("文件删除失败");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * @param fileUrls
	 *            远程文件的url集合
	 * @param localDir
	 *            下载到本地的目录
	 */
	@SuppressWarnings("unused")
	public static void downLoadFiles(List<String> fileUrls, String localDir) {

		File file = new File(localDir);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			try {
				file.mkdirs();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		} 

		for (String fileUrl : fileUrls) {
			// 取出文件的名称
			int lastLength = fileUrl.lastIndexOf("/");
			String fileName = fileUrl.substring(lastLength + 1);
			URL url = null;
			HttpURLConnection urlc = null;
			DataOutputStream dos = null;
			BufferedInputStream bis = null;
			FileOutputStream fos = null;
			String localFile = localDir + "/" + fileName; // 文件保存的地方及文件名，具体情况可以改
			String localFile_bak = localFile; // 未下载完文件加.tp扩展名，以便于区别
			GetRemoteFile_Client_GoOn gco = new GetRemoteFile_Client_GoOn();
			long start = System.currentTimeMillis();
			int len = 0;
			byte[] bt = new byte[1024];
			RandomAccessFile raFile = null;
			long TotalSize = 0; // 要下载的文件总大小
			try {
				url = new URL(fileUrl);
				urlc = (HttpURLConnection) url.openConnection();
				fos = new FileOutputStream(localFile_bak); // 没有下载完毕就将文件的扩展名命名.bak
				dos = new DataOutputStream(fos);
				bis = new BufferedInputStream(urlc.getInputStream());
				len = bis.read(bt);
				while (len > 0){ // 循环获取文件
					dos.write(bt, 0, len);
					len = bis.read(bt);
				}
				if (bis != null){
					bis.close();
				}
				if (dos != null){
					dos.close();
				}
				if (fos != null){
					fos.close();
				}
//				if (raFile != null){
//					raFile.close();
//				}
				if (gco.FileSize(localFile_bak) == TotalSize) // 下载完毕后，将文件重命名
				{
					gco.FileRename(localFile_bak, localFile);
				}
			} catch (Exception e) {
				try {
					if (bis != null){
						bis.close();
					}
					if (dos != null){
						dos.close();
					}
					if (fos != null){
						fos.close();
					}
//					if (raFile != null){
//						raFile.close();
//					}
				} catch (IOException f) {
					LOGGER.error(f.getMessage());
				}
				LOGGER.error(e.getMessage());
			}
		}

	}

	public static void main(String[] args) {
		// 从远程下载
		List<String> urlList = new ArrayList<String>();
		urlList.add("http://10.100.2.22:8080/voxHotline/user/140504/1/93/201405041540370300_93.wav");
		urlList.add("http://10.100.2.22:8080/voxHotline/user/140504/1/93/201405041544170301_93.wav");
		urlList.add("http://10.100.2.22:8080/voxHotline/user/140504/1/93/201405041555540302_93.wav");
		urlList.add("http://10.100.2.22:8080/voxHotline/user/140504/1/93/201405041604110304_93.wav");
		urlList.add("http://10.100.2.22:8080/voxHotline/user/140504/1/93/201405041606430305_93.wav");
		urlList.add("http://10.100.2.22:8080/voxHotline/user/140504/1/93/201405041719500317_93.wav");
		GetRemoteFile_Client_GoOn.downLoadFiles(urlList, "D://luyin");
		// 打包成zip文件
		String sourcePath = "D:" + File.separator + "luyin";
		String zipPath = "D:" + File.separator + "luyin.zip";
		String comment = "";
		try {
			ZipUtil.compress(sourcePath, zipPath, "GBK", comment);
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

	}
}
