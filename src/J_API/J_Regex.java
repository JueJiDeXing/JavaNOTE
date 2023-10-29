package J_API;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class J_Regex {
    //正则表达式
    public static void main(String[] args) {

        /*匹配规则:
            字符类:(单字符)
            [abc]匹配字符a,b,或c
            [^abc]除abc外的所有字符
            [a-zA-Z]匹配52个英文字母
            [a-d[m-p]]匹配a到d,或者m到p
            [a-z && [def]]匹配a到z与def的交集def      (一个&符号会被认作匹配符,两个才表示交集的意思)
            [a-z && [^bcd]]匹配a到z与非bcd的交集[ae-z]   (|或符号,与&的使用相反)
            [a-z&&[^m-p]匹配a到z与非m到p的交集[a-lq-z]

            预定义字符:(单字符)
            . 除\n外的所有字符
            \d 数字:[0-9]     元字符,需要加转义才能表示   \\d
            \D 非数字:[^0-9]
            \s 空白字符:[\t\n\x0b\f\r]
            \S 非空白字符:[^\s]
            \w 英文,数字,下划线:[a-zA-Z_0-9]
            \W 非单词字符:[^\w]

            数量词:
            ?       匹配前面的字符至多一次
            ^       匹配0次或多次
            +       匹配一次或多次
            {n}     匹配正好n次
            {n,}    匹配至少n次
            {n,m}   匹配至少n次,但不超过m次


            ?       前面的数据
            =       后面要跟随的字符,但不获取       Java(?=8|11|17)  ->匹配后面带有版本号的Java,不匹配版本号
            :       后面跟随的字符,获取匹配        Java(?:8|11|17) ->匹配Java8,Java11,Java17
            !       取反      Java(?!8|11|17) ->匹配除了8 11 17三个版本的Java
            ()  分组
            |   或(写在方括号外面表示并集)
            (?i)    忽略后面字符的大小写,例:a((?i)bc)d  忽略bc的大小写


         */
        System.out.println("zz".matches("[^abc][^def]"));//true,一个[]匹配一个字符
        System.out.println("&".matches("[a-z&[^def]]"));//true,&匹配

        //例1:匹配QQ号
        String qq = "123456789";//[6,20]的QQ号,开头不能为0
        System.out.println(qq.matches("[1-9]\\d{5,19}"));//true
        //开头1到9 然后数字0到9匹配5到19个

        //例2:匹配座机号码
        //区号开头为0,区号有3到4位
        //号码不以0开头,总长度5到10位
        //区号与号码之间可能有-号连接
        //座机号码示例:020-2324242, 02122442, 027-42424, 0712-3242434
        String regex = "0\\d{2,3}-?[1-9]\\d{4,9}";
        //开头为0,接2或3位数字,接1-号(可能没有-号),接不为0的数字,接数字4到9位

        //例3:邮箱
        //3408470465@qq.com
        String regex2 = "\\w+ @ [\\w&&[^_]]+ (\\. [a-zA-Z]+)+";
        // 开头为任意长度的字符,数字或下划线,接1@,接网址名不含下划线的任意长度字符串,接多组网址后缀例如.ncu.edu.cn

        //any-rule插件
        String regex3 = "";//快捷键Alt+A

        //例4:匹配24小时
        String regex4 = "([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d";
        //[01]\\d | 2[0-3] 小时 以01开头接任意数字,或以2开头接0到3
        //[0-5]\d 分钟和秒  0到5开头接任意数字

        //Pattern------------------------------------------------------------
        String str = "Java是世界上最好的语言,Java有Java8,Java17等版本";
        //获取正则表达式对象->规则
        Pattern pattern = Pattern.compile("Java(\\d){0,2}");
        //获取文本匹配器对象->要匹配的数据源
        Matcher matcher = pattern.matcher(str);

        //boolean .find()
        // 没有返回false,有则返回true,并在底层记录子串的起始索引和结束索引+1
        boolean b = matcher.find();//在第二次调用时会从上一次读取的位置开始读取

        //String .group()
        //返回匹配到的子串
        String s1 = matcher.group();
        System.out.println(s1);
        //底层调用find记录索引,然后调用subString(startIndex,endIndex)进行截取后返回

        //----循环
        while (matcher.find()) {
            String s = matcher.group();
            System.out.println(s);//Java Java8 Java17
        }

        //贪婪爬取,非贪婪爬取---------------------------------------------
        //+     * 贪婪匹配(默认)
        //+?    *?非贪婪匹配
        //例:ab+ -> 匹配abbbbbbbbbb
        //ab+?  -> 匹配ab
        //----------------
        // file操作:
        // String[] .matches(String regex)匹配正则表达式
        // String .replaceAll(String regex,String newStr)按正则表达式规则进行替换
        String s = "abc,bcd,cde";
        String result = s.replaceAll(",", "--");
        //底层创建匹配器对象,再调用正则表达式的replaceAll方法进行查找替换
        System.out.println(result);//abc--bcd--cde
        // String[] .split(String regex)按正则表达式规则进行分割
        String[] res = result.split("--");
        System.out.println(Arrays.toString(res));//[abc, bcd, cde]

        //捕获分组-------------------------
        //组号:以左括号为基准分组
        //  \\组号:把第n组的数据再拿出来匹配
        //例1:判断一个字符串的头尾是否一致(只考虑一个字符) a123a
        String regex_1="(.).+\\1";
        //例2:判断一个字符串的头尾是否一致(可以有多个字符) abc123ab
        String regex_2="(.+).+\\1";
        //例3:判断一个字符串的头尾是否一致(内部字符也要一致) aac123baa
        String regex_3="((.)\\2*).+\\1";

        //例4:替换,把重复的内容替换为单个     abbbccccdddd->abcd
        String str_1="abbbccccdddd";
        String str_2=str_1.replaceAll("(.)\\1+","$1");//在正则表达式外部捕获组使用$符
        System.out.println(str_2);///abcd

        //非捕获0分组
        //?: , ?= , ?!都是非捕获分组,不记入组号
    }
}
