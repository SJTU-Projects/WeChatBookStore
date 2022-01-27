package org.reins.ssh.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="orders")
public class Order implements Serializable {
    private  int id;
    private  int userid;
    private  int bookid;
    private  String bookname;
    private  String price;
    private  int num;
    private  String total;
    public Order(){};
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy ="increment")
    public int getId(){ return id;}
    private  void setId(int id){this.id=id;}

    public int getUserid(){return userid;}
    public  void setUserid(int userid){this.userid=userid;}

    public int getBookid(){return bookid;}
    public  void setBookid(int bookid){this.bookid=bookid;}

    public String getBookname(){return bookname;}
    public  void setBookname(String bookname){this.bookname=bookname;}

    public String getPrice(){return price;}
    public  void setPrice(String price){this.price=price;}

    public int getNum(){ return num;}
    public void setNum(int num){this.num=num;}

    public String getTotal(){return total;}
    public  void setTotal(String total){this.total=total;}
}
