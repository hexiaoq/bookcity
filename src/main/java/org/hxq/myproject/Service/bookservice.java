package org.hxq.myproject.Service;


import org.hxq.myproject.Mapper.bookmapper;
import org.hxq.myproject.pojo.BOOK;
import org.hxq.myproject.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bookservice {
    @Autowired
    org.hxq.myproject.Mapper.bookmapper bookmapper;
    public void addbook(BOOK book)
    {
        bookmapper.addbook(book);
    }
    public Page<BOOK> page(int pageNo, int pagesize) {
        Page<BOOK> page=new Page<BOOK>();


        page.setPagesize(pagesize);
        Integer pagetalcount=bookmapper.querypagetotal();
        page.setPagetalcount(pagetalcount);

//根据总数据数量，获取页面数量
        Integer pagetotal=(pagetalcount%pagesize)!=0?(pagetalcount/pagesize)+1:(pagetalcount/pagesize);
        page.setPagetotal(pagetotal);
        if(pageNo>pagetotal)
        {
            pageNo=pagetotal;
        }
        if (pageNo<1)
        {
            pageNo=1;
        }
        page.setPageNo(pageNo);

        //获得当前页面数据,保存到items中
        //sql语句中的当前页面起始数据id,时分页的两个参数之一
        int begin=(pageNo-1)*pagesize;

        List<BOOK> items=bookmapper.querypageitems(begin,pagesize);
        page.setItems(items);
        return page;

    }
    public Page<BOOK> pagebyprice(int pageNo, int pagesize, int min, int max) {
        Page<BOOK> page=new Page<BOOK>();


        page.setPagesize(pagesize);
        Integer pagetalcount=bookmapper.querypagetotalbyprice(min,max);
        page.setPagetalcount(pagetalcount);

//根据总数据数量，获取页面数量
        Integer pagetotal=(pagetalcount%pagesize)!=0?(pagetalcount/pagesize)+1:(pagetalcount/pagesize);
        page.setPagetotal(pagetotal);
        if(pageNo>pagetotal)
        {
            pageNo=pagetotal;
        }
        if (pageNo<1)
        {
            pageNo=1;
        }
        page.setPageNo(pageNo);

        //获得当前页面数据,保存到items中
        //sql语句中的当前页面起始数据id,时分页的两个参数之一
        int begin=(pageNo-1)*pagesize;

        List<BOOK> items=bookmapper.querypageitemsbyprice(min,max,begin,pagesize);
        page.setItems(items);
        return page;

    }

    public void deletebookbyid(Integer id) {
        bookmapper.deletebookbyid(id);
    }

    public void updatebook(BOOK book) {
    bookmapper.updatebook(book);
    }

    public BOOK querybookbyid(Integer id) {
        return bookmapper.querybookbyid(id);
    }
}
