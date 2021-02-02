package com.insurancecar.estimator.services;

import com.insurancecar.estimator.models.InsuredCar;

public interface EstimateInsuranceService {
    public Double getPrice(InsuredCar insuredCar);
}
