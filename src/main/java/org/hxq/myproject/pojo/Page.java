package org.hxq.myproject.pojo;

import java.util.List;

/*
*分页对象
*@ 何小强
*/
public class Page<T> {
    //每页显示的数量
    private static final int PAGE_SIZE =4;

    private Integer pagesize=PAGE_SIZE;

    @Override
    public String toString() {
        return "Page{" +
                "pagesize=" + pagesize +
                ", pageNo=" + pageNo +
                ", pagetotal=" + pagetotal +
                ", pagetalcount=" + pagetalcount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    //当前的页码
    private Integer pageNo;
    //总页码数
    private Integer pagetotal;

//总记录数
    private  Integer pagetalcount;
    // 当前页面数据
    private List<T> items;

    //此类页面进行翻页时所要用到的servlet方法
    private  String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPagetotal() {
        return pagetotal;
    }

    public void setPagetotal(Integer pagetotal) {
        this.pagetotal = pagetotal;
    }

    public Integer getPagetalcount() {
        return pagetalcount;
    }

    public void setPagetalcount(Integer pagetalcount) {
        this.pagetalcount = pagetalcount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
