package cn.edu.zsc.Service.impl.collection;

import cn.edu.zsc.Service.BookService;
import cn.edu.zsc.Service.BorrowingService;
import cn.edu.zsc.blms.service.ReaderService;
import cn.edu.zsc.blms.exception.EntityNotExistException;
import cn.edu.zsc.domain.Book;
import cn.edu.zsc.domain.Borrowing;
import cn.edu.zsc.domain.Reader;

import java.util.*;

public class BorrowingServiceImpl extends BaseServiceImpl<Borrowing> implements BorrowingService {

    private final BookServiceImpl bookService;
    private final ReaderServiceImpl readerService;

    public BorrowingServiceImpl(BookService bookService, ReaderService readerService) {
        this.bookService = (BookServiceImpl) bookService;
        this.readerService = (ReaderServiceImpl) readerService;
    }

    private static BorrowingService singleton;

    public static BorrowingService getSingleton() {
        if (singleton == null) {
            singleton = new BorrowingServiceImpl(BookServiceImpl.getSingleton(),
                    ReaderServiceImpl.getSingleton());
        }
        return singleton;
    }

    private final Map<Integer, Borrowing> BOOK_LENDING = new HashMap<>();


    @Override
    public void borrow() {
        try {
            System.out.println("请输入图书ID：");
            Book book = bookService.getOne();
            if (Book.Status.可借出 != book.status) {
                System.out.println("该图书已借出！");
                return;
            }
            if (book.disable) {
                System.out.println("该图书已被废弃！");
                return;
            }
            System.out.println("请输入读者ID：");
            Reader reader = readerService.getOne();
            if (reader.disabled) {
                System.out.println("该读者已被禁用！");
                return;
            }
            Borrowing borrowing = new Borrowing(idCounter, reader, book, new Date());
            DATA.put(borrowing.id, borrowing);
            BOOK_LENDING.put(book.id, borrowing);
            book.status = Book.Status.已借出;
            System.out.println("图书成功借出！");
            idCounter++;
        } catch (EntityNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void giveBack() {
        try {
            System.out.println("请输入图书ID：");
            Book book = bookService.getOne();
            Borrowing borrowing = BOOK_LENDING.get(book.id);
            if (borrowing == null) {
                System.out.println("该图书未被借出！");
                return;
            }
            borrowing.returnTime = new Date();
            book.status = Book.Status.可借出;
            BOOK_LENDING.remove(book.id);
            System.out.println("图书归还成功！欢迎您再次阅读~");
        } catch (EntityNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void query() {
        System.out.println("按出借顺序显示当前正在出借的图书：");
        Set<Borrowing> borrowings = new TreeSet<>(BOOK_LENDING.values());
        for (Borrowing borrowing : borrowings) {
            System.out.println(borrowing);
        }
    }
}
