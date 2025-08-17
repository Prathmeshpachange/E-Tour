package service.impl;

import dto.CustomerDTO;
import entity.Customer;
import mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CustomerRepository;
import service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = Mapper.MapToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return Mapper.MapToCustomerDTO(savedCustomer);
    }

    @Override
    public CustomerDTO getCustomerById(Integer custId) {
        Optional<Customer> customerOpt = customerRepository.findById(custId);
        return customerOpt.map(Mapper::MapToCustomerDTO).orElse(null);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();

        for (Customer customer : customers) {
            customerDTOs.add(Mapper.MapToCustomerDTO(customer));
        }

        return customerDTOs;
    }

    @Override
    public CustomerDTO updateCustomer(Integer custId, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(custId);

        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();

            // Updating fields from DTO
            existingCustomer.setCustName(customerDTO.getCustName());
            existingCustomer.setCustEmail(customerDTO.getCustEmail());
            existingCustomer.setCustPhone(customerDTO.getCustPhone());
            existingCustomer.setCustDob(customerDTO.getCustDob());
            existingCustomer.setCustGender(customerDTO.getCustGender());
            existingCustomer.setCustAddress(customerDTO.getCustAddress());
            existingCustomer.setCustCity(customerDTO.getCustCity());
            existingCustomer.setCustState(customerDTO.getCustState());
            existingCustomer.setCustPincode(customerDTO.getCustPincode());
            existingCustomer.setCustCountry(customerDTO.getCustCountry());

            Customer updatedCustomer = customerRepository.save(existingCustomer);
            return Mapper.MapToCustomerDTO(updatedCustomer);
        }

        return null;
    }

    @Override
    public void deleteCustomer(Integer custId) {
        customerRepository.deleteById(custId);
    }
}
