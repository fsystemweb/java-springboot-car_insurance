package com.insurancecar.estimator.services;


import com.insurancecar.estimator.models.Brand;
import com.insurancecar.estimator.models.InsuredCar;
import com.insurancecar.estimator.utils.Constants;
import com.insurancecar.estimator.utils.CoverageType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EstimateInsuranceServiceTest {
    @Mock
    private BrandRateServiceImpl brandServiceRate;

    @InjectMocks
    private EstimateInsuranceServiceImpl estimateInsuranceService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getPriceZero_whenBrandNotExist()  {
        Mockito.when(brandServiceRate.getBrands()).thenReturn(createListBrandOnlyForTestPurpose());

        Double result = estimateInsuranceService.getPrice(new InsuredCar("Torino", 1971, CoverageType.full.name()));
        Double expected = 0.00;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getHigherPrice_whenUseCoverageTypeFull()  {
        Mockito.when(brandServiceRate.getBrands()).thenReturn(createListBrandOnlyForTestPurpose());

        InsuredCar mBenzFull = new InsuredCar("Mercedes-Benz", 2019, CoverageType.full.name());

        Double result = estimateInsuranceService.getPrice(mBenzFull);

        double sum = Constants.BASE_AMOUNT;
        sum = sum * 1.45;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int diff = currentYear - mBenzFull.getYear();
        sum -= ((diff * Constants.YEAR_PERCENT_DECREASE) * sum) / 100;
        Double expected = sum * Constants.COVERAGE_FULL_RATE;


        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getLowerPrice_whenUseCoverageTypeBasic()  {
        Mockito.when(brandServiceRate.getBrands()).thenReturn(createListBrandOnlyForTestPurpose());

        InsuredCar mBenzFull = new InsuredCar("Mercedes-Benz", 2019, CoverageType.basic.name());

        Double result = estimateInsuranceService.getPrice(mBenzFull);

        double sum = Constants.BASE_AMOUNT;
        sum = sum * 1.45;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int diff = currentYear - mBenzFull.getYear();
        sum -= ((diff * Constants.YEAR_PERCENT_DECREASE) * sum) / 100;
        Double expected = sum * Constants.COVERAGE_BASIC_RATE;


        Assertions.assertEquals(expected, result);
    }

    private List<Brand> createListBrandOnlyForTestPurpose(){
        List<Brand> brandList = new ArrayList<Brand>();

        Brand ford = new Brand("Ford", 1.15);
        Brand mBenz = new Brand("Mercedes-Benz", 1.45);
        Brand kia = new Brand("Kia",  1.1);

        brandList.add(ford);
        brandList.add(mBenz);
        brandList.add(kia);

        return brandList;
    }
}
