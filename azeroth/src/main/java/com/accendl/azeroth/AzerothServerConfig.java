package com.accendl.azeroth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AzerothServerConfig {

    @Value("${azeroth.server.host}")
    public static String serverHost;

    @Value("${azeroth.server.port}")
    public static int serverPort;

}
