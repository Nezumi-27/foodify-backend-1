package fpt.sep490.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "orders", uniqueConstraints = {@UniqueConstraint(columnNames = {"order_tracking_number"})})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_tracking_number", nullable = false)
    private String orderTrackingNumber;

    @Column(name = "order_time", nullable = false)
    private Timestamp orderTime;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "product_cost", nullable = false)
    private BigInteger productCost;

    @Column(name = "shipping_cost", nullable = false)
    private BigInteger shippingCost;

    @Column(name = "total", nullable = false)
    private BigInteger total;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "ward", nullable = false)
    private String ward;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;
}
