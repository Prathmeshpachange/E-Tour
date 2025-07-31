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
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer packageId;
    
    private String packageName;
    private String packageInfo;
    private String packageImagePath;
    private Integer durationDays;
    private LocalDate startDate;
    private LocalDate endDate;
    
    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;
    
    @OneToMany(mappedBy = "package", cascade = CascadeType.ALL)
    private List<Itinerary> itineraries;
    
    @OneToMany(mappedBy = "package", cascade = CascadeType.ALL)
    private List<Cost> costs;
    
    @OneToMany(mappedBy = "package", cascade = CascadeType.ALL)
    private List<Departure> departures;
    
    @OneToMany(mappedBy = "package", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}