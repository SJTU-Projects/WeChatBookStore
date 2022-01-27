package org.reins.ssh.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="slots")
public class Slot implements Serializable {
    private  Long id;
    private  Long userid;
    private  Long bookid;
    private  Long number;
    //private  double totalprice;
//    private List<String> name =new ArrayList<>();;
//    private String sale = "";

    //private Book book;

    public Slot(){};



    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy ="increment")
    public Long getId(){ return id;}
    private  void setId(Long id){this.id=id;}


    public Long getUserid(){return userid;}
    public  void setUserid(Long userid){this.userid=userid;}

//    @Column(insertable=false,updatable=false)
    public Long getBookid(){return bookid;}
    public  void setBookid(Long bookid){this.bookid=bookid;}

    public Long getNumber(){return number;}
    public  void setNumber(Long number){this.number=number;}

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "bookid")
//    public Book getBook() {
//        return book;
//    }
//    public void setBook(Book book){ this.book=book;}


    //    public double getTotalprice(){return totalprice;}
//    public  void setTotalprice(double totalprice){this.totalprice=totalprice;}

//    @ElementCollection
//    @CollectionTable( name="books",
//            joinColumns = { @JoinColumn(name = "id", referencedColumnName = "bookid")})
//    @Column(name="name")
//    public List<String> getName() {
//        return name;
//    }
//    public void setName(List<String> name) {
//        this.name = name;
//    }
//
//    @CollectionTable( name="books",
//            joinColumns = { @JoinColumn(name = "id", referencedColumnName = "bookid")})
//    @Column(name="sale")
//    public String getSale() {
//        return sale;
//    }
//    public void setSale(String sale) {
//        this.sale = sale;
//    }
}
