package repository;

import entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookingRepository extends JpaRepository<Booking, Integer> {
	@Query("SELECT b FROM Booking b LEFT JOIN FETCH b.passengers WHERE b.bookingId = :id")
	Booking findByIdWithPassengers(@Param("id") Integer id);

}
