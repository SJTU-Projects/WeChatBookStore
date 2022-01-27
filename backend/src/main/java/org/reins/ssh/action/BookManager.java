package org.reins.ssh.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.fastinfoset.util.StringArray;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.reins.ssh.entity.Book;
import org.reins.ssh.entity.Order;
import org.reins.ssh.entity.Slot;
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

//@CrossOrigin(maxAge =36000)
@CrossOrigin
@Controller
@ParentPackage("json-default")
public class BookManager extends ActionSupport {
    @Autowired
    private BookService bookService;

    private List<Book> list;

    @Action(value = "managebook", results = {
//            @Result(type="json", name="jsonlist", params={"root", "list"}),
            @Result(type="json", name="getbook" , params={"root", "list"}),
//            @Result(type="redirect", name="jsonlist", location = "/index.jsp"),
            @Result(name = "error", location = "/error.html")
    })
    public String execute() throws Exception {
        list = bookService.queryAll();
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
        JSONObject json_test = (JSONObject)JSONObject.parse (listString);
        String way="";
        if(json_test!=null)
        {
            way=json_test.get("way").toString();
            System.out.println("way: " + way);
        }
        else{
            System.out.println("else null");
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.reset();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");
        Iterator<Book> it = list.iterator();
        ArrayList<JSONArray> booksJson = new ArrayList<JSONArray>();
        System.out.println("beforeway " );
        if(way.equals("test1"))
        {
            Integer id=json_test.getInteger("id");
            System.out.println("test id:"+ id);
            Book book=bookService.findbook(id);
            String name=book.getName();
            String icon=book.getIcon().getIconBase64();
            System.out.println("icon: " + icon);
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(name);
            ArrayList<String> arrayList1 = new ArrayList<String>();
            arrayList1.add(icon);
            booksJson.add((JSONArray) JSONArray.toJSON(arrayList));
            booksJson.add((JSONArray) JSONArray.toJSON(arrayList1));
            String booksString = JSON.toJSONString(booksJson, SerializerFeature.BrowserCompatible);
            //System.out.println("booksString: " + booksString);
            PrintWriter out = response.getWriter();
            out.println(booksString);
            out.flush();
            out.close();

            return "getbook";
        }
        if(way.equals("val"))//下一页
        {
            //get the last 4 books
            while (it.hasNext()) {
                Book book = (Book) it.next();
                ArrayList<String> arrayList = new ArrayList<String>();
                if(book.getId()>4) {
                    arrayList.add(book.getName());
                    booksJson.add((JSONArray) JSONArray.toJSON(arrayList));
                }
                //System.out.println("Book " + tmpstr + " " + book.getId());
            }
            String booksString = JSON.toJSONString(booksJson, SerializerFeature.BrowserCompatible);
            System.out.println("booksString: " + booksString);
            PrintWriter out = response.getWriter();
            out.println(booksString);
            out.flush();
            out.close();
            return "getbook";
        }
        if(way.equals("getdetail"))
        {
            String bookname="";
            bookname=json_test.get("bookname").toString();
            System.out.println("bookname: " + bookname);
            while (it.hasNext()) {
                Book book = (Book) it.next();
                ArrayList<String> arrayList = new ArrayList<String>();
                if(book.getName().equals(bookname)) {
                    System.out.println("equals: " + bookname);
                    arrayList.add(book.getAuthor());
                    booksJson.add((JSONArray) JSONArray.toJSON(arrayList));

                    ArrayList<String> arrayList1 = new ArrayList<String>();
                    arrayList1.add(book.getSale());
                    booksJson.add((JSONArray) JSONArray.toJSON(arrayList1));

                    ArrayList<String> arrayList2 = new ArrayList<String>();
                    arrayList2.add(book.getDetail());
                    booksJson.add((JSONArray) JSONArray.toJSON(arrayList2));
                    //break;
                }
                //System.out.println("Book " + tmpstr + " " + book.getId());
            }
            String booksString = JSON.toJSONString(booksJson, SerializerFeature.BrowserCompatible);
            System.out.println("booksString: " + booksString);
            PrintWriter out = response.getWriter();
            out.println(booksString);
            out.flush();
            out.close();
            return "getbook";
        }
        if(way.equals("test"))
        {
            Book book=new Book();
            book.setName("test");
            book.setAuthor("1");
            bookService.addbook(book);

            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("success");
            booksJson.add((JSONArray) JSONArray.toJSON(arrayList));
            String booksString = JSON.toJSONString(booksJson, SerializerFeature.BrowserCompatible);
            System.out.println("booksString: " + booksString);
            PrintWriter out = response.getWriter();
            out.println(booksString);
            out.flush();
            out.close();

            return "getbook";
        }
        if(way.equals("slot"))
        {
            String bookname= json_test.get("bookname").toString();
            long userid=Long.parseLong(json_test.get("userid").toString());
            long number=Long.parseLong(json_test.get("num").toString());
//            String bookname="";
//            String sale="";
            long bookid=1;
            while (it.hasNext()) {
                Book book = (Book) it.next();
                ArrayList<String> arrayList = new ArrayList<String>();
                if(book.getName().equals(bookname)) {
                    bookid=book.getId();
                }
                //System.out.println("Book " + tmpstr + " " + book.getId());
            }
            Slot slot=new Slot();
            slot.setBookid(bookid);
            slot.setNumber(number);
            slot.setUserid(userid);
//            List<String> namelist = new ArrayList<String>();
//            namelist.add(bookname);
//            slot.setName(namelist);
//            slot.setSale(sale);
            //用这个slot来写入 bookservice.slot(slot)
            bookService.changeslot(slot);
                ArrayList<String> arrayList = new ArrayList<String>();
                    arrayList.add("success");
                    booksJson.add((JSONArray) JSONArray.toJSON(arrayList));
            String booksString = JSON.toJSONString(booksJson, SerializerFeature.BrowserCompatible);
            System.out.println("booksString: " + booksString);
            PrintWriter out = response.getWriter();
            out.println(booksString);
            out.flush();
            out.close();

            return "getbook";
        }
        if(way.equals("cart"))
        {
            long userid=Long.parseLong(json_test.get("userid").toString());
            String sale="";
            //需要从slot里面去找
            List<Slot> slist=bookService.findcart();//allslot
            Iterator<Slot> sit = slist.iterator();
            while (sit.hasNext()) {
                Slot slot = (Slot) sit.next();
                ArrayList<String> arrayList = new ArrayList<String>();
                if(slot.getUserid().equals(userid))
                {
                    Iterator<Book> tit = list.iterator();
                    Long bid=slot.getBookid();
                    arrayList.add(slot.getBookid().toString());
                    //找到bookname
                    while(tit.hasNext())
                    {
                        Book book=(Book) tit.next();
                        //int b=bid.intValue();
                        if(book.getId().equals(bid))
                        {
                            arrayList.add(book.getName());
                            sale=book.getSale();
                            System.out.println("sale"+ sale);
                            arrayList.add(book.getSale());
                            arrayList.add(slot.getNumber().toString());
                            System.out.println(2*4);
                            long price= Long.valueOf(sale).longValue();;
                            System.out.println(price);
                            long total=slot.getNumber()* price;
                            String all=String.valueOf(total);
                            arrayList.add(all);
                        }
                    }
                }
                booksJson.add((JSONArray) JSONArray.toJSON(arrayList));
            }
            String booksString = JSON.toJSONString(booksJson, SerializerFeature.BrowserCompatible);
            System.out.println("booksString: " + booksString);
            PrintWriter out = response.getWriter();
            out.println(booksString);
            out.flush();
            out.close();
            return "getbook";
        }
        if(way.equals("submit"))
        {
            int userid=Integer.parseInt(json_test.get("userid").toString());
            //ArrayList<ArrayList<String>> datalist= (ArrayList<ArrayList<String>>) json_test.get("list");
            System.out.println(json_test.get("list"));
            String onelist=json_test.get("list").toString();
            String[][] arr = JSON.parseObject(onelist, String[][].class);
            System.out.println(arr[0][0]);
            //ArrayList<String> onelist=json_test.get("list").toString();
            //JSONArray arr = JSONArray.parseArray (json_test.get("list").toString());
            int line=arr.length;//一共有多少行
            for(int i=0;i<line;i++)
            {
                Order order=new Order();
                order.setUserid(userid);
                order.setBookid(Integer.parseInt(arr[i][0]));
                order.setBookname(arr[i][1]);
                order.setPrice(arr[i][2]);
                order.setNum(Integer.parseInt(arr[i][3]));
                order.setTotal(arr[i][4]);

                bookService.submit(order);//loop
            }
            //还需要删除掉slots里面的一些信息。
            bookService.reclear(userid);
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("success");
            booksJson.add((JSONArray) JSONArray.toJSON(arrayList));
            String booksString = JSON.toJSONString(booksJson, SerializerFeature.BrowserCompatible);
            System.out.println("booksString: " + booksString);
            PrintWriter out = response.getWriter();
            out.println(booksString);
            out.flush();
            out.close();

            return "getbook";
        }
        if(way.equals("getbooks"))
        {
            JSONArray  itemarry = new JSONArray() ;
            List<Book> BookList=bookService.findall();
            for(Book book1:BookList)
            {
                String v1=book1.getName();
                Long id=book1.getId();
                String price=book1.getSale();
                String detail=book1.getDetail();
                String author=book1.getAuthor();
                String icon=book1.getIcon().getIconBase64();
                JSONObject item=new JSONObject();
                item.put("id",id);
                item.put("name",v1);
                item.put("image",icon);
                item.put("price",price);
                item.put("detail",detail);
                item.put("author",author);
                itemarry.add(item);
            }
            String booksString = JSON.toJSONString(itemarry, SerializerFeature.BrowserCompatible);
            System.out.println(booksString);
            PrintWriter out = response.getWriter();
            out.println(booksString);
            out.flush();
            out.close();
        }
        System.out.println("beforereturn " );
        return "getbook";
    }
    public List<Book> getList() {
        return list;
    }
}
