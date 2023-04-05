package fpt.sep490.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "shippers")
public class Shipper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "is_shipping")
    private Boolean isShipping;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "shipper", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;
}
