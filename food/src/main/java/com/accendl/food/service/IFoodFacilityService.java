package com.accendl.food.service;

import com.accendl.food.entity.FoodFacility;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuqingjian
 * @since 2023-03-18
 */
public interface IFoodFacilityService extends IService<FoodFacility> {

    IPage<FoodFacility> getList(int pageSize, int currentPage);
}
