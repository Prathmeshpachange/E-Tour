package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paxId;
    
    private String paxName;
    private LocalDate paxBirthdate;
    
    @Enumerated(EnumType.STRING)
    private PaxType paxType;
    
    private Double paxAmount;
    
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}

enum PaxType {
    TWIN_SHARING("Twin Sharing"),
    SINGLE_PERSON("Single Person"),
    EXTRA_PERSON("Extra Person"),
    CHILD_WITH_BED("Child With Bed"),
    CHILD_WITHOUT_BED("Child Without Bed");
    
    private final String displayName;
    
    PaxType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}