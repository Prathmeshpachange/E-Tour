package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Departure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departureId;
    
    private LocalDate departDate;
    private LocalDate endDate;
    private Integer noOfDays;
    
    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package tourPackage;
    
    @OneToMany(mappedBy = "departure", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}