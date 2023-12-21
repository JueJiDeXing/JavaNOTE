package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.responsibility职责.jdk;


public class Client {
    public static void main(String[] args) {
        Request  req = null;
        Response res = null ;

        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new FirstFilter()).addFilter(new SecondFilter());
        filterChain.doFilter(req,res);
    }
}
