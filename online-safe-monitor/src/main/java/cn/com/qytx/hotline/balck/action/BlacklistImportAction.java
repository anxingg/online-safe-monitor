package cn.com.qytx.hotline.balck.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jxl.Cell;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;

import org.apache.commons.lang3.StringUtils;

import cn.com.qytx.hotline.balck.domain.Blacklist;
import cn.com.qytx.hotline.balck.service.IBlacklist;
import cn.com.qytx.hotline.util.ImportBaseAction;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.oa.domain.OaConst;
import cn.com.qytx.platform.org.domain.UserInfo;
/**
 * 功能:黑名单导入
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2015-2-12
 * 修改日期: 2015-2-12
 * 修改列表:
 */
public class BlacklistImportAction extends ImportBaseAction{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6321220420106682425L;

	/**
     * log4j日志对象
     */
	 private final static MonitorLogger logger =new Log4jImpl(BlacklistImportAction.class);
    /**
     * 锁定类型的String
     */
    private static String lockTypeStr = "";

    /**
     * 黑名单service
     */
    @Resource
    IBlacklist blacklistService;
    
    /**
     * 把导入成功的数据返回到前台
     */
    private List<Blacklist> successList = new ArrayList<Blacklist>();
    /**
     * 检查导入的文件是否合法
     * 此文件一般不用动
     * @return
     */
    public String checkBeforeImporting(){
        if (uploadFile()){
            boolean flag = true;
            String result = initSheet();
            if (result == null){
                // 第一行不判断
                for (int j = 1; j < sheet.getRows() && flag; j++){
                    Cell[] cells = sheet.getRow(j); // 读取第j行,得到这一行的所有单元格。Cell
                    if (cells != null && cells.length > 0){
                        for (int k = 0; k < OldCols && flag; k++){
                            String val = "";
                            if (k < cells.length){
                                val = cells[k].getContents();// 内容
                            }
                            result = verify(val, j, k);// 检验单元格的返回结果
                            if (result != null && !"".equals(result)){
                                flag = false;// 退出循环
                            }
                        }
                    }
                    if (!flag){
                        break;
                    }
                }
            }
            PrintWriter writer = null;
            this.getResponse().setHeader("ContentType", "text/json");
            this.getResponse().setCharacterEncoding("utf-8");
            this.getResponse().setContentType("text/html;charset=utf-8");
            try{
                writer = new PrintWriter(this.getResponse().getWriter());
            }
            catch (IOException e){
            	logger.error("checkBeforeImporting error", e);
            }finally{
                colseAllStream();
            }
            writer.print(result);
            writer.close();
        }
        return null;
    }
    
    /**
     * 功能：验证各个单元是否符合要求
     * @param thisCellContent 单元格内容
     * @param r 行号
     * @param c 列号
     * @return
     */
    private String verify(String thisCellContent, int r, int c){
    	String regex1 = "^1\\d{10}$";// 正则（手机号码）
        String regex2 = "^(\\d{3,4}[- ]?)?\\d{7,8}$";// 正则（座机号码）
        String regexNum = "^([1-9][0-9]*)$";//正则（非零开头的数字）
        String result = null;
        if (thisCellContent != null){
            thisCellContent = thisCellContent.trim();
        }
        if (thisCellContent == null || "".equals(thisCellContent)){
            if (c == 1){
                result = "第" + (r + 1) + "行第" + (c + 1) + "列电话号码不能为空！";
                return result;
            }
            if(c == 2){
            	result = "第" + (r + 1) + "行第" + (c + 1) + "列锁定类型不能为空！";
                return result;
            }
        }
        switch (c)
        {
        case 0:// 序号
            break;
        case 1:
            if (!"".equals(thisCellContent) && (thisCellContent.matches(regex1) || thisCellContent.matches(regex2))){// 判断电话号码 
               
            }else{
            	 result = "第" + (r + 1) + "行第" + (c + 1) + "列用户电话号码格式不正确！";
            }
            break;
        case 2:
            if(!"永久".equals(thisCellContent)&&!"自定义".equals(thisCellContent)){
            	result = "第" + (r + 1) + "行第" + (c + 1) + "列锁定类型内容不符合标准，请输入“永久”或“自定义”！";
            }
            break;
        case 3:
        	if(thisCellContent!=null && !"".equals(thisCellContent) && !thisCellContent.matches(regexNum)){//判断锁定时长
        		result = "第" + (r + 1) + "行第" + (c + 1) + "列锁定时长格式不正确,只能输入非零开头的数字！";
        	}
        	break;
        case 4:
        	if(thisCellContent.length() > 1000){//判断备注长度
        		result = "第" + (r + 1) + "行第" + (c + 1) + "列备注长度不能大于1000！";
        	}
        	break;
        default:
            break;
        }
        return result;
    }
    
