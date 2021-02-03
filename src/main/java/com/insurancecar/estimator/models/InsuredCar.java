package com.insurancecar.estimator.models;

import com.insurancecar.estimator.utils.CoverageType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    public InsuredCar() {
    }

    public InsuredCar(String brand, Integer year, String coverageType) {
        this.brand = brand;
        this.year = year;
        this.coverageType = coverageType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
