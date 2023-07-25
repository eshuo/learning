//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.eetrust.etcommon;

public class Test {
    public Test() {
    }

    public static void main(String[] args) {
        try {
            Common.nativeLoad("EtCommon_vc9_x64_dbg");
        } catch (Exception var6) {
            throw new RuntimeException(var6);
        }

//        String version = Crypt.EtGetVersion();
//        System.out.println(version);
        String key = "123456781234567";
        String digital = "123";
        String encDigital = Crypt.DigitalEnc(key, digital);
        String decDigital = Crypt.DigitalDec(key, encDigital);
        System.out.println(encDigital);
        System.out.println(digital.equals(decDigital));
        digital = "12345619999999999X";
        encDigital = Crypt.DigitalEnc(key, digital);
        decDigital = Crypt.DigitalDec(key, encDigital);
        System.out.println(encDigital);
        System.out.println(digital.equals(decDigital));

        System.err.println( Crypt.DigitalEnc("18810585582", "500C6779CE15F06599B4B228D08AA576F4DA6362738E6835E48640FC037AEB71"));

    }
}
