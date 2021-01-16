package org.hxq.myproject.pojo;

import java.math.BigDecimal;

public class Cartitem {
    private  Integer id;
    private String name;
    private  Integer count;
    private BigDecimal pirce;
    private BigDecimal totalprice;
   

    public Cartitem( Integer id, String name,  Integer count, BigDecimal pirce, BigDecimal totalprice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.pirce = pirce;
        this.totalprice = totalprice;
    }

    public Cartitem() {
    }

    public  Integer getId() {
        return id;
    }

    public void setId( Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  Integer getCount() {
        return count;
    }

    public void setCount( Integer count) {
        this.count = count;
    }

    public BigDecimal getPirce() {
        return pirce;
    }

    public void setPirce(BigDecimal pirce) {
        this.pirce = pirce;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    @Override
    public String toString() {
        return "Cartitem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", pirce=" + pirce +
                ", totalprice=" + totalprice +
                '}';
    }
}
