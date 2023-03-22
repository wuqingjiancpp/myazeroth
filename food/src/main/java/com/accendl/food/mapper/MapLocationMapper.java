package com.accendl.food.mapper;

import com.accendl.food.dto.MapLocationDTO;
import com.accendl.food.entity.MapLocation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuqingjian
 * @since 2023-03-22
 */
public interface MapLocationMapper extends BaseMapper<MapLocation> {

    int batchInsert(List<MapLocation> list);
}
