package dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
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
    private String custCountry;
    private LocalDateTime custCreatedAt;
}
