package com.accendl.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("foodTruck")
@Slf4j
public class FoodTruckController {

    @GetMapping("getListPage")
    public String getFoodTruckPage() throws Exception {
        return "foodTruck/list";
    }

    @GetMapping("getList/{name}")
    public String getFoodTruck(@PathVariable String name) throws Exception {
        log.info(name.trim());
        return "foodTruck/list";
    }
    
}
