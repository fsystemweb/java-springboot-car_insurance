package com.insurancecar.estimator.controllers;

import com.insurancecar.estimator.models.InsuredCar;
import com.insurancecar.estimator.services.EstimateInsuranceServiceImpl;
import com.insurancecar.estimator.utils.Constants;
import com.insurancecar.estimator.utils.CoverageType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.text.DecimalFormat;
import java.util.Calendar;


public class EstimatorControllerTest {
    @Mock
    private EstimateInsuranceServiceImpl estimateInsuranceService;

    @InjectMocks
    private EstimatorController estimatorController;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void return404whenYearNotFound() {
        InsuredCar insuredCar = new InsuredCar();

        insuredCar.setBrand("Ford");
        insuredCar.setCoverageType(CoverageType.full.name());

        ResponseEntity result = estimatorController.getEstimation(insuredCar);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST ,result.getStatusCode());
    }

    @Test
    public void return404whenBrandNotFound() {
        InsuredCar insuredCar = new InsuredCar();

        insuredCar.setYear(2019);
        insuredCar.setCoverageType(CoverageType.full.name());

        ResponseEntity result = estimatorController.getEstimation(insuredCar);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST ,result.getStatusCode());
    }

    @Test
    public void return200whenEstimatePrice() {
        InsuredCar insuredCar = new InsuredCar();

        insuredCar.setBrand("Ford");
        insuredCar.setYear(2019);
        insuredCar.setCoverageType(CoverageType.full.name());

        Double expected = 335.11;

        Mockito.when(estimateInsuranceService.getPrice(insuredCar)).thenReturn(expected);

        ResponseEntity<InsuredCar> result = estimatorController.getEstimation(insuredCar);

        Assertions.assertEquals(HttpStatus.OK ,result.getStatusCode());
        Assertions.assertEquals(expected, result.getBody().getPrice());
    }
}
