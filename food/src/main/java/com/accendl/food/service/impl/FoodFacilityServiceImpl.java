package com.accendl.food.service.impl;

import com.accendl.food.entity.FoodFacility;
import com.accendl.food.enums.PermitStatus;
import com.accendl.food.mapper.FoodFacilityMapper;
import com.accendl.food.service.IFoodFacilityService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuqingjian
 * @since 2023-03-18
 */
@Service
public class FoodFacilityServiceImpl extends ServiceImpl<FoodFacilityMapper, FoodFacility> implements IFoodFacilityService {

    @Autowired
    private FoodFacilityMapper foodFacilityMapper;

    public IPage<FoodFacility> getList(int pageSize, int currentPage){
        FoodFacility foodFacility = new FoodFacility();
        foodFacility.setStatus(PermitStatus.APPROVED.toString());
        Wrapper<FoodFacility> wrapper = new QueryWrapper<>(foodFacility);
        return foodFacilityMapper.selectPage(PageDTO.of(currentPage, pageSize), wrapper);
    }
}
