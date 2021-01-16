package org.hxq.myproject.conctroller;


import org.hxq.myproject.Service.bookservice;
import org.hxq.myproject.pojo.BOOK;
import org.hxq.myproject.pojo.Page;
import org.hxq.myproject.utils.webutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class book {
    @Autowired
    org.hxq.myproject.Service.bookservice bookservice;
    @RequestMapping("/manager.html")
    public String manager()
    {
        return "pages/manager/manager";
    }
    @RequestMapping("/manager/page")
    public String page(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        int pageNo = webutils.parseInt(request.getParameter("pageNo"), 1);
        int pagesize = webutils.parseInt(request.getParameter("pagesize"), 4);
        Page<BOOK> bookPage = bookservice.page(pageNo,pagesize);
        bookPage.setUrl("manager/page");
        model.addAttribute("page",bookPage);
        request.getSession().setAttribute("page1",bookPage);

        return "pages/manager/book_manager";


    }
    @RequestMapping("/index")
    protected String page1(HttpServletRequest request, HttpServletResponse response,Model model) throws ServletException, IOException {
        int pageNo = webutils.parseInt(request.getParameter("pageNo"), 1);
        int pagesize = webutils.parseInt(request.getParameter("pagesize"), 4);
        Page<BOOK> bookPage = bookservice.page(pageNo,pagesize);
        bookPage.setUrl("index");
        model.addAttribute("page",bookPage);

       return "pages/client/index1";


    }
    @RequestMapping("/toaddbook")
    public String toaddbook()
    {
        return "pages/manager/book_add";
    }
    @RequestMapping("/addbook")
    protected void addbook(HttpServletRequest request, HttpServletResponse response,Integer pageNo) throws ServletException, IOException {
        BOOK bean = new BOOK();
        webutils.copypartobean(request.getParameterMap(), bean);
        bookservice.addbook(bean);
        response.sendRedirect(request.getContextPath()+"/manager/page?pageNo="+pageNo+1);



    }
    @RequestMapping("/delete")
    protected void deletebookbyid(HttpServletRequest request, HttpServletResponse response,Integer pageNo) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        bookservice.deletebookbyid(id);
        response.sendRedirect(request.getContextPath()+"/manager/page?pageNo="+pageNo);

    }
    @RequestMapping("/update")
    protected void updatebook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BOOK book= new BOOK();
        webutils.copypartobean(request.getParameterMap(), book);
        bookservice.updatebook(book);
        //F5刷新浏览器时,是再次执行上一次请求,而此方法为两次请求,本次请求和上次请求没有关系,刷新时不会再次添加图书,
        // 而resquest的方法则为一次请求,刷新时为反复添加图书
        response.sendRedirect(request.getContextPath()+"/manager/page?pageNo="+request.getParameter("pageNo"));

    }
    //和updatebook方法一起完成修改图书的功能
    //此方法负责将所需要修改的图书从数据库中得到,并回显到表单中,然后updatebook方法将修改后的表单封装成对象
    //同步到数据库中,完成修改,最后list显示出来所有图书,展示给用户修改后的图书表
    @RequestMapping("/toupdatebook")
    protected String getbook(HttpServletRequest request, HttpServletResponse response,Model model,Integer pageNo)throws ServletException, IOException
    { Integer id = Integer.valueOf(request.getParameter("id"));
        BOOK book = bookservice.querybookbyid(id);
        model.addAttribute("book",book);
        model.addAttribute("pageNo",pageNo);
          return "pages/manager/book_edit";
    }
    @RequestMapping("/pagebyprice")
    protected String pagebyprice(HttpServletRequest request, HttpServletResponse response,Model model) throws ServletException, IOException {
        int pageNo = webutils.parseInt(request.getParameter("pageNo"), 1);
        int pagesize = webutils.parseInt(request.getParameter("pagesize"), 4);
        int min=webutils.parseInt(request.getParameter("min"),0);
        int max=webutils.parseInt(request.getParameter("max"),10000);
        Page<BOOK> bookPage = bookservice.pagebyprice(pageNo,pagesize,min,max);
        StringBuilder s=new StringBuilder("/pagebyprice?");
        if(request.getParameter("min")!=null)
            s.append("min=").append(min);
        if(request.getParameter("max")!=null)
            s.append("&max=").append(max);


        bookPage.setUrl(String.valueOf(s));
        System.out.println(String.valueOf(s));
        model.addAttribute("page",bookPage);

        return "pages/client/index1";


    }

}
