package com.wyci.utils.str;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @Description 字符串压缩
 * @Author wangshuo
 * @Date 2023-02-03 13:54
 * @Version V1.0
 */
public class ZipUtil {

    public final static Pattern base64Regular = Pattern.compile("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$");


    /**
     * 压缩字符串
     *
     * @return
     */
    public static String gzipCompress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
                gzip.write(str.getBytes(StandardCharsets.UTF_8));
            }
            return Base64.getEncoder().encodeToString(out.toByteArray());
//            return new BASE64Encoder().encode(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }


    /**
     * 解压字符串
     *
     * @return
     */
    public static String gzipUncompress(String str) {
        if (str == null) {
            return null;
        }
        if (!base64Regular.matcher(str).matches()) {
            return str;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
//             ByteArrayInputStream in = new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(str));
             ByteArrayInputStream in = new ByteArrayInputStream(Base64.getDecoder().decode(str));
        ) {
            try (
                    GZIPInputStream gzip = new GZIPInputStream(in)) {
                byte[] buffer = new byte[1024];
                int offset;
                while ((offset = gzip.read(buffer)) != -1) {
                    out.write(buffer, 0, offset);
                }
            }
            return out.toString("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static void main(String[] args) {


        String str = "eyJhbGciOiJIUzUxMiJ9" +
                ".eyJqdGkiOiI4MTg1Njg5NTE3NTQ2NTM2OTYiLCJzdWIiOiJ7XCJhdXRoZWRTZXRcIjowLFwiYXV0aGVudGljYXRlZFwiOnRydWUsXCJnbG9iYWxQb2xpY3lWZXJpZmllZFwiOmZhbHNlLFwiaW5zdGFuY2VDb2RlXCI6XCJVc2VyUGFzc3dvcmRBdXRoXCIsXCJsYXN0QXV0aEVyclwiOlwiXCIsXCJsb2dpbk5hbWVcIjpcImFkbWluXCIsXCJsb2dpblN0YXRlXCI6MSxcInByaW5jaXBhbFwiOntcImNsaWVudENvbnRlbnRcIjp7XCJjbGllbnRDcHVcIjoxLFwiY2xpZW50SXBcIjpcIjE5Mi4xNjguMS45NVwiLFwiY2xpZW50UHJvY2Vzc1wiOltcIldlQ2hhdC5leGVcIl0sXCJjbGllbnRUaW1lXCI6XCIxNjcyOTA3ODA3MDExXCIsXCJjbGllbnRUeXBlXCI6MSxcImNsaWVudFV1aWRcIjpcIkRCNTE0RUQyMUQwMkJENTc5MjA3NEE5MjJEOTBCQUQ2XCIsXCJ1c2VyQWdlbnRcIjpcIlpOMTRDMjFKIERCNTE0RUQyMUQwMkJENTc5MjA3NEE5MjJEOTBCQUQ2IDE5Mi4xNjguMS45NSBFNDo1NDpFODo1MTpFRTpEMSBERVNLVE9QLVBBSkhBQzl0ZXJtaW5hbENhdGVnb3J5LTFcIn0sXCJlbmFibGVDYXB0Y2hhXCI6XCIxXCIsXCJyZXN1bHRDb2RlXCI6XCIxXCIsXCJyZXN1bHRNZXNzYWdlXCI6XCLorqTor4HpgJrov4dcIixcInRlbXBvcmFyeUF1dGhcIjpmYWxzZSxcInVzZXJcIjp7XCJhdXRob3JpemVcIjpmYWxzZSxcImNyeXB0ZWRQYXNzd29yZFwiOlwiXCIsXCJkYXRhVHlwZVwiOjEsXCJlbWFpbFwiOlwibGlsQDE2My5jb21cIixcImdlbmRlclwiOlwiWjAxXCIsXCJpZFwiOlwiZmQ2NDExZGVlNzZkYTQ3MGM4ZTBmZDdkZjgxZDhmYTNcIixcImlkQ2FyZE51bWJlclwiOlwiNDEwNjAzMTk4MzExMTM5OTY5XCIsXCJpc0RlbGV0ZVwiOjAsXCJsb2dpbk5hbWVcIjpcImFkbWluXCIsXCJtb2JpbGVcIjpcIjEzMDM2MjYzMjMyXCIsXCJwYXNzd29yZFwiOlwibCsyRk9udHVsYlhwaHZXSDdQeDdHQT09XCIsXCJzZWNMZXZlbFwiOlwiMVwiLFwic2hvd051bWJlclwiOjEsXCJzdGF0dXNcIjoxLFwidXNlck5hbWVcIjpcImFkbWluXCJ9LFwidXNlcklkXCI6XCJmZDY0MTFkZWU3NmRhNDcwYzhlMGZkN2RmODFkOGZhM1wiLFwidXNlckluZm9cIjp7XCIkcmVmXCI6XCIkLnByaW5jaXBhbC51c2VyXCJ9fSxcInVzZXJcIjp7XCIkcmVmXCI6XCIkLnByaW5jaXBhbC51c2VyXCJ9fSIsImlzcyI6InVzZXIiLCJpYXQiOjE2NzUzMjg1MTIsImV4cCI6MTY3NTMzMDMxMn0.KanK0TdZVvX0acEyCtPm1yJAj8HG0DJnB_momECipWauxdpW2hX3K8deONE8sBCn10HLZKolqqtSIncYn4b41w";;

        final String gzipCompress = gzipCompress(str);

        System.out.println(gzipCompress);

    }


}
