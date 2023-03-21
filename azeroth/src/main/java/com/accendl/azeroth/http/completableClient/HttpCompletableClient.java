package com.accendl.azeroth.http.completableClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class HttpCompletableClient {

    @Value("${azeroth.server.host}")
    public String host;

    @Value("${azeroth.server.port}")
    public int port;

    public String sendCommand(String command) throws ExecutionException, InterruptedException {
        String url = "http://"+host+":"+port;
        return getHttpContent(url, command);
    }

    public String getHttpContent(String url, String command) throws ExecutionException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String xmlmsg = "<SOAP-ENV:Envelope" +
                " xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"" +
                " xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\"" +
                " xmlns:xsi=\"http://www.w3.org/1999/XMLSchema-instance\"" +
                " xmlns:xsd=\"http://www.w3.org/1999/XMLSchema\"" +
                " xmlns:ns1=\"urn:AC\">" +
                "<SOAP-ENV:Body>" +
                "<ns1:executeCommand>" +
                "<command>"+command+"</command>" +
                "</ns1:executeCommand>" +
                "</SOAP-ENV:Body>" +
                "</SOAP-ENV:Envelope>";

        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(xmlmsg))
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .header("Content-Type", "application/xml")
                .header("Accept-Encoding", "gzip, deflate, br")
                .build();

        CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(httpRequest,
                HttpResponse.BodyHandlers.ofString());

        HttpResponse<String> response = future.whenComplete((s,t)->{
            if (t==null){
                System.out.println("s="+s);
            }else{
                System.out.println("throw="+t);
                try {
                    throw new Exception(t);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).get();

        System.out.println("STATUS: " + response.statusCode());
        System.out.println("VERSION: " + response.version());
        System.out.println();

        if (response.headers() != null) {
            for (Map.Entry<String, List<String>> item: response.headers().map().entrySet()) {
                System.out.print("HEADER: " + item.getKey()+ " = " );
                for (String header : item.getValue()){
                    System.out.println(header);
                }
            }
            System.out.println();
        }

        System.out.println(response.body());
        return response.body();
    }
}
