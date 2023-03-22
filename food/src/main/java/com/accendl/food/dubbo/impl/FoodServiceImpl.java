package com.accendl.food.dubbo.impl;

import com.accendl.food.dto.FoodFacilityDTO;
import com.accendl.food.dto.MapLocationDTO;
import com.accendl.food.entity.FoodFacility;
import com.accendl.food.dubbo.FoodService;
import com.accendl.food.service.IFoodFacilityService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@DubboService(version = "1.0.0", protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}", timeout = 6000)
public class FoodServiceImpl implements FoodService {

    private final IFoodFacilityService foodFacilityServiceImpl;

    public FoodServiceImpl(IFoodFacilityService foodFacilityServiceImpl) {
        this.foodFacilityServiceImpl = foodFacilityServiceImpl;
    }

    @Override
    public IPage<FoodFacilityDTO> getFoodFacilityDTOPage(int pageSize, int currentPage) {
        IPage<FoodFacility> foodFacilityIPage = foodFacilityServiceImpl.getPage(pageSize, currentPage);
        Function<FoodFacility, FoodFacilityDTO> mapper = e->(
                                    FoodFacilityDTO.builder()
                                            .id(e.getId())
                                            .address(e.getAddress())
                                            .latitude(e.getLatitude())
                                            .longitude(e.getLongitude())
                                    .build());
        return foodFacilityIPage.convert(mapper);
    }

    @Override
    public int batchUpdateMapLocationDTO(List<MapLocationDTO> mapLocationDTOList) {
        return 0;
    }
}
