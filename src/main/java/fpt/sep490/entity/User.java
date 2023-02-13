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

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstname;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "dob", nullable = false)
    private Date dateOfBirth;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "is_locked", nullable = false)
    private boolean isLocked;

    @Column(name = "created_time", nullable = false)
    @CreationTimestamp
    private Timestamp createdTime;

    @Column(name = "identified_code", nullable = false)
    private String identifiedCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_address",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> addresses;
}
