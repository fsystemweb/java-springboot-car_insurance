package com.insurancecar.estimator.models;

import com.insurancecar.estimator.utils.CoverageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuredCar {
    @NotNull
    @NotEmpty
    private String brand;
    @NotNull
    private Integer year;
    @NotNull
    @NotEmpty
    private String coverageType;
    private Double price;

    public InsuredCar(String brand, Integer year, String coverageType) {
        this.brand = brand;
        this.year = year;
        this.coverageType = coverageType;
    }
}
