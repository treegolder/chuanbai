package cn.cbschool.chuanbai.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
//订单类，按情况考虑保留或删掉
@Entity
@Getter@Setter@NoArgsConstructor
public class PaymentOrder {
    @Id
    private String id;
    private String name;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @ManyToOne
    private Personal personal;
}
