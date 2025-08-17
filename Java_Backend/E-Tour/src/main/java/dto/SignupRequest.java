package dto;

import java.time.LocalDate;

import lombok.Data;


@Data
public class SignupRequest {
	private String name;
	private String email;
	private String phone;
	private String password;
	private String gender;
	private LocalDate dob;
	private String address;
	private String city;
	private String state;
	private String pincode;

}
