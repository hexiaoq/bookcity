package org.hxq.myproject.pojo;

import java.math.BigDecimal;

public class BOOK {
private String name;
private BigDecimal price;
private Integer id;
private String author;
private Integer stock;
private Integer sales;
private String img_path= "static/css/logo.gif";

    public BOOK() {
    }

    @Override
    public String toString() {
        return "BOOK{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", author='" + author + '\'' +
                ", stock=" + stock +
                ", sales=" + sales +
                ", img_path='" + img_path + '\'' +
                '}';
    }

    public BOOK(Integer id,String name, BigDecimal price, String author, Integer stock, Integer sales, String img_path) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.stock = stock;
        this.sales = sales;
        if(img_path!=null||!("".equals(img_path))){
            this.img_path = img_path;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        if(img_path!=null||!"".equals(img_path)){
            this.img_path = img_path;
        }
    }
}
