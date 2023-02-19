package fpt.sep490.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "shops", uniqueConstraints = {@UniqueConstraint(columnNames = "user_id")})
public class Shop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_enabled",nullable = false)
    private Boolean isEnabled;

    @Column(name = "is_student",nullable = false)
    private Boolean isStudent;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
