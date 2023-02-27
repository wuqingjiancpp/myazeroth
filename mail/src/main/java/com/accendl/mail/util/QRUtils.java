package com.accendl.mail.util;

import com.accendl.mail.service.impl.UserServiceImpl;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.netty.util.concurrent.CompleteFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class QRUtils {

    private static final Logger logger = LoggerFactory.getLogger(QRUtils.class);
    private static final String IMAGE_FORMAT = "png";
    private static final String CHARSET = "utf-8";
    private static final String BASE64_IMAGE = "data:image/png;base64,%s";

    private static final int QR_WIDTH = 300;
    private static final int QR_HEIGHT = 300;
    private static final int LOGO_WIDTH = 60;
    private static final int LOGO_HEIGHT = 60;

    public static CompletableFuture<String> generatorQRCode(String content, String logoUrl, String email) throws Exception{
        File dir = new File("QRfile");
        if (!dir.exists()){
            dir.mkdir();
        }else if (!dir.isDirectory()){
            throw new IOException("QRfile是个文件，创建目录失败");
        }
        String fileName = "QRfile/base32key-"+ email + ".png";
        BufferedImage bufferedImage = createQRCode(content, QR_WIDTH, QR_HEIGHT,
                logoUrl, LOGO_WIDTH,LOGO_HEIGHT);
        if (bufferedImage != null){
            try (OutputStream outputStream = new FileOutputStream(fileName)) {
                ImageIO.write(bufferedImage, IMAGE_FORMAT, outputStream);
            }
            return CompletableFuture.completedFuture(fileName);
        }else{
            logger.error("bufferedImage为空");
            throw new Exception("创建二维码失败");
        }
    }


    public static BufferedImage createQRCode(String content, Integer width, Integer height,
                                             String logoUrl, Integer logoWidth, Integer logoHeight) {
        if (StringUtils.hasText(content)) {
            HashMap<EncodeHintType, Comparable> hints = new HashMap<>(4);
            // 指定字符编码为utf-8
            hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
            // 指定二维码的纠错等级为中级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            // 设置图片的边距
            hints.put(EncodeHintType.MARGIN, 2);
            try {
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                    }
                }
                if (logoUrl != null) {
                    insertLogo(bufferedImage, width, height, logoUrl, logoWidth, logoHeight);
                }
                return bufferedImage;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static void insertLogo(BufferedImage source, Integer width, Integer height,
                                  String logoUrl, Integer logoWidth, Integer logoHeight) throws Exception {
        // logo 源可为 File/InputStream/URL
        if (StringUtils.hasText(logoUrl)){

            Image src = ImageIO.read(new File(logoUrl));
            // 插入LOGO
            Graphics2D graph = source.createGraphics();
            int x = (width - logoWidth) / 2;
            int y = (height - logoHeight) / 2;
            graph.drawImage(src, x, y, logoWidth, logoHeight, null);
            Shape shape = new RoundRectangle2D.Float(x, y, logoWidth, logoHeight, 6, 6);
            graph.setStroke(new BasicStroke(3f));
            graph.draw(shape);
            graph.dispose();
        }//else 没有logo一样生成二维码
    }
}
