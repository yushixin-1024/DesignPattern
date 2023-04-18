package com.yushixin.excel.read;

import java.util.List;

public class ImportExcelData {
    
    private List<List<String>> firstSheetList;
    
    private List<List<String>> secondSheetList;
    
    private List<List<String>> thirdSheetList;
    
    public List<List<String>> getFirstSheetList() {
        return firstSheetList;
    }
    
    public void setFirstSheetList(List<List<String>> firstSheetList) {
        this.firstSheetList = firstSheetList;
    }
    
    public List<List<String>> getSecondSheetList() {
        return secondSheetList;
    }
    
    public void setSecondSheetList(List<List<String>> secondSheetList) {
        this.secondSheetList = secondSheetList;
    }
    
    public List<List<String>> getThirdSheetList() {
        return thirdSheetList;
    }
    
    public void setThirdSheetList(List<List<String>> thirdSheetList) {
        this.thirdSheetList = thirdSheetList;
    }
}