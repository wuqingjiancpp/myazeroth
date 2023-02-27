package com.accendl.mail.service.impl;

import com.accendl.mail.service.IUserService;
import com.accendl.mail.util.QRUtils;
import io.netty.util.concurrent.CompleteFuture;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@DubboService(version = "1.0.0", protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}", timeout = 30000)
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final JavaMailSender mailSender;

    public UserServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private static final String contentTemplate = "otpauth://totp/LABEL?PARAMETERS";
    private static final String logoUrl = "image/wotlk20230202.jpg";

    /**
     * otpauth://TYPE/LABEL?PARAMETERS
     *
     * otpauth://totp/Example:alice@google.com?secret=JBSWY3DPEHPK3PXP&issuer=Example
     * otpauth://totp/:user@example.com?secret=QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK&issuer=
     * &algorithm=SHA1&digits=6&period=30
     *
     */
    @Override
    public void sendQROfGoogleAuthenticator(String email, String secret) throws Exception {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(email));
                mimeMessage.setFrom(new InternetAddress("wuqingjiancpp@qq.com"));
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setSubject("注册成功！还有最后一步");
                helper.setText("<html><body><p>你的专属密钥已生成：" +secret+ "，"+
                        "请使用Google Authenticator App扫描二维码或者" +
                        "输入密钥和Email获取6位验证码。</p>" +"\n" +
                        "<img src='cid:myazeroth'>" +
                        "<p>请妥善保管好自己的密钥，不要把邮件转发给任何人！</p>" +
                        "<p>wuqingjiancpp@qq.com和相关人员在任何情况下都不会索要密钥或者二维码。</p>"+
                        "</body>" +
                        "</html>", true);
                String content = contentTemplate.replace("LABEL", ":"+email)
                        .replace("PARAMETERS", "secret="+secret+"&issuer=");
                CompletableFuture<String> completeFuture = QRUtils.generatorQRCode(content, logoUrl, email);
                String fileName = completeFuture.get();
//                    helper.addAttachment("myazeroth.png", new File(fileName));
                helper.addInline("myazeroth", new File(fileName));
            }
        };
        try {
            logger.info("send Email to "+email+ " with secret="+secret);
            this.mailSender.send(preparator);
        }catch (Exception e){
            logger.error("send email fail "+e.getMessage());
            throw new Exception(e);
        }

    }
}