    /**
     * 导入数据时调用的方法
     * 次方法一般不用修改
     * @return
     */
    public String importBlacklist(){
    	try{
            if (uploadFile()){// 上传文件
                String result = initSheet();
                if (result == null){
                    boolean lastRowPass = false;
                    for (int r = 1; r < OldRows; r++){
                        Cell[] cells = new Cell[OldCols];
                        boolean thisRowPass = true;
                        for (int c = 0; c < OldCols; c++){
                            Cell cell = sheet.getCell(c, r);
                            cells[c] = cell;// 把这一列中的每一单元格存入代表这一行单元格的数组中。方便下面的recordThisLineCalls(cells)方法调用。
                            boolean dynamicCellPass = maskExcelFile(cell, c, r);// 用来筛分每个单元格，过滤出不符合标准的excel信息，并另存入excel错误信息统计表中。并返回一个boolean值，true表示该行全部符合。
                            if (!dynamicCellPass){// 在这一行当中。只要有一列不符合标准。这一行就不符合标准，这一行就不通过。
                                thisRowPass = false;
                            }
                        }
                        if (thisRowPass){
                            recordThisLineCalls(cells);// 记录下这一行的数据。把这行的数据转换成一个对象，追加到记录成功的successList中。
                        }else{
                            errorRowsNum++;
                            errorImportNum++;
                        }
                        lastRowPass = thisRowPass;
                    }
                    if (errorWWB != null){
                        if (errorImportNum > 0){
                            if (lastRowPass){// 当最后一行是正确的数据时，移除最后一行。
                                errorws = errorWWB.getSheet(0);
                                errorws.removeRow(errorRowsNum);
                            }
                            errorWWB.write();
                        }
                    }
                }
                /* ==== 后续操作，显示出导入的结果。==== */
                result = getImportResult();
                this.getResponse().setHeader("ContentType", "text/json");
                this.getResponse().setCharacterEncoding("utf-8");
                this.getResponse().setContentType("text/html;charset=utf-8");
                Map<String, Object> jsonMap = new HashMap<String, Object>();
                jsonMap.put("result", result);
                jsonMap.put("successList", successList);
                ajax(jsonMap);
            }
        }
        catch (Exception e){
        	logger.error("importBlacklist error", e);
        }
        finally{
            colseAllStream();
        }
        return null;
    }
    
