package br.com.asaplog.service;

import br.com.asaplog.dtos.insurancepolicyreport.InsurancePolicyReportDto;
import br.com.asaplog.exceptions.ValidationException;
import br.com.asaplog.model.Customer;
import br.com.asaplog.model.InsurancePolicy;
import br.com.asaplog.repository.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class InsurancePolicyReportService {

    private final InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    public InsurancePolicyReportService(InsurancePolicyRepository insurancePolicyRepository) {
        this.insurancePolicyRepository = insurancePolicyRepository;
    }

    public InsurancePolicyReportDto findByPolicyNumber(String policyNumber) {

        final Optional<InsurancePolicy> optional = insurancePolicyRepository.findByPolicyNumber(policyNumber);

        if (optional.isPresent()) {

            InsurancePolicy insurancePolicy = optional.get();

            LocalDate localDateNow = LocalDate.now();

            LocalDate localDateFinal = insurancePolicy.getFinalDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            long period = ChronoUnit.DAYS.between(localDateNow, localDateFinal);

            return new InsurancePolicyReportDto(insurancePolicy.getId(),
                    insurancePolicy.getPolicyNumber(),
                    period <= 0,
                    period >= 0 ? period : 0,
                    insurancePolicy.getPlateNumber(),
                    insurancePolicy.getValue());
        }

        throw new ValidationException("Policy not fount");

    }

}
