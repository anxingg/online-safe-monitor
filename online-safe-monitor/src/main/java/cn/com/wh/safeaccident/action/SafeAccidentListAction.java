package cn.com.wh.safeaccident.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.file.domain.Attachment;
import cn.com.qytx.cbb.file.service.IAttachment;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.wh.safeaccident.domain.SafeAccident;
import cn.com.wh.safeaccident.domain.SafeAccidentStatisticsResult;
import cn.com.wh.safeaccident.service.SafeAccidentService;
import cn.com.wh.safeaccident.util.ReadProperties;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.util.DataInitUtil;

import com.google.gson.Gson;

/**
 * 列表
 * 
 * @author lilipo
 * 
 */
public class SafeAccidentListAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6492798190208991478L;

	/**
	 * 列表中的列没有数据
	 */
	private static final String LIST_COLS_NO_DATA = "-";

	/**
	 * 时间格式
	 */
	private static final String FORMAT_TIME_ALL = "yyyy-MM-dd HH:mm:ss";

	// /**
	// * 数据字典接口
	// */
	// @Autowired
	// IDict dictService;

	/**
	 * 安全生产事故的接口
	 */
	@Autowired
	SafeAccidentService safeAccidentService;

	// /**
	// * 公司接口
	// */
	// @Autowired
	// public IWHCompany companyImpl;

	/**
	 * 附件接品
	 */
	@Autowired
	private IAttachment attachmentService;

	/**
	 * 事故生产安全对象
	 */
	private SafeAccident safeAccident;
	private String companyName;
	private Integer whroletype;
	private String beginTime;
	private String endTime;
	private Integer attachmentId;

	private Map<Integer, String> accidentCharacterTypemap;

	/**
	 * 分页查询列表
	 * 
	 * @return
	 */
	public String getList() {
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;

			// 创建排序规则
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "groupId"),new Sort.Order(Direction.DESC, "createTime"));

			// String occurredDescription = safeAccident == null ? null
			// : safeAccident.getOccurredDescription();
			Integer accidentCharacter = safeAccident == null ? null
					: safeAccident.getAccidentCharacter();
			String accidentName = safeAccident == null ? null : safeAccident
					.getAccidentName();
			Integer groupId = safeAccident.getGroupId();

			String keyword = safeAccident == null ? null : safeAccident
					.getKeyword();

			Date beginDate = null;
			Date endDate = null;
			if (safeAccident != null
					&& StringUtils
							.isNotBlank(safeAccident.getOccurredTimeStr())) {
				try {
					beginDate = Tool.getDateByString(
							safeAccident.getOccurredTimeStr()
									+ "-01-01 00:00:00.0", null,
							"yyyy-MM-dd HH:mm:ss.mis");
					endDate = Tool.getDateByString(
							safeAccident.getOccurredTimeStr()
									+ "-12-31 23:59:59.999", null,
							"yyyy-MM-dd HH:mm:ss.mis");
				} catch (Exception e) {
					LOGGER.error("安全生产事故查询列表，事故时间传参格式转换出错。", e);
				}
			}

			// 查询
			Page<SafeAccident> pageInfo = safeAccidentService.findByPage(
					this.getPageable(sort), keyword, accidentCharacter,
					accidentName, whroletype, groupId, beginDate, endDate);

			List<SafeAccident> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && !list.isEmpty()) {

				// 获取数据字典Map<value, name>
				accidentCharacterTypemap = Tool.loadaccidentCharacterTypemap();

				for (SafeAccident safeAccident : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 序号
					map.put("no", i);

					// 主键
					map.put("vid", safeAccident.getVid());

					// 数据来源
					map.put("dataSource", safeAccident.getDataSource());

					// 事故简介
					map.put("occurredDescription", StringUtils.defaultString(
							safeAccident.getOccurredDescription(),
							LIST_COLS_NO_DATA));

					// 企业名称
					map.put("groupName",
							safeAccident.getGroupId() == null ? LIST_COLS_NO_DATA
									: StringUtils.defaultString(
											DataInitUtil.companyMap
													.get(safeAccident
															.getGroupId()),
											LIST_COLS_NO_DATA));

					// 事故名称
					map.put("accidentName", StringUtils.defaultString(
							safeAccident.getAccidentName(), LIST_COLS_NO_DATA));

					// 企业端是否可见
					map.put("canSee", safeAccident.getCanSee() == null
							|| safeAccident.getCanSee() == 0 ? "不可见" : "可见");
					
					int accchar = null==safeAccident.getAccidentCharacter()?-1:safeAccident.getAccidentCharacter();
					
					// 事故性质
					map.put("accidentCharacter",null==accidentCharacterTypemap.get(accchar)?LIST_COLS_NO_DATA:accidentCharacterTypemap.get(accchar));
						

					// 事故处理情况
					map.put("processCondition", StringUtils.defaultString(
							safeAccident.getProcessCondition(),
							LIST_COLS_NO_DATA));

					// 事故调查人员/部门
					map.put("responsible", StringUtils.defaultString(
							safeAccident.getResponsible(), LIST_COLS_NO_DATA));

					// 事故报告ID
					map.put("reportId", StringUtils.defaultString(
							safeAccident.getReportId(), LIST_COLS_NO_DATA));

					// 事故报告名称
					map.put("reportName", StringUtils.defaultString(
							safeAccident.getReportName(), LIST_COLS_NO_DATA));

					mapList.add(map);
					i++;
				}
			}
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("iTotalRecords", pageInfo.getTotalElements());
			jsonMap.put("iTotalDisplayRecords", pageInfo.getTotalElements());
			jsonMap.put("aaData", mapList);
			Gson json = new Gson();
			String jsons = json.toJson(jsonMap);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error("安全生产事故查询列表出错", e);
		}
		return null;
	}

	/**
	 * 统计分页查询列表
	 * 
	 * @return
	 */
	public String getStatisticsList() {
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;

			// 创建排序规则
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));

			Integer accidentCharacter = safeAccident == null ? null
					: safeAccident.getAccidentCharacter();
			Integer groupId = safeAccident.getGroupId();
			Timestamp occurredTimeBegin = Tool.getTimestampByString(beginTime,
					FORMAT_TIME_ALL);
			Timestamp occurredTimeEnd = Tool.getTimestampByString(endTime,
					FORMAT_TIME_ALL);

			// 查询
			Page<SafeAccidentStatisticsResult> pageInfo = safeAccidentService
					.findStatisticsResultByPage(this.getPageable(sort),
							occurredTimeBegin, occurredTimeEnd,
							accidentCharacter, whroletype, groupId);

			List<SafeAccidentStatisticsResult> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {

				// 获取数据字典Map<value, name>
				accidentCharacterTypemap = Tool.loadaccidentCharacterTypemap();

				for (SafeAccidentStatisticsResult sasr : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 序号
					map.put("no", i);

					// 事故性质
					map.put("accidentCharacter", sasr.getAccidentCharacter());

					// 事故性质名称
					map.put("accidentCharacterName",
							sasr.getAccidentCharacter() == null ? LIST_COLS_NO_DATA
									: StringUtils.defaultString(
											accidentCharacterTypemap.get(sasr
													.getAccidentCharacter()),
											LIST_COLS_NO_DATA));

					// 企业ID
					map.put("groupId", sasr.getGroupId());

					// 企业名称
					map.put("groupName",
							sasr.getGroupId() == null ? LIST_COLS_NO_DATA
									: StringUtils.defaultString(
											DataInitUtil.companyMap.get(sasr
													.getGroupId()),
											LIST_COLS_NO_DATA));

					// 数量
					map.put("count", sasr.getCount());

					mapList.add(map);
					i++;
				}
			}
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("iTotalRecords", pageInfo.getTotalElements());
			jsonMap.put("iTotalDisplayRecords", pageInfo.getTotalElements());
			jsonMap.put("aaData", mapList);
			Gson json = new Gson();
			String jsons = json.toJson(jsonMap);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error("安全生产事故统计分页查询列表", e);
		} catch (ParseException e) {
			LOGGER.error("安全生产事故统计分页查询列表", e);
		}
		return null;
	}

	/*
	 * public String downFileByFilePath() { HttpServletResponse response =
	 * this.getResponse(); response.reset(); // 文件下载时采用文件流输出的方式处理：
	 * response.setContentType("application/x-download"); java.io.OutputStream
	 * outp = null; java.io.FileInputStream in = null; try { List<Attachment>
	 * attalist = attachmentService
	 * .getAttachmentsByIds(attachmentId.toString()); Attachment attachment =
	 * attalist == null ? null : attalist.get(0); String filePath =
	 * attachment.getAttachFile(); if (StringUtils.isEmpty(filePath)) {
	 * response.getWriter().print("filePath为空，无法下载");
	 * response.getWriter().flush(); return null; } String startPath =
	 * ReadProperties.readPropertiesValue( "application.properties",
	 * "fileUploadPath"); // 获取文件保存路径 String fileRealPath = startPath +
	 * "upload/" + filePath;
	 * 
	 * String fileName = attachment.getAttachName(); // fileName =
	 * fileName.substring(0, fileName.lastIndexOf(".")); fileName =
	 * URLEncoder.encode(fileName, "UTF-8");
	 * response.addHeader("Content-Disposition", "attachment;filename=" +
	 * fileName); outp = response.getOutputStream(); in = new
	 * FileInputStream(fileRealPath); byte[] b = new byte[1024]; int i = 0;
	 * while ((i = in.read(b)) > 0) { outp.write(b, 0, i); } outp.flush(); }
	 * catch (Exception e) { LOGGER.error("附件下载时出错", e); } finally { if (in !=
	 * null) { try { in.close(); } catch (IOException e) {
	 * LOGGER.error("附件下载，关闭流时出错", e); } in = null; } } return null; }
	 */

	/**
	 * 根据FilePath下载文件，处理未存数据库的附件
	 */
	public String downFileByFilePath() {
		HttpServletResponse response = this.getResponse();
		response.reset();
		// 获取用户浏览器信息
		Map<String, Object> browerInfoMap = getBrowerInfo(this.getRequest());

		// 文件下载时采用文件流输出的方式处理：
		response.setContentType("application/x-download");

		java.io.OutputStream outp = null;
		java.io.InputStream in = null;
		try {
			List<Attachment> attalist = attachmentService
					.getAttachmentsByIds(attachmentId.toString());
			Attachment attachment = attalist == null ? null : attalist.get(0);
			String filePath = attachment.getAttachFile();
			if (StringUtils.isEmpty(filePath)) {
				response.getWriter().print("filePath为空，无法下载");
				response.getWriter().flush();
				return null;
			}
			// String startPath = (String)
			// this.getSession().getAttribute("downPath");
			// if(StringUtils.isNotBlank(startPath)){
			// startPath = startPath.replaceAll("\\:", ":");
			// }
			String startPath = ReadProperties.readPropertiesValue(
					"application.properties", "downloadPath");
			// 获取文件保存路径
			String fileRealPath = startPath + "upload/" + filePath;
			LOGGER.info("下载文件时间：" + new Date() + "下载文件URL=" + fileRealPath);
			URL url = new URL(fileRealPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置超时间为3秒
			conn.setConnectTimeout(3 * 1000);
			String userAgent = browerInfoMap.get("User-agent").toString();
			// 防止屏蔽程序抓取而返回403错误
			// conn.setRequestProperty("User-Agent",
			// "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			conn.setRequestProperty("User-Agent", userAgent);
			response.setCharacterEncoding("utf-8");

			String fileName = attachment.getAttachName();
			if (userAgent.toUpperCase().contains("FIREFOX")
					|| userAgent.toUpperCase().contains("CHROME")
					|| userAgent.toUpperCase().contains("OPR")
					|| userAgent.toUpperCase().contains("SAFARI")) {

				fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
			} else {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			}

			response.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			outp = response.getOutputStream();
			// 得到输入流
			in = conn.getInputStream();
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = in.read(b)) > 0) {
				outp.write(b, 0, i);
			}
			outp.flush();
		} catch (Exception e) {
			LOGGER.error("附件下载时出错", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LOGGER.error("附件下载，关闭流时出错", e);
				}
				in = null;
			}
		}
		return null;
	}

	/**
	 * 获取浏览器信息
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, Object> getBrowerInfo(
			javax.servlet.http.HttpServletRequest request) {
		Map<String, Object> browerInfoMap = new HashMap<String, Object>();

		browerInfoMap.put("User-agent", request.getHeader("User-agent"));// 返回客户端浏览器的版本号、类型

		// getHeader(String name);//获得http协议定义的传送文件头信息，

		browerInfoMap.put("Method", request.getMethod());// 获得客户端向服务器端传送数据的方法有GET、POST、PUT等类型

		browerInfoMap.put("RequestURI", request.getRequestURI());// 获得发出请求字符串的客户端地址

		browerInfoMap.put("ServletPath", request.getServletPath());// 获得客户端所请求的脚本文件的文件路径

		browerInfoMap.put("ServerName", request.getServerName());// 获得服务器的名字

		browerInfoMap.put("ServerPort", request.getServerPort());// 获得服务器的端口号

		browerInfoMap.put("RemoteAddr", request.getRemoteAddr());// 获得客户端的IP地址

		browerInfoMap.put("RemoteHost", request.getRemoteHost());// 获得客户端电脑的名字，若失败，则返回客户端电脑的IP地址

		browerInfoMap.put("Protocol", request.getProtocol());//

		browerInfoMap.put("HeaderNames", request.getHeaderNames());// 返回所有request
																	// header的名字，结果集是一个Enumeration（枚举）类的实例
		// System.out.println(browerInfoMap);
		return browerInfoMap;
	}

	public SafeAccident getSafeAccident() {
		return safeAccident;
	}

	public void setSafeAccident(SafeAccident safeAccident) {
		this.safeAccident = safeAccident;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getWhroletype() {
		return whroletype;
	}

	public void setWhroletype(Integer whroletype) {
		this.whroletype = whroletype;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

}
