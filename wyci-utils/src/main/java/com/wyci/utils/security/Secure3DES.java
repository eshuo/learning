package com.wyci.utils.security;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;

public class Secure3DES {

    @Getter
    private String plainText;

    @Getter
    private String cipherText;

    private final Pattern compile = Pattern.compile("\\s*|\t|\r|\n");

    public boolean encrypt(String plainText, String secretKey, String iv)
        throws EncoderException {
        try {
            TripleDES des = new TripleDES();
            byte[] encrypted = des.encrypt(plainText.getBytes(StandardCharsets.UTF_8), secretKey.getBytes(), iv.getBytes());
            this.cipherText = Base64.encodeBase64String(encrypted);
            if ((this.cipherText != null) && (!this.cipherText.trim().isEmpty())) {
                Matcher m = compile.matcher(this.cipherText);
                this.cipherText = m.replaceAll("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new EncoderException(e.getMessage());
        }
        return true;
    }

    public boolean decrypt(String cipherText, String secretKey, String iv)
        throws EncoderException {
        try {
            byte[] data = Base64.decodeBase64(cipherText);
            TripleDES des = new TripleDES();
            byte[] decrypted = des.decrypt(data, secretKey.getBytes(), iv.getBytes());
            this.plainText = new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EncoderException(e.getMessage());
        }
        return true;
    }

}
