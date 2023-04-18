package com.yushixin.excel.read;

import org.apache.poi.hssf.eventusermodel.*;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.*;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取XLS格式Excel
 */
public class ExcelXlsReader implements HSSFListener {
    
    private int minColumns = -1;
    
    private POIFSFileSystem fs;
    
    /**
     * 总行数
     */
    private int totalRows = 0;
    
    /**
     * 上一行row的序号
     */
    private int lastRowNumber;
    
    /**
     * 上一单元格的序号
     */
    private int lastColumnNumber;
    
    /**
     * 是否输出formula，还是它对应的值
     */
    private boolean outputFormulaValues = true;
    
    /**
     * 用于转换formulas
     */
    private EventWorkbookBuilder.SheetRecordCollectingListener workbookBuildingListener;
    
    //excel2003工作簿
    private HSSFWorkbook stubWorkbook;
    
    private SSTRecord sstRecord;
    
    private FormatTrackingHSSFListener formatListener;
    
    private final HSSFDataFormatter formatter = new HSSFDataFormatter();
    
    /**
     * 文件的绝对路径
     */
    private String filePath = "";
    
    //表索引
    private int sheetIndex = 0;
    
    private BoundSheetRecord[] orderedBSRs;
    
    @SuppressWarnings("unchecked")
    private ArrayList boundSheetRecords = new ArrayList();
    
    private int nextRow;
    
    private int nextColumn;
    
    private boolean outputNextStringRecord;
    
    // 当前行
    private int curRow = 0;
    
    // 存储一行记录所有单元格的容器
    private List<String> cellList = new ArrayList<>();
    
    // 第一个sheet列表
    private static List<List<String>> firstSheetList = new ArrayList<>();
    // 第二个sheet列表
    private static List<List<String>> secondSheetList = new ArrayList<>();
    // 第三个sheet列表
    private static List<List<String>> thirdSheetList = new ArrayList<>();
    
    private static Map<String, List<List<String>>> allListMap = new HashMap<>();
    
    
    /**
     * 判断整行是否为空行的标记
     */
    private boolean flag = false;
    
    @SuppressWarnings("unused")
    private String sheetName;
    
    /**
     * 遍历excel下所有的sheet
     */
    public Map<String,List<List<String>>> process(File file) throws Exception {
        this.fs = new POIFSFileSystem(Files.newInputStream(file.toPath()));
        MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
        formatListener = new FormatTrackingHSSFListener(listener);
        HSSFEventFactory factory = new HSSFEventFactory();
        HSSFRequest request = new HSSFRequest();
        if (outputFormulaValues) {
            request.addListenerForAllRecords(formatListener);
        } else {
            workbookBuildingListener = new EventWorkbookBuilder.SheetRecordCollectingListener(formatListener);
            request.addListenerForAllRecords(workbookBuildingListener);
        }
        factory.processWorkbookEvents(request, fs);
        
        allListMap.put("first", new ArrayList<>(firstSheetList));
        allListMap.put("second", new ArrayList<>(secondSheetList));
        allListMap.put("third", new ArrayList<>(thirdSheetList));
        firstSheetList.clear();
        secondSheetList.clear();
        thirdSheetList.clear();
        return allListMap;
    }
    
