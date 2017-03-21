package cn.com.qytx.hotline.outcall.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.org.action.CheckUtil;
import cn.com.qytx.cbb.org.util.ExportExcel;
import cn.com.qytx.hotline.outcall.domain.OutCallSegment;
import cn.com.qytx.hotline.outcall.service.IOutCallSegment;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能： 本地号段的维护，包括新增号段，导入、导出号段
 * 
 * @author 蔡明雪 创建日期 : 2014-03-07
 * 
 */
public class OutCallSegmentAction extends BaseActionSupport {

	protected static final Logger logger = Logger.getLogger(OutCallSegmentAction.class);
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Autowired
	private IOutCallSegment outcallSegmentService;

	private String phone;
	private File fileToUpload;// 上传文件
	private String uploadFileName;// 上传文件的文件名
	private String file;// 上传的文件，完整路径的文件。
	private int allNum = 0;// 总个数
	private int successNum = 0;// 成功个数
	private int skipNum = 0;// 跳过个数
	private int errorNum = 0;// 错误个数
	private List<OutCallSegment> errorSegmentList = new ArrayList<OutCallSegment>();
	private String errorFile;
	private String addphone; // 添加的号段

	

	public void getHaoduanList() {

		Page<OutCallSegment> page = outcallSegmentService.
					findOutCallSegmentPageList(getPageable(new Sort(Direction.DESC, "createTime")), phone);

		// 得到记录列表
		List<OutCallSegment> list = page.getContent();
		// 将List转化为MapList
		List<Map<String, Object>> mapList = listToMaplist(list);

		try {
			this.ajaxPage(page, mapList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增号段
	 */
	public String phoneAdd() {
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();

			// 判断增加的号段是否已添加过，避免重复
			OutCallSegment tmpocs = outcallSegmentService.findOutCallSegmentByPhone(addphone);
			if (null != tmpocs) {
				// 说明此时号段已经存在
				out.print(2);
				return null;
			}

			UserInfo adminUser = getLoginUser();

			OutCallSegment ocs = new OutCallSegment();
			ocs.setCreateUserInfo(adminUser);
			ocs.setCreateTime(new Timestamp(System.currentTimeMillis()));
			ocs.setIsDelete(0);
			ocs.setPhone(addphone);
			ocs.setCheckState(0);
			this.outcallSegmentService.saveOutCallSegment(ocs);

			// result = "1";
			out.print(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			out.print("号段更新失败！");
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

		return null;
	}

	/**
	 * 删除号码段的号码
	 * 
	 * @param list
	 * @return
	 */
	public String deletePhone() {
		PrintWriter out = null;

		try {
			out = this.getResponse().getWriter();
			this.outcallSegmentService.deleteOutCallSegmentById(vId);
			out.print(1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			out.print(2);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

		return null;
	}

	public List<Map<String, Object>> listToMaplist(List<OutCallSegment> list) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// int orderNum=0; //序号
		int i = getPageable().getPageNumber() * getPageable().getPageSize() + 1;

		if (list != null && list.size() > 0) {
			for (OutCallSegment o : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vid", o.getVid());
				map.put("orderNum", i);
				i++;
				map.put("phone", o.getPhone());
				map.put("addTime", sdf.format(o.getCreateTime()));
				map.put("action", "");

				mapList.add(map);
			}
		}

		return mapList;
	}

	/**
	 * 导入号段
	 */
	public void importPhone() throws Exception {

		PrintWriter writer = null;

		try {

			if (uploadFile()) { // 如果上传文件成功（接下来就可以导入了）
				String result = startImportPhone(file);
				this.getResponse().setHeader("ContentType", "text/json");
				this.getResponse().setCharacterEncoding("utf-8");
				this.getResponse().setContentType("text/html;charset=utf-8");
				writer = new PrintWriter(this.getResponse().getWriter());
				if (allNum > 0) {
					int failNum = allNum - successNum - skipNum - errorNum;// 失败条数,导入文件中的记录不是电话号码
					if (failNum < 0) {
						failNum = 0;
					}

					result = "共" + allNum + "行数据，导入成功" + (successNum) + "行，跳过"
							+ skipNum + "行，失败" + errorNum + "行";
				}
				writer.print(result);
				writer.close();
			}

		} catch (Exception e) {
			logger.error(e);
			writer = new PrintWriter(this.getResponse().getWriter());
			writer.print("对不起，导入号段错误！");
			writer.close();
		}
	}

	/**
	 * 先上传号段文件，然后实现导入文件----------上传
	 */
	private boolean uploadFile() {
		@SuppressWarnings("deprecation")
		String path = ServletActionContext.getRequest().getRealPath("/upload");
		File checkPath = new File(path);
		if (!checkPath.exists()) {
			// 目录不存在，则创建目录
			try {
				checkPath.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			if (fileToUpload != null) {
				String fileName = UUID.randomUUID().toString();
				String ext = ".xls";
				if (uploadFileName != null) {
					ext = uploadFileName.substring(uploadFileName
							.lastIndexOf("."));
				}
				if (!ext.equals(".xls")) {
					return false;
				}
				String saveFileName = fileName + ext;
				File savefile = new File(new File(path), saveFileName);
				if (!savefile.getParentFile().exists())
					try {
						savefile.getParentFile().mkdirs();
					} catch (Exception e) {
						e.printStackTrace();
					}
				FileUtils.copyFile(fileToUpload, savefile);// 拷贝文件，实现上传
				file = path + "/" + saveFileName;

			}

		} catch (Exception e) {

			return false;
		}
		return true;
	}

	/**
	 * 真正开始导入号段(但是导入之前还要检查一下excel表格)
	 */
	private String startImportPhone(String excel) throws Exception {

		String check = checkExcel(excel);
		if (!check.equals("")) {
			return check;
		} else {
			// 导入号段，首先应该把文件中的所有记录转换成一个List
			List<OutCallSegment> segmentList = getSegmentList(excel);
			if (segmentList != null && segmentList.size() > 0) {

				allNum = segmentList.size();

				for (OutCallSegment ocs : segmentList) {
					try {
						// 判断重复，该号段是否已经存在

						OutCallSegment tempocs = this.outcallSegmentService
								.findOutCallSegmentByPhone(ocs.getPhone());
						if (null != tempocs) {
							// 如果号段已经存在，跳过
							// this.outcallSegmentService.deleteOutCallSegmentById(ocs.getVid());
							skipNum++; // 跳过数加1
							continue;
						}

						// 电话号码或为空，或数据格式不正确，或数字的长度过长
						if (ocs.getPhone() == null || ocs.getPhone().equals("")
								|| ocs.getPhone().length() > 8
								|| !CheckUtil.isNumeric(ocs.getPhone())) {
							errorNum++;
							continue;
						}

						// 判断文件中的折行
						this.outcallSegmentService.saveOutCallSegment(ocs);
						successNum++;
						continue;

					} catch (Exception e) {
						errorSegmentList.add(ocs);
					}
				}

			}

		}
		return "";
	}

	/**
	 * 由文件生成号段列表
	 */
	public List<OutCallSegment> getSegmentList(String excel) {
		UserInfo adminUser = (UserInfo) this.getSession().getAttribute(
				"adminUser");

		List<OutCallSegment> list = new ArrayList<OutCallSegment>();
		Workbook wb = null;

		try {
			InputStream in = new FileInputStream(excel);
			wb = Workbook.getWorkbook(in);
			Sheet sheet = wb.getSheet("本地号码维护");
			boolean flag = true;

			// 第一行不判断
			for (int j = 1; j < sheet.getRows(); j++) {
				Cell[] cells = sheet.getRow(j); // 读取一行
				if (cells != null && cells.length > 0) {

					OutCallSegment ocs = new OutCallSegment();

					String impphone = "";

					for (int k = 0; k < cells.length; k++) {
						String val = cells[k].getContents();// 得到单元格的内容
						if (val != null) {
							val = val.trim() == null ? "" : val.trim();
						}
						if (k == 0) {
							impphone = val;
						}
					}

					ocs.setCreateUserInfo(adminUser);
					ocs.setCreateTime(new Timestamp(System.currentTimeMillis()));
					ocs.setIsDelete(0);
					ocs.setPhone(impphone);
					ocs.setCheckState(0);
					list.add(ocs);
				}
				if (!flag) {
					break;
				}

			}// 一个工作表判断完成
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			if (wb != null) {
				wb.close();
			}
		}

		return list;
	}

	/**
	 * 判断文件是否符合要求
	 * 
	 * @param excel
	 * @return 空字符串表示验证成功 其他失败
	 * @throws Exception
	 */
	private String checkExcel(String excel) {
		Workbook wb = null;
		String result = "";
		try {
			InputStream is = new FileInputStream(excel);
			wb = Workbook.getWorkbook(is); // 这里要注意版本问题。
			if (wb == null) {
				result = "要导入的文件不正确！";
				return result;
			}
			Sheet sheet = wb.getSheet("本地号码维护"); // 获取工作Excel，文件的名字必须得是
													// “本地号码维护”？
			if (sheet == null) {
				result = "要导入的文件不正确！";
				return result;
			}
			boolean flag = true;
			int totalRow = sheet.getRows();
			if (totalRow == 1) {
				result = "请填写要导入的内容！";
				return result;
			}
			// 得到列的长度
			Cell[] cellsZero = sheet.getRow(0); // 读标题行
			Integer headLength = cellsZero.length;
			// 第一行不判断
			for (int j = 1; j < sheet.getRows(); j++) {
				Cell[] cells = sheet.getRow(j); // 读取一行
				// 如果这行全部为空,则不处理
				boolean isEmpty = true;
				if (cells != null) {
					for (Cell cell : cells) {
						String val = cell.getContents();// 内容
						if (null != val && !"".equals(val.trim())) {
							isEmpty = false;
							break;
						}
					}
				}
				if (isEmpty) {
					continue;
				}

				if (cells != null && cells.length < headLength) {
					result = "第" + (j + 1) + "行数据不完整！";
					break;
				}
				if (cells != null && cells.length > 0) {
					for (int k = 0; k < cells.length; k++) {
						// 判断excel表格内容是否符合要求，如内容是否是数字号码，以后根据业务实现，现在不详。
						String val = cells[k].getContents();// 内容
						if (val != null) {
							val = val.trim();
						}
					}// column
				}
				if (!flag) {
					break;
				}
			}// one sheet

		} catch (Exception ex) {
			result = ex.getMessage();
		} finally {
			if (wb != null) {
				wb.close();
			}
		}
		return result;
	}

	/**
	 * 导出本地维护的号码段
	 */
	public void exportPhone() {
		HttpServletResponse resp = this.getResponse();
		resp.setContentType("application/vnd.ms-excel");

		OutputStream outp = null;
		try {

			this.setIDisplayLength(99999);
			this.setIDisplayStart(0);
			Page<OutCallSegment> pageInfo = this.outcallSegmentService
					.findOutCallSegmentPageList(getPageable(new Sort(
							Direction.DESC, "createTime")), phone);

			List<OutCallSegment> list = pageInfo.getContent();
			// 把联系人信息填充到map里面
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ new String("本地维护的号码段信息.xls".getBytes(), "iso8859-1"));// 解决中文
			// 文件名问题
			outp = resp.getOutputStream();
			List<Map<String, Object>> mapList = listToMapList(list);

			ExportExcel exportExcel = new ExportExcel(outp,
					getExportHeadList(), mapList, getExportKeyList());
			exportExcel.export();

		} catch (Exception e) {
			logger.error("getAddressGroupList error. ", e);
		}
	}

	private List<Map<String, Object>> listToMapList(List<OutCallSegment> list) {
		// 把号码端信息填充到Map中
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();

		if (list != null) {
			for (OutCallSegment outc : list) {
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("phone", outc.getPhone());

				ls.add(map);
			}
		}
		return ls;

	}

	private List<String> getExportHeadList() {
		List<String> headList = new ArrayList<String>();
		headList.add("手机号段");
		return headList;
	}

	private List<String> getExportKeyList() {
		List<String> headList = new ArrayList<String>();
		headList.add("phone");
		return headList;
	}
	
	public String getAddphone() {
		return addphone;
	}

	public void setAddphone(String addphone) {
		this.addphone = addphone;
	}

	private Integer vId;

	public Integer getVId() {
		return vId;
	}

	public void setVId(Integer vId) {
		this.vId = vId;
	}

	public String getErrorFile() {
		return errorFile;
	}

	public void setErrorFile(String errorFile) {
		this.errorFile = errorFile;
	}

	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}

	public int getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(int errorNum) {
		this.errorNum = errorNum;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public File getFileToUpload() {
		return fileToUpload;
	}

	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}

