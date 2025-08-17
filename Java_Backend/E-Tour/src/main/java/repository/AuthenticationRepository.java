package repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import entity.Authentication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthenticationRepository extends JpaRepository<Authentication, Integer> {
    @Query("SELECT a FROM Authentication a WHERE a.customer.custEmail = :email")
    Optional<Authentication> findByCustomerEmail(@Param("email") String email);
}
