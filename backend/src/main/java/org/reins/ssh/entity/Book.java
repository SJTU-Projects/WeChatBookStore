package org.reins.ssh.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="books")
public class Book implements Serializable {
    private  Long id;
    private  String name;
    private  String author;
    private  String sale;
    private  String detail;
    private  String language;
//    private  String publishage;
    private  String base64;//这个要注意，不知道一个string存不存的下来

    //private List<Slot> slot;

//

    public Book(){};

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy ="increment")
    @Column(name = "id")
    public Long getId(){ return id;}
    private  void setId(Long id){this.id=id;}

    @Basic
    @Column(name = "name")
    public String getName(){return name;}
    public  void setName(String name){this.name=name;}

    @Basic
    @Column(name = "author")
    public String getAuthor(){return author;}
    public  void setAuthor(String author){this.author=author;}

    @Basic
    @Column(name = "sale")
    public String getSale(){return sale;}
    public  void setSale(String sale){this.sale=sale;}

    @Basic
    @Column(name = "detail")
    public String getDetail(){return detail;}
    public  void setDetail(String detail){this.detail=detail;}

    @Basic
    @Column(name = "language")
    public String getLanguage(){return language;}
    public  void setLanguage(String language){this.language=language;}
//

//    public String getPublishage(){return publishage;}
//    public  void setPublishage(String publishage){this.publishage=publishage;}

    @Basic
    @Column(name = "base64")
    public String getBase64(){return base64;}
    public  void setBase64(String base64){this.base64=base64;}

//    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
//    public List<Slot> getSlot() {
//        return slot;
//    }
//    public void setSlot(List<Slot> slot) {
//        this.slot = slot;
//    }
    private Bookicon icon;
    @Transient
    public Bookicon getIcon(){
    return icon;
}

    public void setIcon(Bookicon icon) {
        this.icon = icon;
    }


}
