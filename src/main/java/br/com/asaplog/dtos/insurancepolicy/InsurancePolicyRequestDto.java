package br.com.asaplog.dtos.insurancepolicy;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePolicyRequestDto {

    private String initialDate;
    private String finalDate;
    private String plateNumber;
    private Double value;
}
