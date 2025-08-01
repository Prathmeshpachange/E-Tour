package entity;

import jakarta.persistence.*;
import lombok.*;
import entity.Customer;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authId;
    
    private String password;
    private LocalDateTime lastLogin;
    private Byte status = 1;
    
    @OneToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;
}