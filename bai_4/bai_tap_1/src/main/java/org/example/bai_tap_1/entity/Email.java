package org.example.bai_tap_1.entity;

public class Email {
    private String language;
    private int pageSize;
    private boolean filter;
    private String sign;

    public Email() {
    }

    public Email(String language, int pageSize, boolean filter, String sign) {
        this.language = language;
        this.pageSize = pageSize;
        this.filter = filter;
        this.sign = sign;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
