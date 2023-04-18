package com.yushixin.excel.read;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取XLSX格式Excel
 */
public class ExcelXlsxReader extends DefaultHandler {
    
    /**
     * 单元格中的数据可能的数据类型
     */
    enum CellDataType {
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER, DATE, NULL
    }
    
    /**
     * 共享字符串
     */
    private SharedStrings sst;
    
    /**
     * 上一次的索引值
     */
    private String lastIndex;
    
    /**
     * 文件的绝对路径
     */
    private final String filePath = "";
    
    /**
     * 工作表索引
     */
    private int sheetIndex = 0;
    
    /**
     * sheet名
     */
    private String sheetName = "";
    
    /**
     * 总行数
     */
    private int totalRows = 0;
    
    /**
     * 一行内cell集合
     */
    private static final List<String> cellList = new ArrayList<>();
    
    // 第一个sheet列表
    private static final List<List<String>> firstSheetList = new ArrayList<>();
    // 第二个sheet列表
    private static final List<List<String>> secondSheetList = new ArrayList<>();
    // 第三个sheet列表
    private static final List<List<String>> thirdSheetList = new ArrayList<>();
    
    private static final Map<String,List<List<String>>> allListMap = new HashMap<>();
    
    /**
     * 判断整行是否为空行的标记
     */
    private boolean flag = false;
    
    /**
     * 当前行
     */
    private int curRow = 1;
    
    /**
     * 当前列
     */
    private int curCol = 0;
    
    /**
     * T元素标识
     */
    private boolean isTElement;
    
    /**
     * 异常信息，如果为空则表示没有异常
     */
    private String exceptionMessage;
    
    /**
     * 单元格数据类型，默认为字符串类型
     */
    private CellDataType nextDataType = CellDataType.SSTINDEX;
    
    private final DataFormatter formatter = new DataFormatter();
    
    /**
     * 单元格日期格式的索引
     */
    private short formatIndex;
    
    /**
     * 日期格式字符串
     */
    private String formatString;
    
    // 定义前一个元素和当前元素的位置，用来计算其中空的单元格数量，如A6和A8等
    private String preRef = null, ref = null;
    
    // 定义该文档一行最大的单元格数，用来补全一行最后可能缺失的单元格
    private String maxRef = null;
    
    /**
     * 单元格
     */
    private StylesTable stylesTable;
    
    /**
     * 遍历工作簿中所有的电子表格，并缓存在mySheetList中
     */
    public Map<String,List<List<String>>> process(File file) throws Exception {
        OPCPackage pkg = OPCPackage.open(file);
        XSSFReader xssfReader = new XSSFReader(pkg);
        stylesTable = xssfReader.getStylesTable();
        SharedStrings sst = xssfReader.getSharedStringsTable();
        XMLReader parser = XMLReaderFactory.createXMLReader("com.sun.org.apache.xerces.internal.parsers.SAXParser");
        this.sst = sst;
        parser.setContentHandler(this);
        XSSFReader.SheetIterator sheets = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
        // 遍历sheet
        while (sheets.hasNext()) {
            // 标记初始行为第一行
            curRow = 1;
            sheetIndex ++;
            // sheets.next()和sheets.getSheetName()不能换位置，否则sheetName报错
            InputStream sheet = sheets.next();
            sheetName = sheets.getSheetName();
            InputSource sheetSource = new InputSource(sheet);
            // 解析excel的每条记录，在这个过程中startElement()、characters()、endElement()这三个函数会依次执行
            parser.parse(sheetSource);
            sheet.close();
        }
        allListMap.put("first",new ArrayList<>(firstSheetList));
        allListMap.put("second",new ArrayList<>(secondSheetList));
        allListMap.put("third",new ArrayList<>(thirdSheetList));
        firstSheetList.clear();
        secondSheetList.clear();
        thirdSheetList.clear();
        return allListMap;
    }
    
