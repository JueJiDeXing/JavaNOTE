package MyNote.J_API;

public class J_Object {
    public static void main(String[] args) throws CloneNotSupportedException {
        //空参构造
        Object object=new Object();
        //成员方法------------------------------------
        class Person{
            int age;
        }

        //String .toString()返回字符串的表现形式
        Person person=new Person();
        System.out.println(person.toString());//都为包名加类名加地址值,J_API.J_OBJECT$1Person@3b07d329
        //getClass().getName()+"@"+Integer.toHexString(hashCode())
        //输出语句底层逻辑
        System.out.println(person);
        //打印对象时,底层调用toString,然后打印在控制台,打印完换行
        //想要使toString返回属性,可以在类里重写toString方法
        //yoString{return name+","+age}//返回该name与age属性值,以逗号拼接


        //boolean .equals(Object obj)比较两个对象
        Person person2=new Person();
        System.out.println(person.equals(person2));//false,用==号比较对象的地址值
        //要比较属性值,可以重写equals方法:
        /*equals(Object obj){
            if(this==obj){return true;}//同一个对象,返回true
            if(obj==null||this.getClass()!=obj.getClass()){return false;}//比较是否为同一个类
            Person p=(Person) obj;//类型强转
            return this.name==p.name && this.age==p.age;
            }
         */

        //equals例
        String s="abc";
        StringBuilder sb=new StringBuilder("abc");
        System.out.println(s.equals(sb));//String 重写了equals方法,sb不是String类型,输出false
        System.out.println(sb.equals(s));//StringBuilder未重写,比较地址值,不同返回false



        // #protected# Object clone()克隆对象
        class Animals implements Cloneable{//Cloneable接口是一个标记性接口,无成员方法
            protected Object clone() throws CloneNotSupportedException{
                return super.clone();//浅克隆
                //实现深克隆,可以在方法里创建一个新数组,克隆后赋值给新对象,再返回新对象
            }
        }
        Animals animals1=new Animals();
        Animals animals2=(Animals)animals1.clone();
        System.out.println(animals2);
        //浅克隆:复制对象属性时,引用数据类型拷贝的是地址值,如String,int[]等
        //深克隆:复制对象属性时,引用数据类型重新创建一份数据 //对于String类型,存储在串池当中,依然拷贝地址值

        //第三方库 克隆gson.jar
        //创建lib文件夹,把第三方库放在lib文件夹下,然后添加为库,即可使用该库
        // Gson gson = new Gson();
        // String s = gson.toJson(u1);//s以键值对形式存储对象属性
        // User user = gson.fromJson(s,User.class);//字符串重新变回对象
    }
}
