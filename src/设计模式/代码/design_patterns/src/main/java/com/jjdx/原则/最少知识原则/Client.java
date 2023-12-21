package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.最少知识原则;

/**
 <h1>最少知识原则/迪米特法则</h1>
 只和你的直接朋友交谈，不跟“陌生人”说话（Talk only to your immediate friends and not to strangers）。<br>
 其含义是：如果两个软件实体无须直接通信，那么就不应当发生直接的相互调用，可以通过第三方转发该调用。<br>
 其目的是降低类之间的耦合度，提高模块的相对独立性。<br>
 <p>
 迪米特法则中的“朋友”是指：当前对象本身、当前对象的成员对象、当前对象所创建的对象、当前对象的方法参数等，<br>
 这些对象同当前对象存在关联、聚合或组合关系，可以直接访问这些对象的方法。
 */
public class Client {
    public static void main(String[] args) {
        //创建经纪人类
        Agent agent = new Agent();
        //创建明星对象
        Star star = new Star("林青霞");
        agent.setStar(star);
        //创建粉丝对象
        Fans fans = new Fans("李四");
        agent.setFans(fans);
        //创建媒体公司对象
        Company company = new Company("黑马媒体公司");
        agent.setCompany(company);

        agent.meeting();//和粉丝见面
        agent.business();//和媒体公司洽谈业务
    }
}
