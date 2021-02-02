package com.insurancecar.estimator.models;

import com.insurancecar.estimator.utils.CoverageType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InsuredCar {
    @NotNull
    @NotEmpty
    private String brand;
    @NotNull
    @NotEmpty
    private Integer year;
    @NotNull
    @NotEmpty
    private CoverageType coverageType;
    private Double price;

    public InsuredCar() {
    }

    public InsuredCar(String brand, Integer year, CoverageType coverageType) {
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

    public CoverageType getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(CoverageType coverageType) {
        this.coverageType = coverageType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