    /**
     * 功能：把每一行的数据转换为外呼用户对象 并保存到list里面
     * case 对应的是表的列
     * @param cells
     */
    private void recordThisLineCalls(Cell[] cells){
        UserInfo userInfo = this.getLoginUser();
        if (cells != null && cells.length > 0){
        	Blacklist bl = new Blacklist();
            for (int k = 0; k < OldCols; k++){
                String val = cells[k].getContents();// 内容
                if (val != null){
                    val = val.trim();
                }
                switch (k){
                case 0:
                    break;
                case 1:
                	bl.setPhone(val);
                    break;// 电话号码
                case 2:
                	if("自定义".equals(val)){
                		bl.setLockType(1);
                		lockTypeStr = "自定义";
                	}else{
                		bl.setLockType(0);
                		lockTypeStr = "永久";
                	}
                    break;// 锁定类型2
                case 3:
                	bl.setBegintime(new Timestamp(System.currentTimeMillis()));
            		Timestamp begintime = bl.getBegintime();
                	//锁定时间
            		Integer duration = null;
                	if(StringUtils.isNotBlank(lockTypeStr)&&"自定义".equals(lockTypeStr)){
                		if(StringUtils.isNotBlank(val)){
                			duration = Integer.parseInt(val);
                		}else{
                			duration = 1;
                		}
                	}else if(StringUtils.isNotBlank(lockTypeStr)&&"永久".equals(lockTypeStr)){
                		duration = 999999;
                	}
                	bl.setDuration(duration);
                	Timestamp endtime = new Timestamp(begintime.getTime() + (long)duration * 60 * 60 * 1000);
                	bl.setEndtime(endtime);
                    break;// 锁定时长3
                case 4:
                	bl.setRemark(val);
                    break;// 备注4
                default:
                	break;
                }
            }
            // 设置添加人和添加时间
            bl.setCreateUserId(userInfo.getUserId());
            bl.setCreateTime(new Timestamp(System.currentTimeMillis()));
            bl.setIsDelete(OaConst.NOT_DELETE);
            bl.setCompanyId(this.getLoginUser().getCompanyId());
            successList.add(bl);
            
            // 保存黑名单信息
            //blacklistService.saveOrUpdate(bl); // 李立泼2015年04月08日修改，因为没有判断这个电话号码是否已经存在，所以不用了。
            Blacklist oldBlacklist = blacklistService.findByPhone(bl.getPhone());
            if (oldBlacklist == null) {
                blacklistService.saveOrUpdate(bl);
            }else {
            	oldBlacklist.setBegintime(bl.getBegintime());
                oldBlacklist.setEndtime(bl.getEndtime());
                oldBlacklist.setCreateUserId(this.getLoginUser().getUserId());
                oldBlacklist.setCreateTime(new Timestamp(System.currentTimeMillis()));
                oldBlacklist.setIsDelete(OaConst.NOT_DELETE);
                oldBlacklist.setDuration(bl.getDuration());
                oldBlacklist.setRemark(bl.getRemark());
                oldBlacklist.setLockType(bl.getLockType());
                oldBlacklist.setCompanyId(bl.getCompanyId());
                blacklistService.saveOrUpdate(oldBlacklist);
            }
            
            successNum++;
        }
    }

    /**
     * 功能：初始化输出的错误文件的头部文件
     */
    private boolean initErrorFileHead() {
        boolean flage = initErrorFile();
        if (flage && errorWWB == null){
            try{
                errorWWB = Workbook.createWorkbook(new File(errorFileNamePath));
                errorws = errorWWB.createSheet("sheet1", 0);
                Label label00 = new Label(0, 0, "序号");// 添加错误信息表的表头。这几行是固定的。
                Label label01 = new Label(1, 0, "电话号码");
                Label label02 = new Label(2, 0, "锁定类型（永久/自定义）");
                Label label03 = new Label(3, 0, "锁定时间");
                Label label04 = new Label(4, 0, "备注");
                errorws.addCell(label00);
                errorws.addCell(label01);
                errorws.addCell(label02);
                errorws.addCell(label03);
                errorws.addCell(label04);
            }catch (Exception e){
            	logger.error("initErrorFileHead error", e);
                return false;
            }
        }
        return flage;

    }
    
    
    /**
     * 功能：判断单元格是否符合要求 并记录到错误的sheet中
     * @param cell 单元格
     * @param c 列
     * @param r 行
     * @return
     */
    protected boolean maskExcelFile(Cell cell, int c, int r){
        if (initErrorFileHead()){
            String thisCellContent = cell.getContents();
            String verifyResult = verify(thisCellContent, r, c);// 通过这个单元格的内容，和这个单元格所在的列号，判断出这个单元格中的信息是否符合标准。符合返回true。
            WritableCellFormat wcfDt = new WritableCellFormat(NumberFormats.TEXT);// 设置单元格为文本格式
            Label label0x = null;
            try{
                if (verifyResult == null){// 根据这个单元格中内容的符合性来，判断是否给这一列添加底色。
                    label0x = new Label(c, errorRowsNum, thisCellContent, wcfDt);
                }else{
                    wcfDt.setBackground(Colour.RED);
                    label0x = new Label(c, errorRowsNum, thisCellContent, wcfDt);
                    /* 给错误的单元格添加批注信息 - 开始 */
                    WritableCellFeatures cellFeatures = new WritableCellFeatures();
                    cellFeatures.setComment(verifyResult);
                    label0x.setCellFeatures(cellFeatures);
                    /* 给错误的单元格添加批注信息 - 结束 */
                }
                errorws.addCell(label0x);// 把这个单元格添加到错误信息表中。若错误行数不变时，这个单元格中的数据会被覆盖掉。
            }catch (Exception e){
            	logger.error("maskExcelFile error", e);
            }
            return verifyResult == null;// 返回一个boolean值。true表示这个单元格中的数据符合标准。
        }else{
            return false;
        }
    }

}
