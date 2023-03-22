package com.accendl.food.dubbo;

import com.accendl.food.dto.FoodFacilityDTO;
import com.accendl.food.dto.MapLocationDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuqingjian
 * @since 2023-03-18
 */
public interface IFoodService {

    IPage<FoodFacilityDTO> getFoodFacilityDTOPage(int pageSize, int currentPage);

    int batchUpdateMapLocationDTO(List<MapLocationDTO> mapLocationDTOList);
}
