package cn.com.qytx.hotline.outcall.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.outcall.domain.OutCallSegment;
import cn.com.qytx.hotline.outcall.service.IOutCallSegment;
import cn.com.qytx.hotline.util.ImportBaseAction;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.oa.domain.OaConst;
import cn.com.qytx.platform.org.domain.UserInfo;
/**
 * 本地号码段维护导入
 * 功能:
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2015年4月13日
 * 修改人员：
 * 修改日期: 
 * 修改列表:
 */
public class OutCallSegmentImportAction extends ImportBaseAction {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 7361008521282082787L;
	/**
     * log4j日志对象
     */
	private final static MonitorLogger logger =new Log4jImpl(OutCallSegmentImportAction.class);
	/**
	 * 本地号码端接口
	 */
	@Autowired
	private IOutCallSegment outcallSegmentService;
	/**
     * 把导入成功的数据返回到前台
     */
	private List<OutCallSegment> successList = new ArrayList<OutCallSegment>();
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
        String regexNum = "^\\d+$";//数字
        String result = null;
        if (thisCellContent != null){
            thisCellContent = thisCellContent.trim();
        }
        if (thisCellContent == null || "".equals(thisCellContent)){
            if (c == 1){
                result = "第" + (r + 1) + "行第" + (c + 1) + "列手机号段不能为空！";
                return result;
            }
        }
        switch (c){
        case 0:// 序号
            break;
        case 1:
            if (!"".equals(thisCellContent) && thisCellContent.matches(regexNum) ){// 判断手机号段
               
            }else{
            	 result = "第" + (r + 1) + "行第" + (c + 1) + "列手机号段只能输入数字！";
            }
            if(thisCellContent.length() > 7){//判断手机号段长度
        		result = "第" + (r + 1) + "行第" + (c + 1) + "列手机号段长度不能大于7！";
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
    public String importLocalPhone(){
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
        	logger.error("importLocalPhone error", e);
        }
        finally{
            colseAllStream();
        }
        return null;
    }
    
    /**
     * 功能：把每一行的数据转换为号码段对象 并保存到list里面
     * case 对应的是表的列
     * @param cells
     */
    private void recordThisLineCalls(Cell[] cells){
        UserInfo userInfo = this.getLoginUser();
        if (cells != null && cells.length > 0){
        	OutCallSegment ocs = new OutCallSegment();
            for (int k = 0; k < OldCols; k++){
                String val = cells[k].getContents();// 内容
                if (val != null){
                    val = val.trim();
                }
                switch (k){
                case 0:
                    break;
                case 1:
                	ocs.setPhone(val);
                    break;// 手机号码段
                default:
                	break;
                }
            }
            // 设置添加人和添加时间
            ocs.setCreateTime(new Timestamp(System.currentTimeMillis()));
            ocs.setCreateUserInfo(userInfo);
            ocs.setIsDelete(OaConst.NOT_DELETE);
            ocs.setCheckState(0);
            ocs.setCompanyId(userInfo.getCompanyId());
            successList.add(ocs);
            
            // 保存本地号段信息
            OutCallSegment tmpocs = outcallSegmentService.findOutCallSegmentByPhone(ocs.getPhone());
            if (tmpocs == null) {
            	outcallSegmentService.saveOrUpdate(ocs);
            	successNum++;
            }else {
            	skipNum++;
            }
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
                Label label01 = new Label(1, 0, "手机号段");
                errorws.addCell(label00);
                errorws.addCell(label01);
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
