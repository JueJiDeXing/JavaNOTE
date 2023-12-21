package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.responsibility职责.jdk;


public class FirstFilter implements Filter {
    public void doFilter(Request req, Response res, FilterChain chain) {
        System.out.println("过滤器1 前置处理");

        // 先执行所有request再倒序执行所有response
        chain.doFilter(req, res);

        System.out.println("过滤器1 后置处理");
    }
}
