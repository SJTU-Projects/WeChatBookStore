package org.reins.ssh.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.reins.ssh.entity.Book;
import org.reins.ssh.entity.User;
import org.reins.ssh.service.BookService;
import org.reins.ssh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(maxAge =36000)
@Controller
@ParentPackage("json-default")
public class UserAction extends ActionSupport {

    @Autowired
    private UserService userService;

    private List<User> list;

    @Action(value = "Login", results = {
//            @Result(type="json", name="jsonlist", params={"root", "list"}),
            @Result(type="json",name="SUCCESS")
    })
    public String execute() throws Exception {
        list = userService.queryAll();
        System.out.println("Before getrequest ......");
        HttpServletRequest request = ServletActionContext.getRequest();

        String listString="";
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        listString =sb.toString();
        System.out.println(listString);

        JSONObject json_test = (JSONObject)JSONObject.parse (listString);
        String postuname="";
        String postpassword="";
        if(json_test!=null)
        {
            System.out.println("get " + json_test.get("username").toString());
            postuname=json_test.get("username").toString();
            postpassword=json_test.get("password").toString();
        }
        else{
            System.out.println("else null");
        }

        HttpServletResponse response = ServletActionContext.getResponse();
        response.reset();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");

        boolean founduname=false;
        boolean foundall=false;
        long userid=1;
        Iterator<User> it = list.iterator();
        while (it.hasNext()) {
            User user = (User) it.next();
            String queryname=user.getUsername();
            String querypassword=user.getPassword();
            if(queryname.equals(postuname) )
            {
                founduname=true;
                if( querypassword.equals(postpassword))
                {
                    foundall=true;
                    userid=user.getId();
                    break;
                }
            }
            //System.out.println("Book " + tmpstr + " " + user.getId());
        }
        ArrayList<JSONArray> userJson = new ArrayList<JSONArray>();
        ArrayList<String> arrayList = new ArrayList<String>();
        //String booksString = booksJson.toJSONString();
        //System.out.println(booksString);
        PrintWriter out = response.getWriter();

        if(foundall)
        {
            arrayList.add("access");
            userJson.add((JSONArray) JSONArray.toJSON(arrayList));
            ArrayList<String> arrayList2 = new ArrayList<String>();
            String s2 = String.valueOf(userid);
            arrayList2.add(s2);
            userJson.add((JSONArray) JSONArray.toJSON(arrayList2));
            String booksString = JSON.toJSONString(userJson, SerializerFeature.BrowserCompatible);
            System.out.println(booksString);
            out.println(booksString);
            out.flush();
            out.close();
            return "SUCCESS";
        }
        else if (founduname)
        {
            arrayList.add("wrong code");
            userJson.add((JSONArray) JSONArray.toJSON(arrayList));
            String booksString = JSON.toJSONString(userJson, SerializerFeature.BrowserCompatible);
            out.println(booksString);
            out.flush();
            out.close();
            return "SUCCESS";//找到了用户名但是密码不对
        }
        arrayList.add("no user");
        userJson.add((JSONArray) JSONArray.toJSON(arrayList));
        String booksString = JSON.toJSONString(userJson, SerializerFeature.BrowserCompatible);
        out.println(booksString);
        out.flush();
        out.close();
        return "SUCCESS";//什么都没有找到，都不对
    }
    public List<User> getList() {
        return list;
    }
}
