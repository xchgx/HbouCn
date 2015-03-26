package com.xchgx.bean.excel.io;

public class ExcelTitleArea {

    private int row;
    private int column;
    private int width;

    public ExcelTitleArea() {
    }

    public ExcelTitleArea(int row, int column, int width) {
        this.row = row;
        this.column = column;
        this.width = width;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
