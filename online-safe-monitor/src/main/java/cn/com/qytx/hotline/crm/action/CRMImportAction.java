package cn.com.qytx.hotline.crm.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.crm.service.ICRM;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;

@SuppressWarnings("serial")
/**
 * CRM的导入
 * 创建人：李立泼 
 * 创建时间：2014年04月15日
 * 修改人：
 * 修改时间：
 */
public class CRMImportAction extends BaseActionSupport {
	
	/**
	 * log4j日志对象
	 */
    private final static MonitorLogger logger =new Log4jImpl(CRMImportAction.class);
	
	//输入
	@Autowired
    private ICRM crmservice;
	private File fileToUpload;
    private String uploadFileName;//设置上传文件的文件名
    private String uploadContentType;//上传文件的类型
    private String radioType;//导入类型
    private String file;//上传的文件
    private int allNum = 0;//总个数
    private int successNum = 0;//成功个数
    private int skipNum = 0;//跳过个数
    private int errorNum = 0;//错误个数
    private int overrideNum = 0;//覆盖个数
    private List<CRM> errorProductList=new ArrayList<CRM>();
    private String errorFile;
    /* ========================== */
    private String excelFileName;
	private int OldRows;
	private int OldCols;
	private List<CRM> successList=new ArrayList<CRM>();
	private WritableWorkbook errorWWB;
	private WritableSheet ws;
	private int errorRowsNum=1;//错误信息文件行数
	private String errorFileName;
	private int successImportNum=0;
	private int errorImportNum=0;
	private String name;
	
    /**
     * 检查文件是否符合格式action
     * @return
     * @throws Exception
     */
    public String checkBeforeImporting() throws Exception {

        PrintWriter writer = null;
        try {
            if (uploadFile()) {
                String result = checkExcel(file);
                this.getResponse().setHeader("ContentType", "text/json");
				this.getResponse().setCharacterEncoding("utf-8");
				this.getResponse().setContentType("text/html;charset=utf-8");  
                writer = new PrintWriter(this.getResponse().getWriter());
                writer.print(result);
                writer.close();
            }

        } catch (Exception e) {
        	logger.error("checkBeforeImporting error. ", e);
            writer = new PrintWriter(this.getResponse().getWriter());
            writer.print("对不起！导入文件时出错！");
            writer.close();
        }
        return null;
    }
    
