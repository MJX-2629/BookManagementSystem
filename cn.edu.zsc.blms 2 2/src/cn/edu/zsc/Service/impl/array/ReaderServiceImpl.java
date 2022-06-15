package cn.edu.zsc.Service.impl.array;

import cn.edu.zsc.blms.App;
import cn.edu.zsc.blms.exception.EntityNotExistException;
import cn.edu.zsc.domain.BaseEntity;
import cn.edu.zsc.domain.Reader;
import cn.edu.zsc.blms.service.ReaderService;

public class ReaderServiceImpl extends BaseServiceImpl<BaseEntity> implements ReaderService {
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
        if (idCounter >= DATA.length) {

            System.out.println("读者已满，不能再添加");
            return;
        }
        System.out.println("请输入姓名");
        String name = App.input.nextLine();

        System.out.println("请输入电话");
        String phone = App.input.nextLine();

        System.out.println("请输入居住地：");
        String address = App.input.nextLine();

        Reader reader = new Reader(idCounter, name, phone, address);
        DATA[idCounter] = reader;
        System.out.println("成功增加1位读者");
        idCounter++;
    }


    public void disable() {
        System.out.println("请输入要禁用的读者TD：");
        Reader reader = null;
        try {
            reader = (Reader) getOne();
        } catch (EntityNotExistException e) {
            System.out.println(e.getMessage());
        }
        if (reader != null) {
            reader.disabled = true;
            System.out.println("该用户已被禁用！");
        }
    }

    public void update()  {
        System.out.println("请输入要编辑的读者ID：");
        Reader reader = null;
        try{
            reader=(Reader) getOne();
        }catch (EntityNotExistException e){
            System.out.println(e.getMessage());
        }

        if (reader!=null) {
            System.out.println("请输入姓名，直接按回车表示不修改：");
            String name = App.input.nextLine();
            reader.name="".equals(name)?reader.name:name;

            System.out.println("请输入电话，直接按回车表示不修改：");
            String phone = App.input.nextLine();

            reader.phone="".equals(phone)?reader.phone:phone;

            System.out.println("请输入居住地，直接按回车表示不修改：");
            String address = App.input.nextLine();
            reader.address="".equals(address)?reader.address:address;

            System.out.println("该用户的信息已被修改");
        }
    }

    public void query() {
        System.out.println("请输入要查询的读者的姓名：");
        String name = App.input.nextLine();
        for (int i = 0; i < idCounter; i++) {
            Reader reader = (Reader) DATA[i];
            if (reader.name != null && reader.name.contains(name)) {
                System.out.println(reader);
            }
        }

    }
}
