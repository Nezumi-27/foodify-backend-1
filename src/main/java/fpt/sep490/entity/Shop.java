package fpt.sep490.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_enabled",nullable = false)
    private boolean isEnabled;

    @Column(name = "is_student",nullable = false)
    private boolean isStudent;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
