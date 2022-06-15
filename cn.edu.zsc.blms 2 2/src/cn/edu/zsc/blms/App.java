package cn.edu.zsc.blms;

import java.text.ParseException;
import java.util.Scanner;

import cn.edu.zsc.Service.BorrowingService;
import cn.edu.zsc.Service.impl.collection.BorrowingServiceImpl;
import cn.edu.zsc.blms.exception.EntityNotExistException;
import cn.edu.zsc.blms.service.ReaderService;
import cn.edu.zsc.Service.BookService;
import cn.edu.zsc.Service.impl.collection.BookServiceImpl;
import cn.edu.zsc.Service.impl.collection.ReaderServiceImpl;
 
/**
 * 应用程序主类
 */
public class App {
    /**
     * 输入扫描器
     */
    public static Scanner input;

    private final   BookService bookService;

    public final ReaderService readerService;

    public final BorrowingService borrowingService;

    public App(ReaderService readerService,BookService bookService,BorrowingService borrowingService) {

        this.readerService = readerService;
        this.bookService=bookService;
        this.borrowingService=borrowingService;
    }

    public static void main(String[] args) throws EntityNotExistException, ParseException {
        App app = new App(ReaderServiceImpl.getSingleton(),BookServiceImpl.getSingleton(),
                BorrowingServiceImpl.getSingleton());
        app.run();
    }

    /**
     * 输出系统管理菜单
     */
    public void systemMenu() {
        System.out.println("---------------------");
        System.out.println("    图书管理系统     ");
        System.out.println("---------------------");
        System.out.println("|   0...退出系统	|");
        System.out.println("|   1...读者管理	|");
        System.out.println("|   2...图书管理	|");
        System.out.println("|   3...借还图书	|");
        System.out.println("---------------------");
        System.out.print("请输入选项：");
    }

    /**
     * 输出读者管理菜单
     */
    public void readerMenu() {
        System.out.println("---------------------");
        System.out.println("    读者管理     ");
        System.out.println("---------------------");
        System.out.println("|   0...返回上级	|");
        System.out.println("|   1...添加读者	|");
        System.out.println("|   2...禁用读者	|");
        System.out.println("|   3...编辑读者	|");
        System.out.println("|   4...查询读者	|");
        System.out.println("---------------------");
        System.out.print("请输入选项：");
    }

    /**
     * 输出图书管理菜单
     */
    public void bookMenu() {
        System.out.println("---------------------");
        System.out.println("    图书管理     ");
        System.out.println("---------------------");
        System.out.println("|   0...返回上级	|");
        System.out.println("|   1...添加图书	|");
        System.out.println("|   2...废弃图书	|");
        System.out.println("|   3...编辑图书	|");
        System.out.println("|   4...查询图书	|");
        System.out.println("---------------------");
        System.out.print("请输入选项：");
    }

    /**
     * 输出借还图书菜单
     */
    public void borrowingMenu() {
        System.out.println("---------------------");
        System.out.println("    借还图书     ");
        System.out.println("---------------------");
        System.out.println("|   0...返回上级	|");
        System.out.println("|   1...借阅图书	|");
        System.out.println("|   2...归还图书	|");
        System.out.println("|   3...查询记录	|");
        System.out.println("---------------------");
        System.out.print("请输入选项：");
    }

    /**
     * 程序启动
     */
    public void run() throws EntityNotExistException, ParseException {
        input = new Scanner(System.in);
        String systemMenuChoice;
        do {
            systemMenu();
            systemMenuChoice = input.nextLine();
            switch (systemMenuChoice) {
                case "0":
                    System.out.println("成功退出系统，欢迎再次使用！");
                    break;
                case "1":
                    String readerMenuChoice;
                    do {
                        readerMenu();
                        readerMenuChoice = input.nextLine();
                        switch (readerMenuChoice) {
                            case "0":
                                break;
                            case "1":
                                readerService.create();
                                break;
                            case "2":
                                readerService.disable();
                                break;
                            case "3":
                                readerService.update();
                                break;
                            case "4":
                                readerService.query();
                                break;
                            default:
                                System.out.println("输入非法，请输入正确的菜单编号！");
                        }
                    } while (!("0").equals(readerMenuChoice));
                    break;
                case "2":
                    String bookMenuChoice;
                    do {
                        bookMenu();
                        bookMenuChoice = input.nextLine();
                        switch (bookMenuChoice) {
                            case "0":
                                break;
                            case "1":
                                bookService.create();
                                break;
                            case "2":
                                bookService.disable();
                                break;
                            case "3":
                                bookService.update();
                                break;
                            case "4":
                                bookService.query();
                                break;
                            default:
                                System.out.println("输入非法，请输入正确的菜单编号！");
                        }
                    } while (!"0".equals(bookMenuChoice));
                    break;
                case "3":
                    String borrowingMenuChoice;
                    do {
                        borrowingMenu();
                        borrowingMenuChoice = input.nextLine();
                        switch (borrowingMenuChoice) {
                            case "0":
                                break;
                            case "1":
                                borrowingService.borrow();
                                break;
                            case "2":
                                borrowingService.giveBack();
                                break;
                            case "3":
                                borrowingService.query();
                                break;
                            default:
                                System.out.println("输入非法，请输入正确的菜单编号！");
                        }
                    } while (!"0".equals(borrowingMenuChoice));
                    break;
                default:
                    System.out.println("输入非法，请输入正确的菜单编号！");
            }
        } while (!"0".equals(systemMenuChoice));
        input.close();
    }
}
