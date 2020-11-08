package br.com.asaplog.controller;

import br.com.asaplog.dtos.insurancepolicy.InsurancePolicyRequestDto;
import br.com.asaplog.dtos.insurancepolicy.InsurancePolicyResponseDto;
import br.com.asaplog.dtos.other.BadRequestResponse;
import br.com.asaplog.exceptions.ValidationException;
import br.com.asaplog.model.InsurancePolicy;
import br.com.asaplog.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurancepolicy")
public class InsurancePolicyController {

    private final InsurancePolicyService insurancePolicyService;

    @Autowired
    public InsurancePolicyController(InsurancePolicyService insurancePolicyService) {
        this.insurancePolicyService = insurancePolicyService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody InsurancePolicyRequestDto insurancePolicyRequestDto) {

        try {

            InsurancePolicy insurancePolicy = insurancePolicyService.save(insurancePolicyRequestDto);
            return ResponseEntity.ok(InsurancePolicyResponseDto.create(insurancePolicy));

        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new BadRequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody InsurancePolicyRequestDto insurancePolicyRequestDto) {

        try {

            InsurancePolicy insurancePolicy = insurancePolicyService.update(id, insurancePolicyRequestDto);
            return ResponseEntity.ok(InsurancePolicyResponseDto.create(insurancePolicy));

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

            InsurancePolicy insurancePolicy = insurancePolicyService.delete(id);
            return ResponseEntity.ok(InsurancePolicyResponseDto.create(insurancePolicy));

        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new BadRequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e);
        }
    }

}
