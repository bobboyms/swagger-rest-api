package br.com.asaplog.controller;

import br.com.asaplog.dtos.insurancepolicyreport.InsurancePolicyReportDto;
import br.com.asaplog.dtos.other.BadRequestResponse;
import br.com.asaplog.exceptions.ValidationException;
import br.com.asaplog.service.InsurancePolicyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurancepolicyreport")
public class InsurancePolicyReportController {

    private final InsurancePolicyReportService insurancePolicyReportService;

    @Autowired
    public InsurancePolicyReportController(InsurancePolicyReportService insurancePolicyReportService) {
        this.insurancePolicyReportService = insurancePolicyReportService;
    }

    @GetMapping("/find/{policynumber}")
    public ResponseEntity findPolicy(@PathVariable("policynumber") String id) {

        try {

            InsurancePolicyReportDto insurancePolicy = insurancePolicyReportService.findByPolicyNumber(id);
            return ResponseEntity.ok(insurancePolicy);

        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BadRequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e);
        }

    }

}
