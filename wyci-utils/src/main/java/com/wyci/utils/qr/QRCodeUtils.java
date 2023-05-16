package com.wyci.utils.qr;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-03-08 15:28
 * @Version V1.0
 */
public class QRCodeUtils {

    private static final String CHARSET = "UTF-8";

    public static final String FORMAT_NAME = "JPG";

    /**
     * 二维码尺寸
     */
    private static final int QRCODE_SIZE = 430;


    /**
     * 生成带Logo的二维码
     *
     * @param content  二维码内容
     * @param logoPath Logo
     * @param destPath 二维码输出路径
     * @param isFillet 圆角LOGO
     *
     * @throws Exception void
     */
    public static void create(String content, String logoPath, String destPath, boolean isFillet) throws Exception {
        BufferedImage image = QRCodeUtils.createImage(content, logoPath, isFillet);
        mkdirs(destPath);
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }

    /**
     * 生成不带Logo的二维码
     *
     * @param content  二维码内容
     * @param destPath 二维码输出路径
     */
    public static void create(String content, String destPath) throws Exception {
        QRCodeUtils.create(content, null, destPath, false);
    }

    /**
     * 生成带Logo的二维码，并输出到指定的输出流
     *
     * @param content  二维码内容
     * @param logoPath Logo
     * @param output   输出流
     * @param isFillet 圆角LOGO
     */
    public static void create(String content, String logoPath, OutputStream output, boolean isFillet) throws Exception {
        BufferedImage image = QRCodeUtils.createImage(content, logoPath, isFillet);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * 生成不带Logo的二维码，并输出到指定的输出流
     *
     * @param content 二维码内容
     * @param output  输出流
     *
     * @throws Exception void
     */
    public static void create(String content, OutputStream output) throws Exception {
        QRCodeUtils.create(content, null, output, false);
    }


    /**
     * 创建二维码图片
     *
     * @param content  内容
     * @param logoPath logo
     * @param isFillet 圆角LOGO
     *
     * @return 返回二维码图片
     *
     * @throws WriterException e
     * @throws IOException     BufferedImage
     */
    public static BufferedImage createImage(String content, String logoPath, boolean isFillet) throws WriterException, IOException {
        return createImage(content, logoPath, isFillet, QRCODE_SIZE, QRCODE_SIZE);
    }

    /**
     * 根据内容生成二维码信息
     *
     * @param content
     *
     * @return
     *
     * @throws WriterException
     * @throws IOException
     */
    public static BufferedImage createImage(String content) throws WriterException, IOException {
        return createImage(content, null, false, QRCODE_SIZE, QRCODE_SIZE);
    }

    /**
     * 创建二维码图片
     *
     * @param content  内容
     * @param logoPath logo
     * @param isFillet 圆角LOGO
     * @param width    宽
     * @param height   高
     *
     * @return 返回二维码图片
     *
     * @throws WriterException e
     * @throws IOException     BufferedImage
     */
    public static BufferedImage createImage(String content, String logoPath, boolean isFillet, Integer width, Integer height) throws WriterException, IOException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        // 设置二维码边的空度，非负数
        hints.put(EncodeHintType.MARGIN, 1);
        if (null == width || 0 == width) {
            width = QRCODE_SIZE;
        }
        if (null == height || 0 == height) {
            height = QRCODE_SIZE;
        }
        BitMatrix bitMatrix = encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(bitMatrixWidth, bitMatrixHeight, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < bitMatrixWidth; x++) {
            for (int y = 0; y < bitMatrixHeight; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }


        if (null == logoPath || "".equals(logoPath)) {
            return image;
        }

        // 插入图片
        QRCodeUtils.insertImage(image, logoPath, isFillet);
        return image;
    }


    public static BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {

        if (contents.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }

        if (format != BarcodeFormat.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + format);
        }

        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + width + 'x' +
                    height);
        }

        ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
        int quietZone = 4;
        if (hints != null) {
            ErrorCorrectionLevel requestedECLevel = (ErrorCorrectionLevel) hints.get(EncodeHintType.ERROR_CORRECTION);
            if (requestedECLevel != null) {
                errorCorrectionLevel = requestedECLevel;
            }
            Integer quietZoneInt = (Integer) hints.get(EncodeHintType.MARGIN);
            if (quietZoneInt != null) {
                quietZone = quietZoneInt;
            }
        }

        QRCode code = Encoder.encode(contents, errorCorrectionLevel, hints);
        return renderResult(code, width, height, quietZone);
    }


    /**
     * 对 zxing 的 QRCodeWriter 进行扩展, 解决白边过多的问题
     * <p/>
     * 源码参考 {@link com.google.zxing.qrcode.QRCodeWriter#renderResult(QRCode, int, int, int)}
     *
     * @param code
     * @param width
     * @param height
     * @param quietZone 取值 [0, 4]
     *
     * @return
     */
    private static BitMatrix renderResult(QRCode code, int width, int height, int quietZone) {
        ByteMatrix input = code.getMatrix();
        if (input == null) {
            throw new IllegalStateException();
        }

        // xxx 二维码宽高相等, 即 qrWidth == qrHeight
        int inputWidth = input.getWidth();
        int inputHeight = input.getHeight();
        int qrWidth = inputWidth + (quietZone * 2);
        int qrHeight = inputHeight + (quietZone * 2);


        // 白边过多时, 缩放
        int minSize = Math.min(width, height);
        int scale = calculateScale(qrWidth, minSize);
        if (scale > 0) {
            int padding, tmpValue;
            // 计算边框留白
            padding = (minSize - qrWidth * scale) / 4 * quietZone;
            tmpValue = qrWidth * scale + padding;
            if (width == height) {
                width = tmpValue;
                height = tmpValue;
            } else if (width > height) {
                width = width * tmpValue / height;
                height = tmpValue;
            } else {
                height = height * tmpValue / width;
                width = tmpValue;
            }
        }

        int outputWidth = Math.max(width, qrWidth);
        int outputHeight = Math.max(height, qrHeight);

        int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);
        int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
        int topPadding = (outputHeight - (inputHeight * multiple)) / 2;

        BitMatrix output = new BitMatrix(outputWidth, outputHeight);

        for (int inputY = 0, outputY = topPadding; inputY < inputHeight; inputY++, outputY += multiple) {
            // Write the contents of this row of the barcode
            for (int inputX = 0, outputX = leftPadding; inputX < inputWidth; inputX++, outputX += multiple) {
                if (input.get(inputX, inputY) == 1) {
                    output.setRegion(outputX, outputY, multiple, multiple);
                }
            }
        }

        return output;
    }


    /**
     * 如果留白超过10% , 则需要缩放
     * (10% 可以根据实际需要进行修改)
     *
     * @param qrCodeSize 二维码大小
     * @param expectSize 期望输出大小
     *
     * @return 返回缩放比例, <= 0 则表示不缩放, 否则指定缩放参数
     */
    private static int calculateScale(int qrCodeSize, int expectSize) {
        if (qrCodeSize >= expectSize) {
            return 0;
        }
        int scale = expectSize / qrCodeSize;
        int abs = expectSize - scale * qrCodeSize;
        if (abs < expectSize * 0.1) {
            return 0;
        }

        return scale;
    }


    /**
     * 添加Logo
     *
     * @param qrCode   二维码图片
     * @param logoPath Logo
     * @param isFillet 圆角
     *
     * @throws IOException void
     */
    private static void insertImage(BufferedImage qrCode, String logoPath, boolean isFillet) throws IOException {
        File file = new File(logoPath);
        if (!file.exists()) {
            return;
        }
        int QRCODE_WIDTH = qrCode.getWidth();
        int QRCODE_HEIGHT = qrCode.getHeight();

        // 获取logo图片
        BufferedImage bf = ImageIO.read(new File(logoPath));
        if (isFillet) {
            int boderSize = bf.getWidth() / 15;
            // 生成圆角边框logo
            bf = makeRoundBorder(bf, boderSize, null); // 边距为二维码图片的1/15
        }

        // logo的宽高
        int w = Math.min(bf.getWidth(), QRCODE_WIDTH * 2 / 10);
        int h = Math.min(bf.getHeight(), QRCODE_HEIGHT * 2 / 10);

        // 插入LOGO
        Graphics2D graph = qrCode.createGraphics();

        int x = (QRCODE_WIDTH - w) >> 1;
        int y = (QRCODE_HEIGHT - h) >> 1;

        graph.drawImage(bf, x, y, w, h, null);
        graph.dispose();
        bf.flush();
    }

    /**
     * fixme 边框的计算需要根据最终生成logo图片的大小来定义，这样才不会出现不同的logo原图，导致边框不一致的问题
     * <p>
     * 生成圆角图片 & 圆角边框
     *
     * @param image 原图
     * @param size  边框的边距
     * @param color 边框的颜色
     *
     * @return 返回带边框的圆角图
     */
    public static BufferedImage makeRoundBorder(BufferedImage image, int size, Color color) {
        // 将图片变成圆角
        int cornerRadius = 30;
//        if (logoStyle == QrCodeOptions.LogoStyle.ROUND) {
//            cornerRadius = 30;
        image = makeRoundedCorner(image, cornerRadius);
//        }

        int borderSize = size;
        int w = image.getWidth() + borderSize;
        int h = image.getHeight() + borderSize;
        BufferedImage output = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color == null ? Color.WHITE : color);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius,
                cornerRadius));

        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, size, size, null);
        g2.dispose();

        return output;
    }

    /**
     * 生成圆角图片
     *
     * @param image        原始图片
     * @param cornerRadius 圆角的弧度
     *
     * @return 返回圆角图
     */
    public static BufferedImage makeRoundedCorner(BufferedImage image,
                                                  int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)

        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius,
                cornerRadius));

        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }

    /**
     * 二维码解析
     *
     * @param file 二维码
     *
     * @return 返回解析得到的二维码内容
     *
     * @throws Exception String
     */
    public static String parse(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }

    /**
     * 二维码解析
     *
     * @param path 二维码存储位置
     *
     * @return 返回解析得到的二维码内容
     *
     * @throws Exception String
     */
    public static String parse(String path) throws Exception {
        return QRCodeUtils.parse(new File(path));
    }

    /**
     * 判断路径是否存在，如果不存在则创建
     *
     * @param dir 目录
     */
    public static void mkdirs(String dir) {
        if (dir != null && !"".equals(dir)) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdirs();
            }
        }
    }

}
