package 课程.实验课.课堂练习;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class 继承与多态课堂练习 {
    public static void main(String[] args) {
        SaveAccount account = new SaveAccount(
                "ZhangHao", "张三", "36014220030528681X",
                10000, "123456", "北京", 100,
                0.01);
        account.setPassword("123456", "654321");
        System.out.println("密码为:" + account.getPassword());
        account.setPassword("1234567", "12345");
        System.out.println("密码为:" + account.getPassword());
        SaveAccount.setYearRate(0.03);
        System.out.println(account);
    }


}

@Data
class Account {
    private final String account;//创建后不可修改
    String name;
    Date date;
    String id;
    double balance;

    public Account(String account, String name, String id, double balance) {
        this.account = account;
        this.name = name;
        this.date = new Date();
        this.id = id;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}

@Getter
class SaveAccount extends Account {
    private String password;
    @Setter
    String address;
    @Setter
    double minBalance;
    @Setter
    double monthRate;
    @Setter
    static double yearRate;


    public SaveAccount(String account, String name, String id, double balance,
                       String password, String address, double minBalance, double monthRate) {
        super(account, name, id, balance);
        this.password = password;
        this.address = address;
        this.minBalance = minBalance;
        this.monthRate = monthRate;
    }

    public void setPassword(String oldPassword, String newPassword) {
        if (oldPassword == null || !oldPassword.equals(password)) {
            System.out.println("密码错误,修改失败");
            return;
        }
        password = newPassword;
    }

    public double getMonthInterest() {
        return balance * yearRate / 12;
    }

    @Override
    public String toString() {
        return "SaveAccount{" +
                "password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", minBalance=" + minBalance +
                ", monthRate=" + monthRate +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}
