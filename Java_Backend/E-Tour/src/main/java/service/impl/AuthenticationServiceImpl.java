 package service.impl;

import dto.LoginRequest;
import dto.LoginResponse;
import dto.SignupRequest;
import dto.SignupResponse;
import entity.Authentication;
import entity.Customer;
import mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.AuthenticationRepository;
import repository.CustomerRepository;
import security.JwtService;
import service.AuthenticationService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse authenticateUser(LoginRequest request) {
        Authentication auth = authenticationRepository.findByCustomerEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), auth.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        auth.setLastLogin(LocalDateTime.now());
        authenticationRepository.save(auth);

        String token = jwtService.generateToken(auth.getCustomer());

        return new LoginResponse(token, auth.getCustomer().getCustName());
    }

    @Override
    public SignupResponse registerUser(SignupRequest request) {

        if (authenticationRepository.findByCustomerEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }

        // Create customer
        Customer customer = new Customer();
        customer.setCustName(request.getName());
        customer.setCustEmail(request.getEmail());
        customer.setCustPhone(request.getPhone());
        customer.setCustDob(request.getDob());
        
        String gender = request.getGender();
        if (gender != null && !gender.isEmpty()) {
            customer.setCustGender(gender.charAt(0));
        } else {
            customer.setCustGender('U'); 
        }

        customer.setCustAddress(request.getAddress());
        customer.setCustCity(request.getCity());
        customer.setCustState(request.getState());
        customer.setCustPincode(request.getPincode());
        customer.setCustCountry("India"); // Set default country or get from frontend
        customer.setCustCreatedAt(LocalDateTime.now());

        // Create authentication
        Authentication auth = new Authentication();
        auth.setPassword(passwordEncoder.encode(request.getPassword()));
        auth.setCustomer(customer);
        customer.setAuthentication(auth);

        // Save customer (cascade saves auth)
        customerRepository.save(customer);

        String token = jwtService.generateToken(customer);

        return new SignupResponse(token, "Signup successful");
    }

    @Override
    public Optional<Authentication> findByCustomerCustEmail(String email) {
        return authenticationRepository.findByCustomerEmail(email);
    }
}
