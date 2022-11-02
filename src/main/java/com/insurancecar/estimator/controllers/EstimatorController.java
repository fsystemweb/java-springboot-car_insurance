package com.insurancecar.estimator.controllers;

import com.insurancecar.estimator.models.InsuredCar;
import com.insurancecar.estimator.services.EstimateInsuranceService;
import com.insurancecar.estimator.services.EstimateInsuranceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class EstimatorController {
    private final EstimateInsuranceService estimateInsuranceService;

    @PostMapping(value="/api")
    public ResponseEntity getEstimation(@Valid @RequestBody InsuredCar insuredCar){

        Double price = estimateInsuranceService.getPrice(insuredCar);

        if(price == 0.00)
            return ResponseEntity.badRequest().body("Invalid brand");

        insuredCar.setPrice(price);

        return ResponseEntity.ok().body(insuredCar);
    }
}
