package br.com.asaplog.model;

import br.com.asaplog.dtos.customer.CustomerRequestDto;
import br.com.asaplog.dtos.insurancepolicy.InsurancePolicyRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer")
public class InsurancePolicy {

    @Id
    private String id;
    private String policyNumber;
    private Date initialDate;
    private Date finalDate;
    private String plateNumber;
    private Double value;

    public static InsurancePolicy create(InsurancePolicyRequestDto insurancePolicyRequestDto) throws ParseException {
        return new InsurancePolicy(null,
                UUID.randomUUID().toString(),
                new SimpleDateFormat("dd/MM/yyyy").parse(insurancePolicyRequestDto.getInitialDate()),
                new SimpleDateFormat("dd/MM/yyyy").parse(insurancePolicyRequestDto.getFinalDate()),
                insurancePolicyRequestDto.getPlateNumber(),
                insurancePolicyRequestDto.getValue());
    }

    public static InsurancePolicy create(InsurancePolicyRequestDto insurancePolicyRequestDto, String policyNumber) throws ParseException {
        return new InsurancePolicy(null,
                policyNumber,
                new SimpleDateFormat("dd/MM/yyyy").parse(insurancePolicyRequestDto.getInitialDate()),
                new SimpleDateFormat("dd/MM/yyyy").parse(insurancePolicyRequestDto.getFinalDate()),
                insurancePolicyRequestDto.getPlateNumber(),
                insurancePolicyRequestDto.getValue());
    }
}
