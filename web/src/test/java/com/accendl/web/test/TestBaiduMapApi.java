package com.accendl.web.test;

import com.accendl.web.baiduMap.WebServiceApi;
import com.accendl.web.dto.MapLocation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestBaiduMapApi {

    @Autowired
    private WebServiceApi webServiceApi;

    @Test
    public void testGetLocationByIp() throws Exception {
         MapLocation mapLocation = webServiceApi.getLocationByIp("101.224.96.58");
         System.out.println(mapLocation);
    }

    @Test
    public void testConvertLocations() throws Exception {
        List<MapLocation> mapLocationList = webServiceApi
                .convertGpsToBaiduLocation("-118.21892734521,34.575429778924;-122.21892734521,37.575429778924");
        System.out.println(mapLocationList);
    }


}
