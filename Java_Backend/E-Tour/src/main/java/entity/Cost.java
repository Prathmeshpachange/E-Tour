package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer costId;
    
    private Double singlePersonCost;
    private Double extraPersonCost;
    private Double childWithBed;
    private Double childWithoutBed;
    private LocalDate validFrom;
    private LocalDate validTo;
    
    @ManyToOne
    @JoinColumn(name = "package_id")
    private TourPackage tourPackage;
}