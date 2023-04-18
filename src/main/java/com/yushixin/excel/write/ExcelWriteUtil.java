package com.yushixin.excel.write;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExcelWriteUtil {
    
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        String path = "C:\\Users\\Shane\\Desktop\\test.xlsx";
        int size = 1000;
        List<User> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            User user = new User();
            user.setId(i);
            user.setUsername("username-" + i);
            user.setPassword("password-" + i);
            user.setTel("tel-" + i);
            user.setAddress("address-" + i);
            list.add(user);
        }
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("创建文件结果：" + file.createNewFile());
        }
        SXSSFWorkbook wb = new SXSSFWorkbook();
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
    
        SXSSFSheet sheet_0 = wb.createSheet("Sheet_0");
        SXSSFSheet sheet_1 = wb.createSheet("Sheet_1");
        SXSSFSheet sheet_2 = wb.createSheet("Sheet_2");
        SXSSFSheet sheet_3 = wb.createSheet("Sheet_3");
        
        Future<Integer> future_0 = threadPool.submit(new WriteTask(sheet_0, list));
        Future<Integer> future_1 = threadPool.submit(new WriteTask(sheet_1, list));
        Future<Integer> future_2 = threadPool.submit(new WriteTask(sheet_2, list));
        Future<Integer> future_3 = threadPool.submit(new WriteTask(sheet_3, list));
    
        Integer result_0 = future_0.get();
        Integer result_1 = future_1.get();
        Integer result_2 = future_2.get();
        Integer result_3 = future_3.get();
    
        FileOutputStream fos = new FileOutputStream(file);
        wb.write(fos);
        fos.close();
        threadPool.shutdown();
    }
    
    static class WriteTask implements Callable<Integer> {
    
        private SXSSFSheet sheet;
        
        private List<User> list;
    
        public WriteTask(SXSSFSheet sheet, List<User> list) {
            this.sheet = sheet;
            this.list = list;
        }
    
        @Override
        public Integer call() {
            int size = list.size();
            System.out.println(sheet.getSheetName());
            for (int i = 0; i < size; i++) {
                User user = list.get(i);
                SXSSFRow row = sheet.createRow(i);
                
                SXSSFCell cell_id = row.createCell(0, CellType.STRING);
                cell_id.setCellValue(user.getId());
                
                SXSSFCell cell_username = row.createCell(1, CellType.STRING);
                cell_username.setCellValue(user.getUsername());
                
                SXSSFCell cell_password = row.createCell(2, CellType.STRING);
                cell_password.setCellValue(user.getPassword());
                
                SXSSFCell cell_tel = row.createCell(3, CellType.STRING);
                cell_tel.setCellValue(user.getTel());
                
                SXSSFCell cell_address = row.createCell(4, CellType.STRING);
                cell_address.setCellValue(user.getAddress());
            }
            return size;
        }
    }
}
