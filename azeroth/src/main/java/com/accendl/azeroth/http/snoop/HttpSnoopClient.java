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
package com.accendl.azeroth.http.snoop;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cookie.ClientCookieEncoder;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

import static io.netty.buffer.Unpooled.wrappedBuffer;

/**
 * A simple HTTP client that prints out the content of the HTTP response to
 * {@link System#out} to test {@link }.
 */
@Slf4j
@Component
public final class HttpSnoopClient {

    @Value("${azeroth.server.host}")
    private String host;

    @Value("${azeroth.server.port}")
    private int port;

    private static final String POST_BODY =  "<SOAP-ENV:Envelope" +
            " xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"" +
            " xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\"" +
            " xmlns:xsi=\"http://www.w3.org/1999/XMLSchema-instance\"" +
            " xmlns:xsd=\"http://www.w3.org/1999/XMLSchema\"" +
            " xmlns:ns1=\"urn:AC\">" +
            "<SOAP-ENV:Body>" +
            "<ns1:executeCommand>" +
            "<command>${command}</command>" +
            "</ns1:executeCommand>" +
            "</SOAP-ENV:Body>" +
            "</SOAP-ENV:Envelope>";

    public String sendCommand(String command) throws Exception{
        String url = "http://" + host + ":" + port;
        return this.post(url, POST_BODY.replace("${command}", command));
    }

    public String post(String url, String body) throws Exception{
        URI uri = new URI(url);

        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new HttpSnoopClientInitializer(null));

            // Make the connection attempt.
            Channel ch = b.connect(host, port).sync().channel();

            // Prepare the HTTP request.
            HttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1, HttpMethod.POST, uri.toString(), wrappedBuffer(body.getBytes(CharsetUtil.UTF_8)));
            request.headers().set(HttpHeaderNames.HOST, host);
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
            request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);

            request.headers().set(HttpHeaderNames.AUTHORIZATION, "Basic YWRtaW46YWRtaW4=");
            request.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_XML);

            // Send the HTTP request.
            ch.writeAndFlush(request);

            HttpSnoopClientHandler httpSnoopClientHandler = (HttpSnoopClientHandler) ch.pipeline().last();
            String httpContent = httpSnoopClientHandler.getHttpContent();
            log.info(Thread.currentThread()+" httpContent="+httpContent);

            // Wait for the server to close the connection.
            ch.closeFuture().sync();

            return httpContent;
        } finally {
            // Shut down executor threads to exit.
            group.shutdownGracefully();
        }
    }

}
