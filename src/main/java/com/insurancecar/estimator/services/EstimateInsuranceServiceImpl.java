package com.insurancecar.estimator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurancecar.estimator.models.Brand;
import com.insurancecar.estimator.models.InsuredCar;
import com.insurancecar.estimator.utils.Constants;
import com.insurancecar.estimator.utils.CoverageType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstimateInsuranceServiceImpl implements EstimateInsuranceService{
    private final BrandRateService brandRateService;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Double getPrice(InsuredCar insuredCar){
        List<Brand> brandList = brandRateService.getBrands();


        Double rate = getRateByBrand(brandList, insuredCar.getBrand());

        // get base amount is the same for all
        double sum = Constants.BASE_AMOUNT;

        // add brand rate
        sum = sum * rate;

        // calculate age car
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int diff = currentYear - insuredCar.getYear();

        // every year of the car the price decreases YEAR_PERCENT_DECREASE %
        sum -= ((diff * Constants.YEAR_PERCENT_DECREASE) * sum) / 100;

        // check coverage type and increment with corresponding cost
        if(insuredCar.getCoverageType().matches(CoverageType.full.name()))
            sum = sum * Constants.COVERAGE_FULL_RATE;
        else
            sum = sum * Constants.COVERAGE_BASIC_RATE;

        return this.formatPrice(sum);
    }

    private Double formatPrice(Double sum){
        return Double.parseDouble(df.format(sum));
    }

    private Double getRateByBrand(List<Brand> brandList, String brand){
        Optional<Brand> brandResult = brandList.stream()
                .filter(element -> element.getName().matches(brand))
                .findFirst();

        if(brandResult.isPresent())
            return brandResult.get().getRate();

        return 0.00;
    }
}
