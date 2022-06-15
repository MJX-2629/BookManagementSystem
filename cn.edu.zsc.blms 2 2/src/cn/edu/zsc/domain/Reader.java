package cn.edu.zsc.domain;

public class Reader extends BaseEntity{
     public String name;
     public String phone;
     public String address;
     public boolean disabled;

     public Reader(){}
     public Reader(int id,String name,String phone,String address){
         super(id);
         this.name=name;
         this.phone=phone;
         this.address=address;
     }

    @Override
    public String toString() {
        return "ID：" + id + "\t\t" + "姓名：" + name + "\t\t" + "电话：" + phone + "\t\t\t" + "居住地："
                + address + "\t\t" + "禁用：" + (disabled ? "是" : "否");
    }
}
