package br.com.asaplog.dtos.customer;

import br.com.asaplog.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private String id;

    public static CustomerResponseDto create(Customer customer) {
        return new ModelMapper().map(customer, CustomerResponseDto.class);
    }

}
