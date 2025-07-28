package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;
    
    private LocalDateTime bookingDate = LocalDateTime.now();
    private Integer noOfPax;
    private Double tourAmount;
    private Double taxes;
    private Double totalAmount;
    private String paymentStatus;
    
    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package tourPackage;  
    
    @ManyToOne
    @JoinColumn(name = "departure_id")
    private Departure departure;
    
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Passenger> passengers;
}