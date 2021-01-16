package org.hxq.myproject.conctroller;


import com.google.code.kaptcha.Constants;
import org.hxq.myproject.Service.loginservice;
import org.hxq.myproject.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.jws.WebParam;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
public class login {
    @Autowired
    org.hxq.myproject.Service.loginservice loginservice;
@Autowired
    com.google.code.kaptcha.impl.DefaultKaptcha captchaProducer;


    @PostMapping("/login")
   public  String login(User user, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception {
        String attribute =(String) request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        request.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
        String name=request.getParameter("name");
        String code=request.getParameter("code");
        Thread.sleep(5000);
        if(code.equals(attribute)&&attribute!=null)
        {
            model.addAttribute("username",user.getName());
            if(loginservice.login(user)==null)
            {
                System.out.println("输入的账户或者密码错误");
                model.addAttribute("wrong","您输入的密码或账号错误");
                return "pages/user/login";


            }
            else {

                System.out.println("登陆成功");
                session.setAttribute("loginuser",user.getName());
                return "pages/user/login_success";

            }

        }

        else {
            if(attribute!=null){
                model.addAttribute("wrong","验证码错误");
                System.out.println("验证码错误");
                request.setAttribute("name",name);
            }
            else { model.addAttribute("wrong","请勿重复提交");
                System.out.println("请勿重复提交");}


            return "pages/user/login";

        }





    }
    @PostMapping("/register")
    public String register(User user,HttpServletRequest request,HttpServletResponse response ,Model model) throws Exception {
        String attribute =(String) request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        //防止重复提交,每一次提交都必须获得新的验证码
        request.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String code=request.getParameter("code");
        Thread.sleep(5000);
        if(code.equals(attribute)&&attribute!=null)
        {
            if(loginservice.exsistusername(user))
            {
                model.addAttribute("wrong","用户已存在");
                System.out.println("用户已存在");
               return "pages/user/regist";
                }
            else {
                loginservice.register(user);
                request.getSession().setAttribute("loginuser",user.getName());
                System.out.println("注册成功");
                return "pages/user/regist_success";

            }
        }

        else {
            if(attribute!=null){
                model.addAttribute("wrong","验证码错误");
                System.out.println("验证码错误");
                request.setAttribute("name",name);
                request.setAttribute("email",email);
            }
            else { model.addAttribute("wrong","请勿重复提交");
                System.out.println("请勿重复提交");}


            return "pages/user/regist";

        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        //1.销毁session中的用户信息
        session.invalidate();

        //2.重新重定向到首页
        return "redirect:/index";
    }
    //谷歌验证码
@RequestMapping("/kaptcha.jpg")
public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpSession session = request.getSession();
    response.setDateHeader("Expires", 0);
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
    response.setHeader("Pragma", "no-cache");
    response.setContentType("image/jpeg");
    //生成验证码
    String capText = captchaProducer.createText();
    session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
    //向客户端写出
    BufferedImage bi = captchaProducer.createImage(capText);
    ServletOutputStream out = response.getOutputStream();
    ImageIO.write(bi, "jpg", out);
    try {
        out.flush();
    } finally {
        out.close();
    }
}


}
