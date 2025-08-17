
package service;

import dto.*;
import entity.*;
import java.util.Optional;

public interface AuthenticationService {
    LoginResponse authenticateUser(LoginRequest request);
    SignupResponse registerUser(SignupRequest request);
    Optional<Authentication> findByCustomerCustEmail(String email);

}
