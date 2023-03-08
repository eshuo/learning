package com.wyci.utils.ip;

import com.wyci.resp.IpInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author wangyu
 * @title: IpregionUtil
 * @projectName eetrust-zt-res
 * @description: TODO
 * @date 2022/12/2215:57
 */
public class Ip2regionUtil {

    private final static Logger logger = LoggerFactory.getLogger(Ip2regionUtil.class);

    private static Searcher searcher;

    private static final Pattern SPLIT_PATTERN = Pattern.compile("\\|");

    /**
     * ip2region.db 文件路径
     */
    static String fileLocation = "classpath:ip2region.xdb";


    public static Searcher getInstance() {
        if (searcher != null) {
            return searcher;
        }
        try {
            ClassPathResource classPathResource = new ClassPathResource("ip2region.xdb");
            InputStream inputStream = classPathResource.getInputStream();
            File file = asFile(inputStream);//手动转换：InputStream To File
            final String fileUrl = file.getPath();
            byte[]  cBuff = Searcher.loadContentFromFile(fileUrl);
//            byte[] vIndex = Searcher.loadVectorIndexFromFile(fileUrl);
//            searcher = Searcher.newWithVectorIndex(fileUrl, vIndex);
            searcher = Searcher.newWithBuffer(cBuff);
        } catch (IOException e) {
            logger.error("Searcher init error", e);
            e.printStackTrace();
            return searcher;
        }


        return searcher;
    }


    public static File asFile(InputStream in) throws IOException {
        File tempFile = File.createTempFile("ip2region", ".xdb");
        tempFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(tempFile);
        IOUtils.copy(in, out);
        return tempFile;
    }



    /**
     * 搜索
     *
     * @param ip
     *
     * @return
     */
    public static IpInfo search(String ip) {
        if (StringUtils.isBlank(ip)) {
            return null;
        }
        try {
            return toIpInfo(getInstance().search(ip.trim()));
        } catch (Exception e) {
            logger.error("search error:", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 将 ip 转化为 IpInfo
     *
     * @param convertIp 转换后IP
     *
     * @return IpInfo
     */
    public static IpInfo toIpInfo(String convertIp) {
        if (convertIp == null) {
            return null;
        }
        IpInfo ipInfo = new IpInfo();
        String[] splitInfos = SPLIT_PATTERN.split(convertIp);
        // 补齐5位
        if (splitInfos.length < 5) {
            splitInfos = Arrays.copyOf(splitInfos, 5);
        }
        ipInfo.setCountry(fillZero(splitInfos[0]));
        ipInfo.setRegion(fillZero(splitInfos[1]));
        ipInfo.setProvince(fillZero(splitInfos[2]));
        ipInfo.setCity(fillZero(splitInfos[3]));
        ipInfo.setIsp(fillZero(splitInfos[4]));
        return ipInfo;
    }

    /**
     * 填充0
     *
     * @param info info
     *
     * @return info
     */
    private static String fillZero(String info) {
        if (info == null || "0".equals(info)) {
            return null;
        }
        return info;
    }


    public static void close() {
        try {
            searcher.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        final IpInfo search = Ip2regionUtil.search("192.168.1.1");

        System.out.println(search);

    }


}
