package org.hxq.myproject.Service;



import org.hxq.myproject.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginservice {
    @Autowired
    org.hxq.myproject.Mapper.loginmapper loginmapper;
    public User login(User user)
    {return loginmapper.finduserbynameandpd(user);

    }
    public void  register(User user)
    {
        loginmapper.add(user);
    }
    public boolean exsistusername(User user)
    {
        if(loginmapper.finduserbyname(user)!=null)
        {
            return true;
        }
        else return false;
    }
}
