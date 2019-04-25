package com.report.model.wp;

/**
 * wordpress文章
 */
public class Post {
    private int id;
    private int post_author;
    private String post_date;
    private String post_date_gmt;
    private String post_content;
    private String post_title;
    private String post_excerpt;
    private String post_status;
    private String post_mime_type;
    private String post_name;
    private String post_modified;
    private String post_modified_gmt;
    private int post_parent;
    private String to_ping;

    public String getPost_mime_type() {
        return post_mime_type;
    }

    public void setPost_mime_type(String post_mime_type) {
        this.post_mime_type = post_mime_type;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    private String post_type;
    private String pinged;
    private String post_content_filtered;

    public int getPost_author() {
        return post_author;
    }

    public void setPost_author(int post_author) {
        this.post_author = post_author;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getPost_date_gmt() {
        return post_date_gmt;
    }

    public void setPost_date_gmt(String post_date_gmt) {
        this.post_date_gmt = post_date_gmt;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String url, String imgSrc) {
        String contentTemplate = "<img class=\"alignnone size-full\" src=\"%s\" alt=\"\" />";
        if (url != null) {
            contentTemplate = "<a href=\"%s\">" + contentTemplate + "</a>";
            this.post_content = String.format(contentTemplate, url, imgSrc);
        }else{
            this.post_content = String.format(contentTemplate, imgSrc);
        }

    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_excerpt() {
        return post_excerpt;
    }

    public void setPost_excerpt(String post_excerpt) {
        this.post_excerpt = post_excerpt;
    }

    public String getPost_status() {
        return post_status;
    }

    public void setPost_status(String post_status) {
        this.post_status = post_status;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getPost_modified() {
        return post_modified;
    }

    public void setPost_modified(String post_modified) {
        this.post_modified = post_modified;
    }

    public String getPost_modified_gmt() {
        return post_modified_gmt;
    }

    public void setPost_modified_gmt(String post_modified_gmt) {
        this.post_modified_gmt = post_modified_gmt;
    }

    public int getPost_parent() {
        return post_parent;
    }

    public void setPost_parent(int post_parent) {
        this.post_parent = post_parent;
    }

    public String getTo_ping() {
        return to_ping;
    }

    public void setTo_ping(String to_ping) {
        this.to_ping = to_ping;
    }

    public String getPinged() {
        return pinged;
    }

    public void setPinged(String pinged) {
        this.pinged = pinged;
    }

    public String getPost_content_filtered() {
        return post_content_filtered;
    }

    public void setPost_content_filtered(String post_content_filtered) {
        this.post_content_filtered = post_content_filtered;
    }
}
