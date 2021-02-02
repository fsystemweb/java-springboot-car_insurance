package com.insurancecar.estimator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurancecar.estimator.models.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrandRateServiceImpl implements BrandRateService{
    @Autowired
    private ObjectMapper objectMapper;

    public List<Brand> getBrands(){
        List<Brand> brandList = new ArrayList<Brand>();

        try {
            Brand[] brands = this.objectMapper.readValue(new ClassPathResource("./brand-rate.json").getFile(),
                    Brand[].class);

            brandList = new ArrayList<Brand>(Arrays.asList(brands));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return brandList;
    }
}
