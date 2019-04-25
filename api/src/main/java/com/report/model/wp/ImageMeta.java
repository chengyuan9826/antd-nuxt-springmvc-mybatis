package com.report.model.wp;

public class ImageMeta {
    private int width;
    private int height;
    private String yearMonthName;
    private String fileName;
    private String mimeType = "";

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getYearMonthName() {
        return yearMonthName;
    }

    public void setYearMonthName(String yearMonthName) {
        this.yearMonthName = yearMonthName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
