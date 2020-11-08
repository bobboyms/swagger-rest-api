package br.com.asaplog.controller;

import br.com.asaplog.dtos.customer.CustomerRequestDto;
import br.com.asaplog.dtos.customer.CustomerResponseDto;
import br.com.asaplog.dtos.other.BadRequestResponse;
import br.com.asaplog.exceptions.ValidationException;
import br.com.asaplog.model.Customer;
import br.com.asaplog.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody CustomerRequestDto customerRequestDto) {

        try {

            Customer customer = customerService.save(customerRequestDto);
            return ResponseEntity.ok(CustomerResponseDto.create(customer));

        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new BadRequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody CustomerRequestDto customerRequestDto) {

        try {

            Customer customer = customerService.update(id, customerRequestDto);
            return ResponseEntity.ok(CustomerResponseDto.create(customer));

        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new BadRequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity update(@PathVariable("id") String id) {

        try {

            Customer customer = customerService.delete(id);
            return ResponseEntity.ok(CustomerResponseDto.create(customer));

        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new BadRequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e);
        }
    }

}
