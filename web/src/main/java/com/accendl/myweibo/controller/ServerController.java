package com.accendl.myweibo.controller;

import com.accendl.myweibo.dto.ServerInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("server")
public class ServerController {

    @GetMapping
    public String overview(){
        return "overview";
    }

    @ModelAttribute
    public ServerInfo getServerInfo(){
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setPlayer(1000);
        serverInfo.setCharacter(0);
        serverInfo.setPeak(0);
        serverInfo.setUptime("15 day(s) 2 hour(s) 14 minute(s) 37 second(s)");
        serverInfo.setUpdateTimeDiff(15);
        serverInfo.setAverageDelay(11);
        return serverInfo;
    }
}
