package com.xchgx.bean.excel.io;

import com.xchgx.domain.jiuye.hukou.Hukou;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

    private ExcelTitleArea excelTitleArea;
    private ExcelDataArea excelDataArea;
    private FileInputStream fileInputStream;
//    private POIFSFileSystem fs;
    private Workbook wb;
    private Sheet sheet;
    private boolean excelSupport = false;

    /**
     * 初始化Excel阅读器，
     *
     * @param file 文件路径（物理路径）
     * @param excelTitleArea 标题行区域配置
     * @param excelDataArea 数据行区域配置
     * @throws FileNotFoundException
     */
    public ExcelReader(String file, ExcelTitleArea excelTitleArea, ExcelDataArea excelDataArea) throws FileNotFoundException {
        this.excelTitleArea = excelTitleArea;
        this.excelDataArea = excelDataArea;
        this.fileInputStream = new FileInputStream(file);
        init();
    }

    /**
     * 初始化Excel阅读器，
     *
     * @param file 文件对象
     * @param excelTitleArea 标题行区域配置
     * @param excelDataArea 数据行区域配置
     * @throws FileNotFoundException
     */
    public ExcelReader(File file, ExcelTitleArea excelTitleArea, ExcelDataArea excelDataArea) throws FileNotFoundException {
        this.excelTitleArea = excelTitleArea;
        this.excelDataArea = excelDataArea;
        this.fileInputStream = new FileInputStream(file);
        init();
    }

    /**
     * 初始化Excel阅读器，同时给定一个缺省值 this.excelDataArea = new ExcelDataArea(2);
     * this.excelTitleArea = new ExcelTitleArea(1, 0, 7);
     *
     * @param file 文件对象
     * @throws FileNotFoundException
     */
    public ExcelReader(File file) throws FileNotFoundException {
        this.excelDataArea = new ExcelDataArea(2);
        this.excelTitleArea = new ExcelTitleArea(1, 0, 7);
        this.fileInputStream = new FileInputStream(file);
        init();
    }

    /**
     * 初始化Excel阅读器，同时给定一个缺省值 this.excelDataArea = new ExcelDataArea(2);
     * this.excelTitleArea = new ExcelTitleArea(1, 0, 7);
     *
     * @param file 文件路径（物理路径）
     * @throws FileNotFoundException
     */
    public ExcelReader(String file) throws FileNotFoundException {
        this.excelDataArea = new ExcelDataArea(2);
        this.excelTitleArea = new ExcelTitleArea(1, 0, 7);
        this.fileInputStream = new FileInputStream(file);
        init();
    }

    /**
     * 初始化工作簿等对象
     */
    private void init() {
        try {
            wb = WorkbookFactory.create(fileInputStream);
            sheet = wb.getSheetAt(0);
            excelSupport = true;
        } catch (IllegalArgumentException | InvalidFormatException | IOException ex) {
//            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
            excelSupport = false;
        }
    }

    /**
     * 从Excel文件中获取指定数据行的指定列单元格数据。
     *
     * @param row 数据行
     * @param column 数据列
     * @return 返回单元格数据String
     */
    public String getRowData(int row, int column) {
        // 获取数据区域的row行column列单元格内容
        Row r = sheet.getRow(excelDataArea.getFirstRow() + row);
        if (r == null) {
            return "";
        }
//        HSSFRow r = getSheet().getRow(excelDataArea.getFirstRow() + row);
//        Cell cell = getSheet().getCell(excelTitleArea.getColumn() + column, excelDataArea.getFirstRow() + row);
//        return cell.getContents();
        Cell cell = r.getCell(excelTitleArea.getColumn() + column);
//        return getStringCellValue(r.getCell(excelTitleArea.getColumn() + column));
        return printExcelX(cell);
    }

    /**
     * 从Excel文件中获取指定的数据行，同时指定分隔符，将字段用分隔符连接形成一串String
     *
     * @param row 数据行
     * @param separator 分隔符
     * @return
     */
    public String getRowData(int row, String separator) {
        String result = "";
        Row r = getSheet().getRow(excelDataArea.getFirstRow() + row);
        if (r == null) {
            return "";
        }
        for (int col = 0; col < excelTitleArea.getWidth(); col++) {
            result += printExcelX(r.getCell(excelTitleArea.getColumn() + col));
//            result += getSheet().getCell(excelTitleArea.getColumn() + col, excelDataArea.getFirstRow() + row).getContents();
            result += col == excelTitleArea.getWidth() - 1 ? "" : separator;
        }
        return result;
        //return "20132011013,陈刚,计算机,男,432901198310103018,湖南,湖北省武汉市洪山区,没有";
    }

    /**
     * 从Excel文件中获取指定的数据行（row），封装成Hukou对象
     *
     * @param row
     * @return
     */
    public Hukou getRowData(int row) {
        Row r = getSheet().getRow(excelDataArea.getFirstRow() + row);
        Row titleRow = getSheet().getRow(excelTitleArea.getRow());
        if (r == null || titleRow == null) {
            return null;
        }
        Map<String, String> hukouTitleAndData = new HashMap<>();
        for (int col = 0; col < excelTitleArea.getWidth(); col++) {
            String title = printExcelX(titleRow.getCell(excelTitleArea.getColumn() + col));

            //一定要先判断r是否为空，如果行为空则不需要在进行行内列单元格的判断
            hukouTitleAndData.put(title, null == r ? "" : printExcelX(r.getCell(excelTitleArea.getColumn() + col)));
        }
        return parse(hukouTitleAndData);
    }

    /**
     * 从Excel中获取所有数据行，封装成Hukou对象全部保存在Map中，key：sfz，value：Hukou
     *
     * @return
     */
    public Map<String, Hukou> getRowData() {
        Map<String, Hukou> hukouMap = new HashMap<>();
        int n = 0;
        while (true) {
            Hukou hukou = getRowData(n++);
            if (hukou == null) {
                break;
            }
            if (!validHukouIsFail(hukou)) {
                hukouMap.put(hukou.getSfz(), hukou);
            } else {
                break;
            }
        }
        return hukouMap;
    }

    /**
     * 判断Hukou对象是否满足条件，
     * <li>学号不能为空</li>
     * <li>姓名不能为空</li>
     * <li>专业不能为空</li>
     * <li>身份证不能为空</li>
     * <li>户口去向不能为空</li>
     *
     * @param hukou
     * @return true：Hukou不满足条件必填字段存在空值 false：Hukou满足条件有效
     */
    private boolean validHukouIsFail(Hukou hukou) {
        return validHukouValueIsEmptyOrNull(hukou.getNo())
                || validHukouValueIsEmptyOrNull(hukou.getName())
                || validHukouValueIsEmptyOrNull(hukou.getProfession())
                || validHukouValueIsEmptyOrNull(hukou.getSfz())
                || validHukouValueIsEmptyOrNull(hukou.getWhere());

    }

    /**
     * 判断Value值是否为空
     *
     * @param hukouValue Hukou的属性值
     * @return true：没有值 false：有值
     */
    private boolean validHukouValueIsEmptyOrNull(String hukouValue) {
        return null == hukouValue || null == hukouValue.trim() || hukouValue.trim().equals("");
    }

    /**
     * 从包含有一整行数据的Map<列名,行元数据>转换成Hukou对象。
     *
     * @param map 包含一整行数据。Key:列名 value:元数据
     * @return Hukou对象
     */
    private Hukou parse(Map<String, String> map) {
        Hukou hukou = new Hukou();
        for (String s : map.keySet()) {
            switch (s.trim()) {
                case "学号":
                    hukou.setNo(map.get(s));
                    break;
                case "姓名":
                    hukou.setName(map.get(s));
                    break;
                case "性别":
                    hukou.setSex(map.get(s));
                    break;
                case "专业":
                    hukou.setProfession(map.get(s));
                    break;
                case "身份证":
                    hukou.setSfz(map.get(s));
                    break;
                case "毕业去向":
                    hukou.setWhere(map.get(s));
                    break;
                case "报到证签往单位名称":
                    hukou.setCompany(map.get(s));
                    break;
            }
        }
        return hukou;
    }

    /**
     * 获得标题名称
     *
     * @param index 从0开始
     * @return
     */
    public String getTitleName(int index) {
        System.out.println("excelTitleArea.getRow : " + excelTitleArea.getRow());
        Row r = getSheet().getRow(excelTitleArea.getRow());
        if (r == null) {
            return "";
        }
        Cell cell = r.getCell(excelTitleArea.getColumn() + index);
//        Cell cell = getSheet().getCell(excelTitleArea.getColumn() + index, excelTitleArea.getRow());
//        return cell.getContents();
        return printExcelX(cell);
    }

