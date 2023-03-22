package com.accendl.web.service;

import com.accendl.food.dto.FoodFacilityDTO;
import com.accendl.food.dto.MapLocationDTO;
import com.accendl.food.dubbo.FoodService;
import com.accendl.web.baiduMap.WebServiceApi;
import com.accendl.web.dto.MapLocation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodFacilityService {

    @DubboReference(version = "1.0.0")
    private FoodService foodService;

    private final WebServiceApi webServiceApi;

    public FoodFacilityService(WebServiceApi webServiceApi) {
        this.webServiceApi = webServiceApi;
    }

    public void batchConvertMapLocation() throws Exception {
        int currentPage = 0;
        IPage<FoodFacilityDTO> foodFacilityDTOIPage = foodService.getFoodFacilityDTOPage(40, currentPage);
        List<MapLocationDTO> mapLocations = new ArrayList<>();
        for (currentPage=1; currentPage<foodFacilityDTOIPage.getPages(); currentPage++){
            List<FoodFacilityDTO> foodFacilityDTOList = foodService.getFoodFacilityDTOPage(40, currentPage).getRecords();
            List<String> gpsList = foodFacilityDTOList.parallelStream().map(e->e.getLongitude()+ "," +e.getLatitude()).toList();
            StringBuilder gpsBuilder = new StringBuilder();
            for (String gps : gpsList){
                gpsBuilder.append(gps).append(";");
            }
            List<MapLocation> mapLocationList = webServiceApi.convertGpsToBaiduLocation(gpsBuilder.toString());
            mapLocations.addAll(mapLocationList.parallelStream().map(e->MapLocationDTO.builder().build()).toList());
        }
        foodService.batchUpdateMapLocationDTO(mapLocations);

    }

}
