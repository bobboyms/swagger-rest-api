package br.com.asaplog.dtos.insurancepolicy;

import br.com.asaplog.model.InsurancePolicy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePolicyResponseDto {

    private String id;
    private String policyNumber;

    public static InsurancePolicyResponseDto create(InsurancePolicy insurancePolicy) {
        return new ModelMapper().map(insurancePolicy, InsurancePolicyResponseDto.class);
    }

}
