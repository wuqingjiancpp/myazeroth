package com.accendl.food.dubbo.impl;

import com.accendl.food.dto.FoodFacilityDTO;
import com.accendl.food.dto.MapLocationDTO;
import com.accendl.food.entity.FoodFacility;
import com.accendl.food.dubbo.IFoodService;
import com.accendl.food.entity.MapLocation;
import com.accendl.food.service.IFoodFacilityService;
import com.accendl.food.service.IMapLocationService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@DubboService(version = "1.0.0", protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}", timeout = 6000)
public class IFoodServiceImpl implements IFoodService {

    private final IFoodFacilityService foodFacilityServiceImpl;
    private final IMapLocationService mapLocationServiceImpl;

    public IFoodServiceImpl(IFoodFacilityService foodFacilityServiceImpl, IMapLocationService mapLocationServiceImpl) {
        this.foodFacilityServiceImpl = foodFacilityServiceImpl;
        this.mapLocationServiceImpl = mapLocationServiceImpl;
    }

    @Override
    public IPage<FoodFacilityDTO> getFoodFacilityDTOPage(int pageSize, int currentPage) {
        IPage<FoodFacility> foodFacilityIPage = foodFacilityServiceImpl.getPage(pageSize, currentPage);
        Function<FoodFacility, FoodFacilityDTO> mapper = e->(
                                    FoodFacilityDTO.builder()
                                            .id(e.getId())
                                            .locationId(e.getLocationId())
                                            .address(e.getAddress())
                                            .latitude(e.getLatitude())
                                            .longitude(e.getLongitude())
                                    .build());
        return foodFacilityIPage.convert(mapper);
    }

    @Override
    public int batchUpdateMapLocationDTO(List<MapLocationDTO> mapLocationDTOList) {
        return mapLocationServiceImpl.batchUpdate(mapLocationDTOList.parallelStream()
                .map(e->new MapLocation(e.getId(), e.getMercatorX(),e.getMercatorY())).toList());
    }
}
