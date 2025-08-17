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
