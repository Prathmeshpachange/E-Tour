package dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import entity.Passenger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Integer bookingId;
    private LocalDateTime bookingDate;
    private Integer noOfPax;
    private Double tourAmount;
    private Double taxes;
    private Double totalAmount;
    private String paymentStatus;
    private Integer customerId;
    private Integer packageId;
    private Integer departureId;
    private List<PassengerDTO> passengers;
    
}
