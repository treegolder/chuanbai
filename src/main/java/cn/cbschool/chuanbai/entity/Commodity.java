package cn.cbschool.chuanbai.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

//商品类保留，及时不用展示商家页面，但是测试的时候需要，属性不全，根据后续需求添加
@Entity
@Getter@Setter@NoArgsConstructor
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   @Size(min = 2)
    private String name;
   @Min(0)
    private Integer money;
    //库存
    @Min(0)
    private Integer stock;
    @ManyToOne
    private Personal personal;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;


}
