package com.insurancecar.estimator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurancecar.estimator.models.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;


public class BrandRateServiceTest {

        @Mock
        private ObjectMapper objectMapper;

        @InjectMocks
        private BrandRateServiceImpl brandRateService;

        @BeforeEach
        public void init(){
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void getProducts_objectMapperIsCalled() throws IOException {
            Brand[] mockResponse = createBransdOnlyForTestPurpose();
            List<Brand> expected = createListBrandOnlyForTestPurpose();

            given(objectMapper.readValue(new ClassPathResource("./brand-rate.json").getFile(),
                    Brand[].class)).willReturn(mockResponse);


            List<Brand> result = brandRateService.getBrands();

            assertAll("Expected should be the same than result",
                    () -> assertEquals(expected.size(), result.size()),
                    () -> assertEquals(expected.get(2).getName(), result.get(2).getName()),
                    () -> assertEquals(expected.get(1).getRate(), result.get(1).getRate())
            );

        }

        private Brand[] createBransdOnlyForTestPurpose(){

            Brand ford = new Brand("Ford", 1.15);
            Brand mBenz = new Brand("Mercedes-Benz", 1.45);
            Brand kia = new Brand("Kia",  1.1);

            Brand[] brands = {ford, mBenz, kia};

            return brands;
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


