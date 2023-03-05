package com.accendl.web.controller;

import com.accendl.web.dto.ServerInfo;
import com.accendl.web.service.AzerothService;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("server")
public class ServerController {


    private final AzerothService azerothService;

    public ServerController(AzerothService azerothService) {
        Assert.notNull(azerothService, "AzerothService must not be null!");
        this.azerothService = azerothService;
    }

    @GetMapping
    public String overview(){
        return "overview";
    }

    @ModelAttribute
    public ServerInfo getServerInfo() throws Exception{
        ServerInfo serverInfo = azerothService.getServerInfo();
        return serverInfo;
    }
}
