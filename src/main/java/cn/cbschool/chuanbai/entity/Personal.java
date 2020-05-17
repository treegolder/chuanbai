package cn.cbschool.chuanbai.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter@Setter@NoArgsConstructor
public class Personal {
    //使用电话号作为登录账号
    @Id
    @Size(min = 11,max = 11,message ="您输入的值为${validatedValue},不符合标准电话号位数" )
    private String tel;
    @Size(min = 6,max = 24,message = "您输入的值为${validatedValue},长度应在6到24位")
    private String pwd;
    @Size(min = 2,max = 6,message = "您输入的值为${validatedValue},长度应在2到6位")
    private String name;
    //当identityFlag为1时，StoreName为空
    private String storeName;
    @Size(min = 18,max = 18
    ,message = "您输入的值为${validatedValue},不符合标准身份证号位数")
    private String identity;



    //身份标识 个人用户：1，商家：2，学校组织：3(组织先别取消，注册可以没有，后期需要这个标识！！！！！)
    private String identityFlag;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    private String school;


    @OneToMany(mappedBy = "personal")
    private List<PaymentOrder> orders;

    @OneToMany(mappedBy = "personal")
    private List<Commodity> commodities;

}
