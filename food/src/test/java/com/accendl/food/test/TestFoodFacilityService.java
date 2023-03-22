package com.accendl.food.test;

import com.accendl.food.entity.FoodFacility;
import com.accendl.food.service.impl.FoodFacilityServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestFoodFacilityService {

    @Autowired
    private FoodFacilityServiceImpl foodFacilityService;

    @Test
    public void test(){
        IPage<FoodFacility> facilityIPage = foodFacilityService.getPage(10,2);
        System.out.println(facilityIPage.getPages());
    }
}
