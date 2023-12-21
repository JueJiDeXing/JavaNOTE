package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.combination组合;

import java.util.ArrayList;
import java.util.List;


public class Menu extends MenuComponent {

    //菜单可以有多个子菜单或者子菜单项
    private List<MenuComponent> menuComponentList = new ArrayList<MenuComponent>();

    //构造方法
    public Menu(String name,int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponentList.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponentList.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int index) {
        return menuComponentList.get(index);
    }

    @Override
    public void print() {
        //打印菜单名称
        for(int i = 0; i < level; i++) {
            System.out.print("--");
        }
        System.out.println(name);

        //打印子菜单或者子菜单项名称
        for (MenuComponent component : menuComponentList) {
            component.print();
        }
    }
}
