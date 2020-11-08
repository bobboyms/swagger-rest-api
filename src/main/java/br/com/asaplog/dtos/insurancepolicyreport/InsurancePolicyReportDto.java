package br.com.asaplog.dtos.insurancepolicyreport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePolicyReportDto {

    private String id;
    private String policyNumber;
    private Boolean finished;
    private Long daysToFinish;
    private String plateNumber;
    private Double value;


}