//    private String getStringCellValue(HSSFCell cell) {
//        String strCell = "";
//        switch (cell.getCellType()) {
//            case HSSFCell.CELL_TYPE_STRING:
//                strCell = cell.getStringCellValue();
//                break;
//            case HSSFCell.CELL_TYPE_NUMERIC:
//                strCell = String.valueOf(cell.getNumericCellValue());
//                break;
//            case HSSFCell.CELL_TYPE_BOOLEAN:
//                strCell = String.valueOf(cell.getBooleanCellValue());
//                break;
//            case HSSFCell.CELL_TYPE_BLANK:
//                strCell = "";
//                break;
//            default:
//                strCell = "";
//                break;
//        }
//        if (strCell.equals("") || strCell == null) {
//            return "";
//        }
//        if (cell == null) {
//            return "";
//        }
//        return strCell;
//    }
//    private String printExcel(Cell cell) {
//
//        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
//            System.out.println("单元格是字符串，值是： " + cell.getStringCellValue());
//            return cell.getStringCellValue();
//        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
//            System.out.println("单元格是数字，值是： " + cell.getNumericCellValue());
//            return "" + cell.getNumericCellValue();
//        } else {
//            System.out.println("单元格的值不是字符串或数值。");
//            return "单元格的值不是字符串或数值。";
//        }
//    }
    /**
     * 将单元格对象转换层字符串值（取单元格的值）
     *
     * @param cell 单元格
     * @return String单元格值
     */
    private String printExcelX(Cell cell) {
        try {
            if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
//                if (cell.getStringCellValue() == null) {
//                    System.out.println("单元格是字符串，值是： Null");
//                } else {
//                    System.out.println("单元格是字符串，值是： " + cell.getStringCellValue());
//                }
                return null == cell.getStringCellValue() ? "" : cell.getStringCellValue();
            } else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
//                System.out.println("单元格是数字，值是： " + cell.getNumericCellValue() == null ? "0" : cell.getNumericCellValue());
                return (String) ("" + cell.getNumericCellValue() == null ? "0" : cell.getNumericCellValue());
            } else {
//                System.out.println("单元格的值不是字符串或数值。");
                return "";
            }
        } catch (NullPointerException npe) {
            return "";
        }
    }

    /**
     * 依次查找Excel文件中身份证列（第5列）的数据，与参数sfz比较。条件符合则获取该行，封装成户口对象
     *
     * @param sfz 身份证值
     * @return Hukou
     */
    public Hukou findHukouBySfz(String sfz) {
        String sfzValue = getRowData(0, 4);
        int n = 0;
        Hukou hukou = null;
        while (sfzValue != null && !sfzValue.equals("")) {
            if (sfzValue.equals(sfz)) {
                System.out.println("expect: " + sfzValue + "acture: " + sfz);
                hukou = getRowData(n);
                break;
            }
            sfzValue = getRowData(++n, 4);
        }
        return hukou;
    }

    public void close() throws IOException {
        wb.close();
        fileInputStream.close();
    }

    public ExcelTitleArea getExcelTitleArea() {
        return excelTitleArea;
    }

    public void setExcelTitleArea(ExcelTitleArea excelTitleArea) {
        this.excelTitleArea = excelTitleArea;
    }

    public ExcelDataArea getExcelDataArea() {
        return excelDataArea;
    }

    public void setExcelDataArea(ExcelDataArea excelDataArea) {
        this.excelDataArea = excelDataArea;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public boolean isExcelSupport() {
        return excelSupport;
    }

    public void setExcelSupport(boolean excelSupport) {
        this.excelSupport = excelSupport;
    }

}
