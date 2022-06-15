package cn.edu.zsc.Service.impl.collection;

import cn.edu.zsc.blms.App;
import cn.edu.zsc.blms.exception.EntityNotExistException;
import cn.edu.zsc.blms.service.ReaderService;
import cn.edu.zsc.domain.Reader;

import java.util.Scanner;
import java.util.Set;

public class ReaderServiceImpl extends BaseServiceImpl<Reader> implements ReaderService {
    public static Scanner input;

    //单例
    private static ReaderService singleton;

    //构造私有方法
    private ReaderServiceImpl() {
    }

    /*
        获取单例
        @return ReaderService单例
     */
    public static ReaderService getSingleton() {
        if (singleton == null) {
            singleton = new ReaderServiceImpl();
        }
        return singleton;
    }

    public void create() {
        System.out.println("请输入姓名");
        String name = App.input.nextLine();

        System.out.println("请输入电话");
        String phone = App.input.nextLine();

        System.out.println("请输入居住地：");
        String address = App.input.nextLine();

        Reader reader = new Reader(idCounter, name, phone, address);
        DATA.put(reader.id, reader);
        System.out.println("成功增加1位读者");
        idCounter++;
    }


    public void disable() {
        System.out.println("请输入要禁用的读者TD：");
        Reader reader = null;
        try {
            reader = getOne();
        } catch (EntityNotExistException e) {
            System.out.println(e.getMessage());
        }
        if (reader != null) {
            reader.disabled = true;
            System.out.println("该用户已被禁用！");
        }
    }

    public void update() {
        System.out.println("请输入要编辑的读者ID：");
        Reader reader = null;
        try {
            reader = getOne();
        } catch (EntityNotExistException e) {
            System.out.println(e.getMessage());
        }
        if (reader != null) {
            System.out.println("请输入姓名，直接按回车表示不修改：");
            String name = App.input.nextLine();
            reader.name = "".equals(name) ? reader.name : name;

            System.out.println("请输入电话，直接按回车表示不修改：");
            String phone = App.input.nextLine();
            reader.phone = "".equals(phone) ? reader.phone : phone;

            System.out.println("请输入居住地，直接按回车表示不修改：");
            String address = App.input.nextLine();
            reader.address = "".equals(address) ? reader.address : address;

            System.out.println("该用户的信息已被修改");
        }
    }

    public void query() {
//        System.out.println("请输入要查询的读者的姓名：");
//        String name = App.input.nextLine();
//        Set<Integer> ids = DATA.keySet();
//        for (Integer id : ids) {
//            Reader reader = DATA.get(id);
//            if (reader.name != null && reader.name.contains(name)) {
//                System.out.println(reader);
//            }
//        }
        System.out.println("选择你要查询的方式：1.单个查询  2.查询全部  0.返回菜单");
        input = new Scanner(System.in);
        String queryChoice;
        do {
            queryChoice = input.nextLine();
            switch (queryChoice) {
                case "0":
                    break;
                case "1":
                    System.out.println("请输入要查询的读者的姓名：");
                    String name = App.input.nextLine();
                    Set<Integer> ids1 = DATA.keySet();
                    for (Integer id : ids1) {
                        Reader reader = DATA.get(id);
                        if (reader.name != null && reader.name.contains(name)) {
                            System.out.println(reader);
                        }
                    }
                    System.out.println("选择你要查询的方式：1.单个查询  2.查询全部  0.返回菜单");
                    break;
                case "2":
                    Set<Integer> ids2 = DATA.keySet();
                    for (Integer id : ids2) {
                        Reader reader = DATA.get(id);
                        System.out.println(reader);
                    }
                    System.out.println("选择你要查询的方式：1.单个查询  2.查询全部  0.返回菜单");
                    break;
            }
        } while (!"0".equals(queryChoice));
    }
}

