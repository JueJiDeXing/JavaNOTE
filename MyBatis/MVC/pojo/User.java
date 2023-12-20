package demo.my_batis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//使用lombok简化,在编译时期自动生成对应java代码
@Data//@Data=@Getter+@Setter+@ToString+@EqualsAndHashCode
@NoArgsConstructor//生成无参构造
@AllArgsConstructor//生成全参构造
public class User {
    private Integer id;
    private String name;
    private short age;
}
