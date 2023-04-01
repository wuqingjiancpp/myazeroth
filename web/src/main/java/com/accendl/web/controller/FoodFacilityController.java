package com.accendl.web.controller;

import com.accendl.web.baiduMap.WebServiceApi;
import com.accendl.web.dto.MapLocation;
import com.accendl.web.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("foodFacility")
@Slf4j
public class FoodFacilityController {

    private final WebServiceApi webServiceApi;

    public FoodFacilityController(WebServiceApi webServiceApi) {
        this.webServiceApi = webServiceApi;
    }

    @GetMapping("getListPage")
    public String getFoodFacilityPage(@ModelAttribute MapLocation mapLocation, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
        }
        return "foodFacility/list";
    }

    @GetMapping("getBaiduMapPage")
    public String getBaiduMapPage() throws Exception {
        return "foodFacility/baiduMap";
    }

    @PostMapping("getNearbyFoodFacility")
    public String getNearbyFoodFacility(@ModelAttribute MapLocation mapLocation, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            return "redirect:/foodFacility/baiduMap";
        }else{

            return "foodFacility/list";
        }
    }

    @GetMapping("choosePlace")
    public String choosePlace() throws Exception {
        return "/foodFacility/baiduMap";
    }

    @ModelAttribute
    public MapLocation getCurrentPlace(@ModelAttribute MapLocation mapLocation,
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
            if(tmp == null){
                return MapLocation.builder()
                        .address("开发环境或者调用API出错")
                        .mercatorX(0)
                        .mercatorY(0)
                        .build();
            }else{
                return tmp;
            }

        }
    }

    @GetMapping("getList/{name}")
    public String getFoodTruck(@PathVariable String name) throws Exception {
        log.info(name.trim());
        return "foodFacility/list";
    }
    
}
