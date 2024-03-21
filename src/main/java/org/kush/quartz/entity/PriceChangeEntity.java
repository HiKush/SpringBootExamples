package org.kush.quartz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "price_change")  // Optional: customize the database table name
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceChangeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private Long id;

    @Column(nullable = false) // Specify column constraints
    Double priceChange;

}
