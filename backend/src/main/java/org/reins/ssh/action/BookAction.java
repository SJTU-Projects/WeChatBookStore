package org.reins.ssh.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Lombok;
import lombok.var;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.reins.ssh.entity.Book;
import org.reins.ssh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import static com.mysql.cj.xdevapi.Type.JSON;

@CrossOrigin(maxAge =36000)
@Controller
@ParentPackage("json-default")
public class BookAction extends ActionSupport {

    @Autowired
    private BookService bookService;

    private List<Book> list;

    @Action(value = "getjson", results = {
//            @Result(type="json", name="jsonlist", params={"root", "list"}),
            @Result(type="json", name="jsonlist"),
//            @Result(type="redirect", name="jsonlist", location = "/index.jsp"),
            @Result(name = "error", location = "/error.html")
    })
    public String execute() throws Exception {


        System.out.println("JSON Action ......");

        list = bookService.queryAll();
        System.out.println("Before getrequest ......");
        HttpServletRequest request = ServletActionContext.getRequest();
//        InputStream inputStream = request.getInputStream();
//        Reader input = new InputStreamReader(inputStream);
//        Writer output = new StringWriter();
//        char[] buffer = new char[1024 * 4];
//        int n = 0;
//        while(-1 != (n = input.read(buffer))) {
//            output.write(buffer, 0, n);
//        }
        System.out.println("...");
//        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
//        StringBuilder sb = new StringBuilder("");
//        String temp;
//        while ((temp = br.readLine()) != null) {
//            sb.append(temp);
//        }
//        br.close();
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

//        String listString="";

//        BufferedReader br = null;
//        StringBuilder sb = new StringBuilder("");
//        try
//        {
//            br = request.getReader();
//            String str;
//            while ((str = br.readLine()) != null)
//            {
//                sb.append(str);
//            }
//            br.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            if (null != br)
//            {
//                try
//                {
//                    br.close();
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
        System.out.println(listString);
//        System.out.println(" ......");
//        BufferedReader br = request.getReader();
//        String str = "";
//        StringBuilder listString= new StringBuilder("");
//        while ((str = br.readLine()) != null) {
//            listString.append(str);
//        }
        System.out.println(listString);
        //listString=sb.toString();

        //现在listString就是转化成string的json数据，需要转成json提取出来需要的。

        //System.out.println(listString);
        String test="{\"uname\":\"lily\"}";
        System.out.println(test);
        JSONObject json_test1 = JSON.parseObject(test);
        System.out.println("jsontest1 " + json_test1.getString("uname"));
        JSONObject json_test = (JSONObject)JSONObject.parse (listString);
        //JSONObject jsont = JSON.parseObject(listString);
        //System.out.println("uname " + jsont.getString("uname"));
        if(json_test==null)
        {
            System.out.println("nullll");
        }
//        assert json_test != null;
        //String uname=json_test.toJSONString();
        String uname1 = JSON.toJSONString(json_test, SerializerFeature.BrowserCompatible);
        System.out.println("uname " + uname1);
        String val="";
        String way="";
        if(json_test!=null)
        {
            way=json_test.get("way").toString();
            System.out.println("get " + way);
        }
        else{
            System.out.println("else null");
        }
        String tmpstr=" ";
        //JsonObject json= Json.createObjectBuilder().add();
        //String uname1=json_test.toJSONString();
//        assert json_test != null;
        //String uname1=json_test.getString("uname");
        //System.out.println("uname1 " + json_test.getString("uname"));
        //String tmpstr=request.getParameter("body");

        HttpServletResponse response = ServletActionContext.getResponse();
        response.reset();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");
//        PrintWriter out=response.getWriter();
//        out.write(String.valueOf(list));
//        out.flush();
//        out.close();不能够这样写，应该返回的是json
        Iterator<Book> it = list.iterator();
        JSONArray  itemarry = new JSONArray() ;
        //ArrayList<JSONArray> booksJson = new ArrayList<JSONArray>();
//        while (it.hasNext()) {
            //Book book = (Book) it.next();
        Book book=bookService.findbook(1);

        List<Book> BookList=bookService.findall();

            //ArrayList<String> arrayList = new ArrayList<String>();
//            if(book.getId().toString().equals(val)) {
//                arrayList.add(book.getDetail());
//                booksJson.add((JSONArray) JSONArray.toJSON(arrayList));
//            }
        for(Book book1:BookList)
        {
            String v1=book1.getName();
            Long id=book1.getId();
            String price=book1.getSale();
            String detail=book1.getDetail();
            String author=book1.getAuthor();

            String icon=book1.getIcon().getIconBase64();

            JSONObject item=new JSONObject();
            //var imgData = v2.replace(/[\r\n]/g, '') // 将回车换行换为空字符''
            item.put("id",id);
            item.put("name",v1);
            item.put("image",icon);
            item.put("price",price);
            item.put("detail",detail);
            item.put("author",author);
            itemarry.add(item);
        }
//            String v1=book.getName();
//            Long id=book.getId();
//            String price=book.getSale();
//            String detail=book.getDetail();
//            String author=book.getAuthor();
//
//            String icon=book.getIcon().getIconBase64();
//
//            JSONObject item=new JSONObject();
//            //var imgData = v2.replace(/[\r\n]/g, '') // 将回车换行换为空字符''
//            item.put("id",id);
//            item.put("name",v1);
//            item.put("image",icon);
//            item.put("price",price);
//            item.put("detail",detail);
//            item.put("author",author);
//            itemarry.add(item);
            //System.out.println("Book " + tmpstr + " " + book.getId());
        //}
        String booksString = JSON.toJSONString(itemarry, SerializerFeature.BrowserCompatible);
        //String booksString = booksJson.toJSONString();
        System.out.println(booksString);
        PrintWriter out = response.getWriter();
        out.println(booksString);
        out.flush();
        out.close();
//        while (it.hasNext()) {
//            Book book = (Book) it.next();
//            System.out.println("Book " + tmpstr + " " + book.getId());
//        }
        System.out.println("JSON Action End.");

        return "jsonlist";
    }


    public List<Book> getList() {
        return list;
    }
}
