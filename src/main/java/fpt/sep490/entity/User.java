package fpt.sep490.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"identified_code"})
    })
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "dob", nullable = false)
    private String dateOfBirth;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "is_locked", nullable = false)
    private Boolean isLocked;

    @Column(name = "created_time", nullable = false)
    @CreationTimestamp
    private Timestamp createdTime;

    @Column(name = "identified_code", nullable = false)
    private String identifiedCode;

    @Column(name = "fcm_token")
    private String fcmToken;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "default_address")
    private Long defaultAddress;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_address",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> addresses;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Shipper shipper;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Shop shop;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;
}
