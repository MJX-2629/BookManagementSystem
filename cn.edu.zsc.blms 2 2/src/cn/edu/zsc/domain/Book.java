package cn.edu.zsc.domain;

import cn.edu.zsc.blms.util.DateUtil;

import java.util.Date;


public class Book extends BaseEntity {
    public String name;
    public String isbn;  //书号
    public String category;  //类目
    public String author;
    public String press;  //出版社
    public Date publishDate;
    public Double price;
    public boolean disable;

    public Status status=Status.可借出;

    public enum Status{
        可借出,
        已借出
    }

    public Book() {
    }

    public Book(Integer id, String name, String isbn, String category, String author, String press, Date publishDate, Double price) {
        super(id);
        this.name = name;
        this.isbn = isbn;
        this.category = category;
        this.author = author;
        this.press = press;
        this.publishDate = publishDate;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID：" + id + "\t\t" + "书名：" + name + "\t\t" + "书号：" + isbn + "\t\t" + "类目："
                + category + "\t\t" + "作者：" + author + "\t\t" + "出版社：" + press +"\t\t"+"日期："+ DateUtil.format(publishDate)
                + "\t\t" + "单价：" + price + "\t\t" + "废弃：" + (disable ? "是" : "否");
    }

}
