package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itineraryId;
    
    private Integer dayNo;
    private String dayDetail;
    
    @ManyToOne
    @JoinColumn(name = "package_id")
    private TourPackage tourPackage;
}