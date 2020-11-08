package br.com.asaplog.service;

import br.com.asaplog.dtos.insurancepolicy.InsurancePolicyRequestDto;
import br.com.asaplog.exceptions.ValidationException;
import br.com.asaplog.model.InsurancePolicy;
import br.com.asaplog.repository.InsurancePolicyRepository;
import br.com.asaplog.utils.ValidationDouble;
import br.com.asaplog.utils.ValidationString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InsurancePolicyService {

    private final InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    public InsurancePolicyService(InsurancePolicyRepository insurancePolicyRepository) {
        this.insurancePolicyRepository = insurancePolicyRepository;
    }

    public InsurancePolicy save(InsurancePolicyRequestDto insurancePolicyRequestDto)  {

        final List<String> errorMessages = basicValidateInsurancePolicy(insurancePolicyRequestDto);

        if (errorMessages.isEmpty()) {

            try {

                InsurancePolicy insurancePolicy = InsurancePolicy.create(insurancePolicyRequestDto);
                return insurancePolicyRepository.save(insurancePolicy);

            } catch (ParseException e) {
                errorMessages.add("initial date or final date is invalid");
            }


        }

        throw new ValidationException(String.join(" - ", errorMessages));

    }

    public InsurancePolicy update(String id, InsurancePolicyRequestDto insurancePolicyRequestDto) {

        final List<String> errorMessages = basicValidateInsurancePolicy(insurancePolicyRequestDto);

        Optional<InsurancePolicy> optional = insurancePolicyRepository.findById(id);

        if (!optional.isPresent()) {
            errorMessages.add("Policy not found");
        }

        if (errorMessages.isEmpty()) {

            try {
                InsurancePolicy tempInsurancePolicy = InsurancePolicy.create(insurancePolicyRequestDto, optional.get().getPolicyNumber());
                tempInsurancePolicy.setId(id);
                return insurancePolicyRepository.save(tempInsurancePolicy);
            } catch (ParseException e) {
                errorMessages.add("initial date or final date is invalid");
            }

        }

        throw new ValidationException(String.join(" - ", errorMessages));

    }

    public InsurancePolicy delete(String id) {

        final List<String> errorMessages = new ArrayList<>();

        Optional<InsurancePolicy> optional = insurancePolicyRepository.findById(id);

        if (!optional.isPresent()) {
            errorMessages.add("Policy not found");
        }

        if (errorMessages.isEmpty()) {
            insurancePolicyRepository.delete(optional.get());
            return optional.get();
        }

        throw new ValidationException(String.join(" - ", errorMessages));

    }

    private List<String> basicValidateInsurancePolicy(InsurancePolicyRequestDto insurancePolicyRequestDto) {

        final List<String> errorMessages = new ArrayList<>();

        if (!ValidationString.isValid(insurancePolicyRequestDto.getInitialDate().trim())) {
            errorMessages.add("empty or null initial date is not allowed");
        }

        if (!ValidationString.isValid(insurancePolicyRequestDto.getFinalDate().trim())) {
            errorMessages.add("empty or null final date is not allowed");
        }

        if (!ValidationString.isValid(insurancePolicyRequestDto.getPlateNumber().trim())) {
            errorMessages.add("empty or null plate number is not allowed");
        }

        if (!ValidationDouble.isValid(insurancePolicyRequestDto.getValue())) {
            errorMessages.add("null or less and equal to zero value is not allowed");
        }

        return errorMessages;
    }

}
