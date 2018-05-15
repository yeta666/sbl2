package com.yeta.sbl2.wechat.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YETA
 * @date 2018/05/15/13:42
 */
public class CodeUtil {

    public static void main(String[] args) throws WriterException, IOException, NotFoundException {
        //encode();
        decode();
    }

    public static void encode() throws WriterException, IOException {

        //定义图片宽、高
        int width = 300;
        int height = 300;

        //定义图片格式
        String format = "png";

        //定义图片内容
        String content = "www.baidu.com";

        //定义二维码参数
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);

        //设置编码参数
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);

        //定义路径
        Path file = new File("F:/Projects/Java/sbl2/src/main/resources/static/img/code.png").toPath();

        //编码
        MatrixToImageWriter.writeToPath(bitMatrix, format, file);
    }

    public static void decode() throws IOException, NotFoundException {

        //定义读取对象
        MultiFormatReader multiFormatReader = new MultiFormatReader();

        //定义文件
        File file = new File("F:/Projects/Java/sbl2/src/main/resources/static/img/code.png");

        //读取文件，识别成一个图片
        BufferedImage image = ImageIO.read(file);

        //定义解码参数
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

        //定义二维码参数
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        //解码
        Result result = multiFormatReader.decode(binaryBitmap, hints);

        //打印
        System.out.println("解码结果：" + result.toString());
        System.out.println("二维码格式类型：" + result.getBarcodeFormat());
        System.out.println("二维码文本内容：" + result.getText());
    }
}
