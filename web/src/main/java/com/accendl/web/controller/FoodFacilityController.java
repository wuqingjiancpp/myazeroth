package com.accendl.web.controller;

import com.accendl.web.baiduMap.WebServiceApi;
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

    private final WebServiceApi webServiceApi;

    public FoodFacilityController(WebServiceApi webServiceApi) {
        this.webServiceApi = webServiceApi;
    }

    @GetMapping("getListPage")
    public String getFoodFacilityPage() throws Exception {
        return "foodFacility/list";
    }

    @GetMapping("getBaiduMapPage")
    public String getBaiduMapPage() throws Exception {
        return "foodFacility/baiduMap";
    }

    @PostMapping("getNearbyFoodFacility")
    public String getNearbyFoodFacility(@RequestBody MapLocation mapLocation, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            return "redirect:/foodFacility/baiduMap";
        }else{

            return "foodFacility/list";
        }
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
    public MapLocation getCurrentPlace(@RequestBody(required = false) MapLocation mapLocation,
                                       HttpServletRequest request) throws Exception {
        if (mapLocation != null){
            return MapLocation.builder()
                    .address(mapLocation.getAddress())
                    .mercatorX(mapLocation.getMercatorX())
                    .mercatorY(mapLocation.getMercatorY())
                    .build();
        }else{
            String ipAddress = IpUtil.getIpAddr(request);
            MapLocation tmp = webServiceApi.getLocationByIp(ipAddress);
            return tmp;
        }
    }

    @GetMapping("getList/{name}")
    public String getFoodTruck(@PathVariable String name) throws Exception {
        log.info(name.trim());
        return "foodFacility/list";
    }
    
}