    /**
     * 第一个执行
     *
     * @param uri
     * @param localName
     * @param name
     * @param attributes
     */
    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) {
        //c => 单元格
        if ("c".equals(name)) {
            //前一个单元格的位置
            if (preRef == null) {
                preRef = attributes.getValue("r");
            } else {
                preRef = ref;
            }
            
            //当前单元格的位置
            ref = attributes.getValue("r");
            //设定单元格类型
            this.setNextDataType(attributes);
        }
        
        //当元素为t时
        isTElement = "t".equals(name);
        
        //置空
        lastIndex = "";
    }
    
    /**
     * 第二个执行
     * 得到单元格对应的索引值或是内容值
     * 如果单元格类型是字符串、INLINESTR、数字、日期，lastIndex则是索引值
     * 如果单元格类型是布尔值、错误、公式，lastIndex则是内容值
     * @param ch
     * @param start
     * @param length
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        lastIndex += new String(ch, start, length);
    }
    
    /**
     * 第三个执行
     *
     * @param uri
     * @param localName
     * @param name
     */
    @Override
    public void endElement(String uri, String localName, String name) {
        // t元素也包含字符串
        if (isTElement) {
            // 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
            String value = lastIndex.trim();
            cellList.add(curCol, value);
            curCol++;
            isTElement = false;
            // 如果里面某个单元格含有值，则标识该行不为空行
            if (value != null && !"".equals(value)) {
                flag = true;
            }
        } else if ("v".equals(name)) {
            // 根据索引值获取对应的单元格值：v => 单元格的值，如果单元格是字符串，则v标签的值为该字符串在SST中的索引
            String value = this.getDataValue(lastIndex.trim(), "");
            // 补全单元格之间的空单元格
            if (!ref.equals(preRef)) {
                int len = countNullCell(ref, preRef);
                for (int i = 0; i < len; i++) {
                    cellList.add(curCol, "");
                    curCol++;
                }
            }
            cellList.add(curCol, value);
            curCol++;
            // 如果里面某个单元格含有值，则标识该行不为空行
            if (value != null && !"".equals(value)) {
                flag = true;
            }
        } else {
            // 如果标签名称为row，这说明已到行尾，调用optRows()方法
            if ("row".equals(name)) {
                // 默认第一行为表头，以该行单元格数目为最大数目
                if (curRow == 1) {
                    maxRef = ref;
                }
                // 补全一行尾部可能缺失的单元格
                if (maxRef != null) {
                    int len = countNullCell(maxRef, ref);
                    for (int i = 0; i <= len; i++) {
                        cellList.add(curCol, "");
                        curCol++;
                    }
                }
    
                //该行不为空行且该行不是第一行，则发送（第一行为列名，不需要）
                if (flag && curRow != 1) {
                    switch(sheetIndex) {
                        case 1 :
                            //第一个sheet
                            List<String> a = new ArrayList<>(cellList);
                            firstSheetList.add(a);
                            break;
                        case 2 :
                            //第二个sheet
                            List<String> b = new ArrayList<>(cellList);
                            secondSheetList.add(b);
                            break;
                        case 3 :
                            //第三个sheet
                            List<String> c =  new ArrayList<>(cellList);
                            thirdSheetList.add(c);
                            break;
                        default :
                            break;
                    }
                    totalRows++;
                }
                
                cellList.clear();
                curRow++;
                curCol = 0;
                preRef = null;
                ref = null;
                flag=false;
            }
        }
    }
    
    /**
     * 处理数据类型
     *
     * @param attributes
     */
    public void setNextDataType(Attributes attributes) {
        // cellType为空，则表示该单元格类型为数字
        nextDataType = CellDataType.NUMBER;
        formatIndex = -1;
        formatString = null;
        // 单元格类型
        String cellType = attributes.getValue("t");
        // 单元格样式
        String cellStyleStr = attributes.getValue("s");
        // 获取单元格的位置，如A1,B1
        String columnData = attributes.getValue("r");
        
        if ("b".equals(cellType)) {
            // 处理布尔值
            nextDataType = CellDataType.BOOL;
        } else if ("e".equals(cellType)) {
            // 处理错误
            nextDataType = CellDataType.ERROR;
        } else if ("inlineStr".equals(cellType)) {
            nextDataType = CellDataType.INLINESTR;
        } else if ("s".equals(cellType)) {
            // 处理字符串
            nextDataType = CellDataType.SSTINDEX;
        } else if ("str".equals(cellType)) {
            nextDataType = CellDataType.FORMULA;
        }
        
        if (cellStyleStr != null) {
            // 处理日期
            int styleIndex = Integer.parseInt(cellStyleStr);
            XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
            formatIndex = style.getDataFormat();
            formatString = style.getDataFormatString();
            
            if (formatString.contains("m/d/yy")) {
                nextDataType = CellDataType.DATE;
                formatString = "yyyy-MM-dd hh:mm:ss";
            }
        }
    }
    
    /**
     * 对解析出来的数据进行类型处理
     * @param value 单元格的值，
     *        value代表解析：BOOL的为0或1， ERROR的为内容值，FORMULA的为内容值，INLINESTR的为索引值需转换为内容值，
     *        SSTINDEX的为索引值需转换为内容值， NUMBER为内容值，DATE为内容值
     * @param thisStr 一个空字符串
     */
    @SuppressWarnings("deprecation")
    public String getDataValue(String value, String thisStr) {
        // 这几个的顺序不能随便交换，交换了很可能会导致数据错误
        switch (nextDataType) {
            case BOOL:
                // 布尔值
                char first = value.charAt(0);
                thisStr = first == '0' ? "FALSE" : "TRUE";
                break;
            case ERROR:
                // 错误
                thisStr = "\"ERROR:" + value + '"';
                break;
            case FORMULA:
                // 公式
                thisStr = '"' + value + '"';
                break;
            case INLINESTR:
                XSSFRichTextString rtsi = new XSSFRichTextString(value);
                thisStr = rtsi.toString();
                break;
            case SSTINDEX:
                // 字符串
                try {
                    // 根据idx索引值获取内容值
                    int idx = Integer.parseInt(value);
                    thisStr = sst.getItemAt(idx).toString();
                } catch (NumberFormatException ex) {
                    thisStr = value;
                }
                break;
            case NUMBER:
                // 数字
                if (formatString != null) {
                    thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString).trim();
                } else {
                    thisStr = value;
                }
                thisStr = thisStr.replace("_", "").trim();
                break;
            case DATE:
                // 日期
                thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString);
                // 对日期字符串作特殊处理，去掉T
                thisStr = thisStr.replace("T", " ");
                break;
            default:
                thisStr = " ";
                break;
        }
        return thisStr;
    }
    
    public int countNullCell(String ref, String preRef) {
        // Excel2007最大行数是1048576，最大列数是16384，最后一列列名是XFD
        String xfd = ref.replaceAll("\\d+", "");
        String xfd_1 = preRef.replaceAll("\\d+", "");
        
        xfd = fillChar(xfd, 3, '@', true);
        xfd_1 = fillChar(xfd_1, 3, '@', true);
        
        char[] letter = xfd.toCharArray();
        char[] letter_1 = xfd_1.toCharArray();
        int res = (letter[0] - letter_1[0]) * 26 * 26 + (letter[1] - letter_1[1]) * 26 + (letter[2] - letter_1[2]);
        return res - 1;
    }
    
    public String fillChar(String str, int len, char let, boolean isPre) {
        int len_1 = str.length();
        if (len_1 < len) {
            StringBuilder sb = new StringBuilder();
            if (isPre) {
                for (int i = 0; i < (len - len_1); i++) {
                    sb.append(let).append(str);
                }
            } else {
                for (int i = 0; i < (len - len_1); i++) {
                    sb.append(str).append(let);
                }
            }
            return sb.toString();
        }
        return str;
    }
    
    /**
     * @return the exceptionMessage
     */
    public String getExceptionMessage() {
        return exceptionMessage;
    }
    
}