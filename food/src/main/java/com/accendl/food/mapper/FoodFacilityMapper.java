package com.accendl.food.mapper;

import com.accendl.food.entity.FoodFacility;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuqingjian
 * @since 2023-03-18
 */
public interface FoodFacilityMapper extends BaseMapper<FoodFacility> {

    int batchInsert();
}
