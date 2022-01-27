package org.reins.ssh.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import static com.opensymphony.xwork2.Action.SUCCESS;

@CrossOrigin(maxAge =3600)
@Controller
public class WelcomeAction  extends ActionSupport {

    @Action(value = "/", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String welcome() {
        System.out.println("Welcome!");
        return SUCCESS;
    }
}
