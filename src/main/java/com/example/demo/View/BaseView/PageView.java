package com.example.demo.View.BaseView;

import lombok.Data;

@Data
public class PageView {
    private int pageNum;
    private int pageSize;
    private String orderBy;
    private String sort;

    public PageView() {
        this.orderBy="create_time";
        this.sort="asc";
    }

    public PageView(int pageNum, int pageSize, String orderBy, String sort) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.sort = sort;
    }

    public PageView( String orderBy, String sort) {
        this.orderBy = orderBy;
        this.sort = sort;
    }

    public String getOrderByKey() {
        return this.orderBy + " "+this.sort;
    }

}
