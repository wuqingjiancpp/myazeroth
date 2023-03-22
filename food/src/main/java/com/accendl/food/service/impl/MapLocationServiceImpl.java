package com.accendl.food.service.impl;

import com.accendl.food.dto.FoodFacilityDTO;
import com.accendl.food.dto.MapLocationDTO;
import com.accendl.food.entity.MapLocation;
import com.accendl.food.mapper.MapLocationMapper;
import com.accendl.food.service.IMapLocationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuqingjian
 * @since 2023-03-22
 */
@Service
public class MapLocationServiceImpl extends ServiceImpl<MapLocationMapper, MapLocation> implements IMapLocationService {

    @Autowired
    private MapLocationMapper mapLocationMapper;

    public int batchUpdate(List<MapLocation> mapLocationList){
        return mapLocationMapper.batchInsert(mapLocationList);
    }

}
