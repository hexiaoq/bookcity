package org.hxq.myproject.conctroller;

import org.hxq.myproject.pojo.BOOK;
import org.hxq.myproject.pojo.Cart;
import org.hxq.myproject.pojo.Cartitem;
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
public class cart {
    @Autowired
    org.hxq.myproject.Service.bookservice bookservice;
    @RequestMapping("/tocart")
    public String tocart()
    {
        return "pages/cart/cart";
    }
    @RequestMapping("/additem")
    public void additem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = webutils.parseInt(req.getParameter("id"),0);
        BOOK book = bookservice.querybookbyid(id);
        Cartitem bookitem=null;
        bookitem=new Cartitem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if(cart==null)
        {
            cart=new Cart();
            cart.additems(bookitem);
            req.getSession().setAttribute("cart",cart);

        }
        else
            cart.additems(bookitem);
        req.getSession().setAttribute("lastname",book.getName());
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);
    }
    @RequestMapping("/clear")
    protected  void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if(cart!=null)
        {cart.clear();}
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);

    }
@RequestMapping("/deleteitem")
    protected  void deleteitem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id=webutils.parseInt(req.getParameter("id"),0);
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        cart.deleteitem(id);
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);



    }
}

