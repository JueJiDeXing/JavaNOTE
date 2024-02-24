package 基础.基础;

/*
函数式接口（Functional Interface）是指只包含一个抽象方法的接口。
函数式接口是Java 8引入的概念，它是支持函数式编程的关键要素。

函数式接口具有以下特点：

1. 只包含一个抽象方法： 函数式接口中只能有一个未被实现的抽象方法。这个抽象方法定义了函数式接口的行为。
2. 可以有默认方法和静态方法： 函数式接口可以包含默认方法和静态方法。默认方法提供了已经实现的方法，而静态方法提供了与接口相关的实用方法。
3. 可以由Lambda表达式和方法引用表示： 函数式接口可以使用Lambda表达式或方法引用来创建接口的实例。
4. 函数式接口的引入主要是为了支持函数式编程风格，它可以作为方法的参数、返回值或赋值给变量/常量，使得代码更简洁、易读、易维护。

在Java标准库中，已经定义了一些常用的函数式接口，比如Runnable、Comparator、Consumer、Function等。
此外，也可以通过@FunctionalInterface注解来显式地标记一个接口为函数式接口，编译器会检查接口是否满足函数式接口的条件。

函数式接口的典型应用场景是使用Lambda表达式或方法引用来实现函数式编程，如在Stream API、并行处理等功能中。
 */
// 定义一个函数式接口
@FunctionalInterface //可选注解,添加注解有助于编译器检查
interface Operation {
    int calculate(int a, int b);
}

public class 函数传参 {// 函数A，接受一个Operation函数作为参数

    static void performOperation(int a, int b, Operation operation) {
        int result = operation.calculate(a, b);
        System.out.println("Result: " + result);
    }

    public static void main(String[] args) {
        // 使用Lambda表达式传递函数到performOperation函数调用上
        performOperation(5, 3, (a, b) -> a - b);

        // 使用自定义的实现类传递函数到performOperation函数调用上
        performOperation(4, 7, new Operation() {
            @Override
            public int calculate(int a, int b) {
                return a - b;
            }
        });
    }
}



