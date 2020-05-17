package cn.cbschool.chuanbai.component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyToken {
    public static final String AUTHORIZATION = "authorization";
    public static final String UID = "uid";
    private Integer uid;
}
