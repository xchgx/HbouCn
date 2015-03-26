package com.xchgx.bean.excel.io;

public class ExcelDataArea {

    private int firstRow;

    public ExcelDataArea() {
    }

    public ExcelDataArea(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }
}
