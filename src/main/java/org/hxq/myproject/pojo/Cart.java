package org.hxq.myproject.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private  Integer totalcount;
    private BigDecimal totalprice;
    private Map<Integer,Cartitem> items=new LinkedHashMap<Integer, Cartitem>();
    
    @Override
    public String toString() {
        return "Cart{" +
                "totalcount=" + getTotalcount() +
                ", totalprice=" + getTotalprice() +
                ", items=" + items +
                '}';
    }

    //添加商品
    public void additems(Cartitem cartitem){
       if(items.get(cartitem.getId())==null)
       {
           items.put(cartitem.getId(),cartitem);
       }
       //重复则修改原来商品中的数据
       else {
           Cartitem cartitem1 = items.get(cartitem.getId());
           cartitem1.setCount(cartitem.getCount()+1);
           cartitem1.setTotalprice(cartitem1.getPirce().multiply(new BigDecimal(cartitem1.getCount())));
       }

    }
    public void clear(){
        items.clear();
    }
    public void deleteitem(Integer id)
    {
        items.remove(id);
    }
    public void  updatecount(Integer id,Integer count)
    {
        Cartitem item = items.get(id);
        if(item!=null)
        {
            item.setCount(count);
            item.setTotalprice(item.getPirce().multiply(new BigDecimal(item.getCount())));


    }

    }

    public Integer getTotalcount() {
        Integer totalcount=0;
        for (Cartitem value : items.values()) {
            totalcount+=value.getCount();

        }
        return totalcount;
    }



    public BigDecimal getTotalprice() {
       BigDecimal totalprice=new BigDecimal(0);
        for (Cartitem value : items.values()) {
            totalprice=totalprice.add(value.getTotalprice());
        }
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }


    public Map<Integer, Cartitem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Cartitem> items) {
        this.items = items;
    }
}
