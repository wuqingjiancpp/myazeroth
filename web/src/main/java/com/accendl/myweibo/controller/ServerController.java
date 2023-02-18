package com.accendl.myweibo.controller;

import com.accendl.myweibo.dto.ServerInfo;
import com.accendl.myweibo.service.AzerothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
