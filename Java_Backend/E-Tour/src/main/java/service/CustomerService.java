package service;

import dto.CustomerDTO;
import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Integer custId);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO updateCustomer(Integer custId, CustomerDTO customerDTO);
    void deleteCustomer(Integer custId);
}
