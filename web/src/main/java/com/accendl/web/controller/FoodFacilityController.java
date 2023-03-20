package com.accendl.web.controller;

import com.accendl.web.dto.MapLocation;
import com.accendl.web.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("foodFacility")
@Slf4j
public class FoodFacilityController {

    @GetMapping("getListPage")
    public String getFoodFacilityPage() throws Exception {
        return "foodFacility/list";
    }

    @GetMapping("getBaiduMapPage")
    public String getBaiduMapPage() throws Exception {
        return "foodFacility/baiduMap";
    }

    @PostMapping("choosePlace")
    public String choosePlace(@RequestBody MapLocation mapLocation, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            return "redirect:/foodFacility/baiduMap";
        }else{
            return "foodFacility/list";
        }
    }

    @ModelAttribute
    public MapLocation getCurrentPlace(@RequestBody MapLocation mapLocation, HttpServletRequest request){
        if (mapLocation != null){
            return MapLocation.builder()
                    .address(mapLocation.getAddress())
                    .mercatorX(mapLocation.getMercatorX())
                    .mercatorY(mapLocation.getMercatorY())
                    .build();
        }else{
            String ipAddress = IpUtil.getIpAddr(request);

            return MapLocation.builder().build();
        }
    }

    @GetMapping("getList/{name}")
    public String getFoodTruck(@PathVariable String name) throws Exception {
        log.info(name.trim());
        return "foodFacility/list";
    }
    
}
