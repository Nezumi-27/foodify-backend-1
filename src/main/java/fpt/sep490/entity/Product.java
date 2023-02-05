package fpt.sep490.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_time", nullable = false)
    @CreationTimestamp
    private Date createdTime;

    @Column(name = "updated_time", nullable = false)
    @UpdateTimestamp
    private Date updatedTime;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;

    @Column(name = "discount_percent", nullable = false)
    private float discountPercent;

    @Column(name = "cost", nullable = false)
    private BigInteger cost;

    @Column(name = "average_rating", nullable = false)
    private float averageRating;

    @Column(name = "review_count", nullable = false)
    private int reviewCount;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    Set<Category> categories;
}