    /**
     * HSSFListener 监听方法，处理Record
     * 处理每个单元格
     * @param record
     */
    @SuppressWarnings("unchecked")
    public void processRecord(Record record) {
        int thisRow = -1;
        int thisColumn = -1;
        String thisStr = null;
        String value;
        switch (record.getSid()) {
            case BoundSheetRecord.sid:
                boundSheetRecords.add(record);
                break;
            case BOFRecord.sid:
                // 开始处理每个sheet
                BOFRecord bofRecord = (BOFRecord) record;
                if (bofRecord.getType() == BOFRecord.TYPE_WORKSHEET) {
                    // 如果有需要，则建立子工作簿
                    if (workbookBuildingListener != null && stubWorkbook == null) {
                        stubWorkbook = workbookBuildingListener.getStubHSSFWorkbook();
                    }
                    
                    if (orderedBSRs == null) {
                        orderedBSRs = BoundSheetRecord.orderByBofPosition(boundSheetRecords);
                    }
                    sheetName = orderedBSRs[sheetIndex].getSheetname();
                    sheetIndex++;
                }
                break;
            case SSTRecord.sid:
                sstRecord = (SSTRecord) record;
                break;
            case BlankRecord.sid:
                // 单元格为空白
                BlankRecord blankRecord = (BlankRecord) record;
                thisRow = blankRecord.getRow();
                thisColumn = blankRecord.getColumn();
                thisStr = "";
                cellList.add(thisColumn, thisStr);
                break;
            case BoolErrRecord.sid:
                // 单元格为布尔类型
                BoolErrRecord boolErrRecord = (BoolErrRecord) record;
                thisRow = boolErrRecord.getRow();
                thisColumn = boolErrRecord.getColumn();
                thisStr = boolErrRecord.getBooleanValue() + "";
                cellList.add(thisColumn, thisStr);
                checkRowIsNull(thisStr);
                // 如果里面某个单元格含有值，则标识该行不为空行
                break;
            case FormulaRecord.sid:
                // 单元格为公式类型
                FormulaRecord formulaRecord = (FormulaRecord) record;
                thisRow = formulaRecord.getRow();
                thisColumn = formulaRecord.getColumn();
                if (outputFormulaValues) {
                    if (Double.isNaN(formulaRecord.getValue())) {
                        outputNextStringRecord = true;
                        nextRow = formulaRecord.getRow();
                        nextColumn = formulaRecord.getColumn();
                    } else {
                        thisStr = '"' + HSSFFormulaParser.toFormulaString(stubWorkbook, formulaRecord.getParsedExpression()) + '"';
                    }
                } else {
                    thisStr = '"' + HSSFFormulaParser.toFormulaString(stubWorkbook, formulaRecord.getParsedExpression()) + '"';
                }
                cellList.add(thisColumn, thisStr);
                checkRowIsNull(thisStr);
                // 如果里面某个单元格含有值，则标识该行不为空行
                break;
            case StringRecord.sid:
                // 单元格中公式的字符串
                if (outputNextStringRecord) {
                    StringRecord stringRecord = (StringRecord) record;
                    thisStr = stringRecord.getString();
                    thisRow = nextRow;
                    thisColumn = nextColumn;
                    outputNextStringRecord = false;
                }
                break;
            case LabelRecord.sid:
                LabelRecord labelRecord = (LabelRecord) record;
                curRow = thisRow = labelRecord.getRow();
                thisColumn = labelRecord.getColumn();
                value = labelRecord.getValue().trim();
                value = value.equals("") ? "" : value;
                cellList.add(thisColumn, value);
                checkRowIsNull(value);
                // 如果里面某个单元格含有值，则标识该行不为空行
                break;
            case LabelSSTRecord.sid:
                // 单元格为字符串类型
                LabelSSTRecord labelSSTRecord = (LabelSSTRecord) record;
                curRow = thisRow = labelSSTRecord.getRow();
                thisColumn = labelSSTRecord.getColumn();
                if (sstRecord == null) {
                    cellList.add(thisColumn, "");
                } else {
                    value = sstRecord.getString(labelSSTRecord.getSSTIndex()).toString().trim();
                    value = value.equals("") ? "" : value;
                    cellList.add(thisColumn, value);
                    checkRowIsNull(value);
                    // 如果里面某个单元格含有值，则标识该行不为空行
                }
                break;
            case NumberRecord.sid:
                // 单元格为数字类型
                NumberRecord numberRecord = (NumberRecord) record;
                curRow = thisRow = numberRecord.getRow();
                thisColumn = numberRecord.getColumn();
                
                // 第二种方式，参照formatNumberDateCell里面的实现方法编写
                double valueDouble = numberRecord.getValue();
                String formatString = formatListener.getFormatString(numberRecord);
                if (formatString.contains("m/d/yy")) {
                    formatString = "yyyy-MM-dd hh:mm:ss";
                }
                int formatIndex=formatListener.getFormatIndex(numberRecord);
                value = formatter.formatRawCellContents(valueDouble, formatIndex, formatString).trim();
                value = value.equals("") ? "" : value;
                // 向容器加入列值
                cellList.add(thisColumn, value);
                checkRowIsNull(value);
                // 如果里面某个单元格含有值，则标识该行不为空行
                break;
            default:
                break;
        }
        
        // 遇到新行的操作
        if (thisRow != -1 && thisRow != lastRowNumber) {
            lastColumnNumber = -1;
        }
        
        // 空值的操作
        if (record instanceof MissingCellDummyRecord) {
            MissingCellDummyRecord mc = (MissingCellDummyRecord) record;
            curRow = thisRow = mc.getRow();
            thisColumn = mc.getColumn();
            cellList.add(thisColumn, "");
        }
        
        // 更新行和列的值
        if (thisRow > -1)
            lastRowNumber = thisRow;
        if (thisColumn > -1)
            lastColumnNumber = thisColumn;
        
        // 行结束时的操作
        if (record instanceof LastCellOfRowDummyRecord) {
            if (minColumns > 0) {
                // 列值重新置空
                if (lastColumnNumber == -1) {
                    lastColumnNumber = 0;
                }
            }
            lastColumnNumber = -1;
            
            if (flag && curRow != 0) {
                // 该行不为空行且该行不是第一行，发送（第一行为列名，不需要）
                switch(sheetIndex) {
                    case 1 :
                        // 第一个sheet
                        List<String> a = new ArrayList<>(cellList);
                        firstSheetList.add(a);
                        break;
                    case 2 :
                        // 第二个sheet
                        List<String> b = new ArrayList<>(cellList);
                        secondSheetList.add(b);
                        break;
                    case 3 :
                        // 第三个sheet
                        List<String> c =  new ArrayList<>(cellList);
                        thirdSheetList.add(c);
                        break;
                    default :
                        break;
                }
                totalRows++;
            }
            //清空容器
            cellList.clear();
            flag = false;
        }
    }
    
    /**
     * 如果里面某个单元格含有值，则标识该行不为空行
     * @param value 单元格的值
     */
    public void checkRowIsNull(String value){
        if (value != null && !"".equals(value)) {
            flag = true;
        }
    }
}