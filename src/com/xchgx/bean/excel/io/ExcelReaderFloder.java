/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchgx.bean.excel.io;

import com.xchgx.domain.jiuye.hukou.Hukou;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cg
 */
public class ExcelReaderFloder {

    private List<File> listFiles = new ArrayList<>();
    private List<File> supportExcels = new ArrayList<>();
    private List<File> notSupportExcels = new ArrayList<>();

    public boolean init(String floderPath) {
        File filePath = new File(floderPath);
        if (!filePath.isDirectory()) {
            return false;
        }

        File[] files = filePath.listFiles();
        Collections.addAll(listFiles, files);
        for (File f : files) {
            if (f.isDirectory()) {
                continue;
            }
            int start = f.getName().lastIndexOf(".") + 1;
            String hz = f.getName().substring(start, f.getName().length());
            if (hz.equals("xls") || hz.equals("xlsx")) {
//                System.out.println(f.getAbsoluteFile());
                try {
                    if (new ExcelReader(f.getAbsoluteFile(), new ExcelTitleArea(1, 0, 7), new ExcelDataArea(2)).isExcelSupport()) {
                        supportExcels.add(f);
                    } else {
                        notSupportExcels.add(f);
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ExcelReaderFloder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
//        System.out.println("文件总数：" + files.length);
        return true;
    }

    public Hukou findHukouFromAllExcel(String sfz) {
        Hukou hukou = null;
        for (File f : getSupportExcels()) {
            try {
                ExcelReader excelReader = new ExcelReader(f);
                hukou = excelReader.findHukouBySfz(sfz);
                excelReader.close();
                if (hukou != null) {
                    break;
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ExcelReaderFloder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExcelReaderFloder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return hukou;
    }

    public List<File> getListFiles() {
        return listFiles;
    }

    public List<File> getNotSupportExcels() {
        return notSupportExcels;
    }

    public List<File> getSupportExcels() {
        return supportExcels;
    }

    public void setListFiles(List<File> listFiles) {
        this.listFiles = listFiles;
    }

    public void setNotSupportExcels(List<File> notSupportExcels) {
        this.notSupportExcels = notSupportExcels;
    }

    public void setSupportExcels(List<File> supportExcels) {
        this.supportExcels = supportExcels;
    }

}
