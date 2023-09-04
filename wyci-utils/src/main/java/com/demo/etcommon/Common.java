package com.demo.etcommon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-07-13 10:52
 * @Version V1.0
 */
public class Common {
    public static final int ERROR_INVALID_PARAMETER = 87;

    public static final int ERROR_FUNCTION_FAILED = 1627;

    private static int LOGTYPE_NORMAL = 0;

    private static int LOGTYPE_ERROR = 1;

    private static int LOGTYPE_WARNING = 2;

    private static final String NATIVE_FOLDER_PATH_PREFIX = "EtCommon";

    private static File temporaryDir;

    public Common() {
    }

    private static native String GetCurrentPath();

    private static native int WriteFile(String var0, byte[] var1, boolean var2);

    private static native byte[] GetRand(int var0);

    private static native long GetTimeStamp();

    private static String byteArrayToString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (data != null && data.length > 0) {
            for (int i = 0; i < data.length; ++i) {
                int value = data[i] & 255;
                String hexValue = Integer.toHexString(value).toUpperCase();
                if (hexValue.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hexValue);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    private static String byteArrayToString(byte[] data, int offset, int len) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (data != null && data.length > 0) {
            for (int i = offset; i < len; ++i) {
                int value = data[i] & 255;
                String hexValue = Integer.toHexString(value).toUpperCase();
                if (hexValue.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hexValue);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    private static byte ascChar2Byte(char asc) {
        if (asc >= 'a' && asc <= 'f') {
            return (byte) (asc - 97 + 10);
        } else if (asc >= 'A' && asc <= 'F') {
            return (byte) (asc - 65 + 10);
        } else {
            return asc >= '0' && asc <= '9' ? (byte) (asc - 48) : 0;
        }
    }

    private static byte[] string2ByteArray(String hexStr) {
        int len = hexStr.length();
        if (0 != (len & 1)) {
            return null;
        } else {
            byte[] data = new byte[len / 2];

            for (int i = 0; i < len; i += 2) {
                int value = ascChar2Byte(hexStr.charAt(i));
                value <<= 4;
                value += ascChar2Byte(hexStr.charAt(i + 1));
                data[i / 2] = (byte) value;
            }

            return data;
        }
    }

    public static byte[] byteArrayCopyOfRange(byte[] data, int offset, int len) {
        byte[] retData = new byte[len];

        for (int i = 0; i < len; ++i) {
            retData[i] = data[offset + i];
        }

        return retData;
    }

    private static void logMsg(int msgType, String msg) {
        if (LOGTYPE_ERROR == msgType) {
            System.out.print("\u001b[31;1m" + msg + "\u001b[0m");
        } else if (LOGTYPE_WARNING == msgType) {
            System.out.print("\u001b[33;1m" + msg + "\u001b[0m");
        } else {
            System.out.print(msg);
        }

    }

    private static void logMsg(int logType, String format, Object... args) {
        String msg = String.format(format, args);
        logMsg(logType, msg);
    }

    private static void logMsg(String format, Object... args) {
        System.out.format(format, args);
    }

    private static void logArray(String title, byte[] array) {
        String strArray = byteArrayToString(array);
        logMsg(LOGTYPE_NORMAL, "%s:%s\r\n", title, strArray);
    }

    private static void logArray(String title, byte[] array, int offset, int len) {
        String strArray = byteArrayToString(array, offset, len);
        logMsg(LOGTYPE_NORMAL, "%s:%s\r\n", title, strArray);
    }

    private static synchronized void loadLibraryFromJar(String libName) throws IOException {
        if (temporaryDir == null) {
            temporaryDir = createTempDirectory("EtCommon");
            temporaryDir.deleteOnExit();
        }

        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") >= 0) {
            libName = libName + ".dll";
        } else {
            libName = libName + ".so";
        }

        File temp = new File(temporaryDir, libName);
        String libPath = "/nativelib/" + libName;

        try {
            InputStream is = Common.class.getResourceAsStream(libPath);
            if (null == is) {
                System.err.println("加载:[ " + libPath + " ] 失败,动态库不存在!");
                return;
            }
            Throwable var5 = null;

            try {
                Files.copy(is, temp.toPath(), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
            } catch (Throwable var25) {
                var5 = var25;
                throw var25;
            } finally {
                if (is != null) {
                    if (var5 != null) {
                        try {
                            is.close();
                        } catch (Throwable var24) {
                            var5.addSuppressed(var24);
                        }
                    } else {
                        is.close();
                    }
                }

            }
        } catch (IOException var28) {
            temp.delete();
            throw var28;
        } catch (NullPointerException var29) {
            temp.delete();
            throw new FileNotFoundException("File " + libPath + " was not found inside JAR.");
        }

        try {
            System.load(temp.getAbsolutePath());
        } finally {
            if (isPosixCompliant()) {
                temp.delete();
            } else {
                temp.deleteOnExit();
            }

        }

    }

    private static boolean isPosixCompliant() {
        try {
            return FileSystems.getDefault().supportedFileAttributeViews().contains("posix");
        } catch (SecurityException | FileSystemNotFoundException | ProviderNotFoundException var1) {
            return false;
        }
    }

    private static File createTempDirectory(String prefix) throws IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        File generatedDir = new File(tempDir, prefix);
        if (generatedDir.exists()) {
            File[] listFiles = generatedDir.listFiles();
            File[] var4 = listFiles;
            int var5 = listFiles.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                File file = var4[var6];
                System.out.println("Delete temp file " + file.getName());
                file.delete();
            }
        } else if (!generatedDir.mkdir()) {
            throw new IOException("Failed to create temp directory " + generatedDir.getName());
        }

        return generatedDir;
    }

    public static void nativeLoad(String libName) throws Exception {
        loadLibraryFromJar(libName);
    }

    public static void main(String[] args) {
        try {
            nativeLoad("EtCommon_vc9_x64_dbg");
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }
}
