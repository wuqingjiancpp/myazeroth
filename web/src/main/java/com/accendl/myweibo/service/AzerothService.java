package com.accendl.myweibo.service;

import com.accendl.azeroth.service.ServerService;
import com.accendl.myweibo.dto.ServerInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class AzerothService {

    private static final Logger logger = LoggerFactory.getLogger(AzerothService.class);

    @DubboReference(version = "1.0.0")
    private ServerService serverService;

    public ServerInfo getServerInfo() throws Exception {
        String message = serverService.info();
        Document document = DocumentHelper.parseText(message);
        Node node = document.selectSingleNode("//SOAP-ENV:Envelope/SOAP-ENV:Body/ns1:executeCommandResponse/result");

        String serverText = node.getText();
        logger.debug(node.getText());
        if (serverText.contains("AzerothCore")){
            int playerIndex = serverText.indexOf("Connected players");
            int charactersIndex = serverText.indexOf("Characters in world");
            int peakIndex = serverText.indexOf("Connection peak");
            int upTimeIndex = serverText.indexOf("Server uptime");
            int diffIndex = serverText.indexOf("Update time diff");
            int average = serverText.indexOf("average");

            String playerText = serverText.substring(playerIndex, charactersIndex);
            String charactersText = serverText.substring(charactersIndex, peakIndex);
            String peakText = serverText.substring(peakIndex, upTimeIndex);
            String upTimeText = serverText.substring(upTimeIndex, diffIndex);
            String diffText = serverText.substring(diffIndex, average);
            String averageText = serverText.substring(average);

            String[] playerStrs = playerText.split(": ");
            String[] charactersStrs = charactersText.split(": ");
            String[] peakStrs = peakText.split(": ");
            String[] upTimeStrs = upTimeText.split(": ");
            String[] diffStrs = diffText.split(": ");
            String[] averageStrs = averageText.split(": ");

            ServerInfo serverInfo = new ServerInfo();
            String playerStr = playerStrs[1];
            serverInfo.setPlayer(Integer.parseInt(playerStr.substring(0, playerStr.length()-2)));
            String charactersStr = charactersStrs[1];
            serverInfo.setCharacter(Integer.parseInt(charactersStr.substring(0, charactersStr.length()-3)));
            String peakStr = peakStrs[1];
            serverInfo.setPeak(Integer.parseInt(peakStr.substring(0, peakStr.length()-3)));
            String upTimeStr = upTimeStrs[1];
            serverInfo.setUptime(upTimeStr);
            String diffStr = diffStrs[1];
            serverInfo.setUpdateTimeDiff(Integer.parseInt(diffStr.substring(0, diffStr.length()-4)));
            String averageStr = averageStrs[1];
            serverInfo.setAverageDelay(Integer.parseInt(averageStr.substring(0, averageStr.length()-5)));
            return serverInfo;
        }else {
            ServerInfo serverInfo = new ServerInfo();
            serverInfo.setUptime("离线");
            return serverInfo;
        }

    }
}
