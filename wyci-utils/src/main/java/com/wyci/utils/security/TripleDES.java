package com.wyci.utils.security;

import com.sun.crypto.provider.SunJCE;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class TripleDES
{
    private static final String MCRYPT_TRIPLEDES = "DESede";
    private static final String TRANSFORMATION = "DESede/CBC/PKCS5Padding";

    static
    {
        Security.addProvider(new SunJCE());
    }

    public byte[] decrypt(byte[] data, byte[] key, byte[] iv)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec IvParameters = new IvParameterSpec(iv);
        cipher.init(2, sec, IvParameters);
        return cipher.doFinal(data);
    }

    public byte[] encrypt(byte[] data, byte[] key, byte[] iv)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec IvParameters = new IvParameterSpec(iv);
        cipher.init(1, sec, IvParameters);
        return cipher.doFinal(data);
    }
}
