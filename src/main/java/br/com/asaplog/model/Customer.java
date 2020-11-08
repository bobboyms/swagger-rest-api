package br.com.asaplog.model;

import br.com.asaplog.dtos.customer.CustomerRequestDto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer")
public class Customer {

    @Id
    private String id;
    private String name;
    private String cpf;
    private String city;
    private String uf;

    public static Customer create(CustomerRequestDto customerRequestDto) {
        return new ModelMapper().map(customerRequestDto, Customer.class);
    }

}
