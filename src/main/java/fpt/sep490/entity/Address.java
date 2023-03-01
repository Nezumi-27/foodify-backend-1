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
@Table(name = "address", uniqueConstraints = {@UniqueConstraint(columnNames = {"address"})})
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "ward", nullable = false)
    private String ward;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;

    @ManyToMany(mappedBy = "addresses")
    private Set<User> users;
}
