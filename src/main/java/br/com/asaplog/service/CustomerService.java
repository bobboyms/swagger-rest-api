package br.com.asaplog.service;

import br.com.asaplog.dtos.customer.CustomerRequestDto;
import br.com.asaplog.exceptions.ValidationException;
import br.com.asaplog.model.Customer;
import br.com.asaplog.repository.CustomerRepository;
import br.com.asaplog.utils.ValidationCpf;
import br.com.asaplog.utils.ValidationString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(CustomerRequestDto customerRequestDto) {

        final List<String> errorMessages = basicValidateCustomer(customerRequestDto);

        if (customerRepository.findByCpf(customerRequestDto.getCpf()).isPresent()) {
            errorMessages.add("cpf already exists in the database");
        }

        if (errorMessages.isEmpty()) {
            return customerRepository.save(Customer.create(customerRequestDto));
        }

        throw new ValidationException(String.join(" - ", errorMessages));

    }

    public Customer update(String id, CustomerRequestDto customerRequestDto) {

        final List<String> errorMessages = basicValidateCustomer(customerRequestDto);

        Optional<Customer> optional = customerRepository.findById(id);

        if (!optional.isPresent()) {
            errorMessages.add("Customer not found");
        }

        if (errorMessages.isEmpty()) {
            Customer tempCustomer = Customer.create(customerRequestDto);
            tempCustomer.setId(id);
            return customerRepository.save(tempCustomer);
        }

        throw new ValidationException(String.join(" - ", errorMessages));

    }

    public Customer delete(String id) {

        final List<String> errorMessages = new ArrayList<>();

        Optional<Customer> optional = customerRepository.findById(id);

        if (!optional.isPresent()) {
            errorMessages.add("Customer not found");
        }

        if (errorMessages.isEmpty()) {
            customerRepository.delete(optional.get());
            return optional.get();
        }

        throw new ValidationException(String.join(" - ", errorMessages));

    }

    private List<String> basicValidateCustomer(CustomerRequestDto customerRequestDto) {

        final List<String> errorMessages = new ArrayList<>();

        if (!ValidationString.isValid(customerRequestDto.getName().trim())) {
            errorMessages.add("empty or null name is not allowed");
        }

        if (!ValidationCpf.isValid(customerRequestDto.getCpf().trim())) {
            errorMessages.add("invalid cpf");
        }

        if (!ValidationString.isValid(customerRequestDto.getCity().trim())) {
            errorMessages.add("empty or null city is not allowed");
        }

        if (!ValidationString.isValid(customerRequestDto.getUf().trim())) {
            errorMessages.add("empty or null UF is not allowed");
        }

        return errorMessages;
    }

}
