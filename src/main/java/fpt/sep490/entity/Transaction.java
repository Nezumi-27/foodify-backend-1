package fpt.sep490.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    @Id
    private String id;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false)
    private Timestamp createdTime;

    @Column(name = "user_full_name", nullable = false)
    private String userFullName;

    @Column(name = "product_cost", nullable = false)
    private Long productCost;

    @Column(name = "shipping_cost", nullable = false)
    private Long shippingCost;

    @Column(name = "total", nullable = false)
    private Long total;
}
