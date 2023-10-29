package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 权重随机算法 {
    //以每个人的权重划分区间
    //[0.3,0.2,0.3,0.2]->[0,0.3),[0.3,0.5),[0.5,0.8),[0.8,1)
    public static void main(String[] args) {
        List<Student4>stuList=new ArrayList<>();
        stuList.add(new Student4(1));
        stuList.add(new Student4(1));
        stuList.add(new Student4(1));
        stuList.add(new Student4(1));
        stuList.add(new Student4(1));
        //给出一个对象带权重集合(目前权重都为1),实现概率权重随机抽取算法
        //得到文件里的对象集合
        //计算总权重
        double weight=0;
        for (Student4 student4 : stuList) {
            weight = weight + student4.getWeight();
        }
        //计算每一个人的实际占比
        double[] arr=new double[stuList.size()];
        for (int i = 0; i< stuList.size(); i++){
            arr[i]= stuList.get(i).getWeight()/weight;
        }
        System.out.println(Arrays.toString(arr));//[0.2, 0.2, 0.2, 0.2, 0.2]
        //区间
        for (int i=1;i<arr.length;i++){
            arr[i]=arr[i]+arr[i-1];
        }
        System.out.println(Arrays.toString(arr));//[0.2, 0.4, 0.6000000000000001, 0.8, 1.0]

        //随机抽取
        double number= Math.random();
        System.out.println(number);//0.24520110143955542
        int i = Arrays.binarySearch(arr, number);//返回-插入点-1
        System.out.println(-i-1);//1,抽到的为索引为1的学生
        int index=-i-1;
        Student4 stu=stuList.get(index);
        //修改抽到的学生权重,并写入到文件中
        stu.setWeight(stu.getWeight()/2);//1->0.5
        System.out.println(Arrays.toString(arr));

    }

}
class Student4{
    double weight;

    public Student4() {
        this.weight=1;
    }

    public Student4(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
