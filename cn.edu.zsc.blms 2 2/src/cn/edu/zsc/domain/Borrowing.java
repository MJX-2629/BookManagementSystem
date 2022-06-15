package cn.edu.zsc.domain;

import cn.edu.zsc.blms.util.DateUtil;

import java.util.Date;

public class Borrowing extends BaseEntity {
    public Reader reader;

    public Book book;

    public Date borrowTime;

    public Date returnTime;

    public Borrowing() {

    }

    public Borrowing(Integer id, Reader reader, Book book, Date borrowTime) {
        super(id);
        this.reader = reader;
        this.book = book;
        this.borrowTime = borrowTime;
    }

    @Override
    public String toString() {
        return "ID" + id + "\t\t" + "读者姓名：" + reader.name + "\t\t" + "图书名称：" + book.name + "\t\t"
                + "借出时间：" + DateUtil.formatTime(borrowTime) + "\t\t"
                + "归还时间：" + (returnTime == null ? "" : DateUtil.formatTime(returnTime));
    }
}
