/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchgx.service.jiuye.hukou;

import com.xchgx.bean.excel.io.ExcelDataArea;
import com.xchgx.bean.excel.io.ExcelReader;
import com.xchgx.bean.excel.io.ExcelReaderFloder;
import com.xchgx.bean.excel.io.ExcelTitleArea;
import com.xchgx.domain.jiuye.hukou.Hukou;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author cg
 *
 * @version 1.1 新增getHukouByDataRow()方法。
 */
@Service
public class HukouService {

    public String doImportExcelToMysql(String file) {
        //TODO 将刚刚上传的Excel文件导入到数据库中，进行同步。
        return "";
    }

    /**
     * 从文件中获取指定行数据
     *
     * @param file 文件路径String
     * @param row 数据行的第几行，不是excel表格中的行数
     * @return Hukou对象
     */
    public Hukou getHukouByDataRowFromExcel(String file, int row) {
        try {
            //TODO从Excel文件中获取指定的行数据，并封装程Hukou对象
            ExcelReader er = new ExcelReader(file, new ExcelTitleArea(1, 0, 7), new ExcelDataArea(2));
            Hukou hk = er.getRowData(row);
            er.close();
            return hk;
        } catch (IOException | IllegalArgumentException ex) {
            Logger.getLogger(HukouService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private static final Logger LOG = Logger.getLogger(HukouService.class.getName());

    /**
     * 读取Excel所有的数据
     *
     * @param file Excel文件物理路径
     * @return Map<身份证, Hukou>
     * @throws FileNotFoundException
     * @throws IOException
     */
    Map<String, Hukou> getHukouListByExcel(String file) {
        ExcelReader er;
        try {
            er = new ExcelReader(file, new ExcelTitleArea(1, 0, 7), new ExcelDataArea(2));
            Map<String, Hukou> rowData = er.getRowData();
            er.close();
            return rowData;
        } catch (IOException | IllegalArgumentException ex) {
            Logger.getLogger(HukouService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * 通过身份证搜索相关学生信息
     *
     * @param file Excel文件物理路径
     * @param sfz 身份证
     * @return Hukou
     */
    public Hukou findHukouBySfz(String file, String sfz) {
        return getHukouListByExcel(file) == null ? null : getHukouListByExcel(file).get(sfz);
    }

    /**
     * 通过身份证搜索相关学生信息
     *
     * @param file Excel文件物理路径
     * @param sfz 身份证
     * @param singleExcel true:单个文件逐行扫描查找 false:批量读取Excel形成Map数据库查找，效率低
     * @return Hukou
     */
    public Hukou findHukouBySfz(String file, String sfz, boolean singleExcel) {
        if (!singleExcel) {
            return getHukouListByExcel(file) == null ? null : getHukouListByExcel(file).get(sfz);
        } else {
            return findHukouBySfzFromExcel(file, sfz);
        }
    }

    /**
     * 搜索所有Excel并单文件单行逐行查询
     *
     * @param file 文件或存放Excel文件的路径
     * @param sfz
     * @return
     */
    private Hukou findHukouBySfzFromExcel(String file, String sfz) {
        System.out.println(file);
        ExcelReaderFloder excelReaderFloder = new ExcelReaderFloder();
        File filePath = new File(file);
        if(!filePath.exists()) filePath.mkdir();
        if (filePath.isDirectory()) {
            excelReaderFloder.init(filePath.toString());
        } else {
            excelReaderFloder.init(new File(file).getAbsolutePath());
        }
        return excelReaderFloder.findHukouFromAllExcel(sfz);
    }

    public boolean uploadExcelFileIsSupport(String file) {
        try {
            ExcelReader excelReader = new ExcelReader(file);
            boolean support = excelReader.isExcelSupport();
            if (support) {
                excelReader.close();
            }
            return support;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HukouService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HukouService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
