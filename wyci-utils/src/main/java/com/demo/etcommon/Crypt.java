package com.demo.etcommon;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-07-13 10:53
 * @Version V1.0
 */
public class Crypt {
    public static int PADDING_TYPE_ZERO = 0;
    public static int PADDING_TYPE_PKCS_1 = 1;
    public static int PADDING_TYPE_PKCS_2 = 2;
    public static int PADDING_TYPE_PKCS_5 = 5;
    public static int PADDING_TYPE_PKCS_9 = 9;
    public static int PADDING_TYPE_7816 = 128;
    public static int PADDING_TYPE_HEADLEN1 = 17;
    public static int PADDING_TYPE_HEADLEN2 = 18;
    public static int SM4_GCM_ENCRYPT = 1;
    public static int SM4_GCM_DECRYPT = 0;

    public Crypt() {
    }

    public static native long Sm3Init(byte[] var0);

    public static native int Sm3Update(long var0, byte[] var2);

    public static native byte[] Sm3Final(long var0);

    public static native void Sm3Release(long var0);

    public static native byte[] Sm3Hash(byte[] var0);

    public static native byte[] Sm4EncEcb(int var0, byte[] var1, byte[] var2);

    public static native byte[] Sm4DecEcb(int var0, byte[] var1, byte[] var2);

    public static native byte[] Sm4EncCbc(int var0, byte[] var1, byte[] var2, byte[] var3);

    public static native byte[] Sm4DecCbc(int var0, byte[] var1, byte[] var2, byte[] var3);

    public static native long Sm4GcmInit(int var0, byte[] var1, byte[] var2, byte[] var3);

    public static native int Sm4GcmUpdate(long var0, byte[] var2);

    public static native byte[] Sm4GcmFinal(long var0, byte[] var2);

    public static native String DigitalEnc(String var0, String var1);

    public static native String DigitalDec(String var0, String var1);
}
