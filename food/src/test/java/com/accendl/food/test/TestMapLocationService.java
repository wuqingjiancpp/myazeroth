package com.accendl.food.test;

import com.accendl.food.entity.MapLocation;
import com.accendl.food.service.impl.MapLocationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestMapLocationService {

    @Autowired
    private MapLocationServiceImpl mapLocationService;

    @Test
    public void testBatchInsert(){
        List<MapLocation> mapLocationList = new ArrayList<>();
        for (int i=0;i<10; i++){
            MapLocation mapLocation = new MapLocation(++i, BigDecimal.valueOf(1.3+i), new BigDecimal(2.1+i));
            mapLocationList.add(mapLocation);
        }
        mapLocationService.batchUpdate(mapLocationList);
    }
}
