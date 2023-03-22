package com.accendl.food.service;

import com.accendl.food.entity.MapLocation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuqingjian
 * @since 2023-03-22
 */
public interface IMapLocationService extends IService<MapLocation> {
    int batchUpdate(List<MapLocation> mapLocationList);
}
