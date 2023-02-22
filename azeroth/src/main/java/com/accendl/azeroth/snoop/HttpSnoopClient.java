/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.accendl.azeroth.snoop;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public final class HttpSnoopClient {

    @Value("${azeroth.server.host}")
    public String host;

    @Value("${azeroth.server.port}")
    public int port;

    private final HttpSnoopClientInitializer httpSnoopClientInitializer;

    public HttpSnoopClient(HttpSnoopClientInitializer httpSnoopClientInitializer) {
        this.httpSnoopClientInitializer = httpSnoopClientInitializer;
    }

    public ChannelFuture post(String command) throws Exception {

        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
             .channel(NioSocketChannel.class)
             .handler(httpSnoopClientInitializer);

            // Make the connection attempt.
            Channel ch = b.connect(host, port).sync().channel();

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

            // Prepare the HTTP request.
            HttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1, HttpMethod.POST, "/", Unpooled.copiedBuffer(xmlmsg.getBytes()));
            request.headers().set(HttpHeaderNames.HOST, host);
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
            request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);

            request.headers().set(HttpHeaderNames.AUTHORIZATION, "Basic YWRtaW46YWRtaW4=");
            request.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_XML);

            // Send the HTTP request.
            ch.writeAndFlush(request);

            // Wait for the server to close the connection.
            return ch.closeFuture().sync();
        } finally {
            // Shut down executor threads to exit.
            group.shutdownGracefully();
        }
    }


}
