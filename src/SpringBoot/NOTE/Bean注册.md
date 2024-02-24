使用XML配置文件：
在XML配置文件中定义Bean的配置，然后在应用程序启动时加载该配置文件并解析注册Bean。
例如，使用Spring Framework的<bean>标签进行配置和注册。

    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
                http://www.springframework.org/schema/beans/spring-beans.xsd">
    
        <bean id="myBean" class="com.example.MyBean">
            <!-- Bean的属性配置 -->
            <property name="name" value="John" />
            <property name="age" value="25" />
        </bean>
    </beans>

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    
        // 获取注册的Bean
        MyBean myBean = (MyBean) context.getBean("myBean");
    
        // 使用Bean
        System.out.println("Name: " + myBean.getName());
        System.out.println("Age: " + myBean.getAge());
    }
使用注解：
在Java类中使用注解来标记Bean，并在应用程序中使用相应的注解处理器来扫描并注册Bean。
例如，使用Spring Framework的@Component、@Service、@Repository等注解。

    @Component("myBean")
    public class MyBean {
        private String name;
        private int age;
    
        // 构造方法、Getter和Setter省略
    
        public void doSomething() {
            System.out.println("Doing something...");
        }
    }

使用Java配置类：
创建一个Java配置类，通过编程方式定义和注册Bean。
例如，使用Spring Framework的@Configuration注解并在该类中使用@Bean注解定义Bean。

    @Configuration
    public class CommonConfig {
        @ConditionOnProperty(prefix = "mybean", name = {"name", "age"})//条件注入,存在属性则注入Bean对象到IOC
        @Bean("bean_name1")
        public MyBean bean_name1(@Value("${mybean.name") String name,@Value("${mybean.age") String age){//读入配置
            MyBean myBean = new MyBean();
            myBean.setName(name);//给Bean的属性赋值
            myBean.setAge(age);
            return myBean;
        }
        
        @ConditionOnMissingBean(MyBean2.class)//MyBean2的对象存在时才注入
        @Bean("bean_name2")
        public MyBean bean_name2(MyBean2 bean) {//依赖其他bean对象
            return new MyBean();
        }
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(QuickstartApplication.class, args);
        MyBean myBean = (MyBean) context.getBean("bean_name");
    }

使用注解扫描：
配置应用程序进行注解扫描，让应用程序自动发现带有特定注解的类并将其注册为Bean。
例如，使用Spring Framework的@ComponentScan注解来扫描并注册带有@Component注解的类。

使用Import注解：
导入配置类或ImportSelector接口的实现类
    
    @Import({CommonConfig.class,...})//导入配置类,加了Bean注解的方法会自动注入IOC
    @SpringBootApplication
    public class Application {
        public static void main(String[] args) {
    
        }
    }

    public class CommonImportSelector implements ImportSelector {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            // 返回需要导入的类
            return new String[]{"com.jjdx.quickstart.config.CommonConfig",...};
            /*
            也可以写在自定义文件中,然后通过流进行读取,减少耦合
            List<String>imports=new ArrayList<>();
            InputStream is = CommonImportSelector.class.getClassLoader().getResourceAsStream("myFile.file");
            ......
            return imports.toArray(new String[0]);
            */
        }
    }
    
    @Import(CommonImportSelector.class)
    @SpringBootApplication
    public class Application {
        public static void main(String[] args) {
    
        }
    }

使用第三方容器：
使用第三方容器或框架，如Google Guice、Apache Tomcat等，它们提供自己的方式来注册和管理Bean。
