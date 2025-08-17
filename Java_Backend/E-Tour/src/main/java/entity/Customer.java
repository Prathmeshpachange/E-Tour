package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer custId;
    
    private String custName;
    private String custEmail;
    private String custPhone;
    private LocalDate custDob;
    private Character custGender;
    private String custAddress;
    private String custCity;
    private String custState;
    private String custPincode;
    private String custCountry = "India";
    private LocalDateTime custCreatedAt = LocalDateTime.now();
    
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Authentication authentication;
    

    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}