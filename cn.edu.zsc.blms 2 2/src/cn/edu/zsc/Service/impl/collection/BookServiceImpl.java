package cn.edu.zsc.Service.impl.collection;

import cn.edu.zsc.Service.BookService;
import cn.edu.zsc.blms.App;
import cn.edu.zsc.blms.exception.EntityNotExistException;
import cn.edu.zsc.blms.util.DateUtil;
import cn.edu.zsc.domain.Book;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {
    private static BookService singleton;

    private BookServiceImpl() {

    }

    public void setIdCounter(int idCounter) {
        this.idCounter = idCounter;
    }

    public static BookService getSingleton() {
        if (singleton == null) {
            singleton = new BookServiceImpl();
        }
        return singleton;
    }

    public void create() {

        System.out.println("请输入书名：");
        String name = App.input.nextLine();

        System.out.println("请输入书号：");
        String isbn = App.input.nextLine();

        System.out.println("请输入类目：");
        String category = App.input.nextLine();

        System.out.println("请输入作者：");
        String author = App.input.nextLine();

        System.out.println("请输入出版社：");
        String press = App.input.nextLine();

        System.out.println("请输入出版日期，格式为yyyy-MM-dd：");
        Date publishDate = null;
        while (publishDate == null) {
            String strDate = App.input.nextLine();
            try {
                publishDate = DateUtil.parse(strDate);
            } catch (ParseException e) {
                System.out.println("输入的格式有误，请重新输入：");
            }
        }

        System.out.println("请输入单价：");
        Double price = null;
        while (price == null) {
            String strPrice = App.input.nextLine();
            try {
                price = Double.parseDouble(strPrice);
            } catch (NumberFormatException e) {
                System.out.println("输入的格式单价格式有误，请重新输入：");
            }
        }

        Book book = new Book(idCounter, name, isbn, category, author, press, publishDate, price);
        DATA.put(book.id, book);
        System.out.println("成功添加1本图书！");
        idCounter++;
    }

    public void disable() {
        System.out.println("请输入要废弃的图书Id：");
        Book book = null;
        try {
            book = getOne();
        } catch (EntityNotExistException e) {
            System.out.println(e.getMessage());
        }
        if (book != null) {
            book.disable = true;
            System.out.println("该图书已被禁用！");
        }
    }

    public void update() {
        System.out.println("请输入要编辑的图书Id：");
        Book book = null;
        try {
            book = getOne();
        } catch (EntityNotExistException e) {
            System.out.println(e.getMessage());
        }

        if (book != null) {
            System.out.println("请输入新的书名（直接回车表示不修改）：");
            String name = App.input.nextLine();
            book.name = "".equals(name) ? book.name : name;

            System.out.println("请输入新的书号（直接回车表示不修改）：");
            String isbn = App.input.nextLine();
            book.isbn = "".equals(isbn) ? book.isbn : isbn;

            System.out.println("请输入新的类目（直接回车表示不修改）：");
            String category = App.input.nextLine();
            book.category = "".equals(category) ? book.category : category;

            System.out.println("请输入新的作者（直接回车表示不修改）：");
            String author = App.input.next();
            book.author = "".equals(author) ? book.author : author;

            System.out.println("请输入新的出版社（直接回车表示不修改）：");
            String press = App.input.nextLine();
            book.press = "".equals(press) ? book.press : press;

            System.out.println("请输入出版日期，格式为yyyy-MM-dd，（直接回车表示不修改）");
            Date publishDate = null;
            while (publishDate == null) {
                String strDate = App.input.nextLine();
                if ("".equals(strDate)) {
                    publishDate = (Date) book.publishDate;
                    break;
                }
                try {
                    publishDate = DateUtil.parse(strDate);
                } catch (ParseException e) {
                    System.out.println("输入的日期格式有误，请重新输入：");
                }
            }
            book.publishDate = publishDate;

            System.out.println("请输入新的单价（直接回车表示不修改）：");
            Double price = null;
            while (price == null) {
                String strDate = App.input.nextLine();
                if ("".equals(strDate)) {
                    price = book.price;
                    break;
                }
                try {
                    price = Double.parseDouble(strDate);
                } catch (NumberFormatException e) {
                    System.out.println("输入的单价格式有误，请重新输入：");
                }
            }
            book.price = price;

            System.out.println("图书信息修改成功！");
        }
    }

    public void query() {
        System.out.println("请输入要查询的图书名称：");
        String name = App.input.nextLine();
        Set<Integer> ids = DATA.keySet();
        for (Integer id : ids) {
            Book book=DATA.get(id);
            if (book.name != null && book.name.contains(name)) {
                System.out.println(book);
            }
        }
    }
}

