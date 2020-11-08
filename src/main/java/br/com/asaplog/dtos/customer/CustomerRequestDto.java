package br.com.asaplog.dtos.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {

    private String name;
    private String cpf;
    private String city;
    private String uf;

}
