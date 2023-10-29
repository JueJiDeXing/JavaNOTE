package 集合;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class demoStreamEG {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list,1,2,3,4,5,6,7,8,9,10);
        Integer[] array = list.stream().filter(n -> n % 2 == 0).toArray(Integer[]::new);
        System.out.println(Arrays.toString(array));
    }
}
