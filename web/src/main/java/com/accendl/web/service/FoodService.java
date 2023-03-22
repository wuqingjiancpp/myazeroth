package com.accendl.web.service;

import com.accendl.food.dto.FoodFacilityDTO;
import com.accendl.food.dto.MapLocationDTO;
import com.accendl.food.dubbo.IFoodService;
import com.accendl.web.baiduMap.WebServiceApi;
import com.accendl.web.dto.MapLocation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    @DubboReference(version = "1.0.0")
    private IFoodService iFoodService;

    private final WebServiceApi webServiceApi;

    public FoodService(WebServiceApi webServiceApi) {
        this.webServiceApi = webServiceApi;
    }

    public boolean batchConvertMapLocation() throws Exception {
        int currentPage = 0;
        int pageSize = 40;
        IPage<FoodFacilityDTO> foodFacilityDTOIPage = iFoodService.getFoodFacilityDTOPage(pageSize, currentPage);
        List<MapLocationDTO> mapLocationDTOList = new ArrayList<>();
        for (currentPage=1; currentPage<=foodFacilityDTOIPage.getPages(); currentPage++){
            IPage<FoodFacilityDTO> facilityDTOIPage = iFoodService.getFoodFacilityDTOPage(pageSize, currentPage);
            List<FoodFacilityDTO> foodFacilityDTOList = facilityDTOIPage.getRecords();
            // 调用百度地图API转换坐标，一次最多40个
            List<String> gpsList = foodFacilityDTOList.parallelStream().map(e->e.getLongitude()+ "," +e.getLatitude()).toList();
            StringBuilder gpsBuilder = new StringBuilder();
            boolean flag = false;
            for (String gps : gpsList){
                if (flag){
                    gpsBuilder.append(";");
                }else{
                    flag = true;
                }
                gpsBuilder.append(gps);
            }
            List<MapLocation> mapLocationList = webServiceApi.convertGpsToBaiduLocation(gpsBuilder.toString());

            for (int i=0; i<gpsList.size(); i++){
                MapLocation mapLocation = mapLocationList.get(i);
                mapLocationDTOList.add(MapLocationDTO.builder()
                        .id(foodFacilityDTOList.get(i).getLocationId())
                        .mercatorX(mapLocation.getMercatorX())
                        .mercatorY(mapLocation.getMercatorY())
                        .build());
            }

        }
        int count = iFoodService.batchUpdateMapLocationDTO(mapLocationDTOList);
        if (count > 0){
            return true;
        }else{
            return false;
        }
    }

}
