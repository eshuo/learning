package com.wyci.utils.qr;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.wyci.resp.GenerateQrCodeReq;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.Hashtable;

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
     * LOGO宽度
     */
    private static final int WIDTH = 60;

    /**
     * LOGO高度
     */
    private static final int HEIGHT = 60;

    /**
     * 创建二维码图片
     *
     * @param content    内容
     * @param logoPath   logo
     * @param isCompress 是否压缩Logo
     *
     * @return 返回二维码图片
     *
     * @throws WriterException e
     * @throws IOException     BufferedImage
     */
    public static BufferedImage createImage(String content, String logoPath, boolean isCompress) throws WriterException, IOException {
        return createImage(content, logoPath, isCompress, QRCODE_SIZE, QRCODE_SIZE);
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
     * @param content    内容
     * @param logoPath   logo
     * @param isCompress 是否压缩Logo
     * @param width      宽
     * @param height     高
     *
     * @return 返回二维码图片
     *
     * @throws WriterException e
     * @throws IOException     BufferedImage
     */
    public static BufferedImage createImage(String content, String logoPath, boolean isCompress, Integer width, Integer height) throws WriterException, IOException {
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
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
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
        QRCodeUtils.insertImage(image, logoPath, isCompress, width, height);
        return image;
    }

    /**
     * 添加Logo
     *
     * @param source     二维码图片
     * @param logoPath   Logo
     * @param isCompress 是否压缩Logo
     * @param width
     * @param height
     *
     * @throws IOException void
     */
    private static void insertImage(BufferedImage source, String logoPath, boolean isCompress, int width, int height) throws IOException {
        File file = new File(logoPath);
        if (!file.exists()) {
            return;
        }

        Image src = ImageIO.read(new File(logoPath));
        int srcWidth = src.getWidth(null);
        int srcHeight = src.getHeight(null);
        // 压缩LOGO
        if (isCompress) {
            if (srcWidth > WIDTH) {
                srcWidth = WIDTH;
            }

            if (srcHeight > HEIGHT) {
                srcHeight = HEIGHT;
            }

            Image image = src.getScaledInstance(srcWidth, srcHeight, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            // 绘制缩小后的图
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }

        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (width - srcWidth) / 2;
        int y = (height - srcHeight) / 2;
        graph.drawImage(src, x, y, srcWidth, srcHeight, null);
        Shape shape = new RoundRectangle2D.Float(x, y, srcWidth, srcWidth, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 生成带Logo的二维码
     *
     * @param content    二维码内容
     * @param logoPath   Logo
     * @param destPath   二维码输出路径
     * @param isCompress 是否压缩Logo
     *
     * @throws Exception void
     */
    public static void create(String content, String logoPath, String destPath, boolean isCompress) throws Exception {
        BufferedImage image = QRCodeUtils.createImage(content, logoPath, isCompress);
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
     * @param content    二维码内容
     * @param logoPath   Logo
     * @param output     输出流
     * @param isCompress 是否压缩Logo
     */
    public static void create(String content, String logoPath, OutputStream output, boolean isCompress) throws Exception {
        BufferedImage image = QRCodeUtils.createImage(content, logoPath, isCompress);
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

    /**
     * 生成二维码base64字符流
     *
     * @param req
     *
     * @return
     */
    public String generateQrCode(GenerateQrCodeReq req) {
        try (FastByteArrayOutputStream out = new FastByteArrayOutputStream()) {
            final BufferedImage bufferedImage = QRCodeUtils.createImage(req.getContent(), req.getLogPath(), "0".equals(req.getCompress()), req.getWidth(), req.getHeight());
            ImageIO.write(bufferedImage, QRCodeUtils.FORMAT_NAME, out);
            return Base64.getEncoder().encodeToString(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成二维码图片
     *
     * @param req
     */
    public void generateQrCodeImage(GenerateQrCodeReq req, HttpServletResponse response) {
        try {
            final BufferedImage bufferedImage = QRCodeUtils.createImage(req.getContent(), req.getLogPath(), "0".equals(req.getCompress()), req.getWidth(), req.getHeight());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageOutputStream imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
            ImageIO.write(bufferedImage, "jpeg", imageOutput);
            InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            OutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            response.setCharacterEncoding("UTF-8");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
