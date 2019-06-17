package com.report.exceptions;

public class ImageErrorException extends Exception {
    public ImageErrorException() {
        super("图片文件已损坏");
    }

    public ImageErrorException(String msg) {
        super(msg);
    }
}