	/**
	 * 导入操作的入口
	 * 这个方法中需要修改的方法有：
	 * 	maskExcelFile(cell,c,r); verify(thisCellContent,c); recordThisLineCalls(cells); saveOrUpdate(successList);
	 * @return
	 */
	public String importCRM() {
		PrintWriter writer = null;
		UserInfo userInfo = getLoginUser();
		try {
			if (uploadFile()) {//上传文件
				excelFileName=file;
				InputStream stream = new FileInputStream(excelFileName);
				Workbook wb = Workbook.getWorkbook(stream);
				Sheet sheet = wb.getSheet(0);
				OldCols=sheet.getColumns();
				OldRows=sheet.getRows();
				boolean lastRowPass=false;
				for(int r=1;r<OldRows;r++){
					Cell[] cells=new Cell[OldCols];
					boolean thisRowPass=true;
					for(int c=0;c<OldCols;c++){
						Cell cell = sheet.getCell(c, r);
						cells[c]=cell;//把这一列中的每一单元格存入代表这一行单元格的数组中。方便下面的recordThisLineCalls(cells)方法调用。
						boolean dynamicCellPass=maskExcelFile(cell,c,r);//用来筛分每个单元格，过滤出不符合标准的excel信息，并另存入excel错误信息统计表中。并返回一个boolean值，true表示该行全部符合。
						if(!dynamicCellPass){//在这一行当中。只要有一列不符合标准。这一行就不符合标准，这一行就不通过。
							thisRowPass=false;
						}
					}
					if(thisRowPass){
						recordThisLineCalls(cells);//记录下这一行的数据。把这行的数据转换成一个对象，追加到记录成功的successList中。
					}else{
						errorRowsNum++;
						errorImportNum++;
					}
					lastRowPass=thisRowPass;
				}
				saveOrUpdate(successList);
				if(errorWWB!=null){
					if(errorImportNum>0){
						if(lastRowPass){//当最后一行是正确的数据时，移除最后一行。
							WritableSheet ws=errorWWB.getSheet(0);
							ws.removeRow(errorRowsNum);
						}
						errorWWB.write();
					}
				}
				/*==== 后续操作，显示出导入的结果。==== */
				this.getResponse().setHeader("ContentType", "text/json");
				this.getResponse().setCharacterEncoding("utf-8");
				this.getResponse().setContentType("text/html;charset=utf-8");  
				writer = new PrintWriter(this.getResponse().getWriter());
				String result="";
                if (OldRows > 1) {
//                    int failNum = allNum - successNum - skipNum - overrideNum;//失败条数
//                    if (failNum < 0) {
//                        failNum = 0;
//                    }
                    String path = this.getRequest().getContextPath();
                    System.out.println("path:"+path);
                    String basePath = getRequest().getScheme() + "://" + getRequest().getServerName() + ":" + getRequest().getServerPort() + path + "/";
                    System.out.println("getRequest().getScheme():"+getRequest().getScheme());
                    System.out.println("getRequest().getServerName():"+getRequest().getServerName());
                    System.out.println("getRequest().getServerPort():"+getRequest().getServerPort());
                    System.out.println("basePath:"+basePath);
//                    String projectName=path.substring(1);
//                    errorFileName = errorFileName.substring(errorFileName.indexOf(projectName));
//                    errorFileName = errorFileName.substring(projectName.length()+1);
                    System.out.println("errorFileName:"+errorFileName);
                    System.out.println("basePath+errorFileName:"+basePath+"/upload/"+errorFileName);
                    if(errorImportNum>0) {
                    	result = "追加完毕！追加成功"+successImportNum+"条记录，失败"+errorImportNum+"条记录&nbsp;<a href='"+basePath+"/upload/"+errorFileName+"' class='ml10' >下载失败记录</a>";
                    }else{
                    	result = "追加完毕！追加成功"+successImportNum+"条记录，失败"+errorImportNum+"条记录。";
                    }
                }else{
                	result = "追加完毕！追加成功"+successImportNum+"条记录，失败"+errorImportNum+"条记录。";
                }
                writer.print(result);
                writer.close();
				
			}
		}catch (Exception e){
			logger.error("importCRM error. 当前操作人：" + userInfo.getUserId(), e);
		}finally{
			if(errorWWB!=null){
				try {
					errorWWB.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	private boolean maskExcelFile(Cell cell,int c,int r) throws Exception{
		String errorFileNameCr = "";
		if(errorFileName==null || "".equals(errorFileName)){
			@SuppressWarnings("deprecation")
			String path = ServletActionContext.getRequest().getRealPath("/upload");
			System.out.println("**path:"+path);
			errorFileName = UUID.randomUUID().toString();
			errorFileNameCr = path+File.separator+"errorImportExcel"+File.separator+errorFileName+".xls";
			errorFileName = File.separator+"errorImportExcel"+File.separator+errorFileName+".xls";
			System.out.println("**errorFileName:"+errorFileName);
			File filePath=new File(path+File.separator+"errorImportExcel");
			if(!filePath.isDirectory()){
				try {
					filePath.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			File file=new File(errorFileNameCr);
			if(!file.isFile()){
				try {
					file.createNewFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(errorWWB==null){
			errorWWB = Workbook.createWorkbook(new File(errorFileNameCr));
			ws = errorWWB.createSheet("sheet1", 0);
			Label label00 = new Label(0, 0, "序号");//添加错误信息表的表头。这几行是固定的。
			Label label01 = new Label(1, 0, "姓名");
			Label label02 = new Label(2, 0, "性别");
			Label label03 = new Label(3, 0, "联系电话");
			Label label04 = new Label(4, 0, "备用号码");
			Label label05 = new Label(5, 0, "联系地址");
			Label label06 = new Label(6, 0, "身份证号");
			Label label07 = new Label(7, 0, "户口所在地");
			Label label08 = new Label(8, 0, "工作单位");
			Label label09 = new Label(9, 0, "职务");
			Label label10 = new Label(10, 0, "月收入");
			Label label11 = new Label(11, 0, "客户类别");
			Label label12 = new Label(12, 0, "备注");
			Label label13 = new Label(13, 0, "年龄");
			ws.addCell(label00);
			ws.addCell(label01);
			ws.addCell(label02);
			ws.addCell(label03);
			ws.addCell(label04);
			ws.addCell(label05);
			ws.addCell(label06);
			ws.addCell(label07);
			ws.addCell(label08);
			ws.addCell(label09);
			ws.addCell(label10);
			ws.addCell(label11);
			ws.addCell(label12);
			ws.addCell(label13);
		}
		
		//String columnName=getColumnName(c);//通过列号得到表头中这一列的列名。
		String thisCellContent=cell.getContents().trim();
		boolean thisCellContentPass;
		String verifyResult = verify(thisCellContent,c);//通过这个单元格的内容，和这个单元格所在的列号，判断出这个单元格中的信息是否符合标准。符合返回true。
		if(verifyResult==null){
			thisCellContentPass = true;
		}else{
			thisCellContentPass = false;
		}
		//NumberFormat nf = new NumberFormat("#00");//设置单元格的格式（但是这个不是文本格式，所以用下面的有参new WritableCellFormat(NumberFormats.TEXT)构造器）
		WritableCellFormat wcfDt = new WritableCellFormat(NumberFormats.TEXT);//设置单元格为文本格式
//		WritableCellFormat wcfDt = new WritableCellFormat();//这个无参数的构造器没有设置单元格的格式
		Label label0x = null;
		if(thisCellContentPass){//根据这个单元格中内容的符合性来，判断是否给这一列添加底色。
			label0x = new Label(c, errorRowsNum, thisCellContent,wcfDt);
		}else{
			wcfDt.setBackground(Colour.RED);
			label0x = new Label(c, errorRowsNum, thisCellContent,wcfDt);
			/* 给错误的单元格添加批注信息 - 开始 */
			WritableCellFeatures cellFeatures = new WritableCellFeatures();  
			cellFeatures.setComment(verifyResult);  
			label0x.setCellFeatures(cellFeatures);
			/* 给错误的单元格添加批注信息 - 结束 */
		}
		ws.addCell(label0x);//把这个单元格添加到错误信息表中。若错误行数不变时，这个单元格中的数据会被覆盖掉。
		//errorWWB.write();
		return thisCellContentPass;//返回一个boolean值。true表示这个单元格中的数据符合标准。
	}
	
	private String verify(String thisCellContent,int c){
		String regex3="^1\\d{10}$";//第3列正则（手机号码）
		String regex4="^(\\d{3,4}[- ]?)?\\d{7,8}$";//第4列正则（座机号码）
		
		String result=null;
		if(thisCellContent==null || "".equals(thisCellContent)){//所有字段全部不能为空。
//			if(c!=0 && c!=3 && c!=4 && c!=5){//第0列序号不判断
//				result=false;
//				return result;
//			}
			if(c==1){
				result="姓名不能为空值";
				return result;
			}else if(c==2){
				result="性别不能为空值";
				return result;
			}else if(c==3){
				result="联系电话不能为空值";
				return result;
			}
		}
		switch(c){
			case 0://序号
					break;
			case 1:if(thisCellContent.length() > 10){//判断姓名
						result="姓名长度大于10";
					}else{
						name = thisCellContent;
					}
					break;
			case 2:if(thisCellContent.length() > 1){//判断性别
						result="性别长度大于1";
					}else{//判断性别是否存在
			        	if(!"男".equals(thisCellContent) && !"女".equals(thisCellContent)){
			                result = "性别应为：男/女";
			        	}
					}
					break;
			case 3:if(!"".equals(thisCellContent) && (thisCellContent.matches(regex3)||thisCellContent.matches(regex4))){//判断联系电话
						if (verifyMobileOrBackphone(thisCellContent,"",name)) {
							result = "用户姓名与联系电话相同的用户已存在";
						}
					}else{
						result = "联系电话格式不正确";
					}
					break;
			case 4:if(!"".equals(thisCellContent)){//判断备用号码
//						if(verifyMobileOrBackphone("",thisCellContent,name)){
//							 result="用户姓名与备用号码已存在";
//						}
						if(!thisCellContent.matches(regex3)&&!thisCellContent.matches(regex4)){
							 result="备用号码格式不正确";
						 }
					}
					break;
			case 5:if(!"".equals(thisCellContent)&&(thisCellContent!=null)){//判断居住地
						if(thisCellContent.length() > 50){
							result = "联系地址长度大于50";
						}
					}
					break;
			case 6:if(!"".equals(thisCellContent)&&(thisCellContent!=null)) {//判断身份证号
					
//						String idReg18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$";
//						String idReg15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
						String idReg = "^([1-6]\\d{5}(19|20)\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])\\d{3}[0-9xX])|([1-6]\\d{5}\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])\\d{3})$";
//						if(!thisCellContent.matches(idReg18) && !thisCellContent.matches(idReg15)){
						if(!thisCellContent.matches(idReg)){
							result = "身份证号格式不正确";
						}
					}
					break;
			case 7:if(!"".equals(thisCellContent)&&(thisCellContent!=null)){//判断户口所在地
						if(thisCellContent.length() > 50){
							result = "户口所在地长度大于50";
						}
					}
					break;
			case 8:if(!"".equals(thisCellContent)&&(thisCellContent!=null)){//判断工作单位
						if(thisCellContent.length() > 50){
							result = "工作单位长度大于50";
						}
					}
					break;
			case 9:if(!"".equals(thisCellContent)&&(thisCellContent!=null)){//判断职务
						if(thisCellContent.length() > 20){
							result = "职务长度大于20";
						}
					}
					break;
			case 10:if(!"".equals(thisCellContent)&&(thisCellContent!=null)){//判断月收入
						String moneyReg = "^([1-9][0-9]*)+(.[0-9]{1,2})?$";
						if(!thisCellContent.matches(moneyReg)){
							result = "月收入输入不正确";
						}
					}
					break;
			case 11:if(!"".equals(thisCellContent)&&(thisCellContent!=null)){//判断客户类别
						if(!"一般客户".equals(thisCellContent) && !"VIP客户".equals(thisCellContent)){
							result = "客户类别应输入：一般客户/VIP客户";
						}
					}
					break;
			case 12:if(!"".equals(thisCellContent)&&(thisCellContent!=null)){//判断备注
						if(thisCellContent.length() > 500){
							result = "备注长度大于500";
						}
					}
					break;
			case 13:if(StringUtils.isNotBlank(thisCellContent)){//判断年龄
				String rex = "[1-9]+[0-9]*";
				if( thisCellContent.matches(rex) ){
					if(thisCellContent.length() > 4){
						result = "年龄长度大于3";
					}
				}else{
					result = "年龄必须是非零开头的数字";
				}
			}
			break;
			default:
				break;
		}
		return result;
	}
	
	private void recordThisLineCalls(Cell[] cells){
		String regex3="^1\\d{10}$";//第3列正则（手机号码）
		String regex4="^(\\d{3,4}[- ]?)?\\d{7,8}$";//第4列正则（座机号码）
		if (cells != null && cells.length > 0) {
        	CRM crm =new CRM();
        	String name="";//姓名1
            Integer gender=null;//性别2
            String mobile="";//联系电话3
            String backPhone="";//备用号码4
            String address="";//居住地5
            String cardId = "";//身份证号6
            String hkAddress = "";//户口所在地7
            String company = "";//工作单位8
            String job = "";//职务9
            BigDecimal receiveMoney = null; //月收入10
            Integer personType = null;//客户类别11
            String note = "";//备注12
            Integer age = null;//年龄13
            for (int k = 0; k < cells.length; k++) {
                String val = cells[k].getContents();//内容
                if (val != null) {
                    val = val.trim();
                }
                if (k == 0) {//序号
                	
                }else if (k == 1) {//姓名1
                	name=val;
                }else if (k == 2) {//性别2
                	if("女".equals(val)){
                		gender=0;
                	}else if("男".equals(val)){
                		gender=1;
                	}else{
                		//no
                	}
                }else if (k == 3) {//联系电话3
                	if(val.matches(regex3)||val.matches(regex4)){
                		mobile=val;
                	}
                }else if (k == 4) {//备用号码4
                	if(val.matches(regex3)||val.matches(regex4)){
                		backPhone=val;
                	}
                }else if (k == 5) {//居住地5
                	address=val;
                }else if(k == 6){//身份证号
                	String idReg = "^([1-6]\\d{5}(19|20)\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])\\d{3}[0-9xX])|([1-6]\\d{5}\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])\\d{3})$";
					if(val.matches(idReg)){
						cardId = val;
					}
                }else if(k==7){
                	hkAddress = val;
                }else if(k==8){
                	company = val;
                }else if(k==9){
                	job = val;
                }else if(k==10){
                	String moneyReg = "^([1-9][0-9]*)+(.[0-9]{1,2})?$";
					if(val.matches(moneyReg)){
						receiveMoney = new BigDecimal(Double.parseDouble(val));
					}
                }else if(k==11){
                	if("一般客户".equals(val)){
                		personType = 1;
					}else if("VIP客户".equals(val)){
						personType = 2;
					}else{
						personType = 0;
					}
                }else if(k==12){
                	note = val;
                }else if(k==13){
                	if(StringUtils.isNotBlank(val)){
                		age = Integer.parseInt(val);
                	}
                }
            }
            //给crm对象赋值
            crm.setName(name);
            crm.setGender(gender);
            crm.setMobile(mobile);
            crm.setBackPhone(backPhone);
            crm.setAddress(address);
            crm.setCardId(cardId);
            crm.setHkAddress(hkAddress);
            crm.setCompany(company);
            crm.setJob(job);
            crm.setReceiveMoney(receiveMoney);
            crm.setPersonType(personType);
            crm.setNote(note);
            crm.setAge(age);
            crm.setIsForkGroup(getLoginUser().getIsForkGroup());
//            crm.setCreateTime(new Timestamp(System.currentTimeMillis()));
//            UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
//            crm.setCreateUserId(userInfo.getUserId());
//            crm.setIsDelete(0);
//            crm.setLastUpdateUserId(userInfo.getUserId());
//            crm.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
            
            successList.add(crm);
        }
	}
	
	private void saveOrUpdate(List<CRM> successList){
		UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
		if(successList!=null && successList.size()>0) {
			successImportNum=successList.size();//总个数
            for(CRM crm:successList) {
                  //开始导入CRM
            	  try{
	                   // 判断是否重复 （重复时覆盖）
//	                   CRM c = crmservice.findCRMByMobile(crm.getMobile());
//	                   if (c!=null){
//	                	   c.setName(crm.getName());
//	                	   c.setGender(crm.getGender());
//	                	   c.setMobile(crm.getMobile());
//	                	   c.setTelephone(crm.getTelephone());
//	                	   c.setAddress(crm.getAddress());
//	                	   c.setCreateTime(new Timestamp(System.currentTimeMillis()));
//	                	   c.setCreateUserId(userInfo.getUserId());
//	                	   c.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
//	                	   c.setLastUpdateUserId(userInfo.getUserId());
//	                	   crmservice.saveOrUpdate(c);
//	                       overrideNum++;
//	                       continue;
//	                   }
	                   // CRM默认设置
	                   crm.setCreateTime(new Timestamp(System.currentTimeMillis()));
                	   crm.setCreateUserId(userInfo.getUserId());
                	   crm.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
                	   crm.setLastUpdateUserId(userInfo.getUserId());
                	   crm.setIsDelete(0);
                	   crm.setCompanyId(userInfo.getCompanyId());
                	   crmservice.saveOrUpdate(crm);
                	   logger.info("客户档案，导入操作插入的信息。 " + crm);
	                   successNum++;
            	  }catch (Exception e) {
            		  errorProductList.add(crm);
            	  }finally{
            		  
            	  }
            }
        }
	}
	
	/**
	 * 判断是不是闰年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year){
		if(year>=3200){
			if(year%3200==0 && year%172800==0){
				return true;
			}else{
				return false;
			}
		}else{
			return (year%4==0&&year%100!=0)||year%400==0;
		}
	}

	/**
	 * 判断日期是不是真实存在
	 * @param timeString
	 * @param timeFormat
	 * @return
	 */
	public static boolean verifyTimeString(String timeString, String timeFormat){
		boolean pass=true;
		//timeFormat "yyyy-MM-dd HH:mm:ss"
		String yearStr="";
		if(timeFormat.indexOf("y")>-1){
			yearStr=timeString.substring(timeFormat.indexOf("y"), timeFormat.lastIndexOf("y")+1);
		}
		String monthStr="";
		if(timeFormat.indexOf("M")>-1){
			monthStr=timeString.substring(timeFormat.indexOf("M"), timeFormat.lastIndexOf("M")+1);
		}
		String dayStr="";
		if(timeFormat.indexOf("d")>-1){
			dayStr=timeString.substring(timeFormat.indexOf("d"), timeFormat.lastIndexOf("d")+1);
		}
		String hourStr="";
		if(timeFormat.indexOf("H")>-1){
			hourStr=timeString.substring(timeFormat.indexOf("H"), timeFormat.lastIndexOf("H")+1);
		}
		String minuteStr="";
		if(timeFormat.indexOf("m")>-1){
			minuteStr=timeString.substring(timeFormat.indexOf("m"), timeFormat.lastIndexOf("m")+1);
		}
		String secondStr="";
		if(timeFormat.indexOf("s")>-1){
			secondStr=timeString.substring(timeFormat.indexOf("s"), timeFormat.lastIndexOf("s")+1);
		}
		int year,month,day,hour,minute,second;
		if(yearStr!=null && !"".equals(yearStr)){//有年时还要判断是不是闰年
			year=Integer.parseInt(yearStr);
			System.out.println(year);
			month=Integer.parseInt(!"".equals(monthStr)?monthStr:"1");
			day=Integer.parseInt(!"".equals(dayStr)?dayStr:"1");
			if(month>12){
				pass= false;
			}
			if(isLeapYear(year)){//是闰年
				if(month==2){
					if(day>29){
						pass=false;
					}
				}else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
					if(day>31){
						pass=false;
					}
				}else{
					if(day>30){
						pass=false;
					}
				}
			}else{//不是闰年
				if(month==2){
					if(day>28){
						pass=false;
					}
				}else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
					if(day>31){
						pass=false;
					}
				}else{
					if(day>30){
						pass=false;
					}
				}
			}
		}
		if(monthStr!=null && !"".equals(monthStr)){
			month=Integer.parseInt(monthStr);
			System.out.println(month);
			day=Integer.parseInt(!"".equals(dayStr)?dayStr:"1");
			if(month>12){
				pass=false;
			}else if(month==2){
				if(day>29){
					pass=false;
				}
			}else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
				if(day>31){
					pass=false;
				}
			}else{
				if(day>30){
					pass=false;
				}
			}
		}
		if(dayStr!=null && !"".equals(dayStr)){
			day=Integer.parseInt(dayStr);
			System.out.println(day);
			if(day>31){
				pass=false;
			}
		}
		if(hourStr!=null && !"".equals(hourStr)){
			hour=Integer.parseInt(hourStr);
			System.out.println(hour);
			if(hour>23){
				pass=false;
			}
		}
		if(minuteStr!=null && !"".equals(minuteStr)){
			minute=Integer.parseInt(minuteStr);
			System.out.println(minute);
			if(minute>59){
				pass=false;
			}
		}
		if(secondStr!=null && !"".equals(secondStr)){
			second=Integer.parseInt(secondStr);
			System.out.println(second);
			if(second>59){
				pass=false;
			}
		}
		return pass;
	}    

    /**
     * 上传文件
     */
    private boolean uploadFile() {
		@SuppressWarnings("deprecation")
		String path = ServletActionContext.getRequest().getRealPath("/upload");
        File checkPath = new File(path);
        if (!checkPath.exists()) {
        	try {
        		//目录不存在，则创建目录
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
                    ext = uploadFileName.substring(uploadFileName.lastIndexOf("."));
                }
                if (!ext.equals(".xls")) {
                    return false;
                }
                String saveFileName = fileName + ext;
                File savefile = new File(new File(path), saveFileName);
                if (!savefile.getParentFile().exists()){
                	try {
                		savefile.getParentFile().mkdirs();
					} catch (Exception e) {
						e.printStackTrace();
					}
                }
                FileUtils.copyFile(fileToUpload, savefile);//拷贝文件
                file = path + "/" + saveFileName;

            }

        } catch (Exception e) {
        	logger.error("uploadFile error. ", e);
            return false;
        }
        return true;
    }
	
    /**
     * 判断文件是否符合要求
     * @param excel 上传的文件路径
     * @return 空字符串表示验证成功 其他失败
     * @throws Exception
     */
    private String checkExcel(String excel) {
    	String regex3="^\\d{1}\\d{10}$";//第3列正则（手机号码）
    	String regex4="^(\\d{3,4}[- ]?)?\\d{7,8}$";//第4列正则（座机号码）
    	Workbook wb = null;
    	String result = "";
    	try {
    		InputStream is = new FileInputStream(excel);
    		wb = Workbook.getWorkbook(is);
    		if(wb==null){
    			result="要导入的文件不正确！";
    			return result;
    		}
//            Sheet sheet = wb.getSheet("通讯录");    //感觉这样不好，因为sheet还要命名。
    		Sheet sheet = wb.getSheet(0);    //获取Excel工作表对象 Sheet sheet=book.getSheet(0);
    		if(sheet==null){
    			result="要导入的文件不正确！";
    			return result;
    		}
    		boolean flag = true;//??????????????????????????????????????
    		int totalRow=sheet.getRows();
    		if(totalRow==1){
    			result="请填写要导入的内容！";
    			return result;
    		}
    		//得到列的长度
    		//Cell[] cellsZero = sheet.getRow(0); //读标题行（这里的标题行是表头的意思，不是真的表格内的标题。因为以前做的excel表格全都没有表格内的标题）
    		//Integer headLength=cellsZero.length; //headLength是表格的表头的列数
			//OldRows=sheet.getRows();
    		int OldCols=sheet.getColumns();
    		//第一行不判断
    		for (int j = 1; j < sheet.getRows() && flag; j++) {
    			Cell[] cells = sheet.getRow(j);    //读取第j行,得到这一行的所有单元格。Cell cell1=sheet.getCell(0,0);可以获得指定的行、列的单元格。
    			
    			if (cells != null && cells.length > 0) {
    				/* ============下面的遍历是对具体excel表格模板的每个单元格中的具体数的格式进行的验证。不同的项目，不同的模板 ，下面的操作将不同。============ */
    				for (int k = 0; k < OldCols && flag; k++) {
    					String val = "";
    					if(k<cells.length){
    						val = cells[k].getContents();//内容
    					}
    					if (val != null) {
    						val = val.trim();
    					}
    					switch(k){
    					case 0:
    						break;
    					case 1:
    						//判断姓名
    						if (val == null || "".equals(val)) {
    							result = "第" + (j + 1) + "行第" + (k + 1) + "列姓名不能为空！";
    							flag = false;//退出循环
    							break;
    						} else {
    							if (val.length() > 20) {
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列用户姓名长度不能大于10！";
    								flag = false;//退出循环
    								break;
    							}
    						}
    						break;
    					case 2:
    						//判断性别
    						if (val == null || "".equals(val)) {
    							result = "第" + (j + 1) + "行第" + (k + 1) + "列性别不能为空！";
    							flag = false;//退出循环
    							break;
    						} else {
    							if (val.length() > 2) {
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列性别长度不能大于1！";
    								flag = false;//退出循环
    								break;
    							}else if(!"女".equals(val) && !"男".equals(val)){
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列性别内容不符合!";
    								flag = false;//退出循环
    								break;
    							}
    						}
    						break;
    					case 3:
    						//判断联系电话
    						if (val == null || "".equals(val)) {
    							result = "第" + (j + 1) + "行第" + (k + 1) + "列手机号码不能为空！";
    							flag = false;//退出循环
    							break;
    						} else {
    							if (!val.matches(regex3)&&!val.matches(regex4)) {
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列联系电话格式不正确！";
    								flag = false;//退出循环
    								break;
    							}else if(verifyMobileOrBackphone(val,"",cells[1].getContents())){
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列用户姓名与联系电话相同的用户已存在！";
    								flag = false;//退出循环
    								break;
    							}
    						}
    						break;
    					case 4:
    						//判断座机号码
    						if (val == null || "".equals(val)){
//    							result = "第" + (j + 1) + "行第" + (k + 1) + "列座机号码不能为空！";
//    							flag = false;//退出循环
    							break;
    						}else{
    							if(!val.matches(regex3)&&!val.matches(regex4)){
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列备用号码格式不正确！";
    								flag = false;//退出循环
    								break;
    							}
//    							else if(verifyMobileOrBackphone("",val,cells[1].getContents())){
//    								result = "第" + (j + 1) + "行第" + (k + 1) + "列用户姓名与备用号码相同的用户已存在已存在！";
//    								flag = false;//退出循环
//    								break;
//    							}
    						}
    						break;
    					case 5:
    						//判断地址
    						if(val==null || "".equals(val)){
//    							result = "第" + (j + 1) + "行第" + (k + 1) + "列地址不能为空！";
//    							flag = false;//退出循环
    							break;
    						}else{
    							if (!"".equals(val) && val.length() > 100) {
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列联系地址长度不能大于50！";
    								flag = false;//退出循环
    								break;
    							}
    						}
    						break;
    					case 6://判断身份证号
    						if(val==null||"".equals(val)){
    							break;
    						}else{
    							String idReg = "^([1-6]\\d{5}(19|20)\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])\\d{3}[0-9xX])|([1-6]\\d{5}\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])\\d{3})$";
    							if(!val.matches(idReg)){
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列身份证号不符合要求！";
    								flag = false;//退出循环
    								break;
    							}
    						}
    						break;
    					case 7://户口所在地
    						if(val==null || "".equals(val)){
    							break;
    						}else{
    							if (!"".equals(val) && val.length() > 100) {
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列户口所在地长度不能大于50！";
    								flag = false;//退出循环
    								break;
    							}
    						}
    						break;
    					case 8://工作单位
    						if(val==null || "".equals(val)){
    							break;
    						}else{
    							if (!"".equals(val) && val.length() > 100) {
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列工作单位长度不能大于50！";
    								flag = false;//退出循环
    								break;
    							}
    						}
    						break;
    					case 9://职务
    						if(val==null || "".equals(val)){
    							break;
    						}else{
    							if (!"".equals(val) && val.length() > 40) {
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列职务长度不能大于20！";
    								flag = false;//退出循环
    								break;
    							}
    						}
    						break;
    					case 10://月收入
    						if(val==null || "".equals(val)){
    							break;
    						}else{
    							String moneyReg = "^([1-9][0-9]*)+(.[0-9]{1,2})?$";
    							if(!val.matches(moneyReg)){
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列收入不符合要求！";
    								flag = false;//退出循环
    								break;
    							}
    						}
    						break;
    					case 11://客户类别
    						if(val==null || "".equals(val)){
    							break;
    						}else{
    							if(!"一般客户".equals(val) && !"VIP客户".equals(val)){
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列客户类别内容不符合!";
    								flag = false;//退出循环
    								break;
    							}
    						}
    						break;
    					case 12://备注
    						if(val==null || "".equals(val)){
    							break;
    						}else{
    							if (!"".equals(val) && val.length() > 1000) {
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列备注长度不能大于500！";
    								flag = false;//退出循环
    								break;
    							}
    						}
    						break;
    					case 13://年龄
    						if(val==null || "".equals(val)){
    							break;
    						}else{
    							if (!"".equals(val) && val.length() > 4) {
    								result = "第" + (j + 1) + "行第" + (k + 1) + "列年龄长度不能大于3！";
    								flag = false;//退出循环
    								break;
    							}
    						}
    						break;
    					default:
    						break;
    					}
    				}//column
    				/* ============上面的遍历是对具体excel表格模板的每个单元格中的具体数的格式进行的验证。不同的项目，不同的模板 ，下面的操作将不同。============ */
    			}
    			if (!flag) {
    				break;
    			}
    		}//one sheet
    		
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
	 * 返回true表示有重复，返回false表示没有重复。
	 * @param mobile
	 * @param telephone
	 * @return
	 */
	public boolean verifyMobileOrBackphone(String mobile, String backphone,String name){
		try{
			CRM crmMobile=null;
			CRM crmTelephone=null;
			
			if(mobile!=null && !"".equals(mobile)&&name!=null && !"".equals(name)){
				crmMobile=crmservice.findByMobileAndName(mobile,name);//4
			}
			if(backphone!=null && !"".equals(backphone)&&name!=null && !"".equals(name)){
				crmTelephone=crmservice.findByBackPhoneAndName(backphone,name);//2
			}
			
			//4表示仅联系电话重复；2表示仅备用号码重复；6表示联系电话和备用号码都重复。
			Integer result = 0;
			if(crmMobile!=null){
				result+=4;
			}
			if(crmTelephone!=null){
				result+=2;
			}
			if(result>0){
				return true;
			}
		}catch(Exception e){
			logger.error("verifyMobileOrBackphone error. ", e);
		}
		return false;
	}
    
	public static String getStandardTimeString(String timeString){
		StringBuffer sbf=new StringBuffer();
		String regex="\\D";//非数字
		String[] temp= timeString.split(regex);
		for(int i=0;i<temp.length;i++){
			if(temp[i].length()<2){
				sbf.append("0");
				sbf.append(temp[i]);
			}else{
				sbf.append(temp[i]);
			}
			if(i<temp.length-1){
				sbf.append("-");
			}
		}
		return sbf.toString();
	}
    
    /* get set */
	public File getFileToUpload() {
		return fileToUpload;
	}
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getRadioType() {
		return radioType;
	}
	public void setRadioType(String radioType) {
		this.radioType = radioType;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getAllNum() {
		return allNum;
	}
	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}
	public int getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}
	public int getSkipNum() {
		return skipNum;
	}
	public void setSkipNum(int skipNum) {
		this.skipNum = skipNum;
	}
	public int getErrorNum() {
		return errorNum;
	}
	public void setErrorNum(int errorNum) {
		this.errorNum = errorNum;
	}
	public int getOverrideNum() {
		return overrideNum;
	}
	public void setOverrideNum(int overrideNum) {
		this.overrideNum = overrideNum;
	}
	public List<CRM> getErrorProductList() {
		return errorProductList;
	}
	public void setErrorProductList(List<CRM> errorProductList) {
		this.errorProductList = errorProductList;
	}
	public String getErrorFile() {
		return errorFile;
	}
	public void setErrorFile(String errorFile) {
		this.errorFile = errorFile;
	}
	
}
